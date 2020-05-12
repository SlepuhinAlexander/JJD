package ru.ifmo.jjd.utils.time;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;

public class TimeRange {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("H:mm");

    private LocalTime from;
    private LocalTime to;

    public TimeRange(LocalTime from, LocalTime to) {
        set(from, to);
    }

    public TimeRange(int fromHour, int fromMinute, int toHour, int toMinute) {
        set(fromHour, fromMinute, toHour, toMinute);
    }

    public static LocalTime trim(LocalTime t) {
        return t == null ? null : t.withSecond(0).withNano(0);
    }

    private static LocalTime min(LocalTime one, LocalTime two) {
        return (one == null || two == null) ? null : trim(one).isBefore(trim(two)) ? trim(one) : trim(two);
    }

    private static LocalTime max(LocalTime one, LocalTime two) {
        return (one == null || two == null) ? null : trim(one).isBefore(trim(two)) ? trim(two) : trim(one);
    }

    private static int hash(LocalTime t) {
        long nanos = ((t.getHour() * 60) + t.getMinute()) * 60_000_000_000L;
        return (int) (nanos ^ (nanos >>> 32));
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        try {
            set(from, to);
        } catch (IllegalArgumentException | NullPointerException e) {
            println("Failed to set From time '" + from.format(FORMAT) + "': " + e.getMessage());
        }
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        try {
            set(from, to);
        } catch (IllegalArgumentException | NullPointerException e) {
            println("Failed to set To time '" + to.format(FORMAT) + "': " + e.getMessage());
        }
    }

    public void set(LocalTime from, LocalTime to) {
        if (from == null) throw new NullPointerException("From date cannot be null");
        if (to == null) throw new NullPointerException("to date cannot be null");
        LocalTime f = trim(from);
        LocalTime t = trim(to);
        if (!f.isBefore(t)) throw new IllegalArgumentException("From time `" + f.format(FORMAT) +
                                                               "' must be earlier than To time '" + t.format(FORMAT) +
                                                               "'");
        this.from = f;
        this.to = t;
    }

    public void set(int fromHour, int fromMinute, int toHour, int toMinute) {
        set(LocalTime.of(fromHour, fromMinute), LocalTime.of(toHour, toMinute));
    }

    public void setFrom(int hour, int minute) {
        setFrom(LocalTime.of(hour, minute));
    }

    public void setTo(int hour, int minute) {
        setTo(LocalTime.of(hour, minute));
    }

    public TimeRange intersect(TimeRange another) {
        try {
            return new TimeRange(max(this.from, another.from), min(this.to, another.to));
        } catch (NullPointerException | IllegalArgumentException e) {
            println("Failed to intersect: " + e.getMessage());
            return this;
        }
    }

    public boolean contains(LocalTime time) {
        LocalTime t = trim(time);
        return !from.isAfter(t) && !to.isBefore(t);
    }

    public boolean contains(LocalDateTime dateTime) {
        return contains(dateTime.toLocalTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeRange)) return false;
        TimeRange timeRange = (TimeRange) o;
        return from.getHour() == timeRange.from.getHour() && from.getMinute() == timeRange.from.getMinute() &&
               to.getHour() == timeRange.to.getHour() && to.getMinute() == timeRange.to.getMinute();
    }

    @Override
    public int hashCode() {
        return 31 * hash(from) + hash(to);
    }

    @Override
    public String toString() {
        return "с " + from.format(FORMAT) + " по " + to.format(FORMAT);
    }
}
