// Программа, которая выводит сумму элементов массива а, значения которых двузначные четные числа.

#include <iostream>
#include <cmath>
#include <windows.h>
using namespace std;

int main()
{

    SetConsoleCP(65001);
    SetConsoleOutputCP(65001);

    int n = 18;
    int a[18], b[18];
    b[0] = 19;
    int sum = 0;

    for (int i = 0; i < n - 1; i++)
    {

        b[i + 1] = (37 * b[i] + 3) % 64;
    }

    for (int i = 0; i < n; i++)
    {

        a[i] = b[i] - 32;
        cout << " " << a[i];
    }

    for (int i : a)
    {

        if (i % 2 == 0 && abs(i) >= 10 && abs(i) < 100)
        {
            sum += i;
        }
    }

    cout << endl
         << "Сума елементів масиву A, значення яких двузначні парні числа: " << sum << endl;

    system("pause");
}
