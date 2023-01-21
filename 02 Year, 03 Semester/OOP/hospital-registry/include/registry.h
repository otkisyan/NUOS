#ifndef REGISTRY_REGISTRY_H
#define REGISTRY_REGISTRY_H

#include <vector>
#include "patient.h"
#include "doctor.h"
#include "appointment.h"

class Registry {

public:

    Registry(const std::vector<std::pair<std::string, Patient>> &patients,
             const std::vector<std::pair<std::string, Doctor>> &doctors,
             const std::vector<std::pair<std::string, Appointment>> &appointments);

    Registry();

    ~Registry();

    std::vector<std::pair<std::string, Patient>> GetPatients() const;

    std::vector<std::pair<std::string, Doctor>> GetDoctors() const;

    std::vector<std::pair<std::string, Appointment>> GetAppointments() const;

    void SetPatients(const std::vector<std::pair<std::string, Patient>> &patients);

    void SetDoctors(const std::vector<std::pair<std::string, Doctor>> &doctors);

    void SetAppointments(const std::vector<std::pair<std::string, Appointment>> &appointments);

    void AddPatient();

    void AddDoctor();

    void ScheduleAppointment();

    void RemovePatient();

    void RemoveDoctor();

    void RemoveAppointment();

    void ShowAllPatients();

    void ShowAllDoctors();

    void ShowAllAppointments();

    void ShowPatientAppointments();

    void ShowDoctorAppointments();


private:

    std::vector<std::pair<std::string, Patient>> patients_;
    std::vector<std::pair<std::string, Doctor>> doctors_;
    std::vector<std::pair<std::string, Appointment>> appointments_;

    std::vector<std::pair<std::string, Patient>>::iterator Find(const PatientId &patientId);

    std::vector<std::pair<std::string, Doctor>>::iterator Find(const DoctorId &doctorId);

    std::vector<std::pair<std::string, Appointment>>::iterator Find(const AppointmentId &appointmentId);

    std::vector<std::pair<std::string, Appointment>> FindAppointments(const DoctorId &doctorId);

    std::vector<std::pair<std::string, Appointment>> FindAppointments(const PatientId &patientId);

    void Add(const Doctor &doctor);

    void Add(const Patient &patient);

    void ScheduleAppointment(const DoctorId &doctorId, const PatientId &patientId, const Date &date);

    void Remove(const PatientId &patientId);

    void Remove(const DoctorId &doctorId);

    void Remove(const AppointmentId &appointmentId);

    void ShowAll(const std::vector<std::pair<std::string, Patient>> &patients) const;

    void ShowAll(const std::vector<std::pair<std::string, Doctor>> &doctors) const;

    void
    ShowAll(const std::vector<std::pair<std::string, Appointment>> &appointments,
            const std::vector<std::pair<std::string, Patient>> &patients) const;

    void
    ShowAll(const std::vector<std::pair<std::string, Appointment>> &appointments,
            const std::vector<std::pair<std::string, Doctor>> &doctors) const;


    void ShowAll(const std::vector<std::pair<std::string, Appointment>> &appointments,
                 const std::vector<std::pair<std::string, Patient>> &patients,
                 const std::vector<std::pair<std::string, Doctor>> &doctors) const;

};

#endif //REGISTRY_REGISTRY_H
