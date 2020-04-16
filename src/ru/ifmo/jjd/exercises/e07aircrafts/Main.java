package ru.ifmo.jjd.exercises.e07aircrafts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        Country australia = new Country("Australia", "AUS");
        City sydney = new City("Sydney", australia, "Australia/Sydney");
        Airport sydneyA = new Airport("SYD", "Kingsford Smith Airport", sydney);

        Country usa = new Country("USA", "USA");
        City houston = new City("Houston", usa, "America/Chicago");
        Airport houstonA = new Airport("IAH", "George Bush Intercontinental Airport", houston);

        City washington = new City("Washington D.C.", usa, "America/New_York");
        Airport washingtonA = new Airport("IAD", "Washington Dulles International Airport", washington);

        Country canada = new Country("Canada", "CAN");
        City ottawa = new City("Ottawa", canada, "America/Toronto");
        Airport ottawaA = new Airport("YOW", "Macdonald-Cartier International Airport", ottawa);

        Flight sydneyHouston = new Flight("UA-100", sydneyA, houstonA,
                LocalDateTime.of(LocalDate.now().plusWeeks(1),
                        LocalTime.of(19, 0)), 15, 35);
        Flight houstonWashington = new Flight("UA-2496", houstonA, washingtonA,
                sydneyHouston.getArrTime().plusHours(1), 2, 49);
        Flight washingtonOttawa = new Flight("UA-6053", washingtonA, ottawaA,
                houstonWashington.getArrTime().plusHours(1).plusMinutes(10), 1, 40);

        Route route = new Route();
        route.add(sydneyHouston);
        route.add(houstonWashington);
        route.add(washingtonOttawa);
        System.out.println("Flight route:\n" + route + "\n");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("d MMMM yyyy в HH:mm");
        System.out.println("Время вылета из аэропорта Хьюстона: " +
                route.getFlights().get(1).getDepTime().format(f));
        System.out.println("Время вылета из аэропорта Вашингтона: " +
                route.getFlights().get(2).getDepTime().format(f));
        System.out.println("Время прибытия в Оттаву: " + route.getFlights().getLast().getArrTime().format(f));
    }
}
/*
 * Flight route:
 * Flight UA-100: from SYD, Sydney, AUS (19:00 Apr 11, Sat) - to IAH, Houston, USA (19:35 Apr 11, Sat)
 * Flight time 15h 35m
 * Transit at IAH, Houston, USA for 1h 00m
 * Flight UA-2496: from IAH, Houston, USA (20:35 Apr 11, Sat) - to IAD, Washington DC, USA (00:24 Apr 12, Sun)
 * Flight time 2h 49m
 * Transit at IAD, Washington DC, USA for 1h 10m
 * Flight UA-6053: from IAD, Washington DC, USA (01:34 Apr 12, Sun) - to YOW, Ottawa, CAN (03:14 Apr 12, Sun)
 * Flight time 1h 40m
 *
 * Время вылета из аэропорта Хьюстона: 11 апреля 2020 в 20:35
 * Время вылета из аэропорта Вашингтона: 12 апреля 2020 в 01:34
 * Время прибытия в Оттаву: 12 апреля 2020 в 03:14
 * */