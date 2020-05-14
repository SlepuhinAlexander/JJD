package ru.ifmo.jjd.lessons.l24streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CollectorsDemo {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("qwe", "Apple", 2000, 19));
        employees.add(new Employee("asd", "Yandex", 3000, 38));
        employees.add(new Employee("zxc", "Google", 2500, 49));
        employees.add(new Employee("vbn", "Google", 3000, 22));
        employees.add(new Employee("tyu", "Apple", 1500, 22));

        // Методы класса Collectors
        System.out.println("---Группировка по компании---");
        Map<String, List<Employee>> byCompany = employees.stream()
                // метод groupingBy
                /*
                 * принимает на вход функцию
                 * функция вычисляет признак, который будет служить ключом для мапы
                 * все соответствующие объекты складываются в лист
                 * */
                .collect(Collectors.groupingBy(Employee::getCompany));
        byCompany.entrySet().forEach(System.out::println);
        System.out.println();
        /*
         * ---Группировка по компании---
         * Google=[Employee{name='zxc', company='Google', salary=2500, age=49},
         *      Employee{name='vbn', company='Google', salary=3000, age=22}]
         * Apple=[Employee{name='qwe', company='Apple', salary=2000, age=19},
         *      Employee{name='tyu', company='Apple', salary=1500, age=22}]
         * Yandex=[Employee{name='asd', company='Yandex', salary=3000, age=38}]
         * */

        System.out.println("---Группировка 22 года");
        Map<Boolean, List<Employee>> by22 = employees.stream()
                // метод partitioningBy()
                /*
                 * принимает на вход Predicate
                 * группирует стрим на две группы: удовлетворяющих условию, и не удовлетворяющих условию
                 * */
                .collect(Collectors.partitioningBy(employee -> employee.getAge() == 22));
        by22.entrySet().forEach(System.out::println);
        System.out.println();
        /*
         * ---Группировка 22 года
         * false=[Employee{name='qwe', company='Apple', salary=2000, age=19},
         *      Employee{name='asd', company='Yandex', salary=3000, age=38},
         *      Employee{name='zxc', company='Google', salary=2500, age=49}]
         * true=[Employee{name='vbn', company='Google', salary=3000, age=22},
         *      Employee{name='tyu', company='Apple', salary=1500, age=22}]
         * */

        System.out.println("---названия компаний в алфавитном порядке---");
        List<String> companiesNames = employees.stream()
                .map(Employee::getCompany)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(companiesNames);
        System.out.println();
        /*
         * ---названия компаний в алфавитном порядке---
         * [Apple, Google, Yandex]
         * */

        System.out.println("---название компании - количество сотрудников---");
        Map<String, Long> companyCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompany,
                        /*
                         * второй аргумент в groupingBy определяет куда складывать группу. По умолчанию,
                         * она складывается в лист, но можно указать любой коллектор.
                         * */
                        // метод counting()
                        /*
                         * возвращает коллектор, который считает количество элементов в группе.
                         * */
                        Collectors.counting()));
        companyCount.entrySet().forEach(System.out::println);
        System.out.println();
        /*
         * ---название компании - количество сотрудников---
         * Google=2
         * Apple=2
         * Yandex=1
         * */

        System.out.println("---зп - список сотрудников---");
        Map<Integer, ArrayList<Employee>> salaryMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.toCollection(ArrayList::new)));
        salaryMap.entrySet().forEach(System.out::println);
        System.out.println();
        /*
         * ---зп - список сотрудников---
         * 2000=[Employee{name='qwe', company='Apple', salary=2000, age=19}]
         * 2500=[Employee{name='zxc', company='Google', salary=2500, age=49}]
         * 3000=[Employee{name='asd', company='Yandex', salary=3000, age=38},
         *      Employee{name='vbn', company='Google', salary=3000, age=22}]
         * 1500=[Employee{name='tyu', company='Apple', salary=1500, age=22}]
         * */

        System.out.println("---название компании - средняя зп по компании---");
        Map<String, Double> compAverageSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompany, Collectors.averagingDouble(Employee::getSalary)));
        compAverageSalary.entrySet().forEach(System.out::println);
        System.out.println();
        /*
         * ---название компании - средняя зп по компании---
         * Google=2750.0
         * Apple=1750.0
         * Yandex=3000.0
         * */

        System.out.println("---название компании: зп - список сотрудников---");
        Map<String, Map<Integer, List<Employee>>> companyAndSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompany,
                        Collectors.groupingBy(Employee::getSalary)));
        companyAndSalary.entrySet().forEach(System.out::println);
        System.out.println();
        /*
         * ---название компании: зп - список сотрудников---
         * Google={2500=[Employee{name='zxc', company='Google', salary=2500, age=49}],
         *      3000=[Employee{name='vbn', company='Google', salary=3000, age=22}]}
         * Apple={2000=[Employee{name='qwe', company='Apple', salary=2000, age=19}],
         *      1500=[Employee{name='tyu', company='Apple', salary=1500, age=22}]}
         * Yandex={3000=[Employee{name='asd', company='Yandex', salary=3000, age=38}]}
         *
         * */
    }
}
