package com.awakeee.hodgepodge.netty1;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class Niotest {

    public static void main(String[] args) {


        //bufff.read()
        //buff.flip)()
        //buff.write()


        IntBuffer buffer = IntBuffer.allocate(10);


        //写
        for(int i=0; i< 3;i++){
            int n = new SecureRandom().nextInt(20);
            buffer.put(n);
        }



        System.out.println(buffer.hasRemaining());

        System.out.print(buffer.limit());
        System.out.print(" ");
        System.out.print(buffer.capacity());
        System.out.print(" ");
        System.out.print(buffer.position());
        System.out.println();

        buffer.flip();//读写状态转换

        System.out.println(buffer.hasRemaining());

        System.out.print(buffer.limit());
        System.out.print(" ");
        System.out.print(buffer.capacity());
        System.out.print(" ");
        System.out.print(buffer.position());
        System.out.println();

        buffer.flip();

        System.out.println(buffer.hasRemaining());

        System.out.print(buffer.limit());
        System.out.print(" ");
        System.out.print(buffer.capacity());
        System.out.print(" ");
        System.out.print(buffer.position());
        System.out.println();

        buffer.flip();

        System.out.println(buffer.hasRemaining());

        System.out.print(buffer.limit());
        System.out.print(" ");
        System.out.print(buffer.capacity());
        System.out.print(" ");
        System.out.print(buffer.position());
        System.out.println();

        System.out.println(buffer.hasRemaining());

        //读
//        while (buffer.hasRemaining()) {
//            System.out.println(buffer.get());
//        }

    }
}
