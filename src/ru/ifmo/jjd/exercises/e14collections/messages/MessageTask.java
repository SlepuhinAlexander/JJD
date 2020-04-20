package ru.ifmo.jjd.exercises.e14collections.messages;

import java.util.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class MessageTask {
    public static void main(String[] args) {
        countEachPriority(MessageGenerator.generate(randomInt(10, 30)));
        countEachCode(MessageGenerator.generate(randomInt(10, 30)));
        uniqueMessageCount(MessageGenerator.generate(randomInt(10, 30)));
        uniqueMessagesInOriginalOrder(MessageGenerator.generate(randomInt(10, 30)));
        removeEach(MessageGenerator.generate(randomInt(10, 30)),
                MessagePriority.getPriority(randomInt(MessagePriority.values().length)));
        removeOther(MessageGenerator.generate(randomInt(10, 30)),
                MessagePriority.getPriority(randomInt(MessagePriority.values().length)));
    }


    /*
     * Подсчитать количество сообщений для каждого приоритета
     * Ответ в консоль
     * */
    public static void countEachPriority(List<Message> messageList) {
        if (messageList == null) return;
        println("---countEachPriority---");
        println("given list: ");
        println(messageList);
        println("\ncontains the following counts of priorities: ");
        MessagePriority[] priorities = MessagePriority.values();
        int[] counts = new int[priorities.length];
        for (Message message : messageList) {
            counts[message.getPriority().ordinal()]++;
        }
        for (int i = 0; i < priorities.length; i++) {
            println(priorities[i] + " : " + counts[i]);
        }
    }
    /*
     * ---countEachPriority---
     * given list:
     * Message{code=1, priority=MEDIUM}
     * Message{code=1, priority=LOW}
     * Message{code=1, priority=MEDIUM}
     * Message{code=6, priority=MEDIUM}
     * Message{code=9, priority=URGENT}
     * Message{code=4, priority=MEDIUM}
     * Message{code=6, priority=LOW}
     * Message{code=5, priority=HIGH}
     * Message{code=8, priority=HIGH}
     * Message{code=0, priority=MEDIUM}
     * Message{code=8, priority=HIGH}
     *
     * contains the following counts of priorities:
     * LOW : 2
     * MEDIUM : 5
     * HIGH : 3
     * URGENT : 1
     * */


    /*
     * Подсчитать количество сообщений для каждого кода сообщения
     * Ответ в консоль.
     * */
    public static void countEachCode(List<Message> messageList) {
        if (messageList == null) return;
        println("---countEachCode---");
        println("given list: ");
        println(messageList);
        println("\ncontains the following counts of codes: ");
        Set<Integer> codesUnique = new TreeSet<>();
        for (Message message : messageList) {
            codesUnique.add(message.getCode());
        }
        ArrayList<Integer> codes = new ArrayList<>(codesUnique);
        int[] counts = new int[codes.size()];
        for (Message message : messageList) {
            counts[codes.indexOf(message.getCode())]++;
        }
        for (int i = 0; i < codes.size(); i++) {
            println("code " + codes.get(i) + " : " + counts[i]);
        }
    }
    /*
     * ---countEachCode---
     * given list:
     * Message{code=9, priority=LOW}
     * Message{code=7, priority=URGENT}
     * Message{code=3, priority=MEDIUM}
     * Message{code=4, priority=HIGH}
     * Message{code=7, priority=HIGH}
     * Message{code=0, priority=LOW}
     * Message{code=9, priority=MEDIUM}
     * Message{code=8, priority=LOW}
     * Message{code=7, priority=URGENT}
     * Message{code=1, priority=URGENT}
     * Message{code=4, priority=LOW}
     * Message{code=5, priority=URGENT}
     * Message{code=6, priority=URGENT}
     * Message{code=2, priority=HIGH}
     * Message{code=0, priority=MEDIUM}
     * Message{code=0, priority=HIGH}
     * Message{code=2, priority=MEDIUM}
     *
     * contains the following counts of codes:
     * code 0 : 3
     * code 1 : 1
     * code 2 : 2
     * code 3 : 1
     * code 4 : 2
     * code 5 : 1
     * code 6 : 1
     * code 7 : 3
     * code 8 : 1
     * code 9 : 2
     * */


    /*
     * Подсчитать количество уникальных сообщений
     * Ответ в консоль
     * */
    private static void uniqueMessageCount(List<Message> messageList) {
        if (messageList == null) return;
        println("---uniqueMessageCount---");
        println("given list: ");
        println(messageList);
        Set<Message> uniques = new HashSet<>(messageList);
        println("\ncontains " + uniques.size() + " unique messages");
    }
    /*
     * ---uniqueMessageCount---
     * given list:
     * Message{code=4, priority=URGENT}
     * Message{code=0, priority=HIGH}
     * Message{code=4, priority=URGENT}
     * Message{code=6, priority=LOW}
     * Message{code=1, priority=LOW}
     * Message{code=8, priority=LOW}
     * Message{code=2, priority=MEDIUM}
     * Message{code=2, priority=HIGH}
     * Message{code=4, priority=URGENT}
     * Message{code=5, priority=URGENT}
     * Message{code=2, priority=HIGH}
     * Message{code=1, priority=URGENT}
     * Message{code=2, priority=HIGH}
     * Message{code=4, priority=LOW}
     * Message{code=4, priority=HIGH}
     * Message{code=9, priority=URGENT}
     * Message{code=2, priority=HIGH}
     * Message{code=7, priority=MEDIUM}
     * Message{code=2, priority=URGENT}
     * Message{code=0, priority=LOW}
     *
     * contains 15 unique messages
     * */


    /*
     * вернуть только неповторяющиеся сообщения и в том порядке, в котором они встретились в первоначальном списке
     * Например, было: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}]
     * на выходе: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}]
     * */
    public static List<Message> uniqueMessagesInOriginalOrder(List<Message> messageList) {
        if (messageList == null) return null;
        println("---uniqueMessagesInOriginalOrder---");
        println("given list: ");
        println(messageList);
        Set<Message> uniques = new LinkedHashSet<>(messageList);
        println("\ncontains the following unique messages: ");
        println(uniques);
        return new ArrayList<>(uniques);
    }
    /*
     * ---uniqueMessagesInOriginalOrder---
     * given list:
     * Message{code=0, priority=MEDIUM}
     * Message{code=4, priority=MEDIUM}
     * Message{code=7, priority=HIGH}
     * Message{code=1, priority=URGENT}
     * Message{code=1, priority=MEDIUM}
     * Message{code=2, priority=URGENT}
     * Message{code=8, priority=HIGH}
     * Message{code=8, priority=HIGH}
     * Message{code=2, priority=LOW}
     * Message{code=0, priority=HIGH}
     * Message{code=9, priority=LOW}
     * Message{code=8, priority=URGENT}
     * Message{code=9, priority=HIGH}
     * Message{code=8, priority=HIGH}
     * Message{code=9, priority=MEDIUM}
     * Message{code=7, priority=LOW}
     *
     * contains the following unique messages:
     * Message{code=0, priority=MEDIUM}
     * Message{code=4, priority=MEDIUM}
     * Message{code=7, priority=HIGH}
     * Message{code=1, priority=URGENT}
     * Message{code=1, priority=MEDIUM}
     * Message{code=2, priority=URGENT}
     * Message{code=8, priority=HIGH}
     * Message{code=2, priority=LOW}
     * Message{code=0, priority=HIGH}
     * Message{code=9, priority=LOW}
     * Message{code=8, priority=URGENT}
     * Message{code=9, priority=HIGH}
     * Message{code=9, priority=MEDIUM}
     * Message{code=7, priority=LOW}
     * */


    /*
     * удалить из коллекции каждое сообщение с заданным приоритетом
     * вывод в консоль до удаления и после удаления
     * */
    public static void removeEach(List<Message> messageList, MessagePriority priority) {
        if (messageList == null || priority == null) return;
        println("---removeEach---");
        println("From a given list: ");
        println(messageList);
        println("\nremoving all messages with " + priority + " priority: ");
        Iterator<Message> iterator = messageList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getPriority() == priority) {
                iterator.remove();
            }
        }
        println(messageList);
    }
    /*
     * ---removeEach---
     * From a given list:
     * Message{code=2, priority=MEDIUM}
     * Message{code=8, priority=HIGH}
     * Message{code=1, priority=LOW}
     * Message{code=3, priority=MEDIUM}
     * Message{code=7, priority=HIGH}
     * Message{code=4, priority=LOW}
     * Message{code=9, priority=LOW}
     * Message{code=9, priority=LOW}
     * Message{code=8, priority=HIGH}
     * Message{code=5, priority=HIGH}
     * Message{code=6, priority=MEDIUM}
     * Message{code=4, priority=MEDIUM}
     * Message{code=3, priority=LOW}
     * Message{code=3, priority=URGENT}
     * Message{code=3, priority=URGENT}
     * Message{code=8, priority=MEDIUM}
     * Message{code=5, priority=MEDIUM}
     * Message{code=2, priority=MEDIUM}
     *
     * removing all messages with MEDIUM priority:
     * Message{code=8, priority=HIGH}
     * Message{code=1, priority=LOW}
     * Message{code=7, priority=HIGH}
     * Message{code=4, priority=LOW}
     * Message{code=9, priority=LOW}
     * Message{code=9, priority=LOW}
     * Message{code=8, priority=HIGH}
     * Message{code=5, priority=HIGH}
     * Message{code=3, priority=LOW}
     * Message{code=3, priority=URGENT}
     * Message{code=3, priority=URGENT}
     * */


    /*
     * Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет
     * вывод в консоль до удаления и после удаления
     * */
    public static void removeOther(List<Message> messageList, MessagePriority priority) {
        /*
         * definitely can be implemented the same way as removeEach() method, but here is an alternative
         * */
        if (messageList == null || priority == null) return;
        println("---removeOther---");
        println("With a given list: ");
        println(messageList);
        println("\nretaining only messages with " + priority + " priority: ");
        ArrayList<Message> survivors = new ArrayList<>();
        for (Message message : messageList) {
            if (message.getPriority() == priority) {
                survivors.add(message);
            }
        }
        messageList.retainAll(survivors);
        println(messageList);
    }
    /*
     * ---removeOther---
     * With a given list:
     * Message{code=4, priority=MEDIUM}
     * Message{code=7, priority=HIGH}
     * Message{code=7, priority=HIGH}
     * Message{code=9, priority=URGENT}
     * Message{code=9, priority=MEDIUM}
     * Message{code=7, priority=URGENT}
     * Message{code=8, priority=HIGH}
     * Message{code=8, priority=HIGH}
     * Message{code=5, priority=URGENT}
     * Message{code=3, priority=MEDIUM}
     *
     * retaining only messages with URGENT priority:
     * Message{code=9, priority=URGENT}
     * Message{code=7, priority=URGENT}
     * Message{code=5, priority=URGENT}
     * */
}
