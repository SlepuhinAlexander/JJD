## Ещё классы из concurrent пакета

1. Пакет concurrent.locks - блокировщики.  
    Классы из этого пакета могут использоваться для замены synchronized блоков или методов.

2. Класс Exchanger - обменник между потоками.  
    Создаётся на два потока. Ожидается, что один поток передаёт данные второму, а второй - первому.  
    Поток всегда получает данные взамен отданным данынм:  
    Первый поток передал данные в обменник, ждёт пока второй поток будет готов.  
    Второй поток передаёт данные в обменник, получает из него данные от первого потока. 
    Первый поток тоже забирает из обменника данные, полученные от второго потока.
    
3. Класс Semaphore - тоже аналог synchronize блока.  
    Один метод открывает синхронизованную часть инструкций.  
    Второй метод закрывать синхронизованную часть инструкций.  
    Имеет флаг справедливости.  
    Имеет количество разрешений: указывыает сколько потоков одновременно могут работать с ним.  
    Если только один, то работа аналогична синхронизации:  
        все потоки будут ждать пока синхронизованный блок кода отработает у каждого их потока.
        
4. Класс CountDownLatch - используется, когда потоку нужно дождаться выполнения каких-то событий.  
    Используется для выравнивания потоков.
    
5. Пакет atomic. Классы AtomicXXX - используются для атомарных операций.  
    Позволяют с уверенностью читать и устанавливать значения свойств потоко-независимо. У каждого потока будет 
    актуальная информация по этому свойству. 
    
6. ForkJoinPool - пул потоков. Используется для рекурсивных задач: когда одна задача может породить другие 
    (или несколько).
    Если у одного потока - простой, а у другого - много задач, то можно организовать так, чтобы простаивающий поток 
    забрал и выполнил задачу занятого потока. 