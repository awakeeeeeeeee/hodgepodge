package com.awakeee.hodgepodge.netty1.zerocopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {

    public static void main(String[] args) throws IOException {


//        FileInputStream fileInputStream = new FileInputStream("input.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
//
//        byte[] bytes = new byte[1];
//        int r ;
//        while ((r = fileInputStream.read(bytes)) != -1){
//            System.out.println((char)bytes[0]);
//            fileOutputStream.write(bytes);
//        }
//
//        System.out.println(r);


        FileInputStream in = new FileInputStream("in.txt");
        FileChannel fileChannel = in.getChannel();


        FileOutputStream out = new FileOutputStream("out.txt");
        FileChannel fileChannel1 = out.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        int r1 ;
        while ((r1 = fileChannel.read(byteBuffer)) != -1){
            byteBuffer.flip();
            fileChannel1.write(byteBuffer);
            byteBuffer.clear();
            byteBuffer.rewind();

        }








    }
}
