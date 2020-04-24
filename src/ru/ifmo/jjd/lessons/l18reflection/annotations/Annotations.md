### Аннотации
специальная форма синтаксических метаданных, которая может быть добавлена в исходный код. 
Аннотации используются для анализа кода, компиляции или выполнения. Аннотации - это метки, с помощию которых 
разработчик говорит компилятору и средствам разработки что делать с тем или иным участком кода. 
Можно писать свои аннотации или использовать стандартные (Java)
#### Аннотировать можно:
1. пакеты
2. классы
3. свойства
4. методы
5. параметры методов

#### Объявление аннотации

        @interface НазваниеАннотации{ ... }
        
#### Указание на то, что может быть аннотировано данной аннотацией

        @Target(ElementType.TYPE) аннотация для классов
        @Target(ElementType.CONSTRUCTOR) аннотация для конструкторов
        @Target(ElementType.METHOD) аннотация для методов
        @Target(ElementType.FIELD) аннотация для полей
        @Target(ElementType.PARAMETER) аннотация для параметров метода
        @Target(ElementType.LOCAL_VARIABLE)
        @Target(ElementType.PACKAGE) аннотация для пакета
        @Target({ElementType.CONSTRUCTOR, ElementType.METHOD}) аннотация для конструкторов и для методов
        @interface НазваниеАннотации{ ... }
        
#### Указание на то, когда необходимо использовать аннотацию

        @Retention(RetentionPolicy.RUNTIME) аннотация времени выполнения
        @Retention(RetentionPolicy.CLASS) существует в скомпилированных файлах, но на этапе выполнения программы в машинном коде их уже нет
        @Retention(RetentionPolicy.SOURCE) существует на исходном коде, и не узнаваем компилятором (compiler)
        @interface НазваниеАннотации{ ... }
        
#### Например, аннотация времени выполнения. Можно аннотировать только классы

        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        @interface ConfigClass{ ... }
        
#### Параметры аннотации. Можно использовать: строки, примитивы и перечисления (enum)

        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface ConfigClass {
            // при использовании аннотации значении file указывать обязательно
            String file();
            // при использовании аннотации значении version указывать не обязательно
            int version() default 1;
        }

#### Наследование

        @Inherited // аннотация передается всем потомкам класса
        @interface ConfigClass{ ... }


#### Использование аннотаций

        @ConfigClass("config.txt")
        class SerevrConfig {
            описание класса
        }
         