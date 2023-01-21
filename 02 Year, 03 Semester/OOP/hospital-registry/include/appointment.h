#ifndef REGISTRY_APPOINTMENT_H
#define REGISTRY_APPOINTMENT_H

#include "../include/date.h"

struct AppointmentId {

    int id;

    explicit AppointmentId(int id);

    AppointmentId();

    AppointmentId &operator=(int id);
};

std::istream &operator>>(std::istream &is, AppointmentId &d);

std::ostream &operator<<(std::ostream &os, AppointmentId &d);

bool operator==(const AppointmentId &d1, const AppointmentId &d2);


class Appointment {

public:

    Appointment(const std::string &patientKey, const std::string &doctorKey, const Date &date);

    Appointment();

    ~Appointment();

    void SetDate(const Date &date);

    void SetPatientKey(const std::string &patientKey);

    void SetDoctorKey(const std::string &doctorKey);

    Date GetDate() const;

    std::string GetPatientKey() const;

    std::string GetDoctorKey() const;

    AppointmentId GetId() const;

    friend std::ostream &operator<<(std::ostream &os, Appointment &a);

    friend std::istream &operator>>(std::istream &is, Appointment &a);

private:

    static int count_;
    AppointmentId id_;
    std::string patientKey_;
    std::string doctorKey_;
    Date date_;

};

std::ostream &operator<<(std::ostream &os, Appointment &a);

std::istream &operator>>(std::istream &is, Appointment &a);

#endif //REGISTRY_APPOINTMENT_H
