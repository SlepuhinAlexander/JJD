package lessons.lesson06;

/*
 * В библиотеке можно:
 * - добавлять книги по одной или несколько сразу
 * - можно взять книгу (указав автора)
 * - взять домой / для чтения в билиотеке
 * */
public class Library {
    private String name = "Библиотека";
    /*
     * При объявлении свойства класса, можно задавать ему значение по умолчанию.
     * */
    /*
     * Если свойство private и не задан сеттер, то значение свойства будет нельзя изменить
     * */
    private Book[] books = new Book[5];

    public String getName() {
        return name;
    }

    // добавление одной книги
    public void addBook(Book newBook) {             // в качестве имени метода принято использовать глагол,
        if (newBook == null) {                      // описывающий что метод делает
            return;
        }
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = newBook;
                newBook.setInLibrary(true);
                break;
            }
        }
    }

    // добавление нескольких книг
    /*
     * Методы могут иметь одиаковые имена, но при этом должны иметь различные наборы аргументов
     * */
    public void addBook(Book... newBooks) {
        /*
         * ... многоточие означает переменное количество аргументов.
         * при использовании такого метода вместо аргумента newBooks можно передать сколько угодно аргументов такого
         * же типа данных
         *
         * Фактически, всё переменное количество аргументов собирается в массив.
         * */
        for (Book book : newBooks) {
            addBook(book);
        }
    }

    // получение книжки на руки для того чтобы забрать домой
    public Book getHome(String title) {
        if (title == null || "".equals(title)) {
            return null;
        }
        for (Book book : books) {
            if (book != null && title.equals(book.getTitle()) &&
                    book.isInLibrary() && book.isForHome()) {
                book.setInLibrary(false);
                return book;
            }
        }
        return null;
    }

}
