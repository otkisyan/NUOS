#include "../include/doctor.h"
#include <iostream>

using namespace std;

istream &operator>>(istream &is, DoctorId &d) {

    is >> d.id;

    return is;
}

ostream &operator<<(ostream &os, DoctorId &d) {

    os << d.id;

    return os;
}

bool operator==(const DoctorId &d1, const DoctorId &d2) {

    return d1.id == d2.id;
}

DoctorId::DoctorId() : id(0) {

}

DoctorId::DoctorId(int id) : id(id) {

}

DoctorId &DoctorId::operator=(int id) {

    this->id = id;
    return *this;
}

int Doctor::count_ = 0;

Doctor::Doctor(const std::string &name, const std::string &surname, const std::string &qualification) : Person(name,
                                                                                                               surname),
                                                                                                        qualification_(
                                                                                                                qualification) {
    count_++;
    id_ = count_ - 1;
}

Doctor::Doctor() : Person("", ""), qualification_("") {

    count_++;
    id_ = count_ - 1;
}

Doctor::~Doctor() {

}


string Doctor::ToString() const {

    return "Номер лікаря: " + to_string(id_.id) + "\n"
           + "Ім'я лікаря: " + name_ + "\n"
           + "Фамілія лікаря: " + surname_ + "\n"
           + "Кваліфікація лікаря: " + qualification_ + "\n";

}

void Doctor::Show() const {

    cout << ToString();
}

void Doctor::SetQualification(const string &qualification) {

    qualification_ = qualification;

}

string Doctor::GetQualification() const {

    return qualification_;
}

DoctorId Doctor::GetId() const {

    return id_;
}

ostream &operator<<(ostream &os, Doctor &d) {

    os << ' ' << d.name_ << ' ' << d.surname_ << ' ' << d.qualification_ << ' ' << endl;

    return os;
}

istream &operator>>(istream &is, Doctor &d) {

    is >> d.name_ >> d.surname_ >> d.qualification_;

    return is;
}

