#include <iostream>
#include <cmath>
#include <windows.h>
#include <stdlib.h>
#include <iomanip>
using namespace std;
 
const int n = 3;
const int m = 4;
 
void MatrixFill (int A[n][m], const int n, const int m);
void MatrixOutputA (int A[n][m], const int n, const int m);
void MatrixOutputB (int B[m][n], const int n, const int m);
void MatrixTranspose (int A[n][m], int B[m][n], const int n, const int m);
 
int main(){
 
SetConsoleCP(65001);
SetConsoleOutputCP(65001);
srand (time(NULL));
 
int A[n][m];
int B[m][n];
 
MatrixFill(A, n, m);
cout << "Початкова матриця" << endl;
MatrixOutputA (A, n, m);
cout << endl;
MatrixTranspose (A, B, m, n);
cout << "Транспонована матриця" << endl;
 
MatrixOutputB(B, n , m);
 
 system("pause");
 return 0;
}
 
 
void MatrixFill(int A[n][m], const int n, const int m){
 
for (int i = 0; i < n; i++)
{
    for (int j = 0; j < m ; j++)
    {
        A[i][j] = rand() % 100;
    }
    
}
}
 
void MatrixOutputA (int A[n][m], const int n, const int m){
 
   for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cout << A[i][j] << " | ";
        }
        cout << endl;
}
}
 
void MatrixOutputB (int B[m][n], const int n, const int m){
 
   for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << B[i][j] << " | ";
        }
        cout << endl;
}
}
 
 
void MatrixTranspose (int A[n][m], int B[m][n], const int n, const int m){
    
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
           B[i][j] = A[j][i];
           
        }
        
}
}


// --------------------------------- //
// 2 версия

#include <iostream>
#include <windows.h>
#include <iomanip>
 
using namespace std;
 
const int n = 3;
const int m = 4;
 
void MatrixFill(int A[n][m]);
 
void MatrixOutput(int A[n][m]);
 
void MatrixOutput(int A[m][n]);
 
void MatrixTranspose(int A[n][m], int B[m][n]);
 
int main() {
 
    SetConsoleCP(65001);
    SetConsoleOutputCP(65001);
    srand(time(NULL));
 
    int A[n][m];
    int B[m][n];
 
    MatrixFill(A);
    cout << "Початкова матриця" << endl;
    MatrixOutput(A);
    cout << endl;
    MatrixTranspose(A, B);
    cout << "Транспонована матриця" << endl;
 
    MatrixOutput(B);
 
    system("pause");
    return 0;
}
 
 
void MatrixFill(int A[n][m]) {
 
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            A[i][j] = rand() % 100;
        }
 
    }
}
 
void MatrixOutput(int A[n][m]) {
 
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cout << A[i][j] << " | ";
        }
        cout << endl;
    }
}
 
void MatrixOutput(int A[m][n]) {
 
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cout << A[i][j] << " | ";
        }
        cout << endl;
    }
}
 
 
void MatrixTranspose(int A[n][m], int B[m][n]) {
 
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            B[j][i] = A[i][j];
 
        }
 
    }
}

