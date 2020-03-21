package ru.ifmo.jjd.lessons.lesson08.school;

abstract public class Learner extends Person {
    private static final String[] subjects = {"География", "История", "Литература", "Музыка", "Физика", "Математика",
            "Труд", "Биология", "Химия", "Информатика", "Астрономия"};
    private String subject = "";

    public Learner(String name, String surname, int age) {
        super(name, surname, age);
    }

    public Learner(String name, String surname, int age, String subject) {
        super(name, surname, age);
        setSubject(subject);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        for (String s : subjects) {
            if (s.equals(subject)) {
                this.subject = subject;
                break;
            }
        }
    }
}
