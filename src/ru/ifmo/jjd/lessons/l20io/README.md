## Ввод-вывод

В Java есть пакет классов `java.io` для работы с передачей данных из программы и в программу.
Вся вместе встроенная в язык функциональность по вводу-выводу данных называется IO API. 
В первую очередь в файл и из файла.

IO API предоставляет возможности по передаче данных в виде последовательности `byte` или `char`. Есть служебные методы 
по передаче строк, объектов, но внутри они тоже используют передачу потока `byte` или `char`.

Пакет `java.io` - не единственная возможность передачи данных. Аналогичную возможность предоставляет, например, пакет 
`java.nio`.

### Класс File

Содержится в пакете `io`. Предоставляет возможности работы с файловой системой:
Создавать файлы/директории, перемещать, переименовывать; проверять/устанавливать атрибуты файла/директории и так далее.

[IODemo.java](IODemo.java)

### Input и Output Streams

Родительские классы дерева IO - `InputStream` и `OutputStream`
`InputStream` - входной поток в программу, принимает данные, использует метод `read()`
`OutputSteram` - выходной поток из программы, отправляет данные, использует метод `write()`

FileInput|OutputStream - работают с файлами (чтение/запись)
ByteArrayInput|OutputStream - работают с массивами данных
ObjectInput|OutputStream - работают с байтовым представлением объекта
DataInputOutputStream - полезны для работы с примитивными типами данных, обёртками, строками
BufferedInput|OutputStream - накапливают информацию в буффер во время чтения / перед записью