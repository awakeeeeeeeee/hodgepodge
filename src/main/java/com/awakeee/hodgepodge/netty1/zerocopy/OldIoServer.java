package com.awakeee.hodgepodge.netty1.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIoServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8899);

        while (true){
            System.out.println("开始接受连接.....");
            Socket socket = serverSocket.accept();
            System.out.println(socket);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());


            byte[] bytes = new byte[4096];
            int total = 0;

            while (true){
                int read = dataInputStream.read(bytes,0,bytes.length);
                if(read == -1){
                    break;
                }
                total += read;
            }

            System.out.println("server ending......");
            System.out.println("读取字节数: "+total);
        }

    }
}
