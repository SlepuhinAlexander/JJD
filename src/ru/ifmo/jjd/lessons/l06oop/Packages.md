## Пакеты

### Введение

Пакет является организационной структурой для исходного кода в Java.
Все классы исходного кода находятся в каком-либо пакете.   
Пакеты могут быть вложенными друг в друга на неограниченную глубину. Полное имя пакета при этом будет состоять из 
цепочки имён всех родительских пакетов и текущего пакета, соединённых через точку. Например, `base.core.engine.models`.  
Дерево пакетов в проекте полностью соотвествует дереву директорий в операционной системе.

### Пространства имён и видимость классов

Имя пакета задаёт пространство имён для всех классв находящихся в этих пакетах. Пространство имён определяет видимость 
(доступность) классов: по умолчанию, если не задано иных модификаторов доступа, любой класс находящийся в пакете имеет 
доступ ко всем остальным классам (их полям и методам) в этом пакете. А класс вне данного пакета, по умолчанию, 
содержимое пакета "не видит".  

Кстати, вложенный пакет _не виден_ из родительского пакета. Например, класс в пакете `base.core`, по умолчанию, 
_"не видит"_ классы из пакета `base.core.engine`.

Модификаторы доступа (см. ниже) изменяют поведение по умолчанию: можно как ограничить видимость класса (его полей и 
методов) сильнее, так и, наоборот, расширить видимость класса за пределы пакета.

Организация классов в пакеты и видимость классов является одним из инструментов сокрытия (инкапсуляции) исходного кода 
от нежелательного использования. 

### Соглашение по именованию пакетов

Технически, для именования пакетов, как и для именования директорий в операционной системе, возможно использование 
почти любых Unicode символов (кроме запрещённых, зарезервированных операционной системой). Имя пакета должно 
_начинаться_ с латинской буквы или знаков `_` или `$`. Кроме того, имя пакета не должно совпадать с зарезервированным в 
Java ключевым словом.  
Имя пакета должно быть уникально в пределах содержащего его родительского пакета (равносильно именам директорий 
в одной родительской директории). При этом полное имя пакета должно быть глобально уникально. 

Соглашение по именованию предписывает использовать только символы латинского алфавита и цифры; в исключительных 
случаях, для замены знака `-` или обхода запрета на использование ключевых слова языка Java разрешено использовать знак 
`_`.  
Для исключения возможных конфликтов с именами классов соглашение по именованию предписывает использовать для имён 
пакетов только нижний регистр латиницы. Если имя пакета состоит из нескольких слов, их следует записывать слитно, никак 
не разделяя.

Кроме того, для предотвращения конфликта имён пактов в различных огранизациях, соглашение по именованию предписывает в 
качестве корневых пакетов использовать инвертированное доменное имя организации и имя проекта.  
Например, проект `JJD` в организации `IFMO.RU` должен использовать в качестве корня всех остальных пакетов следующую 
структуру: `ru.ifmo.jjd`.

### Имена классов и инструкция импорта

Любой класс в языке Java имеет полное имя, состояшее из имении пакета и, собственно, имени класса.  
Например класс `Arrays`, содержащий полезные методы для работы с массивами, фактически имеет полное имя 
`java.util.Arrays`.  
Полное имя класса, так же как и полное имя пакета, должно быть глобально уникально. Распределение классов по пакетам, в 
том числе, позволяет использовать классы с одинаковыми краткими именами и избежать пробему неоднозначности, т.к. полное 
имя класса остаётся уникальным.

Для того чтобы в принципе обратиться к классу, и сделать это однозначно, необходимо указать полное имя класса. 
Например,
 
    base.core.engine.models.Car car = new base.code.engine.models.Car();  

Чтобы не прописывать полное имя класса каждый раз, существует инструкция `import`, которую необходимо указать в начале 
используемого `.java` файла. Например, инструкция

    import base.core.engine.models.Car;
    
Позволяет в текущем классе обрщаться к классу `base.core.engine.models.Car`, указывая только его имя:

    Car car = new Car();

Фактически, инструкция импорта указывает компилятору, что при компиляции каждое краткое имя класса `Car` необходимо 
заменить на полное имя класса `base.core.engine.models.Car`.

Если использована инструкция

    import base.core.engine.models.Car;
 
то использовать короткие имена других классов пакета `base.core.engine.models` нельзя, они "не видны" компилятору, т.к. 
инструкция импорта включает только класс `Car`. Чтобы импортировать все классы интересующего пакета необходимо 
использовать символ `*`. Наример,

    import base.core.engine.*; 

позволяет обращаться по короткому имени к любому классу из пакета `base.core.engine` (если это не запрещено 
модификаторами доступа).  
_**Но**_ при этом классы из пакета `base.core.engine.models` не импортированы! Для компилятора это два различных пакета, 
и не имеет значения что второй вложен в первый.

Пакет `java.lang` всегда неявно импортирован по умолчанию, поэтому для основных классов языка Java не требуется явно 
указывать полное имя класса или инструкцию импорта.

В ситуации конфликта имён импортированных классов, необходимо явно указывать компилятору какой именно класс необходимо 
использовать. Например, 

    import base.core.engine.models.Car;
    import base.core.engine.sounds.Car;
    /*
     * <some code here>
     */  
    Car car = new Car();

Приведёт к ошибке компиляции, т.к. невозможно определить какой из двух классов `Car` необходимо использовать.
Чтобы разрешить этот конфликт, необходимо импортировать только один класс `Car`, а для второго придётся использовать 
полное имя; либо можно использовать полные имена для обоих конфликтующих классов.

### Модификаторы доступа

Модификаторы доступа - это дополнительные инструкции, указывающие копмилятру на ограничение доступа к классу (или его 
содержимому). Два из четырёх имеющихся модификатора доступа имеют прямое отношение к пакетам.
- _**public**_ - обозначает доступность везде, из любого класса, из любого пакета. Самый широкий доступ.
- _**protected**_ - разрешает доступ только для классов-наследников указанного класса, **и** для всех классов в том же 
    пакете; запрещает доступ всем остальным классам.
- _(пусто, ничего не указано)_ - доступ по умолчанию; также называется `package-default`; разрешает доступ для всех
    классов в том же пакете; запрещает доступ всем остальным классам.  
    В отличие от модификатора `protected` если класс-наследник находится в другом пакете по сравнению со своим 
    родительским классом, то класс-наследник _не будет_ иметь доступа к родительскому классу.
- _**private**_ - разрешает доступ только в этом же классе; запрещает доступ всем остальным классам. Самый ограниченный 
    доступ.