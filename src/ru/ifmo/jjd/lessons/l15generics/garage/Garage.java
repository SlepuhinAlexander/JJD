package ru.ifmo.jjd.lessons.l15generics.garage;

/*
 * Класс для обслуживания транспортных средств для ремонта.
 * */
public class Garage<T extends Transport & Repairing> {
    /*
     * Если просто задать generic T, то в качестве T может использоваться любой ссылочный тип, это никак не
     * ограничивает использование гаража.
     *
     * А мы хотим, чтобы в гараже ремонтировались только те машины, которые подлежат ремонту.
     *
     * Для этого нужно ограничить используемый тип T.
     * */

    /*
     * В описании класса в качестве типа generic можно указать <T extends Transport>
     * В таком случае тип данных для carOnService может быть Transport или его наследники.
     * */
    /*
     * Если в описании класса в качестве типа указать <T extends Bus> то carOnService может быть только типом Bus
     * или его наследником
     * Тогда нельзя будет создать Garage<Transport>.
     * */
    /*
     * На T можно наложить ограничение не только наследования, но и имплементации интерфейса: указываются они через &
     * Garage<T extends Bus & Repairing>.
     * Наследование только одно можно указать (как при наследовании классов). Интерфейсов можно указать сколько угодно
     * (как при множественной имплементации интерфейсов): Garage<T extends Bus & Repairing & Cloneable>.
     * */
    /*
     * Если в качестве ограничения на T только интерфейс используется - то он тоже указывается через extends
     * (не implements)
     * Garage<T extends Repairing>
     * */
    private T carOnService;

    public T getCarOnService() {
        return carOnService;
    }

    public void setCarOnService(T carOnService) {
        this.carOnService = carOnService;
    }

    public void service() {
        carOnService.repair();
        /*
         * Пока общий тип указан как T - может быть передан любой объект. Не у любого объекта есть метод repair().
         * Поэтому нельзя его здесь так вызвать.
         *
         * Если тип T не ограничен, то у объектов типа T можно вызывать методы только Object.
         *
         * Если указать generic тип <T extends Transport>, то для carOnService можно будет вызывать методы класса
         * Transport.
         * */
    }
}



