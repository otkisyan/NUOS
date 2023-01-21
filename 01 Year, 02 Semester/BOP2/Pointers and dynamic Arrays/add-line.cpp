//Добавить строку, после строки, в которой находится наибольший элемент

#include <iostream>
#include <cmath>
#include <cstdlib>
#include <iomanip>

using namespace std;
 
int **CreateMatrix (int rows, int cols);
void FillMatrix (int **matrix, int rows, int cols);
void OutputMatrix (int **matrix, int rows, int cols);
void ModifyMatrix (int**& matrix, int& rows, int& cols, int &maxValue, int &rowIndex);
void DellMatrix(int **matrix, int rows);
 
int main(){
 
    srand(time(NULL));
 
    int rows = 9, cols = 12;
    int maxValue = INT_MIN;
    int rowIndex = 0;
     
    int **matrix = CreateMatrix(rows, cols);
    FillMatrix(matrix, rows, cols);
    cout << "Цикл до модифікації: " << endl;
    OutputMatrix(matrix, rows, cols);
    cout << endl;
     
    ModifyMatrix(matrix, rows, cols, maxValue, rowIndex);
    cout << "Максимальний елемент масиву: " << maxValue << endl;
    cout << "Індекс строки в якій знаходиться максимальний елемент масиву: " << rowIndex << endl;
    cout << endl;
     
    cout << "Цикл після модифікації: " << endl;
    OutputMatrix(matrix, rows, cols);
    
    DellMatrix(matrix, rows);
     
    system("pause");
    return 0;
}
 
 
int **CreateMatrix(int rows, int cols){         //создаем матрицу
 
    int **matrix = new int *[rows];
 
    for (int i = 0; i < rows; i++)
    {
      matrix[i] = new int [cols];
      
    }
    return matrix;
    
}
 
void FillMatrix(int **matrix, int rows, int cols){      //заполняем матрицу случайными числами
 
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            matrix[i][j] = rand() % 100; // ( rand() % 100 - 200)
        }
        
    }
}
 
 
void OutputMatrix(int **matrix, int rows, int cols){        //выводим матрицу на экран
 
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            cout << setw(5) << matrix[i][j]; 
        }
        cout << endl;
    }
}
 
void ModifyMatrix (int**& matrix, int &rows, int &cols, int &maxValue, int &rowIndex){     //модификация матрицы
 
for (int i = 0; i < rows; i++)                      //поиск наибольшего элемента в матрице и индекса строки в которой он находится
{
    for (int j = 0; j < cols; j++)
    {
        if (matrix[i][j] > maxValue) {
            maxValue = matrix[i][j];
            rowIndex = i;
        }
    }
    }                           
 
 
int *row = new int[cols];       //создаем строку которую планируем добавить
 
cout << "New row: " << endl;
 
for (int i = 0; i < cols; i++)
{ 
        row[i] = rand() % 100;      //заполняем строку случайными числами и выводим на экран для дальнейшей проверки
        cout << row[i] << " ";
}
cout << endl << endl; 

 // создание и копирование матрицы
 
 
    int **new_matrix = new int *[rows + 1];          //создаем матрицу в которую запишем новую матрицу с модификациями (rows + 1, так как будем добавлять + 1 строку)
    int i;
    for (i = 0; i <= rowIndex; i++){    //копируем матрицу до строки с наибольшим элементом
        new_matrix[i] = matrix[i]; 
    }

    new_matrix[i] = row; // добавили новую строку
    i++;

    for (;i < rows + 1; i++){
        new_matrix[i] = matrix[i - 1];  //Так как новая матрица на строку больше старой , то вторая часть матрицы , должна копироваться со смещением.
    }
 
    matrix = new_matrix; // вернули новый указатель на матрицу
    rows++; // увеличили количество строк для новой матрицы
 
}


void DellMatrix(int **matrix, int rows){

for (int i = 0; i < rows; i++)
{
    delete[] matrix[i];
}
    delete[] matrix;

}


//Создаёте новый массив int * row = new int[cols];. Заполняете его. Затем для матрицы создаёте новый двумерный массив int ** new_matrix = new int*[rows + 1];. 
//Копируете строки из старой матрицы в новую до номера строки с наибольшим элементом, потом созданный ранее row, потом оставшиеся строки.
