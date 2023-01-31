// Угол между векторами a={2; -4; 4} и b={-3; 2; 6}

#include <iostream>
#include <cmath>
#include <stdio.h>
#include <windows.h>
using namespace std;

int main()
{

	SetConsoleOutputCP(1251);
	SetConsoleCP(1251);

	int a1, a2, a3, b1, b2, b3;
	cout << "Введіть координати a1, a2, a3, b1, b2, b3" << endl;
	cin >> a1 >> a2 >> a3 >> b1 >> b2 >> b3;

	float scalar = a1 * b1 + a2 * b2 + a3 * b3;
	cout << "Скалярный добуток векторів: " << scalar << endl;

	int vectorA = sqrt(pow(a1, 2) + pow(a2, 2) + pow(a3, 2));
	cout << "Модуль вектора A: " << vectorA << endl;

	int vectorB = sqrt(pow(b1, 2) + pow(b2, 2) + pow(b3, 2));
	cout << "Модуль вектора B: " << vectorB << endl;

	float vectorAngle = (scalar / abs(vectorA * vectorB));
	cout << "Кут між векторами a = {" << a1 << "," << a2 << "," << a3 << "}"
		 << " i "
		 << "b = {" << b1 << "," << b2 << "," << b3 << "}"
		 << " = " << vectorAngle;
}
