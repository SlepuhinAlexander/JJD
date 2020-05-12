package ru.ifmo.jjd.exercises.e20lambdas.ex06;

import ru.ifmo.jjd.utils.Pair;

import java.util.*;

import static ru.ifmo.jjd.utils.StringHelper.isNullOrBlank;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class TextAnalyser {
    private final String text;
    private final TreeMap<String, Integer> words = new TreeMap<>();
    private final TreeMap<Character, Integer> symbols = new TreeMap<>();
    private final List<Pair<Integer, String>> popularWords = new ArrayList<>();
    private final List<Pair<Integer, Character>> popularSymbols = new ArrayList<>();
    private int wordsCount;

    private TextAnalyser(String text) {
        if (isNullOrBlank(text)) throw new IllegalArgumentException("Text cannot be null or blank");
        String textNorm = normalize(text, "[\\S&&[^A-Za-zА-ЯЁа-яё]]+").toLowerCase();
        if (textNorm.isBlank()) throw new IllegalArgumentException("Text consists of unsupported characters only");
        this.text = textNorm;
    }

    public static TextAnalyser forText(String text) {
        return new TextAnalyser(text);
    }

    private void tokenize() {
        if (!words.isEmpty()) return;
        String[] wordArray = text.split("\\s+");
        for (String w : wordArray) words.merge(w, 1, Integer::sum);
        wordsCount = wordArray.length;
    }

    private void symbolize() {
        if (!symbols.isEmpty()) return;
        char[] chars = text.replaceAll("\\s+", "").toCharArray();
        for (char c : chars) symbols.merge(c, 1, Integer::sum);
    }

    public Map<String, Integer> getWords() {
        tokenize();
        return words;
    }

    public Map<Character, Integer> getSymbols() {
        symbolize();
        return symbols;
    }

    public int wordCount(String word) {
        tokenize();
        if (isNullOrBlank(word)) return 0;
        return words.getOrDefault(word.toLowerCase(), 0);
    }

    public int symbolCount(char c) {
        symbolize();
        return symbols.getOrDefault(Character.toLowerCase(c), 0);
    }

    public double wordPopularity(String word) {
        tokenize();
        if (isNullOrBlank(word)) return 0d;
        return (int) (words.getOrDefault(word.toLowerCase(), 0) * 10_000d / wordsCount) / 100d;
    }

    public double symbolPopularity(char c) {
        symbolize();
        return (int) (symbols.getOrDefault(Character.toLowerCase(c), 0) * 10_000d / text.length()) / 100d;
    }

    private void fillPopularWords() {
        if (!popularWords.isEmpty()) return;
        tokenize();
        words.forEach((word, count) -> popularWords.add(new Pair<>(count, word)));
        popularWords.sort((o1, o2) -> o1.getKey().equals(o2.getKey()) ?
                o1.getValue().compareTo(o2.getValue()) :
                o2.getKey().compareTo(o1.getKey()));
    }

    private void fillPopularSymbols() {
        if (!popularSymbols.isEmpty()) return;
        symbolize();
        symbols.forEach((symbol, count) -> popularSymbols.add(new Pair<>(count, symbol)));
        popularSymbols.sort((o1, o2) -> o1.getKey().equals(o2.getKey()) ?
                o1.getValue().compareTo(o2.getValue()) :
                o2.getKey().compareTo(o1.getKey()));
    }

    public List<Pair<Integer, String>> getPopularWords() {
        fillPopularWords();
        return popularWords;
    }

    public List<Pair<Integer, Character>> getPopularSymbols() {
        fillPopularSymbols();
        return popularSymbols;
    }

    public List<Pair<Integer, String>> topPopularWords(int count) {
        if (count <= 0) throw new IllegalArgumentException("Count cannot be negative");
        fillPopularWords();
        return count >= popularWords.size() ? popularWords : popularWords.subList(0, count);
    }

    public List<Pair<Integer, Character>> topPopularSymbols(int count) {
        if (count <=0) throw new IllegalArgumentException("Count cannot be negative");
        fillPopularSymbols();
        return count >= popularSymbols.size() ? popularSymbols : popularSymbols.subList(0, count);
    }
}
