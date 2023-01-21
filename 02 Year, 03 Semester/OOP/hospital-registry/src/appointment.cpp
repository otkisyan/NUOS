#include "../include/appointment.h"
#include <iostream>

using namespace std;

istream &operator>>(istream &is, AppointmentId &d) {

    is >> d.id;

    return is;
}

ostream &operator<<(ostream &os, AppointmentId &d) {

    os << d.id;

    return os;
}

bool operator==(const AppointmentId &d1, const AppointmentId &d2) {

    return d1.id == d2.id;
}

AppointmentId::AppointmentId() : id(0) {

}

AppointmentId::AppointmentId(int id) : id(id) {

}

AppointmentId &AppointmentId::operator=(int id) {

    this->id = id;
    return *this;
}


int Appointment::count_ = 0;

Appointment::Appointment(const string &patientKey, const string &doctorKey, const Date &date) : patientKey_(patientKey),
                                                                           doctorKey_(doctorKey), date_(date) {
    count_++;
    id_ = count_ - 1;
}

Appointment::Appointment() : patientKey_(""), doctorKey_(""), date_(0, 0, 0, 0, 0) {

    count_++;
    id_ = count_ - 1;
}

Appointment::~Appointment() {

};

void Appointment::SetDate(const Date &date) {

    date_ = date;
}

void Appointment::SetPatientKey(const string &patientKey) {

    patientKey_ = patientKey;
}

void Appointment::SetDoctorKey(const string &doctorKey) {

    doctorKey_ = doctorKey;
}

Date Appointment::GetDate() const {

    return date_;
}

string Appointment::GetPatientKey() const {

    return patientKey_;
}

string Appointment::GetDoctorKey() const {

    return doctorKey_;
}

AppointmentId Appointment::GetId() const {

    return id_;
}

ostream &operator<<(ostream &os, Appointment &a) {

    os << ' ' << a.date_ << ' ' << a.doctorKey_ << ' ' << a.patientKey_ << endl;

    return os;
}

istream &operator>>(istream &is, Appointment &a) {

    is >> a.date_ >> a.doctorKey_ >> a.patientKey_;

    return is;
}

