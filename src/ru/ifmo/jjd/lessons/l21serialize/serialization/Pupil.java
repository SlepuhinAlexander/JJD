package ru.ifmo.jjd.lessons.l21serialize.serialization;

import java.time.LocalDate;
import java.util.Objects;

public class Pupil extends Human implements LearnAble {
    /*
     * свойства, отмеченные transient не участвуют в сериализации
     * используется вместе с интерфейсом Serializable
     * */
    transient private final String info = "Ученик";
    private Group group;
    private int level;
    private LocalDate lastLesson;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pupil)) return false;
        Pupil pupil = (Pupil) o;
        if (level != pupil.level) return false;
        if (!Objects.equals(group, pupil.group)) return false;
        return Objects.equals(lastLesson, pupil.lastLesson);
    }

    @Override
    public int hashCode() {
        int result = group != null ? group.hashCode() : 0;
        result = 31 * result + level;
        result = 31 * result + (lastLesson != null ? lastLesson.hashCode() : 0);
        return result;
    }

    @Override
    public void learn() {
        lastLesson = LocalDate.now();
        level += (int) (Math.random() * group.getMaxKnowledge());
    }

    @Override
    public String toString() {
        return "Pupil{" +
               "group=" + group +
               ", level=" + level +
               ", lastLesson=" + lastLesson +
               ", info='" + info + '\'' +
               ", name='" + name + '\'' +
               ", age=" + age +
               '}';
    }
}
