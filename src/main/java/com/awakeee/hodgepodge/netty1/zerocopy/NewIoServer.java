package com.awakeee.hodgepodge.netty1.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIoServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);//连接关闭后的wait状态，当一个连接刚关闭时，另一个连接过来可以马上就连接上
        serverSocket.bind(new InetSocketAddress(8899));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true){
            System.out.println("开始接受请求.....");
            SocketChannel socketChannel = serverSocketChannel.accept();
            int total = 0;
            socketChannel.configureBlocking(true);
            System.out.println(socketChannel.getRemoteAddress());
            while (true){
                byteBuffer.rewind();
                int read = socketChannel.read(byteBuffer);
                if(read == -1){
                    break;
                }
                total += read;
//                byteBuffer.rewind();
//                byteBuffer.clear();
            }
            System.out.println("接受数据完成.....");
            System.out.println("总字节数: "+total);
        }
    }
}
