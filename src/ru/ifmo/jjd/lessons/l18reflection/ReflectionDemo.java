package ru.ifmo.jjd.lessons.l18reflection;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionDemo {
    // установка менеджера безопасности
    static {
/*
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
*/
    }

    public static void main(String[] args) {
        /*
         * Рефлексия в Java - это механизм, с помощью которого можно получить информацию о классах, интерфейсах, полях
         * и методах во время выполнения программы, для чего нужна ссылка на класс.
         * Кроме того, Reflection API даёт возможность создавать новые экземпляры классов, вызывать методы, а также
         * получать или устанавливать значения полей.
         * */

        // Все классы в java являются объектами типа Class
        // Получение ссылки на классы:
        // 1. Через имя класса.
        Class stringClass = String.class;
        System.out.println(stringClass); // class java.lang.String
        Class intClass = int.class;
        System.out.println(intClass); // int
        Class textMessageClass = TextMessage.class;
        System.out.println(textMessageClass); // class ru.ifmo.jjd.lessons.l18reflection.TextMessage
        /*
         * Типовая ситуация - есть набор (сет) классов (заранее неизвестный), по нему итерацией проходим, и что-то
         * делаем с сылками на классы
         * */

        //2. получение ссылки на класс через объект
        TextMessage textMessage = new TextMessage("Срочное сообщение");
        textMessage.setText("Рефлексия");
        Class classByObject = textMessage.getClass();
        System.out.println(classByObject); // class ru.ifmo.jjd.lessons.l18reflection.TextMessage
        int someInt = 123;
        /*
         * метод getClass определён в Object, доступен для всех объектов.
         * */

        // методы класса Class
        /*
         * Поскольку класс Class является классом, у него тоже есть свои методы.
         */
        // получения названия класса, название пакета
        String className = textMessageClass.getName();
        String packageName = textMessageClass.getPackageName();
        //String packageName = textMessageClass.getPackage().getName(); // для более старых версий Java
        System.out.println(className); // ru.ifmo.jjd.lessons.l18reflection.TextMessage
        System.out.println(packageName); // ru.ifmo.jjd.lessons.l18reflection

        // получение списка интерфейсов, которые класс реализует (объявил, что реализует).
        Class[] interfaces = textMessageClass.getInterfaces();
        System.out.println(Arrays.toString(interfaces)); // [interface java.io.Serializable]
        /*
         * возвращает только! интерфейсы, указанные в объявлении класса через implements
         * */

        // получение ссылки на родительский класс
        System.out.println(textMessageClass.getSuperclass()); // class ru.ifmo.jjd.lessons.l18reflection.Message
        System.out.println(textMessageClass.getSuperclass().getSuperclass()); // class java.lang.Object
        System.out.println(textMessageClass.getSuperclass().getSuperclass().getSuperclass()); // null
        System.out.println(interfaces[0].getSuperclass()); // null
        /*
         * Если у класса нет родителя - метод вернёт null
         * К интерфейсам тоже можно использовать.
         *
         * Полученная ссылка - является ссылкой на класс, для которой можно применять все рефлексивные методы.
         * */
        // например, получение всех интерфейсов родительского класса к textMessageClass
        System.out.println(Arrays.toString(textMessageClass.getSuperclass().getInterfaces())); // []

        // Доступ к компонентам класса
        Class<TextMessage> tmClass = TextMessage.class;
        // доступ к полям класса
        // получение публичных полей класса
        Field[] fields = tmClass.getFields();
        /*
         * У всех полей класса тип данных поля - Field.
         * Метод getFields() возвращает все ПУБЛИЧНЫЕ поля класса (включая всех предков).
         * */
        System.out.println(Arrays.toString(fields)); // []
        /*
         * У нас нет публичных полей в иерархии классов для TextMessage, поэтому массив пустой.
         * */

        // получение всех полей класса
        Field[] declaredFields = tmClass.getDeclaredFields();
        /*
         * метод getDeclaredFields() возвращает все поля класса, с любыми модификаторами доступа.
         * Без полей предков
         * */
        System.out.println(Arrays.toString(declaredFields));
        // [private java.lang.String ru.ifmo.jjd.lessons.l18reflection.TextMessage.text]

        // получение методов класса
        Method[] methods = tmClass.getMethods();
        /*
         * метод getMethods() возвращает все ПУБЛИЧНЫЕ методы класса (включая всех предков).
         * */
        System.out.println(Arrays.toString(methods));
        /*
         * [public java.lang.String ru.ifmo.jjd.lessons.l18reflection.TextMessage.getText(),
         *  public void ru.ifmo.jjd.lessons.l18reflection.TextMessage.setText(java.lang.String),
         *  public java.lang.String ru.ifmo.jjd.lessons.l18reflection.Message.getTitle(),
         *  public java.time.LocalDateTime ru.ifmo.jjd.lessons.l18reflection.Message.getCreated(),
         *  public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException,
         *  public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException,
         *  public final void java.lang.Object.wait() throws java.lang.InterruptedException,
         *  public boolean java.lang.Object.equals(java.lang.Object),
         *  public java.lang.String java.lang.Object.toString(),
         *  public native int java.lang.Object.hashCode(),
         *  public final native java.lang.Class java.lang.Object.getClass(),
         *  public final native void java.lang.Object.notify(),
         *  public final native void java.lang.Object.notifyAll()]
         * */
        Method[] declaredMethods = tmClass.getDeclaredMethods();
        /*
         * метод getDeclaredMethods() возвращает все методы класса, с любыми модификаторами доступа.
         * Без методов предков.
         * */
        System.out.println(Arrays.toString(declaredMethods));
        /*
         * [public java.lang.String ru.ifmo.jjd.lessons.l18reflection.TextMessage.getText(),
         * public void ru.ifmo.jjd.lessons.l18reflection.TextMessage.setText(java.lang.String)]
         * */

        // доступ к конструкторам класса
        Constructor[] constructors = tmClass.getConstructors();
        /*
         * метод getMethods() возвращает все ПУБЛИЧНЫЕ конструкторы класса.
         * */
        System.out.println(Arrays.toString(constructors));
        // [public ru.ifmo.jjd.lessons.l18reflection.TextMessage(java.lang.String)]
        Constructor[] declaredConstructors = tmClass.getDeclaredConstructors();
        /*
         * метод getDeclaredConstructors() возвращает все конструкторы класса с любыми модификаторами доступа.
         * */
        System.out.println(Arrays.toString(declaredConstructors));
        // [public ru.ifmo.jjd.lessons.l18reflection.TextMessage(java.lang.String)]

        System.out.println();
        // создание объекта, используя рефлексию
        /*
         * для начала нужно получить конструктор класса: необходимо знать последовательность и типы данных аргументов.
         * */
        Constructor<TextMessage> tmConstructor = null;
        try {
            tmConstructor = tmClass.getDeclaredConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        /*
         * вызываем метод getDeclaredConstructor(), передаём ему в аргументы ССЫЛКИ НА КЛАССЫ аргументов искомого
         * конструктора
         *
         * Метод бросает проверяемое исключение NoSuchMethodException. Его необходимо обработать.
         * */

        // создание объекта, используя полученный конструктор.
        TextMessage reflectionMessage = null;
        try {
            if (tmConstructor != null) {
                reflectionMessage = tmConstructor.newInstance("Reflect Message");
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        /*
         * Вызываем метод newInstance у конструктора, передаём ему в аргументы необходимые аргументы конструктора.
         * Метод newInstance тоже бросает проверяемые исключения, которые необходимо обработать.
         * */
        if (reflectionMessage != null) {
            System.out.println(reflectionMessage.getTitle()); // Reflect Message
        }

        // доступ к полю private String text
        Field field = null;
        try {
            field = tmClass.getDeclaredField("text");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        /*
         * Метод getDeclaredField по имени поля (в описании класса) вернёт ссылку на это поле в классе
         * text - название поля в классе Text Message
         * */
        if (field != null) {
            System.out.println(field.getType()); // class java.lang.String
            System.out.println(field.getName()); // text
            field.setAccessible(true);
            /*
             * Поле приватное, необходимо установить к нему доступ: для этого есть метод setAccessible() класса Field
             * */
            try {
                System.out.println(field.get(reflectionMessage)); // null
                /*
                 * получение значения поля для конкретного объекта: передаём в метод get ссылку на объект.
                 * */
                field.set(reflectionMessage, "Значение установлено через рефлексию");
                /*
                 * установка значения поля для конкретного объекта: передаём объект и новое значение.
                 * */
                System.out.println(field.get(reflectionMessage)); // Значение установлено через рефлексию
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // доступ к методу private void printInfo(String prefix, String postfix)
        Method method = null;
        try {
            method = tmClass.getDeclaredMethod("printInfo", String.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (method != null) {
            System.out.println(method.getName()); // printInfo
            System.out.println(method.getReturnType()); // void
            method.setAccessible(true);
            // вызов метода конкретного объекта
            try {
                method.invoke(reflectionMessage, "???", "!!!");
                // ??? Значение установлено через рефлексию !!!
                /*
                 * вызывается метод invoke от метода, куда передаём - объект, и все аргументы метода.
                 * */
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            boolean isPrivate = Modifier.isPrivate(method.getModifiers());
            System.out.println("is method 'printInfo' private? " + isPrivate); // is method 'printInfo' private? true
            /*
             * Класс Modifier содержит ряд методов, проверяющих модификаторы класса и его компонентов.
             * Получить модификаторы можно методом getModifiers()
             * */
        }
    }
}
