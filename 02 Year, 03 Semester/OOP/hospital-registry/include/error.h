#ifndef REGISTRY_ERROR_H
#define REGISTRY_ERROR_H

#include "state.h"
#include <iostream>

class Error : public std::runtime_error {

public:

    Error(const char *msg, State state);

    Error(const std::string &msg, State state);

    State GetState() const;

private:

    State state_;
};


#endif //REGISTRY_ERROR_H
