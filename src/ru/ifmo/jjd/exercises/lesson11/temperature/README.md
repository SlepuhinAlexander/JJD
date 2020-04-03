Написать конвертер температур из градусов по Цельсию в Кельвины, Фаренгейты и т.д.

В каждом классе-конвертере обязательно должны быть методы `void convertValue()` и `double getConvertedValue()`:
- (Названия методов одинаковые, реализации разные)
- `convertValue()` - конвертация температуры - сохранение значения в соответствующее свойство
- `getConvertedValue()` - метод возвращает сконвертированное значение

Каждый класс-конвертер хранит базовое значение (по цельсию), это значение можно задать через конструктор или через 
сеттер.  
Каждый класс-конвертер хранит сконвертированное значение.  
Каждый конвертер хранит свойства необходимые ему для конвертации температуры (значения этих свойств их нельзя изменить 
(final)).

Кроме этого в базовом классе-конвертере должен быть static метод, который возвращает объект соответствующего 
конвертера, в зависимости от переданного в него аргумента `String type`.  
Возможные значение `type`: “FR” - метод должен вернуть конвертер в фаренгейты, “KL” -  метод должен вернуть конвертер в 
кельвины.

Формулы конвертации смотрите в интернете.