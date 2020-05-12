package ru.ifmo.jjd.exercises.e20lambdas.ex05;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

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
        if (nameNorm.isBlank()) throw new IllegalArgumentException("Provided name '" + name +
                                                                   "' is invalid or too short");
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
        return age == customer.age &&
               uuid.equals(customer.uuid) &&
               name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, age);
    }

    @Override
    public String toString() {
        return "Customer{" + "uuid='" + uuid + '\'' + ", name='" + name + '\'' + ", age=" + age + '}';
    }

    static class Generator {
        public static Map<String, Customer> generate(int amount) {
            Map<String, Customer> result = new HashMap<>();
            String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max",
                    "Jenifer", "Linda", "Elizabeth", "Oliver", "Harry", "George", "Leo", "Oscar", "Jakob", "Eva"};
            Customer customer;
            for (int i = 0; i < amount; i++) {
                customer = new Customer(names[randomInt(names.length)], randomInt(AGES[0], AGES[1]));
                result.put(customer.getUuid(), customer);
            }
            return result;
        }
    }
}
