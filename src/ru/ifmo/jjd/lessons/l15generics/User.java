package ru.ifmo.jjd.lessons.l15generics;

/*
 * Для использования обобщённого типа данных в классе, нужно в определении класса добавить указание, что класс
 * использует обобщённый тип: <T> и использовать этот же тип внутри класса, везде где нужно.
 *
 * Вместо буквы T может быть любое имя. T - это просто принятое именование.
 * */
public class User<T> {
    private T id;
    /*
     * Необходима возможность выбрать тип данных поля id при создании объекта
     * Тогда вместо конкретного типа данных указывается generic тип
     * */
    private String login;
    private String pwd;

    /*
     * В методах, использующих
     * */
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
