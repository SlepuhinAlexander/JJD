package ru.ifmo.jjd.lessons.l22nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

public class NioBufferDemo {
    public static void main(String[] args) {

        //Java NIO (New IO aka Non-blocking IO) - предназначен для работы с вводом-выводом.
        // Неблокирующий
        // Асинхронный
        // Буфер-ориентированный

        //Каналы:
        // канал может быть использован и для записи и для чтения
        // чтение и запись может происходить асинхронно
        // каналы всегда пишут в буфер и читают из буфера
        // Каналы могут быть созданы на основе:
        // - FileChannel
        // - DatagramChannel
        // - SocketChannel
        // - ServerSocketChannel

        // Буферы могут быть:
        // ByteBuffer
        // CharBuffer
        // DoubleBuffer
        // FloatBuffer
        // IntBuffer
        // LongBuffer
        // ShortBuffer

        // свойства буфера:
        // capacity - емкость (не меняется после установки)
        // position - текущая позиция в буфере (изначально 0)
        // limit - до какого значения можно читать/писать данные (изначально равен capacity)

        /*
         * При создании буфера указывается его емкость (начальная, и в принципе). Ёмкость в дальнейшем не меняется.
         * capacity() - получить ёмкость буфера, постоянная.
         * position() - получить позицию буфера. Значение позиции меняется в зависимости от того сколько данных в буфер
         *      добавлено. Изначально буфер пустой, позиция 0.
         * limit() - получить лимит: указание, до какого значения можно читать/писать (не включительно). Лимит не может
         *      быть больше capacity. Но может быть меньше.
         * */

        /*
         * При создании буфера:
         * capacity() == initial capacity;
         * position() == 0;
         * limit() == capacity();
         *
         * remaining() - разница между position и limit
         * */
        ByteBuffer buffer = ByteBuffer.allocate(16);
        assert buffer.position() == 0;
        assert buffer.capacity() == 16;
        assert buffer.limit() == 16;
        assert buffer.remaining() == 16; // разница между position и limit

        IntBuffer intBuffer = IntBuffer.allocate(33); // new int[33]
        assert intBuffer.position() == 0;
        assert intBuffer.capacity() == 33;
        assert intBuffer.limit() == 33;
        assert intBuffer.remaining() == 33;

        CharBuffer charBuffer = CharBuffer.allocate(57); // new char[57]
        assert charBuffer.position() == 0;
        assert charBuffer.capacity() == 57;
        assert charBuffer.limit() == 57;
        assert charBuffer.remaining() == 57;

        /*
         * В буфер можно положить данные. При этом position будет смещена.
         * Например, ByteBuffer умеет положить в себя любые типы-примитивы.
         *
         * position смещается на длину типа данных (в байтах).
         * например, при putInt - позиция увеличится на 4.
         * remaining() тоже обновится
         * */
        // Увеличивает позицию на 4.
        buffer.putInt(100);

        assert buffer.position() == 4;
        assert buffer.remaining() == 12;

        /*
         * putDouble() положит тип данных double, который занимает 8 байт
         * позиция увеличится на 8 (до 12)
         * remaining тоже обновится: до 4.
         * */
        // Увеличивает позицию на 8.
        buffer.putDouble(100.25);

        assert buffer.position() == 12;
        assert buffer.remaining() == 4;
        /*
         * С началом буфера связи нет, есть только position
         * последние 4 ячейки буфера не нужны, там незначимые данные.
         * */

        /*
         * Чтобы перейти к чтению из буфера, нужно
         * - указать лимит на место текущей позиции (чтобы не читать лишнюю информацию)
         * - переместить позицию (на то место, откуда нужно читать. Обычно - в ноль)
         * Именно это сделает метод flip: сначала устанавливает лимит в текущую позицию, затем сбрасывает позицию в
         * ноль.
         * */
        // Устанавливает лимит на место позиции, сбрасывает позицию в 0 (для чтения из буфера)
        buffer.flip();

        assert buffer.position() == 0;
        assert buffer.limit() == 12;
        assert buffer.remaining() == 12;
        /*
         * после flip() буфер по-прежнему доступен и для записи и для чтения.
         * но из-за того что лимит теперь меньше (12), то и прочитать можно только 12 байт.
         *
         * */

        /*
         * При чтении, аналогично, смещается position и remaining на количество прочитанных ячеек (байт).
         * limit остаётся неизменным.
         * */
        // Увеличивает позицию на 4.
        int anInt = buffer.getInt();

        assert buffer.position() == 4;
        assert buffer.remaining() == 8;

        // Увеличивает позицию на 8.
        double aDouble = buffer.getDouble();

        assert buffer.position() == 12;
        assert buffer.remaining() == 0;

        /*
         * Допустим, нужно произвести повторное чтение.
         * Лимит тогда не нужно изменять, а нужно изменить позицию (снова на ноль).
         * Для этого используется метод rewind()
         * */
        // Сбрасывает позицию в 0 (limit остается на прежнем месте - для повторного чтения)
        buffer.rewind();

        assert buffer.position() == 0;
        assert buffer.limit() == 12;
        assert buffer.remaining() == 12;

        // Увеличивает позицию на 4.
        assert anInt == buffer.getInt();
        // Увеличивает позицию на 8.
        assert aDouble == buffer.getDouble();

        /*
         * Восстановить состояние лимита в изначальное состояние поможет метод clear().
         * Метод clear() НЕ ОЧИЩАЕТ сами данные, он устанавливает позицию в 0, а лимит делает равным ёмкости
         * т.е. буфер снова доступен для чтения/записи с начала и целиком.
         * */
        // Сбрасывает позицию в 0, ставит лимит, равный емкости буфера (для записи в буфер)
        buffer.clear();

        assert buffer.position() == 0;
        assert buffer.capacity() == 16;
        assert buffer.limit() == 16;
        assert buffer.remaining() == 16;

        /*
         * Значения свойств limit, position - можно явно установить, передав методу в аргумент новое значение.
         * Но, как правило, делать этого не требуется.
         * */

        /*
         * Метод compact()
         * - КОПИРУЕТ непрочитанные данные (всё что было от position до limit).
         * - устанавливает позицию после этих скопированных (непрочитанных данных)
         * - устанавливает лимит на максимум.
         *
         * Таким образом, в результате работы в начале буфера останутся непрочитанные данные, а дальнейшие действия с
         * буфером можно производить после этих данных.
         * */
        // копирует все непрочитанные данные в начало буфера. Лимит равен емкости буфера
        // (для записи в буфер после непрочитанных данных)
        // buffer.compact();
    }
}