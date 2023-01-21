#ifndef REGISTRY_MENU_H
#define REGISTRY_MENU_H

#include "menuItem.h"
#include "showable.h"
#include <vector>

class Menu : Showable, Runnable {

public:

    Menu(std::initializer_list<MenuItem> menuItems);

    explicit Menu(const std::vector<MenuItem> &menuItems);

    Menu();

    ~Menu() override;

    void SetRunning(bool running);

    void SetMenuItems(const std::vector<MenuItem> &menuItems);

    bool IsRunning() const;

    std::vector<MenuItem> GetMenuItems() const;

    std::string ToString() const;

    void Show() const override;

    void Run() override;

private:

    std::vector<MenuItem> menuItems_;
    bool running_;

    int GetInput();

    void Perform(int select);

    bool Quit();

};


#endif //REGISTRY_MENU_H
