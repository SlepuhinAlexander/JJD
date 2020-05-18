package ru.ifmo.jjd.exercises.e22alarm;

import java.util.ArrayList;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomBoolean;

public class Sensor implements Runnable {
    private final ArrayList<Alarm> alarms = new ArrayList<>();
    private int temp;

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

    public void removeAlarm(Alarm alarm) {
        alarms.remove(alarm);
    }

    private void notifyAlarms() {
        alarms.forEach(alarm -> alarm.tempChanged(temp));
    }

    private void randomTempChange() {
        if (randomBoolean() && randomBoolean()) temp--;
        else temp++;
        notifyAlarms();
    }

    @Override
    public void run() {
        while (temp < 1000) {
            try {
                //noinspection BusyWait
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            randomTempChange();
        }
        println("Malfunction: Sensor is overheated");
    }
}
