package ru.ifmo.jjd.exercises.e23sync.ex01;

import java.util.Objects;

public class Transaction implements Runnable {
    private final Account src; // source account
    private final Account dst; // destination account
    private final int amount;
    private int attempt;
    private boolean processed;

    public Transaction(Account src, Account dst, int amount) {
        Objects.requireNonNull(src, "Source account cannot be null");
        Objects.requireNonNull(dst, "Destination account cannot be null");
        if (amount < 0) throw new IllegalArgumentException("Transaction amount cannot be negative");
        this.src = src;
        this.dst = dst;
        this.amount = amount;
    }

    public Account getSrc() {
        return src;
    }

    public Account getDst() {
        return dst;
    }

    public int getAmount() {
        return amount;
    }

    public int getAttempt() {
        return attempt;
    }

    public boolean isProcessed() {
        return processed;
    }

    private void setProcessed() {
        processed = true;
    }

    @Override
    public void run() {
        attempt++;
        synchronized (src) {
            if (src.getBalance() < amount) return;
            src.setBalance(src.getBalance() - amount);
        }
        synchronized (dst) {
            dst.setBalance(dst.getBalance() + amount);
        }
        setProcessed();
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "src=" + src.getId() +
               ", dst=" + dst.getId() +
               ", amount=" + amount +
               ", attempt=" + attempt +
               '}';
    }
}
