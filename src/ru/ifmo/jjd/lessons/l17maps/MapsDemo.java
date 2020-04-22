package ru.ifmo.jjd.lessons.l17maps;

import java.lang.ref.WeakReference;
import java.util.*;

public class MapsDemo {
    public static User cbf = new User("cbf", "12443", Role.USER);
    public static User asd = new User("asd", "2625", Role.ADMIN);
    public static User rty = new User("rty", "8734", Role.USER);
    public static User bnm = new User("bnm", "2688", Role.ADMIN);

    public static void main(String[] args) {
        /*
         * Map в Java - структура данных, которая хранит данные в виде пары ключ-значение.
         * Ключи должны быть уникальными.
         * Каждому ключу соответствует только одно значение.
         * Map НЕ расширяет интерфейс Collection
         * */
        hashMapDemo();
        mapIterationDemo();
        enumMapDemo();
        treeMapDemo();
        weakHashMapDemo();
    }

    public static void hashMapDemo() {
        System.out.println("---HashMap---");
        /*
         * 1. хранит ключи в хеш-таблице. Для того чтобы положить элемент в HashMap - вычисляет hashCode ключа
         * 2. для объектов, которые используются в качестве ключей ОБЯЗАТЕЛЬНО должны быть переопределены методы
         *      equals() и hashCode()
         * 3. порядок хранения элементов может отличаться от порядка добавления
         * 4. null можно использовать в качестве ключа
         * */

        // создание объекта HashMop
        /*
         * Все мапы дженерики, необходимо указать типы данных для ключей и значений.
         * */
        HashMap<String, User> users = new HashMap<>();

        // добавление элементов в HashMap
        /*
         * Для данной мапы в качестве ключей используются логины пользователей,
         * в качестве значений - соответствующие объекты типа User.
         *
         * Добавление нового элемента в карту выполняет метод put();
         * */
        users.put(asd.getLogin(), asd);
        users.put(cbf.getLogin(), cbf);
        users.put(rty.getLogin(), rty);
        users.put(bnm.getLogin(), bnm);
        users.put(null, null);
        /*
         * Добавили в карту 5 пар - 5 элементов ключ-значение.
         * */
        printMap(users);
        /*
         * bnm=User{login='bnm', pwd='2688', role=ADMIN}
         * null=null
         * asd=User{login='asd', pwd='2625', role=ADMIN}
         * cbf=User{login='cbf', pwd='12443', role=USER}
         * rty=User{login='rty', pwd='8734', role=USER}
         * */

        // получение значения из мапы.
        /*
         * Доступ к элементам мапы осуществляется по ключу: по конкретному ключу мы можем получить значение
         * метод get()
         * */
        System.out.println();
        System.out.println(users.get("asd")); // User{login='asd', pwd='2625', role=ADMIN}
        System.out.println(users.get("ddd")); // null
        /*
         * По неизвестному ключу вернётся null. Ошибок не будет.
         * */
        // метод getOrDefault()
        System.out.println(users.getOrDefault("nnn", asd)); // User{login='asd', pwd='2625', role=ADMIN}
        /*
         * Возвращает значение, соответствующее ключу.
         * Если его нет, возвращает значение по умолчанию (второй аргумент метода).
         * Значение по умолчанию возвращается ТОЛЬКО если ключ не найден.
         * */

        // удаление элемента из мапы
        users.remove("asd");
        /*
         * Удаляет элемент (пару) с ключом равным переданному аргументу.
         * */
        users.remove("bnm", bnm);
        /*
         * Удаляет элемент (пару) c ключом равным переданному аргументу ТОЛЬКО ЕСЛИ его значение равно второму
         * переданному аргументу.
         * */

        // обновление элемента в мапе
        users.replace(null, asd);
        /*
         * Если такого ключа нет - ничего не произойдёт.
         * Если такой ключ есть - заменит значение, какое бы оно ни было, на указанное значение.
         * */
        users.replace(null, asd, null);
        /*
         * Если такого ключа нет - ничего не произойдёт.
         * Если ключ есть, но переданный второй аргумент (текущее значение) - ничего не произойдёт.
         * Только если есть такая запись (с таким ключом-значением) - заменит значение на новое.
         * */

        /*
         * Проработать методы:
         * clear()
         * containsKey(Object key)
         * containsValue(Object value)
         * entrySet()
         * get(Object key)
         * getOrDefault(Object key, V defaultValue)
         * isEmpty()
         * keySet()
         * put(K key, V value)
         * putAll(Map<? extends K,? extends V> m)
         * putIfAbsent(K key, V value)
         * remove(Object key)
         * remove(Object key, Object value)
         * replace(K key, V value)
         * replace(K key, V oldValue, V newValue)
         * size()
         * values()
         * */
    }

    public static void mapIterationDemo() {
        HashMap<String, User> users = new HashMap<>();
        users.put(asd.getLogin(), asd);
        users.put(cbf.getLogin(), cbf);
        users.put(rty.getLogin(), rty);
        users.put(bnm.getLogin(), bnm);
        users.put(null, null);

        System.out.println("---Iterating Through Map---");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        /*
         * ---Iterating Through Map---
         * bnm=User{login='bnm', pwd='2688', role=ADMIN}
         * null=null
         * asd=User{login='asd', pwd='2625', role=ADMIN}
         * cbf=User{login='cbf', pwd='12443', role=USER}
         * rty=User{login='rty', pwd='8734', role=USER}
         * */
    }

