#ifndef VOLUNTEERPROBONOEMPLOYEE_H
#define VOLUNTEERPROBONOEMPLOYEE_H

#include "Employee.h"
#include <map>
#include <ctime>

class Volunteer : public Employee
{
public:
   Volunteer();
    virtual ~Volunteer();

    virtual double CalculatePay(std::string date);



};

#endif // VOLUNTEERPROBONOEMPLOYEE_H
