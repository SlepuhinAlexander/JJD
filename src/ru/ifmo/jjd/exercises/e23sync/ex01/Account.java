package ru.ifmo.jjd.exercises.e23sync.ex01;

import java.util.Objects;

public class Account {
    private final int id; // unique id
    private int balance;

    public Account(int id, int balance) {
        if (id < 0) throw new IllegalArgumentException("Malformed id " + id);
        if (balance < 0) throw new IllegalArgumentException("Balance cannot be negative");
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < 0) throw new IllegalArgumentException("Balance cannot be negative");
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", balance=" + balance + '}';
    }
}
