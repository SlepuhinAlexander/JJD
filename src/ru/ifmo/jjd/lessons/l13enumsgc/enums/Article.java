package ru.ifmo.jjd.lessons.l13enumsgc.enums;


import java.time.LocalDateTime;

/*
 * Создать класс Article со следующими полями:
 * - title - название статьи
 * - text - текст статьи
 * - dateCreated - дата и время написания статьи (текущие дата и время должны устанавливаться во время создания объекта)
 * - зачение title инициализируется при создании объекта
 * - text задаётся через сеттер
 * */
public class Article {
    final LocalDateTime dateCreated = LocalDateTime.now();
    private String title = "";
    private String text = "";
    private Country country;
    /*
     * Указываем тип данных enum-а и имя переменнойж
     * */

    public Article(String title) {
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text != null && !text.isBlank()) {
            this.text = text;
        }
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Article{" +
                "dateCreated=" + dateCreated +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", country=" + country +
                '}';
    }
}
