package ru.ifmo.jjd.lessons.l37inner;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private List<Exam> exams = new ArrayList<>();

    public Student(String name) {
        this.name = name;
        System.out.println();
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void passInfo(){
        System.out.println("Максимальная отметка: " + Exam.MAX_MARK);
        exams.forEach(Exam::examInfo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //к классу высшего уровня нельзя применить модификатор static
    // область видимости вложенных классов - согласно модификаторам доступа
    public static class Exam {
        private static final int MAX_MARK = 5;
        private String examName;
        private int mark;

        public Exam(String examName, int mark) {
            this.examName = examName;
            this.mark = mark;
        }

        public String getExamName() {
            return examName;
        }

        public void setExamName(String examName) {
            this.examName = examName;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public void examInfo(){
            // вложенный класс, который может обращаться только к статическим полям и методам внешнего класса, в том числе и приватным
            // доступ к нестатическим полям внешнего класса может быть осуществлен только через ссылку на экземпляр внешнего объекта
            System.out.println(
                    // "Студент: "+ name + "; " +
                    "Экзамен " + examName + "; " +
                    "Оценка " + mark + ".");
        }
    }
}