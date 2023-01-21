// Структура "Автомобіль":
 // марка;
 // серійний номер;
 // кількість обертів двигуна;
 // літраж двигуна;
 // рік випуску. 

// Створити масив структур. Вивести:
// a) список автомобілів заданої марки;
// b) список автомобілів, які мають об’єм двигуна більше за x л., та які експлуатуються менше n років;
// c) список автомобілів вказаного року випуску, які мають певну цифру в серійному номері.
// d) додати запис до масиву

#include <iostream>
#include <cmath>
#include <string>
using namespace std;

struct Car {
    string mark;
    string serialNumber;
    int engineRPM;
    float engineCapacity;
    int year;
};

Car AddData ();
int IncreaseSize (Car *&array, int &N);
void OutputAll (Car *array, int N);
void FindByName (Car *array, string inputMark, int N);
void FindByCapacityAndYear (Car *array, int yearExploitation, float inputCapacity, int N);
void FindByYearAndSerial(Car *array, int inputYear, char inputSerial, int N);


int main(){
    int work = 0, use = 0;
    int N = 6;

    Car *array = new Car[N] { {"Toyota", "ZXA22300005556777", 2000, 3.6, 2020},
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
        cout << "4 - Список всіх автомобілів" << endl;
        cout << "5 - Додати свій автомобіль до бази" << endl;
        cout << "➡ ";
        cin >> use;

        if(use > 5 || use <= 0){
            cout << "Некоректне введеня" << endl;
            work = 1;
        }


        switch (use) {
            case 1: {
                work = 1;

                // Пошук за заданою маркою
                string inputMark;
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

                // Список автомобілів, які мають об’єм двигуна більше за 3 л., та які експлуатуються менше n років
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

                // Список автомобілів вказаного року випуску, які мають певну цифру в серійному номері.
                int inputYear;
                cout << "Введіть рік випуску: ";
                cin >> inputYear;
                char inputSerial;
                cout << "Введіть бажану цифру серійного номеру: ";
                cin >> inputSerial;
                cout << "Список автомобілів " << inputYear << " року випуску," << " які мають цифру " << inputSerial << " в серійному номері";
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

            case 4:
            {
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

            case 5:
            {

                IncreaseSize(array, N);
                int i = N - 1;
                array[i] = AddData ();

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

Car AddData(){

    Car a;

    cout << "Введіть марку: " << endl;
    cin >> a.mark;

    cout << "Введіть серійний номер: " << endl;
    cin >> a.serialNumber;

    cout << "Введіть кількість обертів двигуна: " << endl;
    cin >> a.engineRPM;

    cout << "Введіть літраж двигуна: " << endl;
    cin >> a.engineCapacity;

    cout << "Введіть рік випуску: " << endl;
    cin >> a.year;

    return a;

}

int IncreaseSize (Car *&array, int &N){

    Car *array2 = new Car[N + 1];

    for (int i = 0; i < N; i++) {
        array2[i] = array[i];
    }

    delete [] array;
    array = array2;
    N++;

}

void OutputAll (Car *array, int N){
    for (int i = 0; i < N; i++) {
        cout << "Марка: " << array[i].mark << " | Серійний номер: " << array[i].serialNumber << endl;
        cout << "Оберти двигуна: " << array[i].engineRPM << " | Літраж двигуна: " << array[i].engineCapacity << endl;
        cout << "Рік випуску: " << array[i].year << endl;
        cout << endl;

    }
}

void FindByName(Car *array, string inputMark, int N){
    int foundedCars = 0;
    for (int i = 0; i < N ; i++) {
        if(inputMark == array[i].mark){

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

void FindByYearAndSerial(Car *array, int inputYear, char inputSerial, int N){
    int foundedCars = 0;

    for (int i = 0; i < N; i++) {

        if(array[i].year == inputYear && array[i].serialNumber.find(inputSerial) != string::npos){
            cout << array[i].mark << " | " << array[i].year << " рік випуску" << " | "
                 << "Серійний номер: " << array[i].serialNumber << endl;
            foundedCars++;
        }
    }
    if (foundedCars == 0){
        cout << "Автомобілів за вашими параметрами не знайдено" << endl;
    }
}

//while - в зависимости от условия тело цикла может быть не выполнено ни разу.
//do while - тело цикла будет выполнено минимум один раз.
