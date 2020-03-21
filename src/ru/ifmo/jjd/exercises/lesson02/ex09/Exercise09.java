package ru.ifmo.jjd.exercises.lesson02.ex09;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise09 {
    public static void main(String[] args) {
        generateSequence(2, 20, 2);
    }

    static void generateSequence(int start, int quantity, int factor) {
        for (int i = 0, n = start; i < quantity; i++, n *= factor) {
            print(n + " ");
        }
    }
}
/*
 * output:
 * 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 16384 32768 65536 131072 262144 524288 1048576
 * */
