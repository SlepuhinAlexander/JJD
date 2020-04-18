## Обобщения

В разработке бывают ситуации, когда выбор типа какого-то поля нужно сделать при создании объекта, а не при описании 
класса. Именно для этого в языке есть функциональность под названием Generics (или обобщённые типы).

    public class User {
        private int id;
        private String login;
        private String pwd;
    }
    
В текущей реализации можно в классе User использовать id только типа int. Допустим, мы хотим, чтобы тип поля id можно 
было выбирать в момент создания объекта.

Для этого в объявлении класса необходимо указать genetic тип `<T>` и в самом поле указать использование этого типа T.
Вместо T может быть **любое** имя типа.

    public class User<T> {
        private T id;
        private String login;
        private String pwd;
    }

[User](User.java) - создали класс User с одним generic типам
 
[GenericsDemo](GenericsDemo.java) - создали различные объекты User

[PairContainer](PairContainer.java) - создали класс PairContainer с двумя generic типами

[GenericsDemo](GenericsDemo.java) - создали различные объекты PairContainer
Уточнили про использование generic типов с наследованием: строго ограничено, наследование в generic типах не 
используется.
За исключением наследования от Object: если не указать generic тип, будет использоваться Object.

----

дальше перешли в [гараж](garage)
создали класс [Garage](garage/Garage.java)
в [GarageTest](garage/GarageTest.java) создали объекты Garage.