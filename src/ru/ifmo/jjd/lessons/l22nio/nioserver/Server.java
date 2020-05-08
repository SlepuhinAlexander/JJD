package ru.ifmo.jjd.lessons.l22nio.nioserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

// селекторы позволяют одному потоку обслуживать несколько каналов.
// селектор подписывается на событие (или несколько событий) в канале (Connect / Accept / Read / Write).
// когда в канале наступит то или иное событие, селектор будет проинформирован об этом
// и сможет отреагировать необходимым образом
// Например, можно на каждое соединение с сервером можно создать отдельный канал
public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        // запускаем один поток для обработки всех соединений
        new Thread(new NioThread()).start();
    }

    private class NioThread extends Worker {
        private static final int BUF_SIZE = 1024;

        private ByteBuffer byteBuffer;
        private ServerSocketChannel serverChannel;
        private Selector selector;

        @Override
        protected void init() throws IOException {
            // создаем буфер
            byteBuffer = ByteBuffer.allocate(BUF_SIZE);

            // создаем селектор
            selector = Selector.open();

            // открываем канал сервера
            serverChannel = ServerSocketChannel.open();
            // слушаем определенный порт (8090)
            /*
             * Создаём канал, который будет ожидать подключение клиентов.
             *
             * */
            serverChannel.bind(new InetSocketAddress(8090));
            // перевод в неблокирующий режим
            /*
             * Это значит, что когда клиент подключится к серверу, другие клиенты тоже будут иметь возможность
             * подключаться.
             *
             * По умолчанию каналы автоматически блокируются при действиях с ними (т.е. в блокирующем режиме работают).
             * */
            serverChannel.configureBlocking(false);
            /*
             * Селектор пока не подключен к каналу
             * */

            // регистрируем канал в селекторе на все допустимые события (для serverSocket - OP_ACCEPT)
            /*
             * селектор подписывается на указанные события (все доступные) в serverChannel.
             *
             * Для ServerSocketChannel validOps == OP_ACCEPT - событие подключения. Других событий в
             * ServerSocketChannel ничего быть не может.
             * */
            serverChannel.register(selector, serverChannel.validOps());
        }

        // метод loop() выполняется в бесконечном цикле
        @Override
        protected void loop() throws IOException {
            // ожидание новых событий
            selector.select();

            // получаем ключи, на которые пришли события
            Set<SelectionKey> keys = selector.selectedKeys();

            // перебираем все ключи
            Iterator<SelectionKey> iterator = keys.iterator();

            // обрабатываем все ключи
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                // флаг isAcceptable означает, что пришло входящее подключение
                // можно получать новый канал для этого соединения
                if (key.isAcceptable()) {
                    // получение канала
                    /*
                     * Воспроизведение клиентского сокета. Плюс выделение клиенту отдельного канала. Переключение
                     * клиента на этот канал.
                     *
                     * Так происходит для каждого клиента.
                     * */
                    SocketChannel channel = serverChannel.accept();
                    // переводим в неблокирующий режим
                    /*
                     * Для того чтобы операции чтения / записи в канале не блокировали друг друга
                     * Канал открыт и для чтения и для записи.
                     *
                     * Соединение держится открытым, пока клиент не отключится сам.
                     * Сервер может передать данные клиенту в любое время.
                     * */
                    channel.configureBlocking(false);
                    // регистрируем в селекторе
                    /*
                     * только на операцию чтения:
                     * когда клиент отправит что-то в этот канал, в нём появятся данные и селектор об этом узнает
                     *
                     * За записью в канал (от сервера) селектор не следит.
                     * */
                    channel.register(selector, SelectionKey.OP_READ);
                    System.out.println("Client connected " + channel.getRemoteAddress());
                }
                // флаг означает, что пришли данные для чтения
                /*
                 * В каком канале - неизвестно
                 * метод channel() у ключа вернёт в каком конкретно канале произошло событие.
                 * */
                else if (key.isReadable()) {
                    // читаем входящие данные
                    System.out.println("читаем входящие данные...");
                    readData((SocketChannel) key.channel());
                }
                // isValid проверяет, что соединение открыто
                // isWritable канал готов к отправке данных
                else if (key.isValid() && key.isWritable()) {
                    // записываем данные в канал
                    System.out.println("запись данных в канал...");
                    writeData((SocketChannel) key.channel(), key);
                }
                // удаляем обработанный ключ
                iterator.remove();
            }

        }

        @Override
        protected void stop() throws IOException {
            serverChannel.close();
            selector.close();
        }

        private void readData(SocketChannel channel) {
            // готовим буфер для чтения данных
            byteBuffer.clear();

            try {
                int read = channel.read(byteBuffer);
                /*
                 * Данные, которые появились в канале, канал кладёт в буфер.
                 * Записываем в буфер.
                 * */
                if (read != -1) {
                    System.out.println(new String(byteBuffer.array(), 0, byteBuffer.position(),
                            StandardCharsets.UTF_8));
                } else {
                    System.out.println("client disconnected " + channel.getRemoteAddress());
                    channel.close();
                }

                /*
                 * Записали данные в буфер, нужно теперь их разослать всем
                 * */

                // готовим буфер для записи
                byteBuffer.flip();

                Set<SelectionKey> keys = selector.keys();
                for (SelectionKey key : keys) {
                    if ((key.channel().validOps() & SelectionKey.OP_WRITE) > 0
                        /*
                         * если в канал можно писать
                         * */) {
                        key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                        /*
                         * сервер инициирует событие записи в канал
                         * */
                    }
                }

            } catch (IOException e) {
                System.out.println("client disconnected ");
                byteBuffer.clear();
                try {
                    channel.close();
                } catch (IOException e1) {
//                    e1.printStackTrace();
                }
            }
        }

        private void writeData(SocketChannel channel, SelectionKey key) throws IOException {
            //  записываем данные из буфера
            channel.write(byteBuffer);
            // готовим для следующей записи
            byteBuffer.rewind();
            /*
             * убрать событие записи из событий канала
             * */
            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
        }
    }
}