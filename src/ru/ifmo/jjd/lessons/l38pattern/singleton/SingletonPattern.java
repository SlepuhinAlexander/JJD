package ru.ifmo.jjd.lessons.l38pattern.singleton;

public class SingletonPattern {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.data = "Some data";

        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton2.data); // Some data

        Singleton singleton3 = Singleton.getInstance();
        System.out.println(singleton3.data); // Some data
    }
}

// ленивая (отложенная) инициализация
class Singleton {
    private static Singleton instance;
    public String data;

    // закрываем возможность создания объекта вне класса
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) { // проверка был ли создан объект
            // создание объекта (если он не был создан ранее)
            instance = new Singleton();
        }
        return instance;
    }
}

// thread - safe Singleton
class Singleton2 {
    private static Singleton2 instance;

    // закрываем возможность создания объекта вне класса
    private Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) { // проверка был ли создан объект
            // создание объекта (если он не был создан ранее)
            instance = new Singleton2();
        }
        return instance;
    }
}

class Singleton3 {
    private static volatile Singleton3 instance;
    /*
     * модицфикатор volatile используется при работе с в многопоточной среде.
     * считав переменную, каждый поток каждый поток складывает её значение в свой кэш.
     * к общей памяти далее они не обращаются.
     *
     * модификатор volatile означает, что поток не должен складывать эту переменную в кэш, а всегда обращаться к общей
     * памяти.
     * */


    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        Singleton3 local = instance;
        if (local == null) {
            synchronized (Singleton3.class) {
                local = instance;
                if (local == null) {
                    instance = local = new Singleton3();
                }
            }
        }
        return local;
    }
}

class Singleton4 {
    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        return SingletonInst.SINGLETON_INSTANCE;
    }

    public static class SingletonInst {
        public static final Singleton4 SINGLETON_INSTANCE = new Singleton4();
    }
}