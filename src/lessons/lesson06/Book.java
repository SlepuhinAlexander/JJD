package lessons.lesson06;

/*
 * название
 * автор
 * количество страниц
 * можноли брать домой
 * доступна ли для выдачи
 * */
public class Book {
    private String title;
    private int pageCount;
    private Author author;
    private boolean isForHome;      // имена булевских переменных принято начинать с is
    private boolean isInLibrary;    // имена методов, возвращающих булекские значения - тоже

    /*
     * Если в классе не описано конструкторов, то java автоматически создаёт скрытый конструктор по умолчанию
     * public Book(){}
     *
     * Если в классе описан хотя бы один конструктор с пораметрами, то конструктор по умолчанию (без параметров)
     * будет уже недоступен.
     *
     * Если он всё же нужен, то необходимо его явно указать.
     * */

    public Book() {
    }

    public Book(String title) {
        setTitle(title);
    }

    public Book(String title, int pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean isForHome() {
        return isForHome;
    }

    public void setForHome(boolean forHome) {
        isForHome = forHome;
    }

    public boolean isInLibrary() {
        return isInLibrary;
    }

    public void setInLibrary(boolean inLibrary) {
        isInLibrary = inLibrary;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", pageCount=" + pageCount +
                ", author=" + author +
                ", isForHome=" + isForHome +
                ", isInLibrary=" + isInLibrary +
                '}';
    }
}
