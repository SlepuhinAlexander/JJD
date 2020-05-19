package ru.ifmo.jjd.exercises.e23sync.ex02;

import java.util.*;

import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class WordProcessor implements Runnable {
    private final Map<String, Integer> words;
    private final List<String> lines;

    public WordProcessor(Map<String, Integer> words, List<String> lines) {
        Objects.requireNonNull(words, "Words map cannot be null");
        Objects.requireNonNull(lines, "Lines list cannot be null");
        this.words = words;
        this.lines = lines;
    }

    @Override
    public void run() {
        Map<String, Integer> wordsPart = new HashMap<>();
        for (String line : lines) {
            line = normalize(line, "[\\S&&[^А-ЯЁа-яё]]+").toLowerCase();
            if (line.isBlank()) continue;
            String[] wordArr = line.split(" +");
            for (String word : wordArr) {
                word = word.trim();
                if (wordsPart.containsKey(word)) {
                    wordsPart.replace(word, wordsPart.get(word) + 1);
                } else {
                    wordsPart.put(word, 1);
                }
            }
        }
        synchronized (words) {
            Set<Map.Entry<String, Integer>> entrySet = wordsPart.entrySet();
            for (Map.Entry<String, Integer> entry : entrySet) {
                if (words.containsKey(entry.getKey())) {
                    words.replace(entry.getKey(), words.get(entry.getKey()) + entry.getValue());
                } else {
                    words.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
