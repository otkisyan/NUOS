// Программа для вывода простых чисел с диапазона

#include <iostream>
#include <cmath>
#include <windows.h>
using namespace std;

bool prime (int a){
bool s = true; // изначально считаем что число простое
for (int i = 3; i <= sqrt(a); i += 2) // перебираем все нечетные числа до корня из a
 if (a % i == 0){ // если a делится на это число без остатка, то
    return false; //... число не простое (составное) - возвращаем ложь
 }
 
 return s;

}
 
int main(){
 
 
SetConsoleCP(1251);
SetConsoleOutputCP(1251);
 
 int n = 1000;
 
 cout << "2 ";
 for (int i=3; i < n; i+=2) 
 
 if (prime (i)){
     
    cout << i << " ";
    
 }


 system("pause");
}
