package ru.ifmo.jjd.lessons.l16collections;

import java.util.Comparator;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private String name;
    private String surname;
    private int age;

    public Student(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return age == student.age &&
                name.equals(student.name) &&
                surname.equals(student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

    /*
     * Метод compare to должен сравнивать два объекта и возвращать:
     * - 0, если объекты равны
     * - отрицательное число, если вызывающий объект меньше аргумента
     * - положительное число, если вызывающий объект больше аргумента
     * */
    @Override
    public int compareTo(Student o) {
        return Integer.compare(getAge(), o.getAge());
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';


    }
}

// класс сравнения студентов по именам
/*
 * Класс компаратора должен реализовывать интерфейс Comparator.
 * Поскольку интерфейс Comparator - дженерик, необходимо указывать тип данных, который данный компаратор будет
 * сравнивать.
 * */
class StudentNameComparator implements Comparator<Student> {
    /*
     * Метод compare принимает два объекта и возвращает
     * 0, если объекты равны
     * отрицательное число, если первый объект меньше второго
     * положительное число, если первый объект больше второго
     * */

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

// класс сравнения студентов по возрастам
class StudentAgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}