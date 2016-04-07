#include "Employeefactory.h"

EmployeeFactory::EmployeeFactory()
{

}

EmployeeFactory::~EmployeeFactory()
{

}


Employee *EmployeeFactory::GetEmployee(PayType type)
{
    Employee* emp = NULL;
    switch(type) {
    case SalaryExempt:
    {
        emp = new StaffSalaryExempt;
        break;
    }
    case HourlyNonExempt:
    {
        emp = new StudentHourly;
        break;
    }
    case SalaryExemptPlusBonus:
    {
        emp = new Teacher;
        break;
    }
    case VolunteerProBono:
    {
        emp = new Volunteer;
        break;
    }
    }
    return emp;
}

void FreeEmployee(Employee* emp)
{
    delete emp;
}
