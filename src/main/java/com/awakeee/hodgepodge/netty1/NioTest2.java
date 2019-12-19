package com.awakeee.hodgepodge.netty1;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("/Users/cool/Downloads/test.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()){
            System.out.println((char) byteBuffer.get());
        }

        fileChannel.close();
    }
}
