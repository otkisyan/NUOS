// Подсчитать количество цифр во введенном пользователем числе, их сумму и установить цифры в обратном порядке. 
// Предусмотреть возможность повторения действия пользователя несколько раз.

#include <iostream>
#include <cmath>
#include <windows.h>
#include <stdlib.h>
#include <iomanip>
using namespace std;

int Amount(int x);
int Sum(int x);
int Reverse (int x);

int main(){
 
SetConsoleCP(65001);
SetConsoleOutputCP(65001);

int x = 0, n = 0;

do
{
    int choice;
    cout << "Введите число: " << endl;
    cin >> x;
    n = 1;

    Amount(x);
    Sum(x);
    Reverse(x);
    
    cout << "Желаете продолжить работу? 1 - да, 2 - нет" << endl;
    cin >> choice;

    switch (choice)
    {
    case 1:
        n = 0;
        break;
    
    default:
        break;
    }
    

} while (n == 0);

 system("pause");

}


int Amount (int x){

int digits = 0;

while (x > 0)
    {
        x /= 10;
        digits++;
       
    }

  cout << "Количество цифр в числе: " << digits << endl;

}

int Sum (int x){

    int sum = 0;
     while (x > 0)
    {
        sum += x % 10;
        x /= 10;
    }
     cout << "Сумма цифр числа: " << sum << endl;
}

int Reverse (int x){

    int reverse = 0;
    

    while (x > 0) 
    {
        
        reverse = reverse * 10 + x % 10;
        x /= 10;
    }
    cout << "Обратная запись числа: " << reverse <<  endl;

}
