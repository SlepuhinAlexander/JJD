package ru.ifmo.jjd.exercises.lesson03.optional.ex04;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ArraysHelper.*;
import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise04 {
    public static void main(String[] args) {
        int[] someArr = new int[11];
        fillRandomly(someArr, -1, 2);
        println(Arrays.toString(someArr));
        findMostCommonElement(someArr);
    }

    private static void findMostCommonElement(int[] arr) {
        int[] elements = new int[arr.length];
        int[] counts = new int[arr.length];
        for (int element : arr) {
            for (int j = 0; j < elements.length; j++) {
                if (element == elements[j] && counts[j] != 0) { // встретили имеющийся элемент.
                    counts[j]++;
                    break;
                } else if (counts[j] == 0) { // встретили новый элемент
                    elements[j] = element;
                    counts[j]++;
                    break;
                }
            }
        }
        int effectiveLength = 0;
        for (int count : counts) { // подсчитали полезные данные
            if (count == 0) break;
            effectiveLength++;
        }
        elements = trimToSize(elements, effectiveLength); // отрезали лишнее
        counts = trimToSize(counts, effectiveLength);

        if (counts.length < 1) {
            return;
        }
        int[] copy = counts.clone();
        Arrays.sort(copy);
        if (copy[copy.length - 1] == copy[copy.length - 2]) { // в массиве counts нет строго наибольшего элемента.
            return;
        }
        int maxCount = copy[copy.length - 1];

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == maxCount) {
                println("Чаще всего (" + maxCount + " раз(a)) в массиве встречается элемент " + elements[i]);
                return;
            }
        }
    }

    static int[] trimToSize(int[] arr, int size) {
        return (size < 0 || size >= arr.length) ? arr : Arrays.copyOf(arr, size);
    }
}
/*
 * output:
 * [1, -1, 0, 1, 1, 1, -1, -1, -1, -1, -1]
 * Чаще всего (6 раз(a)) в массиве встречается элемент -1
 * */
