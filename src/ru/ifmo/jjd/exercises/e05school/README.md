Создать архитектуру классов и интерфейсов для моделирования работы школы.  
Написать реализации методов, создать нужные объекты, показать учебный процесс.

Класс Ученик.  
- создаётся со следующими характеристиками:
    - имя (тип String)
    - возраст (тип int)
    - изучаемый предмет (тип String)
    - уровень знаний (тип int)
- и методом
    - учиться() - уровень знаний ученика по его предмету повышается.

Класс Учитель  
- создаётся со следующими характеристиками:
    - имя (тип String)
    - возраст (тип int)
    - преподаваемый предмет (тип String)
- и методом: 
    - учить(обучаемый) - вызывает метод обучаемого учиться()  
        тип данных для обучаемого определить самостоятельно.

Класс Директор  
- создаётся со следующими характеристиками:  
    - имя (тип String)
    - возраст (тип int)
- и методами: 
    - объявить начало занятий()  
    - объявить окончание занятий()  

Класс Школа
- создаётся со следующими характеристиками:
    - название (тип String)  
        задаётся при создании объекта и не может быть изменено в последствии 
    - директор  
        школа не может функционировать без директора
    - учителя[] - массив учителей
    - ученики[] - массив учеников
- и методом:
    - школьный день()  
        1. директор объявляет начало занятий
        2. учителя учат учеников (предмет ученика и учителя должны совпадать)
        3. директор объявляет окончание занятий

Будем считать, что в школе каждый ученик посещает только один изучаемый предмет.
Также будем считать, что каждый учитель преподаёт только один предмет

Методы учить() и учиться() необходимо описать в разных интерфейсах. 

Общие свойства необходимо вынести в родительские классы (какие - определить самостоятельно).
В классах можно добавлять свои методы и свойства.