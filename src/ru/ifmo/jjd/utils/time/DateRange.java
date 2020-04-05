package ru.ifmo.jjd.utils.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;

public class DateRange {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");

    private LocalDate from;
    private LocalDate to;

    public DateRange(LocalDate from, LocalDate to) {
        set(from, to);
    }

    public DateRange(int yearFrom, Month monthFrom, int dayFrom, int yearTo, Month monthTo, int dayTo) {
        set(yearFrom, monthFrom, dayFrom, yearTo, monthTo, dayTo);
    }

    public DateRange(int yearFrom, int monthFrom, int dayFrom, int yearTo, int monthTo, int dayTo) {
        set(yearFrom, monthFrom, dayFrom, yearTo, monthTo, dayTo);
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        try {
            set(from, to);
        } catch (IllegalArgumentException | NullPointerException e) {
            println("Failed to set From date '" + from.format(FORMAT) + "': " + e.getMessage());
        }
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        try {
            set(from, to);
        } catch (IllegalArgumentException | NullPointerException e) {
            println("Failed to set To date '" + to.format(FORMAT) + "': " + e.getMessage());
        }
    }

    public void set(LocalDate from, LocalDate to) {
        if (from != null && to != null && !from.isAfter(to)) {
            this.from = from;
            this.to = to;
        } else if (from == null) {
            throw new NullPointerException("From date is null");
        } else if (to == null) {
            throw new NullPointerException("To date is null");
        } else {
            throw new IllegalArgumentException("From date '" + from.format(FORMAT) +
                    "' cannot be after To date '" + to.format(FORMAT) + "'");
        }
    }

    public void set(int yearFrom, Month monthFrom, int dayFrom, int yearTo, Month monthTo, int dayTo) {
        set(LocalDate.of(yearFrom, monthFrom, dayFrom), LocalDate.of(yearTo, monthTo, dayTo));
    }

    public void set(int yearFrom, int monthFrom, int dayFrom, int yearTo, int monthTo, int dayTo) {
        set(LocalDate.of(yearFrom, monthFrom, dayFrom), LocalDate.of(yearTo, monthTo, dayTo));
    }

    public void setFrom(int year, Month month, int day) {
        setFrom(LocalDate.of(year, month, day));
    }

    public void setFrom(int year, int month, int day) {
        setFrom(LocalDate.of(year, month, day));
    }

    public void setTo(int year, Month month, int day) {
        setTo(LocalDate.of(year, month, day));
    }

    public void setTo(int year, int month, int day) {
        setTo(LocalDate.of(year, month, day));
    }

    public boolean contains(LocalDate date) {
        return !from.isAfter(date) && !to.isBefore(date);
    }

    public boolean contains(LocalDateTime dateTime) {
        return contains(dateTime.toLocalDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateRange)) return false;
        DateRange dateRange = (DateRange) o;
        return from.equals(dateRange.from) &&
                to.equals(dateRange.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "c " + from.format(FORMAT) + " по " + to.format(FORMAT);
    }
}
