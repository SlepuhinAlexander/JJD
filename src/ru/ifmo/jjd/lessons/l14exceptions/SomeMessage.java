package ru.ifmo.jjd.lessons.l14exceptions;

public class SomeMessage {
    private String title;
    private String text;

    public SomeMessage(String title, String text) throws ChatException {
        setTitle(title);
        /*
         * метод setTitle теперь выбрасывает проверяемое исключение. Его обязательно нужно либо обработать либо
         * пробросить выше: throws ChatException
         * */
        setText(text);

        /*
         * если произойдёт ChatException - прервётся выполнение конструктора - и объект создан не будет!
         * */
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws ChatException {
        if (title.length() < 3) {
            throw new ChatException("Длина не может быть меньше 3");
            /*
             * Выбрасывание исключения *прерывает* работу метода. Дальнейший код выполнен не будет.
             * */
        }
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
