package ru.ifmo.jjd.exercises.e20lambdas.ex05;

import java.util.HashMap;
import java.util.Map;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class CustomersTask {
    public static void main(String[] args) {
        Map<String, Customer> customers = Customer.Generator.generate(randomInt(20, 50));
        println("Given Map:");
        println(customers);
        int lower = randomInt(12, 30), upper = lower + 1 + randomInt(50);
        println("\nContains the following customers of ages in [" + lower + ";" + upper + ")");
        println(getCustomersByAge(customers, lower, upper));
    }

    public static Map<String, Customer> getCustomersByAge(Map<String, Customer> customers, int lower, int upper) {
        if (customers == null) throw new NullPointerException("Customers cannot be null");
        if (upper <= lower) throw new IllegalArgumentException("lower bound should be less than upper bound");
        Map<String, Customer> result = new HashMap<>();
        customers.forEach((id, c) -> {
            if (c.getAge() >= lower && c.getAge() < upper) result.put(id, c);
        });
        return result;
    }
}
/*
 * Given Map:
 * {894d6cba=Customer{uuid='894d6cba', name='Jakob', age=34}
 *  e8fac9ec=Customer{uuid='e8fac9ec', name='Elizabeth', age=61}
 *  d71547d3=Customer{uuid='d71547d3', name='Charlie', age=48}
 *  2383f5ce=Customer{uuid='2383f5ce', name='Jenifer', age=38}
 *  b8ed0c99=Customer{uuid='b8ed0c99', name='John', age=30}
 *  d4ae8ebf=Customer{uuid='d4ae8ebf', name='Jakob', age=57}
 *  bfbcf56f=Customer{uuid='bfbcf56f', name='Tom', age=73}
 *  da11be10=Customer{uuid='da11be10', name='Tom', age=55}
 *  f2977288=Customer{uuid='f2977288', name='Alex', age=75}
 *  16fbe6f2=Customer{uuid='16fbe6f2', name='Mike', age=25}
 *  3fc82991=Customer{uuid='3fc82991', name='Oliver', age=47}
 *  c2ae2270=Customer{uuid='c2ae2270', name='Eva', age=78}
 *  070ce788=Customer{uuid='070ce788', name='Charlie', age=96}
 *  111939c0=Customer{uuid='111939c0', name='Eva', age=75}
 *  cb703d3a=Customer{uuid='cb703d3a', name='Jakob', age=19}
 *  37d69102=Customer{uuid='37d69102', name='George', age=39}
 *  8eaab670=Customer{uuid='8eaab670', name='Leo', age=61}
 *  4bb9c73e=Customer{uuid='4bb9c73e', name='Linda', age=92}
 *  6fd7b775=Customer{uuid='6fd7b775', name='Linda', age=18}
 *  7e6cd61b=Customer{uuid='7e6cd61b', name='Oliver', age=30}
 *  d770aa2b=Customer{uuid='d770aa2b', name='Jakob', age=73}
 *  fd2c496c=Customer{uuid='fd2c496c', name='Linda', age=66}
 *  3b80caab=Customer{uuid='3b80caab', name='Leo', age=37}
 *  dde37942=Customer{uuid='dde37942', name='Max', age=44}
 *  48876c0b=Customer{uuid='48876c0b', name='Mike', age=82}
 *  20c61a9f=Customer{uuid='20c61a9f', name='Peter', age=13}
 *  ac293b7a=Customer{uuid='ac293b7a', name='Linda', age=89}
 *  4dc13bec=Customer{uuid='4dc13bec', name='Eva', age=84}
 *  0747d30e=Customer{uuid='0747d30e', name='Elizabeth', age=18}
 *  58c9f7cf=Customer{uuid='58c9f7cf', name='Eva', age=83}
 *  a9823c7a=Customer{uuid='a9823c7a', name='Charlie', age=53}
 *  1ef86f07=Customer{uuid='1ef86f07', name='Elizabeth', age=26}
 *  ec893371=Customer{uuid='ec893371', name='Linda', age=26}
 *  78c65074=Customer{uuid='78c65074', name='Jakob', age=65}
 *  3ae1dfae=Customer{uuid='3ae1dfae', name='Charlie', age=17}
 *  f11900aa=Customer{uuid='f11900aa', name='Leo', age=96}
 *  3a01e0eb=Customer{uuid='3a01e0eb', name='George', age=33}
 *  205ad7d4=Customer{uuid='205ad7d4', name='George', age=58}
 *  4ec08110=Customer{uuid='4ec08110', name='Max', age=27}
 *  a15f5330=Customer{uuid='a15f5330', name='Tom', age=49}
 *  fdd97369=Customer{uuid='fdd97369', name='Peter', age=62}
 *  c39cbd4d=Customer{uuid='c39cbd4d', name='Elizabeth', age=29}
 *  fff29d9c=Customer{uuid='fff29d9c', name='George', age=30}
 *  255ee918=Customer{uuid='255ee918', name='Elizabeth', age=72}
 *  9193377a=Customer{uuid='9193377a', name='Jack', age=87}}
 *
 * Contains the following customers of ages in [18;46)
 * {894d6cba=Customer{uuid='894d6cba', name='Jakob', age=34}
 *  6fd7b775=Customer{uuid='6fd7b775', name='Linda', age=18}
 *  7e6cd61b=Customer{uuid='7e6cd61b', name='Oliver', age=30}
 *  3b80caab=Customer{uuid='3b80caab', name='Leo', age=37}
 *  2383f5ce=Customer{uuid='2383f5ce', name='Jenifer', age=38}
 *  dde37942=Customer{uuid='dde37942', name='Max', age=44}
 *  b8ed0c99=Customer{uuid='b8ed0c99', name='John', age=30}
 *  0747d30e=Customer{uuid='0747d30e', name='Elizabeth', age=18}
 *  1ef86f07=Customer{uuid='1ef86f07', name='Elizabeth', age=26}
 *  16fbe6f2=Customer{uuid='16fbe6f2', name='Mike', age=25}
 *  ec893371=Customer{uuid='ec893371', name='Linda', age=26}
 *  3a01e0eb=Customer{uuid='3a01e0eb', name='George', age=33}
 *  4ec08110=Customer{uuid='4ec08110', name='Max', age=27}
 *  cb703d3a=Customer{uuid='cb703d3a', name='Jakob', age=19}
 *  37d69102=Customer{uuid='37d69102', name='George', age=39}
 *  c39cbd4d=Customer{uuid='c39cbd4d', name='Elizabeth', age=29}
 *  fff29d9c=Customer{uuid='fff29d9c', name='George', age=30}}
 * */
