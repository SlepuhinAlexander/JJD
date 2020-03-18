package ru.ifmo.jjd.exercises.lesson06.ex02;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat(120, "Downing Street", 12);
        Cat cat2 = new Cat(150, "King Street", 25);
        cat1.setName("Барсик");
        cat2.setName("Пушок");
        cat1.fight(cat2);
        System.out.println(cat1.getHealth() + " : " + cat2.getHealth());
        cat2.fight(cat1);
        System.out.println(cat1.getHealth() + " : " + cat2.getHealth());
        while (cat1.getHealth() > 0 && cat2.getHealth() > 0) {
            cat1.fight(cat2);
            cat2.fight(cat1);
        }
        if (cat1.getHealth() > 0) {
            System.out.println(cat1.getName());
        } else {
            System.out.println(cat2.getName());
        }
    }
}
