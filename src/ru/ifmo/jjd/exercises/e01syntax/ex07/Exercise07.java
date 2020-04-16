package ru.ifmo.jjd.exercises.e01syntax.ex07;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise07 {
    public static void main(String[] args) {
        generateSequence(1, 55, 2);
    }

    static void generateSequence(int start, int quantity, int step) {
        for (int i = 0, n = start; i < quantity; i++, n += step) {
            print(n + " ");
        }
    }
}
/*
 * output:
 * 1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45 47 49 51 53 55 57 59 61 63 65 67 69 71 73 75 77 79
 * 81 83 85 87 89 91 93 95 97 99 101 103 105 107 109
 * */
