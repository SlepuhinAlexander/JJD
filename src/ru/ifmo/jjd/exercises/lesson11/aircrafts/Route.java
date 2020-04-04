package ru.ifmo.jjd.exercises.lesson11.aircrafts;

import java.time.Duration;
import java.util.LinkedList;

public class Route {
    private LinkedList<Flight> flights = new LinkedList<>();
    private LinkedList<Duration> transits = new LinkedList<>();

    private static String formatDuration(Duration duration) {
        StringBuilder builder = new StringBuilder();
        if (duration != null) {
            if (duration.toHours() != 0) {
                builder.append(duration.toHours()).append("h ");
            }
            builder.append(duration.toMinutesPart() / 10).append(duration.toMinutesPart() % 10).append("m");
        } else {
            builder.append("0h 00m");
        }
        return builder.toString();
    }

    public LinkedList<Flight> getFlights() {
        return flights;
    }

    public LinkedList<Duration> getTransits() {
        return transits;
    }

    public void add(Flight flight) {
        if (flight != null) {
            if (flights.isEmpty()) {
                transits.clear();
                flights.add(flight);
            } else if (flights.getLast().arrival.equals(flight.departure) &&
                    flights.getLast().getArrTime().isBefore(flight.getDepTime().minusMinutes(30))) {
                transits.add(Duration.between(flights.getLast().getArrTime(), flight.getDepTime()));
                flights.add(flight);
            } else if (!flights.getLast().arrival.equals(flight.departure)) {
                throw new IllegalArgumentException("Next flight should depart from the airport of last arrival `" +
                        flights.getLast().arrival + "`");
            } else {
                throw new IllegalArgumentException("Next flight should depart " +
                        "no later than 30 min since last arrival '" + flights.getLast().getArrTime() + "'");
            }
        } else {
            throw new NullPointerException("Flight is null");
        }
    }

    public void remove() {
        flights.removeLast();
        transits.removeLast();
    }

    public void clear() {
        flights.clear();
        transits.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (!flights.isEmpty()) {
            builder.append(flights.getFirst());
        }
        for (int i = 1; i < flights.size(); i++) {
            builder.append("\n").append("Transit at ").append(flights.get(i).departure.withLocation()).append(" for ");
            builder.append(formatDuration(transits.get(i - 1))).append("\n");
            builder.append(flights.get(i));
        }
        return builder.toString();
    }

}
