## Условные конструкции

1. [Оператор `if`](#if)
    1. [Оператор `if-else`](#if-else)
    1. [Последовательные условия `else-if`](#else-if)
    1. [Вложенные операторы `if`](#nested-if)
1. [Оператор `switch`](#switch)
    1. [Улучшенный (enhanced) `switch`](#enhanced-switch)
    1. [Улучшенный `switch` с возвращаемым значением](#switch-yield)

**Условные контрукции** (они же конструкции **ветвления**) используются, когда программе в зависимости от какого-то 
условия необходимо выполнить разные действия.

Условных конструкций в языке Java две: 
- `if` (он же`if-else`) - используется, когда проверяемое условие может принимать два альтернативных значения: 
    истина или ложь;
- `switch` - используется, когда проверяемое условие может принимать несколько явных значений.

### Оператор `if` <a name="if"></a>

Базовый синтаксис оператора `if`:

    if (условие) {
        код, который выполнится, только если условие истинно
    }
    код, который выполнится после if в любом случае
    */
    
В операторе `if` наличие `условия` - обязательно. Оно всегда должно принимать значение типа `boolean`.
Блок кода внутри `if` может быть пустым, но тогда применение оператора `if` не имеет смысла.   
Если `условие` равно `true`, исполнение кода идёт по ветке внутри `{ }` оператора `if`, выполняет в нём все инструкции 
и возвращается к коду, следующему за оператором `if`.
Если `условие` равно `false`, код внутри `{ }` оператора `if` **не выполняется**, исполнение сразу переходит к коду, 
следующему за `if`.

Другими словами, код внутри `if` выполнится только если `условие` истинно. Код, следующий после `if` выполнится всегда.

Пример использования:   
Если ответ равен 13, вывести в консоль "Ответ верный".

    if (answer == 13) {
        System.out.println("Ответ верный");
    }

Другой пример:  
Если случайное двузначное целое число - чётное, вывести этот факт в консоль.

[`ConditionalStatementsDemo.java`][Conditionals]

    ifElseDemo() {
        /* <code> */
        int n = (int) (Math.random() * 100); // генерируем случайное целое число от 0 до 99
        if (n % 2 == 0) {
            System.out.println("Число " + n + " - чётное.");
        }
        // Число 68 - чётное.
    }

Часто в `условиях` используют [логические операторы](../l01basesyntax/Operators.md#logical) для комбинации нескольких 
проверок. Например:  
Если случайное число от 0 до 999 - двузначное, вывести этот факт в консоль.

[`ConditionalStatementsDemo.java`][Conditionals]

    ifElseDemo() {
        /* <code> */
        int n = (int) (Math.random() * 1000); // генерируем случайное целое число от 0 до 999
        if (n >= 10 && n <= 99) {
            System.out.println("Число " + n + " двузначное");
        }
        // Число 53 - двузначное
        /* <code> */
    }

#### Оператор `if-else` <a name="if-else"></a>

Условный оператор `if` может содержать необязательный блок `else`.  
Его синтаксис:

    if (условие) {
        код, который выполнится, только если условие истинно
    } else {
        код, который выполнится, только если условие ложно
    }
    код, который выполнится после if в любом случае

Оператор `if-else` задаёт ход программы в обоих альтернативах: 
- если проверяемое `условие` истинно, выполнится ветка кода в `if`;
- если проверяемое `условие` ложно, выполнится ветка кода в `else`;
- после чего для обоих альтернатив выполнение программы продолжится с инструкций, следующих за оператором `if-else`.

Пример использования:  
Если ответ равен 13, вывести в консоль "Ответ верный", в противном случае - вывести в консоль "Ошибка в ответе".

    if (answer == 13) {
        System.out.println("Ответ верный");
    } else {
        System.out.println("Ошибка в ответе");
    }

Другой пример:  
Определить, чётным или нечётным является случайное двузначное целое число. Вывезти результат в консоль.

[`ConditionalStatementsDemo.java`][Conditionals]

    ifElseDemo() {
        /* <code> */
        int n = (int) (Math.random() * 100);
        if (n % 2 == 0) {
            System.out.println("Число " + n + " - чётное.");
        } else {
            System.out.println("Число " + n + " - нечётное.");
        }
        // Число 32 - чётное.
        /* <code> */
    }
    
Если блок кода внутри `if` или `else` состоит из одной инструкции, заключать его в `{ }` *не обязательно*.  
Этот же пример более компактно:
 
    int n = (int) (Math.random() * 100);
    if (n % 2 == 0) System.out.println("Число " + n + " - чётное.");
    else System.out.println("Число " + n + " - нечётное.");

Для удобства читаемости рекомендуется использовать одинаковое форматирование кода для блоков `if` и `else`: т.е. если 
один из блоков заключён в `{ }`, то второй будет нагляднее - тоже заключить в `{ }`, даже если он состот из одной 
инструкции. 

#### Последовательные условия `else-if` <a name="else-if"></a>

Условная конструкция `if-else` может быть расширена для **последовательной** проверки нескольких условий.
Синтаксис:

    if (условие1) {
        код, который выполнится, если условие1 истинно.
            // далее программа переходит к строке //##
    } else if (условие2) {
            // условие2 проверяется только если условие1 ложно.
        код, который выполнится, если условие1 ложно, а условие2 истинно
            // далее программа переходит к строке //##
    } else if (условие3) {
            // условие3 проверяется только если условие1 и условие2 ложны
        код, который выполнится, если и условие1 ложно и условие2 ложно, а условие3 истинно
            // далее программа переходит к строке //##
    } else {
        код, который выполнится, если все три условия ложны
            // секция else так же необязательна
            // далее программа переходит к строке //##
    }
    // ##
    // код, который выполнится после if в любом случае
    
Очередное условие в `else if` проверяется *только если все предыдущие условия были ложны*.  
Если очередное условие истинно, выполняется соответствующий блок кода внутри `if` (`else if`); дальнейшие условия *не 
проверяются*; исполнение программы переходит к коду следующему за всей конструкцией `if`.  
Если очередное условие ложно, исполнение кода переходит к проверке следующего условия (если оно есть), или к блоку, 
`else` если следующего условия нет.  
Блок `else` по-прежнему необязательный, поэтому если его нет и все условия оказались ложны, то никакое из ответвлений 
кода не будет выполнено, а программа перейдёт к коду, следующему за всем блоком `if`.

Таким образом, в конструкциях `else-if` будет выполнен как максимум один из вариантов ответвления кода.  
Количество дополнительных условий `else-if` не ограничено.

Пример:  
В переменной `minute` предположительно лежит число от 0 до 59 (хранящее число минут).  
Определить в какую четверть часа попадает данное число и ответ вывести в консоль: 
-  0-14 : "первая четверть"
- 15-29 : "вторая четверть"
- 30-44 : "третья четверть"
- 45-59 : "четвёртая четверть"

[`ConditionalStatementsDemo.java`][Conditionals]

    ifElseDemo() {
        /* <code> */
        minute = (int) (Math.random() * 100) - 10;
        System.out.print(minute + " мин. - это ");
        if (minute < 0 || minute >= 60) {
            System.out.println("некорректное значение.");
        } else if (minute < 15) {
            System.out.println("первая четверть.");
        } else if (minute < 30) {
            System.out.println("вторая четверть.");
        } else if (minute < 45) {
            System.out.println("третья четверть.");
        } else {
            System.out.println("четвёртая четверть.");
        }
        // 55 мин. - это четвёртая четверть.
        /* <code> */
    }        

#### Вложенные операторы `if` <a name="nested-if"></a>

Внутри блоков кода `if`, `else if` и `else` могут содержаться *любые инструкции*. В том числе, там можно расположить 
ещё один оператор `if`, если при выполнении какого-то условия, нужно сделать ещё одну проверку и выполнить ещё одно 
ветвление кода.  
Вложенность условных операторов не ограничена.

Пример:  
Проверить, является ли случайное целое число положительным или отрицательным, чётным или нечётным. 
Результат вывести в консоль.

[`ConditionalStatementsDemo.java`][Conditionals]

    ifElseDemo() {
        /* <code> */
        int num = (int) (Math.random() * 1999) - 999; // [-999; 999]
        System.out.print("Число " + num + " - ");
        if (num > 0) {
            System.out.print("положительное");
            if (num % 2 == 0) {
                System.out.println(" чётное.");
            } else {
                System.out.println(" нечётное.");
            }
        } else if (num < 0) {
            System.out.println("отрицательное.");
        } else {
            System.out.println("ноль.");
        }
        // Число -665 - отрицательное
        /* <code> */
    }
    
### Оператор `switch` <a name="switch"></a>

В отличие от оператора `if`, который проверяет на истинность выражение с типом `boolean`, оператор `switch` проверяет 
выражение *любого* типа на *равенство* конкретному значению. Количество проверяемых вариантов значений не ограничено.

Синтаксис `switch`:

    switch (выражение) {
        case значение1:
            // <code1>, 
            break;
        case значение2:
            // <code2>
            break;
        case значение3:
            // <code3>
        case значение4:
            // <code4>
            break;
        default:
            // <code default>, который выполняется если не один case не совпал.
    }
    
Все проверяемые варианты `значений` (случаев / `case`) должны быть уникальны.  
Конструкция `switch` вычисляет значение `выражения` и последовательно проверяет его на *равенство* с указанными 
вариантами `значений`.  
Если соответствие установлено, код внутри `switch` начинает выполняться **от точки** соответствующего *совпавшего* 
`case` и далее **пока не встретится инструкция** `break;`, **или закончится** весь блок `switch`.  
Если ни один случай не совпал, и создан вариант `default` - выполняется вариант `default`;   
если ни один случай не совпал и `default` не задан - не выполняется ни одна из веток кода.

То есть, если инструкции `break` не использовать, то будет выполнен код, соответствующий совпадению с несколькими 
вариантами `значений`. В приведённом примере синтаксиса, если `выражение` равно `значению3`, то выполнится и блок кода 
`code3`, и блок кода `code4`.    
Блоки кода могут быть и пустыми.  
Обе эти особенности оператора `switch` позволяют *объединять* несколько вариантов `значений` `выражения`, для которых 
требуется выполнить одинаковую ветку кода.

Инструкция `break;` означает прерывание. C её помощью можно [прервать выполнение цикла](Loops.md#break-continue) и 
прервать выполнение оператора `switch`.  
Посокольку вариант `default` указывается последним в операторе `switch` в его блоке кода использовать `break;` не 
требуется, оператор `switch` и так там заканчивается.  

Пример использования:  
Игральный автомат выдаёт случаное трёхзначное число.  
Если выпало 111 или 222 или 333 - игрок получает малый приз  
Если выпало 444 или 555 - игрок получает средний приз  
Если выпало 777 - игрок получает крупный приз  

[`ConditionalStatementsDemo.java`][Conditionals]

    switchDemo() {
        /* <code> */
        int num = ((int) (Math.random() * 9) + 1) * 111; // для простоты проверки сделаем все три цифры одинаковыми
        switch (num) {
            case 111:
            case 222:
            case 333:
                System.out.println(num + " малый приз");
                break;
            case 444:
            case 555:
                System.out.println(num + " средний приз");
                break;
            case 777:
                System.out.println(num + " крупный приз");
                break;
            default:
                System.out.println(num + " попробуйте ещё раз");
        }
        // 666 попробуйте ещё раз
        /* <code> */
    }
    
#### Улучшенный (enhanced) `switch` <a name="enhanced-switch"></a>

В версиях Java 12 и 13 стало возможно использовать оператор `switch` в улучшенной, более компактной форме.  
Улучшенный ситнаксис: 

     switch (выражение) {
         case значение1, значение2, значение3 -> {<code1>, который нужно выполнить при любом из этих трёх случаев};
         case значение4, значение5 -> {<code2>, который нужно выполнить при любом из этих двух случаев};
         case значение6 -> {<code3>, который нужно выполнить если выражение == значение6};
         default -> {<code>, который нужно выполнить если не один case не совпал}
     }

В такой форме инструкции `break;` использовать уже не требуется, а несколько вариантов значений с одинаковой веткой 
применяемого кода - можно объединять просто перечислив их через запятую.

Тот же пример про игральный автомат принимает вид:

[`ConditionalStatementsDemo.java`][Conditionals]

    switchDemo() {
        /* <code> */
        int num = ((int) (Math.random() * 9) + 1) * 111;
        switch (num) {
            case 111, 222, 333 -> System.out.println(num + " малый приз");
            case 444, 555 -> System.out.println(num + " средний приз");
            case 777 -> System.out.println(num + " крупный приз");
            default -> System.out.println(num + " попробуйте ещё раз");
        }
        // 111 малый приз
        /* <code> */
    }

Если действие для конкретного `case` состоит только из одной инструкции, оборачивать его в `{ }` не обязательно. 
Конструкции 

     default -> System.out.println(num + " попробуйте ещё раз");

и

     default -> {
        System.out.println(num + " попробуйте ещё раз");
     }

равнозначны. Если инструкций более 1, то обернуть их в блок кода (`{ }`) синтаксически обязательно.

#### Улучшенный `switch` с возвращаемым значением <a name="switch-yield"></a>

Также в версиях Java 12 и 13 оператор `switch` может вычислять значение и возвращать его вызывающему коду;

Тот же пример про игральный автомат можно переписать как:

[`ConditionalStatementsDemo.java`][Conditionals]

    switchDemo()
        /* <code> */
        num = ((int) (Math.random() * 9) + 1) * 111;
        String result = switch (num) {
            case 111, 222, 333 -> num + " малый приз";
            case 444, 555 -> num + " средний приз";
            case 777 -> num + " крупный приз";
            default -> num + " попробуйте ещё раз";
        };
        System.out.println(result);
        // 777 крупный приз
        /* <code> */
    }
    
Здесь переменной `result`, в зависимости от совпадения значения `num` с каким-либо из вариантов (`case`) присваивается 
значение соответствующего выражения.

Если конкретному `case` соответствует блок кода (когда необходимо выполнить несколько инструкций), в нём нужно явно 
указать какое значение возвращается в `switch`.  
В Java 12 это выполняется директивой `break`;  
В Java 13 - ключевым словом `yield`.

Для демонстрации, случай

            default -> num + " попробуйте ещё раз";

можно искусственно переписать как

            default -> {
                String msg = " попробуйте ещё раз";
                System.out.print(num);
                // break msg; // Java 12
                yield msg; // Java 13
            }

<!--  ------------------------------  -->
[Conditionals]: ConditionalStatementsDemo.java
