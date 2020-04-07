package ru.ifmo.jjd.lessons.l09objectclass;

import java.util.Objects;

public class Author implements Cloneable {
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!"".equals(name)) {
            this.name = name;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (!"".equals(surname)) {
            this.surname = surname;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) &&
                Objects.equals(surname, author.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public Author clone() throws CloneNotSupportedException {
        return (Author) super.clone();
    }
}
