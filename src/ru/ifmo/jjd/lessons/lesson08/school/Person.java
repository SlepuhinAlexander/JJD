package ru.ifmo.jjd.lessons.lesson08.school;

abstract public class Person {
    private String name = "Иван";
    private String surname = "Иванов";
    private int age = 7;

    public Person() {
    }

    public Person(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() > 1) {
            this.name = name;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname != null && surname.length() > 1) {
            this.surname = surname;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 7) {
            this.age = age;
        }
    }
}
