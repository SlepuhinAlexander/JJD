package ru.ifmo.jjd.exercises.lesson11.fitness;

import ru.ifmo.jjd.utils.time.TimeRange;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Main {
    private static final String[] R_NAMES_M = {"Александр", "Михаил", "Максим", "Иван", "Сергей",
            "Виктор", "Андрей", "Пётр", "Степан", "Владимир"};
    private static final String[] R_NAMES_F = {"Екатерина", "Мария", "Анна", "Виктория", "Алиса",
            "Татьяна", "Анастасия", "Евгения", "Дарья", "Ольга"};
    private static final String[] R_SURNAMES_M = {"Александров", "Михайлов", "Максимов", "Иванов", "Сергеев",
            "Викторов", "Андреев", "Петров", "Степанов", "Владимиров"};
    private static final String[] R_SURNAMES_F = {"Александрова", "Михайлова", "Максимова", "Иванова", "Сергеева",
            "Викторова", "Андреева", "Петрова", "Степанова", "Владимирова"};
    private static final Random R = new Random(new Date().getTime());

    public static void main(String[] args) {
        FitnessClub club = new FitnessClub("Тю-лень",
                new TimeRange(7, 0, 23, 0));
        club.addArea("Большой", Area.AreaType.POOL,
                new TimeRange(8, 0, 19, 0), 20);
        club.addArea("Малый", Area.AreaType.POOL,
                new TimeRange(8, 0, 19, 0), 10);
        club.addArea("Общий", Area.AreaType.GYM,
                new TimeRange(9, 30, 22, 30), 20);
        club.addArea("Индивидуальный", Area.AreaType.GYM,
                new TimeRange(9, 30, 22, 30), 2);
        club.addArea("Йога", Area.AreaType.GROUP,
                new TimeRange(12,30,15,30),20);
        club.addArea("Пилатес", Area.AreaType.GROUP,
                new TimeRange(15,45,17,15),15);
        club.addArea("Лечебная физкультура", Area.AreaType.GROUP,
                new TimeRange(7,30,11,0),30);

        Subscription.SubscriptionType[] types = Subscription.SubscriptionType.values();
        for (int i = 0; i < 1000; i++) {
            club.register(getRandomPerson(), types[randomInt(types.length)]);
        }
        ArrayList<Subscription> subs = new ArrayList<>(club.getSubscriptions());
        ArrayList<Area> areas = new ArrayList<>(club.getAreas());
        for (int i = 0; i < 1000; i++) {
            club.visit(subs.get(randomInt(subs.size())), areas.get(randomInt(areas.size()))/*,
                    LocalTime.now().minusMinutes(randomInt(300,1000))*/); // toggle comment to adjust visit time
        }
        club.listVisitors();
    }

    static Person getRandomPerson() {
        Person result;
        LocalDate birthday = LocalDate.now().minusYears(18).minusDays(randomInt(10000));
        if (randomBoolean()) {
            result = new Person(R_SURNAMES_F[randomInt(R_SURNAMES_F.length)],
                    R_NAMES_F[randomInt(R_NAMES_F.length)], birthday);
        } else {
            result = new Person(R_SURNAMES_M[randomInt(R_SURNAMES_M.length)],
                    R_NAMES_M[randomInt(R_NAMES_M.length)], birthday);
        }
        return result;
    }
}
