## Наследование

#### Задача

Предположим, нам необходимо создать следующие 4 класса с перечисленными свойствами:

King: 
- healthScore
- speed
- name
- gold

Doctor: 
- healthScore
- speed
- name
- attackScore
- medicineScore

Knight
- healthScore
- speed
- name
- attackScore
- horseSpeed

Infantry
- healthScore
- speed
- name
- attackScore
- armour

Перечисленные классы имеют набор одинаковых свойств, и вероятно, будут иметь схожие методы для работы с этими 
свойствами.

В таком случае, нерационально реализовывать каждый класс в отдельности, а стоит использовать механизм **наследования**
Наследование позволяет выделить в отдельный класс, называющимся **родительским** все общие свойства и методы 
запланированных классов, а остальные классы - будут **наследовать** свойства и методы родительского класса и добавлять 
свои.

В данном случае.
Unit
- healthScore
- speed
- name

BattleUnit
attackScore

и остальные

При наследовании все дочерние классы получают от родительских классов все доступные методы и свойства
Наследование это всегда расширение класса, а не его изменение.

Если дочерний клас **расширяет** родительский, то в дочернем классе есть всё из родительского, и дополнительные 
свои свойства и методы.

Создадим базовый класс `Unit`

    // Unit.java

Классам-потомкам достались "в наследство" не-приватные методы родительского класса. Классы-потомки могут эти методы 
просто использовать; а могут - изменять: переделывать, расширять, и т.д.

Теперь опишем дочерний класс `BattleUnit`

    // BattleUnit.java
    
        /*
         * Для того чтобы указать, что класс - наследуется, необходимо после объявления класса указать ключевое слово
         * extends и указать класс, от которого данный класс наследуется.
         *
         * Unit - родительский класс, суперкласс
         * BattleUnit - дочерний класс, расширенный класс.
         * */
    
        /*
         * Множественное наследование классов через extends -- запрещено.
         * Наследование через extends -- всегда от одного класса.
         * */

В текущей реалицации будет ошибка компиляции: остутствует конструктор класса, соответствующий конструктору 
родительского класса; и нет других конструкторов класса BattleUnit.

Класс BattleUnit расширяет класс Unit, а Unit имеет только один конструктор `Unit(int healthScore, int speed)`
Значит требуется, чтобы BattleUnit в своём конструкторе вызывал конструктор родительского класса Unit

Происходит это потому что при создании объекта класса (при вызове его конструктора), всегда должен вызываться 
конструктор его родителя.
Если у нас в потомке конструктор не определён -- значит используется конструктор по умолчанию `BattleUnit() {}`
В этом конструкторе неявно первой инструкцией прописано

    super();
    
А такого конструктора (без аргументов) в классе Unit -- нет 

Чтобы разрешить эту проблему нужно: либо создать новый конструктор; либо создать конструктор с тем же набором
аргументов, что и родительский конструктор:
    
    // BattleUint.java
    // ... 
    public BattleUnit(int healthScore, int speed, int attackScore) {
        super(healthScore, speed);
        setAttackScore(attackScore);
    }

Опишем все остальные классы: Doctor, Knight, Infantry, King

    // Doctor.java
    // ...
    
    // Knight.java
    // ...
    
    // Infantry.java
    // ...
    
    // King.java
    // ...
    
    
Далее мы хотим, чтобы у всех BattleUnit-ов был метод attack(другого BattleUnit-а).
Если мы укажем attack(Knight knight), например, то BattleUnit может атаковать только рыцаря.
А мы хотим уметь атаковать любого BattleUnit.
Поэтому будем использовать метод attack(BattleUnit u)

Для этого мы объявим контракт, что все классы-наследники BattleUnit должны иметь метод attack(BattleUnit u)

    // CanAttack.java
    
Интерфейс - это особый тип класса. Чем отличается интерфейс от класса:
- на основе интерфейса нельзя создать объект
- до Java 8 у интерфейса могут быть методы только без реализации.

Для того чтобы укзаать, что класс реализаует какой-то интерфейс, необходимо указать ключевое слово `implements` и имя 
интерфейса который он должен реализовать.

    // BattleUnit.java
    // сделали его абстрактным
    
А вот для конкретных классов Doctor, Knight, Infantry мы должны явно реализовать методы интерфейса CanAttack

    // Doctor.java
    @Override
    public void attack(BattleUnit enemy) {
        System.out.println("Infantry attacks " + enemy);
    }
    
Добавим ещё один интерфейс CanRest

    // CanRest.java
    public interface CanRest {
        void rest();
    }

И добавим его в класс Unit.
Плюс добавим реализацию метода rest() во все классы наследники Unit

Начиная с Java 8 появилась возможность в интерфейсах указывать методы с реализацией. 
Они называются методами по умолчанию (дефолтными), в которых указывается реализация метода по умолчанию.

    // CanRest.java
    // ...

Добавим в оба интерфейса метод runFromField();
    
Если класс реализаует два интерфейса, в каждом из которых есть дефолтный метод с одинаковой сигнатурой, нужно в этом 
классе явно указать конкретную реализацию метода, т.к. иначе конфликт: не понятно какую из реализаций метода 
использовать.

На уровне уже BattleUnit необходимо указать какую реалицацию метода runFromField() он должен использовать.

При переопределении метода нельзя понижать модификатор доступа. Но можно повышать.