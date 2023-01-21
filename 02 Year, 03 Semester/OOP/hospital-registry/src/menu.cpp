#include "../include/menu.h"
#include <iostream>

using namespace std;

// У класса vector есть конструктор, который принимает initializer_list.
// Так данные попадают из конструктора в член класса.
Menu::Menu(initializer_list<MenuItem> menuItems) : menuItems_(menuItems), running_(false) {

}

Menu::Menu(const vector<MenuItem> &menuItems) : menuItems_(menuItems) {

}

Menu::Menu() : running_(false), menuItems_() {

}


Menu::~Menu() {

};


void Menu::SetRunning(bool running) {

    running_ = running;
}


void Menu::SetMenuItems(const vector<MenuItem> &menuItems) {

    menuItems_ = menuItems;
}

bool Menu::IsRunning() const {

    return running_;
}

std::vector<MenuItem> Menu::GetMenuItems() const {

    return menuItems_;
}

string Menu::ToString() const {

    string items;

    for (int i = 0; i < menuItems_.size(); i++) {

        items += to_string(i) + " - " + menuItems_[i].GetText() + '\n';

    }

    return items;
}

void Menu::Show() const {

    cout << ToString();
}

int Menu::GetInput() {

    cout << "Оберіть функцію: " << endl;
    cout << "➡ ";

    int select;
    cin >> select;
    while (cin.get() != '\n');

    while (select > menuItems_.size() - 1 || select < 0) {

        cout << "Некоректний номер функції!" << endl;
        cout << "Оберіть функцію: " << endl;
        cout << "➡ ";
        cin >> select;
        while (cin.get() != '\n');
    }

    return select;
}


void Menu::Perform(int select) {

    menuItems_[select].Run();
}

bool Menu::Quit() {

    cout << "Бажаєте продовжити роботу? 1 - так, 0 - ні" << endl;
    cout << "➡ ";

    int select;
    cin >> select;
    while (cin.get() != '\n');

    while (select < 0 || select > 1) {

        cout << "Некоректний ввід данних!" << endl;
        cout << "Бажаєте продовжити роботу? 1 - так, 0 - ні" << endl;
        cout << "➡ ";
        cin >> select;
        while (cin.get() != '\n');
    }

    if (select == 0) {

        return false;

    } else {

        return true;
    }
}

void Menu::Run() {

    running_ = true;
    int select;

    do {

        Show();
        select = GetInput();
        Perform(select);
        running_ = Quit();

        if (!running_) {

            break;
        }


    } while (running_);
}


