#ifndef REGISTRY_MENUITEM_H
#define REGISTRY_MENUITEM_H

#include "runnable.h"
#include <string>
#include <functional>

class MenuItem : Runnable {

public:

    MenuItem(const std::string &text, const std::function<void()> &function);

    MenuItem();

    ~MenuItem() override;

    void SetText(const std::string &text);

    void SetFunction(const std::function<void()> &function);

    std::string GetText() const;

    std::function<void()> GetFunction() const;

    void Run() override;

private:

    std::string text_;
    std::function<void()> function_;
};


#endif //REGISTRY_MENUITEM_H
