package com.awakeee.hodgepodge.netty1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

public class NioServer {

    public static void main(String[] args) throws IOException {

        Map<String,SocketChannel> clientMap = new HashMap<>();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        //将serverSocketChannel 注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){

            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                final SocketChannel client;
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ);

                    clientMap.put(UUID.randomUUID().toString(),client);
//                    iterator.remove();

                }else if(selectionKey.isReadable()){

                    client = (SocketChannel) selectionKey.channel();
                    client.configureBlocking(false);

                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    int read = client.read(byteBuffer);
                    if(read > 0){
                        byteBuffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        String receivedMessage = String.valueOf(charset.decode(byteBuffer).array());
                        System.out.println("client: "+client + " received message: "+ receivedMessage);

                        String senderKey = null;
                        for(Map.Entry<String,SocketChannel> entry : clientMap.entrySet()){
                            if(client ==  entry.getValue()){
                                senderKey = entry.getKey();
                                break;
                            }
                        }

                        for(Map.Entry<String,SocketChannel> entry : clientMap.entrySet()){
                            SocketChannel socketChannel = entry.getValue();
                            ByteBuffer byteBuffer1 = ByteBuffer.allocate(512);
                            byteBuffer1.put((senderKey + ": " + receivedMessage).getBytes());
                            byteBuffer1.flip();
                            socketChannel.write(byteBuffer1);
                        }

                    }

                }
                selectionKeys.clear();
            }
        }
    }
}
