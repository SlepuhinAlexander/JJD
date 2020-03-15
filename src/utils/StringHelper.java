package utils;

import java.util.Random;

public class StringHelper {
    private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u', 'y'};
    private static final char[] CONSONANTS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r',
            's', 't', 'v', 'w', 'x', 'z'};

    public static String uppercaseFirst(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        return s;
    }

    /**
     * Provides a randomly selected letter sequence of given length, alternating vowels and consonants.
     * Assuming it is a random word.
     * Uses latin alphabet. Lowercase.
     *
     * @param length sets the length of generated word. Cannot be less than 1.
     */
    public static String randomWord(int length) {
        length = Math.max(length, 1);
        Random r = new Random();
        boolean nextIsVowel = Math.random() < 0.5; // A word can start with vowel
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (nextIsVowel) {
                builder.append(VOWELS[r.nextInt(VOWELS.length)]);
                nextIsVowel = false;
            } else {
                builder.append(CONSONANTS[r.nextInt(CONSONANTS.length)]);
                nextIsVowel = true;
            }
        }
        return builder.toString();
    }

    /**
     * Provides a sentence composed of randomly generated words having from 1 to 10 symbols.
     *
     * @param length sets the amount of words in sentence. Cannot be less than 2.
     */
    public static String randomSentence(int length) {
        length = Math.max(length, 2);
        Random r = new Random();
        StringBuilder builder = new StringBuilder();
        builder.append(uppercaseFirst(randomWord(10) + 1));
        for (int i = 1; i < length; i++) {
            builder.append(" ");
            builder.append(randomWord(r.nextInt(10) + 1));
        }
        return builder.toString();
    }

    /**
     * Splits the given string to array of strings containing latin, cyrillic and digit char sequences.
     */
    public static String[] splitToWords(String s) {
        return s.split("[\\W&&[^А-Яа-я-]]+");
    }
}
