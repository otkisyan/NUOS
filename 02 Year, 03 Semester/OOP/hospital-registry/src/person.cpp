#include "../include/person.h"
#include <iostream>
#include <regex>

using namespace std;

Person::Person(const string &name, const string &surname) : name_(name), surname_(surname) {

}

Person::Person() : name_(""), surname_("") {

}

Person::~Person() {

}

void Person::SetName(const string &name) {

 /*   // regex nameRegex("[\\s\\d]+");

    if(regex_search(name, regex("[\\s\\d]+"))){

        throw invalid_argument("Invalid name");
    }
*/
    name_ = name;
}

void Person::SetSurname(const string &surname) {

    surname_ = surname;
}


string Person::GetName() const {

    return name_;
}

string Person::GetSurname() const {

    return surname_;
}

string Person::ToString() const {

    return "Ім'я: " + name_ + '\n'
           + "Фамілія:" + surname_ + '\n';

}

void Person::Show() const {

    cout << ToString();
}

