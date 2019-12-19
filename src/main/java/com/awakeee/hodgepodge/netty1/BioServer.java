package com.awakeee.hodgepodge.netty1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8899);
        System.out.println("开始监听8899");
        Socket socket = serverSocket.accept();
        System.out.println("连接请求: "+socket);

        while (true){
            PrintWriter printWriter;

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("服务器开始接受数据.......");

            String message;
            while ((message = bufferedReader.readLine()).equalsIgnoreCase("END")){
                break;
            }
            System.out.println("服务器收到了: "+message);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("服务器返回了"+message + "\r");
            bufferedWriter.write("END");
            bufferedWriter.flush();
        }
    }
}
