/*
 * 1. Разработайте на языке программирования C/С++ функции преобразования числа из одной системы счисления в другую.
 * Числа задавать и получать в виде строки.
 * 2. Разработайте функции для сложения и вычитания чисел в данных системах счисления.
 * (из 8 с.ч в 10 с.ч)
*/

#include <iostream>
#include <string>
#include <math.h>

using namespace std;

string OctalToDecimal(string number);

string DecimalToOctal(string number);

string OctalSubstract(string number1, string number2);

string OctalAddition(string number1, string number2);

bool CheckOctal(string number);

bool CheckSubstract(string number1, string number2);


int main() {

    int select = 0;
    int work = 0;


    do {

        cout << "Оберіть функцію: " << endl;
        cout << "1 - Переведення з 8 у 10 систему числення" << endl;
        cout << "2 - Віднімання у 8 системі числення" << endl;
        cout << "3 - Додавання у 8 системі числення" << endl;
        cout << "0 - Вийти з програми" << endl;
        cout << "➡ ";

        cin >> select;

        if (select > 4 || select < 0) {
            cout << "Некоректний номер функції" << endl << endl;
            work = 1;

        } else if (select == 0) {

            exit(0);
        }


        switch (select) {

            case 1: {

                work = 1;
                string number;

                cout << "Введіть число у 8 с.ч: " << endl;
                cout << "➡ ";
                cin >> number;

                if (CheckOctal(number) == false) {

                    cout << "Некоректно введене число! У 8 с.ч не може бути цифр більше 7" << endl;

                } else {
                    cout << "8 → 10 = ";
                    cout << OctalToDecimal(number) << endl;
                }


                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work != 1) {

                    exit(0);
                }

                break;
            }


            case 2: {

                work = 1;
                string number1;
                string number2;

                cout << "Введіть перше число у 8 с.ч: " << endl;
                cout << "➡ ";
                cin >> number1;

                cout << "Введіть друге число у 8 с.ч: " << endl;
                cout << "➡ ";
                cin >> number2;

                if (CheckOctal(number1) == false || CheckOctal(number2) == false) {

                    cout << "Некоректно введене число! У 8 с.ч не може бути цифр більше 7" << endl;

                } else if (CheckSubstract(number1, number2) == false) {

                    cout << "Перше число не може бути менше за друге" << endl;

                } else {
                    cout << number1 << " - " << number2 << ": " << OctalSubstract(number1, number2) << endl;
                }

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work != 1) {

                    exit(0);
                }

                break;

            }

            case 3: {

                work = 1;
                string number1;
                string number2;

                cout << "Введіть перше число у 8 с.ч: " << endl;
                cout << "➡ ";
                cin >> number1;

                cout << "Введіть друге число у 8 с.ч: " << endl;
                cout << "➡ ";
                cin >> number2;

                if (CheckOctal(number1) == false || CheckOctal(number2) == false) {

                    cout << "Некоректно введене число! У 8 с.ч не може бути цифр більше 7" << endl;

                } else {

                    cout << number1 << " + " << number2 << ": " << OctalAddition(number1, number2) << endl;
                }

                cout << "Бажаєте продовжити роботу? 1 - так, 2 - ні" << endl;
                cout << "➡ ";
                cin >> work;

                if (work != 1) {

                    exit(0);
                }

                break;

            }

        }

    } while (work == 1);


    return 0;
}


string OctalToDecimal(string number) {

    int decimal = 0, i = 0, rem = 0;

    int octal = stoi(number);

    while (octal != 0) {

        rem = octal % 10;
        octal /= 10;
        decimal += rem * pow(8, i);
        i++;
    }

    string result = to_string(decimal);

    return result;
}

string DecimalToOctal(string number) {

    int octal = 0, i = 1, rem = 0;

    int decimal = stoi(number);

    while (decimal != 0) {

        rem = decimal % 8;
        decimal /= 8;
        octal += rem * i;
        i *= 10;
    }

    string result = to_string(octal);

    return result;
}


string OctalSubstract(string number1, string number2) {

    number1 = OctalToDecimal(number1);
    number2 = OctalToDecimal(number2);

    int operand1 = stoi(number1);
    int operand2 = stoi(number2);

    int substraction = operand1 - operand2;

    string result = to_string(substraction);
    result = DecimalToOctal(result);

    return result;

}

string OctalAddition(string number1, string number2) {

    number1 = OctalToDecimal(number1);
    number2 = OctalToDecimal(number2);

    int operand1 = stoi(number1);
    int operand2 = stoi(number2);

    int addition = operand1 + operand2;

    string result = to_string(addition);
    result = DecimalToOctal(result);

    return result;
}


bool CheckOctal(string number) {

    int octal = stoi(number);

    if (octal <= 0) {
        return false;
    }

    if (number.find_first_not_of("01234567") != string::npos) {
        return false;
    }

    return true;
}

bool CheckSubstract(string number1, string number2) {

    int operand1 = stoi(number1);
    int operand2 = stoi(number2);

    if (operand1 < operand2) {
        return false;
    }

    return true;
}
