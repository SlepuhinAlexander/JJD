package ru.ifmo.jjd.exercises.e21streams.ex02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static ru.ifmo.jjd.utils.RandomHelper.randomBoolean;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.normalizeWord;

public class Pupil {
    private static int counter = 0;
    private final int number; // уникальное значение для каждого ученика
    private final String name;
    private final Gender gender;
    private final LocalDate birthday;

    public Pupil(String name, Gender gender, LocalDate birth) {
        if (gender == null) throw new NullPointerException("gender cannot be null");
        String nameNorm = normalizeWord(name);
        if (nameNorm.isBlank()) throw new IllegalArgumentException("name '" + name + "' is invalid");
        if (birth.isAfter(LocalDate.now().minusYears(6))) {
            throw new IllegalArgumentException("pupils under 6 y.o. are not allowed");
        }
        number = ++counter;
        this.name = nameNorm;
        this.gender = gender;
        this.birthday = birth;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pupil)) return false;
        Pupil pupil = (Pupil) o;
        return number == pupil.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Pupil{" +
               "number=" + number +
               ", name='" + name + '\'' +
               ", gender=" + gender.toString().toLowerCase() +
               ", birthday='" + birthday.format(DateTimeFormatter.ofPattern("d MMM yy", Locale.ENGLISH)) +
               '\'' + '}';
    }

    enum Gender {
        MALE, FEMALE
    }

    static class Generator {
        public static List<Pupil> generate(int amount) {
            if (amount <= 0) return Collections.emptyList();
            List<Pupil> pupils = new ArrayList<>();
            String[] male = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max"};
            String[] female = {"Jenifer", "Linda", "Elizabeth", "Samantha", "Sophia"};
            boolean b;
            for (int i = 0; i < amount; i++) {
                b = randomBoolean();
                pupils.add(new Pupil(b ? male[randomInt(male.length)] : female[randomInt(female.length)],
                        b ? Gender.MALE : Gender.FEMALE,
                        LocalDate.now()
                                .minusYears(randomInt(6, 16))
                                .minusMonths(randomInt(0, 12))
                                .minusDays(randomInt(0,30))
                ));
            }
            return pupils;
        }
    }
}