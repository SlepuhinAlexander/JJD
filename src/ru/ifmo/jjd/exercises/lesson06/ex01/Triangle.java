package ru.ifmo.jjd.exercises.lesson06.ex01;

public class Triangle {
    private double sideA = 0.0;
    private double sideB = 0.0;
    private double sideC = 0.0;

    public Triangle(double sideA, double sideB, double sideC) {
        if (checkTriangle(sideA, sideB, sideC)) {
            this.sideA = sideA;
            this.sideB = sideB;
            this.sideC = sideC;
        }
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        if (checkTriangle(sideA, sideB, sideC)) {
            this.sideA = sideA;
        }
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        if (checkTriangle(sideA, sideB, sideC)) {
            this.sideB = sideB;
        }
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        if (checkTriangle(sideA, sideB, sideC)) {
            this.sideC = sideC;
        }
    }

    private boolean checkTriangle(double sideA, double sideB, double sideC) {
        return sideA > 0.0 && sideB > 0.0 && sideC > 0.0 &&
                sideA < sideB + sideC && sideB < sideC + sideA && sideC < sideA + sideB;
    }

    public double calcPerimeter() {
        return sideA + sideB + sideC;
    }

    public double calcArea() {
        double semiPerimeter = (sideA + sideB + sideC) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC));
    }
}
