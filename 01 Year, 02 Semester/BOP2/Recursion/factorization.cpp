#include <iostream>
using namespace std;

void f(int n, int k) {
    if (k > n / 2) { //если k > n / 2
        cout << n << " "; //выводим n
        return; //выход
    }
    if (n % k == 0) { //если n делится на k без остатка
        cout << k << " "; //выводим k
        f(n / k, k); //рекурсивная функция, в первом параметре передаем n / k, во втором k
    }
    else f(n, ++k);
}
int main() {
    int N = 19;
    f(125 + N,2); // 2 - минимальное простое число

    return 0;
}
