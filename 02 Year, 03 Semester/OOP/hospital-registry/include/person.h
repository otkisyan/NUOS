#ifndef REGISTRY_PERSON_H
#define REGISTRY_PERSON_H

#include <string>
#include "showable.h"

class Person : Showable {

public:

    Person(const std::string &name, const std::string &surname);

    Person();

    ~Person() override;

    void SetName(const std::string &name);

    void SetSurname(const std::string &surname);

    std::string GetName() const;

    std::string GetSurname() const;

    virtual std::string ToString() const;

    void Show() const override;

protected:

    std::string name_;
    std::string surname_;
};


#endif //REGISTRY_PERSON_H
