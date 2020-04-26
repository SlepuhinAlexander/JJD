package ru.ifmo.jjd.exercises.e16reflection.zoo;

import java.util.Objects;

public abstract class Animal {
    public final String clazz;
    public final String family;
    public final String species;
    public String name;

    public Animal(String name, String clazz, String family, String species) {
        setName(name);
        this.clazz = clazz;
        this.family = family;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public String getFamily() {
        return family;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return name.equals(animal.name) &&
                clazz.equals(animal.clazz) &&
                family.equals(animal.family) &&
                species.equals(animal.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, clazz, family, species);
    }
}
