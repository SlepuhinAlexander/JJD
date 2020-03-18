package ru.ifmo.jjd.exercises.lesson06.ex01;

public class Main {
    public static void main(String[] args) {
        Circle circle1 = new Circle();
        circle1.setRadius(1.2);
        System.out.println("Длина окружности: " + circle1.getPerimeter()); // Длина окружности: 7.5398223686155035
        System.out.println("Площадь: " + circle1.getArea()); // Площадь: 4.523893421169302
    }
}
