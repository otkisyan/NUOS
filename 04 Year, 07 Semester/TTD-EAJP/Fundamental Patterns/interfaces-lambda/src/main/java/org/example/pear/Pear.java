package org.example.pear;

import org.example.color.Color;

public class Pear {

    private Double weight;
    private Color color;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Pear(Double weight, Color color) {
        this.weight = weight;
        this.color = color;
    }
}
