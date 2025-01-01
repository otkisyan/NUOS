package org.example;

// Подкласс Cat, наследующий абстрактный суперкласс Animal
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow!");
    }
}