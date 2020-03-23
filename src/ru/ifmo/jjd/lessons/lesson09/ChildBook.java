package ru.ifmo.jjd.lessons.lesson09;

import java.util.Arrays;

public class ChildBook extends Book implements Cloneable {
    private String[] pics;

    public ChildBook(String[] pics) {
        setPics(pics);
    }

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        if (pics != null) {
            this.pics = pics;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChildBook childBook = (ChildBook) o;
        return Arrays.equals(pics, childBook.pics);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(pics);
        return result;
    }

    @Override
    public String toString() {
        return "ChildBook{" +
                "pics=" + Arrays.toString(pics) +
                ", title='" + title + '\'' +
                ", pageCount=" + pageCount +
                ", author=" + author +
                '}';
    }

    @Override
    protected ChildBook clone() throws CloneNotSupportedException {
        ChildBook book = (ChildBook) super.clone();
        book.setAuthor(book.getAuthor().clone());
        /*
         * Вызывается метод clone() у родителя.
         * При этом создаётся копия объекта, свойства объекта копируются.
         *
         *
         * В базовой реализации метод clone() возвращает тип Object.
         * А значит у возвращаемого объекта не будет доступа к свойствам и методам, специфичным для нашего класса.
         *
         * Если нам нужно, чтобы переопределённый метод clone() возвращал правильный тип - нужно: изменить тип
         * возвращаемых данных и сделать приведение типов.
         *
         * Если в составе объекта был другой объект, он не будет склонирован, будет скопирована ссылка на него.
         * (При клонировании объекты не копируются).
         * Чтобы клонировались вложенные объекты - нужно описывать реализацию самостоятельно для каждого вложенного
         * объекта.
         * Например, если мы хотим использовать клонирование объектов Author, нужно опять же имплементировать интерфейс
         * Cloneable и т.д.
         * */
        return book;
        /*
         * альтернативно:
         * ChildBook book = new ChildBook(pics);
         * books.setAuthor(author.clone());
         * books.setTitle(title);
         * books.setPageCount(pageCount);
         * return book;
         * */
    }

}
