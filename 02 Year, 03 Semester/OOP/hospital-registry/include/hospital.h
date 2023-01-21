#ifndef REGISTRY_HOSPITAL_H
#define REGISTRY_HOSPITAL_H

#include "registry.h"
#include "menu.h"
#include "file.h"

class Hospital : Runnable {

public:

    Hospital();

    ~Hospital() override;

    void Run() override;

private:

    Registry registry_;
    Menu menu_;
    File file_;

};

#endif //REGISTRY_HOSPITAL_H
