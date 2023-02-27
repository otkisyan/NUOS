#include "../include/idgenerator.h"
#include <ctime>

using namespace std;

string IdGenerator::Generate() {

    srand(time(nullptr));
    string letters = "QWERTYUIOPASDFGHJKLZXCVBNM"; // 26 букв

    string result;
    int a;

    for (int i = 0; i < 10; i++) {

        a = rand() % letters.length();
        result += letters[a];
    }

    int b = rand();
    result += to_string(b) + '-';

    for (int i = 0; i < 10; i++) {

        a = rand() % letters.length();
        result += letters[a];
    }

    int c = rand();
    result += to_string(c);

    return result;

}