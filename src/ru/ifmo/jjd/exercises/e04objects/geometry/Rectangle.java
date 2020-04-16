package ru.ifmo.jjd.exercises.e04objects.geometry;

public class Rectangle {
    private double width = 0.0;
    private double height = 0.0;

    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width > 0.0) {
            this.width = width;
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        }
    }

    public double calcPerimeter() {
        return 2 * (width + height);
    }

    public double calcArea() {
        return width * height;
    }
}
