package ru.ifmo.jjd.exercises.e16reflection.zoo;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionTask {
    public static void main(String[] args) {
        beautify(toString(generate()));
    }

    private static void beautify(String s) {
        String padding = "    ";
        int pad = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            switch (chars[i]) {
                case '{', '[' -> {
                    System.out.println(chars[i]);
                    pad++;
                    System.out.print(padding.repeat(pad));
                }
                case ',' -> {
                    System.out.println(chars[i]);
                    System.out.print(padding.repeat(pad));
                }
                case ']', '}' -> {
                    pad--;
                    System.out.println();
                    System.out.print(padding.repeat(pad));
                    System.out.print(chars[i]);
                }
                case ' ' -> {
                    if (i > 1 && chars[i - 1] == ',') continue;
                    System.out.print(chars[i]);
                }
                default -> System.out.print(chars[i]);
            }
        }
    }

    public static String toString(Object o) {
        if (o == null) return "null";
        Class<?> c = o.getClass();
        StringBuilder builder = new StringBuilder();
        if (c == Boolean.class || c == Byte.class || c == Short.class || c == Integer.class ||
                c == Long.class || c == Float.class || c == Double.class) {
            return o.toString();
        }
        if (c == String.class || c == Character.class || c.isEnum()) {
            return "'" + o.toString() + "'";
        }
        if (c.isArray()) {
            Object[] objects = ((Object[]) o);
            builder.append("[");
            for (Object object : objects) {
                builder.append(toString(object)).append(", ");
            }
            if (objects.length > 0) builder.setLength(builder.length() - 2); // crop last delimiter;
            builder.append("]");
            return builder.toString();
        }
        if (o instanceof Collection) {
            Collection<?> col = ((Collection<?>) o);
            builder.append("{");
            for (Object element : col) {
                builder.append(toString(element)).append(", ");
            }
            if (col.size() > 0) builder.setLength(builder.length() - 2); // crop last delimiter;
            builder.append("}");
            return builder.toString();
        }
        if (o instanceof Map) {
            Map<?, ?> map = ((Map<?, ?>) o);
            builder.append("{");
            Set<? extends Map.Entry<?, ?>> entries = map.entrySet();
            for (Map.Entry<?, ?> entry : entries) {
                builder.append("{").append(toString(entry.getKey())).append("=").append(toString(entry.getValue()));
                builder.append("}, ");
            }
            if (entries.size() > 0) builder.setLength(builder.length() - 2); // crop last delimiter;
            builder.append("}");
            return builder.toString();
        }
        if (c == Object.class) {
            return "{}";
        }
        builder.append(c.getSimpleName()).append("=").append("{");
        ArrayList<Field> fields = new ArrayList<>();
        while (c != null) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
            c = c.getSuperclass();
        }
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(o);
                builder.append(field.getName()).append(": ");
                builder.append(toString(value));
            } catch (Exception e) {
                continue;
            }
            builder.append(", ");
        }
        if (fields.size() > 0) builder.setLength(builder.length() - 2); // crop last delimiter;
        builder.append("}");
        return builder.toString();

    }

    public static Zoo generate() {
        Zoo zoo = new Zoo("Zoo", 3);

        zoo.setTicketPrice(4.50d);

        zoo.hire(new Person("John", 43), Position.DIRECTOR, 2000);
        zoo.hire(new Person("Tom", 33), Position.TRAINER, 1600);
        zoo.hire(new Person("Kathy", 29), Position.ZOOKEEPER, 1200);
        zoo.hire(new Person("Rosy", 36), Position.CASHIER, 600);
        zoo.hire(new Person("Bill", 27), Position.CLEANER, 400);

        zoo.addAnimal(new Wolf("Alpha", 3), 1);
        zoo.addAnimal(new Wolf("Stan", 3), 1);
        zoo.addAnimal(new Tiger("Roger", 1), 2);
        zoo.addAnimal(new Tiger("Felix", 2), 2);
        zoo.addAnimal(new Bison("Jack", 4), 3);
        zoo.addAnimal(new Bison("Phill", 6), 3);

        zoo.addVisitor(new Visitor("Dorothy", 7));
        zoo.addVisitor(new Visitor("Charlie", 6));
        zoo.addVisitor(new Visitor("Stacy", 8));
        return zoo;
    }
}

