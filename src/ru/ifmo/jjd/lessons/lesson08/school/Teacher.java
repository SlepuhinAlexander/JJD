package ru.ifmo.jjd.lessons.lesson08.school;

public class Teacher extends Learner implements CanTeach {
    @Override
    public void setAge(int age) {
        if (age > 22) {
            super.setAge(age);
        }
    }

    public Teacher(String name, String surname, int age) {
        super(name, surname, age);
    }

    public Teacher(String name, String surname, int age, String subject) {
        super(name, surname, age, subject);
    }

    @Override
    public void teach(CanStudy student) {
        System.out.println(this + " преподаёт " + getSubject());
        student.study();
    }
}
