package ru.ifmo.jjd.lessons.l26sync.waitnotify;

/*
 * Пусть есть некоторое хранилище данных
 * Одна часть потоков кладёт данные в хранилище
 * Другая часть потоков забирает данные из этого хранилища
 * putThread -> [storage] -> getThread
 * */

import java.util.ArrayList;

public class WaitNotifyDemo {
    public static void main(String[] args) {
        BookStorage bookStorage = new BookStorage();
        new Thread(new PutBook(bookStorage)).start();
        new Thread(new GetBook(bookStorage)).start();
    }
}

// нечто, что мы будем складывать и забирать из хранилища
class Book {
}

// само хранилище
class BookStorage {
    private final ArrayList<Book> books = new ArrayList<>();

    // метод будет вызывать потоки, которые будут передавать книги в хранилище
    public synchronized void putBook(Book book) throws InterruptedException {
        System.out.println("PUT_BOOK: начало выполнения");
        while (books.size() > 5) {
            notify();
            /*
             * Метод notify() будит какой-то (любой) поток, у которого ранее был вызван метод wait()
             * Будит только 1 поток. Если таковых нет, то ничего не происходит.
             *
             * Поток, отправивший notify, может разбудить сам себя.
             *
             * Метод notify() должен вызываться только в synchronized блоке или методе.
             * */

            // notifyAll()
            /*
             * метод notifyAll() будит все ожидающие потоки (у которых ранее был вызван метод wait())
             *
             * */
            System.out.println("PUT_BOOK: ожидание. Книг: " + books.size());
            wait();
            /*
             * метод wait() освобождает удерживаемый объект (его монитор переводит в не-заблокировано)
             * поток, в котором вызван wait() - переходит в состояние ожидания
             * будет находится в этом состоянии до тех пор пока не будет разбужен методом notify() (из любого потока)
             *
             * Метод wait() должен вызываться только в synchronized блоке или методе.
             * */

            /*
             * В крайне редких случаях шедулер может разбудить спящий поток (не по notify, а просто так)
             * */
        }
        books.add(book);
        System.out.println("PUT_BOOK: добавил одну книгу. Книг: " + books.size());
    }
    /*
     * при вызове этого метода будет блокироваться объект типа BookStorage
     * т.к. метод putBook - synchronized
     * */

    public synchronized Book getBook() throws InterruptedException {
        System.out.println("GET_BOOK: начало выполнение");
        while (books.size() < 1) {
            notify();
            System.out.println("GET_BOOK: ожидание. Книг: " + books.size());
            wait();
        }
        Book book = books.get(0);
        books.remove(0);
        System.out.println("GET_BOOK: забрал 1 книгу. Книг: " + books.size());
        return book;
    }
}

class PutBook implements Runnable {
    private final BookStorage bookStorage;

    public PutBook(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println(Thread.currentThread().getName() + " готовит данные для передачи");
            try {
                //noinspection BusyWait
                Thread.sleep(700);
                bookStorage.putBook(new Book());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class GetBook implements Runnable {
    private final BookStorage bookStorage;

    public GetBook(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Book book = bookStorage.getBook();
                System.out.println(Thread.currentThread().getName() + " получил книгу " + book);
                //noinspection BusyWait
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}