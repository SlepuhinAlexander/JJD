package ru.ifmo.jjd.exercises.lesson06.geometry;

public class Circle {
    private double radius = 0.0;

    public Circle(double radius) {
        setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0.0) {
            this.radius = radius;
        }
    }

    public double calcPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double calcArea() {
        return Math.PI * radius * radius;
    }
}
