package ru.ifmo.jjd.exercises.e23sync.ex01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class BankTask {
    public static void main(String[] args) {
        Bank bank = new Bank();
        // worst case when two accs are tossing all their money
        Account a1 = new Account(0, 1000);
        Account a2 = new Account(1, 1000);
        bank.addAccount(a1);
        bank.addAccount(a2);
        for (int i = 0; i < 1000; i++) {
            bank.transferMoney(a1, a2, 1000);
            bank.transferMoney(a2, a1, 1000);
        }
/*
        ArrayList<Account> accounts = new ArrayList<>(AccountGenerator.generate(5));
        accounts.forEach(bank::addAccount);
        bank.accountStats();
        for (int i = 0; i < 10000; i++) {
            int srcInd = randomInt(accounts.size());
            int dstInd = (srcInd + randomInt(1, accounts.size())) % accounts.size();
            bank.transferMoney(accounts.get(srcInd), accounts.get(dstInd), randomInt(1,100));
        }
*/
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bank.accountStats();
    }

    static class AccountGenerator {
        private static int idCounter = randomInt(Integer.MAX_VALUE - 1_000_000);

        public static List<Account> generate(int amount) {
            if (amount < 0) return Collections.emptyList();
            List<Account> accounts = new ArrayList<>(amount);
            for (int i = 0; i < amount; i++) {
                accounts.add(new Account(idCounter++, randomInt(1, 10) * 1000));
            }
            return accounts;
        }
    }
}
