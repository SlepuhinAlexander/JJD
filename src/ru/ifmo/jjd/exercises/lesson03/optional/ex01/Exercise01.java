package ru.ifmo.jjd.exercises.lesson03.optional.ex01;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise01 {
    public static void main(String[] args) {
        int[] someArr = new int[10];
        for (int i = 1; i <= someArr.length; i++) {
            someArr[i - 1] = i * 2;
        }
        println(reverseToString(someArr));
    }

    private static String reverseToString(int[] arr) {
        StringBuilder result = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            result.append(" ").append(arr[i]);
        }
        return result.toString();
    }
}
/*
 * output:
 *  20 18 16 14 12 10 8 6 4 2
 * */