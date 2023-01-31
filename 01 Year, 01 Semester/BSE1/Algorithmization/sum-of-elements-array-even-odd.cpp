// Дан двумерный массив чисел A размерности k * l.
// Вычислить сумму элементов четных и сумму элементов нечетных строк массива.
// Вывести на экран исходный массив A и вычисленные суммы.

#include <iostream>
#include <cmath>
#include <windows.h>
#include <stdlib.h>
#include <iomanip>
using namespace std;

int main()
{

    SetConsoleCP(65001);
    SetConsoleOutputCP(65001);

    int sumEven = 0;
    int sumOdd = 0;
    const int k = 5;
    const int l = 5;

    int A[k][l];

    cout << "Введіть матрицю " << k << " x " << l << endl;

    cout << endl;

    for (int i = 0; i < k; i++)
    {

        for (int j = 0; j < l; j++)
        {
            cout << "A[" << i << j << "]: ";
            cin >> A[i][j];
        }
    }

    cout << "Матриця:" << endl;
    cout << endl;

    for (int i = 0; i < k; i++)
    {
        for (int j = 0; j < l; j++)
        {
            cout << A[i][j] << " | ";
        }
        cout << endl;
    }
    cout << endl;

    cout << "Сумма елементів парних строк матриці: " << endl;
    for (int i = 0; i < k; i += 2)
    {
        sumEven = 0;
        for (int j = 0; j < l; j++)
        {
            sumEven += A[i][j];
        }

        cout << "Сума елементів " << i << " строки: " << sumEven << endl;
    }

    cout << endl;

    cout << "Сумма елементів непарних строк матриці: " << endl;
    for (int i = 1; i < k; i += 2)
    {
        sumOdd = 0;
        for (int j = 0; j < l; j++)
        {
            sumOdd += A[i][j];
        }

        cout << "Сума елементів " << i << " строки: " << sumOdd << endl;
    }

    cout << endl;

    system("pause");
    return 0;
}

// --------------------------------------- //
// 2 версия
void task(const int k, const int l)
{

    int array[k][l] = {0};

    for (int i = 0; i < k; i++)
    {
        for (int j = 0; j < l; j++)
        {
            array[i][j] = rand() % 10 - 1;
        }
    }

    int sumPaired = 0, sumNotPaired = 0;
    for (int i = 0; i < k; i++)
    {
        for (int j = 0; j < l; j++)
        {
            if (i % 2 == 0)
            {
                sumPaired += array[i][j];
            }
            else
            {
                sumNotPaired += array[i][j];
            }
        }
    }

    for (int i = 0; i < k; i++)
    {
        for (int j = 0; j < l; j++)
        {
            cout << array[i][j] << "\t";
        }
        cout << endl;
    }

    cout << "\n sumPaired = " << sumPaired << endl
         << "\n sumNotPaired = " << sumNotPaired << endl;
}
