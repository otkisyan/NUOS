package org.example;

// Абстрактный суперкласс
abstract class Animal {
    // Общее свойство для всех животных
    protected String name;

    // Конструктор
    public Animal(String name) {
        this.name = name;
    }

    // Общий метод для всех животных
    public void eat() {
        System.out.println(name + " is eating.");
    }

    // Абстрактный метод для звука, который будет реализован в подклассах
    public abstract void makeSound();
}