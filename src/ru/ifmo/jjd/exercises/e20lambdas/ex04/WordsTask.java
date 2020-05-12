package ru.ifmo.jjd.exercises.e20lambdas.ex04;

import java.util.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.randomWord;

public class WordsTask {
    public static void main(String[] args) {
        List<String> words = Generator.generate(randomInt(20, 50), randomInt(2, 5));
        println("Given List:");
        println(words);
        println("\nContains the following words frequencies:");
        println(frequencies(words), "'%k': %v time(s)");
    }

    public static Map<String, Integer> frequencies(List<String> words) {
        if (words == null) throw new NullPointerException("Words argument cannot be null");
        Map<String, Integer> result = new TreeMap<>();
        words.forEach(word -> result.merge(word, 1, Integer::sum));
        return result;
    }

    private static class Generator {
        private static List<String> generate(int amount, int dictionarySize) {
            Set<String> words = new HashSet<>();
            while (words.size() < dictionarySize) words.add(randomWord(randomInt(2, 5)));
            String[] dictionary = new String[dictionarySize];
            words.toArray(dictionary);
            List<String> result = new ArrayList<>();
            for (int i = 0; i < amount; i++) result.add(dictionary[randomInt(dictionarySize)]);
            return result;
        }
    }
}
/*
 * Given List:
 * {usu
 *  agi
 *  konu
 *  konu
 *  agi
 *  zi
 *  zi
 *  usu
 *  usu
 *  zi
 *  usu
 *  agi
 *  usu
 *  zi
 *  agi
 *  konu
 *  konu
 *  konu
 *  konu
 *  zi
 *  usu
 *  zi
 *  zi
 *  konu
 *  konu
 *  agi}
 *
 * Contains the following words frequencies:
 * {'agi': 5 time(s)
 *  'konu': 8 time(s)
 *  'usu': 6 time(s)
 *  'zi': 7 time(s)}
 * */
