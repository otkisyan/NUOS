//Заполнение двумерного массива: элементы в заштрихованной области генерируются случайным образом, все остальные равно N; 
//Упорядочить по убыванию случайные элементы находящиеся в заштрихованной области.
//ЗАШТРИХОВАННАЯ ОБЛАСТЬ - НИЖНИЙ ТРЕУГОЛЬНИК

//ВАРИАНТ: СОРТИРОВКА В ОБЩЕМ ВСЕХ ЭЛЕМЕНТОВ НАХОДЯЩИХСЯ В ЗАШТРИХОВАННОЙ ОБЛАСТИ ПО УБЫВАНИЮ

#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <iomanip>
using namespace std;

int **CreateMatrix (int rows, int cols);
int FindSize(int **matrix, int rows, int cols);
void FillMatrix (int **matrix, int rows, int cols, int arraySize);
void OutputMatrix (int **matrix, int rows, int cols);
void DellMatrix(int **matrix, int rows);


int main(){

    srand(time(NULL));

    int rows = 9, cols = 9;
    int **matrix = CreateMatrix(rows, cols);
    int arraySize = FindSize(matrix, rows, cols);
    FindSize(matrix, rows, cols);
    FillMatrix(matrix, rows, cols, arraySize);
    cout << "Цикл до сортування: " << endl;
    OutputMatrix(matrix, rows, cols);

    cout << endl;
    DellMatrix(matrix, rows);


    system("pause");
    return 0;
}

int **CreateMatrix(int rows, int cols){     //создаем двумерную матрицу

    int **matrix = new int *[rows];

    for (int i = 0; i < rows; i++)
    {
        matrix[i] = new int [cols];

    }
    return matrix;

}

int FindSize(int **matrix, int rows, int cols){  //ищем количество элементов в заштрихованой области
    int arraySize = 0;
    for (int i = rows - 1; i > rows / 2 - 1; i--)
    {
        for (int k = cols - 1 - i; k < 1 + i; k++)
        {
            arraySize++;
        }

    }
    return arraySize;
}


void FillMatrix(int **matrix, int rows, int cols, int arraySize){

    int N = 19; //непарный вариант
    int matrix2[arraySize]; //создаем одномерный массив с размерностю из функции FindSize
    int tmp;

    for (int l = 0; l < arraySize; l++) {
        matrix2[l] = rand() % 100;      //заполняем массив случайными числами
    }

    for(int i = 0; i < arraySize; i++)      //сортировка методом выбора по убыванию
    {
        int pos = i;
        tmp = matrix2[i];
        for(int j = i + 1; j < arraySize; j++)
        {
            if (matrix2[j] < tmp)
            {
                pos = j;
                tmp = matrix2[j];
            }
        }
        matrix2[pos] = matrix2[i];
        matrix2[i] = tmp;
    }


    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {

            matrix[i][j] = N; //заполняем всю матрицу числом N

        }
    }

    int index = 0;
    for (int i = rows - 1; i > rows / 2 - 1; i--) //(rows / 2) - делим на 2 так как у нас нижний треугольник, (rows - 1) -1 так как цикл с 0 чтобы не выйти за область памяти
        // тоесть условно наша картинка делится на 2 части, там где треугольник, и там где все остальное
    {
        for (int k = cols - 1 - i; k < 1 + i; k++) //(cols - i) - так как генерируем треугольник то удаляем каждый раз элементы по бокам
            //(cols - i) | условно: cols = 8; i = 7; cols = 8 - 7 = 1; i каждый раз уменшаеться, тем самым получается сужение и треугольник
            // (сужаем область генерации чисел, вследствии получается треугольник)
        {


                matrix[i][k] = matrix2[index++]; //заполняем заштрихованную область (треугольник) одномерным массивом со случайными числами созданным ранее


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

void DellMatrix(int **matrix, int rows){

    for (int i = 0; i < rows; i++)
    {
        delete[] matrix[i];
    }
    delete[] matrix;

}

________________

//2 ВАРИАНТ: СОРТИРОВКА ЭЛЕМЕНТОВ НАХОДЯЩИХСЯ В ЗАШТРИХОВАННОЙ ОБЛАСТИ ПОСТРОЧНО (сортировка строк)
#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <iomanip>
using namespace std;

int **CreateMatrix (int rows, int cols);
void FillMatrix (int **matrix, int rows, int cols);
void OutputMatrix (int **matrix, int rows, int cols);
void SortMatrix (int **matrix, int rows, int cols);
void DellMatrix(int **matrix, int rows);

int main(){

    srand(time(NULL));

    int rows = 9, cols = 9;

    int **matrix = CreateMatrix(rows, cols);
    FillMatrix(matrix, rows, cols);
    cout << "Цикл до сортування: " << endl;
    OutputMatrix(matrix, rows, cols);

    cout << endl;
    SortMatrix(matrix, rows, cols);
    cout << "Цикл після сортування: " << endl;
    OutputMatrix(matrix, rows, cols);
    DellMatrix(matrix, rows);


    system("pause");
    return 0;
}



int **CreateMatrix(int rows, int cols){     //создаем двумерную матрицу

    int **matrix = new int *[rows];

    for (int i = 0; i < rows; i++)
    {
        matrix[i] = new int [cols];

    }
    return matrix;

}

void FillMatrix(int **matrix, int rows, int cols){

    int N = 19; //непарный вариант

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {

            matrix[i][j] = N; //заполняем всю матрицу числом N

        }
    }

        for (int i = rows - 1; i > rows / 2 - 1; i--) //(rows / 2) - делим на 2 так как у нас нижний треугольник,
                                                     // тоесть условно наша картинка делится на 2 части, там где треугольник, и там где все остальное
        {
            for (int k = cols - 1 - i; k < 1 + i; k++) //(cols - i) - так как генерируем треугольник то удаляем каждый раз элементы по бокам
                                                       // (сужаем область генерации чисел, вследствии получается треугольник)
            {
                matrix[i][k] = rand() % 100; //заполняем заштрихованную область (треугольник) случайными числами
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

void SortMatrix (int **matrix, int rows, int cols){ //сортировка выбором

    int max, maxi;

    for (int k = rows - 1; k > rows / 2 - 1; k--) //
    {                                             // берем те же самые циклы которые мы брали при заполнении матрицы
        for (int i = cols - 1 - k; i < 1 + k; i++) // так как мы сортируем только элементы в заштрихованной области
        {

            max = matrix[k][i]; //берем максимальный элемент первый элемент на последней строке, так как идем снизу вверх
            maxi = i; //записываем индекс первого элемента


            for (int j = i + 1; j <= k; j++) // (j = i + 1) | берем i + 1, так как мы уже взяли 0 элемент как максимальный
                                            // и сравниваем его со следующим
            //берем (j <= k) так как k = rows - 1 = 9 - 1 = 8, берем строгое равенство чтобы цикл доходил до 8,
            //иначе цикл не будет брать последний элемент и вследствии сортировка будет неверной
            {

                if (matrix[k][j] > max) //сравниваем текущий элемент с условно максимальным
                {
                    max = matrix[k][j]; //если элемент оказался больше то записываем его в max
                    maxi = j;           //записываем его индекс
                }
            }

            matrix[k][maxi] = matrix[k][i]; //идем к элементу на строке k с индексом maxi
                                            //и меняем его на элемент на строке k с индексом i
            matrix[k][i] = max; //меняем элемент на строке k с индексом i на элемент max
            //тоесть берем наибольшие элементы и ставим их по порядку друг за другом, начиная с самого большого.
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
