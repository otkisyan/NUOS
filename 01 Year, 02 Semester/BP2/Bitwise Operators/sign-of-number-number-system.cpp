// https://ravesli.com/urok-46-bitovye-flagi-i-bitovye-maski/
// https://www.cyberforum.ru/cpp-beginners/thread2990819.html#post16265242
// Данное целое число A, пользуясь бинарными операциями, определить знак числа (число является отрицательным или нет).
// Число A привести в 2, 8, 16 и 10 системах счисления.
//  1 ВАРИАНТ:

#include <iostream>
#include <string>

using namespace std;
string DecimalToBinary(int number);
string DecimalToOctal(int number);
string DecimalToHexadecimal(int number);
int SignBit(int number);

// define INT_SIZE sizeof(int) * 2

int main()
{

    int number = -17;

    SignBit(number);
    cout << endl;
    cout << "10 → 10 = ";
    cout << number << endl;
    cout << "10 → 2 = ";
    cout << DecimalToBinary(number) << endl;
    cout << "10 → 8 = ";
    cout << DecimalToOctal(number) << endl;
    cout << "10 → 16 = ";
    cout << DecimalToHexadecimal(number) << endl;

    return 0;
}

int SignBit(int number)
{
    // старший бит 0 - положительное, 1 - отрицательное

    unsigned mask = 0x800; // 10000000
    int result = number & mask;
    if (result == 0)
    {

        cout << "Число не є від'ємним" << endl;
    }
    else
    {
        cout << "Число є від'ємним" << endl;
    }
}

// char - это знаковое целое число в диапазоне [-128 , 127]
// 239 больше 127, поэтому оно соответствует отрицательному числу равному 239-256=-17
// |-128| + |127| + 1(это нолик между ними) = 256.
string DecimalToBinary(int number)
{

    string binary = "";
    int remainder = 0;
    unsigned char i = number;

    while (i > 0)
    {
        remainder = i % 2;
        i /= 2;
        binary = char('0' + remainder) + binary;
    }

    return binary;
}

string DecimalToOctal(int number)
{

    string octal = "";
    int remainder = 0;
    unsigned char i = number;

    while (i > 0)
    {
        remainder = i % 8;
        i /= 8;
        octal = char('0' + remainder) + octal;
    }

    return octal;
}

string DecimalToHexadecimal(int number)
{

    string hexadecimal = "";
    int remainder = 0;
    char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    unsigned char i = number;

    while (i > 0)
    {
        remainder = i % 16;
        i /= 16;
        hexadecimal = hex[remainder] + hexadecimal; // итоговая строка = остаток от деления + итоговая строка
    }

    return hexadecimal;
}

//----------------------------------------------------------------------------------------------------------------------------//
// 2 ВАРИАНТ

#include <iostream>
#include <string>
using namespace std;

int DecimalToBinary(int number);
int DecimalToOctal(int number);
string DecimalToHexadecimal(int number);
int SignBit(int number);

int main()
{
    int number = -42;

    SignBit(number);
    cout << endl;
    cout << "10 → 10 = ";
    cout << number << endl;
    cout << "10 → 2 = ";
    DecimalToBinary(number);
    cout << endl;
    cout << "10 → 8 = ";
    DecimalToOctal(number);
    cout << endl;
    cout << "10 → 16 = ";
    cout << DecimalToHexadecimal(number) << endl;

    return 0;
}

int SignBit(int number)
{
    // старший бит 0 - положительное, 1 - отрицательное

    unsigned mask = 0x80; // 10000000
    int result = number & mask;
    if (result == 0)
    {

        cout << "Число не є від'ємним" << endl;
    }
    else
    {
        cout << "Число є від'ємним" << endl;
    }
}

/*void SignBit(int number){

    if (signbit(number) == 1) {
        cout << "Число є від'ємним";
    }
    else{
        cout << "Число не є від'ємним";
    }
}*/

int DecimalToBinary(int number)
{
    int decimal = abs(number);
    int binary[100];
    int i = 0;

    while (decimal > 0)
    {

        // binary[i] = decimal % 2;
        binary[i] = decimal & 0x0001; // 0x0001 (16) - 0000 0000 0000 0001 (2)
        // decimal = decimal / 2;
        decimal = decimal >> 1; // (сдвиг битов вправо) 2 в степени n (1) -  (2)

        i++;
    }

    if (number < 0)
    {
        cout << "-";
    }

    for (int j = i - 1; j >= 0; j--) // вывожу массив в обратном порядке
        // j = i - 1 потомучто нумерация элементов в массиве с 0
        // j > = 0, к примеру j = 2 идем в обратном порядке к 0; j--
        cout << binary[j];
}

