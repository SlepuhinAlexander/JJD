package ru.ifmo.jjd.exercises.e03strings.ex01;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.*;
import static ru.ifmo.jjd.utils.StringHelper.*;

public class Exercise01 {
    public static void main(String[] args) {
        String[] words = getUserInput();
        println(words[0] + " + " + words[1] + " = " + mergeWords(words[0], words[1]));
    }

    private static String[] getUserInput() {
        String[] result;
        print("Программа ожидает от пользователя ввод двух слов, состоящих из чётного числа букв.\n" +
              "Лишние слова будут проигнорированы.\n" +
              "Введите \"random\", чтобы использовать случайно сгенерированные значения.\n" +
              "Оставьте ввод пустым, чтобы использовать значения по умолчанию.\n");
        while (true) {
            String input = readLine("Введите 2 слова: ").trim();
            if (input.length() == 0) {
                result = new String[]{"PenPineapple", "ApplePen"};
                break;
            } else if (input.replaceAll("\\W+", "").equalsIgnoreCase("random")) {
                result = new String[]{randomWord(2 * randomInt(2, 7)),
                        randomWord(2 * randomInt(2, 7))};
                break;
            } else {
                result = splitToWords(input);
                if (result.length < 2) {
                    println("Вы ввели недостаточно слов.");
                    continue;
                } else if (result.length > 2) {
                    result = Arrays.copyOf(result, 2);
                }
                if (result[0].length() % 2 == 0 && result[1].length() % 2 == 0) {
                    break;
                } else {
                    println("Слова должны состоять из чётного количества букв.");
                }
            }
        }
        return result;
    }

    /**
     * Checks if two given strings have even letters and returns the first half of one merged with the second half of
     * two. Returns null when provided arguments are unexpected.
     */
    private static String mergeWords(String one, String two) {
        if (one == null || two == null) {
            return null;
        } else if (one.length() % 2 != 0 || two.length() % 2 != 0) {
            return null;
        } else {
            return one.substring(0, one.length() / 2) + two.substring(two.length() / 2);
        }
    }

    private static String uppercaseFirst(String s) {
        return s;
    }
}
/*
 * output:
 * Программа ожидает от пользователя ввод двух слов, состоящих из чётного числа букв.
 * Лишние слова будут проигнорированы.
 * Введите "random", чтобы использовать случайно сгенерированные значения.
 * Оставьте ввод пустым, чтобы использовать значения по умолчанию.
 * Введите 2 слова:
 * PenPineapple + ApplePen = PenPinePen
 * */
