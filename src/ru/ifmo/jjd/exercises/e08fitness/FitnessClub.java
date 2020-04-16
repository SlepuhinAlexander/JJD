package ru.ifmo.jjd.exercises.e08fitness;

import ru.ifmo.jjd.utils.time.TimeRange;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class FitnessClub {
    public static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("(d MMM yy H:mm): ");

    private String name;
    private TimeRange workingHours;
    private TreeSet<Area> areas = new TreeSet<>();
    private TreeSet<Subscription> subscriptions = new TreeSet<>();

    public FitnessClub(String name, TimeRange workingHours) {
        setName(name);
        setWorkingHours(workingHours);
    }

    public FitnessClub(String name) {
        this(name, new TimeRange(LocalTime.MIN, LocalTime.MAX));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String nameNorm = normalize(name);
        if (!nameNorm.isBlank() && nameNorm.length() > 3) {
            this.name = nameNorm;
        } else {
            println("name '" + name + "' is invalid or too short");
        }
    }

    public TimeRange getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(TimeRange workingHours) {
        this.workingHours = new TimeRange(workingHours.getFrom(), workingHours.getTo());
    }

    public TreeSet<Area> getAreas() {
        return areas;
    }

    public void addArea(String name, Area.AreaType type, TimeRange workingHours, int maxVisitors) {
        areas.add(new Area(name, type, this.workingHours.intersect(workingHours), maxVisitors));
    }


    public void addArea(String name, Area.AreaType type, TimeRange workingHours) {
        areas.add(new Area(name, type, this.workingHours.intersect(workingHours)));
    }

    public void addArea(String name, Area.AreaType type) {
        areas.add(new Area(name, type, workingHours));
    }

    public TreeSet<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public Subscription findSubscription(Person person) {
        for (Subscription s : subscriptions) {
            if (s.holder.equals(person) && s.isValid()) {
                return s;
            }
        }
        return null;
    }

    public Subscription register(Person person, Subscription.SubscriptionType type) {
        Subscription result = null;
        if (person != null && type != null) {
            println(person + " желает купить абонемент " + type);
            result = findSubscription(person);
            if (result != null) {
                println(person + " уже имеет абонемент");
            } else if (!person.birthday.isAfter(LocalDate.now().minusYears(18))) {
                result = new Subscription(person, type);
                subscriptions.add(result);
            } else {
                println(person + " ещё не исполнилось 18 лет");
            }
        }
        return result;
    }

    public void visit(Subscription subscription, Area area, LocalTime when) {
        LocalTime whenT = TimeRange.trim(when);
        String message = "Доступ разрешён";
        if (subscription != null && area != null && whenT != null) {
            logVisit(subscription, area, whenT);
            if (subscriptions.contains(subscription) && subscription.isValid() &&
                    areas.contains(area) && subscription.type.isPermitted(area.getType()) &&
                    area.getWorkingHours().intersect(subscription.type.getPermission(area.getType())).contains(whenT) &&
                    area.hasSlot()) {
                area.visit(subscription);
                subscription.use();
            } else if (!subscriptions.contains(subscription)) {
                message = "абонемент не найден в базе данных: " + subscription;
            } else if (!subscription.isValid()) {
                message = "абонемент более не действительен:\n" + subscription.details();
            } else if (!areas.contains(area)) {
                message = "фитнес-клуб не предоставляет таких услуг: " + area;
            } else if (!subscription.type.isPermitted(area.getType())) {
                message = "в абонемент не входит посещение " + area + ":\n" + subscription.details();
            } else if (!area.getWorkingHours().
                    intersect(subscription.type.getPermission(area.getType())).contains(whenT)) {
                message = "по графику работы (" + area.getWorkingHours() + ") и условиям абонемента и посещение " +
                        area + " в " + whenT.format(TimeRange.FORMAT) + " невозможно:\n" + subscription.details();
            } else {
                message = "в " + area + " нет свободных мест";
            }
        } else if (subscription == null) {
            message = "subscription is null";
        } else if (area == null) {
            message = "area is null";
        } else {
            message = "area is null";
        }
        println(message);
    }

    private void logVisit(Subscription subscription, Area area, LocalTime when) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n").append(LocalDateTime.of(LocalDate.now(), when).format(TIMESTAMP));
        builder.append(subscription.holder).append(" желает посетить ").append(area);
        println(builder.toString());
    }

    public void visit(Subscription subscription, Area area) {
        visit(subscription, area, LocalTime.now());
    }

    public void leave(Subscription subscription) {
        if (subscription != null && subscriptions.contains(subscription)) {
            areas.forEach(area -> area.getVisitors().remove(subscription));
        }
    }

    public void listVisitors() {
        println();
        areas.forEach(area -> {
            println(area + ": ");
            area.getVisitors().forEach(subscription -> println("\t" + subscription.holder));
        });
    }

    public void close(LocalTime when) {
        if (when != null && when.isAfter(workingHours.getTo())) {
            areas.forEach(Area::close);
        }
    }

    public void close() {
        close(LocalTime.now());
    }
}
