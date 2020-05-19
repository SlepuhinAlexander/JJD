package ru.ifmo.jjd.exercises.e23sync.ex02;

import ru.ifmo.jjd.utils.ConfigLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.ifmo.jjd.utils.ConsoleHelper.printf;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;

public class TextTask {
    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        ConfigLoader loader = ConfigLoader.getConfigLoader();
        Path file = Paths.get(loader.getConfigFile(className).getParent() + File.separator +
                              loader.getProperty(className, "filename"));
        if (!Files.exists(file) || !Files.isRegularFile(file) || !Files.isReadable(file)) {
            println("Failed to find a file to parse");
            return;
        }
        Map<String, Integer> words = tokenize(file);
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(words.entrySet());
        entryList.sort((o1, o2) -> o2.getValue() - o1.getValue());
        entryList = entryList.subList(0, 100);
        println("100 самых популярных слов в '" + file + "':");
        entryList.forEach(entry -> printf("%10s: %5d раз(а)\n", '\'' + entry.getKey() + '\'', entry.getValue()));
    }

    public static Map<String, Integer> tokenize(Path file) {
        if (file == null || !Files.exists(file) || !Files.isRegularFile(file) || !Files.exists(file)) return null;
        int processors = Runtime.getRuntime().availableProcessors();
        List<String> lines;
        try (BufferedReader in = Files.newBufferedReader(file)) {
            lines = Files.readAllLines(file);
        } catch (IOException e) {
            println("Failed to read from file '" + file + "'");
            e.printStackTrace();
            return null;
        }
        Map<String, Integer> words = new HashMap<>();
        int part = lines.size() / processors;
        for (int i = 1; i <= processors; i++) {
            Thread thread = new Thread(new WordProcessor(words, lines.subList((i - 1) * part, i == processors ?
                    lines.size() : i * part)));
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return words;
    }
}
/*
 * 100 самых популярных слов в 'sources\ru\ifmo\jjd\exercises\e23sync\ex02\VoinaIMir.txt':
 *        'и': 24679 раз(а)
 *        'в': 18212 раз(а)
 *       'не':  9600 раз(а)
 *       'он':  8145 раз(а)
 *      'что':  7677 раз(а)
 *       'на':  7538 раз(а)
 *        'с':  6863 раз(а)
 *      'как':  4550 раз(а)
 *      'его':  4395 раз(а)
 *        'к':  3941 раз(а)
 *      'стр':  3530 раз(а)
 *   'строка':  3441 раз(а)
 *        'я':  3396 раз(а)
 *        'г':  3099 раз(а)
 *       'но':  2997 раз(а)
 *   'вместо':  2936 раз(а)
 *      'она':  2913 раз(а)
 *      'изд':  2794 раз(а)
 *      'это':  2783 раз(а)
 *     'было':  2679 раз(а)
 *       'то':  2388 раз(а)
 *        'о':  2299 раз(а)
 *   'сказал':  2225 раз(а)
 *       'по':  2202 раз(а)
 *      'так':  2120 раз(а)
 *        'а':  2101 раз(а)
 *       'за':  2015 раз(а)
 *       'от':  1976 раз(а)
 *       'же':  1948 раз(а)
 *       'из':  1919 раз(а)
 *      'ему':  1914 раз(а)
 *       'ее':  1880 раз(а)
 *      'был':  1835 раз(а)
 *      'всё':  1803 раз(а)
 *   'только':  1735 раз(а)
 *       'бы':  1614 раз(а)
 *    'князь':  1592 раз(а)
 *     'пьер':  1572 раз(а)
 *        'у':  1499 раз(а)
 *      'для':  1467 раз(а)
 *      'еще':  1256 раз(а)
 *        'р':  1213 раз(а)
 *       'вы':  1207 раз(а)
 *    'когда':  1174 раз(а)
 *      'все':  1146 раз(а)
 *      'они':  1025 раз(а)
 *       'да':  1014 раз(а)
 *     'того':  1011 раз(а)
 *     'него':  1006 раз(а)
 *    'после':  1004 раз(а)
 *     'была':   985 раз(а)
 *   'теперь':   979 раз(а)
 *     'были':   976 раз(а)
 *       'ни':   969 раз(а)
 *   'андрей':   963 раз(а)
 *      'нет':   954 раз(а)
 *       'ты':   919 раз(а)
 *     'себя':   896 раз(а)
 *   'наташа':   892 раз(а)
 *       'их':   888 раз(а)
 *       'во':   882 раз(а)
 *      'или':   867 раз(а)
 *      'мне':   864 раз(а)
 *    'этого':   859 раз(а)
 *      'том':   852 раз(а)
 *     'быть':   851 раз(а)
 *    'время':   838 раз(а)
 *    'чтобы':   829 раз(а)
 *      'уже':   809 раз(а)
 *  'сказала':   783 раз(а)
 *       'до':   767 раз(а)
 *  'которые':   742 раз(а)
 *     'себе':   735 раз(а)
 *  'говорил':   732 раз(а)
 *       'ей':   708 раз(а)
 *  'который':   708 раз(а)
 *     'этот':   676 раз(а)
 *     'меня':   676 раз(а)
 *      'вот':   666 раз(а)
 *       'мы':   659 раз(а)
 *    'опять':   659 раз(а)
 *       'чт':   650 раз(а)
 *      'тем':   648 раз(а)
 *       'ну':   645 раз(а)
 *     'всех':   645 раз(а)
 *      'при':   636 раз(а)
 *   'ничего':   629 раз(а)
 *    'очень':   617 раз(а)
 *      'чем':   613 раз(а)
 *     'слов':   606 раз(а)
 *    'пьера':   604 раз(а)
 *   'ростов':   601 раз(а)
 *      'под':   598 раз(а)
 *   'княжна':   594 раз(а)
 *   'потому':   594 раз(а)
 *    'князя':   591 раз(а)
 *       'ли':   567 раз(а)
 *  'человек':   556 раз(а)
 *     'лицо':   555 раз(а)
 *    'будто':   547 раз(а)
 * */
