package com.awakeee.hodgepodge.netty1.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class OldIoClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",8899);

        FileInputStream fileInputStream = new FileInputStream("/Users/cool/Downloads/安装包/jdk-8u161-linux-x64.tar.gz");

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        long startTime = System.currentTimeMillis();

        byte[] bytes = new byte[4096];

        int read = 0;
        int total = 0;

        while ((read = fileInputStream.read(bytes)) != -1){
//            System.out.println("read:"+read);
            dataOutputStream.write(bytes);
            total += read;
        }

        System.out.println("发送的字节数: "+total + ",耗时: "+ (System.currentTimeMillis() - startTime));

        fileInputStream.close();
        dataOutputStream.close();
        socket.close();
    }
}
