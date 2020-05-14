package ru.ifmo.jjd.lessons.l24streams;

import java.util.Objects;

public class User {
    private String name;
    private int age;
    private boolean active;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    boolean isActive() {
        return active;
    }

    void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return age == user.age &&
               active == user.active &&
               name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, active);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + ", active=" + active + '}';
    }
}