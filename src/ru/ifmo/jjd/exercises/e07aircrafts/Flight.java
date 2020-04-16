package ru.ifmo.jjd.exercises.e07aircrafts;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.ifmo.jjd.utils.StringHelper.normalizeLatin;

public class Flight {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(" (HH:mm MMM d, eee)",
            Locale.ENGLISH);

    public final String code;
    public final Airport departure;
    public final Airport arrival;
    private ZonedDateTime depTime;
    private ZonedDateTime arrTime;
    private Duration duration;

    public Flight(String code, Airport departure, Airport arrival) {
        String normalizedCode = normalizeLatin(code);
        if (!normalizedCode.isBlank() && departure != null && arrival != null && !departure.equals(arrival)) {
            this.code = normalizedCode;
            this.departure = departure;
            this.arrival = arrival;
        } else if (normalizedCode.isBlank()) {
            throw new IllegalArgumentException("Flight code '" + code + "' is invalid or too short");
        } else if (departure == null) {
            throw new NullPointerException("Departure airport is null");
        } else if (arrival == null) {
            throw new NullPointerException("Arrival airport is null");
        } else {
            throw new IllegalArgumentException("Arrival and departure airports cannot be the same");
        }
        depTime = departure.city.now();
        arrTime = arrival.city.now();
        duration = Duration.ZERO;
    }

    public Flight(String code, Airport departure, Airport arrival, ZonedDateTime depTime, ZonedDateTime arrTime) {
        this(code, departure, arrival);
        setFlightTimes(depTime, arrTime);
    }

    public Flight(String code, Airport departure, Airport arrival, LocalDateTime depTime, LocalDateTime arrTime) {
        this(code, departure, arrival);
        setFlightTimes(depTime, arrTime);
    }

    public Flight(String code, Airport departure, Airport arrival, ZonedDateTime depTime, int hours, int minutes) {
        this(code, departure, arrival);
        setDepartureTime(depTime, hours, minutes);
    }

    public Flight(String code, Airport departure, Airport arrival, LocalDateTime depTime, int hours, int minutes) {
        this(code, departure, arrival);
        setDepartureTime(depTime, hours, minutes);
    }

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

    public ZonedDateTime getDepTime() {
        return depTime;
    }

    public ZonedDateTime getArrTime() {
        return arrTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setFlightTimes(ZonedDateTime depTime, ZonedDateTime arrTime) {
        if (depTime != null && arrTime != null) {
            ZonedDateTime depTimeZ = depTime.withZoneSameInstant(departure.city.zone);
            ZonedDateTime arrTimeZ = arrTime.withZoneSameInstant(arrival.city.zone);
            if (depTimeZ.isAfter(departure.now()) && arrTimeZ.isAfter(arrival.now()) && arrTimeZ.isAfter(depTimeZ)) {
                this.depTime = depTimeZ;
                this.arrTime = arrTimeZ;
                duration = Duration.between(depTimeZ, arrTimeZ);
            } else if (!depTime.isAfter(departure.now())) {
                throw new IllegalArgumentException("Departure time '" + depTime + "' cannot be in the past");
            } else if (!arrTime.isAfter(arrival.now())) {
                throw new IllegalArgumentException("Arrival time '" + arrTime + "' cannot be in the past");
            } else {
                throw new IllegalArgumentException("Arrival time '" + arrTime +
                        "' cannot be before departure time '" + depTime + "'");
            }
        } else if (depTime == null) {
            throw new NullPointerException("Departure time is null");
        } else {
            throw new NullPointerException("Arrival time is null");
        }
    }

    public void setFlightTimes(LocalDateTime depTime, LocalDateTime arrTime) {
        setFlightTimes(depTime.atZone(departure.city.zone), arrTime.atZone(arrival.city.zone));
    }

    public void setDepartureTime(ZonedDateTime depTime, int hours, int minutes) {
        setFlightTimes(depTime,
                (ZonedDateTime) Duration.ofHours(hours).plusMinutes(minutes).addTo(arrival.toLocalZone(depTime)));
    }

    public void setDepartureTime(LocalDateTime depTime, int hours, int minutes) {
        ZonedDateTime depTimeZ = depTime.atZone(departure.city.zone);
        setFlightTimes(depTimeZ,
                (ZonedDateTime) Duration.ofHours(hours).plusMinutes(minutes).addTo(arrival.toLocalZone(depTimeZ)));
    }

    public void setDepartureTime(ZonedDateTime depTime) {
        setFlightTimes(depTime, (ZonedDateTime) duration.addTo(arrival.toLocalZone(depTime)));
    }

    public void setDepartureTime(LocalDateTime depTime) {
        setDepartureTime(depTime.atZone(departure.city.zone));
    }

    public void setArrivalTime(ZonedDateTime arrTime) {
        if (arrTime != null) {
            ZonedDateTime arrTimeZ = arrTime.withZoneSameInstant(arrival.city.zone);
            if (arrTimeZ.isAfter(arrival.now()) && arrTimeZ.isAfter(depTime)) {
                this.arrTime = arrTimeZ;
                duration = Duration.between(depTime, arrTimeZ);
            } else if (!arrTime.isAfter(arrival.now())) {
                throw new IllegalArgumentException("Arrival time '" + arrTime + "' cannot be in the past");
            } else {
                throw new IllegalArgumentException("Arrival time '" + arrTime +
                        "' cannot be before departure time '" + depTime + "'");
            }
        } else {
            throw new NullPointerException("Arrival time is null");
        }
    }

    public void setArrivalTime(LocalDateTime arrTime) {
        setArrivalTime(arrTime.atZone(arrival.city.zone));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Flight ").append(code);
        builder.append(": from ").append(departure.withLocation()).append(depTime.format(FORMAT));
        builder.append(" - to ").append(arrival.withLocation()).append(arrTime.format(FORMAT));
        builder.append("\nFlight time ").append(formatDuration(duration));
        return builder.toString();
    }
}