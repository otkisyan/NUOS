#include "../include/registry.h"
#include "../include/idgenerator.h"
#include <iostream>

using namespace std;

Registry::Registry() : patients_(), doctors_(), appointments_() {

}

Registry::Registry(const vector<pair<string, Patient>> &patients,
                   const vector<pair<string, Doctor>> &doctors,
                   const vector<pair<string, Appointment>> &appointments) : patients_(patients),
                                                                            doctors_(doctors),
                                                                            appointments_(appointments) {

}

Registry::~Registry() {

}

vector<pair<string, Patient>> Registry::GetPatients() const {

    return patients_;
}

vector<pair<string, Doctor>> Registry::GetDoctors() const {

    return doctors_;
}

vector<pair<string, Appointment>> Registry::GetAppointments() const {

    return appointments_;
}

void Registry::SetPatients(const std::vector<std::pair<std::string, Patient>> &patients) {

    patients_ = patients;
}

void Registry::SetDoctors(const std::vector<std::pair<std::string, Doctor>> &doctors) {

    doctors_ = doctors;
}

void Registry::SetAppointments(const vector<pair<string, Appointment>> &appointments) {

    appointments_ = appointments;
}


void Registry::Add(const Doctor &doctor) {

    doctors_.emplace_back(IdGenerator::Generate(), doctor);
}

void Registry::Add(const Patient &patient) {

    patients_.emplace_back(IdGenerator::Generate(), patient);
}

void Registry::ScheduleAppointment(const DoctorId &doctorId, const PatientId &patientId, const Date &date) {

    auto itDoctor = Find(doctorId);

    if (itDoctor == doctors_.end()) {

        throw invalid_argument("Лікаря з таким кодом не існує");
    }

    auto itPatient = Find(patientId);

    if (itPatient == patients_.end()) {

        throw invalid_argument("Пацієнта з таким кодом не існує");
    }

    // emplace_back - вместо того, чтобы принимать value_type, он принимает вариативный список аргументов, так что это означает,
    // что теперь вы можете идеально пересылать аргументы и напрямую создавать объект в контейнере без временного хранения.
    appointments_.emplace_back(IdGenerator::Generate(), Appointment(itPatient->first, itDoctor->first, date));
}


void Registry::Remove(const PatientId &patientId) {

    auto it = Find(patientId);

    if (it == patients_.end()) {

        throw invalid_argument("Пацієнта з таким кодом не існує");
    }

    string patientKey = it->first;
    patients_.erase(it);

    // Удаляю все приемы в которых удаленный пациент
    // erase - удаляет из вектора один элемент (позицию), либо диапазон элементов.
    appointments_.erase(

            // remove_if - элементы удовлетворяющие условию записывает в конец вектора, и возвращает итератор на начало элементов на удаление
            remove_if(appointments_.begin(), appointments_.end(),
                      [&patientKey](const pair<string, Appointment> &appointment) {

                          return appointment.second.GetPatientKey() == patientKey;

                      }), appointments_.end());


}

void Registry::Remove(const DoctorId &doctorId) {

    auto it = Find(doctorId);

    if (it == doctors_.end()) {

        throw invalid_argument("Лікаря з таким кодом не існує");
    }

    string doctorKey = it->first;
    doctors_.erase(it);

    appointments_.erase(

            remove_if(appointments_.begin(), appointments_.end(),
                      [&doctorKey](const pair<string, Appointment> &appointment) {

                          return appointment.second.GetDoctorKey() == doctorKey;

                      }), appointments_.end());

}

void Registry::Remove(const AppointmentId &appointmentId) {

    auto it = Find(appointmentId);

    if (it == appointments_.end()) {

        throw invalid_argument("Прийому пацієнта до лікаря з таким кодом не існує");
    }

    appointments_.erase(it);
}


vector<pair<string, Appointment>> Registry::FindAppointments(const DoctorId &doctorId) {

    auto it = Find(doctorId);

    if (it == doctors_.end()) {

        throw invalid_argument("Лікаря з таким кодом не існує");
    }

    vector<pair<string, Appointment>> doctorAppointments;
    string doctorKey = it->first;

    copy_if(appointments_.begin(), appointments_.end(), back_inserter(doctorAppointments),
            [&doctorKey](const pair<string, Appointment> &appointment) {

                return appointment.second.GetDoctorKey() == doctorKey;
            });

    return doctorAppointments;
}

