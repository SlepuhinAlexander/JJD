package ru.ifmo.jjd.exercises.e08fitness;

import ru.ifmo.jjd.utils.time.TimeRange;

import java.time.LocalTime;
import java.util.Objects;
import java.util.TreeSet;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.StringHelper.normalize;
import static ru.ifmo.jjd.utils.StringHelper.normalizeCyrillic;

public class Area implements Comparable<Area> {
    private final AreaType type;
    private String name = "";
    private TimeRange workingHours;
    private int maxVisitors = 20;
    private TreeSet<Subscription> visitors = new TreeSet<>();

    public Area(String name, AreaType type, TimeRange workingHours) {
        setName(name);
        this.type = type;
        setWorkingHours(workingHours);
    }

    public Area(String name, AreaType type, TimeRange workingHours, int maxVisitors) {
        this(name, type, workingHours);
        setMaxVisitors(maxVisitors);
    }

    public Area(String name, AreaType type) {
        this(name, type, new TimeRange(LocalTime.MIN, LocalTime.MAX));
    }

    public Area(AreaType type) {
        this(type.toString(), type, new TimeRange(LocalTime.MIN, LocalTime.MAX));
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

    public AreaType getType() {
        return type;
    }

    public TimeRange getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(TimeRange workingHours) {
        this.workingHours = new TimeRange(workingHours.getFrom(), workingHours.getTo());
    }

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = Math.min(1000, Math.max(0, maxVisitors));
    }

    public TreeSet<Subscription> getVisitors() {
        return visitors;
    }

    public void visit(Subscription s) {
        if (hasSlot()) {
            visitors.add(s);
        }
    }

    public void close(LocalTime when) {
        if (when != null && when.isAfter(workingHours.getTo())) {
            visitors.clear();
        }
    }

    public void close() {
        close(LocalTime.now());
    }

    public boolean hasSlot() {
        return visitors.size() < maxVisitors;
    }

    @Override
    public int compareTo(Area o) {
        int cmp = type.ordinal() - o.type.ordinal();
        if (cmp == 0) {
            cmp = name.compareToIgnoreCase(o.name);
        }
        return cmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Area)) return false;
        Area area = (Area) o;
        return type == area.type && name.equals(area.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    @Override
    public String toString() {
        return type + " '" + name + "'";
    }

    enum AreaType {
        GYM("Тренажёрный зал"),
        POOL("Бассейн"),
        GROUP("Групповое занятие");

        private final String description;

        AreaType(String description) {
            this.description = normalizeCyrillic(description);
        }

        AreaType() {
            this("");
        }

        @Override
        public String toString() {
            return description;
        }
    }
}
