## Операции с массивами

Помимо операций с отдельными элементами массива, есть ряд типовых операций с массивом целиком.

### Копирование массива

Одной из частых операций с массивом является создание его полной или частичной копии.

Рассморим следующие инструкции: 

    int[] original = {1, 2, 3, 4, 5};
    int[] copy = original;
    
Присваивание `copy = original`  не означает, что будет создан новый массив `copy` идентичный 
`original`. Данная инстркция означает, что будет создана новая ссылочная переменная типа `int[]` с именем `copy` и ей 
будет присвоено значение равное переменной `original`, которая, в свою очередь, так же является ссылочной переменной 
типа `int[]`.  
Другими словами, в переменную `copy` будет скопирована **ссылка** на массив `{1, 2, 3, 4, 5}`. Сам же массив останется 
в единственном экземпляре.  
Обращаться к элементам массива теперь можео будет через любое из его имён `original` или `copy`. Изменение элемента 
будет "заметно" также по любому из имён массива.

Копированием массива такая операция не является. Скопировать массив означает получить новый массив (новый объект), 
который имеет такое же содержимое, что и исходный масив. Но оба массива должны быть независимы (не связаны).

Есть несколько способов копировать / клонировать массив или его часть

#### Способ 1: вручную с использованием цикла

    // ArraysDemo.java
    // copyingArraysDemo()
    /*
     * <code> 
     */
    int[] original = {2, 4, 8, 16, 32};
    int[] copy = new int[original.length];
    for (int i = 0; i < original.length; i++) {
        copy[i] = original[i];
    }
    System.out.println("original = " + Arrays.toString(original)); // original = [2, 4, 8, 16, 32]
    System.out.println("copy = " + Arrays.toString(copy)); // copy = [2, 4, 8, 16, 32]
    
Разумеется, этот способ можно модифицировать, например, для копирования части массива (какого-то диапазона его 
индексов).

#### Способ 2: метод `clone()` у самого массива

Метод `clone()` есть у любой переменной типа массив. Он создаёт новый массив того же типа данных и той же длины, что и 
вызывающий массив, и копирует все элементы в новый массив. В результате выполнения метод возвращает созданный новый 
массив.  
Другими словами, метод `clone()` создаёт полную копию исходного массива и возвращает её в результате своей работы. 
Результат можно присвоить, например, другой переменной.  
Метод `clone()` самый простой, но не гибкий: он позволяет создать только полную копию массива.

    // ArraysDemo.java
    // copyingArraysDemo()
    /*
     * <code> 
     */
    original = new int[]{3, 6, 9, 12, 15};
    copy = original.clone();
    System.out.println("original = " + Arrays.toString(original)); // original = [3, 6, 9, 12, 15]
    System.out.println("copy = " + Arrays.toString(copy)); // copy = [3, 6, 9, 12, 15]

#### Способ 3: метод `System.arraycopy()`

Метод `System.arraycopy()` является статическим методом класса `System` и позволяет гибко копировать часть элементов 
одного массива в другой. Оба массива, исходный и целевой, должны уже существовать, и их нужно передать методу 
`arraycopy()` в качестве аргументов.

Метод `arraycopy()` имеет следующие 5 аргументов:
- `Object src` - исходный объект; в частности, исходный массив;
- `int srcPos` - позиция в исходном объекте; индекс в исходном массиве, начиная с которого нужно брать данные;
- `Object dest` - целевой объект; в частности, целевой массив;
- `int destPos` - позиция в целевом объекте; индекс в целевом массиве, начиная с которого нужно вставлять данные; 
- `int length` - длина копируемой части массива: количество элементов, которые нужно взять, начиная с `srcPos` из 
    `src` и поместить в `dest`, начиная с `destPos`. 

Если в целевом массиве уже были какие-то данные, они будут перезаписаны новыми значениями.

Метод `arrayCopy()` может выбрасывать исключение `java.lang.ArrayIndexOutOfBoundsException` при обращении к 
несуществующим индексам в исходном и/или целевом массивах.

    // ArraysDemo.java
    // copyingArraysDemo()
    /*
     * <code> 
     */
    original = new int[]{23, 1, -10, 5};
    copy = new int[12];
    System.arraycopy(original, 1, copy, 3, 2);
    System.out.println("original = " + Arrays.toString(original)); // original = [23, 1, -10, 5]
    System.out.println("copy = " + Arrays.toString(copy)); // copy = [0, 0, 0, 1, -10, 0, 0, 0, 0, 0, 0, 0]

#### Способ 4: метод `Arrays.copyOf()`

Метод `copyOf()` является статическим методом служебного класса `Arrays` и позволяет скопировать несколько *первых* 
элементов массива или сделать полную копию.

Метод `copyOf()` имеет два аргумента: исходный массив и желемую длину целевого массива. Если в качестве длины будет 
передано отрицательное число, метод выбросит исключение `java.lang.NegativeArraySizeException`.
   
