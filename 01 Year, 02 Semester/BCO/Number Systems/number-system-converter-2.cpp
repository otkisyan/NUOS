// Конвертер систем счисления

#include <iostream>
#include <cmath>
#include <stdlib.h>
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
        cout << DecimalToBinary2(number) << endl;
        cout << "10 → 8 = ";
        cout << DecimalToOctal8(number) << endl;
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
        cout << BinaryToOctal8(binDecimal) << endl;
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
        cout << OctalToBinary2(octDecimal) << endl;
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
        cout << HexadecimalToOctal8(hexDecimal) << endl;
        cout << "16 → 2 = ";
        cout << HexadecimalToBinary2(hexDecimal) << endl;
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

    int BinToDecimal = 0, i = 0, rem;

    while (number != 0)
    {
        rem = number % 10;
        number /= 10;
        BinToDecimal += rem * pow(2, i);
        i++;
    }

    return BinToDecimal;
}

int BinaryToOctal8(int binDecimal)
{

    int rem, i = 1, octalNumber = 0;

    while (binDecimal != 0)
    {
        rem = binDecimal % 8;
        binDecimal /= 8;
        octalNumber += rem * i;
        i *= 10;
    }

    return octalNumber;
}

int BinaryToHexadecimal16(int binDecimal)
{

    int r;
    string hexdec = "";
    char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    while (binDecimal > 0)
    {
        r = binDecimal % 16;
        hexdec = hex[r] + hexdec;
        binDecimal = binDecimal / 16;
    }
    cout << hexdec << endl;
}

// Перевод восьмеричных чисел

int OctalToDecimal10(int number)
{

    int OctToDecimal = 0, i = 0, rem;
    while (number != 0)
    {
        rem = number % 10;
        number /= 10;
        OctToDecimal += rem * pow(8, i);
        i++;
    }

    return OctToDecimal;
}

int OctalToBinary2(int octDecimal)
{

    long long OctToBinary = 0;
    int rem, i = 1;

    while (octDecimal != 0)
    {
        rem = octDecimal % 2;
        octDecimal /= 2;
        OctToBinary += rem * i;
        i *= 10;
    }
    return OctToBinary;
}

int OctalToHexadecimal16(int octDecimal)
{

    int r;
    string hexdec = "";
    char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    while (octDecimal > 0)
    {
        r = octDecimal % 16;
        hexdec = hex[r] + hexdec;
        octDecimal = octDecimal / 16;
    }
    cout << hexdec << endl;
}

// Перевод шестнадцатеричных чисел

int HexadecimalToDecimal10(string hexNumber)
{

    int HexToDecimal = 0, rem, i = 0, len = 0;
    char hexDecNum[20];
    hexNumber.c_str();
    strcpy(hexDecNum, hexNumber.c_str());

    while (hexDecNum[i] != '\0')
    {
        len++;
        i++;
    }
    len--;
    i = 0;

    while (len >= 0)
    {
        rem = hexDecNum[len];
        if (rem >= 48 && rem <= 57)
            rem = rem - 48;
        else if (rem >= 65 && rem <= 70)
            rem = rem - 55;
        else if (rem >= 97 && rem <= 102)
            rem = rem - 87;
        else
        {
            cout << "Некоректно ведено число!" << endl;
            return 0;
        }

        HexToDecimal = HexToDecimal + (rem * pow(16, i));
        len--;
        i++;
    }
    return HexToDecimal;
}

int HexadecimalToBinary2(int hexDecimal)
{

    long long HexToBinary = 0;
    int rem, i = 1;

    while (hexDecimal != 0)
    {
        rem = hexDecimal % 2;
        hexDecimal /= 2;
        HexToBinary += rem * i;
        i *= 10;
    }
    return HexToBinary;
}

int HexadecimalToOctal8(int hexDecimal)
{

    int rem, i = 1, HexToOctal = 0;
    while (hexDecimal != 0)
    {
        rem = hexDecimal % 8;
        hexDecimal /= 8;
        HexToOctal += rem * i;
        i *= 10;
    }
    return HexToOctal;
}

// Перевод десятичных чисел

int DecimalToBinary2(int number)
{

    long long DecimalToBinary = 0;
    int rem, i = 1;

    while (number != 0)
    {
        rem = number % 2;
        number /= 2;
        DecimalToBinary += rem * i;
        i *= 10;
    }
    return DecimalToBinary;
}

int DecimalToOctal8(int number)
{

    int rem, i = 1, octalNumber = 0;

    while (number != 0)
    {
        rem = number % 8;
        number /= 8;
        octalNumber += rem * i;
        i *= 10;
    }

    return octalNumber;
}
int DecimalToHexadecimal16(int number)
{

    int r;
    string hexdec = "";
    char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    while (number > 0)
    {
        r = number % 16;
        hexdec = hex[r] + hexdec;
        number = number / 16;
    }
    cout << hexdec << endl;
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
