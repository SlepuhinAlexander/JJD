package ru.ifmo.jjd.lessons.lesson09;

public class BooksDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
         * Object - родительский класс для всех java-классов.
         * Соответственно, его методы доступны для использования и переопределения в дочерних классах.
         * */
        Object obj = new Object();

        // Методы класса Object
        // 1. toString()
        Author author = new Author();
        author.setName("Иван");
        author.setSurname("Григорьев");

        ChildBook book1 = new ChildBook(new String[]{"dog", "cat"});
        book1.setTitle("Детская книга");
        book1.setPageCount(230);
        book1.setAuthor(author);

        String stringBook = book1.toString();
        /*
         * Метод toString() возвращает строковое представление объекта.
         * По умолчанию это полное имя класса объекта плюс его хеш-код.
         * */
        System.out.println(stringBook);
        // ru.ifmo.jjd.lessons.lesson09.ChildBook@1d81eb93

        /*
         * Если реализация метода toString() по умолчанию - не устраивает, то необходимо в классе переопределить этот
         * метод toString() и прописать ему нужную реализацию.
         *  */

        // ChildBook{pics=[dog, cat], title='Детская книга', pageCount=230,
        // author=ru.ifmo.jjd.lessons.lesson09.Author@6f539caf}
        /*
         * Переопределить toString в ChildBook оказалось недостаточно, нужно переопределить его ещё и в Author
         * */

        // ChildBook{pics=[dog, cat], title='Детская книга', pageCount=230,
        // author=Author{name='Иван', surname='Григорьев'}}

        /*
         * Следующие два метода класса Object нужно рассматривать вместе
         * */
        // 2. метод equals() - служит для сравнения двух объектов этого класса между собой.
        // 3. метод hashCode() - возвращает hash code объекта.

        ChildBook book2 = new ChildBook(new String[]{"dog", "cat"});
        book2.setTitle("Детская книга");
        book2.setPageCount(230);
        book2.setAuthor(author);
        // сравним два объекта.
        boolean isEqualBooks = book1.equals(book2);
        System.out.println(isEqualBooks); // false
        /*
         * Метод equals сравнивает два объекта, и возвращает булевское значение: true - если объекты равны;
         * false - если объекты не равны
         * */
        /*
         * В реализации по умолчанию (в классе Object) метод equals сравнивает ссыкли на оба объекта.
         *
         * Если реализация по умолчанию не устраивает, то в классе необходимо переопределить метод equals().
         * Вместе с переопределением метода equals() принято переопределять метод hashCode().
         * */

        System.out.println(book1.hashCode());
        /*
         * Изначально и в идеале hash code объекта это адрес объекта в памяти.
         * В зависимости от JVM алгоритм определения хэш кода может отличаться.
         * Плюс объекты в памяти перемещаются.
         * Хэш код не уникально определяет объект: у нескольких объектов может быть одинаковый хэшкод.
         *
         * Поэтому метод hashCode() нужно переопределять вместе c методом equals()
         * */
        System.out.println(isEqualBooks); // true
        /*
         * Теперь объекты ChildBook сравниваются по массиву pics. Они равны, если массивы картинок равны.
         * Но остальные свойства, оставшиеся ClassBook от родителя не сравниваются.
         *
         * Для этого нужно
         * */

        // 4. Метод clone()
        /*
         * Метод clone() используется для создания копии объекта.
         * */
        // ChildBook cloneBook = book1;
        /*
         * В таком виде мы создаём новую ссылку на тот же объект. Это не клонирование объекта.
         * */
        ChildBook cloneBook = book1.clone(); // method clone() has protected access
        /*
         * При использовании метода clone() есть два варианта.
         * 1. Использовать базовую реализацию (ту, что уже описана)
         * Тогда:
         *  - класс должен реализовывать интерфейс Cloneable.
         *      Интерфейс Cloneable не содержит методов. Он является интерфейсом-маркером. Он помечает, что класс,
         *      реализующий интерфейс, может клонироваться.
         *
         * Можно расширить модификатор доступа метода до public.
         * Можно привести тип вовращаемого значения к нашему классу.
         *
         * 2. Без использования базовой реализации (без использования super.clone()):
         *  - класс не обязан реализовывать интерфейс Cloneable
         *  - необходимонаписать свою реализацию по созданию копии объекта
         * */
        System.out.println(cloneBook.clone());
        // ChildBook{pics=[dog, cat], title='Детская книга', pageCount=230,
        // author=Author{name='Иван', surname='Григорьев'}}
    }
}