Метод создаёт новый массив желаемой длины, заполняет его копиями элементов исходного массива, начиная с нулевого 
индекса и продолжая до тех пор пока не закончится исходный массив или не будет достигнута целевая длина 
(закончится целевой массив).  
Если длина желаемой копии меньше длины оригинала, избыточные элементы массива-оригинала будут проигнорированы.  
Если, наоборот, длина желаемой копии больше длины оригинала, метод заполнит оставшиеся элементы массива-копии 
значениями по умолчанию.  
Таким образом, метод `copyOf()` безопасен с точки зрения выхода за диапазон доступных индексов.   

В результате работы метод `copyOf()` возвращает созданный массив, который можно, например, присвоить переменной.

    // ArraysDemo.java
    // copyingArraysDemo()
    /*
     * <code> 
     */
    original = new int[]{1, 2, 3};
    copy = Arrays.copyOf(original, 2);
    System.out.println("original = " + Arrays.toString(original)); // original = [1, 2, 3]
    System.out.println("copy = " + Arrays.toString(copy)); // copy = [1, 2]
    copy = Arrays.copyOf(original, 5);
    System.out.println("copy = " + Arrays.toString(copy)); // copy = [1, 2, 3, 0, 0]
    
####  Способ 5: метод `Arrays.copyOfRange()`

Метод `copyOfRange()` аналогичен предыдущему, только имеет три аргумента вместо двух:
- исходный массив;
- начальную позицию (индекс `from`) в исходном массиве;
- конечную позицию (индекс `to`) в исходном массиве, до которой необходимо копировать (не включая).

Если аргумент `to` будет меньше, чем аргумент `from`, метод выбросит исключение `java.lang.IllegalArgumentException`; 
если любой из аргументов `from` или `to` отрицательны, метод выбросит исключение 
`java.lang.ArrayIndexOutOfBoundsException`.

Полученный массив будет иметь длину равную `to - from`. Если позиция `to` выходит за границы исходного массива, 
соответствующие элементы в массиве-копии будут заполнены значениями по умолчанию.

    // ArraysDemo.java
    // copyingArraysDemo()
    /*
     * <code> 
     */
    original = new int[]{12, 13, 14, 15, 16, 17, 18};
    copy = Arrays.copyOfRange(original, 2, 5);
    System.out.println("original = " + Arrays.toString(original)); // original = [12, 13, 14, 15, 16, 17, 18]
    System.out.println("copy = " + Arrays.toString(copy)); // copy = [14, 15, 16]
    copy = Arrays.copyOfRange(original, 2, 10);
    System.out.println("copy = " + Arrays.toString(copy)); // copy = [14, 15, 16, 17, 18, 0, 0, 0]
    
### Методы класса `Arrays`

Класс `Arrays` обладает ещё рядом полезных статических методов для работы с массивами, помимо уже упомянутых.

#### Сравнение массивов

Под сравнением массивов логично понимать сравнение их содержимого. Т.е. массивы считаются равными, если они содержат 
одинаковые данные.

Для сравнения массивов в таком контексте оператор `==` и метод `equals()` массива - не подходят:

    // ArraysDemo.java
    // arraysClassDemo()
    /*
     * <code> 
     */
    int[] arr1 = new int[10];
    int[] arr2 = new int[10];
    System.out.println("arr1 == arr2 : " + (arr1 == arr2)); // arr1 == arr2 : false
    System.out.println("arr1.equals(arr2) : " + (arr1.equals(arr2))); // arr1.equals(arr2) : false

Оператор `==` сравнивает значения двух ссылочных переменных `arr1` и `arr2`. Т.е. сравнивает *ссылки* на массивы. Он 
вернёт `true`, если обе ссылки ведут на один и тот же объект. Из этого, конечно, следует, что массивы равны (т.к. это 
будет один и тот же массив). Но обратное не верно: два разных массива с одинаковым содержимым будут иметь различные 
ссылки на них.

Метод `equals()` сравнивает объекты, ссылки на которые ведут из `arr1` и `arr2`. Он вернёт `true` если объекты равны. 
Из этого, вообще говоря, не следует, что содержимое массивов равно. И обратное тоже не верно: два разных метода с 
одинаковым содержимым определённо являются разными объектами.

Для сравнения массивов по содержимому есть готовый метод `Arrays.equals()`. В качестве аргументов он принимает два 
сравниваемых массива и возвращает `true` только если массивы миеют одинаковые типы данных, одинаковую длину, и на 
равных позициях хранят равные значения.

    // ArraysDemo.java
    // arraysClassDemo()
    /*
     * <code> 
     */
    System.out.println("Arrays.equals(arr1, arr2) : " + Arrays.equals(arr1, arr2)); // Arrays.equals(arr1, arr2) : true

#### Наполнение массива данными

Иногда бывает необходимо наполнить массив одинаковыми данными, не по умолчанию. Для этих целей есть готовый метод 
`Arrays.fill()`. Метод принимает два аргумента: массив, который необходимо заполнить, и значение, которое нужно 
присвоить каждому из элементов массива.  
Разумеется, значение должно быть того же типа данных, что и массив.

    // ArraysDemo.java
    // arraysClassDemo()
    /*
     * <code> 
     */
    int[] someArr = new int[5];
    Arrays.fill(someArr, 11);
    System.out.println("someArr = " + Arrays.toString(someArr)); // someArr = [11, 11, 11, 11, 11]

