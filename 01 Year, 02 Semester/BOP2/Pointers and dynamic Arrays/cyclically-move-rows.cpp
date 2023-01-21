// Дано массив C (9,12). Непарные рядки массива ссунуть циклически на K элементов влево, где K вводится с клавиатуры

#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <iomanip>
using namespace std;

int **CreateMatrix (int rows, int cols);
void FillMatrix (int **matrix, int rows, int cols);
void OutputMatrix (int **matrix, int rows, int cols);
void ModifyMatrix (int **matrix, int rows, int cols, int K);
void DellMatrix(int **matrix, int rows);

int main(){

srand(time(NULL));

int rows = 9, cols = 12, K;

cout << "Enter K ";
cin >> K;

int **matrix = CreateMatrix(rows, cols);
FillMatrix(matrix, rows, cols);
cout << "Цикл до модифікації: " << endl;
OutputMatrix(matrix, rows, cols);
cout << endl;
ModifyMatrix(matrix, rows, cols, K);
cout << "Цикл після модифікації: " << endl;
OutputMatrix(matrix, rows, cols);
DellMatrix(matrix, rows);


 system("pause");
 return 0;
}


int **CreateMatrix(int rows, int cols){

    int **matrix = new int *[rows];

    for (int i = 0; i < rows; i++)
    {
      matrix[i] = new int [cols];
      
    }
    return matrix;
    
}

void FillMatrix(int **matrix, int rows, int cols){

for (int i = 0; i < rows; i++)
{
    for (int j = 0; j < cols; j++)
    {
        matrix[i][j] = rand() % 100;
    }
    
}
}


void OutputMatrix(int **matrix, int rows, int cols){

for (int i = 0; i < rows; i++)
{
    for (int j = 0; j < cols; j++)
    {
        cout << setw(5) << matrix[i][j]; 
    }
    cout << endl;
}
}

void ModifyMatrix (int **matrix, int rows, int cols, int K){

int tmp;
for (int i = 1; i < rows; i += 2)     // i = 1; i+2 так как только непарные рядки
{
    for (int k = 0; k < K; k++)     // цикл который отвечает за количество циклических сдвигов (K)
{

tmp = matrix[i][0]; // присваиваем tmp =  1 [0] элемент 2 [1] строки

    for (int j = 1; j < cols; j++)
    {

        matrix[i][j - 1] = matrix[i][j];

    }

    matrix[i][cols - 1] = tmp;  // берем последний элемент и меняем его на первый (строка 86)
        
    }

}
}

void DellMatrix(int **matrix, int rows){

for (int i = 0; i < rows; i++)
{
    delete[] matrix[i];
}
    delete[] matrix;

}

