#ifndef EMPLOYEEFACTORY_H
#define EMPLOYEEFACTORY_H

#include  "Employee.h"
#include "StudentHourly.h"
#include "StaffSalaryExempt.h"
#include "Teacher.h"
#include "Volunteer.h"

class EmployeeFactory
{
public:
    // Pay categories
    enum PayType {
        SalaryExempt = 1,
        HourlyNonExempt = 2,
        SalaryExemptPlusBonus = 3,
        VolunteerProBono = 4
    };

public:
    EmployeeFactory();
    virtual ~EmployeeFactory();

    Employee *GetEmployee(PayType type);
    void FreeEmployee(Employee* emp);
};

#endif // EMPLOYEEFACTORY_H