#### Сортировка массива

Метод `Arrays.sort()` принимает аргументом массив, значения которого необходимо отсортировать.
В результате выполнения метода переданный массив будет изменён: все его элементы будут расположены в естественном 
порядке.  
Для числовых данных естественный порядок - порядок по возрастанию;  
Для строк - алфавитный (регистрозависимо) порядок по возрастанию;  
Для ссылочных типов данных - для корректной сортировки порядок должен быть определён в описании класса этих объектов. 

Для сортировки применяется алгоритм быстрой сортировки ([[quick sort]](https://en.wikipedia.org/wiki/Quicksort), 
алгоритм Хоара). Вычислитльная сложность такого - O(n * log2 n).

    // ArraysDemo.java
    // arraysClassDemo()
    /*
     * <code> 
     */
    int[] someArr1 = {12, -10, 123, 6};
    Arrays.sort(someArr1);
    System.out.println("Отсортированный someArr1 = " + Arrays.toString(someArr1));
    // Отсортированный someArr1 = [-10, 6, 12, 123]

#### Сортировка части массива

Тот же метод `Arrays.sort()`, но уже с тремя аргументами, применяется для сортировки части массива. Метод аналогичен 
предыдущему, только принимает в качестве аргументов ещё начальную и конечную позицию в сортируемом массиве.  
Метод сортирует только часть массива от начальной до конечной позиции, остальные элементы массива не затрагивает.  
Начальная граница включается в сортировку, конечная - не включается.

    // ArraysDemo.java
    // arraysClassDemo()
    /*
     * <code> 
     */
    int[] someArr2 = {23, 1, -10, 5, 111, 24, 22, 1};
    Arrays.sort(someArr2, 1, 5); // сортировка части массива от индекса1 до индекса2
    System.out.println("Отсортированный someArr2 = " + Arrays.toString(someArr2));
    // Отсортированный someArr2 = [23, -10, 1, 5, 111, 24, 22, 1]

#### Поиск в массиве

Метод `Arrays.binarySearch()` позволяет проверить, находится ли в массиве определённый элемент, и если да, то на какой 
позиции (по какому индексу).  
Метод имеет смысл использовать только для **отсортированного** массива. Работа метода `binarySearch()` с 
несортированным массивом даст непредсказуемый результат.

Метод принимает два аргумента: массив, в котором нужно искать, и ключ - объект, который разыскивается.  
Если ключ найден в массиве, метод возвращает неотрицательное число - индекс первого найденного в массив элемента, 
который совпадает с ключом.  
Если ключ в массиве не найден, метод возвращает отрицательное число равное `-insertionPoint - 1`, где 
`insertionPoint` - индекс массива, куда можно было бы вставить разыскиваемый ключ, чтобы не нарушить сортировку.

Зачастую, конкретное значение, возвращаемое методом - не рассматривается, для ответа на вопрос есть такой элемент в 
массиве или нет, достаточно сравнить результат метода с нулём.

Метод использует алгоритм [[бинарного (двоичного) поиска]](https://en.wikipedia.org/wiki/Binary_search). Вычислитльная 
сложность алгоритма - O(n * log2 n).

    // ArraysDemo.java
    // arraysClassDemo()
    /*
     * <code> 
     */
    someArr1 = new int[]{-10, 6, 12, 123};
    System.out.println("Searching for 6 in someArr1 : " + Arrays.binarySearch(someArr1, 6));
    // Searching for 6 in someArr1 : 1
    System.out.println("Searching for 15 in someArr1 : " + Arrays.binarySearch(someArr1, 15));
    // Searching for 15 in someArr1 : -4

#### Поиск в части массива

Тот же метод `Arrays.binarySearch()`, но с дополнительными двумя аргументами, позволяет осуществить поиск в части 
массива. Метод аналогичен предыдущему, только принимает в качестве аргументов ещё начальную и конечную позицию в 
массиве. Метод ищет ключ только в части массива от начальной до конечной позиции, остальные элементы массива 
игнорирует. Начальная граница включается в поиск, конечная не включается.  
Вычисление `insertionPoint` также учитывает заданные границы. Например, если искомый элемент будет больше, чем все 
элементы в выбранной части массива, `insertionPoint` Будет равен минус конечной позиции поиска.

    // ArraysDemo.java
    // arraysClassDemo()
    /*
     * <code> 
     */
    someArr2 = new int[]{-10, 1, 1, 5, 22, 23, 24, 111};
    System.out.println("Searching for 23 in someArr2 from 1 to 5 : " + Arrays.binarySearch(someArr2, 1, 5, 23));
    // Searching for 23 in someArr2 from 1 to 5 : -6
    System.out.println("Searching for 22 in someArr2 from 1 to 5 : " + Arrays.binarySearch(someArr2, 1, 5, 22));
    // Searching for 22 in someArr2 from 1 to 5 : 4
    System.out.println("Searching for 100 in someArr2 from 1 to 5 : " + Arrays.binarySearch(someArr2, 2, 5, 1000));
    // Searching for 100 in someArr2 from 1 to 5 : -6