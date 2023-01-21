// Скласти та виконати програму, яка обробляє інформацію з файлу. 
// Вказівка: реалізувати меню з опціями (створити файл, додати один 
// запис у файл, додати декілька записів у файл, видалити файл, видалити 
// один запис у файлі, видалити декілька записів у файлі, вивести вміст файлу 
// на екран, вивести вміст файлу на екран згідно заданих критеріїв).

// Постановка задачі:
// Структура "Автомобіль": 
// марка; 
// серійний номер; 
// кількість обертів двигуна; 
// літраж двигуна; 
// рік випуску. 

// Створити масив структур. Вивести: 
// a) список автомобілів заданої марки; 
// b) список автомобілів, які мають об’єм двигуна більше за 3 л., та які 
// експлуатуються менше n років; 
// c) список автомобілів вказаного року випуску, які мають певну 
// цифру в серійному номері.

#include <iostream>
#include <cmath>
#include <cstring>
#include <fstream>
#include <dirent.h>
#include <regex>
using namespace std;

struct Car {
    char mark [15];
    char serialNumber [25];
    int engineRPM;
    float engineCapacity;
    int year;
};


//ОБРАБОТКА ФАЙЛА

//Создание файла
void CreateFile (Car *array, int N, string filename, string varsPath, string filePath);
//Проверка корректности имени файла
bool CheckNameCorrect (string filename);
//Удаление файла
void DeleteFile (string filePath, string varsPath);
//Получение имени файла
void GetFileName (string &filePath);
//Считывание информации из файла
void ReadFile (Car *array, int &N, string filePath, string varsPath);
//Перезапись файла
void RewriteFile (Car *array, int &N, string filePath, string varsPath);



//ОБРАБОТКА ИНФОРМАЦИИ ИЗ ФАЙЛА

//Добавить запись в файл
Car AddEntry ();
//Увеличить размер массива
void IncreaseSize (Car *&array, int &N);
//Уменшить размер массива
void DecreaseSize (Car *&array, int &N);
//Вывод всей информации из файла
void OutputAll (Car *array, int N);
//Поиск по имени
void FindByName (Car *array, char inputMark[], int N);
//Поиск по обьему двигателя и году выпуска
void FindByCapacityAndYear (Car *array, int yearExploitation, float inputCapacity, int N);
//Поиск по году выпуска и символу в серийном номере
void FindByYearAndSerial (Car *array, int inputYear, char inputSerial[], int N);


