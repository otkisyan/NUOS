// Вводится с клавиатуры последовательность ненулевых действительных чисел, которая заканчивается 0.
// Используя рекурсивную функцию, найти среднее арифметическое чисел.

#include <iostream>
using namespace std;

float FindSum(float *arr, int N, int i);
float FindAverage(float *arr, int N);
void FillArray(float *&arr, int &N);
void PrintArray(float *arr, int N);

int main()
{

    int N = 0;
    float *arr = new float[N];

    FillArray(arr, N);
    cout << "Кількість елементів послідовності: ";
    cout << N << endl;
    cout << "Елементи послідовності: ";
    PrintArray(arr, N);
    cout << endl;
    float arithAverage = FindAverage(arr, N);
    cout << arithAverage;

    return 0;
}

float FindSum(float *arr, int N, int i)
{

    /* float sum = 0.0;
     for (int i = 0; i < N; i++) {
         sum += arr[i];
     }

     return sum;*/

    if (i >= N)
    {
        return 0;
    }

    // float sum = FindSum(arr, N, i + 1);
    // return arr[i] + sum;

    return arr[i] + FindSum(arr, N, i + 1);

    // Рекурсия при удовлетворении условии выхода схлопывается в обратном порядке
}

float FindAverage(float *arr, int N)
{

    return FindSum(arr, N - 1, 0) / (N - 1); // N - 1, потомучто последний элемент массива - 0, его не считаем
}

void FillArray(float *&arr, int &N)
{

    int i = 0;
    cout << "Введіть елементи послідовності: " << endl;

    do
    {
        cin >> arr[i];

        // if (arr[i] == 0){
        //   return;
        //}

        i++;
        N++;

    } while (arr[i - 1] != 0); // берем arr[i - 1], так как после записи элемента в массив происходит i++,
    // нам надо смотреть значение элемента который мы только что записали, поэтому берем i - 1
}

void PrintArray(float *arr, int N)
{
    for (int i = 0; i < N; i++)
    {
        cout << arr[i] << " ";
    }
}
