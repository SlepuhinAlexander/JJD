package ru.ifmo.jjd.exercises.e05school;

import java.util.Random;

public class Student extends Learner implements CanStudy {
    private int subjectLevel = 0;

    public Student(String name, String surname, int age) {
        super(name, surname, age);
    }

    public Student(String name, String surname, int age, String subject) {
        super(name, surname, age, subject);
    }

    public Student(String name, String surname, int age, String subject, int subjectLevel) {
        super(name, surname, age, subject);
        setSubjectLevel(subjectLevel);
    }

    public int getSubjectLevel() {
        return subjectLevel;
    }

    public void setSubjectLevel(int subjectLevel) {
        this.subjectLevel = Math.max(subjectLevel, 0);
    }

    @Override
    public void study() {
        Random r = new Random();
        System.out.println(this + " теперь лучше знает " + getSubject());
        setSubjectLevel(subjectLevel + r.nextInt(5));
    }
}
