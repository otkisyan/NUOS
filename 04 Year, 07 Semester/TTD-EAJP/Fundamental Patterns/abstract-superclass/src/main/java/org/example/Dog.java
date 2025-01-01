package org.example;

// Подкласс Dog, наследующий абстрактный суперкласс Animal
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof!");
    }
}
