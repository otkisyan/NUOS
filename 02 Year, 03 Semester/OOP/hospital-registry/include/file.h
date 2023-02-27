#ifndef REGISTRY_FILE_H
#define REGISTRY_FILE_H

#include "appointment.h"
#include "doctor.h"
#include "patient.h"
#include <vector>

class File {

public:
    File();

    ~File();

    std::vector<std::pair<std::string, Appointment>>
    ReadFile(std::vector<std::pair<std::string, Appointment>> appointments);

    std::vector<std::pair<std::string, Patient>>
    ReadFile(std::vector<std::pair<std::string, Patient>> patients);

    std::vector<std::pair<std::string, Doctor>>
    ReadFile(std::vector<std::pair<std::string, Doctor>> doctors);

    void WriteFile(std::vector<std::pair<std::string, Patient>> patients);

    void WriteFile(std::vector<std::pair<std::string, Doctor>> doctors);

    void WriteFile(std::vector<std::pair<std::string, Appointment>> appointments);

private:
    const std::string kExtension = ".txt";
    const std::string kPatientsFileName = "patients";
    const std::string kDoctorsFileName = "doctors";
    const std::string kAppointmentsFileName = "appointments";
    const std::string kDirectory = "../userfiles/";
    const std::string kPatientsFilePath =
            kDirectory + kPatientsFileName + kExtension;
    const std::string kDoctorsFilePath =
            kDirectory + kDoctorsFileName + kExtension;
    const std::string kAppointmentsFilePath =
            kDirectory + kAppointmentsFileName + kExtension;
};

#endif // REGISTRY_FILE_H
