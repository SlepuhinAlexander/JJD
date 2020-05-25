package ru.ifmo.jjd.exercises.e24restaurant;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

import static ru.ifmo.jjd.utils.ConsoleHelper.printText;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class RestaurantTask {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        restaurant.addWaiter();
        restaurant.addWaiter();
        restaurant.addCook();
        restaurant.addCook();
        restaurant.addCook();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(randomInt(2000, 10000));
                restaurant.addVisitor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Restaurant {
    final ArrayBlockingQueue<Dish> ordering = new ArrayBlockingQueue<>(10, true);
    final ArrayBlockingQueue<Dish> cooking = new ArrayBlockingQueue<>(10, true);
    final ArrayBlockingQueue<Dish> serving = new ArrayBlockingQueue<>(10, true);

    void addWaiter() {
        new Thread(new Waiter(this)).start();
    }

    void addCook() {
        new Thread(new Cook(this)).start();
    }

    void addVisitor() {
        new Thread(new Visitor(this)).start();
    }
}

class Dish {
    final String name;
    final int price;

    public Dish(String name, int price) {
        this.name = Objects.requireNonNull(name);
        this.price = Math.min(Math.max(0, price), 1000);
    }

    public static Dish getDish() {
        String[] names = {"Steak", "Pizza", "Pancakes", "Sushi", "Pasta", "Hamburger", "Salad", "Pie", "Salmon"};
        return new Dish(names[randomInt(names.length)], randomInt(100, 500));
    }

    @Override
    public String toString() {
        return String.format("%s ($%.02f)", name, price / 100d);
    }
}

class Visitor implements Runnable {
    private final Restaurant restaurant;

    public Visitor(Restaurant restaurant) {
        this.restaurant = Objects.requireNonNull(restaurant, "Restaurant cannot be null");
    }

    @Override
    public void run() {
        println("New visitor arrived");
        try {
            Thread.sleep(randomInt(1000, 3000)); // selecting a dish
            Dish dish = Dish.getDish();
            restaurant.ordering.put(dish);
            println("New order: " + dish);
            dish = restaurant.serving.take();
            printText(dish + " received, thanks");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Waiter implements Runnable {
    private final Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = Objects.requireNonNull(restaurant, "Restaurant cannot be null");
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Dish dish = restaurant.ordering.take();
                println("Order received: " + dish);
                //noinspection BusyWait
                Thread.sleep(randomInt(500, 2000)); // passing order to the kitchen
                restaurant.cooking.put(dish);
                println("Order passed to the kitchen: " + dish);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Cook implements Runnable {
    private final Restaurant restaurant;

    public Cook(Restaurant restaurant) {
        this.restaurant = Objects.requireNonNull(restaurant, "Restaurant cannot be null");
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Dish dish = restaurant.cooking.take();
                println("Cooking: " + dish);
                //noinspection BusyWait
                Thread.sleep(randomInt(2000, 10000)); // cooking
                restaurant.serving.put(dish);
                println("Order complete: " + dish);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

