package ru.ifmo.jjd.exercises.e20lambdas.ex01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class Employee {
    private static final int[] AGES = {21, 60};
    private static final int[] SALARIES = {20_000, 100_000};

    private String name;
    private String company;
    private int salary;
    private int age;

    public Employee(String name, String company, int salary, int age) {
        setName(name);
        setCompany(company);
        setSalary(salary);
        setAge(age);
    }

    public static List<Employee> generate(int amount) {
        if (amount <= 0) return Collections.emptyList();
        String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda",
                "Elizabeth"};
        String[] companies = {"Microsoft", "IBM", "Google", "General Electric", "Siemens", "Samsung", "Apple"};
        List<Employee> employees = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            employees.add(new Employee(names[randomInt(names.length)],
                    companies[randomInt(companies.length)],
                    randomInt(SALARIES[0], SALARIES[1] + 1),
                    randomInt(AGES[0], AGES[1] + 1)));
        }
        return employees;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        String companyNorm = normalize(company);
        if (companyNorm.isBlank()) {
            throw new IllegalArgumentException("Provided company name '" + company + "' is invalid or too short");
        }
        this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = Math.min(Math.max(SALARIES[0], salary), SALARIES[1]);
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
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary &&
               age == employee.age &&
               name.equals(employee.name) &&
               company.equals(employee.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, salary, age);
    }

    @Override
    public String toString() {
        return name + ", " + age + " y.o., works at " + company + " for $" + salary + " per year";
    }
}
