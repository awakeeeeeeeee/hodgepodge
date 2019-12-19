package com.awakeee.hodgepodge.netty1.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIoClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);

        FileInputStream fileInputStream = new FileInputStream("/Users/cool/Downloads/安装包/jdk-8u161-linux-x64.tar.gz");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        long startTime = System.currentTimeMillis();

        int total = 0;
        int read;

        while ((read = fileChannel.read(byteBuffer)) != -1){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            total += read;
            byteBuffer.clear();
        }



        // zero copy
//        long total  = fileChannel.transferTo(0,fileChannel.size(),socketChannel);

        System.out.println("发送字节总数:"+total + ",耗时: "+ (System.currentTimeMillis() - startTime));
        fileInputStream.close();
        socketChannel.close();

    }
}
