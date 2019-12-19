package com.awakeee.hodgepodge.netty1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost", 8899));

        while (true) {

            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeySet) {

                if (selectionKey.isConnectable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()) {
                        client.finishConnect();

                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                        writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                        writeBuffer.flip();
                        client.write(writeBuffer);

                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                        executorService.submit(() -> {
                            while (true) {
                                writeBuffer.clear();
                                InputStreamReader in = new InputStreamReader(System.in);
                                BufferedReader bf = new BufferedReader(in);
                                String message = bf.readLine();

                                writeBuffer.put(message.getBytes());
                                writeBuffer.flip();
                                client.write(writeBuffer);
                            }
                        });
                        //client.register(selector,SelectionKey.OP_READ);
                    }
                    client.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel client1 = (SocketChannel) selectionKey.channel();
                    ByteBuffer readBuff = ByteBuffer.allocate(2014);
                    int read = client1.read(readBuff);
                    if (read > 0) {
                        String receivedMessage = new String(readBuff.array(),0,read);
                        System.out.println("receivedMessage :" + receivedMessage);
                    }
                }
            }
            selectionKeySet.clear();
        }
    }
}
