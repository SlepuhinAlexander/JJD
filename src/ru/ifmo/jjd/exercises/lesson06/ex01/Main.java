package ru.ifmo.jjd.exercises.lesson06.ex01;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(1.2);
        circle.setRadius(1.5);
        System.out.println("Длина окружности радиуса " + circle.getRadius() + " : " + circle.calcPerimeter());
        // Длина окружности радиуса 1.5 : 9.42477796076938
        System.out.println("Площадь круга радиуса " + circle.getRadius() + " : " + circle.calcArea());
        // Площадь круга радиуса 1.5 : 7.0685834705770345

        Rectangle rectangle = new Rectangle(1, 2);
        rectangle.setWidth(1.2);
        rectangle.setHeight(3.4);
        System.out.println("Периметр прямоугольника со сторонами " + rectangle.getWidth() + " и " +
                rectangle.getHeight() + " : " + rectangle.calcPerimeter());
        // Периметр прямоугольника со сторонами 1.2 и 3.4 : 9.2
        System.out.println("Площадь прямоугольника со сторонами " + rectangle.getWidth() + " и " +
                rectangle.getHeight() + " : " + rectangle.calcArea());
        // Площадь прямоугольника со сторонами 1.2 и 3.4 : 4.08

        Triangle triangle = new Triangle(2,3,4);
        triangle.setSideA(2.8);
        triangle.setSideB(3.7);
        triangle.setSideC(4.6);
        System.out.println("Периметр треугольника со сторонами " + triangle.getSideA() + ", " + triangle.getSideB() +
                " и " + triangle.getSideC() + " : " + triangle.calcPerimeter());
        // Периметр треугольника со сторонами 2.8, 3.7 и 4.6 : 11.1
        System.out.println("Площадь треугольника со сторонами " + triangle.getSideA() + ", " + triangle.getSideB() +
                " и " + triangle.getSideC() + " : " + triangle.calcArea());
        // Площадь треугольника со сторонами 2.8, 3.7 и 4.6 : 5.1791740412926845
    }
}
