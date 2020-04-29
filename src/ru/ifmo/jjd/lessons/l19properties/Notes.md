## Синтетические методы и Bridge методы

Синтетические методы - это методы, которые создаются компилятором.

Создаются они в некоторых ситуациях.
например, создан класс-компаратор.

    class SomeComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    }
    
и затем используем класс-компаратор без уточнения

    Comparator comp = new SomeComp();
    Object a = 12, b = 122;
    int res = comp.compare(a,b);
    
если посмотреть байт-код, можно увидеть, что компилятор сам сгенерировал метод compare, который принимает тип данных 
Object

такие методы - синтетические.

bridge-метод - методы, которые синтетически создаются для того, чтобы отработать с фактически переданными типами 
данных, отличающимися от ожидаемых.

bridge-методы возникают, когда программа использует "сырой" (raw, не параметризованный) вид дженерик-класса.

## Properties класс

В Java есть полезный класс Properties, который используется для хранения пар свойство-значение.  
Основан на мапе HashTable. Внутри использует многопоточную версию мапы - ConcurrentHashMap.  
Обычно используется для чтения и хранения каких-то настроек приложения.  
И ключи и значения в Properties хранятся в виде строк.  
Ориентирован на работу с `.properties` файлами, где настройки тоже хранятся в виде пар ключ-значение.

Создадим директорию для ресурсов, которые будут использоваться для работы приложения.
В ней хранятся все необходимые ресурсы для работы приложения.
Помечаем директорию как `ResourcesRoot`.  
Создаём `example.properties`.  
В таком файле обычно хранятся настройки приложения.
Синтаксис `.properties` файла:

    Ключ=Значение
    
все ключи-значения используются как **строки**

созданный файл с данными настроек соберём теперь в объект типа Properties