package ru.ifmo.jjd.exercises.e20lambdas.ex02;

import java.util.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class MessageTask {
    public static void main(String[] args) {
        countEachPriority(Message.Generator.generate(randomInt(10, 30)));
        countEachCode(Message.Generator.generate(randomInt(10, 30)));
        uniqueMessageCount(Message.Generator.generate(randomInt(10, 30)));
        uniqueMessagesInOriginalOrder(Message.Generator.generate(randomInt(10, 30)));
        removeEach(Message.Generator.generate(randomInt(10, 30)),
                Message.Priority.getPriority(randomInt(Message.Priority.values().length)));
        removeOther(Message.Generator.generate(randomInt(10,30)),
                Message.Priority.getPriority(randomInt(Message.Priority.values().length)));
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
        TreeMap<Message.Priority, Integer> priorities = new TreeMap<>();
        messageList.forEach(message -> priorities.merge(message.getPriority(), 1, Integer::sum));
        println(priorities);
    }
    /*
     * ---countEachPriority---
     * given list:
     * {Message{code=22, priority=LOW}
     *  Message{code=23, priority=LOW}
     *  Message{code=36, priority=HIGH}
     *  Message{code=14, priority=URGENT}
     *  Message{code=44, priority=URGENT}
     *  Message{code=28, priority=HIGH}
     *  Message{code=43, priority=LOW}
     *  Message{code=32, priority=URGENT}
     *  Message{code=35, priority=URGENT}
     *  Message{code=45, priority=URGENT}
     *  Message{code=33, priority=LOW}
     *  Message{code=12, priority=LOW}
     *  Message{code=27, priority=HIGH}
     *  Message{code=40, priority=HIGH}
     *  Message{code=48, priority=HIGH}
     *  Message{code=20, priority=MEDIUM}
     *  Message{code=42, priority=MEDIUM}
     *  Message{code=33, priority=LOW}
     *  Message{code=31, priority=MEDIUM}
     *  Message{code=12, priority=URGENT}
     *  Message{code=46, priority=HIGH}
     *  Message{code=28, priority=HIGH}
     *  Message{code=44, priority=LOW}
     *  Message{code=41, priority=URGENT}
     *  Message{code=30, priority=MEDIUM}
     *  Message{code=16, priority=URGENT}}
     *
     * contains the following counts of priorities:
     * {LOW=7
     *  MEDIUM=4
     *  HIGH=7
     *  URGENT=8}
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
        TreeMap<Integer, Integer> codes = new TreeMap<>();
        messageList.forEach(message -> codes.merge(message.getCode(), 1, Integer::sum));
        println(codes);
    }
    /*
     * ---countEachCode---
     * given list:
     * {Message{code=14, priority=URGENT}
     *  Message{code=25, priority=HIGH}
     *  Message{code=24, priority=LOW}
     *  Message{code=33, priority=MEDIUM}
     *  Message{code=48, priority=LOW}
     *  Message{code=48, priority=MEDIUM}
     *  Message{code=27, priority=LOW}
     *  Message{code=40, priority=HIGH}
     *  Message{code=47, priority=LOW}
     *  Message{code=46, priority=HIGH}
     *  Message{code=39, priority=MEDIUM}
     *  Message{code=28, priority=URGENT}
     *  Message{code=37, priority=URGENT}
     *  Message{code=47, priority=HIGH}
     *  Message{code=27, priority=URGENT}
     *  Message{code=27, priority=HIGH}
     *  Message{code=47, priority=HIGH}
     *  Message{code=46, priority=LOW}
     *  Message{code=40, priority=HIGH}
     *  Message{code=16, priority=URGENT}
     *  Message{code=25, priority=HIGH}
     *  Message{code=21, priority=LOW}
     *  Message{code=25, priority=HIGH}
     *  Message{code=28, priority=MEDIUM}
     *  Message{code=11, priority=HIGH}
     *  Message{code=31, priority=URGENT}
     *  Message{code=33, priority=HIGH}
     *  Message{code=31, priority=MEDIUM}}
     *
     * contains the following counts of codes:
     * {11=1
     *  14=1
     *  16=1
     *  21=1
     *  24=1
     *  25=3
     *  27=3
     *  28=2
     *  31=2
     *  33=2
     *  37=1
     *  39=1
     *  40=2
     *  46=2
     *  47=3
     *  48=2}
     * */


    /*
     * Подсчитать количество уникальных сообщений
     * Ответ в консоль
     * */
    public static void uniqueMessageCount(List<Message> messageList) {
        if (messageList == null) return;
        println("---uniqueMessageCount---");
        println("given list: ");
        println(messageList);
        Set<Message> uniques = new HashSet<>(messageList);
        println("\ncontains " + uniques.size() + " unique values:");
        println(uniques);
    }
    /*
     * ---uniqueMessageCount---
     * given list:
     * {Message{code=37, priority=MEDIUM}
     *  Message{code=49, priority=URGENT}
     *  Message{code=16, priority=HIGH}
     *  Message{code=47, priority=URGENT}
     *  Message{code=49, priority=HIGH}
     *  Message{code=31, priority=HIGH}
     *  Message{code=12, priority=URGENT}
     *  Message{code=47, priority=URGENT}
     *  Message{code=38, priority=LOW}
     *  Message{code=13, priority=LOW}
     *  Message{code=43, priority=LOW}
     *  Message{code=45, priority=MEDIUM}
     *  Message{code=23, priority=HIGH}
     *  Message{code=33, priority=URGENT}
     *  Message{code=41, priority=MEDIUM}
     *  Message{code=18, priority=LOW}
     *  Message{code=14, priority=MEDIUM}}
     *
     * contains 16 unique values:
     * {Message{code=12, priority=URGENT}
     *  Message{code=45, priority=MEDIUM}
     *  Message{code=14, priority=MEDIUM}
     *  Message{code=49, priority=URGENT}
     *  Message{code=23, priority=HIGH}
     *  Message{code=41, priority=MEDIUM}
     *  Message{code=47, priority=URGENT}
     *  Message{code=38, priority=LOW}
     *  Message{code=37, priority=MEDIUM}
     *  Message{code=43, priority=LOW}
     *  Message{code=31, priority=HIGH}
     *  Message{code=13, priority=LOW}
     *  Message{code=18, priority=LOW}
     *  Message{code=33, priority=URGENT}
     *  Message{code=16, priority=HIGH}
     *  Message{code=49, priority=HIGH}}
     * */


    /*
     * вернуть только неповторяющиеся сообщения и в том порядке, в котором они встретились в первоначальном списке
     * Например, было: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}]
     * на выходе: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}]
     * */
    public static void uniqueMessagesInOriginalOrder(List<Message> messageList) {
        if (messageList == null) return;
        println("---uniqueMessagesInOriginalOrder---");
        println("given list: ");
        println(messageList);
        Set<Message> uniques = new LinkedHashSet<>(messageList);
        println("\ncontains the following unique messages: ");
        println(uniques);
    }
    /*
     * ---uniqueMessagesInOriginalOrder---
     * given list:
     * {Message{code=32, priority=URGENT}
     *  Message{code=14, priority=URGENT}
     *  Message{code=47, priority=MEDIUM}
     *  Message{code=43, priority=HIGH}
     *  Message{code=13, priority=MEDIUM}
     *  Message{code=31, priority=MEDIUM}
     *  Message{code=35, priority=HIGH}
     *  Message{code=12, priority=MEDIUM}
     *  Message{code=32, priority=MEDIUM}
     *  Message{code=14, priority=MEDIUM}
     *  Message{code=44, priority=LOW}
     *  Message{code=17, priority=HIGH}
     *  Message{code=28, priority=HIGH}
     *  Message{code=31, priority=MEDIUM}
     *  Message{code=11, priority=HIGH}
     *  Message{code=10, priority=URGENT}
     *  Message{code=42, priority=LOW}
     *  Message{code=26, priority=URGENT}
     *  Message{code=20, priority=MEDIUM}
     *  Message{code=20, priority=LOW}
     *  Message{code=40, priority=HIGH}
     *  Message{code=40, priority=MEDIUM}
     *  Message{code=11, priority=HIGH}}
     * contains the following unique messages:
     * {Message{code=32, priority=URGENT}
     *  Message{code=14, priority=URGENT}
     *  Message{code=47, priority=MEDIUM}
     *  Message{code=43, priority=HIGH}
     *  Message{code=13, priority=MEDIUM}
     *  Message{code=31, priority=MEDIUM}
     *  Message{code=35, priority=HIGH}
     *  Message{code=12, priority=MEDIUM}
     *  Message{code=32, priority=MEDIUM}
     *  Message{code=14, priority=MEDIUM}
     *  Message{code=44, priority=LOW}
     *  Message{code=17, priority=HIGH}
     *  Message{code=28, priority=HIGH}
     *  Message{code=11, priority=HIGH}
     *  Message{code=10, priority=URGENT}
     *  Message{code=42, priority=LOW}
     *  Message{code=26, priority=URGENT}
     *  Message{code=20, priority=MEDIUM}
     *  Message{code=20, priority=LOW}
     *  Message{code=40, priority=HIGH}
     *  Message{code=40, priority=MEDIUM}}
     * */


    /*
     * удалить из коллекции каждое сообщение с заданным приоритетом
     * вывод в консоль до удаления и после удаления
     * */
    public static void removeEach(List<Message> messageList, Message.Priority priority) {
        if (messageList == null || priority == null) return;
        println("---removeEach---");
        println("From a given list: ");
        println(messageList);
        println("\nremoving all messages with " + priority + " priority: ");
        messageList.removeIf(message -> message.getPriority() == priority);
        println(messageList);
    }
    /*
     * ---removeEach---
     * From a given list:
     * {Message{code=44, priority=MEDIUM}
     *  Message{code=26, priority=URGENT}
     *  Message{code=40, priority=HIGH}
     *  Message{code=12, priority=MEDIUM}
     *  Message{code=37, priority=MEDIUM}
     *  Message{code=10, priority=URGENT}
     *  Message{code=24, priority=LOW}
     *  Message{code=44, priority=HIGH}
     *  Message{code=45, priority=LOW}
     *  Message{code=19, priority=LOW}
     *  Message{code=49, priority=MEDIUM}
     *  Message{code=24, priority=LOW}
     *  Message{code=20, priority=LOW}
     *  Message{code=11, priority=HIGH}
     *  Message{code=12, priority=LOW}
     *  Message{code=48, priority=URGENT}
     *  Message{code=20, priority=HIGH}
     *  Message{code=19, priority=MEDIUM}
     *  Message{code=27, priority=URGENT}}
     *
     * removing all messages with HIGH priority:
     * {Message{code=44, priority=MEDIUM}
     *  Message{code=26, priority=URGENT}
     *  Message{code=12, priority=MEDIUM}
     *  Message{code=37, priority=MEDIUM}
     *  Message{code=10, priority=URGENT}
     *  Message{code=24, priority=LOW}
     *  Message{code=45, priority=LOW}
     *  Message{code=19, priority=LOW}
     *  Message{code=49, priority=MEDIUM}
     *  Message{code=24, priority=LOW}
     *  Message{code=20, priority=LOW}
     *  Message{code=12, priority=LOW}
     *  Message{code=48, priority=URGENT}
     *  Message{code=19, priority=MEDIUM}
     *  Message{code=27, priority=URGENT}}
     * */


    /*
     * Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет
     * вывод в консоль до удаления и после удаления
     * */
    public static void removeOther(List<Message> messageList, Message.Priority priority) {
        if (messageList == null || priority == null) return;
        println("---removeOther---");
        println("With a given list: ");
        println(messageList);
        println("\nretaining only messages with " + priority + " priority: ");
        messageList.removeIf(message -> message.getPriority() != priority);
        println(messageList);
    }
    /*
     * ---removeOther---
     * With a given list:
     * {Message{code=43, priority=LOW}
     *  Message{code=13, priority=URGENT}
     *  Message{code=10, priority=LOW}
     *  Message{code=20, priority=MEDIUM}
     *  Message{code=25, priority=LOW}
     *  Message{code=36, priority=MEDIUM}
     *  Message{code=43, priority=HIGH}
     *  Message{code=20, priority=LOW}
     *  Message{code=11, priority=URGENT}
     *  Message{code=35, priority=MEDIUM}
     *  Message{code=41, priority=MEDIUM}
     *  Message{code=47, priority=LOW}
     *  Message{code=34, priority=URGENT}
     *  Message{code=19, priority=LOW}
     *  Message{code=35, priority=URGENT}
     *  Message{code=37, priority=URGENT}
     *  Message{code=33, priority=HIGH}}
     *
     * retaining only messages with MEDIUM priority:
     * {Message{code=20, priority=MEDIUM}
     *  Message{code=36, priority=MEDIUM}
     *  Message{code=35, priority=MEDIUM}
     *  Message{code=41, priority=MEDIUM}}
     * */
}
