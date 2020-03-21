package ru.ifmo.jjd.exercises.lesson05.ex03;

import java.util.Random;
import java.util.regex.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.StringHelper.*;

public class Exercise03 {
    public static void main(String[] args) {
        String[] input = getUserInput();
        println();
        StringBuilder output = new StringBuilder();
        output.append("В строке\n\"").append(input[1])
                .append("\"\nстрока\n\"").append(input[0]).append("\"\n")
                .append("встречается ")
//                .append(countOccurrences(input[0], input[1]))   // решение через startsWith(query, offset)
                .append(countMatches(input[0], input[1]))       // решение через API регулярных выражений
                .append(" раз(а).");                            // в любом случае поиск регистроЗАВИСИМЫЙ
        println(output);
    }

    private static String[] getUserInput() {
        String[] result = new String[2];
        print("""
                Программа ожидает от пользователя ввод двух строк и ищет количество вхождений первой строки во вторую.
                Введите "random", чтобы использовать случайно сгенерированные значения.
                Оставьте ввод пустым, чтобы использовать значения по умолчанию.
                """);
        result[0] = readLine("Ведите первую строку: ").trim();
        if (result[0].length() == 0) {
            result[0] = "рек";
            result[1] = "Ехал грека через реку. Видит Грека в реке рак.";
        } else if (result[0].replaceAll("\\W+", "").equalsIgnoreCase("random")) {
            Random r = new Random();
            result[0] = randomWord(r.nextInt(5) + 2);
            StringBuilder builder = new StringBuilder(result[0]);
            for (int i = 0; i < r.nextInt(5) + 3; i++) {
                builder.append(" ");
                if (Math.random() < 0.5) {
                    builder.append(randomWord(r.nextInt(i + 2)));
                }
                builder.append(result[0]);
                if (Math.random() < 0.5) {
                    builder.append(randomWord(r.nextInt(i + 2)));
                }
                if (Math.random() < 0.8) {
                    builder.append(" ").append(randomWord(r.nextInt(5) + 2));
                }
            }
            result[1] = builder.toString();
        } else {
            do {
                result[1] = readLine("Введите вторую строку: ");
            } while (result[1].length() == 0);
        }
        return result;
    }

    private static int countOccurrences(String query, String text) {
        int count = 0;
        int offset = 0;
        while (offset <= text.length() - query.length()) {
            if (text.startsWith(query, offset)) {
                offset += query.length();
                count++;
            } else {
                offset++;
            }
        }
        return count;
    }

    // альтернативные решение с использованием API регулярных выражений
    private static int countMatches(String regex, String text) {
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
/*
 * output:
 * Программа ожидает от пользователя ввод двух строк и ищет количество вхождений первой строки во вторую.
 * Введите "random", чтобы использовать случайно сгенерированные значения.
 * Оставьте ввод пустым, чтобы использовать значения по умолчанию.
 * Ведите первую строку:
 *
 * В строке
 * "Ехал грека через реку. Видит Грека в реке рак."
 * строка
 * "рек"
 * встречается 4 раз(а).
 * */
