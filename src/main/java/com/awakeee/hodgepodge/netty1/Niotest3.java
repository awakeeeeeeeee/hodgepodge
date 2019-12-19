package com.awakeee.hodgepodge.netty1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Niotest3 {

    public static void main(String[] args) throws IOException {


        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");


        FileChannel inputChannle = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();

        //inputChannle.lock();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //ByteBuffer.allocateDirect(512); 0拷贝

        while (true){

            byteBuffer.clear();

            int read = inputChannle.read(byteBuffer);

            if(-1 ==  read){
                break;
            }

            byteBuffer.flip();

            outputChannel.write(byteBuffer);

        }

        inputChannle.close();
        outputChannel.close();
    }
}
