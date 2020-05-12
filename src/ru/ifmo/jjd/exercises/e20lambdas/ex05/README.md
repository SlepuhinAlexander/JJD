Дана карта (`customers`).
Написать метод, который принимает на вход аргументы `int from` и `int to` и возвращает новую карту, в которую войдут 
все покупатели, возраст которых находится в диапазоне \[from, to\).

Пример входных данных:

    HashMap<String, Customer> customerMap = new HashMap<>();
    customerMap.put("1", new Customer("Павел", "1", 23));
    customerMap.put("2", new Customer("Олег", "2", 17));
    customerMap.put("3", new Customer("Максим", "3", 48));
    customerMap.put("4", new Customer("Евгения", "4", 67));
