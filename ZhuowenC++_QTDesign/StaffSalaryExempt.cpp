
#include "StaffSalaryExempt.h"


StaffSalaryExempt::StaffSalaryExempt():
    yearlySalary(0.)
{
    this->paycategory = 1;
}

StaffSalaryExempt::~StaffSalaryExempt()
{

}

double StaffSalaryExempt::CalculatePay(std::string s)
{
    return this->yearlySalary / 52;
}
