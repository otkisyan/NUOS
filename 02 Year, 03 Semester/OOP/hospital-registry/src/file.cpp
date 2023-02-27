#include <fstream>
#include <filesystem>
#include "../include/file.h"

using namespace std;

File::File() {

}

File::~File() {

}


void File::WriteFile(vector<pair<string, Patient>> patients) {

    ofstream patientsFile;
    patientsFile.exceptions(ifstream::failbit | ifstream::badbit);
    patientsFile.open(kPatientsFilePath.c_str());

    // Записываем количество пациентов в файл
    patientsFile << patients.size() << endl;

    // Записываем вектор пациентов в файл
    for (int i = 0; i < patients.size(); i++) {

        patientsFile << patients[i].first << patients[i].second;
    }

    patientsFile.close();
}

void File::WriteFile(vector<pair<string, Doctor>> doctors) {

    ofstream doctorsFile;
    doctorsFile.exceptions(ifstream::failbit | ifstream::badbit);
    doctorsFile.open(kDoctorsFilePath.c_str());

    // Записываем количество докторов в файл
    doctorsFile << doctors.size() << endl;

    // Записываем вектор докторов в файл
    for (int i = 0; i < doctors.size(); i++) {

        doctorsFile << doctors[i].first << doctors[i].second;
    }

    doctorsFile.close();
}


void File::WriteFile(vector<pair<string, Appointment>> appointments) {

    ofstream appointmentsFile;
    appointmentsFile.exceptions(ifstream::failbit | ifstream::badbit);
    appointmentsFile.open(kAppointmentsFilePath.c_str());

    // Записываем количество приемов в файл
    appointmentsFile << appointments.size() << endl;

    // Записываем в файл вектор приемов
    for (int i = 0; i < appointments.size(); i++) {

        appointmentsFile << appointments[i].first << appointments[i].second;
    }

}


vector<pair<string, Patient>> File::ReadFile(vector<pair<string, Patient>> patients) {

    if (filesystem::exists(kPatientsFilePath)){
        return patients;
    }

    ifstream patientsFile;
    patientsFile.exceptions(ifstream::failbit | ifstream::badbit);

    patientsFile.open(kPatientsFilePath.c_str());

    if (patientsFile.peek() == ifstream::traits_type::eof()) {

        return patients;
    }


    // Читаем количество пациентов
    int numberOfPatients = 0;
    patientsFile >> numberOfPatients;
    // Увеличиваем размер вектора докторов на количество пациентов
    patients.resize(numberOfPatients);

    // Читаем пациентов из файла в вектор пациентов
    for (int i = 0; i < patients.size(); i++) {

        patientsFile >> patients[i].first >> patients[i].second;

    }

    patientsFile.close();

    return patients;
}

vector<pair<string, Doctor>> File::ReadFile(vector<pair<string, Doctor>> doctors) {

    if (filesystem::exists(kDoctorsFilePath)) {

        return doctors;
    }

    ifstream doctorsFile;

    doctorsFile.exceptions(ifstream::failbit | ifstream::badbit);

    doctorsFile.open(kDoctorsFilePath.c_str());

    if (doctorsFile.peek() == ifstream::traits_type::eof()) {

        return doctors;
    }

    // Читаем количество докторов
    int numberOfDoctors = 0;
    doctorsFile >> numberOfDoctors;

    // Увеличиваем размер вектора докторов на количество докторов
    doctors.resize(numberOfDoctors);

    // Читаем докторов из файла в вектор докторов
    for (int i = 0; i < doctors.size(); i++) {

        doctorsFile >> doctors[i].first >> doctors[i].second;
    }

    doctorsFile.close();

    return doctors;
}

vector<pair<string, Appointment>> File::ReadFile(vector<pair<string, Appointment>> appointments) {

    if (filesystem::exists(kAppointmentsFilePath)) {

        return appointments;
    }

    ifstream appointmentsFile;
    appointmentsFile.exceptions(ifstream::failbit | ifstream::badbit);

    appointmentsFile.open(kAppointmentsFilePath.c_str());

    if (appointmentsFile.peek() == ifstream::traits_type::eof()) {

        return appointments;
    }

    // Читаем количество приемов
    int numberOfAppointments = 0;
    appointmentsFile >> numberOfAppointments;
    appointments.resize(numberOfAppointments);

    // Читаем приемы из файла в вектор приемов
    for (int i = 0; i < appointments.size(); i++) {

        appointmentsFile >> appointments[i].first >> appointments[i].second;
    }

    appointmentsFile.close();

    return appointments;
}

