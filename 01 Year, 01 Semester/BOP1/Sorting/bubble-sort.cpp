// Дана последовательность действительных чисел.
// Расположить элементы последовательности, стоящие на четных местах в порядке убывания (остальные элементы остаются на своих местах).
// Метод сортировки - пузырьком.

#include <iostream>
#include <cmath>
#include <windows.h>
#include <stdlib.h>
#include <iomanip>
using namespace std;

void FloatArr(float Arr[], const int n);
void ArrOutput(float Arr[], const int n);

int main()
{

  SetConsoleCP(65001);
  SetConsoleOutputCP(65001);
  srand(time(NULL));

  const int n = 22;
  float Arr[n];

  FloatArr(Arr, n);
  cout << "Масив до сортировки: " << endl;
  ArrOutput(Arr, n);

  // (int i=1; i < n - 1; i += 2) Начальное значение i = 1, оно каждый раз сравнивается с n-1 а в конце итерации увеличивается на 2.
  // (int j = 1; j < n - i - 1; j += 2) обход начинается с 1 и заканчивает на на элементе отстоящем от конца массива на i.
  // if (Arr[j] < Arr[j+2]) swap(Arr[j], Arr[j+2]) "выдавливает" меньший элемент в конец массива (который мы после не проверяем)

  for (int i = 1; i < n; i += 2)
  {
    for (int j = 1; j < n - i; j += 2)
    {
      if (Arr[j] < Arr[j + 2])
      {
        swap(Arr[j], Arr[j + 2]);

        // if (Arr[j] < Arr[j+2]){
        // int temp = Arr[j];     - 2 вариант
        // Arr[j] = Arr[j + 2];
        // Arr[j + 2] = temp;
      }
    }
  }
  cout << endl;

  cout << "Масив після сортировки: " << endl;
  ArrOutput(Arr, n);

  system("pause");
  return 0;
}

// Генерация случайных действительных чисел
void FloatArr(float Arr[], const int n)
{

  for (int i = 0; i < n; i++)
  {
    Arr[i] = (rand() % 200 - 99) - (float)(rand() % 201) / 100.0;
  }
}

// Вывод массива на экран
void ArrOutput(float Arr[], const int n)
{

  for (int i = 0; i < n; i++)
  {
    cout << fixed << setprecision(2) << " " << Arr[i];
  }
  cout << endl;
}