/*
 * Zoo={
 *     aviaries: [
 *         Aviary={
 *             number: 1,
 *             area: 100.0,
 *             inhabitants: {
 *                 Wolf={
 *                     age: 3,
 *                     TEETH: 30,
 *                     clazz: 'Mammal',
 *                     family: 'Canine',
 *                     species: 'Canis Lupus',
 *                     name: 'Alpha'
 *                 },
 *                 Wolf={
 *                     age: 3,
 *                     TEETH: 30,
 *                     clazz: 'Mammal',
 *                     family: 'Canine',
 *                     species: 'Canis Lupus',
 *                     name: 'Stan'
 *                 }
 *             }
 *         },
 *         Aviary={
 *             number: 2,
 *             area: 400.0,
 *             inhabitants: {
 *                 Tiger={
 *                     age: 1,
 *                     TEETH: 30,
 *                     clazz: 'Mammal',
 *                     family: 'Feline',
 *                     species: 'Panthera Tigris',
 *                     name: 'Roger'
 *                 },
 *                 Tiger={
 *                     age: 2,
 *                     TEETH: 30,
 *                     clazz: 'Mammal',
 *                     family: 'Feline',
 *                     species: 'Panthera Tigris',
 *                     name: 'Felix'
 *                 }
 *             }
 *         },
 *         Aviary={
 *             number: 3,
 *             area: 900.0,
 *             inhabitants: {
 *                 Bison={
 *                     age: 4,
 *                     HORNS: 2,
 *                     clazz: 'Mammal',
 *                     family: 'Bovine',
 *                     species: 'Bison Bison',
 *                     name: 'Jack'
 *                 },
 *                 Bison={
 *                     age: 6,
 *                     HORNS: 2,
 *                     clazz: 'Mammal',
 *                     family: 'Bovine',
 *                     species: 'Bison Bison',
 *                     name: 'Phill'
 *                 }
 *             }
 *         }
 *     ],
 *     animals: {
 *         Bison={
 *             age: 4,
 *             HORNS: 2,
 *             clazz: 'Mammal',
 *             family: 'Bovine',
 *             species: 'Bison Bison',
 *             name: 'Jack'
 *         },
 *         Tiger={
 *             age: 1,
 *             TEETH: 30,
 *             clazz: 'Mammal',
 *             family: 'Feline',
 *             species: 'Panthera Tigris',
 *             name: 'Roger'
 *         },
 *         Tiger={
 *             age: 2,
 *             TEETH: 30,
 *             clazz: 'Mammal',
 *             family: 'Feline',
 *             species: 'Panthera Tigris',
 *             name: 'Felix'
 *         },
 *         Wolf={
 *             age: 3,
 *             TEETH: 30,
 *             clazz: 'Mammal',
 *             family: 'Canine',
 *             species: 'Canis Lupus',
 *             name: 'Stan'
 *         },
 *         Wolf={
 *             age: 3,
 *             TEETH: 30,
 *             clazz: 'Mammal',
 *             family: 'Canine',
 *             species: 'Canis Lupus',
 *             name: 'Alpha'
 *         },
 *         Bison={
 *             age: 6,
 *             HORNS: 2,
 *             clazz: 'Mammal',
 *             family: 'Bovine',
 *             species: 'Bison Bison',
 *             name: 'Phill'
 *         }
 *     },
 *     employees: {
 *         Employee={
 *             position: 'CASHIER',
 *             salary: 600,
 *             name: 'Rosy',
 *             age: 36
 *         },
 *         Employee={
 *             position: 'CLEANER',
 *             salary: 400,
 *             name: 'Bill',
 *             age: 27
 *         },
 *         Employee={
 *             position: 'DIRECTOR',
 *             salary: 2000,
 *             name: 'John',
 *             age: 43
 *         },
 *         Employee={
 *             position: 'TRAINER',
 *             salary: 1600,
 *             name: 'Tom',
 *             age: 33
 *         },
 *         Employee={
 *             position: 'ZOOKEEPER',
 *             salary: 1200,
 *             name: 'Kathy',
 *             age: 29
 *         }
 *     },
 *     accounts: {
 *         {
 *             'Kathy'=Account={
 *                 login: 'Kathy',
 *                 password: '',
 *                 employee: Employee={
 *                     position: 'ZOOKEEPER',
 *                     salary: 1200,
 *                     name: 'Kathy',
 *                     age: 29
 *                 }
 *             }
 *         },
 *         {
 *             'Tom'=Account={
 *                 login: 'Tom',
 *                 password: '',
 *                 employee: Employee={
 *                     position: 'TRAINER',
 *                     salary: 1600,
 *                     name: 'Tom',
 *                     age: 33
 *                 }
 *             }
 *         },
 *         {
 *             'Rosy'=Account={
 *                 login: 'Rosy',
 *                 password: '',
 *                 employee: Employee={
 *                     position: 'CASHIER',
 *                     salary: 600,
 *                     name: 'Rosy',
 *                     age: 36
 *                 }
 *             }
 *         },
 *         {
 *             'John'=Account={
 *                 login: 'John',
 *                 password: '',
 *                 employee: Employee={
 *                     position: 'DIRECTOR',
 *                     salary: 2000,
 *                     name: 'John',
 *                     age: 43
 *                 }
 *             }
 *         },
 *         {
 *             'Bill'=Account={
 *                 login: 'Bill',
 *                 password: '',
 *                 employee: Employee={
 *                     position: 'CLEANER',
 *                     salary: 400,
 *                     name: 'Bill',
 *                     age: 27
 *                 }
 *             }
 *         }
 *     },
 *     visitors: {
 *         Visitor={
 *             name: 'Stacy',
 *             age: 8
 *         },
 *         Visitor={
 *             name: 'Charlie',
 *             age: 6
 *         },
 *         Visitor={
 *             name: 'Dorothy',
 *             age: 7
 *         }
 *     },
 *     name: 'Zoo',
 *     ticketPrice: 4.5
 * }
 * */
