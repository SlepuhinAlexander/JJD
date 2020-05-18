package ru.ifmo.jjd.exercises.e22alarm;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;

public class AlarmTask {
    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        sensor.addAlarm(temp -> println("Temp: " + temp + "\u2103"));
        sensor.addAlarm(new Controller("! GREEN LEVEL ALARM !",100, 300, 600));
        sensor.addAlarm(new Controller("!! YELLOW LEVEL ALARM !!",300, 600));
        sensor.addAlarm(new Controller("!!! RED LEVEL ALARM !!!", 600));
        new Thread(sensor).start();
    }
}

class Controller implements Alarm {
    final String alarmNote;
    final int[] thresholds;
    final boolean[] triggers;
    public Controller(String alarmNote, int... thresholds) {
        this.alarmNote = alarmNote;
        Arrays.sort(thresholds);
        this.thresholds = thresholds;
        triggers = new boolean[thresholds.length];
    }

    @Override
    public void tempChanged(int temp) {
        for (int i = 0; i < thresholds.length; i++) {
            if (!triggers[i] && temp >= thresholds[i]) {
                triggers[i] = true;
                println(alarmNote);
            } else if (triggers[i] && temp < thresholds[i]) {
                triggers[i] = false;
            }
        }
    }
}