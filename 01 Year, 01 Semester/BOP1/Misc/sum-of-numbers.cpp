// Пользователь с клавиатуры последовательно вводит целые числа. Как только ввел 0 показать на экране сумму всех введенных чисел. 
// Решить задачу 3 способами

#include <iostream>
#include <cmath>
#include <windows.h>
using namespace std;

int main(){

SetConsoleCP(65001);
SetConsoleOutputCP(65001);

int x, sum = 0;


// 1 способ
for (;;)

{
cout << "Введіть число:";
cin >> x;

if (x == 0)
break;
sum += x;

}
cout << "Сума: " << sum << endl;




// 2 споосб
while (x != 0) {

    cout << "Введіть число: " << endl;
    cin >> x;
    sum += x;


}

  
    cout << "Сума: " << sum << endl;




// 3 способ
do
{
    cout << "Введіть число: " << endl;                      
    cin >> x;
    sum += x;

} 
while (x != 0 );

cout << "Сума: " << sum << endl;

system("pause");

}
