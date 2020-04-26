package ru.ifmo.jjd.exercises.e16reflection.zoo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Aviary {
    private int number;
    private final double area;
    private final Collection<Animal> inhabitants;

    public Aviary(int number, double area) {
        setNumber(number);
        this.area = area;
        inhabitants = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = Math.max(1, number);
    }

    public double getArea() {
        return area;
    }

    public Collection<Animal> getInhabitants() {
        return inhabitants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aviary)) return false;
        Aviary aviary = (Aviary) o;
        return number == aviary.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
