#ifndef EMPLOYEEBASE_H
#define EMPLOYEEBASE_H

#include <string>
#include <ctime>
#include <sstream>
#include <map>

class Employee
{
public:
    Employee();
    virtual ~Employee();

public:
    // Calculate pay for employee
    virtual double CalculatePay(std::string) = 0;
    virtual double CalculateBonus(std::string t) { return 0.; }
private:
    int employeeID;
    std::string firstname;
    std::string lastname;
    std::string phone;
    std::string address;
    int age;
    std::string title;
    std::string department;

protected:
    int paycategory;

protected:
    // Hours worked for each pay period.
    // Pair is <Sunday date for period start, hours for the period>
    std::map<std::string, double>  hoursWorked;

public:
    void addPeriodHoursWorked(std::string t, double hours) {
        this->hoursWorked[t] = hours;
    }

    std::map<std::string, double> getHoursWorked()const { return this->hoursWorked; }
public:
    static const int WeeksOfPayPeriod;


    int getEmployeeID() const { return employeeID; }
    void setEmployeeID(int id) {employeeID = id; }
    std::string getFirstName() const { return firstname; }
    void setFirstName(std::string s) { firstname = s; }
    std::string getLastName() const { return lastname; }
    void setLastName(std::string s) { lastname = s; }
    std::string getPhone() const { return phone; }
    void setPhone(std::string s) { phone = s; }
    std::string getAddress() const { return address; }
    void setAddress(std::string s) { address = s; }
    int getAge() const { return age; }
    void setAge(int s) { age = s; }
    std::string getTitle() const { return title; }
    void setTitle(std::string s) { title = s; }
    std::string getDepartment() const { return department; }
    void setDepartment(std::string s) { department = s; }
    int getPayCategory() const {return this->paycategory; }

public:

    virtual std::string ToString() const {
        std::stringstream ss;
        ss << "ID: " << employeeID << " Name: " + lastname+", "+firstname+" Phone: "+phone+" Add: "
                + address + " Age: " << age << " Dept: " + department;
        return ss.str();
    }
};

#endif // EMPLOYEEBASE_H
