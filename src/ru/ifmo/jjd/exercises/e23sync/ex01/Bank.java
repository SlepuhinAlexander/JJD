package ru.ifmo.jjd.exercises.e23sync.ex01;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;

public class Bank {
    private static final int MAX_ATTEMPTS = 10;
    private final Set<Account> accounts = new HashSet<>();
    private final ArrayDeque<Transaction> transactions = new ArrayDeque<>();

    public Bank() {
        new Thread(new Scheduler()).start();
    }

    public void addAccount(Account account) {
        Objects.requireNonNull(account, "Account cannot be null");
        accounts.add(account);
    }

    public void accountStats() {
        println(accounts);
    }

    public void transferMoney(Account src, Account dst, int amount) {
        Objects.requireNonNull(src, "Source account cannot be null");
        Objects.requireNonNull(dst, "Destination account cannot be null");
        if (amount < 0) throw new IllegalArgumentException("Transaction amount cannot be negative");
        synchronized (transactions) {
            transactions.add(new Transaction(src, dst, amount));
        }
    }

    /*
     * Infinite looping Thread starting various bank Processors on some schedule
     * */
    private class Scheduler implements Runnable {
        @Override
        public void run() {
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread transactionProcessorThread = new Thread(new TransactionProcessor());
                transactionProcessorThread.start();
                try {
                    transactionProcessorThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * Processor that executes all currently collected transactions in the transactions queue
     * Checks if the transaction is executable and starts its execution Thread if true.
     * Waits each time after getting through the queue.
     * Stops when the transaction queue is empty.
     * */
    private class TransactionProcessor implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (transactions) {
                    int size = transactions.size();
                    println("Transaction queue size: " + size);
                    if (size == 0) return;
                    final Set<Thread> transactionThreads = new HashSet<>();
                    for (int i = 0; i < size; i++) {
                        Transaction transaction = transactions.poll();
                        //noinspection ConstantConditions
                        if (transaction.isProcessed()) continue;
                        if (transaction.getAttempt() >= MAX_ATTEMPTS) {
                            System.out.println("Failed to process " + transaction);
                            continue;
                        }
                        transactions.add(transaction);
//                        System.out.println("processing transaction " + transaction);
                        transactionThreads.add(new Thread(transaction));
                        Thread execution = new Thread(transaction);
                    }
                    transactionThreads.forEach(Thread::start);
                    for (Thread transactionThread : transactionThreads) {
                        try {
                            transactionThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
