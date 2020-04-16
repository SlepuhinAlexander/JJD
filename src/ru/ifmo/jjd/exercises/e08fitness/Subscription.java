package ru.ifmo.jjd.exercises.e08fitness;

import ru.ifmo.jjd.utils.time.DateRange;
import ru.ifmo.jjd.utils.time.TimeRange;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.TreeSet;

import static ru.ifmo.jjd.utils.StringHelper.normalizeCyrillic;

public class Subscription implements Comparable<Subscription> {

    final Person holder;
    final SubscriptionType type;
    final DateRange pass;
    private boolean isUsed = false;

    public Subscription(Person holder, SubscriptionType type) {
        if (holder != null && type != null) {
            this.holder = holder;
            this.type = type;
            pass = new DateRange(LocalDate.now(), (LocalDate) type.period.addTo(LocalDate.now()));
        } else if (holder == null) {
            throw new NullPointerException("Holder is null");
        } else {
            throw new NullPointerException("Subscription type is null");
        }
    }

    boolean isValid() {
        return !isUsed && !pass.getTo().isBefore(LocalDate.now());
    }

    public boolean isUsed() {
        return isUsed;
    }

    void use() {
        if (type.isSingle) isUsed = true;
    }

    public void closeSubscription() {
        pass.setTo(LocalDate.now());
    }

    public String details() {
        StringBuilder builder = new StringBuilder();
        builder.append(holder.details()).append("\n");
        builder.append(type.details()).append("\n");
        builder.append("Действителено ").append(pass);
        if (isUsed) builder.append("\nАбонемент использован.");
        return builder.toString();
    }

    @Override
    public int compareTo(Subscription sub) {
        if (!holder.equals(sub.holder)) {
            return holder.compareTo(sub.holder);
        } else {
            return pass.getTo().compareTo(sub.pass.getTo());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return holder.equals(that.holder) &&
                pass.getTo().equals(that.pass.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(holder, pass.getTo());
    }

    @Override
    public String toString() {
        return holder.toString() + ", абонемент `" + type.toString() + "'";
    }

    enum SubscriptionType {
        SINGLE("Разовый") {
            @Override
            void setup() {
                setSingle(true);
                setPeriod(Period.ofDays(0));
                addPermission(Area.AreaType.GYM, new TimeRange(8, 0, 22, 0));
                addPermission(Area.AreaType.POOL, new TimeRange(8, 0, 22, 0));
            }
        },
        DAYTIME("Дневной") {
            @Override
            void setup() {
                setPeriod(Period.ofMonths(1));
                addPermission(Area.AreaType.GYM, new TimeRange(8, 0, 16, 0));
                addPermission(Area.AreaType.GROUP, new TimeRange(8, 0, 16, 0));
            }
        },
        FULL("Полный") {
            @Override
            void setup() {
                setPeriod(Period.ofMonths(1));
                addPermission(Area.AreaType.GYM, new TimeRange(8, 0, 22, 0));
                addPermission(Area.AreaType.POOL, new TimeRange(8, 0, 22, 0));
                addPermission(Area.AreaType.GROUP, new TimeRange(8, 0, 22, 0));
            }
        };

        final String description;
        private boolean isSingle = false;
        private Period period;
        private TreeSet<Permission> permissions = new TreeSet<>();

        SubscriptionType(String description) {
            this.description = normalizeCyrillic(description);
            setup();
        }

        SubscriptionType() {
            this("");
        }

        void setSingle(boolean single) {
            isSingle = single;
        }

        void setPeriod(Period period) {
            this.period = period;
        }

        void addPermission(Area.AreaType type, TimeRange range) {
            if (type != null && range != null) {
                permissions.add(new Permission(type, range));
            }
        }

        public TreeSet<Permission> getPermissions() {
            return permissions;
        }

        public boolean isPermitted(Area.AreaType type) {
            if (type != null) {
                for (Permission permission : permissions) {
                    if (permission.areaType == type) {
                        return true;
                    }
                }
            }
            return false;
        }

        public TimeRange getPermission(Area.AreaType type) {
            if (isPermitted(type)) {
                for (Permission permission : permissions) {
                    if (permission.areaType == type) {
                        return permission.times;
                    }
                }
            }
            return null;
        }

        void clearPermissions() {
            permissions.clear();
        }

        abstract void setup();

        public String details() {
            StringBuilder builder = new StringBuilder();
            builder.append("Тип абонемента `").append(description).append("'");
            if (isSingle) builder.append(", однократный");
            builder.append(".\nРазрешения:");
            permissions.forEach(permission -> builder.append("\n").append(permission));
            return builder.toString();
        }

        @Override
        public String toString() {
            return description;
        }

        private class Permission implements Comparable<Permission> {
            Area.AreaType areaType;
            TimeRange times;

            public Permission(Area.AreaType areaType, TimeRange times) {
                this.areaType = areaType;
                this.times = times;
            }

            @Override
            public int compareTo(Permission o) {
                return areaType.ordinal() - o.areaType.ordinal();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Permission)) return false;
                Permission that = (Permission) o;
                return areaType == that.areaType;
            }

            @Override
            public int hashCode() {
                return Objects.hash(areaType);
            }

            @Override
            public String toString() {
                return areaType + " " + times;
            }
        }
    }
}

