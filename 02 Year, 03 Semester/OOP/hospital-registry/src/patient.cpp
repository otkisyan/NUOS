#include "../include/patient.h"
#include <iostream>

using namespace std;


istream &operator>>(istream &is, PatientId &d) {

    is >> d.id;

    return is;
}

ostream &operator<<(ostream &os, PatientId &d) {

    os << d.id;

    return os;
}

bool operator==(const PatientId &d1, const PatientId &d2){

    return d1.id == d2.id;
}

PatientId &PatientId::operator=(int id) {

    this->id = id;
    return *this;
}

PatientId::PatientId() : id(0) {

}

PatientId::PatientId(int id) : id(id) {

}


int Patient::count_ = 0;

Patient::Patient(const std::string &name, const std::string &surname, const std::string &phone, int age) : Person(name, surname),
                                                                       phone_(phone), age_(age) {
    count_++;
    id_ = count_ - 1;
}

Patient::Patient() : Person("", ""), phone_(""), age_(0) {

    count_++;
    id_ = count_ - 1;

}


Patient::~Patient() {

}

void Patient::SetPhone(const string &phone) {

    phone_ = phone;
}

void Patient::SetAge(int age) {

    age_ = age;
}

string Patient::GetPhone() const {

    return phone_;
}

int Patient::GetAge() const {
    
    return age_;
}

PatientId Patient::GetId() const {

    return id_;
}

string Patient::ToString() const {

    return "Номер пацієнта: " + to_string(id_.id) + "\n"
           + "Ім'я пацієнта: " + name_ + "\n"
           + "Фамілія пацієнта: " + surname_ + "\n"
           + "Вік пацієнта: " + to_string(age_) + "\n"
           + "Номер телефону пацієнта: " + phone_ + "\n";
}

void Patient::Show() const {

    cout << ToString();
}

ostream &operator<<(ostream &os, Patient &p) {

    os <<  ' ' <<  p.name_ << ' ' << p.surname_ << ' ' << p.age_ << ' ' << p.phone_ << endl;
    return os;
}

istream &operator>>(istream &is, Patient &p) {

    is >> p.name_ >> p.surname_ >> p.age_ >> p.phone_;

    return is;
}
