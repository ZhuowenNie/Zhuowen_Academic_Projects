#include "Teacher.h"
#include <sstream>
#include <ctime>
#include <string>

std::map<std::string, double> Teacher::holidayBonus =
        std::map<std::string, double>();


Teacher::Teacher()

{
    this->paycategory =3;
    this->yearlySalary = 0.;
    setHolidayBonus();
}

Teacher::~Teacher()
{

}

double Teacher::CalculatePay(std::string t)
{
    double pay = this->yearlySalary / 52;
    return pay;
}

double Teacher::CalculateBonus(std::string t)
{
    double bonus = 0;
    t = t.replace(4, 1, " ");
    t = t.replace(7, 1, " ");
    std::stringstream ss(t);
    int y, m, d;
    ss >> y >> m >> d;
    struct std::tm tm2 = {0, 0, 0, d, m, y-1900 };

    std::time_t x = std::mktime(&tm2);
    for (std::map<std::string, double>::iterator it = holidayBonus.begin();
         it!=holidayBonus.end(); it++) {
        std::string t2 = it->first;
        t2 = t2.replace(4, 1, " ");
        t2 = t2.replace(7, 1, " ");
        std::stringstream ss(t2);
        ss >> y >> m >> d;
        struct std::tm tm2 = {0, 0, 0, d, m, y-1900 };
        std::time_t y = std::mktime(&tm2);
        if (std::difftime(y, x)/(24*60*60) < 7 && std::difftime(y, x)/(24*60*60) >=0) {
            bonus += it->second;
        }
    }
    return bonus;
}
