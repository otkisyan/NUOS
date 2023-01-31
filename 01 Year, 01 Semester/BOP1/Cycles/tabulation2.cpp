// Табулирование arcsin x

#include <iostream>
#include <cmath>
#include <windows.h>
#include <iomanip>
using namespace std;

void tab(double aStep, double bStep, double stepSize);

int main()
{

    SetConsoleCP(65001);
    SetConsoleOutputCP(65001);

    double aStep, bStep, stepSize;

    cout << "Зміна значення від: " << endl;
    cin >> aStep;
    cout << "До: " << endl;
    cin >> bStep;
    cout << "Розмір кроку: " << endl;
    cin >> stepSize;
    tab(aStep, bStep, stepSize);

    system("pause");
}

void tab(double aStep, double bStep, double stepSize)
{

    double x;
    double y;
    int i = 0;
    for (x = aStep; x <= bStep; x = aStep + i * stepSize)
    {

        if ((x >= -1) && (x <= 1))
        {
            cout << "arcsin (x) = " << setprecision(1) << x;
            y = asin(x);
            cout << " | y = " << fixed << setprecision(6) << y << endl;
            i++;
        }

        else
        {
            cout << "arcsin (x) = " << setprecision(1) << x << " | y = "
                 << "Функція не існує" << endl;
        }
    }
}
