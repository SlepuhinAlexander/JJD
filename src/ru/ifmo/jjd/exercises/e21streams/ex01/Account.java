package ru.ifmo.jjd.exercises.e21streams.ex01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class Account {
    private final String number;
    private long balance;

    public Account(String number, long balance) {
        if (number == null) throw new NullPointerException("Account number cannot be null");
        String numberNorm = normalize(number, "[^A-F0-9]+");
        if (numberNorm.length() != 8) throw new IllegalArgumentException("Malformed account number '" + number + "'");
        if (balance < 0) throw new IllegalArgumentException("Initial balance cannot be negative");
        this.number = numberNorm;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public long getBalance() {
        return balance;
    }

    public void update(long delta) {
        this.balance += delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return number.equals(account.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Account{" + "number='" + number + '\'' + ", balance=" + balance + '}';
    }

    static class Generator {
        public static List<Account> generate(int amount) {
            if (amount <= 0) return Collections.emptyList();
            List<Account> accounts = new ArrayList<>(amount);
            String number;
            for (int i = 0; i < amount; i++) {
                number = Integer.toHexString(randomInt()).toUpperCase();
                number = "0".repeat(8 - number.length()).concat(number);
                accounts.add(new Account(number, randomInt(1, 10) * 1_000));
            }
            return accounts;
        }
    }
}
