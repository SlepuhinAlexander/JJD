package ru.ifmo.jjd.exercises.lesson05.ex06;

import java.util.Arrays;
import java.util.Random;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.StringHelper.*;

public class Exercise06 {
    public static void main(String[] args) {
        String input = getUserInput();
        println();
        println("Исходная строка: \"" + input + "\"");
        String output = orderWords(input);
        println("Упорядоченная строка: \"" + output + "\"");
    }

    private static String getUserInput() {
        print("""
                Программа ожидает ввод списка названий городов через пробел.
                Введите "random", чтобы использовать случайно сгенерированные значения.
                Оставьте ввод пустым, чтобы использовать значения по умолчанию.
                """);
        String result = readLine().trim();
        if (result.length() == 0) {
            result = "Москва Санкт-Петербург Новосибирск Екатеринбург Казань Челябинск Омск Самара Ростов-на-Дону";
        } else if (result.replaceAll("\\W+", "").equalsIgnoreCase("random")) {
            Random r = new Random();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                builder.append(uppercaseFirst(randomWord(r.nextInt(5) + 5))).append(" ");
            }
            result = builder.toString().trim();
        } else {
            String[] words = splitToWords(result);              // названия городов должны быть с большой буквы
            for (int i = 0; i < words.length; i++) {            // для красоты
                words[i] = uppercaseFirst(words[i]);
            }
            StringBuilder builder = new StringBuilder();
            for (String word : words) {
                builder.append(word).append(" ");
            }
            result = builder.toString().trim();
        }
        return result;
    }

    private static String orderWords(String s) {
        String[] words = splitToWords(s);
        Arrays.sort(words);
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word).append(" ");
        }
        return result.toString().trim();
    }
}
/*
 * output:
 * Программа ожидает ввод списка названий городов через пробел.
 * Введите "random", чтобы использовать случайно сгенерированные значения.
 * Оставьте ввод пустым, чтобы использовать значения по умолчанию.
 *
 *
 * Исходная строка: "Москва Санкт-Петербург Новосибирск Екатеринбург Казань Челябинск Омск Самара Ростов-на-Дону"
 * Упорядоченная строка: "Екатеринбург Казань Москва Новосибирск Омск Ростов-на-Дону Самара Санкт-Петербург Челябинск"
 * */
