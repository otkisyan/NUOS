#include "../include/error.h"

using namespace std;

Error::Error(const char *msg, State state) : std::runtime_error(msg), state_(state){

}

Error::Error(const std::string &msg, State state) : runtime_error(msg), state_(state) {

}


State Error::GetState() const {

    return state_;
}