int main(){

    int work = 0, use = 0;
    int N = 6;

    string filename;
    string filePath = "/Users/otkisyan/CLionProjects/lab5/cmake-build-debug/userfiles/";
    string varsPath = "/Users/otkisyan/CLionProjects/lab5/cmake-build-debug/userfiles/data/variables.txt";


    Car *array = new Car [N] { {"Toyota", "ZXA22300005556777", 2000, 3.6, 2020},
                              { "Honda", "WMB223102303556177", 3600, 1.3, 2013},
                              {"Mercedes", "MFS22312302656727", 2200, 4.8, 2015},
                              {"BMW", "PSD22300302556727", 2600, 2.6, 2021},
                              {"Ferrari", "KDS22396302136727", 4800, 4.8, 2014},
                              {"Land Rover", "JJA41310304350752", 1800, 1.2, 2020}
    };


    if(!array){
        cout << "Помилка" << endl;
    }

    do
    {
        cout << "Оберіть фунцкію: " << endl;
        cout << "1 - Пошук автомоблів заданої марки" << endl;
        cout << "2 - Список автомобілів, які мають об’єм двигуна більше за x л., та які експлуатуються менше n років" << endl;
        cout << "3 - Список автомобілів вказаного року випуску, які мають певну цифру в серійному номері" << endl;
        cout << "4 - Операції з файлом" << endl;
        cout << "➡ ";
        cin >> use;

        if (use > 9 || use <= 0){
            cout << "Некоректне введеня" << endl;
            work = 1;
        }

        if (use == 4){
            cout << "5 - Створити файл" << endl;
            cout << "6 - Видалити файл" << endl;
            cout << "7 - Додати записи до файлу" << endl;
            cout << "8 - Видалити записи з файлу" << endl;
            cout << "9 - Вивести вміст файлу на екран" << endl;
            cout << "➡ ";
            cin >> use;
        }


        switch (use) {
            case 1: {
                work = 1;

                //пошук за заданою маркою

                ReadFile(array, N, filePath, varsPath);

                char inputMark [25];
                cout << "Введіть марку автомобілю: ";
                cin >> inputMark;

                cout << "Список автомобілів заданої марки: " << endl;
                FindByName(array, inputMark, N);
                cout << endl;

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work == 2){
                    delete [] array;
                    exit(0);
                }

                break;
            }

            case 2:
            {
                work = 1;

                //список автомобілів, які мають об’єм двигуна більше за x л., та які експлуатуються менше n років
                ReadFile(array, N, filePath, varsPath);
                int yearExploitation;
                cout << "Введіть кількісіть років експулатації: ";
                cin >> yearExploitation;
                cout << endl;

                float inputCapacity;
                cout << "Введіть об'єм двигуну: ";
                cin >> inputCapacity;

                cout << "Список автомобілів, які мають об’єм двигуна більше за " << inputCapacity << " та які експлуатуються менше "
                     << yearExploitation << " років";
                cout << endl;
                FindByCapacityAndYear(array, yearExploitation, inputCapacity, N);
                cout << endl;

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work == 2){
                    delete [] array;
                    exit(0);
                }
                break;
            }

            case 3:
            {
                work = 1;

                //список автомобілів вказаного року випуску, які мають певну цифру або букву в серійному номері.
                ReadFile(array, N, filePath, varsPath);


                int inputYear;
                cout << "Введіть рік випуску: ";
                cin >> inputYear;

                char inputSerial [25];
                cout << "Введіть бажані цифри або букви серійного номеру: ";
                cin >> inputSerial;

                cout << "Список автомобілів " << inputYear << " року випуску," << " які мають цифри або букви " << inputSerial << " в серійному номері";
                cout << endl;

                FindByYearAndSerial(array, inputYear, inputSerial, N);

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work == 2){
                    delete [] array;
                    exit(0);
                }
                break;
            }

            case 5: {

                cout << "Введіть бажане ім'я файлу: ";
                cin >> filename;

                bool checkName = CheckNameCorrect(filename);
                if (checkName == false){

                    cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                    cout << "➡ ";
                    cin >> work;

                    if (work == 2) {
                        delete [] array;
                        exit(0);
                    }

                    break;
                }


                CreateFile(array, N, filename, varsPath, filePath);
                GetFileName(filePath);
                ReadFile(array, N, filename, varsPath);

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work == 2) {
                    delete [] array;
                    exit(0);
                }
                break;

            }

            case 6: {

                DeleteFile(filePath, varsPath);

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work == 2) {
                    delete [] array;
                    exit(0);
                }
                break;
            }

            case 7: {

                ReadFile(array, N, filePath, varsPath);
                IncreaseSize(array, N);

                int i = N - 1;
                array[i] = AddEntry();

                RewriteFile(array, N, filePath, varsPath);

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work == 2){
                    delete [] array;
                    exit(0);
                }

                break;

            }

            case 8: {

                ReadFile(array, N, filePath, varsPath);
                DecreaseSize(array, N);
                RewriteFile(array, N, filePath, varsPath);

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work == 2){
                    delete [] array;
                    exit(0);
                }

                break;

            }

            case 9: {

                ReadFile(array, N, filePath, varsPath);
                OutputAll(array, N);

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work == 2){
                    delete [] array;
                    exit(0);
                }

                break;


            }

        }

    } while(work == 1);

}


void GetFileName(string &filePath){

    dirent * dirp;
    regex ext_regex("[a-zA-Z0-9]+\\.txt");

    if (DIR *dp = opendir(filePath.c_str())) {

        while ((dirp = readdir(dp))) {

            if (regex_match(dirp->d_name, ext_regex)) {

                //cout << dirp->d_name << endl;
                filePath += dirp->d_name;

            }

        }

        closedir(dp);

    }
}

bool CheckNameCorrect (string filename){

        if (filename.find_first_not_of("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.") != string::npos){
            cout << "Ім'я файлу не підтримує символи окрім букв та цифр!" << endl;
            return false;
        }

        else {
            return true;
        }

    //regex ext_regex("[a-zA-Z0-9]+");
    //return regex_match(filename, ext_regex);

    }


void CreateFile (Car *array, int N, string filename, string varsPath, string filePath){

    ofstream fw_vars;
    fw_vars.open(varsPath.c_str(), ios::binary | ios::out);
    fw_vars.close();

    ofstream fw;

    filename += ".txt";
    fw.open((filePath + filename).c_str(), ios::binary | ios::out);

    for (int i = 0; i < N; i++) {

        fw.write((char *) &array[i], sizeof(array[i]));
    }

    fw.close();
}

