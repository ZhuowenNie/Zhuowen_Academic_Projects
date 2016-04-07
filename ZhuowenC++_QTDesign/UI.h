#ifndef TBWWINDOW_H
#define TBWWINDOW_H

#include <QMainWindow>

#include "payrollmanage.h"

namespace Ui {
class tbwWindow;
}

class tbwWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit tbwWindow(QWidget *parent = 0);
    ~tbwWindow();

private slots:
    void on_pushButton_clicked();

    void on_tableWidget_activated(const QModelIndex &index);

    void on_btnLoadEmployee_clicked();

    void on_btnLoadHours_clicked();

    void on_btnCalculatePay_clicked();

    void on_btnSaveCSV_clicked();

private:
    Ui::tbwWindow *ui;

private:
    PayrollManage *pmng;
};

#endif // TBWWINDOW_H
