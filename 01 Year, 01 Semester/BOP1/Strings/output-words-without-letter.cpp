// Вывод слов из строки в которых нет буквы 'e'

#include <iostream>
#include <string>
#include <windows.h>
#include <stdlib.h>
#include <sstream>
using namespace std;
 
int main()
{
    string s = "We study C++ programming language first semester.", word;
    
    s.pop_back();
    stringstream in (s);

    
    while (in >> word)
    {
        if (word.find('e') == std::string::npos)
        cout << word << endl;            
    }

    //in - это поток строк, и использование оператора >> "анализирует" строку s 
    //на основе разделителя, который представляет собой пробел. 
    //Каждый раз, когда цикл while выполняет итерацию, он помещает следующий набор символов, разделенных пробелом, в слово
    
 /* - 2 версия
 std::string s = "We study C++ programming language first semester.";
    s += ' '; //safeguard;

    std::string word = "";
    for (char c : s)
    {
        if (c == ' ') //break of word
        {
            if (word != "") //word not empty
            {
                bool incE = false;
                for (char c2 : word) //check for e
                {
                    if (c2 == 'e') {incE = true; break;}
                }

                if (!incE)
                {
                    std::cout << word << " ";
                }

                word = "";
            }
        }
        else {word = word + c;}
    }

*/

system("pause");
return 0;
}
