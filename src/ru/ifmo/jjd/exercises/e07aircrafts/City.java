package ru.ifmo.jjd.exercises.e07aircrafts;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class City {
    public final String name;
    public final Country country;
    public final ZoneId zone;

    public City(String name, Country country, ZoneId zone) {
        String normalizedName = normalize(name);
        if (!normalizedName.isBlank() && country != null && zone != null) {
            this.name = normalizedName;
            this.country = country;
            this.zone = zone;
        } else if (normalizedName.isBlank()) {
            throw new IllegalArgumentException("City name '" + name + "' is invalid");
        } else if (country == null) {
            throw new NullPointerException("Country is null");
        } else {
            throw new IllegalArgumentException("Time zone is null");
        }
    }

    public City(String name, Country country, String zone) {
        String normalizedName = normalize(name);
        if (!normalizedName.isBlank() && country != null && zone != null && ZoneId.getAvailableZoneIds().contains(zone)) {
            this.name = normalizedName;
            this.country = country;
            this.zone = ZoneId.of(zone);
        } else if (normalizedName.isBlank()) {
            throw new IllegalArgumentException("City name '" + name + "' is invalid");
        } else if (country == null) {
            throw new NullPointerException("Country is null");
        } else {
            throw new IllegalArgumentException("Time zone '" + zone + "' is is invalid or null");
        }
    }

    public ZonedDateTime now() {
        return ZonedDateTime.now(zone).withNano(0).withSecond(0);
    }

    public ZonedDateTime toLocalZone(ZonedDateTime when) {
        return when.withZoneSameInstant(zone);
    }

    @Override
    public String toString() {
        return name + ", " + country.code;
    }

    public String toFullString() {
        return name + ", " + country;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return name.equals(city.name) &&
                country.equals(city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
