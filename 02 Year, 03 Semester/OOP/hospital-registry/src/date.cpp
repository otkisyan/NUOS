#include "../include/date.h"
#include <iostream>

using namespace std;

Date::Date(int year, int month, int day, int hour, int minute) : year_(year), month_(month), day_(day), hour_(hour),
                                                                 minute_(minute) {

}

Date::Date() : year_(0), month_(0), day_(0), hour_(0), minute_(0) {

}

Date::~Date() {

}

string Date::ToString() const {

    string currentDay = to_string(day_);
    string currentMonth = to_string(month_);
    string currentYear = to_string(year_);
    string currentHour = to_string(hour_);
    string currentMinute = to_string(minute_);

    if (day_ < 10) {

        currentDay = '0' + to_string(day_);
    }

    if (month_ < 10) {

        currentMonth = '0' + to_string(month_);
    }

    if (hour_ < 10) {

        currentHour = '0' + to_string(hour_);
    }

    if (minute_ < 10) {

        currentMinute = '0' + to_string(minute_);
    }

    string date = currentDay + '.' + currentMonth + '.' + currentYear + ", " + currentHour + ':' + currentMinute;

    return date;
}

void Date::Show() const {

    cout << ToString();
}

void Date::SetYear(int year) {

    year_ = year;
}

void Date::SetMonth(int month) {

    month_ = month;
}

void Date::SetDay(int day) {

    day_ = day;
}

void Date::SetHour(int hour) {

    hour_ = hour;
}

void Date::SetMinute(int minute) {

    minute_ = minute;
}

int Date::GetYear() const {

    return year_;
}

int Date::GetMonth() const {

    return month_;
}

int Date::GetDay() const {

    return day_;
}

int Date::GetHour() const {

    return hour_;
}

int Date::GetMinute() const {

    return minute_;
}

int Date::GetNumberOfMonthDays(int year, int month) {

    int leap = (1 - (year % 4 + 2) % (year % 4 + 1)) * ((year % 100 + 2) % (year % 100 + 1)) +
               (1 - (year % 400 + 2) % (year % 400 + 1));

    return 28 + ((month + (month / 8)) % 2) + 2 % month + ((1 + leap) / month) + (1 / month) - (leap / month);
}


ostream &operator<<(ostream &os, Date &d) {

    os << d.day_ << ' ' << d.month_ << ' ' << d.year_ << ' ' << d.hour_ << ' ' << d.minute_;

    return os;
}

istream &operator>>(istream &is, Date &d) {

    is >> d.day_ >> d.month_ >> d.year_ >> d.hour_ >> d.minute_;

    return is;
}

