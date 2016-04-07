#include "UI.h"
#include "ui_tbwwindow.h"
#include <sstream>
#include <QMessageBox>
#include <QFileDialog>

tbwWindow::tbwWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::tbwWindow),
    pmng(new PayrollManage)
{
    ui->setupUi(this);

    setWindowFlags(Qt::WindowMinimizeButtonHint);

}

tbwWindow::~tbwWindow()
{
    delete ui;
}

void tbwWindow::on_pushButton_clicked()
{

    QString path = QFileDialog::getOpenFileName(this,
             tr("Open CSV File"), "~/", tr("XML Files (*.xml)"));
    QMessageBox::information(this, "File", path);


    QTableWidget * tb = this->ui->tableWidget;


    tb->setRowCount(3);
    tb->setColumnCount(2);

    QStringList labels;
    labels << tr("File Name") << tr("Size")  << tr("date");
    tb->setHorizontalHeaderLabels(labels);
    QString ss[3] = {"a", "b", "c"};
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 2; j++)
            tb->setItem(i, j, new QTableWidgetItem(ss[i]+"file name"));

}

void tbwWindow::on_tableWidget_activated(const QModelIndex &index)
{

}



void tbwWindow::on_btnLoadEmployee_clicked()
{
    QString path = QFileDialog::getOpenFileName(this,
             tr("Open Employees information file"), "~/", tr("CSV Files (*.csv)"));
    if (path.compare(QString("")) == 0)
        return;


    int c = this->pmng->LoadEmployeesInfoCSV(path.toStdString());

    std::stringstream text;
    text << "Loaded " << c << " employees\' information.";
    QMessageBox::information(this, tr("Sucesss"), tr(text.str().c_str()));

    QTableWidget* tb = ui->tableWidget;
    tb->setColumnCount(9);
    tb->setRowCount(c);

    QStringList labels;
    labels << tr("ID") << tr("Pay Category") << tr("Last Name") << tr("First Name") << tr("Age")
           << tr("Job Title") << tr("Address") << tr("Phone") << tr("Department");
    tb->setHorizontalHeaderLabels(labels);
    std::map<int, Employee*> employees = pmng->getEmployees();
    int row = 0;
    for ( std::map<int, Employee*>::iterator it = employees.begin();
          it != employees.end(); it++) {
        Employee* emp = it->second;
        text.str("");
        text.clear();
        text << emp->getEmployeeID();
        tb->setItem(row, 0, new QTableWidgetItem(QString(text.str().c_str())));
        text.str("");
        text.clear();
        text << emp->getPayCategory();
        tb->setItem(row, 1, new QTableWidgetItem(QString(text.str().c_str())));
        tb->setItem(row, 2, new QTableWidgetItem(QString(emp->getFirstName().c_str())));
        tb->setItem(row, 3, new QTableWidgetItem(QString(emp->getLastName().c_str())));
        text.str("");
        text.clear();
        text << emp->getAge();
        tb->setItem(row, 4, new QTableWidgetItem(QString(text.str().c_str())));
        tb->setItem(row, 5, new QTableWidgetItem(QString(emp->getTitle().c_str())));
        tb->setItem(row, 6, new QTableWidgetItem(QString(emp->getAddress().c_str())));
        tb->setItem(row, 7, new QTableWidgetItem(QString(emp->getPhone().c_str())));
        tb->setItem(row, 8, new QTableWidgetItem(QString(emp->getDepartment().c_str())));
        row++;
    }
}

void tbwWindow::on_btnLoadHours_clicked()
{
    if (pmng->getEmployees().size() == 0) {
        QMessageBox::warning(this, tr("Warning"), tr("Must load employees\'s information first!"));
        return;
    }
    QString path = QFileDialog::getOpenFileName(this,
             tr("Open weekly worked hours file"), "~/", tr("CSV Files (*.csv)"));
    if (path.compare(QString("")) == 0)
        return;

    int c = this->pmng->LoadEmployeesWeeklyTimeSheet(path.toStdString());

    std::stringstream text;
    text << "Loaded " << c << " weekly worked hours information.";
    QMessageBox::information(this, tr("Sucesss"), tr(text.str().c_str()));

    QTableWidget* tb = ui->tableWidget;
    tb->clear();

    tb->setColumnCount(9);
    QStringList labels;
    labels << tr("Period") << tr("Employee ID") << tr("Pay Category") << tr("Last Name") << tr("First Name")
           << tr("Hours") << tr("Salary/Wage$") << tr("Bonus$") << tr("Total gross pay$");
    tb->setHorizontalHeaderLabels(labels);
    std::map<int, Employee*> employees = pmng->getEmployees();
    int row = 0;
    std::set<std::string> periods = pmng->getAllPeriods();
    for (std::set<std::string>::iterator it = periods.begin();
         it != periods.end(); it++) { // Period by period
        std::string period = (*it);

        for (std::map<int, Employee*>::iterator it2 = employees.begin();
             it2 != employees.end(); it2++) { // employee by employee
            Employee* emp = it2->second;

            if(emp->getHoursWorked().find(period) == emp->getHoursWorked().end()) // not show in the period
                continue;

            double pay = emp->CalculatePay(period);
            double bonus = emp->CalculateBonus(period);
            int hours = emp->getHoursWorked()[period];
            int category = emp->getPayCategory();
            tb->insertRow(row);
            tb->setItem(row, 0, new QTableWidgetItem(QString(period.c_str())));

            QTableWidgetItem *item = new QTableWidgetItem;
            item->setData(Qt::EditRole, emp->getEmployeeID());
            tb->setItem(row, 1, item);

            item = new QTableWidgetItem;
            item->setData(Qt::EditRole, category);
            tb->setItem(row, 2, item);

            tb->setItem(row, 3, new QTableWidgetItem(QString(emp->getLastName().c_str())));
            tb->setItem(row, 4, new QTableWidgetItem(QString(emp->getFirstName().c_str())));

            item = new QTableWidgetItem;
            item->setData(Qt::EditRole, hours);
            tb->setItem(row, 5, item);

            item = new QTableWidgetItem;
            item->setData(Qt::EditRole, pay);
            tb->setItem(row, 6, item);

            item = new QTableWidgetItem;
            item->setData(Qt::EditRole, bonus);
            tb->setItem(row, 7, item);

            item = new QTableWidgetItem;
            item->setData(Qt::EditRole, pay+bonus);
            tb->setItem(row, 8, item);

            row++;
        }
    }
}

void tbwWindow::on_btnCalculatePay_clicked()
{

}

void tbwWindow::on_btnSaveCSV_clicked()
{
    if (pmng->getEmployees().size() == 0 || pmng->getAllPeriods().size() == 0)
    {
        QMessageBox::warning(this, tr("Warning"), tr("Must load employees\'s information and worked hours first!"));
        return;
    }
    pmng->SaveEmployeesPayRollCSV();
    QMessageBox::information(this, tr("Sucesss"), tr("Records saved!"));

}
