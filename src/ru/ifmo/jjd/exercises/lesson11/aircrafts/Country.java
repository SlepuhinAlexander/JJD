package ru.ifmo.jjd.exercises.lesson11.aircrafts;

import java.util.Objects;

import static ru.ifmo.jjd.utils.StringHelper.normalize;
import static ru.ifmo.jjd.utils.StringHelper.normalizeLatinWord;

public class Country {
    public final String name;
    public final String code;

    public Country(String name, String code) {
        String normalizedName = normalize(name);
        String normalizedCode = normalizeLatinWord(code).toUpperCase();
        if (!normalizedName.isBlank() && normalizedCode.length() >= 2 && normalizedCode.length() <= 3) {
            this.name = normalizedName;
            this.code = normalizedCode;
        } else if (normalizedName.isBlank()) {
            throw new IllegalArgumentException("Country name '" + name + "' is invalid");
        } else {
            throw new IllegalArgumentException("Country code '" + code + "' is invalid or too short");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
