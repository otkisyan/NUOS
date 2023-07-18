// Разбить строку на слова на основе разделителей

#include <iostream>
#include <cmath>
#include <windows.h>
#include <stdlib.h>
#include <iomanip>
#include <string>
using namespace std;

int main()
{

    SetConsoleCP(65001);
    SetConsoleOutputCP(65001);

    const int N = 256;
    int x = 0;

    const char *SEPARATORS = "!?.,;/*-+\n\t "; // Разделители
    char S[N] = "We study C++ programming language first semester.";
    cout << S << endl;

    char *ptr = 0;

    ptr = strtok(S, SEPARATORS);

    while (ptr)
    {
        cout << ptr << endl;
        ptr = strtok(0, SEPARATORS);
        x++;
    }

    cout << x << endl;

    system("pause");
}
