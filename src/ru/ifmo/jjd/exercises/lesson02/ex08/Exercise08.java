package ru.ifmo.jjd.exercises.lesson02.ex08;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise08 {
    public static void main(String[] args) {
        generateCountdown(90, -1, 5);
    }

    static void generateCountdown(int start, int finish, int step) {
        while (start > finish) {
            print(start + " ");
            start -= step;
        }
    }
}
/*
 * output:
 * 90 85 80 75 70 65 60 55 50 45 40 35 30 25 20 15 10 5 0
 * */
