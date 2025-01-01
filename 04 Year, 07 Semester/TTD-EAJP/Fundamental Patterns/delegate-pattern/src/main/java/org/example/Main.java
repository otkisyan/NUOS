package org.example;

public class Main {
    public static void main(String[] args) {
        Painter painter = new Painter(new Square());
        painter.draw(); // Painting Square

        painter.setGraphics(new Triangle());
        painter.draw(); // Painting Triangle

        boolean someCondition = true;

        if (someCondition) {
            painter.setGraphics(new Square());
        } else {
            painter.setGraphics(new Triangle());
        }
        painter.draw();

    }
}