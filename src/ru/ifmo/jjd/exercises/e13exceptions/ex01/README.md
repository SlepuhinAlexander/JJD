Метод `static void throwException(Status status) throws JarException, FileNotFoundException, AccessDeniedException`
принимает на вход `enum Status` и выбрасывает следующие исключения в зависимости от значения `status`:
- если `status` `FILE_NOT_FOUND`, выбрасывает `FileNotFoundException`;
- если `status` `ACCESS_DENIED`, выбрасывает `AccessDeniedException`;
- если `status` `JAR_ERROR`, выбрасывает `JarException`.

При вызове метода `throwException` обработать исключения следующим образом:
- `FileNotFoundException` - выводить в консоль сообщение исключения(метод `getMessage()`) + любое дополнительное 
    сообщение;
- `AccessDeniedException` - выводить в консоль сообщение исключения (метод `getMessage()`) и снова выбрасывать 
    `exception`;
- `JarException` - выводить в консоль stacktrace.

`enum` `Status` с необходимыми константами нужно создать.