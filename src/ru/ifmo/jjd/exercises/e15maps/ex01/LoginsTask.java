package ru.ifmo.jjd.exercises.e15maps.ex01;

import java.util.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.randomWord;

public class LoginsTask {

    public static void main(String[] args) {
        Map<String, String> logins = Generator.generate(randomInt(20, 50));
        println("Given map:");
        println(logins);
        String city = Generator.CITIES[randomInt(Generator.CITIES.length)];
        println("\nContains the following logins for city '" + city + "':");
        println(getLoginsByCity(logins, city));
    }

    public static List<String> getLoginsByCity(Map<String, String> map, String city) {
        if (map == null || city == null) throw new NullPointerException();
        List<String> result = new ArrayList<>();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            if (entry.getValue().equals(city)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    private static class Generator {
        private static final String[] CITIES = {"Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург",
                "Нижний Новгород", "Казань", "Самара", "Омск", "Челябинск"};

        private static Map<String, String> generate(int amount) {
            Map<String, String> result = new HashMap<>();
            if (amount <= 0) {
                return result;
            }
            for (int i = 0; i < amount; i++) {
                result.put(randomWord(3), CITIES[randomInt(CITIES.length)]);
                if (result.size() < i + 1) i--; // failed to add, retry;
            }
            return result;
        }
    }
}
/*
 * Given map:
 * umy=Челябинск
 * owi=Нижний Новгород
 * hok=Санкт-Петербург
 * mof=Москва
 * bac=Санкт-Петербург
 * rod=Новосибирск
 * fij=Нижний Новгород
 * ehi=Новосибирск
 * iji=Екатеринбург
 * mec=Самара
 * yhy=Москва
 * myv=Самара
 * uxo=Санкт-Петербург
 * ity=Казань
 * edo=Санкт-Петербург
 * wib=Омск
 * ama=Казань
 * gyg=Казань
 * umo=Екатеринбург
 * daz=Санкт-Петербург
 * did=Екатеринбург
 * ipe=Челябинск
 * uxa=Омск
 *
 * Contains the following logins for city 'Нижний Новгород':
 * owi
 * fij
 * */