void DeleteFile(string filePath, string varsPath){

    GetFileName(filePath);
    remove(filePath.c_str());
    remove(varsPath.c_str());

    //std::__fs::filesystem::remove(filePath); //принимает строки
}

void ReadFile (Car *array, int &N, string filePath, string varsPath){

    GetFileName(filePath);

    ifstream fr_vars;
    fr_vars.open(varsPath.c_str(), ios::binary);
    fr_vars.read((char *) &N, sizeof(N));
    fr_vars.close();

    ifstream fr;
    fr.open((filePath).c_str(), ios::binary);

    for (int i = 0; i < N; i++) {

        fr.read((char *) &array[i], sizeof(array[i]));
    }

    fr.close();


}

void RewriteFile(Car *array, int &N, string filePath, string varsPath){

    GetFileName(filePath);

    ofstream fw_vars;
    fw_vars.open(varsPath.c_str(), ios::binary);
    fw_vars.write((char *) &N, sizeof(N));
    fw_vars.close();

    ofstream fw;
    fw.open((filePath).c_str(), ios::binary);

    for (int i = 0; i < N; i++) {

        fw.write((char *) &array[i], sizeof(array[i]));
    }

    fw.close();


}

Car AddEntry(){

    Car newEntry;

    cout << "Введіть марку: " << endl;
    cin >> newEntry.mark;

    cout << "Введіть серійний номер: " << endl;
    cin >> newEntry.serialNumber;

    cout << "Введіть кількість обертів двигуна: " << endl;
    cin >> newEntry.engineRPM;

    cout << "Введіть літраж двигуна: " << endl;
    cin >> newEntry.engineCapacity;

    cout << "Введіть рік випуску: " << endl;
    cin >> newEntry.year;

    return newEntry;

}

void IncreaseSize (Car *&array, int &N){

    Car *array2 = new Car[N + 1];

    for (int i = 0; i < N; i++) {
        array2[i] = array[i];
    }

    delete [] array;
    array = array2;
    N++;

}

void DecreaseSize (Car *&array, int &N){

    N--;
    Car *array2 = new Car[N];

    for (int i = 0; i < N; i++) {
        array2[i] = array[i];
    }

    delete [] array;
    array = array2;


}


void OutputAll (Car *array, int N){

    for (int i = 0; i < N; i++) {

        cout << "Марка: " << array[i].mark << " | Серійний номер: " << array[i].serialNumber << endl;
        cout << "Оберти двигуна: " << array[i].engineRPM << " | Літраж двигуна: " << array[i].engineCapacity << endl;
        cout << "Рік випуску: " << array[i].year << endl;
        cout << endl;

    }
}

void FindByName(Car *array, char inputMark[], int N){

    int foundedCars = 0;
    for (int i = 0; i < N; i++) {

        if(strcmp(inputMark, array[i].mark) == 0){

            cout << "Марка: " << array[i].mark << " | Серійний номер: " << array[i].serialNumber << endl;
            cout << "Оберти двигуна: " << array[i].engineRPM << " | Літраж двигуна: " << array[i].engineCapacity << endl;
            cout << "Рік випуску: " << array[i].year << endl;
            cout << endl;

            foundedCars++;
        }
    }
    if (foundedCars == 0){
        cout << "Автомобілів за введеною маркою не знайдено" << endl;
    }
}

void FindByCapacityAndYear(Car *array, int yearExploitation, float inputCapacity, int N){
    int foundedCars = 0;
    int currentYear = 2022;

    for (int i = 0; i < N; i++) {

        if(array[i].engineCapacity > inputCapacity && (currentYear - array[i].year) < yearExploitation){
            cout << array[i].mark << " | " << array[i].year << " рік випуску"
                 << " | " << array[i].engineCapacity << " об'єм двигуна" << endl;
            foundedCars++;

        }
    }
    if (foundedCars == 0){
        cout << "Автомобілів за вашими параметрами не знайдено" << endl;
    }
}

void FindByYearAndSerial(Car *array, int inputYear, char inputSerial[], int N){
    int foundedCars = 0;
    char *result;

    for (int i = 0; i < N; i++) {

        if(array[i].year == inputYear && (result = strstr(array[i].serialNumber, inputSerial)) != nullptr){
            cout << array[i].mark << " | " << array[i].year << " рік випуску" << " | "
                 << "Серійний номер: " << array[i].serialNumber << endl;
            foundedCars++;

        }

    }
    if (foundedCars == 0){
        cout << "Автомобілів за вашими параметрами не знайдено" << endl;
    }

}

