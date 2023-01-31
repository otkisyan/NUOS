// Конвертер систем счисления

#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <iomanip>
#include <bitset>
#include <string>
using namespace std;

// Перевод двоичных чисел
int BinaryToDecimal10(int number);
int BinaryToOctal8(int binDecimal);
int BinaryToHexadecimal16(int binDecimal);

// Перевод восьмеричных чисел
int OctalToDecimal10(int number);
int OctalToBinary2(int octDecimal);
int OctalToHexadecimal16(int octDecimal);

// Перевод шестнадцатеричных чисел
int HexadecimalToDecimal10(string hexNumber);
int HexadecimalToBinary2(int hexDecimal);
int HexadecimalToOctal8(int hexDecimal);

// Перевод десятичных чисел
int DecimalToBinary2(int number);
int DecimalToOctal8(int number);
int DecimalToHexadecimal16(int number);

// Проверки
int CheckHexadecimal(string hexNumber);
int CheckBinary(int number);
int CheckOctal(int number);
int CheckDecimal(int number);

int main()
{

    int number, numberSystem;
    string hexNumber;

    cout << "Введіть систему числення числа: ";
    cin >> numberSystem;

    if (numberSystem == 16)
    {

        cout << "Введіть число: ";
        cin >> hexNumber;
    }
    else
    {
        cout << "Введіть число: ";
        cin >> number;
    }

    cout << endl;

    if (numberSystem == 16)
    {
        cout << "Вхідне число: " << hexNumber << endl;
    }
    else
    {
        cout << "Вхідне число: " << number << endl;
    }

    cout << "Система числення вхідного числа: " << numberSystem << endl;

    switch (numberSystem)
    {
    case 10:
    {
        CheckDecimal(number);

        cout << "10 → 2 = ";
        DecimalToBinary2(number);
        cout << "10 → 8 = ";
        DecimalToOctal8(number);
        cout << "10 → 16 = ";
        DecimalToHexadecimal16(number);
        break;
    }

    case 2:
    {
        CheckBinary(number);
        int binDecimal = BinaryToDecimal10(number);

        cout << "2 → 10 = ";
        cout << BinaryToDecimal10(number) << endl;
        cout << "2 → 8 = ";
        BinaryToOctal8(binDecimal);
        cout << "2 → 16 = ";
        BinaryToHexadecimal16(binDecimal);
        break;
    }

    case 8:
    {
        CheckOctal(number);
        int octDecimal = OctalToDecimal10(number);

        cout << "8 → 10 = ";
        cout << OctalToDecimal10(number) << endl;
        cout << "8 → 2 = ";
        OctalToBinary2(octDecimal);
        cout << "8 → 16 = ";
        OctalToHexadecimal16(octDecimal);
        break;
    }

    case 16:
    {
        CheckHexadecimal(hexNumber);
        int hexDecimal = HexadecimalToDecimal10(hexNumber);

        cout << "16 → 10 = ";
        cout << HexadecimalToDecimal10(hexNumber) << endl;
        cout << "16 → 8 = ";
        HexadecimalToOctal8(hexDecimal);
        cout << "16 → 2 = ";
        HexadecimalToBinary2(hexDecimal);
        break;
    }

    default:
    {
        cout << "Введена система числення не підтримується";
        break;
    }
    }
    return 0;
}

// Перевод двоичных чисел
int BinaryToDecimal10(int number)
{

    string tempStr = to_string(number);
    int BinToDecimal = stoul(tempStr, nullptr, 2);
    return BinToDecimal;
}

int BinaryToOctal8(int binDecimal)
{

    cout << oct << binDecimal << endl;
}

int BinaryToHexadecimal16(int binDecimal)
{

    cout << hex << binDecimal << endl;
}

// Перевод восьмеричных чисел

int OctalToDecimal10(int number)
{

    string tempStr = to_string(number);
    int OctToDecimal = stoul(tempStr, nullptr, 8);
    return OctToDecimal;
}

int OctalToBinary2(int octDecimal)
{

    string OctToBinary = bitset<64>(octDecimal).to_string();
    OctToBinary.erase(0, OctToBinary.find_first_not_of('0'));
    cout << OctToBinary << endl;
}

int OctalToHexadecimal16(int octDecimal)
{

    cout << hex << octDecimal << endl;
}

// Перевод шестнадцатеричных чисел

int HexadecimalToDecimal10(string hexNumber)
{

    int HexToDecimal = stoul(hexNumber, nullptr, 16);
    return HexToDecimal;
}

int HexadecimalToBinary2(int hexDecimal)
{

    string HexToBinary = bitset<64>(hexDecimal).to_string();
    HexToBinary.erase(0, HexToBinary.find_first_not_of('0'));
    cout << HexToBinary << endl;
}

int HexadecimalToOctal8(int hexDecimal)
{

    cout << oct << hexDecimal << endl;
}

// Перевод десятичных чисел

int DecimalToBinary2(int number)
{

    string DecToBinary = bitset<64>(number).to_string();
    DecToBinary.erase(0, DecToBinary.find_first_not_of('0'));
    cout << DecToBinary << endl;
}

int DecimalToOctal8(int number)
{

    cout << oct << number << endl;
}
int DecimalToHexadecimal16(int number)
{

    cout << hex << number << endl;
}

// Проверки

int CheckBinary(int number)
{

    string tempStr = to_string(number);

    if (number <= 0)
    {
        cout << "Некоректно введене число!";
        exit(1);
    }

    for (int i = 0; i < tempStr.size(); i++)
        if (tempStr[i] > '1')
        {

            cout << "Некоректно введене число!";
            exit(1);
        }
}

int CheckOctal(int number)
{
    string tempStr = to_string(number);

    if (number <= 0)
    {
        cout << "Некоректно введене число!";
        exit(1);
    }

    if (tempStr.find_first_not_of("01234567") != string::npos)
    {
        cout << "Некоректно введене число!";
        exit(1);
    }
}

int CheckDecimal(int number)
{

    if (number <= 0)
    {
        cout << "Некоректно введене число!";
        exit(1);
    }
}

int CheckHexadecimal(string hexNumber)
{

    if (hexNumber.find_first_not_of("0123456789abcdefABCDEF") != string::npos)
    {
        cout << "Некоректно введене число!";
        exit(1);
    }
}
