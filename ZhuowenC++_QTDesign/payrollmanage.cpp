#include "payrollmanage.h"
#include "Employeefactory.h"

#include <fstream>
#include <sstream>
#include <string>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <set>

PayrollManage::PayrollManage()
{

}

PayrollManage::~PayrollManage()
{

}


int PayrollManage::LoadEmployeesInfoCSV(
        std::string filename)
{
    int c = 0;
    std::ifstream csvf(filename.c_str());
    if (!csvf) {
        std::cerr << "Open file " << filename << " failed!\n";
    }
    else {
        EmployeeFactory empfact;
        std::string ln;
        std::getline(csvf, ln); // skip header


        while (std::getline(csvf, ln)) {
            // Read line by line
            if (ln=="") // skip empty row
                continue;

            std::stringstream ss(ln), ss2;

            std::string fname, lname, title, add, phone, dept, str;
            int id, category, age;

            std::getline(ss, str, ','); // Employee ID
            ss2.clear();
            ss2 << str;
            ss2 >> id;
            if (ss2.fail()){
                    std::cerr << "Error: Bad employee infomation CSV file.\n";
                    break;
             }

            std::getline(ss, str, ','); // Pay category
            ss2.clear();
            ss2 << str;
            ss2 >> category;
            if (ss2.fail()){
                    std::cerr << "Error: Bad employee infomation CSV file.\n";
                    break;
             }

            std::getline(ss, lname, ','); // Last name

            std::getline(ss, fname, ','); // first name

            std::getline(ss, str, ','); // age
            ss2.clear();
            ss2 << str;
            ss2 >> age;
            if (ss2.fail()){
                    std::cerr << "Error: Bad employee infomation CSV file.\n";
                    break;
             }

            std::getline(ss, title, ','); // Job title

            std::getline(ss, add, ','); // address

            std::getline(ss, phone, ','); // phone

            std::getline(ss, dept); // department
            dept = dept.substr(0, dept.length()-1);
            Employee * emp = NULL;
            if (this->employees.find(id) == employees.end()) { // new Employee
                emp = empfact.GetEmployee((EmployeeFactory::PayType)category);

                this->employees[id] = emp;
            }
            else { // Update employee information
                emp = this->employees[id];
            }
            emp->setEmployeeID(id);
            emp->setAddress(add);
            emp->setAge(age);
            emp->setTitle(title);
            emp->setDepartment(dept);
            emp->setFirstName(fname);
            emp->setLastName(lname);
            emp->setPhone(phone);

            std::cout << emp->ToString() << std::endl;
            c++;
        }
    }

    csvf.close();
    return c;
}

int PayrollManage:: LoadEmployeesWeeklyTimeSheet(std::string filename)
{
    int c=0;

    std::ifstream csvf(filename.c_str());
    if (!csvf) {
        std::cerr << "Open file " << filename << " failed!\n";
    }
    else {

        std::string ln;
        std::getline(csvf, ln); // skip header


        while (std::getline(csvf, ln)) {
            // Read line by line
            if (ln[0]=='\r'||ln[0]=='\n') // skip empty row
                continue;

            std::stringstream ss(ln), ss2;

            std::string date, str;
            int id, payrate, hours, total=0;

            std::getline(ss, date, ','); // Date

            this->periods.insert(date);

            std::getline(ss, str, ','); // Employee ID
            ss2.clear();
            ss2 << str;
            ss2 >> id;

            std::getline(ss, str, ','); // Pay rate
            ss2.clear();
            ss2 << str;
            ss2 >> payrate;

            for (int i = 0; i < 6; i++) { // Worked hours from monday to saturday
                std::getline(ss, str, ','); // hours
                ss2.clear();
                ss2 << str;
                ss2 >> hours;
                total += hours;
            }
            std::getline(ss, str); // sunday hours
            ss2.clear();
            ss2 << str;
            ss2 >> hours;
            total += hours;

            Employee * emp = NULL;
            if (this->employees.find(id) == employees.end()) { // new Employee
               std::cerr << "Employee with ID " << id << "not exists!\n";
            }
            else { // Update employee working hours and pay information
                emp = this->employees[id];
                emp->addPeriodHoursWorked(date, total);

                StaffSalaryExempt *p = dynamic_cast<StaffSalaryExempt*>(emp);
                if (p) {
                    p->setYearlySalary(payrate);
                }
                else {
                    StudentHourly*p = dynamic_cast<StudentHourly*>(emp);
                    if (p) {
                        p->setHourlyWage(payrate);
                    }
                    else {
                       Teacher *p = dynamic_cast<Teacher*>(emp);
                        if (p) {
                            p->setYearlySalary(payrate);
                        }

                    }
                }
            }
            c++;
        }
    }

    csvf.close();

    return c;
}

