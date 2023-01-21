#ifndef REGISTRY_SHOWABLE_H
#define REGISTRY_SHOWABLE_H

class Showable {
    
public:

    Showable();

    virtual ~Showable();

    virtual void Show() const = 0;

};


#endif //REGISTRY_SHOWABLE_H
