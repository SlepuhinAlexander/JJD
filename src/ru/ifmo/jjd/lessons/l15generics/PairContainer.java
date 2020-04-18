package ru.ifmo.jjd.lessons.l15generics;

/*
 * В случае, когда необходимо использовать несколько generic типов, нужно их оба объявить в описании класса (с разными
 * именами). И соответственно, использовать объявленные generic типы в реализации класса.
 * */
public class PairContainer<T, V> { // теперь этот тип может использовать 2 generic Типа
    /*
     * Значения типов для T и V при создании объекта могут совпадать.
     * Два generic типа введены для того чтобы можно было использовать различные типы.
     *
     * При создании объекта generic типы нужно указывать в той же последовательности.
     * */
    private T key;
    private V value;

    // конструктор тоже должен использовать те же объявленные generic типы
    public PairContainer(T key, V value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
