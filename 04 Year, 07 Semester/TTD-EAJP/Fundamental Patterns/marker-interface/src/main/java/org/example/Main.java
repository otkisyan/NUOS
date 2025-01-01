package org.example;

public class Main {
    public static void main(String[] args) {
        MyClass myObject = new MyClass("Example");

        // Проверка, реализует ли объект интерфейс
        if (myObject instanceof MyMarkerInterface) {
            System.out.println("myObject реализует MyMarkerInterface.");
        } else {
            System.out.println("myObject НЕ реализует MyMarkerInterface.");
        }
    }
}