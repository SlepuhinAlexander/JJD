package exercises.lesson05;

import static utils.ConsoleHelper.*;
import static utils.StringHelper.*;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Найдите самое длинное слово в предложении, при условии, что в предложении все слова разной длины.
 * */
public class Exercise02 {
    public static void main(String[] args) {
        String input = getUserInput();
        println();
        StringBuilder output = new StringBuilder();
        output.append("В предложении:\n\"").append(input)
                .append("\"\nСамое длинное слово - \"").append(findLongestWord(input)).append("\"");
        println(output);
    }

    private static String getUserInput() {
        print("""
                Программа ожидает от пользователя ввод предложения, в котором все слова разной длины.
                Введите "random", чтобы использовать случайно сгенерированное значение.
                Оставьте ввод пустым, чтобы использовать значение по умолчанию.
                """);
        String result;
        while (true) {
            result = readLine("Введите предложение: ").trim();
            if (result.length() == 0) {
                return "Hello darkness, my old friend.";
            } else if (result.replaceAll("\\W+", "").equalsIgnoreCase("random")) {
                do {
                    result = randomSentence(10);
                } while (!isValidSentence(result));
                return result;
            } else if (isValidSentence(result)) {
                return result;
            } else {
                println("Некорректный ввод. Попробуйте ещё раз. Все слова должны быть разной длины.");
            }
        }
    }

    private static boolean isValidSentence(String s) {
        String[] words = splitToWords(s);
        Arrays.sort(words, new StringLengthComparator());
        if (words.length >= 2) {
            for (int i = 0; i < words.length - 1; i++) {
                if (words[i].length() == words[i + 1].length()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String findLongestWord(String s) {
        String[] words = splitToWords(s);
        Arrays.sort(words, new StringLengthComparator());
        return words[words.length - 1];
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() == o2.length()) {    // Слова одинаковый длины дополнительно сортируем по алфавиту.
                return o1.compareTo(o2);        // Избыточно, но пусть будет для красоты.
            }
            return o1.length() - o2.length();
        }
    }

}
/*
 * output:
 * Программа ожидает от пользователя ввод предложения, в котором все слова разной длины.
 * Введите "random", чтобы использовать случайно сгенерированное значение.
 * Оставьте ввод пустым, чтобы использовать значение по умолчанию.
 * Введите предложение:
 *
 * В предложении:
 * "Hello darkness, my old friend."
 * Самое длинное слово - "darkness"
 * */
