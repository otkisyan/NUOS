package org.example;

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Whiskers");

        dog.eat();         // Buddy is eating.
        dog.makeSound();   // Buddy says: Woof!

        cat.eat();         // Whiskers is eating.
        cat.makeSound();   // Whiskers says: Meow!
    }
}