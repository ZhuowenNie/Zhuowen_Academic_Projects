#ifndef EMPLOYSALARYEXEMPT_H
#define EMPLOYSALARYEXEMPT_H

#include "Employee.h"

class StaffSalaryExempt: public Employee
{
public:
    StaffSalaryExempt();
    virtual ~StaffSalaryExempt();

    virtual double CalculatePay(std::string);

protected:
    double yearlySalary;

public:
    void setYearlySalary(double s) {yearlySalary=s;}
    double getYearlySalary()const {return yearlySalary;}
};

#endif // EMPLOYSALARYEXEMPT_H
