package ru.ifmo.jjd.lessons.l21serialize.serialization;

import java.io.Serializable;

public class Human implements Serializable {
    protected String name = "Человек";
    protected int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 5) this.age = age;
    }
}
