#ifndef PAYROLLMANAGE_H
#define PAYROLLMANAGE_H

#include "Employeefactory.h"
#include <map>
#include <set>

class PayrollManage
{
public:
    PayrollManage();
    ~PayrollManage();
public:

    int LoadEmployeesInfoCSV(std::string filename);

    int LoadEmployeesWeeklyTimeSheet(std::string filename);

    void SaveEmployeesPayRollCSV();

private:
    // <Employee ID, employee>
    std::map<int, Employee*> employees;
    // Weeks time card entry
    std::set<std::string> periods;

public:
    // <employ ID, employee>
    std::map<int, Employee*> getEmployees() const {
        return this->employees; }

    std::set<std::string> getAllPeriods() const {
        return this->periods;
    }
};

#endif // PAYROLLMANAGE_H
