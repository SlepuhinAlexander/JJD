package ru.ifmo.jjd.lessons.lesson08.school;

public class Director extends Person {

    private Director(Person person) {
        super(person.name, person.surname, person.age);
    }

    public static Director appointDirector(Person person) {
        Director director = null;
        if (person != null) {
            director = new Director(person);
        }
        return director;
    }

    public void startClasses() {
        System.out.println("Директор " + getName() + " " + getSurname() + " объявляет начало занятий");
    }

    public void finishClasses() {
        System.out.println("Директор " + getName() + " " + getSurname() + " объявляет окончание занятий");
    }
}