    public static void enumMapDemo() {
        System.out.println("---EnumMap---");
        /*
         * Особенности enumMap:
         * - в качестве ключей использует enum (все ключи должны быть одного типа, т.е. принадлежать одному enum
         * - нельзя использовать null в качестве ключа
         * - порядок хранения элементов соответствует порядку элементов enum
         * - значения (values) хранит в массиве (размер массива - количество элементов enum)
         * - для извлечения элемента из массива использует порядковый номер ключа в энуме:
         *      метод ordinal() - vals[key.ordinal()]
         * */

        // создание объекта EnumMap
        /*
         * В конструкторе ОБЯЗАТЕЛЬНО нужно указать ССЫЛКУ на enum, который был выбран в качестве ключей.
         * Ссылка на класс enum: ClassName.class
         * */
        EnumMap<Role, ArrayList<User>> users = new EnumMap<>(Role.class);
        users.put(Role.USER, new ArrayList<>(Arrays.asList(cbf, rty)));
        users.put(Role.ADMIN, new ArrayList<>(Arrays.asList(asd, bnm)));
        printMap(users);
        /*
         * USER=[User{login='cbf', pwd='12443', role=USER}, User{login='rty', pwd='8734', role=USER}]
         * ADMIN=[User{login='asd', pwd='2625', role=ADMIN}, User{login='bnm', pwd='2688', role=ADMIN}]
         * */
        System.out.println();
        // получение объекта по ключу
        System.out.println(users.get(Role.ADMIN)); // метод вернёт ArrayList<User>
        // [User{login='asd', pwd='2625', role=ADMIN}, User{login='bnm', pwd='2688', role=ADMIN}]

        // Вывести в консоль логины всех пользователей с ролью USER
        System.out.println("Users with role User: ");
        ArrayList<User> userArrayList = users.get(Role.USER);
        for (User user : userArrayList) {
            System.out.println(user.getLogin());
        }
        /*
         * Users with role User:
         * cbf
         * rty
         * */

        // добавить объект пользователя в enumMap в зависимости от роли
        User newUser = new User("newUser", "444", Role.USER); // данные пользователя случайны.
        users.get(newUser.getRole()).add(newUser);
    }

    public static void treeMapDemo() {
        System.out.println("---TreeMap---");
        /*
         * Особенности TreeMap
         * - хранит элементы в отсортированном по ключам виде
         * - основан на алгоритме красно-чёрного дерева
         * - null не может быть использован в качестве ключа
         * - класс, объекты которого являются ключами(!) должен реализовывать интерфейс Comparable, либо объект
         *      Comparator должен передаваться в конструктор TreeMap;
         * */
        HashMap<String, User> users = new HashMap<>();
        users.put(asd.getLogin(), asd);
        users.put(cbf.getLogin(), cbf);

        // Создание объекта TreeMap
        /*
         * Любой Map можно создавать на основе другого Map
         * (Только нужно учитывать хранение null: если null не поддерживается, а передан null одним из элементов map -
         * создание упадёт с exception)
         * */
        TreeMap<String, User> treeMap = new TreeMap<>(users);
        printMap(treeMap);
        /*
         * asd=User{login='asd', pwd='2625', role=ADMIN}
         * cbf=User{login='cbf', pwd='12443', role=USER}
         * */
        System.out.println();
        treeMap.put(asd.getLogin(), asd);
        treeMap.put(rty.getLogin(), rty);
        treeMap.put(bnm.getLogin(), bnm);
        printMap(treeMap);
        /*
         * asd=User{login='asd', pwd='2625', role=ADMIN}
         * bnm=User{login='bnm', pwd='2688', role=ADMIN}
         * cbf=User{login='cbf', pwd='12443', role=USER}
         * rty=User{login='rty', pwd='8734', role=USER}
         * */
        System.out.println();

        // итерация по TreeMap
        for (Map.Entry<String, User> entry : treeMap.entrySet()) {
            if (entry.getValue().getRole().equals(Role.USER)) {
                System.out.println(entry.getKey());
            }
        }
        /*
         * cbf
         * rty
         * */
    }

    public static void weakHashMapDemo() {
        System.out.println("---WeakHashMap---");
        /*
         * 1. хранит ключи в хэш-таблице
         * 2. null можно использовать в качестве ключа
         * 3. запись в WeakHashMap может быть удалена если на ключ не осталось сильных ссылок
         * */

        // (безотносительно мап-ов)
        // сильная ссылка - strong reference
        User user = new User("user", "3425", Role.USER);
        // слабая ссылка - weak reference
        WeakReference<User> weakUser = new WeakReference<>(user);

        /*
        * Если на объект есть сильная ссылка - он сборщиком мусора удалён не будет. Даже при нехватки памяти.
        * Если на объект остались только слабые ссылки - то этот объект доступен для удаления сборщиком мусора.
        * Если не хватит памяти сборщик мусора может удалить этот объект.
        * */

        // создание объекта weakHashMap
        WeakHashMap<Object, String> weakHashMap = new WeakHashMap<>();
        Object weakHashKey = new Object();
        String weakHashVal = "String Value";
        weakHashMap.put(weakHashKey, weakHashVal);

        HashMap<Object, String> hashMap = new HashMap<>();
        Object hashKey = new Object();
        String hashVal = "String Value";
        hashMap.put(hashKey, hashVal);

        weakHashKey = null;
        hashKey = null;
        System.gc();
        System.out.println(weakHashMap);
        System.out.println(hashMap);
    }

    public static <K, V> void printMap(Map<K, V> map) {
        map.keySet().forEach(k -> System.out.println(k + "=" + map.get(k)));
    }
}