vector<pair<string, Appointment>> Registry::FindAppointments(const PatientId &patientId) {

    auto it = Find(patientId);

    if (it == patients_.end()) {

        throw invalid_argument("Пацієнта з таким кодом не існує");
    }

    vector<pair<string, Appointment>> patientAppointments;
    string patientKey = it->first;

    copy_if(appointments_.begin(), appointments_.end(), back_inserter(patientAppointments),
            [&patientKey](const pair<string, Appointment> &appointment) {

                return appointment.second.GetPatientKey() == patientKey;
            });


    return patientAppointments;
}

vector<pair<string, Patient>>::iterator Registry::Find(const PatientId &patientId) {

    // find_if - возвращает итератор к первому элементу в диапазоне [first, last], для которого предикат возвращает true.
    // Если такой элемент не найден, функция возвращает last.

    auto it = find_if(patients_.begin(), patients_.end(),
                      [&patientId](const pair<string, Patient> &patient) {

                          return patient.second.GetId() == patientId;
                      });

    return it;
}

vector<pair<string, Doctor>>::iterator Registry::Find(const DoctorId &doctorId) {

    auto it = find_if(doctors_.begin(), doctors_.end(),
                      [&doctorId](const pair<string, Doctor> &doctor) {

                          return doctor.second.GetId() == doctorId;
                      });

    return it;
}

vector<pair<string, Appointment>>::iterator Registry::Find(const AppointmentId &appointmentId) {

    auto it = find_if(appointments_.begin(), appointments_.end(),
                      [&appointmentId](const pair<string, Appointment> &appointment) {

                          return appointment.second.GetId() == appointmentId;
                      });

    return it;
}

void Registry::ShowAll(const vector<pair<string, Patient>> &patients) const {

    for (int i = 0; i < patients.size(); i++) {

        patients[i].second.Show();
        cout << endl;
    }
}

void Registry::ShowAll(const vector<pair<string, Doctor>> &doctors) const {

    for (int i = 0; i < doctors.size(); i++) {

        doctors[i].second.Show();
        cout << endl;

    }
}

void Registry::ShowAll(const vector<pair<string, Appointment>> &appointments,
                       const vector<pair<string, Patient>> &patients) const {


    for (int i = 0; i < appointments.size(); i++) {

        cout << endl;
        AppointmentId appointmentId = appointments[i].second.GetId();
        cout << "Номер прийому: " << appointmentId << endl << endl;

        string patientKey = appointments[i].second.GetPatientKey();

        auto patientIt = find_if(patients.begin(), patients.end(), [&patientKey](const pair<string, Patient> &patient) {

            return patient.first == patientKey;
        });

        patientIt->second.Show();

        cout << endl;

        Date date = appointments[i].second.GetDate();
        cout << "Дата прийому: ";
        date.Show();
        cout << endl;

    }

}

void Registry::ShowAll(const vector<pair<string, Appointment>> &appointments,
                       const vector<pair<string, Doctor>> &doctors) const {

    for (int i = 0; i < appointments.size(); i++) {

        cout << endl;
        AppointmentId appointmentId = appointments[i].second.GetId();
        cout << "Номер прийому: " << appointmentId << endl << endl;

        string doctorKey = appointments[i].second.GetDoctorKey();

        auto doctorIt = find_if(doctors.begin(), doctors.end(), [&doctorKey](const pair<string, Doctor> &doctor) {

            return doctor.first == doctorKey;
        });

        doctorIt->second.Show();

        cout << endl;

        Date date = appointments[i].second.GetDate();
        cout << "Дата прийому: ";
        date.Show();
        cout << endl;

    }
}


void Registry::ShowAll(const vector<pair<string, Appointment>> &appointments,
                       const vector<pair<string, Patient>> &patients,
                       const vector<pair<string, Doctor>> &doctors) const {

    for (int i = 0; i < appointments.size(); i++) {

        cout << endl;
        AppointmentId appointmentId = appointments[i].second.GetId();
        cout << "Номер прийому: " << appointmentId << endl << endl;

        string patientKey = appointments[i].second.GetPatientKey();

        auto patientIt = find_if(patients.begin(), patients.end(), [&patientKey](const pair<string, Patient> &patient) {

            return patient.first == patientKey;
        });

        patientIt->second.Show();

        cout << endl;
        string doctorKey = appointments[i].second.GetDoctorKey();

        auto doctorIt = find_if(doctors.begin(), doctors.end(), [&doctorKey](const pair<string, Doctor> &doctor) {

            return doctor.first == doctorKey;

        });

        doctorIt->second.Show();

        Date date = appointments[i].second.GetDate();
        cout << endl;
        cout << "Дата прийому: ";
        date.Show();
        cout << endl;

    }
}

