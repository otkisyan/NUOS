#include <iostream>
#include <chrono>

using namespace std;

/*auto GetCurrentTime() {

    return chrono::high_resolution_clock::now();
}

auto GetDuration(chrono::high_resolution_clock::time_point end, chrono::high_resolution_clock::time_point start) {

    chrono::duration<float, milli> duration = end - start;
    // chrono::duration_cast<chrono::milliseconds>(end - start).count();
    return duration.count();
}*/

unsigned int GetCurrentTime();

double GetDuration(unsigned int end, unsigned int start);

void Sum(int a, int b, int N);

void Minus(int a, int b, int N);

void Multiply(int a, int b, int N);

void Division(int a, int b, int N);

void Sum(double a, double b, int N);

void Minus(double a, double b, int N);

void Multiply(double a, double b, int N);

void Division(double a, double b, int N);


int main() {

    const int N = 5000000;
    int a = 50;
    int b = 25;

    double c = 50.30;
    double d = 25.10;

    // Цілочисельні
    cout << "ЦІЛОЧИСЕЛЬНІ" << endl;

    unsigned int start = GetCurrentTime();
    Sum(a, b, N);
    unsigned int end = GetCurrentTime();
    cout << "Сумма:" << endl;
    cout << GetDuration(end, start) << " міллісекунд" << endl;


    start = GetCurrentTime();
    Minus(a, b, N);
    end = GetCurrentTime();
    cout << "Віднімання:" << endl;
    cout << GetDuration(end, start) << " міллісекунд" << endl;


    start = GetCurrentTime();
    Multiply(a, b, N);
    end = GetCurrentTime();
    cout << "Множення:" << endl;
    cout << GetDuration(end, start) << " міллісекунд" << endl;


    start = GetCurrentTime();
    Division(a, b, N);
    end = GetCurrentTime();
    cout << "Ділення:" << endl;
    cout << GetDuration(end, start) << " міллісекунд" << endl;

    // З плаваючою точкою
    cout << endl;
    cout << "З ПЛАВАЮЧОЮ ТОЧКОЮ" << endl;

    start = GetCurrentTime();
    Sum(c, d, N);
    end = GetCurrentTime();
    cout << "Сумма:" << endl;
    cout << GetDuration(end, start) << " міллісекунд" << endl;


    start = GetCurrentTime();
    Minus(c, d, N);
    end = GetCurrentTime();
    cout << "Віднімання:" << endl;
    cout << GetDuration(end, start) << " міллісекунд" << endl;


    start = GetCurrentTime();
    Multiply(c, d, N);
    end = GetCurrentTime();
    cout << "Множення:" << endl;
    cout << GetDuration(end, start) << " міллісекунд" << endl;


    start = GetCurrentTime();
    Division(c, d, N);
    end = GetCurrentTime();
    cout << "Ділення:" << endl;
    cout << GetDuration(end, start) << " міллісекунд" << endl;

    return 0;
}


unsigned int GetCurrentTime() {

    // Возвращает приблизительное процессорное время,
    // использованное процессом с начала определенного реализацией периода, связанного с выполнением программы.
    return clock();
}

double GetDuration(unsigned int end, unsigned int start) {

    return 1000.0 * (end - start) / CLOCKS_PER_SEC;
}

void Sum(int a, int b, int N) {

    for (int i = 0; i < N; i++) {

        a + b;
    }
}

void Minus(int a, int b, int N) {

    for (int i = 0; i < N; i++) {

        a - b;
    }

}

void Multiply(int a, int b, int N) {

    for (int i = 0; i < N; i++) {

        a * b;
    }
}

void Division(int a, int b, int N) {

    for (int i = 0; i < N; i++) {

        a / b;
    }
}

void Sum(double a, double b, int N) {

    for (int i = 0; i < N; i++) {

        a + b;
    }
}

void Minus(double a, double b, int N) {

    for (int i = 0; i < N; i++) {

        a - b;
    }
}

void Multiply(double a, double b, int N) {

    for (int i = 0; i < N; i++) {

        a * b;
    }
}

void Division(double a, double b, int N) {

    for (int i = 0; i < N; i++) {

        a / b;
    }
}

