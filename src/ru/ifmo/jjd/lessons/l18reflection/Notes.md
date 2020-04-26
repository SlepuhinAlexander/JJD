## Рефлексия

Рефлексия - это механизм динамической обработки информации о классах программы. В Java рефлексия осуществляется с 
помощью набора специальных классов, входящих в Java Reflection API. С его помощью можно получать информацию о *классах, 
интерфейсах, конструкторах, полях, методах и их аргументах* -- **во время выполнения программы**, для чего нужна только 
ссылка на класс. Также Reflection API позволяет на основе этой информации создавать новые экземпляры классов, вызывать 
методы, получать или устанавливать значения полей. В том числе приватных.

Все *классы* в java **являются объектами типа Class**.
В том числе примитивы (не обёртки, а именно примитивы).

[ReflectionDemo](ReflectionDemo.java)
Получение ссылки на класс.
Методы класса класс.
Получение интерфейсов класса
Получение ссылки родительского класса
Доступ к компонентам класса: конструкторам, полям и методам


Установка `SecurityManager` позволит защитить программу от `setAccessible()`

    static {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }

Если SecurityManager установлен, любой вызов метода setAccessible приведёт к исключению 
`java.security.AccessControlException`.

## Аннотации

**Аннотации** - это дополнительные метаданные, которые можно прикрепить к классу, полю методу, аргументам, пакету.
Аннотации могут быть любыми.
Эта информация может быть использована на моменте компиляции и на моменте выполнения.

Часто используется на моменте выполнения. 

Пример аннотации `@Override`: это аннотация, которую мы используем, она уже создана в языке.

Можно создавать свои аннотации.
Аннотация создаётся так же как интерфейс, только перед ключевым словом interface указывается символ `'@'`.  
Правила именования аннотаций такие же как классов, интерфейсов и т.д.

    public @interface Required {
    }

Для того чтобы указать к чему аннотацию можно применить, нужно использовать аннотацию `@Target()`
Если одна цель - можно без фигурных скобок, если несколько - нужно заключить в фигурные скобки через запятую.
Аннотация `@Retention` указывает на то, КОГДА будет использована аннотация:
- только в исходном коде; 
- в исходном коде плюс при компиляции, но не во время выполнения; 
- и в исходном коде, и при компиляции, и во время выполнения.

Использование аннотации: указание её перед тем местом где её нужно применить через `@ИмяАннотации`

Создали ещё одну аннотацию `ConfigClass`

Помимо того, чтобы служить маркером, аннотация может хранить дополнительную информацию. Её нужно объявить в описании 
аннотации.

далее [AnnotationsDemo](annotations/AnnotationsDemo.java).