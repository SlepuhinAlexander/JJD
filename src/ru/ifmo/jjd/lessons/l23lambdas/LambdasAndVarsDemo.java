package ru.ifmo.jjd.lessons.l23lambdas;

import java.util.Objects;

@FunctionalInterface
interface Accumulator {
    int getInt(int someInt);
}

/*
 * Внутри лямбд можно обращаться к внешним переменным.
 * У этой возможности есть несколько особенностей.
 * */
public class LambdasAndVarsDemo {
    private static int staticInt = 12;

    public static void main(String[] args) {
        int localInt = 10;
        Data localObj = new Data("Textual data");
        /*
         * в качестве имени аргументов лямбды можно использовать всё что угодно, кроме уже использованных имён
         * переменных в текущем контексте.
         * */
        // Accumulator plusFive = localObj -> localObj;
        /*
         * на чтение локальные переменные доступны в любом виде.
         * на запись - нет, изменить значение локальной переменной в лямбде - нельзя.
         * */
        Accumulator plusFive = x -> {
            System.out.println("localInt = " + localInt);
            // localInt = 100; изменить нельзя.
            System.out.println("localObj = " + localObj);
            // localObj = new Data("New textual data"); изменить нельзя
            localObj.setText("New textual data"); // но обратиться к объекту и использовать его методы - можно
            staticInt = 1000; // статические переменные можно и писать и читать
            System.out.println("staticInt = " + staticInt);

            return localInt + 5;
        };
    }
}

class Data {
    private String text;

    public Data(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return text.equals(data.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}