// https://studfile.net/preview/5680538/page:36/
// Дана квадратная матрица A порядка k.
// Найти сумму элементов матрицы, расположенных над главной диагональю.
// Вывести на экран исходный массив и величину вычисленной суммы.

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

    const int k = 3;
    int sum = 0;
    int A[k][k];

    cout << "Введіть матрицю " << k << " x " << k << endl;

    cout << endl;

    for (int i = 0; i < k; i++)
    {

        for (int j = 0; j < k; j++)
        {
            cout << "A[" << i << j << "]: ";
            cin >> A[i][j];
        }
    }

    cout << "Матриця:" << endl;
    cout << endl;

    for (int i = 0; i < k; i++)
    {
        for (int j = 0; j < k; j++)
        {
            cout << A[i][j] << " | ";
        }
        cout << endl;
    }
    cout << endl;

    for (int i = 0; i < k; i++)
    {
        for (int j = 0; j < k; j++)
        {
            if (i < j)
                sum += A[i][j];
        }
    }
    cout << "Сумма элементов матрицы, расположенных над главной диагональю: " << sum << endl;

    system("pause");

    return 0;
}
