#ifndef REGISTRY_DOCTOR_H
#define REGISTRY_DOCTOR_H

#include "person.h"

struct DoctorId {

    int id;

    explicit DoctorId(int id);

    DoctorId();

    DoctorId &operator=(int id);
};

std::istream &operator>>(std::istream &is, DoctorId &d);

std::ostream &operator<<(std::ostream &os, DoctorId &d);

bool operator==(const DoctorId &d1, const DoctorId &d2);


class Doctor : public Person {

public:

    Doctor(const std::string &name, const std::string &surname, const std::string &qualification);

    Doctor();

    ~Doctor() override;

    std::string ToString() const override;

    void Show() const override;

    void SetQualification(const std::string &qualification);

    std::string GetQualification() const;

    DoctorId GetId() const;

    friend std::ostream &operator<<(std::ostream &os, Doctor &d);

    friend std::istream &operator>>(std::istream &is, Doctor &d);

private:

    static int count_;
    DoctorId id_;
    std::string qualification_;

};

std::ostream &operator<<(std::ostream &os, Doctor &d);

std::istream &operator>>(std::istream &is, Doctor &d);

#endif //REGISTRY_DOCTOR_H
