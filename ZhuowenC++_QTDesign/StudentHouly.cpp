#include "StudentHourly.h"

const int StudentHourly::weekHoursLimit = 40;
const double StudentHourly::excessWageRatio = 1.5;


StudentHourly::StudentHourly():
    normHourlyWage(0.)
{
    this->paycategory = 2;
}

StudentHourly::~StudentHourly()
{

}



double StudentHourly::CalculatePay(std::string date)
{
    double pay = 0;
    double hours = this->hoursWorked[date];
    if (hours <= StudentHourly::weekHoursLimit) {
        pay += hours*normHourlyWage;
    }
    else {
        pay += hours*normHourlyWage + (hours-weekHoursLimit)*normHourlyWage*excessWageRatio;
    }
    return pay;

}
