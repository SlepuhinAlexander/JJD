package ru.ifmo.jjd.exercises.e07aircrafts;

import java.time.ZonedDateTime;
import java.util.Objects;

import static ru.ifmo.jjd.utils.StringHelper.normalize;
import static ru.ifmo.jjd.utils.StringHelper.normalizeLatinWord;

public class Airport {
    public final String code;
    public final String name;
    public final City city;

    public Airport(String code, String name, City city) {
        String normalizedCode = normalizeLatinWord(code).toUpperCase();
        String normalizedName = normalize(name);
        if (normalizedCode.length() == 3 && !normalizedName.isBlank() && city != null) {
            this.code = normalizedCode;
            this.name = normalizedName;
            this.city = city;
        } else if (normalizedCode.length() != 3) {
            throw new IllegalArgumentException("Airport code '" + code + "' is invalid or too short");
        } else if (normalizedName.isBlank()) {
            throw new IllegalArgumentException("Airport name '" + name + "' is invalid");
        } else {
            throw new NullPointerException("City is null");
        }
    }

    public ZonedDateTime now() {
        return city.now();
    }

    public ZonedDateTime toLocalZone(ZonedDateTime when) {
        return city.toLocalZone(when);
    }

    @Override
    public String toString() {
        return code;
    }

    public String withLocation() {
        return code + ", " + city;
    }

    public String toFullString() {
        return name + ", " + city.toFullString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return code.equals(airport.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
