#include "../include/hospital.h"
#include <vector>
#include <iostream>

using namespace std;

Hospital::Hospital() {

}

Hospital::~Hospital() {

}

void Hospital::Run() {

    vector<pair<string, Patient>> patients;
    vector<pair<string, Doctor>> doctors;
    vector<pair<string, Appointment>> appointments;

    try {

        patients = file_.ReadFile(patients);
        // registry_.SetPatients(file_.ReadFile(registry_.GetPatients()));

    }

    catch (const exception &ex) {

        cerr << ex.what() << endl;
    }

    try {

        doctors = file_.ReadFile(doctors);
    }

    catch (const exception &ex) {

        cerr << ex.what() << endl;
    }

    try {

        appointments = file_.ReadFile(appointments);
    }

    catch (const exception &ex) {

        cerr << ex.what() << endl;
    }

    registry_.SetPatients(patients);
    registry_.SetDoctors(doctors);
    registry_.SetAppointments(appointments);

    menu_ = {

            // Для того чтобы была возможность не передавая в параметры лямбды работать с переменными из внешнего контекста
            // В квадратные скобки записываю & - это означает, что я захватываю все переменные используемые в лямбде по ссылке.

            {"Вийти з програми",                     []() {

                exit(0);
            }},

            {"Додати лікаря",                        [&]() {

                registry_.AddDoctor();

                try {

                    file_.WriteFile(registry_.GetDoctors());
                }

                catch (const exception &ex) {

                    cout << ex.what();
                    exit(1);
                }
            }},

            {"Видалити лікаря",                      [&]() {

                registry_.RemoveDoctor();

                try {

                    file_.WriteFile(registry_.GetDoctors());
                }

                catch (const exception &ex) {

                    cout << ex.what();
                    exit(1);
                }

                try {

                    file_.WriteFile(registry_.GetAppointments());
                }

                catch (const exception &ex) {

                    cout << ex.what();
                    exit(1);
                }

            }},

            {"Додати пацієнта",                      [&]() {

                registry_.AddPatient();


                try {

                    file_.WriteFile(registry_.GetPatients());
                }

                catch (const exception &ex) {

                    cout << ex.what();
                    exit(1);
                }
            }},

            {"Видалити пацієнта",                    [&]() {

                registry_.RemovePatient();

                try {

                    file_.WriteFile(registry_.GetPatients());
                }

                catch (const exception &ex) {

                    cout << ex.what();
                    exit(1);
                }

                try {

                    file_.WriteFile(registry_.GetAppointments());
                }

                catch (const exception &ex) {

                    cout << ex.what();
                    exit(1);
                }
            }},

            {"Додати прийом пацієнта до лікаря",     [&]() {

                registry_.ScheduleAppointment();

                try {

                    file_.WriteFile(registry_.GetAppointments());
                }

                catch (const exception &ex) {

                    cout << ex.what();
                    exit(1);
                }

            }},

            {"Видалити прийом пацієнта до лікаря",   [&]() {

                registry_.RemoveAppointment();

                try {

                    file_.WriteFile(registry_.GetAppointments());
                }

                catch (const exception &ex) {

                    cout << ex.what();
                    exit(1);
                }
            }},

            {"Список всіх пацієнтів",                [&]() {

                registry_.ShowAllPatients();
            }},

            {"Список всіх лікарів",                  [&]() {

                registry_.ShowAllDoctors();

            }},

            {"Список всіх прийомів до лікарів",      [&]() {


                registry_.ShowAllAppointments();
            }},

            {"Список прийомів для певного лікаря",   [&]() {

                registry_.ShowDoctorAppointments();

            }},

            {"Список прийомів для певного пацієнта", [&]() {

                registry_.ShowPatientAppointments();

            }},
    };


    menu_.Run();

}