int DecimalToOctal(int number)
{
    // все также как и с двоичной
    int decimal = abs(number);
    int octal[100];
    int i = 0;
    while (decimal > 0)
    {

        // octal[i] = decimal % 8;
        octal[i] = decimal & 0x0007; // 0x0007 (16) - 0000 0000 0000 0111 (2)
        // decimal = decimal / 8;
        decimal = decimal >> 3; // (сдвиг битов вправо) 2 в степени n (3) -  (2 * 2 * 2)

        i++;
    }

    if (number < 0)
    {
        cout << "-";
    }

    for (int j = i - 1; j >= 0; j--)
        cout << octal[j];
}

string DecimalToHexadecimal(int number)
{

    int decimal = abs(number); // модуль числа
    int remainder = 0;
    string hexadecimal = "";
    char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    while (decimal > 0)
    {
        // remainder = decimal % 16;
        remainder = decimal & 0x000F;               // 0x000F (16) - 0000 0000 0000 1111
        hexadecimal = hex[remainder] + hexadecimal; // итоговая строка = остаток от деления + итоговая строка
        // (новая цифра добавляется в начало, прошлая смещается вправо)

        // decimal = decimal / 16;
        decimal = decimal >> 4; // (сдвиг битов вправо) 2 в степени n (4) - (2 * 2 * 2 * 2)
    }

    if (number < 0)
    {
        hexadecimal.insert(0, "-"); // если число отрицательное добавляем минус в начало строки
    }

    return hexadecimal;
}

//----------------------------------------------------------------------------------------------------------------------------//
// 3 ВАРИАНТ

#include <iostream>
#include <string>
using namespace std;

int DecimalToBinary(int number);
int DecimalToOctal(int number);
string DecimalToHexadecimal(int number);
int SignBit(int number);

int main()
{
    int number = 5;

    SignBit(number);
    cout << endl;
    cout << "10 → 10 = ";
    cout << number << endl;
    cout << "10 → 2 = ";
    cout << DecimalToBinary(number) << endl;
    cout << "10 → 8 = ";
    cout << DecimalToOctal(number) << endl;
    cout << "10 → 16 = ";
    cout << DecimalToHexadecimal(number) << endl;

    return 0;
}

int SignBit(int number)
{
    // старший бит 0 - положительное, 1 - отрицательное

    unsigned mask = 0x80; // 10000000
    int result = number & mask;
    if (result == 0)
    {

        cout << "Число не є від'ємним" << endl;
    }
    else
    {
        cout << "Число є від'ємним" << endl;
    }
}

/*void SignBit(int number){

    if (signbit(number) == 1) {
        cout << "Число є від'ємним";
    }
    else{
        cout << "Число не є від'ємним";
    }
}*/

int DecimalToBinary(int number)
{
    int binary = 0, remainder = 0, i = 1;

    while (number != 0)
    {
        remainder = number % 2;  // получаем остаток от деления (0 или 1)
        number /= 2;             // делим число на 2 каждый раз
        binary += remainder * i; // умножаем остаток на i, добавление в конец цифр
        i *= 10;

        // умножаем i на 10 каждый раз чтобы на каждом следующем шаге i было в 10 раз больше,
        // соответствнно reminder при умножении на i становился десятками, сотнями, тысячами и т.д. перед сложением с конечным результатом.
        // Это можно представить как например число abcd = a * 1000 + b * 100 + c * 10 + d
        //
        // Например number = 5;
        //  --ПЕРВЫЙ ШАГ--
        //  remainder = 5 % 2 = 1;
        //  number = 5 / 2 = 2;
        //  binary (0) += 1 * 1 = 1;
        //  i *= 10 = 10;
        //
        //  --ВТОРОЙ ШАГ--
        //  remainder = 2 % 2 = 0
        //  number (2) /= 2 = 1
        //  binary (1) += 0 * 10 = 1
        //  i *= 10 = 100
        //
        //  --ТРЕТИЙ ШАГ--
        //  remainder = 1 % 2 = 1
        //  number = 1 / 2 = 0
        //  binary (1) += 1 * 100 = 100 = 101
        //  i * 10 = 1000;
    }

    return binary;
}

int DecimalToOctal(int number)
{
    // все также как и с двоичной
    int octal = 0, remainder = 0, i = 1;

    while (number != 0)
    {
        remainder = number % 8;
        number /= 8;
        octal += remainder * i;
        i *= 10;
    }
    return octal;
}

string DecimalToHexadecimal(int number)
{

    int decimal = abs(number); // модуль числа
    int remainder = 0;
    string hexadecimal = "";
    char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    while (decimal > 0)
    {
        remainder = decimal % 16;
        hexadecimal = hex[remainder] + hexadecimal; // итоговая строка = остаток от деления + итоговая строка
        // (новая цифра добавляется в начало, прошлая смещается вправо)
        decimal = decimal / 16;
    }

    if (number < 0)
    {
        hexadecimal.insert(0, "-"); // если число отрицательное добавляем минус в начало строки
    }

    return hexadecimal;
}
