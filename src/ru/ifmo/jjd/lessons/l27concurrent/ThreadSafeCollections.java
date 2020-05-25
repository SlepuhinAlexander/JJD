package ru.ifmo.jjd.lessons.l27concurrent;

import java.util.*;
import java.util.concurrent.*;

public class ThreadSafeCollections {
    public static void main(String[] args) {
        // Потокобезопасные коллекции
        /*
         * Старые, медленные, потокобезопасные коллекции и мапы:
         * Vector
         * Stack
         * HashTable
         *
         * Построены на synchronized блоках. При обращении потока к коллекции / мапе, она полностью блокируется, другие
         * потоки не получат доступ к ней.
         * */

        /*
         * Однопоточные коллекции и мапы можно преобразовать к многопоточным.
         * */
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
        /*
         * Аналогично, работают на synchronized блоках. При обращении потока к коллекции / мапе, она полностью
         * блокируется, и другие потоки не получат доступ к ней
         * В остальном это остаются те же коллекции и мапы, с теми же методами.
         * */


        // Коллекции из пакета java.util.concurrent
        CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();
        books.addIfAbsent(new Book("Java", 1800));
        books.addIfAbsent(new Book("Java", 1800));
        System.out.println(books); // [Book{title='Java', pageCount=1800}]

        new Thread(new WriteThread(books)).start();
        new Thread(new ReadThread(books)).start();

        /*
         * Все коллекции CopyOnWrite#### на каждую операцию вставки / удаления элемента будут создавать копию
         * коллекции.
         * */

        CopyOnWriteArraySet<Book> set1;
        ConcurrentSkipListSet<Book> set2;
        /*
         * Элементы ConcurrentSkipListSet будут храниться упорядоченно.
         * */

        // ConcurrentHashMap
        ConcurrentHashMap<?, ?> m1;
        /*
         * Построена на хэш таблице.
         * Элементы не упорядочены.
         * При изменении мапы блокируется только часть мапы, отвечающая за хэшкод изменяемого элемента.
         * */

        ConcurrentSkipListMap<?, ?> m2;
        /*
         * Хранит ключи упорядоченно.
         * */
    }
}

// Поток, который будет складывать книги в коллекцию.
class WriteThread implements Runnable {
    private CopyOnWriteArrayList<Book> books;

    public WriteThread(CopyOnWriteArrayList<Book> books) {
        this.books = Objects.requireNonNull(books);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        books.addIfAbsent(Book.getBook());
        /*
         * Коллекция books при такой вставке не блокируется. Создаётся копия коллекции, изменяется (вставляется новая
         * книга), затем результат объединяется с исходной коллекцией.
         * */
    }
}

// Поток, который будет перебирать книги в коллекции.
class ReadThread implements Runnable {
    private CopyOnWriteArrayList<Book> books;

    public ReadThread(CopyOnWriteArrayList<Book> books) {
        this.books = Objects.requireNonNull(books);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Введите название книги");
            String title = scanner.nextLine();
            for (Book book : books) {
                if (title.equalsIgnoreCase(book.getTitle())) {
                    System.out.println(book);
                    books.remove(book);
                    /*
                     * В однопоточных коллекциях при попытке удаления элемента из коллекции в цикле - возникало
                     * исключение.
                     * В многопоточных коллекциях такой проблемы нет, удаление элемента в цикле для многопоточных
                     * коллекций к исключениям не приводят.
                     * Сначала будет сделана копия изначальной коллекции, действия будут произведены с копией, а затем
                     * произойдёт объединение.
                     * */
                }
            }
        }
    }
}


class Book {
    private String title;
    private int pageCount;

    public Book(String title, int pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }

    public static Book getBook() {
        Random random = new Random();
        String[] titles = {"Алфавит", "Сказки", "Колобок", "Репка"};
        return new Book(titles[random.nextInt(titles.length)],
                random.nextInt(2000)); // до 2000 страниц
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (pageCount != book.pageCount) return false;
        return getTitle() != null ? getTitle().equals(book.getTitle()) : book.getTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + pageCount;
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
               "title='" + title + '\'' +
               ", pageCount=" + pageCount +
               '}';
    }
}