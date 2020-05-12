package ru.ifmo.jjd.utils;

import java.util.regex.Pattern;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class StringHelper {
    private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u', 'y'};
    private static final char[] CONSONANTS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r',
            's', 't', 'v', 'w', 'x', 'z'};

    public static String uppercaseFirst(String s) {
        return (isNullOrEmpty(s) || Character.isLowerCase(s.charAt(0))) ?
                s :
                s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * Provides a randomly selected letter sequence of given length, alternating vowels and consonants.
     * Assuming it is a random word.
     * Uses latin alphabet. Lowercase.
     *
     * @param length sets the length of generated word. Must be positive
     * @throws IllegalArgumentException if provided length is not-positive
     */
    public static String randomWord(int length) {
        if (length < 1) throw new IllegalArgumentException("length must be positive");
        boolean nextIsVowel = Math.random() < 0.5; // A word can start with vowel
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(nextIsVowel ? VOWELS[randomInt(VOWELS.length)] : CONSONANTS[randomInt(CONSONANTS.length)]);
            nextIsVowel = !nextIsVowel;
        }
        return builder.toString();
    }

    /**
     * Provides a sentence composed of randomly generated words having from 1 to 10 symbols.
     *
     * @param length sets the amount of words in sentence. Must be positive
     * @throws IllegalArgumentException if provided length is not-positive
     */
    public static String randomSentence(int length) {
        if (length < 1) throw new IllegalArgumentException("length must be positive");
        StringBuilder builder = new StringBuilder();
        builder.append(uppercaseFirst(randomWord(randomInt(1, 11))));
        for (int i = 1; i < length; i++) {
            builder.append(" ");
            builder.append(randomWord(randomInt(1, 11)));
        }
        return builder.toString();
    }

    /**
     * Splits the given string to array of strings containing latin, cyrillic and digit char sequences.
     */
    public static String[] splitToWords(String s) {
        return s.split("[\\W&&[^А-Яа-я-]]+");
    }

    public static boolean isNullOrEmpty(String s) {
        return (s == null) || s.isEmpty();
    }

    public static boolean isNullOrBlank(String s) {
        return (s == null) || s.isBlank();
    }

    /**
     * Cuts out all matches for given regex in given string. Additionally removes all extra whitespaces.
     * Returns empty string in case of invalid arguments.
     *
     * @param s     A string to normalize
     * @param regex A regex for replacement to empty string
     * @return The given string s with all regex matches and extra whitespaces being cut out. Returns empty string if
     * any param is null or blank.
     */
    public static String normalize(String s, String regex) {
        if (isNullOrBlank(s) || isNullOrBlank(regex)) return "";
        try {
            Pattern.compile(regex);
            return s.replaceAll(regex, "").replaceAll("\\s", " ").
                    replaceAll(" {2,}", " ").trim();
        } catch (Exception e) {
            return "";
        }
    }

    public static String normalize(String s) {
        return normalize(s, "[\\W&&\\S&&[^А-Яа-я\\-]]+");
    }

    public static String normalizeLatin(String s) {
        return normalize(s, "[\\W&&\\S&&[^\\-]]+");
    }

    public static String normalizeCyrillic(String s) {
        return normalize(s, "[^А-ЯЁа-яё\\-_\\d\\s]+");
    }

    public static String normalizeWord(String s) {
        return normalize(s, "[^A-Za-zА-ЯЁа-яё\\-]+");
    }

    public static String normalizeLatinWord(String s) {
        return normalize(s, "[^A-Za-z\\-]+");
    }

    public static String normalizeCyrillicWord(String s) {
        return normalize(s, "[^А-ЯЁа-яё\\-]+");
    }

    public static boolean startsWithIgnoreCase(String string, String prefix) {
        return !isNullOrEmpty(string) &&
               !isNullOrEmpty(prefix) &&
               prefix.length() <= string.length() &&
               string.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    public static boolean endsWithIgnoreCase(String string, String postfix) {
        return !isNullOrEmpty(string) &&
               !isNullOrEmpty(postfix) &&
               postfix.length() <= string.length() &&
               string.regionMatches(true, string.length() - postfix.length(), postfix, 0,
                       postfix.length());
    }
}