void PayrollManage::SaveEmployeesPayRollCSV()
{
    std::ofstream opp1("payroll_period.csv");
    std::ofstream opp2("payroll_period_totall.csv");

    opp1 << "Employee ID,Pay Category,Salary/Wage,Hours,Bonus,Total gross pay" << std::endl;
    opp2 << "Period Date,Payroll Total, Employee Total\n";


    for (std::set<std::string>::iterator it = this->periods.begin();
         it != this->periods.end(); it++) {
        std::string period = (*it);
        printf("\n\n========================================== Payroll of Period: %s ==========================================\n", period.c_str());
        double period_total = 0;
        int employee_total = 0;
        for (std::map<int, Employee*>::iterator it2 = this->employees.begin();
             it2 != this->employees.end(); it2++) {
            Employee* b = it2->second;

            if(b->getHoursWorked().find(period) == b->getHoursWorked().end()) // not show in the period
                continue;

            double pay = b->CalculatePay(period);
            double bonus = b->CalculateBonus(period);
            int hours = b->getHoursWorked()[period];
            int category = b->getPayCategory();
            printf("Employee ID: %7d Pay Category: %d Salary/Wage: %8.2f$ Hours: %3d Bonuses: %8.2f$ Total gross pay: %8.2f$\n",
                   b->getEmployeeID(), category, pay, hours, bonus, pay + bonus);
            opp1 << b->getEmployeeID() << ',' << category << ',' <<  pay << ',' <<hours << ',' <<bonus << ',' << pay + bonus << std::endl;
            period_total += pay + bonus;
            employee_total += 1;
        }
        printf("--------------------------------------------------------------\nPayroll total of Period %s is %.2f$ with %d employees\n",
               period.c_str(), period_total, employee_total);
        opp1 << std::endl;

        opp2 << period << ',' << period_total << ',' << employee_total << std::endl;
    }
    opp1.close();
    opp2.close();

    // save by individual
    std::ofstream opi("payroll_individual.csv");
    opi << "Period,Employee ID,Pay Category,Last Name,First Name,Hours,Salary/Wage,Bonus,Total gross pay\n";

    for (std::map<int, Employee*>::iterator it2 = this->employees.begin();
         it2 != this->employees.end(); it2++) {
        Employee* b = it2->second;

        for (std::set<std::string>::iterator it = this->periods.begin();
             it != this->periods.end(); it++) {
            std::string period = (*it);

            if(b->getHoursWorked().find(period) == b->getHoursWorked().end()) // not show in the period
                continue;

            double pay = b->CalculatePay(period);
            double bonus = b->CalculateBonus(period);
            int hours = b->getHoursWorked()[period];
            int category = b->getPayCategory();
            opi << period << ',' << b->getEmployeeID() <<',' << category << ',' << b->getLastName() << ',' << b->getFirstName()
                << ',' <<hours << ',' <<pay << ',' <<bonus << ',' << pay+bonus << std::endl;
        }
    }
    opi.close();

}
