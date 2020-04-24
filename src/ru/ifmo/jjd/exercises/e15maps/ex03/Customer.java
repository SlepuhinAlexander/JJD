package ru.ifmo.jjd.exercises.e15maps.ex03;

import java.util.UUID;

import static ru.ifmo.jjd.utils.StringHelper.*;

public class Customer {
    private static final int[] AGES = {12, 100};
    private String uuid;
    private String name;
    private int age;

    public Customer(String name, int age) {
        setUuid();
        setName(name);
        setAge(age);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = UUID.randomUUID().toString().substring(0, 8);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String nameNorm = normalize(name);
        if (nameNorm.isBlank()) {
            throw new IllegalArgumentException("Provided name '" + name + "' is invalid or too short");
        }
        this.name = nameNorm;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Math.min(Math.max(AGES[0], age), AGES[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return uuid.equals(customer.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return "Customer{" + "id='" + uuid + '\'' + ", name='" + name + '\'' + ", age=" + age + '}';
    }
}