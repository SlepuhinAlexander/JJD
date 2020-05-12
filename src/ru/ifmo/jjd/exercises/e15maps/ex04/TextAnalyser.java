package ru.ifmo.jjd.exercises.e15maps.ex04;

import ru.ifmo.jjd.utils.Pair;

import java.util.*;

import static ru.ifmo.jjd.utils.StringHelper.isNullOrBlank;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class TextAnalyser {
    private final String normalized;
    private final TreeMap<String, Integer> words = new TreeMap<>();
    private final TreeMap<Character, Integer> symbols = new TreeMap<>();
    private final List<Pair<Integer, String>> popularWords = new ArrayList<>();
    private final List<Pair<Integer, Character>> popularSymbols = new ArrayList<>();
    private int wordsCount;
    private int symbolsCount;

    private TextAnalyser(String text) {
        if (isNullOrBlank(text)) throw new IllegalArgumentException("Text cannot be null or blank");
        String norm = normalize(text, "[\\S&&[^A-Za-zА-ЯЁа-яё]]+").toLowerCase();
        if (norm.isBlank()) throw new IllegalArgumentException("Cannot analyse a text without words");
        normalized = norm;
        words.clear();
        symbols.clear();
        popularWords.clear();
        popularSymbols.clear();
        wordsCount = 0;
        symbolsCount = 0;
    }

    public static TextAnalyser forText(String text) {
        return new TextAnalyser(text);
    }

    private void tokenize() {
        if (!words.isEmpty()) return;
        String[] wordArray = normalized.split(" +");
        for (String w : wordArray) {
            if (words.containsKey(w)) {
                words.replace(w, words.get(w) + 1);
            } else {
                words.put(w, 1);
            }
        }
        wordsCount = wordArray.length;
    }

    private void symbolize() {
        if (!symbols.isEmpty()) return;
        String joined = normalized.replaceAll("\\s+", "");
        char[] chars = joined.toCharArray();
        for (char c : chars) {
            if (symbols.containsKey(c)) {
                symbols.replace(c, symbols.get(c) + 1);
            } else {
                symbols.put(c, 1);
            }
        }
        symbolsCount = chars.length;
    }

    public Map<String, Integer> words() {
        tokenize();
        return words;
    }

    public Map<Character, Integer> symbols() {
        symbolize();
        return symbols;
    }

    public int wordCount(String word) {
        tokenize();
        if (isNullOrBlank(word)) return 0;
        Integer result = words.getOrDefault(word.toLowerCase(), 0);
        return result == null ? 0 : result;
    }

    public int symbolCount(char c) {
        symbolize();
        Integer result = symbols.getOrDefault(Character.toLowerCase(c), 0);
        return result == null ? 0 : result;
    }

    public double wordPopularity(String word) {
        tokenize();
        if (isNullOrBlank(word)) return 0d;
        Integer count = words.getOrDefault(word.toLowerCase(), 0);
        return count == null ? 0 : (int) (10000d * count / wordsCount) / 100d;
    }

    public double symbolPopularity(char c) {
        symbolize();
        Integer count = symbols.getOrDefault(Character.toLowerCase(c), 0);
        return count == null ? 0 : (int) (10000d * count / normalized.length()) / 100d;
    }

    private void fillPopularWords() {
        if (!popularWords.isEmpty()) return;
        tokenize();
        Set<Map.Entry<String, Integer>> entries = words.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            popularWords.add(new Pair<>(entry.getValue(), entry.getKey()));
        }
        popularWords.sort(new PopularityComparator<>(new StringComparator()));
    }

    private void fillPopularSymbols() {
        if (!popularSymbols.isEmpty()) return;
        symbolize();
        Set<Map.Entry<Character, Integer>> entries = symbols.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            popularSymbols.add(new Pair<>(entry.getValue(), entry.getKey()));
        }
        popularSymbols.sort(new PopularityComparator<>(new CharacterComparator()));
    }

    public List<Pair<Integer, String>> popularWords() {
        fillPopularWords();
        return popularWords;
    }

    public List<Pair<Integer, Character>> popularCharacters() {
        fillPopularSymbols();
        return popularSymbols;
    }

    public List<Pair<Integer, String>> topPopularWords(int count) {
        fillPopularWords();
        if (count <= 0) throw new IllegalArgumentException("count cannot be negative");
        return count >= popularWords.size() ? popularWords : popularWords.subList(0, count);
    }

    public List<Pair<Integer, Character>> topPopularSymbols(int count) {
        fillPopularSymbols();
        if (count <= 0) throw new IllegalArgumentException("count cannot be negative");
        return count >= popularSymbols.size() ? popularSymbols : popularSymbols.subList(0, count);
    }

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    private static class CharacterComparator implements Comparator<Character> {
        @Override
        public int compare(Character o1, Character o2) {
            return o1.compareTo(o2);
        }
    }

    // Descending Comparator for Integer frequencies of anything
    private static class PopularityComparator<T extends Comparable<T>> implements Comparator<Pair<Integer, T>> {
        private final Comparator<T> valuesComparator;

        public PopularityComparator(Comparator<T> valuesComparator) {
            this.valuesComparator = valuesComparator;
        }

        public PopularityComparator() {
            valuesComparator = null;
        }

        @Override
        public int compare(Pair<Integer, T> o1, Pair<Integer, T> o2) {
            return valuesComparator == null ? o1.getKey().compareTo(o2.getKey()) :
                    o1.getKey().equals(o2.getKey()) ? valuesComparator.compare(o1.getValue(), o2.getValue()) :
                            o2.getKey().compareTo(o1.getKey());
        }
    }
}
