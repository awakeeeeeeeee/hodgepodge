package com.awakeee.hodgepodge.netty1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest5 {

    //nio selector 一个线程处理多个客户端的连接请求 区别与传统的socket/bio

    public static void main(String[] args) throws IOException {

        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        for(int i=0;i<ports.length;i++){

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口: "+ports[i]);

        }

        while (true){

            int num = selector.select();
            System.out.println("num: "+num);

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            System.out.println("selectKeys: "+selectionKeySet);

            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if(selectionKey.isAcceptable()){

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("socketChannel:"+socketChannel);
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector,SelectionKey.OP_READ);

                    //为什么事件被执行后 必须要remove?
                    iterator.remove();

                }else if(selectionKey.isReadable()){

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);

                    int read;
                    while ((read = socketChannel.read(byteBuffer)) > 0){

                    }
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
//                    byteBuffer.clear();
                }
            }
        }
    }
}
