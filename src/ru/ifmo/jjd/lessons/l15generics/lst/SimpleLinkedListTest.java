package ru.ifmo.jjd.lessons.l15generics.lst;

public class SimpleLinkedListTest {
    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(23);
        list.add(12);

        System.out.println(list.get(1));

        SimpleLinkedList<String> stringList = new SimpleLinkedList<>();
        stringList.add("Hello");
        stringList.add("World!");

        System.out.println(stringList.get(0) + " " + stringList.get(1));
    }
}
