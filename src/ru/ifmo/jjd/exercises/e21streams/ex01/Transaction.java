package ru.ifmo.jjd.exercises.e21streams.ex01;

import java.util.*;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class Transaction {
    private final String uuid;
    private final Account account;
    private final long sum;

    public Transaction(final Account account, final long sum) {
        if (account == null) throw new NullPointerException("account cannot be null");
        this.uuid = UUID.randomUUID().toString();
        this.account = account;
        this.sum = sum;
    }

    public String getUuid() {
        return uuid;
    }

    public long getSum() {
        return sum;
    }

    public Account getAccount() {
        return account;
    }

    public void apply() {
        account.update(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Transaction{" + "uuid='" + uuid + '\'' + ", account=" + account + ", sum=" + sum + '}';
    }

    static class Generator {
        public static List<Transaction> generate(int amount, List<Account> accounts) {
            if (amount <= 0) return Collections.emptyList();
            if (accounts == null) throw new NullPointerException("accounts list cannot be null");
            if (accounts.size() == 0) {
                throw new IllegalArgumentException("not enough accounts to generate transactions");
            }
            ArrayList<Account> acc = new ArrayList<>(accounts);
            List<Transaction> transactions = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                transactions.add(new Transaction(acc.get(randomInt(acc.size())),randomInt(-1000, 1000)));
            }
            return transactions;
        }
    }
}
