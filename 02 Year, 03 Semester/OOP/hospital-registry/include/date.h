#ifndef REGISTRY_DATE_H
#define REGISTRY_DATE_H

#include "showable.h"
#include <string>

class Date : Showable {

public:

    Date(int year, int month, int day, int hour, int minute);

    Date();

    ~Date() override;

    void SetYear(int year);

    void SetMonth(int month);

    void SetDay(int day);

    void SetHour(int hour);

    void SetMinute(int minute);

    int GetYear() const;

    int GetMonth() const;

    int GetDay() const;

    int GetHour() const;

    int GetMinute() const;

    std::string ToString() const;

    void Show() const override;

    static int GetNumberOfMonthDays(int year, int month);

    friend std::ostream &operator<<(std::ostream &os, Date &d);

    friend std::istream &operator>>(std::istream &is, Date &d);

private:

    int year_;
    int month_;
    int day_;
    int hour_;
    int minute_;

};

std::ostream &operator<<(std::ostream &os, Date &d);

std::istream &operator>>(std::istream &is, Date &d);

#endif //REGISTRY_DATE_H
