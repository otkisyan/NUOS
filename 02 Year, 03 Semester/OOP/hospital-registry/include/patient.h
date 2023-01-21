#ifndef REGISTRY_PATIENT_H
#define REGISTRY_PATIENT_H

#include "person.h"

struct PatientId {

    int id;

    explicit PatientId(int id);

    PatientId();

    PatientId &operator=(int id);

};

std::istream &operator>>(std::istream &is, PatientId &d);

std::ostream &operator<<(std::ostream &os, PatientId &d);

bool operator==(const PatientId &d1, const PatientId &d2);


class Patient : public Person {

public:

    Patient(const std::string &name, const std::string &surname, const std::string &phone, int age);

    Patient();

    ~Patient() override;

    std::string ToString() const override;

    void Show() const override;

    void SetPhone(const std::string &phone);

    void SetAge(int age);

    std::string GetPhone() const;

    int GetAge() const;

    PatientId GetId() const;

    friend std::ostream &operator<<(std::ostream &os, Patient &p);

    friend std::istream &operator>>(std::istream &is, Patient &p);

private:

    static int count_;
    PatientId id_;
    std::string phone_;
    int age_;

};

std::ostream &operator<<(std::ostream &os, Patient &p);

std::istream &operator>>(std::istream &is, Patient &p);

#endif //REGISTRY_PATIENT_H
