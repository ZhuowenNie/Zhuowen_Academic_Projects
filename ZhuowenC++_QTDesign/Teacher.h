#ifndef SALARYEXEMPTPLUSBONUSEMPLOYEE_H
#define SALARYEXEMPTPLUSBONUSEMPLOYEE_H

#include "StaffSalaryExempt.h"
#include <map>
#include <ctime>

class Teacher : public StaffSalaryExempt
{
public:
    Teacher();
    ~Teacher();

    virtual double CalculatePay(std::string);
    virtual double CalculateBonus(std::string);
private:
    // Bonus pay for holiday like Thanksgiving or Christmas
    static std::map<std::string, double> holidayBonus;

public:
    static void setHolidayBonus() {
        //struct std::tm t1 = {0, 0, 0, 26, 11, 2015-1900}; // Thanksgiving
        holidayBonus["2015-11-26"] = 100;
        //struct std::tm t2 = {0, 0, 0, 25, 12, 2015-1900}; // Christmas
        holidayBonus["2015-12-25"] = 200;
    }
};

#endif // SALARYEXEMPTPLUSBONUSEMPLOYEE_H
