package ru.ifmo.jjd.exercises.e21streams.ex03;

import ru.ifmo.jjd.utils.ConfigLoader;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class TextTask {
    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        ConfigLoader loader = ConfigLoader.getConfigLoader();
        Path file = Paths.get(loader.getConfigFile(className).getParent() + File.separator
                              + loader.getProperty(className, "filename"));
        if (!Files.exists(file) || !Files.isRegularFile(file) || !Files.isReadable(file)) {
            println("failed to find the file to parse");
            return;
        }
        Stream<String> text;
        try {
            text = Files.lines(file);
        } catch (IOException e) {
            println("failed to read the file '" + file + "'");
            e.printStackTrace();
            return;
        }
        Map<String, Long> popularWords = text
                .parallel()
                .map(line -> normalize(line, "[\\S&&[^A-Za-zА-ЯЁа-яё]]+").toLowerCase())
                .flatMap(line -> Stream.of(line.split("\\s+")))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .parallelStream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        println("In the given text '" + file + "' \nthe top ten most popular words are:");
        popularWords.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> println("'" + entry.getKey() + "': " + entry.getValue() + " times"));
    }
}
/*
 * In the given text 'sources\ru\ifmo\jjd\exercises\e21streams\ex03\SampleText.txt'
 * the top ten most popular words are:
 * 'the': 164 times
 * 'he': 159 times
 * 'to': 119 times
 * 'and': 105 times
 * 'a': 91 times
 * 'of': 79 times
 * 'that': 78 times
 * 'his': 78 times
 * 'it': 68 times
 * 'was': 59 times
 * */
