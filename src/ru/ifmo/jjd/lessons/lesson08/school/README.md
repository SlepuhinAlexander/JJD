## Школа

Будем считать, что в школе каждый ученик посещает только один изучаемый предмет.
Также будем считать, что каждый учитель преподаёт только один предмет

Ученик:
- имя
- возраст
- изучаемый предмет
- уровень знаний
- учиться()  
    Повышать уровень знаний
    
Учитель
- имя
- возраст
- преподаваемый предмет
- учить( ученика )  
    внутри этого метода вызввать метод ученика.учиться()

Директор
- имя
- возраст
- объявить начало занятий()  
    _бесполезный метод_
- объявить окончание занятий()  
    _бесполезный метод_

Школа
- название  
    Название неизменно.
- учителя[]
- ученики[]
- директор
- добавить учителя()
- добавить ученика()
- назначить директора()  
    Школа без директора работать не может!
- школьный день()  
    когда будет вызываться этот метод учителя должны учить учеников, 
    если у них совпадают предметы.

    
Будем считать, что возможно расширение программы, поэтому методы учиться() и учить() вынести в интерфейсы