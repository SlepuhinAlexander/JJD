package ru.ifmo.jjd.lessons.l13enumsgc;

public class TstMessage {

    public static void main(String[] args) {
        String data = "Срочное сообщение";
        int num = 10;

        printStart();

        Message message = new Message();
        message.setText(data);
        message.setCode(num);

    }

    private static void printStart(){
        System.out.println("START");
    }
}