package ru.ifmo.jjd.exercises.e20lambdas.ex01;

import java.util.Comparator;
import java.util.List;

import static ru.ifmo.jjd.utils.ConsoleHelper.print;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class EmployeesTask {
    public static void main(String[] args) {
        List<Employee> employees = Employee.generate(randomInt(10, 30));
        print("=======================================\n" +
              "Created employees list (before sorting)\n" +
              "=======================================\n");
        println(employees);
        employees.sort(Comparator.comparing(Employee::getName));
        print("=======================================\n" +
              "Employee list sorted by name (asc)\n" +
              "=======================================\n");
        println(employees);
        employees.sort(Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary));
        print("===================================================\n" +
              "Employee list sorted by name (asc) and salary (asc)\n" +
              "===================================================\n");
        println(employees);
        employees.sort(Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary).
                thenComparing(Employee::getAge).thenComparing(Employee::getCompany));
        print("=============================================================================\n" +
              "Employee list sorted by name (asc), salary (asc), age (asc) and company (asc)\n" +
              "=============================================================================\n");
        println(employees);
    }
}
/*
 * =======================================
 * Created employees list (before sorting)
 * =======================================
 * {Jenifer, 56 y.o., works at General Electric for $55167 per year
 *  Mike, 58 y.o., works at Samsung for $51254 per year
 *  Mike, 57 y.o., works at Apple for $58649 per year
 *  John, 33 y.o., works at Google for $53118 per year
 *  Tom, 46 y.o., works at Google for $74125 per year
 *  Jack, 43 y.o., works at Microsoft for $79487 per year
 *  Charlie, 22 y.o., works at Google for $79325 per year
 *  Jack, 32 y.o., works at Apple for $60279 per year
 *  Jenifer, 60 y.o., works at General Electric for $85812 per year
 *  Tom, 50 y.o., works at Siemens for $76816 per year
 *  John, 33 y.o., works at Google for $69075 per year
 *  Linda, 55 y.o., works at IBM for $99065 per year
 *  Max, 30 y.o., works at IBM for $54977 per year
 *  Mike, 54 y.o., works at Siemens for $82384 per year}
 * =======================================
 * Employee list sorted by name (asc)
 * =======================================
 * {Charlie, 22 y.o., works at Google for $79325 per year
 *  Jack, 43 y.o., works at Microsoft for $79487 per year
 *  Jack, 32 y.o., works at Apple for $60279 per year
 *  Jenifer, 56 y.o., works at General Electric for $55167 per year
 *  Jenifer, 60 y.o., works at General Electric for $85812 per year
 *  John, 33 y.o., works at Google for $53118 per year
 *  John, 33 y.o., works at Google for $69075 per year
 *  Linda, 55 y.o., works at IBM for $99065 per year
 *  Max, 30 y.o., works at IBM for $54977 per year
 *  Mike, 58 y.o., works at Samsung for $51254 per year
 *  Mike, 57 y.o., works at Apple for $58649 per year
 *  Mike, 54 y.o., works at Siemens for $82384 per year
 *  Tom, 46 y.o., works at Google for $74125 per year
 *  Tom, 50 y.o., works at Siemens for $76816 per year}
 * ===================================================
 * Employee list sorted by name (asc) and salary (asc)
 * ===================================================
 * {Charlie, 22 y.o., works at Google for $79325 per year
 *  Jack, 32 y.o., works at Apple for $60279 per year
 *  Jack, 43 y.o., works at Microsoft for $79487 per year
 *  Jenifer, 56 y.o., works at General Electric for $55167 per year
 *  Jenifer, 60 y.o., works at General Electric for $85812 per year
 *  John, 33 y.o., works at Google for $53118 per year
 *  John, 33 y.o., works at Google for $69075 per year
 *  Linda, 55 y.o., works at IBM for $99065 per year
 *  Max, 30 y.o., works at IBM for $54977 per year
 *  Mike, 58 y.o., works at Samsung for $51254 per year
 *  Mike, 57 y.o., works at Apple for $58649 per year
 *  Mike, 54 y.o., works at Siemens for $82384 per year
 *  Tom, 46 y.o., works at Google for $74125 per year
 *  Tom, 50 y.o., works at Siemens for $76816 per year}
 * =============================================================================
 * Employee list sorted by name (asc), salary (asc), age (asc) and company (asc)
 * =============================================================================
 * {Charlie, 22 y.o., works at Google for $79325 per year
 *  Jack, 32 y.o., works at Apple for $60279 per year
 *  Jack, 43 y.o., works at Microsoft for $79487 per year
 *  Jenifer, 56 y.o., works at General Electric for $55167 per year
 *  Jenifer, 60 y.o., works at General Electric for $85812 per year
 *  John, 33 y.o., works at Google for $53118 per year
 *  John, 33 y.o., works at Google for $69075 per year
 *  Linda, 55 y.o., works at IBM for $99065 per year
 *  Max, 30 y.o., works at IBM for $54977 per year
 *  Mike, 58 y.o., works at Samsung for $51254 per year
 *  Mike, 57 y.o., works at Apple for $58649 per year
 *  Mike, 54 y.o., works at Siemens for $82384 per year
 *  Tom, 46 y.o., works at Google for $74125 per year
 *  Tom, 50 y.o., works at Siemens for $76816 per year}
 * */