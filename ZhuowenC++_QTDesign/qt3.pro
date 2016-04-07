#-------------------------------------------------
#
# Project created by QtCreator 2015-12-02T09:04:27
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Payroll
TEMPLATE = app


SOURCES += main.cpp\
    payrollmanage.cpp \
    Employee.cpp \
    Employeefactory.cpp \
    StudentHouly.cpp \
    StaffSalaryExempt.cpp \
    Teacher.cpp \
    Volunteer.cpp \
    UI.cpp

HEADERS  += \
    payrollmanage.h \
    Employee.h \
    Employeefactory.h \
    StudentHourly.h \
    StaffSalaryExempt.h \
    Teacher.h \
    Volunteer.h \
    UI.h

FORMS    += tbwwindow.ui
