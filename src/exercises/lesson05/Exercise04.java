package exercises.lesson05;

import static utils.ConsoleHelper.*;
import static utils.StringHelper.*;

import java.util.Random;

/*
 * Написать функцию, которая проверяет, является ли строка палиндромом.
 * Палиндром — это слово или фраза, которые одинаково читаются по буквам или по словам как слева направо,
 * так и справа налево.
 * */
public class Exercise04 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
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
        print("""
                Программа проверяет, является ли введённая строка палиндромом
                Введите "random", чтобы использовать случайно сгенерированное значение.
                Оставьте ввод пустым, чтобы использовать значение по умолчанию.
                """);
        String result = readLine().trim();
        if (result.length() == 0) {
            result = "A Santa Lived As a Devil At NASA";
        } else if (result.replaceAll("\\W+", "").equalsIgnoreCase("random")) {
            Random r = new Random();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                builder.append(randomWord(r.nextInt(5) + 5));
            }
            String half = builder.toString();
            builder.reverse().append(half);
            int index = builder.length() - 1;
            index -= r.nextInt(8) + 1;
            while (index > 0) {
                builder.insert(index, " ");
                index -= r.nextInt(8) + 1;
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
