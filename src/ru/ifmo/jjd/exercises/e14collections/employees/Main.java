package ru.ifmo.jjd.exercises.e14collections.employees;

import java.util.List;

import static ru.ifmo.jjd.utils.ConsoleHelper.print;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Employee.employeeGenerator(randomInt(10, 30));
        print("""
                =======================================
                Created employees list (before sorting)
                =======================================
                """);
        println(employees);

        employees.sort(new EmployeeNameComparator());
        print("""
                =======================================
                Employee list sorted by name (asc)
                ======================================= 
                """);
        println(employees);

        employees.sort(new EmployeeNameComparator().thenComparing(new EmployeeSalaryComparator()));
        print("""
                ===================================================
                Employee list sorted by name (asc) and salary (asc)
                =================================================== 
                """);
        println(employees);

        employees.sort(new EmployeeNameComparator().thenComparing(new EmployeeSalaryComparator()).
                thenComparing(new EmployeeAgeComparator()).thenComparing(new EmployeeCompanyComparator()));
        print("""
                =============================================================================
                Employee list sorted by name (asc), salary (asc), age (asc) and company (asc)
                =============================================================================
                """);
        println(employees);
    }
}

/*
 * =======================================
 * Created employees list (before sorting)
 * =======================================
 * John, 24 y.o., works at General Electric for $60550 per year
 * Linda, 57 y.o., works at Google for $78543 per year
 * Linda, 23 y.o., works at IBM for $50366 per year
 * Alex, 46 y.o., works at Siemens for $63330 per year
 * Peter, 21 y.o., works at Google for $47456 per year
 * Mike, 41 y.o., works at Siemens for $60874 per year
 * Jack, 48 y.o., works at Microsoft for $29256 per year
 * Jenifer, 58 y.o., works at Microsoft for $79290 per year
 * Mike, 36 y.o., works at Samsung for $82137 per year
 * Alex, 47 y.o., works at Siemens for $80986 per year
 * Jack, 21 y.o., works at Samsung for $68362 per year
 * Peter, 28 y.o., works at Samsung for $72285 per year
 * Peter, 42 y.o., works at Samsung for $95336 per year
 * Charlie, 32 y.o., works at Siemens for $91054 per year
 * Alex, 44 y.o., works at Google for $30953 per year
 * Tom, 30 y.o., works at IBM for $94952 per year
 * Elizabeth, 52 y.o., works at Apple for $77460 per year
 * Jack, 33 y.o., works at IBM for $72977 per year
 * Linda, 48 y.o., works at General Electric for $80794 per year
 * Alex, 38 y.o., works at Samsung for $25083 per year
 * Charlie, 45 y.o., works at General Electric for $28442 per year
 * Linda, 45 y.o., works at Samsung for $67631 per year
 * Charlie, 30 y.o., works at Google for $74605 per year
 * =======================================
 * Employee list sorted by name (asc)
 * =======================================
 * Alex, 46 y.o., works at Siemens for $63330 per year
 * Alex, 47 y.o., works at Siemens for $80986 per year
 * Alex, 44 y.o., works at Google for $30953 per year
 * Alex, 38 y.o., works at Samsung for $25083 per year
 * Charlie, 32 y.o., works at Siemens for $91054 per year
 * Charlie, 45 y.o., works at General Electric for $28442 per year
 * Charlie, 30 y.o., works at Google for $74605 per year
 * Elizabeth, 52 y.o., works at Apple for $77460 per year
 * Jack, 48 y.o., works at Microsoft for $29256 per year
 * Jack, 21 y.o., works at Samsung for $68362 per year
 * Jack, 33 y.o., works at IBM for $72977 per year
 * Jenifer, 58 y.o., works at Microsoft for $79290 per year
 * John, 24 y.o., works at General Electric for $60550 per year
 * Linda, 57 y.o., works at Google for $78543 per year
 * Linda, 23 y.o., works at IBM for $50366 per year
 * Linda, 48 y.o., works at General Electric for $80794 per year
 * Linda, 45 y.o., works at Samsung for $67631 per year
 * Mike, 41 y.o., works at Siemens for $60874 per year
 * Mike, 36 y.o., works at Samsung for $82137 per year
 * Peter, 21 y.o., works at Google for $47456 per year
 * Peter, 28 y.o., works at Samsung for $72285 per year
 * Peter, 42 y.o., works at Samsung for $95336 per year
 * Tom, 30 y.o., works at IBM for $94952 per year
 * ===================================================
 * Employee list sorted by name (asc) and salary (asc)
 * ===================================================
 * Alex, 38 y.o., works at Samsung for $25083 per year
 * Alex, 44 y.o., works at Google for $30953 per year
 * Alex, 46 y.o., works at Siemens for $63330 per year
 * Alex, 47 y.o., works at Siemens for $80986 per year
 * Charlie, 45 y.o., works at General Electric for $28442 per year
 * Charlie, 30 y.o., works at Google for $74605 per year
 * Charlie, 32 y.o., works at Siemens for $91054 per year
 * Elizabeth, 52 y.o., works at Apple for $77460 per year
 * Jack, 48 y.o., works at Microsoft for $29256 per year
 * Jack, 21 y.o., works at Samsung for $68362 per year
 * Jack, 33 y.o., works at IBM for $72977 per year
 * Jenifer, 58 y.o., works at Microsoft for $79290 per year
 * John, 24 y.o., works at General Electric for $60550 per year
 * Linda, 23 y.o., works at IBM for $50366 per year
 * Linda, 45 y.o., works at Samsung for $67631 per year
 * Linda, 57 y.o., works at Google for $78543 per year
 * Linda, 48 y.o., works at General Electric for $80794 per year
 * Mike, 41 y.o., works at Siemens for $60874 per year
 * Mike, 36 y.o., works at Samsung for $82137 per year
 * Peter, 21 y.o., works at Google for $47456 per year
 * Peter, 28 y.o., works at Samsung for $72285 per year
 * Peter, 42 y.o., works at Samsung for $95336 per year
 * Tom, 30 y.o., works at IBM for $94952 per year
 * =============================================================================
 * Employee list sorted by name (asc), salary (asc), age (asc) and company (asc)
 * =============================================================================
 * Alex, 38 y.o., works at Samsung for $25083 per year
 * Alex, 44 y.o., works at Google for $30953 per year
 * Alex, 46 y.o., works at Siemens for $63330 per year
 * Alex, 47 y.o., works at Siemens for $80986 per year
 * Charlie, 45 y.o., works at General Electric for $28442 per year
 * Charlie, 30 y.o., works at Google for $74605 per year
 * Charlie, 32 y.o., works at Siemens for $91054 per year
 * Elizabeth, 52 y.o., works at Apple for $77460 per year
 * Jack, 48 y.o., works at Microsoft for $29256 per year
 * Jack, 21 y.o., works at Samsung for $68362 per year
 * Jack, 33 y.o., works at IBM for $72977 per year
 * Jenifer, 58 y.o., works at Microsoft for $79290 per year
 * John, 24 y.o., works at General Electric for $60550 per year
 * Linda, 23 y.o., works at IBM for $50366 per year
 * Linda, 45 y.o., works at Samsung for $67631 per year
 * Linda, 57 y.o., works at Google for $78543 per year
 * Linda, 48 y.o., works at General Electric for $80794 per year
 * Mike, 41 y.o., works at Siemens for $60874 per year
 * Mike, 36 y.o., works at Samsung for $82137 per year
 * Peter, 21 y.o., works at Google for $47456 per year
 * Peter, 28 y.o., works at Samsung for $72285 per year
 * Peter, 42 y.o., works at Samsung for $95336 per year
 * Tom, 30 y.o., works at IBM for $94952 per year
 * */
