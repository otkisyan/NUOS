#ifndef REGISTRY_RUNNABLE_H
#define REGISTRY_RUNNABLE_H

class Runnable {

public:

    Runnable();

    virtual ~Runnable();

    virtual void Run() = 0;
};


#endif //REGISTRY_RUNNABLE_H
