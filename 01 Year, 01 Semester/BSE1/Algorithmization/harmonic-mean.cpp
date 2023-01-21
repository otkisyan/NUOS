// Представить схему алгоритма вычисления следующих величин если элементы массива определяются по формуле a(i+1)= mod ((37*a(i)+3) ,64). 
// Значение a1 равняется N (номеру варианта). Индексы массива А изменяются от 1 до 19.
// X + Z / Y + 2, 
// где X - число положительных элементов с нечетным номером, Y - номер последнего нулевого элемента
// Z - произведение первых M элементов не равных 0, M - Среднее гармоническое всех элементов массива не равных 0 

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

const int n = 19;
int x = 0, y = 0, z = 1;
float result = 0;
int a[n]; 
a[0] = 19;
bool nullElement = false;

float elementDenominator = 0;
int m = 0;



// Обчисленння елементів масиву
for(int i = 0; i < n; i++)
 {
  a[i + 1] = ((37 * a[i] + 3) % 64);
  cout << " " << a[i];
  
}
cout << endl;


// Число додатніх елементів з непарним номером
for (int i = 0; i < n; i+=2)
 {    
  if (a[i] > 0) {
    x++;
    
  }
 } 
 

// Номер останнього нульового елементу
for (int i = 0; i < n; i++)
{
  if (a[i] == 0){
      y = i;
      nullElement = true;
  }
  
}


// Серєднє гармонічне (Знаходження знаменника)
for (int i = 0; i < n; i++)
{
  if (a[i] != 0) {
    elementDenominator += 1.0 / a[i];
}
}

m = n / elementDenominator;

cout << "M: " << m;


// Добуток перших М елементів не рівних 0
for (int i = 0; i < m; i++)
{
 if (a[i] != 0) {
   
   z *= a[i];
}
}


// Выведення всіх данних на екран
cout << endl;
cout << "Число додатніх елементів з непарним номером масиві: X = " << x << endl;

if (nullElement)
{
cout << "Номер останнього нулевого елементу: Y = " << y <<  endl;

}
else{
  
  cout << "Нульових елементів в массиві не знайдено" << endl;
}


cout << "Добуток перших M елементів не рівних 0 = " << z << endl;

result = (x + z) / (y + 2);
cout << "(x + z) / (y + 2) = " << result << endl;

system("pause");

}
