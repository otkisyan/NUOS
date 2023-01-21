/*
* Написать программу на языке программирования С/С++ с использованием ассемблерных вставок, 
* выполняющий следующие действия: провести в цикле около одного миллиона операций добавления и замерить время выполнения, 
* затем сделать все то же с использованием ММХ регистров; поступить таким же образом с целочисленными операндами без использования ММХ регистров
*/

// https://www.codeproject.com/Articles/15971/Using-Inline-Assembly-in-C-C

#include <iostream>
#include <ctime>
#include <chrono>

using namespace std;

auto GetCurrentTime() {

    return chrono::high_resolution_clock::now();
}

auto GetDuration(chrono::high_resolution_clock::time_point end, chrono::high_resolution_clock::time_point start) {

    auto duration = chrono::duration_cast<chrono::milliseconds>(end - start);
    return duration.count();
}

void CPPSum(int a, int b, int N) {

    for (int i = 0; i < N; i++) {

        a + b;
    }

}

void CPPSumFloat(float a, float b, int N) {

    for (int i = 0; i < N; i++) {

        a + b;
    }

}

void MMXSum(int a, int b, int N) {

    for (int i = 0; i < N; i++) {

        __asm__ ( "paddd %%mm0, %%mm1;" : : "a" (a) , "b" (b) );

    }

}

int main() {

    const int N = 50000000;
    int a = 20;
    int b = 10;
    float c = 10.55;
    float d = 20.35;

    auto start = GetCurrentTime();
    CPPSum(a, b, N);
    auto end = GetCurrentTime();
    auto duration = GetDuration(end, start);
    cout << "Час витрачений на + | C++ (int): " << duration << " міллісeкунд" << endl;

    start = GetCurrentTime();
    CPPSumFloat(a, b, N);
    end = GetCurrentTime();
    duration = GetDuration(end, start);
    cout << "Час витрачений на + | C++ (float): " << duration << " міллісeкунд" << endl;

    start = GetCurrentTime();
    MMXSum(c, d, N);
    end = GetCurrentTime();
    duration = GetDuration(end, start);
    cout << "Час витрачений на + | MMX (int): " << duration << " міллісeкунд" << endl;
}