void Registry::AddDoctor() {

    string name;
    string surname;
    string qualification;

    cout << "Введіть ім'я лікаря:" << endl;
    cout << "➡ ";
    getline(cin, name);

    while (name.find_first_of("\n\t ") != string::npos ||
           name.find_first_of("0123456789") != string::npos ||
           name.empty()) {

        cout << "Введене ім'я містить пробіли, цифри або інші неприпустимі символи!" << endl;
        cout << "Введіть ім'я лікаря:" << endl;
        cout << "➡ ";
        getline(cin, name);
    }


    cout << "Введіть фамілію лікаря:" << endl;
    cout << "➡ ";
    getline(cin, surname);

    while (surname.find_first_of("\n\t ") != string::npos ||
           surname.find_first_of("0123456789") != string::npos ||
           surname.empty()) {

        cout << "Введена фамілія містить пробіли, цифри або інші неприпустимі символи!" << endl;
        cout << "Введіть фамілію лікаря:" << endl;
        cout << "➡ ";
        getline(cin, surname);
    }

    cout << "Введіть кваліфікацію лікаря:" << endl;
    cout << "➡ ";
    getline(cin, qualification);

    while (qualification.find_first_of("\n\t ") != string::npos ||
           qualification.find_first_of("0123456789") != string::npos || qualification.empty()) {

        cout << "Введена кваліфікація містить пробіли, цифри або інші неприпустимі символи!" << endl;
        cout << "Введіть кваліфікацію лікаря:" << endl;
        cout << "➡ ";
        getline(cin, qualification);
    }

    Doctor newDoctor(name, surname, qualification);
    Add(newDoctor);
}

void Registry::AddPatient() {

    string name;
    string surname;
    string phone;
    int age;

    cout << "Введіть ім'я пацієнта:" << endl;
    cout << "➡ ";
    getline(cin, name);

    // string::npos возвращает true если совпадений не найдено,
    // так как нам нужно наоборот получить true если совпадения найдены, то ставлю !=
    // find_first_of - поиск в строке первого символа, который совпадает с любым из символов, указанных в аргументах
    while (name.find_first_of("\n\t ") != string::npos ||
           name.find_first_of("0123456789") != string::npos ||
           name.empty()) {

        cout << "Введене ім'я містить пробіли, цифри або інші неприпустимі символи!" << endl;
        cout << "Введіть ім'я пацієнта:" << endl;
        cout << "➡ ";
        getline(cin, name);
    }

    cout << "Введіть фамілію пацієнта:" << endl;
    cout << "➡ ";
    getline(cin, surname);

    while (surname.find_first_of("\n\t ") != string::npos ||
           surname.find_first_of("0123456789") != string::npos ||
           surname.empty()) {

        cout << "Введена фамілія містить пробіли, цифри або інші неприпустимі символи!" << endl;
        cout << "Введіть фамілію пацієнта:" << endl;
        cout << "➡ ";
        getline(cin, surname);
    }

    cout << "Введіть вік пацієнта:" << endl;
    cout << "➡ ";
    cin >> age;
    while (cin.get() != '\n');

    cout << "Введіть номер телефону пацієнта:" << endl;
    cout << "➡ ";
    getline(cin, phone);

    while (phone.find_first_of("\n\t ") != string::npos || phone.empty()) {

        cout << "Введений номер телефона містить пробіли або інші неприпустимі символи!" << endl;
        cout << "Введіть номер телефону:" << endl;
        cout << "➡ ";
        getline(cin, phone);
    }

    Patient newPatient(name, surname, phone, age);
    Add(newPatient);
}

