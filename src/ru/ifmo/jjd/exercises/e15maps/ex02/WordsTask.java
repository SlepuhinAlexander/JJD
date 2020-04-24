package ru.ifmo.jjd.exercises.e15maps.ex02;

import java.util.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.normalizeWord;
import static ru.ifmo.jjd.utils.StringHelper.randomWord;

public class WordsTask {
    public static void main(String[] args) {
        List<String> words = Generator.generate(randomInt(20, 50));
        println("Given List:");
        println(words);
        println("\nContains the following words frequencies:");
        Map<String, Integer> map = frequencies(words);
        println(map, "'%k': %v time(s)");
    }

    public static Map<String, Integer> frequencies(List<String> words) {
        Map<String, Integer> result = new TreeMap<>();
        if (words == null) return result;
        String wordNorm;
        for (String word : words) {
            wordNorm = normalizeWord(word).toLowerCase();
            if (result.containsKey(wordNorm)) {
                result.replace(wordNorm, result.get(wordNorm) + 1);
            } else {
                result.put(wordNorm, 0);
            }
        }
        return result;
    }

    private static class Generator {
        private static final String[] WORDS;

        static {
            WORDS = new String[randomInt(5, 10)];
            outer:
            for (int i = 0; i < WORDS.length; i++) {
                String word = randomWord(randomInt(2, 5));
                for (int j = 0; j < i; j++) {
                    if (word.equals(WORDS[j])) { // failed to generate unique word; retry;
                        i--;
                        continue outer;
                    }
                }
                WORDS[i] = word;
            }
        }

        private static List<String> generate(int amount) {
            List<String> result = new ArrayList<>();
            if (amount <= 0) {
                return result;
            }
            for (int i = 0; i < amount; i++) {
                result.add(WORDS[randomInt(WORDS.length)]);
            }
            return result;
        }
    }
}
/*
 * Given List:
 * cuji
 * ymy
 * seq
 * mu
 * qe
 * mu
 * ymy
 * qe
 * seq
 * mu
 * cuji
 * qe
 * ymy
 * cuji
 * mu
 * mu
 * mu
 * qe
 * ewij
 * ewij
 * seq
 * cuji
 * qe
 * ewij
 * ewij
 * ymy
 * cuji
 * ymy
 * ewij
 * ymy
 * ewij
 * seq
 * cuji
 * seq
 * cuji
 * qe
 * qe
 * ymy
 * seq
 * ewij
 * qe
 * cuji
 * qe
 *
 * Contains the following words frequencies:
 * 'cuji': 7 time(s)
 * 'ewij': 6 time(s)
 * 'mu': 5 time(s)
 * 'qe': 8 time(s)
 * 'seq': 5 time(s)
 * 'ymy': 6 time(s)
 * */

