package lessons.lesson06;

public class Main {
    /*
     * В библиотеке есть набор книг. Каждая книга:
     * - имеет название
     * - имеет заданное количество страниц
     * - имеет одного автора (имя, фамилия) _\[ограничим число авторов до одного для простоты\]_
     * - имеет признак можно ли её брать домой
     * - имеет признак доступна ли в данный момент для выдачи.
     *
     * В библиотеке можно:
     * - добавлять книги по одной или несколько сразу
     * - можно взять книгу (указав автора)
     * - взять домой / для чтения в билиотеке
     * */

    public static void main(String[] args) {
        Author author1 = new Author();
        /*
         * Для создания объекта необходимо:
         * - указать тип данных (имя класса. с учётом, возможно, необходимого импорта пакета).
         * - указать имя переменной-объекта
         * - оператор присваивания
         * - ключевое слово new, указывающее что создаётся новый объект.
         * - имя класса
         * - конструктор класса, использующися для создания объекта.
         * */
//        author1.name = "Брюс";
//        System.out.println(author1.name); // Брюс
        /*
         * Для того чтобы обратиться к свойству объекта, у класса этого объекта это свойство должно быть описано.
         * В описании класса свойства объявляются так же как и локальные переменные.
         *
         * Далее для того чтобы обратиться к этому свойству необходимо указать имя объекта, затем через точку имя
         * свойства объекта.
         * */

        Author author2 = new Author();
//        author2.name = "Роберт";
//        System.out.println(author2.name); // Роберт
        /*
         * Создали второй объект типа Author
         * Объект author2 никак не связан с объектом author1, они независимы и самодостаточны.
         * Их объединяет только общий тип данных. А значит то, какие у них есть свойства и какие методы они могут
         * выполнять.
         * */

        author1.setName("Брюс");
        author2.setName("Роберт");
        System.out.println(author1.getName()); // Брюс
        String name = author2.getName();
        System.out.println(name); // Роберт

        // теперь задаём фамилию:
        author1.setSurname("Эккель");
        author2.setSurname("Мартин");

        // вывод объекта целиком
        System.out.println(author2);
        // до переопределения метода toString() :
        // lessons.lesson06.Author@3feba861
        // после переопределения метода toString() :
        // Author{name='Роберт', surname='Мартин'}
        /*
         * печать объекта скрыто вызывает его метод toString(), коротый есть у любого объекта.
         *
         * если метод не переопределён, то объект будет преобразован к строке, состоящей из полного имени класса и
         * хешкода объекта.
         *
         * Чтобы этого избежать, нужно задать метод toString в соответствующем классе явно. (переопределить его)
         * */

//        Book book1 = new Book();
//        System.out.println(book1);
        // Book{title='null', pageCount=0, author=null, isForHome=false, isInLibrary=false}
        /*
         * Объект, если у него не заданы значения свойств по умолчанию и не переопределён конструктор -- заполняет все
         * своии свойства значениями по умолчанию (в зависимости от типа данных):
         *  - для целочисленных типов - 0
         *  - для чисел с плавающей точкой - 0.0
         *  - для булевских - false
         *  - для всех ссылочных типов - null
         * */

        /*
         * Если необходимо, чтобы объект при создании заполнял свои значения не значениями по умолчанию, а так как мы
         * решим, то в описании классса необходимо создать конструктор, а при создании объекта -- использовать этот
         * конструктор
         * */

        Book book1 = new Book("Философия Java");
        System.out.println(book1);
        // Book{title='Философия Java', pageCount=0, author=null, isForHome=false, isInLibrary=false}
        /*
         * В таком исполнении новый объект создался со значением свойства равным переданному в конструкторе
         * */

        Book book2 = new Book("Чистый код", 500);
        System.out.println(book2);
        // Book{title='Чистый код', pageCount=500, author=null, isForHome=false, isInLibrary=false}

        /*
         * Если в классе описан хотя бы один конструктор с пораметрами, то конструктор по умолчанию (без параметров)
         * будет уже недоступен.
         * */

        book1.setPageCount(1350);
        book1.setAuthor(author1);
        book1.setForHome(true);
        System.out.println(book1);
        // Book{title='Философия Java', pageCount=1350, author=Author{name='Брюс', surname='Эккель'}, isForHome=true,
        // isInLibrary=false}

        book2.setAuthor(author2);
        System.out.println(book2);
        // Book{title='Чистый код', pageCount=500, author=Author{name='Роберт', surname='Мартин'}, isForHome=false,
        // isInLibrary=false}

        // Получить имя автора книги book1
        String book1AuthorName = book1.getAuthor().getName();
        System.out.println(book1AuthorName); // Брюс
        /*
         * Чтобы обратиться к свойству / методу объекта, который сам по себе тоже является свойством какого-то
         * объекта необходимо:
         * через точку получить вложенный объект (обратившись к свойству или геттеру)
         * и через точку получить метод / свойство из вложенного объекта.
         * */

        Book[] books = {book1, book2};
        // перебрать все книги и вывести фамилии авторов
        for (Book book : books) {
            System.out.println("\"" + book.getTitle() + "\" " + book.getAuthor().getSurname());
            // "Философия Java" Эккель
            // "Чистый код" Мартин
        }

        Library library = new Library();
        library.addBook(book1, book2, new Book());
    }
}
