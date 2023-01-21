// Удалить N элементов, начиная с номера K, где N и K вводятся с клавиатуры.

// -------------------------------- // 
// 1 способ с использованием 1 массива

#include <iostream>
#include <cmath>
#include <windows.h>
#include <stdlib.h>
#include <iomanip>
using namespace std;
 
void fillArr(int *arr, int size);
void outputArr(int *arr, int size);
void modifyArr(int *arr, int &size, int n, int k);


int main(){
 
SetConsoleCP(65001);
SetConsoleOutputCP(65001);
srand(time(NULL));
 
int size, n, k;
cout << "Enter size of array: ";
cin >> size;
cout << "Enter n: ";
cin >> n;
cout << "Enter k: ";
cin >> k;

int *arr;
arr = new int[size]; //генерируем динамический массив

if(!arr){
      cout << "Error" << endl; //Проверка выделилась ли память
}
 
fillArr(arr, size);
outputArr(arr, size);
cout << endl;
modifyArr(arr, size, n, k);
outputArr(arr, size);
      
 delete [] arr;    //освобождаем память, после этого в указателе хранится мусор     
 arr = nullptr;    //так как в указателе мусор, приравниваем к 0

 system("pause");
 return 0;
}


void fillArr(int *arr, int size){         //заполняем массив рандомными числами

 for(int i = 0; i < size; i++){     

       *(arr + i) = rand() % 100; //arr[i] = rand() % 100; (используется арифметика указателей)
 }

}


void modifyArr(int *arr, int &size, int n, int k){

for (int j = k - 1; j < size - n; j++){                   //цикл от j = k - 1 до size - n, где K - элемент массива с которого надо начинать удаление элементов, n - количество элементов котороые надо удалить,
                                                      //(size - n) = (размер массива - количество элементов которое надо удалить)

   *(arr + j) = *(arr + j + n); //Arr[j] = Arr[j + n];
                                //допустим, k = 3, n = 2, size = 10 | j = 3 - 1 = 2; size = 10 - 2 = 8;
                                //Тогда, arr[2] = arr[2 + 2]; произошел шаг в 2 элемента, так как N = 2;            
}

size -= n; // удалить size - n, где size - размер массива, n - количество элементов которое надо удалить, для того чтобы показать только модифицированную часть массива

}

void outputArr(int *arr, int size){ //вывод массива на экран
for(int i = 0; i < size; i++){
        
        cout << *(arr + i) << " ";
 }
}

//В одном массиве переставляем элементы, массив с мусором остаёться, показываем кусок массива.



// -------------------------------------- // 
//2 способ с использованием двух массивов

#include <iostream>
#include <cmath>
#include <windows.h>
#include <stdlib.h>
#include <iomanip>
using namespace std;
 
void fillArr(int *arr, int size);
void modifyArr(int *arr, int *be, int &size, int N, int K);
void outputArrA(int *arr, int size);
void outputArrB(int *be, int leng);

int main()
{ 
    srand(time(NULL));
    int size, N, K;
    cout << "Size = "; 
    cin >> size;
 
    int *arr = new int[size];
  
    cout << "N="; cin >> N;
    cout << "K="; cin >> K;
 
    int leng = (size - N);
    int *be = new int[leng];

    fillArr(arr, size);
    outputArrA(arr, size);
    cout << endl;
    modifyArr(arr, be, size, N, K);
    outputArrB(be, leng);
    cout << endl;

    delete []arr;
    delete []be;

    system("pause");
    return 0;
}


void fillArr(int *arr, int size){         

 for(int i = 0; i < size; i++){     

       *(arr + i) = rand() % 100; 
 }
}

void outputArrA(int *arr, int size){ 
for(int i = 0; i < size; i++){
        
        cout << *(arr + i) << " ";
 }
}

void modifyArr(int *arr, int *be, int &size, int N, int K){

for (int i = 0; i < K - 1; i++) {
    *(be + i) = *(arr + i);         //от 0 до K - 1, где K - элемент с которого начинать удаление    
}                                   //допустим, K = 3:  be[i] = arr[i], мы в массив be перегоняем элементы из массива arr до K, тоесть если массив arr = 37, 82, 22, 28, 8, 77
                                    //то be = 37, 82 - 2 элемента, хоть и K = 3, потомучто K - 1, так как начинаем удаление по номеру, а не индексу

for (int i = K + N - 1; i < size; i++) {        //от i = K + N - 1, до size(размер массива arr)
    *(be + i - N) = *(arr + i);                 //допустим, K = 3, N = 2; i = 3 + 2 = 5 - 1 = 4
                                                //be[i - N], где N = 2 - количество элементов которое нужно удалить, если i = K + N = 4, тогда be[4 - 2] = be[2]; be[2] = arr[4] 
                                                //произошел шаг в 2 элемента, так как N = 2;
}                                       
}

void outputArrB(int *be, int leng){ 

for (int i = 0; i < leng; i++){
        cout << be[i] << " ";
}
}
