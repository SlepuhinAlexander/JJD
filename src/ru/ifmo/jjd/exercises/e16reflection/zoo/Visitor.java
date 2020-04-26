package ru.ifmo.jjd.exercises.e16reflection.zoo;

public class Visitor extends Person{
    public Visitor(String name, int age) {
        super(name, age);
    }

    public void buyTicket(Zoo zoo) {
        if (zoo == null) throw  new NullPointerException();
        System.out.printf("%s buys a ticket for $%.2f", name, zoo.getTicketPrice());
        zoo.addVisitor(this);
    }
}
