// Определить количество сотен во введеном пользователем вещественном числе

#include <iostream>
#include <cmath>
#include <windows.h>
using namespace std;

int main(){

	SetConsoleCP(65001);
	SetConsoleOutputCP(65001);
	
	float number;
	float hundreds;

	cout << "Введите число: " << endl;
	cin >> number;

	hundreds = (number % 1000) / 100;;
	cout << hundreds << endl;


	system("Pause");

	}
