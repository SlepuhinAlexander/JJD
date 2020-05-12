package ru.ifmo.jjd.exercises.e20lambdas.ex03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static List<String> getLoginsByCity(Map<String, String> logins, String city) {
        if (logins == null || city == null) throw new NullPointerException("Both logins and city cannot be null");
        List<String> result = new ArrayList<>(logins.size());
        logins.forEach((l, c) -> {
            if (city.trim().equalsIgnoreCase(c.trim())) result.add(l);
        });
        return result;
    }

    private static class Generator {
        private static final String[] CITIES = {"Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург",
                "Нижний Новгород", "Казань", "Самара", "Омск", "Челябинск"};

        private static Map<String, String> generate(int amount) {
            Map<String, String> result = new HashMap<>();
            for (int i = 0; i < amount; i++) result.put(randomWord(3), CITIES[randomInt(CITIES.length)]);
            return result;
        }
    }
}
/*
 * Given map:
 * {kyq=Екатеринбург
 *  tuf=Омск
 *  gyx=Нижний Новгород
 *  oby=Самара
 *  fos=Москва
 *  myv=Казань
 *  emu=Москва
 *  caj=Челябинск
 *  tif=Нижний Новгород
 *  woh=Нижний Новгород
 *  yby=Екатеринбург
 *  ref=Санкт-Петербург
 *  lek=Нижний Новгород
 *  axi=Санкт-Петербург
 *  giw=Москва
 *  keq=Москва
 *  seh=Москва
 *  rov=Новосибирск
 *  mev=Казань
 *  new=Москва
 *  ona=Новосибирск
 *  eli=Челябинск
 *  axy=Самара
 *  ovo=Москва
 *  uzy=Екатеринбург
 *  ebu=Челябинск
 *  ubi=Самара
 *  age=Омск}
 *
 * Contains the following logins for city 'Санкт-Петербург':
 * {ref
 *  axi}
 * */

