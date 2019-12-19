package com.awakeee.hodgepodge.netty1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioTest4 {

    //buffer的Scattering和Gathering 分散和聚集
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(inetSocketAddress);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true){
            int byteRead = 0;

            while (byteRead < messageLength){
                long read = socketChannel.read(buffers);
                byteRead += read;

                System.out.println("byteRead :"+byteRead);

                Arrays.asList(buffers).stream().map(buffer -> "position :"+buffer.position() + ", limit :"+buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(
                    byteBuffer -> byteBuffer.flip()
            );

            long byteWritten = 0;
            while (byteWritten < messageLength){
                long write = socketChannel.write(buffers);
                byteWritten += write;
            }

            //必须要clear,否则会重新进入到读的while循环中
            Arrays.asList(buffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead: "+byteRead + ",byteWritten: "+byteWritten + ",messageLength: "+ messageLength);
        }
    }
}
