package ru.ifmo.jjd.exercises.e15maps.ex03;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class CustomersTask {
    public static void main(String[] args) {
        Map<String, Customer> customers = Generator.generate(randomInt(20, 50));
        println("Given Map:");
        println(customers);
        int lower = randomInt(30), upper = lower + randomInt(50);
        println("\nContains the following customers of ages in [" + lower + ";" + upper + ")");
        println(getCustomers(customers, lower, upper));
    }

    public static Map<String, Customer> getCustomers(Map<String, Customer> customers, int lower, int upper) {
        Map<String, Customer> result = new HashMap<>();
        if (customers == null || upper <= lower) {
            return result;
        }
        Set<Map.Entry<String, Customer>> entries = customers.entrySet();
        for (Map.Entry<String, Customer> entry : entries) {
            int age = entry.getValue().getAge();
            if (age >= lower && age < upper) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    private static class Generator {
        private static final String[] NAMES = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max",
                "Jenifer", "Linda", "Elizabeth", "Oliver", "Harry", "George", "Leo", "Oscar", "Jakob", "Eva"};

        private static Map<String, Customer> generate(int amount) {
            Map<String, Customer> result = new HashMap<>();
            if (amount <= 0) {
                return result;
            }
            Customer customer;
            for (int i = 0; i < amount; i++) {
                customer = new Customer(NAMES[randomInt(NAMES.length)], randomInt(12, 100));
                result.put(customer.getUuid(), customer);
            }
            return result;
        }
    }
}
/*
 * Given Map:
 * c1daf651=Customer{id='c1daf651', name='Jenifer', age=79}
 * 40b99e3c=Customer{id='40b99e3c', name='Jack', age=38}
 * 2b12078d=Customer{id='2b12078d', name='Jack', age=45}
 * fc735077=Customer{id='fc735077', name='Oscar', age=23}
 * d0529b05=Customer{id='d0529b05', name='Leo', age=30}
 * 86662b39=Customer{id='86662b39', name='Harry', age=32}
 * 0553c002=Customer{id='0553c002', name='Elizabeth', age=24}
 * 50d7f200=Customer{id='50d7f200', name='Jakob', age=85}
 * 0f44f7ea=Customer{id='0f44f7ea', name='Leo', age=59}
 * 8578fab4=Customer{id='8578fab4', name='Jack', age=31}
 * 80bdcb96=Customer{id='80bdcb96', name='Oliver', age=75}
 * 5e808c0c=Customer{id='5e808c0c', name='Elizabeth', age=21}
 * b1b7590b=Customer{id='b1b7590b', name='Linda', age=61}
 * 3f72e5c0=Customer{id='3f72e5c0', name='Oliver', age=59}
 * 914b5de4=Customer{id='914b5de4', name='Mike', age=70}
 * a45382b3=Customer{id='a45382b3', name='Linda', age=13}
 * 25b4d283=Customer{id='25b4d283', name='Charlie', age=35}
 * a05d6bd6=Customer{id='a05d6bd6', name='Jenifer', age=17}
 * f6de35c2=Customer{id='f6de35c2', name='Jakob', age=75}
 * 153a70c8=Customer{id='153a70c8', name='Leo', age=15}
 * 91e7a745=Customer{id='91e7a745', name='Peter', age=22}
 * b52a65ac=Customer{id='b52a65ac', name='Tom', age=87}
 * 8a238f56=Customer{id='8a238f56', name='Max', age=62}
 * 06c33d30=Customer{id='06c33d30', name='Oscar', age=40}
 * f043f106=Customer{id='f043f106', name='Linda', age=72}
 * c2f40905=Customer{id='c2f40905', name='Alex', age=79}
 * 5694da83=Customer{id='5694da83', name='George', age=79}
 * 63a30aad=Customer{id='63a30aad', name='John', age=34}
 * b4ae16d8=Customer{id='b4ae16d8', name='George', age=52}
 * ad3bb5d5=Customer{id='ad3bb5d5', name='Leo', age=80}
 * ccda8975=Customer{id='ccda8975', name='Elizabeth', age=19}
 * 303e7a21=Customer{id='303e7a21', name='Max', age=79}
 * 3257d0ea=Customer{id='3257d0ea', name='John', age=88}
 * 838dc24b=Customer{id='838dc24b', name='Jack', age=31}
 * 34dc7d97=Customer{id='34dc7d97', name='Alex', age=58}
 * c7a3d871=Customer{id='c7a3d871', name='Jenifer', age=47}
 * ffa684ed=Customer{id='ffa684ed', name='Tom', age=27}
 * cfe397aa=Customer{id='cfe397aa', name='Elizabeth', age=16}
 * cbee5026=Customer{id='cbee5026', name='Alex', age=67}
 * 8e3d8999=Customer{id='8e3d8999', name='Leo', age=40}
 * 17ecc88b=Customer{id='17ecc88b', name='John', age=19}
 * b09435a2=Customer{id='b09435a2', name='Oscar', age=72}
 * 17befe86=Customer{id='17befe86', name='Jack', age=56}
 * 8119a294=Customer{id='8119a294', name='Mike', age=47}
 * 3134b586=Customer{id='3134b586', name='Jack', age=35}
 * 6e289e80=Customer{id='6e289e80', name='Linda', age=67}
 * fd556cce=Customer{id='fd556cce', name='Linda', age=32}
 * 1ba81938=Customer{id='1ba81938', name='Tom', age=81}
 * a0d0fe3b=Customer{id='a0d0fe3b', name='Peter', age=49}
 *
 * Contains the following customers of ages in [18;41)
 * 8578fab4=Customer{id='8578fab4', name='Jack', age=31}
 * 91e7a745=Customer{id='91e7a745', name='Peter', age=22}
 * 5e808c0c=Customer{id='5e808c0c', name='Elizabeth', age=21}
 * 06c33d30=Customer{id='06c33d30', name='Oscar', age=40}
 * 8e3d8999=Customer{id='8e3d8999', name='Leo', age=40}
 * 17ecc88b=Customer{id='17ecc88b', name='John', age=19}
 * 63a30aad=Customer{id='63a30aad', name='John', age=34}
 * 40b99e3c=Customer{id='40b99e3c', name='Jack', age=38}
 * 3134b586=Customer{id='3134b586', name='Jack', age=35}
 * ccda8975=Customer{id='ccda8975', name='Elizabeth', age=19}
 * fd556cce=Customer{id='fd556cce', name='Linda', age=32}
 * 25b4d283=Customer{id='25b4d283', name='Charlie', age=35}
 * fc735077=Customer{id='fc735077', name='Oscar', age=23}
 * 838dc24b=Customer{id='838dc24b', name='Jack', age=31}
 * d0529b05=Customer{id='d0529b05', name='Leo', age=30}
 * ffa684ed=Customer{id='ffa684ed', name='Tom', age=27}
 * 86662b39=Customer{id='86662b39', name='Harry', age=32}
 * 0553c002=Customer{id='0553c002', name='Elizabeth', age=24}
 * */
