#ifndef EMPLOYEEHOURLYNONEXEMPT_H
#define EMPLOYEEHOURLYNONEXEMPT_H

#include "Volunteer.h"

class StudentHourly : public Employee
{
public:
   StudentHourly();

    virtual ~StudentHourly();

    virtual double CalculatePay(std::string date);


private:

    // Top limit of working hours for a week before overtime pay
    static const int weekHoursLimit;

    // Ratio of wage in excess working time in a single week
    static const double excessWageRatio;

    double normHourlyWage;

public:
    void setHourlyWage(double h) {normHourlyWage=h;}
    double getHourlyWage() const {return normHourlyWage;}

};

#endif // EMPLOYEEHOURLYNONEXEMPT_H
