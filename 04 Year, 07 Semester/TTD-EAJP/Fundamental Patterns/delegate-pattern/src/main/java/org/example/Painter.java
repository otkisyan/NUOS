package org.example;

public class Painter {
    Graphics graphics;

    public Painter(Graphics graphics) {
        this.graphics = graphics;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void draw(){
        graphics.draw();
    }
}