void Registry::ScheduleAppointment() {

    int year, month, day, hour, minute;

    cout << "Введіть дату прийому: " << endl;

    cout << "Рік: " << endl;
    cout << "➡ ";
    cin >> year;
    while (cin.get() != '\n');

    cout << "Місяць (1-12): " << endl;
    cout << "➡ ";
    cin >> month;
    while (cin.get() != '\n');

    while (month > 12 || month < 0) {

        cout << "Недійсний місяць: " << month << ", " << "оберіть дійсний місяць" << endl;
        cout << "Місяць (1-12): " << endl;
        cout << "➡ ";

        cin >> month;
        while (cin.get() != '\n');
    }

    int numberOfMonthDays = Date::GetNumberOfMonthDays(year, month);

    cout << "День (1-" << numberOfMonthDays << "): " << endl;
    cout << "➡ ";
    cin >> day;
    while (cin.get() != '\n');

    while (day > numberOfMonthDays || day < 0) {

        cout << "У обраному вами місяцю, не існує дня " << day << ", " << "оберіть дійсний день" << endl;
        cout << "День (1-" << numberOfMonthDays << "): " << endl;
        cout << "➡ ";

        cin >> day;
        while (cin.get() != '\n');
    }

    cout << "Година (0-23): " << endl;
    cout << "➡ ";
    cin >> hour;
    while (cin.get() != '\n');

    while (hour > 23 || hour < 0) {

        cout << "Недійсна година! Введіть годину у 24 часовому форматі" << endl;
        cout << "Година (0-23): " << endl;
        cout << "➡ ";

        cin >> hour;
        while (cin.get() != '\n');

    }

    cout << "Хвилина (0-59): " << endl;
    cout << "➡ ";
    cin >> minute;
    while (cin.get() != '\n');

    while (minute > 59 || minute < 0) {

        cout << "Недійсна хвилина!" << endl;
        cout << "Хвилина (0-59): " << endl;
        cout << "➡ ";

        cin >> hour;
        while (cin.get() != '\n');

    }

    cout << "Оберіть номер необхідного пацієнта" << endl;

    PatientId patientId;
    cout << "➡ ";
    cin >> patientId;
    while (cin.get() != '\n');

    cout << "Оберіть номер необхідного лікаря" << endl;

    DoctorId doctorId;
    cout << "➡ ";
    cin >> doctorId;
    while (cin.get() != '\n');

    try {

        ScheduleAppointment(doctorId, patientId, {year, month, day, hour, minute});
    }

    catch (const exception &ex) {

        cout << ex.what() << endl;
        return;
    }
}

void Registry::RemovePatient() {

    PatientId patientToDelete;
    cout << "Введіть номер пацієнта якого необхідно видалити" << endl;
    cout << "➡ ";
    cin >> patientToDelete;
    while (cin.get() != '\n');

    try {

        Remove(patientToDelete);
    }

    catch (const exception &ex) {

        cout << ex.what() << endl;
        return;
    }

}

void Registry::RemoveDoctor() {

    DoctorId doctorToDelete;
    cout << "Введіть номер лікаря якого необхідно видалити" << endl;
    cout << "➡ ";
    cin >> doctorToDelete;
    while (cin.get() != '\n');

    try {

        Remove(doctorToDelete);
    }

    catch (const exception &ex) {

        cout << ex.what() << endl;
        return;
    }
}

void Registry::RemoveAppointment() {

    AppointmentId appointmentToDelete;
    cout << "Введіть номер прийому який необхідно видалити" << endl;
    cout << "➡ ";
    cin >> appointmentToDelete;
    while (cin.get() != '\n');

    try {

        Remove(appointmentToDelete);
    }
    catch (const exception &ex) {

        ex.what();
        return;
    }
}

void Registry::ShowAllPatients() {

    if (patients_.empty()) {

        cout << "Паціентів не існує!" << endl;
        return;
    }

    ShowAll(patients_);
}

void Registry::ShowAllDoctors() {

    if (doctors_.empty()) {

        cout << "Лікарів не існує!" << endl;
        return;
    }

    ShowAll(doctors_);
}

void Registry::ShowAllAppointments() {

    if (appointments_.empty()) {

        cout << "Прийомів пацієнтів до лікарів не існує" << endl;
        return;
    }

    ShowAll(appointments_, patients_, doctors_);
}

void Registry::ShowPatientAppointments() {

    cout << "Оберіть номер необхідного пацієнта" << endl;
    PatientId chosenPatient;
    cout << "➡ ";
    cin >> chosenPatient;
    while ((cin.get()) != '\n');

    vector<pair<string, Appointment>> appointments;

    try {

        appointments = FindAppointments(chosenPatient);
    }

    catch (const exception &ex) {

        cout << ex.what() << endl;
        return;
    }

    if (appointments.empty()) {

        cout << "Даний пацієнт не має прийомів до лікарів" << endl;
        return;
    }

    ShowAll(appointments, doctors_);
}

void Registry::ShowDoctorAppointments() {

    cout << "Оберіть номер необхідного лікаря" << endl;
    DoctorId chosenDoctor;
    cout << "➡ ";
    cin >> chosenDoctor;
    while ((cin.get()) != '\n');

    vector<pair<string, Appointment>> appointments;

    try {

        appointments = FindAppointments(chosenDoctor);
    }

    catch (const exception &ex) {

        cout << ex.what() << endl;
        return;
    }

    if (appointments.empty()) {

        cout << "Прийомів пацієнтів до даного лікаря не існує" << endl;
        return;
    }

    ShowAll(appointments, patients_);
}
