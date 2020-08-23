package ru.ifmo.jjd.exercises.e03strings.ex04;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.StringHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Exercise04 {
    public static void main(String[] args) {
        String input = getUserInput();
        println();
        StringBuilder output = new StringBuilder();
        output.append("Строка\n\"").append(input).append("\"\n");
        if (!isPalindrome(input)) {
            output.append("не ");
        }
        output.append("является палиндромом");
        println(output.toString());
    }

    private static String getUserInput() {
        print("Программа проверяет, является ли введённая строка палиндромом\n" +
              "Введите \"random\", чтобы использовать случайно сгенерированное значение.\n" +
              "Оставьте ввод пустым, чтобы использовать значение по умолчанию.\n");
        String result = readLine().trim();
        if (result.length() == 0) {
            result = "A Santa Lived As a Devil At NASA";
        } else if (result.replaceAll("\\W+", "").equalsIgnoreCase("random")) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                builder.append(randomWord(randomInt(5, 9)));
            }
            String half = builder.toString();
            builder.reverse().append(half);
            int index = builder.length() - 1;
            index -= randomInt(1, 9);
            while (index > 0) {
                builder.insert(index, " ");
                index -= randomInt(1, 9);
            }
            result = builder.toString();
        }
        return result;
    }

    private static boolean isPalindrome(String s) {
        s = s.replaceAll("[\\W&&[^А-Яа-я]]+", "").toLowerCase();
        return s.equals(new StringBuilder(s).reverse().toString());
    }
}
/*
 * output:
 * Программа проверяет, является ли введённая строка палиндромом
 * Введите "random", чтобы использовать случайно сгенерированное значение.
 * Оставьте ввод пустым, чтобы использовать значение по умолчанию.
 *
 *
 * Строка
 * "A Santa Lived As a Devil At NASA"
 * является палиндромом
 * */
