package com.awakeee.hodgepodge.netty1;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioClient {

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("localhost",8899);
//        socket.connect(new InetSocketAddress("localhost",8899));

//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        bufferedWriter.write("123123123213123\n");
//        bufferedWriter.flush();
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        String msg;
//        while ((msg = bufferedReader.readLine())!= null){ //readLine必须接受到换行符才会结束，否则计算读到数据也会一直阻塞
//            System.out.println("我是客户端收到了你的消息: "+msg);
//        }


//
//        while (true){

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(()->{
               while (true){
                   BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));
                   String read = bufferedReader1.readLine();

                   BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                   bufferedWriter.write(read + "\r");
                   bufferedWriter.write("END");
                   bufferedWriter.flush();


                   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                   String msg;
                   while ((msg = bufferedReader.readLine()).equalsIgnoreCase("END")){
                       break;
                   }
                   System.out.println("我是客户端收到了你的消息: "+msg);
               }
            });
        }
//    }
}
