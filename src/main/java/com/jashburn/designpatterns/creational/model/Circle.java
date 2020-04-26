package com.jashburn.designpatterns.creational.model;

import java.awt.Color;

public class Circle {

    private Color color;

    public Circle() {
        this(Color.WHITE);
    }

    public Circle(Color color) {
        this.color = color;
    }

    public boolean isColor(Color color) {
        return this.color.equals(color);
    }

    @Override
    public String toString() {
        return "Circle [color=" + color + "]";
    }
}
