package ru.ifmo.jjd.exercises.e16reflection.zoo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Zoo {
    private final Aviary[] aviaries;
    private final Set<Animal> animals = new HashSet<>();
    private final Set<Employee> employees = new HashSet<>();
    private final Map<String, Account> accounts = new HashMap<>();
    private final Set<Visitor> visitors = new HashSet<>();
    private String name = "";
    private double ticketPrice;

    public Zoo(String name, int size) {
        setName(name);
        this.aviaries = new Aviary[size];
        for (int i = 1; i <= size; i++) {
            aviaries[i-1] = new Aviary(i, i * i * 100);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) throw new NullPointerException();
        this.name = name;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = Math.min(Math.max(0, ticketPrice), 20D);
    }

    public Aviary[] getAviaries() {
        return aviaries;
    }

    public Aviary getAviary(int number) {
        for (Aviary aviary : aviaries) {
            if (aviary != null && aviary.getNumber() == number) {
                return aviary;
            }
        }
        return null;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void hire(Person person, Position position, int salary) {
        if (person == null || position == null) throw new NullPointerException();
        Employee employee = new Employee(person, position, salary);
        employees.add(employee);
        accounts.put(employee.getName(), new Account(employee.getName(), "", employee));
    }

    public void addVisitor(Visitor visitor) {
        if (visitor == null) throw new NullPointerException();
        visitors.add(visitor);
    }

    public void addAnimal(Animal animal, int aviary) {
        if (animal == null) throw new NullPointerException();
        Aviary av = getAviary(aviary);
        if (av == null) throw new IllegalArgumentException();
        animals.add(animal);
        av.getInhabitants().add(animal);
    }
}
