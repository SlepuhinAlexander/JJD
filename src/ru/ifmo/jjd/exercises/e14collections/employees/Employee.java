package ru.ifmo.jjd.exercises.e14collections.employees;

import java.util.*;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class Employee {
    private static final int[] SALARIES = {20000, 100000};
    private static final int[] AGES = {21, 60};

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

    // метод для создания списка объектов класса Employee
    public static List<Employee> employeeGenerator(int num) {
        // массив с именами
        String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda",
                "Elizabeth"};
        // массив с названиями компаний
        String[] companies = {"Microsoft", "IBM", "Google", "General Electric", "Siemens", "Samsung", "Apple"};
        if (num <= 0) {
            return Collections.emptyList();
        }
        List<Employee> employees = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
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
        this.company = companyNorm;
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

/**
 * Compares two Employees by their name, alphanumerically, ascending.
 */
class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

/**
 * Compares two Employees by their company, alphanumerically, ascending.
 */
class EmployeeCompanyComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getCompany().compareTo(o2.getCompany());
    }
}

/**
 * Compares two Employees by their salary, ascending
 */
class EmployeeSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getSalary() - o2.getSalary();
    }
}

/**
 * Compares two Employees by their age, ascending.
 */
class EmployeeAgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getAge() - o2.getAge();
    }
}

