package ru.ifmo.jjd.utils;

import java.util.regex.Pattern;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class StringHelper {
    private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u', 'y'};
    private static final char[] CONSONANTS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r',
            's', 't', 'v', 'w', 'x', 'z'};

    public static String uppercaseFirst(String s) {
        if (!isNullOrEmpty(s) && Character.isLowerCase(s.charAt(0))) {
            return s.substring(0, 1).toUpperCase() + s.substring(1);
        } else {
            return s;
        }
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
        boolean nextIsVowel = Math.random() < 0.5; // A word can start with vowel
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (nextIsVowel) {
                builder.append(VOWELS[randomInt(VOWELS.length)]);
                nextIsVowel = false;
            } else {
                builder.append(CONSONANTS[randomInt(CONSONANTS.length)]);
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
        if (!isNullOrBlank(s) && !isNullOrBlank(regex)) {
            try {
                Pattern.compile(regex);
                s = s.replaceAll(regex, "").replaceAll("\\s", " ").
                        replaceAll(" {2,}", " ").trim();
            } catch (Exception e) {
                s = "";
            }
        } else {
            s = "";
        }
        return s;
    }

    public static String normalize(String s) {
        return normalize(s, "[\\W&&\\S&&[^А-Яа-я\\-]]+");
    }

    public static String normalizeLatin(String s) {
        return normalize(s, "[\\W&&\\S&&[^\\-]]+");
    }

    public static String normalizeLatinWord(String s) {
        return normalize(s, "[^A-Za-z\\-]+");
    }

    public static String normalizeCyrillic(String s) {
        return normalize(s,"[^А-ЯЁа-яё\\-_\\d\\s]+");
    }

    public static String normalizeCyrillicWord(String s) {
        return normalize(s, "[^А-ЯЁа-яё\\-]+");
    }

    public static boolean startsWithIgnoreCase(String string, String prefix) {
        if (isNullOrEmpty(string) || isNullOrEmpty(prefix)) {
            return false;
        }
        if (prefix.length() > string.length()) {
            return false;
        }
        return string.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    public static boolean endsWithIgnoreCase(String string, String postfix) {
        if (isNullOrEmpty(string) || isNullOrEmpty(postfix)) {
            return false;
        }
        if (postfix.length() > string.length()) {
            return false;
        }
        return string.regionMatches(true, string.length() - postfix.length(),
                postfix, 0, postfix.length());
    }
}
