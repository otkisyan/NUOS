#include <iostream>
#include <cmath>
#include <windows.h>
using namespace std;

int main(){

	// Определить попадает ли введеное пользователем число в диапазон от 1 до 10
	SetConsoleCP(65001);
	SetConsoleOutputCP(65001);

	
	int number;

	cout << "Введите число: " << endl;
	cin >> number;

// 1 способ
	if (number > 0 && number <= 10)
	{
		cout << "Число входит в диапазон" << endl;
	}

	else
	{
		cout << "Число не входит в диапазон" << endl;
	}



// 2 способ
	if (!(number <= 0 || number > 10)){ 

		cout << "Число входит в диапазон" << endl;

	}

	else
	{
		cout << "Число не входит в диапазон" << endl;
	}



	system("pause");

	}

	
