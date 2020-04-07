package ru.ifmo.jjd.lessons.l09objectclass;

import java.util.Objects;

public class Book {
    protected String title;
    protected int pageCount;
    protected Author author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!"".equals(title)) {
            this.title = title;
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        if (pageCount > 0) {
            this.pageCount = pageCount;
        }
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        if (author != null) {
            this.author = author;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pageCount, author);
    }
}
