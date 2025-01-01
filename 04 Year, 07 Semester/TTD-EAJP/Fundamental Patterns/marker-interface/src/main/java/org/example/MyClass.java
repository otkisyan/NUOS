package org.example;

public class MyClass implements MyMarkerInterface {
    private String name;

    public MyClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}