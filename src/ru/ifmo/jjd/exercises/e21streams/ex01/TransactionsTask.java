package ru.ifmo.jjd.exercises.e21streams.ex01;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.ifmo.jjd.utils.ConsoleHelper.print;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class TransactionsTask {
    public static void main(String[] args) {
        List<Transaction> transactions = Transaction.Generator.generate(randomInt(20, 50),
                Account.Generator.generate(randomInt(3, 10)));
        println("In the following transactions:");
        println(transactions);
        println("\naccounts have the following sub-totals:");
        println(calcTransSumPerAcc(transactions), "Account:'%k' Sum:%v");
        print("\nAnd the total sum for all accounts is: ");
        println(calcTransSum(transactions));

    }

    public static Map<String, Long> calcTransSumPerAcc(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(t -> t.getAccount().getNumber(),
                        Collectors.summingLong(Transaction::getSum)));
    }

    public static long calcTransSum(List<Transaction> transactions) {
        return transactions.stream().
                map(Transaction::getSum).
                reduce(0L, Long::sum);
    }
}
/*
 * In the following transactions:
 * {Transaction{uuid='51f72cc5-30fd-4f27-8166-5a1fc4d8b808', account=Account{number='43A8E429', balance=7000}, sum=767}
 *  Transaction{uuid='8f9a3111-f5a6-44f0-a9ce-fa1f91d130b9', account=Account{number='6289232B', balance=8000}, sum=315}
 *  Transaction{uuid='265b9e54-32de-4ba3-b45c-71a8b34ff4ca', account=Account{number='6289232B', balance=8000}, sum=-524}
 *  Transaction{uuid='467396a7-001e-40c1-85d6-bf7cb97024a9', account=Account{number='DC222AE7', balance=9000}, sum=304}
 *  Transaction{uuid='ba11fd45-24bb-4b85-9cfc-864d3bf61e27', account=Account{number='6289232B', balance=8000}, sum=587}
 *  Transaction{uuid='a93979bf-347e-42ae-80f6-b9fe1eac1662', account=Account{number='6289232B', balance=8000}, sum=-558}
 *  Transaction{uuid='18d2cc83-06fc-40b2-b31d-0a9fecd75af2', account=Account{number='1A93C60B', balance=1000}, sum=-197}
 *  Transaction{uuid='dcbd11c5-2ae9-45d5-9b9d-9525daced79e', account=Account{number='6289232B', balance=8000}, sum=-998}
 *  Transaction{uuid='3d16260c-2349-40c6-9fca-d9f7dcf924d8', account=Account{number='27B2F232', balance=8000}, sum=-604}
 *  Transaction{uuid='c431f2c7-6d61-4482-9970-e21aaf4ff28b', account=Account{number='43A8E429', balance=7000}, sum=-520}
 *  Transaction{uuid='95cfebf9-a747-43f1-8928-0e48c58ac178', account=Account{number='1A93C60B', balance=1000}, sum=822}
 *  Transaction{uuid='68a48bc2-53f2-46c3-9022-8efae1f33a9c', account=Account{number='43A8E429', balance=7000}, sum=814}
 *  Transaction{uuid='ed807912-9b2d-4f44-8c26-51cd0a72050d', account=Account{number='B0E06CFE', balance=7000}, sum=733}
 *  Transaction{uuid='8ab9659a-9244-455a-abca-025370c1aa9c', account=Account{number='43A8E429', balance=7000}, sum=-228}
 *  Transaction{uuid='3fc1a732-0674-4107-aa57-d3dace7bf38f', account=Account{number='E26C7080', balance=3000}, sum=616}
 *  Transaction{uuid='81b01189-6131-41f0-9006-3f3324912508', account=Account{number='E26C7080', balance=3000}, sum=-429}
 *  Transaction{uuid='3c8b276c-831a-425c-a7a6-67e57ca4625a', account=Account{number='43A8E429', balance=7000}, sum=-572}
 *  Transaction{uuid='780a73b4-0246-4c05-8f1c-a500e05a99aa', account=Account{number='DC222AE7', balance=9000}, sum=-392}
 *  Transaction{uuid='2237cfde-237b-4426-82bf-1e2381038bc0', account=Account{number='6289232B', balance=8000}, sum=138}
 *  Transaction{uuid='1c33f1f0-2285-423f-b5f0-e53882cd6ca2', account=Account{number='DC222AE7', balance=9000}, sum=-248}
 *  Transaction{uuid='6bba4d69-e193-4efb-b586-6699dfa8218a', account=Account{number='DC222AE7', balance=9000}, sum=-638}
 *  Transaction{uuid='dcdb8c75-7f7d-4209-b36f-51606700e833', account=Account{number='E26C7080', balance=3000}, sum=-990}
 *  Transaction{uuid='961e7c11-51dc-4282-b6ee-7095b0e73f8e', account=Account{number='1A93C60B', balance=1000}, sum=-381}
 *  Transaction{uuid='0d5fa348-ae22-447b-aa74-c66759c0cb5d', account=Account{number='E26C7080', balance=3000}, sum=710}
 *  Transaction{uuid='857e197c-dab9-49ee-b775-d658b4922f83', account=Account{number='43A8E429', balance=7000}, sum=584}
 *  Transaction{uuid='8c4257ba-61ed-43b2-86f9-1056a5b06dd4', account=Account{number='E26C7080', balance=3000}, sum=696}}
 *
 * accounts have the following sub-totals:
 * {Account:'B0E06CFE' Sum:733
 *  Account:'27B2F232' Sum:-604
 *  Account:'E26C7080' Sum:603
 *  Account:'43A8E429' Sum:845
 *  Account:'DC222AE7' Sum:-974
 *  Account:'1A93C60B' Sum:244
 *  Account:'6289232B' Sum:-1040}
 *
 * And the total sum for all accounts is: -193
 * */
