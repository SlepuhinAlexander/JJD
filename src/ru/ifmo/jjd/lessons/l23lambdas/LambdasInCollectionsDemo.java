package ru.ifmo.jjd.lessons.l23lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class LambdasInCollectionsDemo {
    public static void main(String[] args) {

        Student student1 = new Student("Tom", 25, "Canada");
        Student student2 = new Student("Tim", 33, "Russia");
        Student student3 = new Student("Alex", 19, "China");
        Student student4 = new Student("Robert", 36, "Russia");
        Student student5 = new Student("Paul", 30, "China");

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        University university = new University(students);

        // создать предикаты:
        // студенты из России
        Predicate<Student> russian = student -> student.getCountry().trim().equalsIgnoreCase("Russia");
        System.out.println(university.getFilteredStudents(russian));
        // студенты старше 30
        Predicate<Student> over30 = student -> student.getAge() > 30;
        System.out.println(university.getFilteredStudents(over30));
        // студенты из России, старше 30
        System.out.println(university.getFilteredStudents(russian.and(over30)));

        System.out.println("---Comparing.comparing---");
        /*
         * Интерфейс Comparator<T> является функциональным интерфейсом, значит для его использования можно не создавать
         * класс с реализацией этого интерфейса, а воспользоваться лямбда-синтаксисом.
         * */

        //noinspection ComparatorCombinators
        Comparator<Student> byName0 = (std1, std2) -> std1.getName().compareTo(std2.getName());
        // Метод Comparing.comparing
        /*
         * Принимает на вход функцию
         * Возвращает компаратор
         * */
        Comparator<Student> byName = Comparator.comparing(Student::getName);
        /*
         * В метод comparing мы передали ссылку на метод getName
         * Ссылка на метод указывается через ::
         * По сути, здесь мы в метод comparing передали реализацию метода.
         * Это возможно, т.к. любой метод является реализацией интерфейса Function
         * */
        students.sort(byName);
        System.out.println(students);
        students.sort(Comparator.comparing(Student::getAge));
        System.out.println(students);
        students.sort(Comparator.comparing(Student::getAge).thenComparing(Student::getName));
        System.out.println(students);

        // Интерфейс Consumer и метод коллекций forEach
        /*
         * имеет метод
         *      void accept(T t)
         * принимает некоторый аргумент и что-то с ним делает
         * */
        students.forEach(System.out::println);
        /*
         * Метод forEach принимает интерфейс consumer
         * forEach перебирает все элементы коллекции и для каждой из них использует переданную реализацию
         * (в данном случае метод println)
         * */
        students.forEach(student -> student.setAge(student.getAge() + 10));
        System.out.println(students);

        // метод removeIf
        /*
         * Метод коллекций removeIf принимает на вход предикат
         * И если предикат выполнен (возвращает true), то удаляет элемент из коллекции
         * */
        students.removeIf(x -> "China".equalsIgnoreCase(x.getCountry().trim()));
        System.out.println(students);
    }
}

class University {
    private List<Student> studentList;

    public University(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getFilteredStudents(Predicate<Student> filter) {
        List<Student> students = new LinkedList<>();
        for (Student student : studentList) {
            if (filter.test(student)) students.add(student);
        }
        return students;
    }
}


class Student {
    private String name;
    private int age;
    private String country;

    public Student(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", country='" + country + '\'' +
               '}';
    }
}
