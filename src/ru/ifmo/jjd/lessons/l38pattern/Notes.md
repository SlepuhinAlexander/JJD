## Паттерны проектирования

Паттерны проектирования - шаблоны организации кода для решения типичных задач.

Паттерны можно разделить на:
- Порождающие: описывают как создавать объекты
- Поведенческие: описывают взаимодействие объектов
- Структурные: описывают структуру классов, объектную модель программы.

### Порождающие

#### Одиночка 

на всю программу существует только один объект такого типа.

Скрывается конструктор и создаётся статическая переменная, хранящая единственный экземпляр этого типа.
И статический метод, возвращающий эту же переменную.

#### Фабричный метод

Метод принимает на вход некоторые данные и на основе них создаёт конкретный экземпляр определённого типа объектов.
Например, метод возвращает экзепляр родительского класса/интерфейса, а внутри себя фактически создаёт объекты дочерних 
типов.

#### Прототип (prototype)

Объекты создаются на основе существующих объектов.
Например, полное клонирование объекта.
Или создание копии объекта с изменёнными данными.

Должен быть интерфейс с методом clone() или что-то вроде того.
Классы, которые будем прототипировать, должны реализовывать этот интерфейс.

В Java это уже есть по умолчанию, т.к. все классы - наследники Object и метод clone() там уже есть.
Остаётся только его переопределить и задать реализацию в конкретном классе.

#### Builder

Используется, например когда для создания объекта нужно передавать большое количество аргументов.
Вместо того чтобы передавать их в конструктор.

#### Пул объектов

Предполагает, что один объект содержит в себе ряд других уже созданных объектов и предоставляет их по требованию.
В Java например пул потоков, пул соединений.

### Поведенческие

#### Инъекция зависимостей

Если свойство класса ссылается на другой объект, то значение этого свойства должно устанавливаться с помощью сеттера 
или конструктора, которые принимают на вход аргумент с **готовым** внедряемым объектом.
Внедряемый объект не должен создаваться внутри этого класса, это не задача класса создавать такой объект.
 
### Структурные

Когда нужны некоторые объекты-сущности, которые будут сохраняться в таблицах базы данных, используются классы-прослойки:
DAO, Репозиторий.

#### Цепочка вызовов

Паттерн по которому объявляется, что ряд методов возвращает ссылку на текущий объект.
Для того чтобы методы такого объекта можно было вызывать по цепочке.

#### Слушатели

Когда класс умеет информировать любое количество любых слушателей об изменениях в себе.

#### DAO 

Явно прописывает методы взаимодействия с базой данных для кокнретной сущности

#### Репозиторий

Более абстрактный способ, который в свою очередь может использовать различные DAO

#### Спецификации

Есть интерфейсы описывающие спецификацию работы с некоторыми объектами, и которые используются другими классами.
А мы реализуем некоторый класс со статическими методами, которые возвращают реализации этих интерфейсов под конкретные 
нужды.

#### MVC

Три уровня организации классов:
View - пользовательский интерфейс. например HTML-файлы, задающие структуру страниц.
Model - сущности (модели + данные), которые используются программой плюс сервисы - реализация логики работы программы.
Controller - передача данных между View и Model.

#### Декоратор

Есть некоторый основной класс с основным функционалом.
И есть некоторый класс(ы) декоратор(ы) дополняют основной функционал.

#### Итератор

Шаблон для последовательного доступа к элементам набора.
В Java уже реализован в коллекциях и мапах. 

#### Команда

Предполагается, что выполняется ряд похожих / однотипных команд.
Тогда реализуется абстрактный класс базовой команды.
В котором определяется набор абстрактных методов, задающих поведение.
Каждая команда имеет ссылку на контекст, в котором она используется (для получения данных).
И есть статичный фабричный метод, который генерирует конкретные реализации команд.

Каждая из команд наследуется от базовой команды и переопределяет методы выполнения команды.