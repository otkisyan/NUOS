#include "../include/menuItem.h"
using namespace std;

MenuItem::MenuItem(const string &text, const std::function<void()> &function) : text_(text), function_(function) {

}

MenuItem::MenuItem() : text_(""), function_(nullptr) {

}


MenuItem::~MenuItem() {

}


void MenuItem::SetText(const string &text) {

    text_ = text;
}

void MenuItem::SetFunction(const std::function<void()> &function) {

    function_ = function;
}

string MenuItem::GetText() const {

    return text_;
}

std::function<void()> MenuItem::GetFunction() const {

    return function_;
}

void MenuItem::Run() {

    function_();
}
