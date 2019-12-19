package com.awakeee.hodgepodge.netty1.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Test0 {

    public static void main(String[] args) {

        ByteBuf b = Unpooled.buffer(10);

        for(int i=0;i<10;i++){
            b.writeByte(i);
        }

        System.out.println(b.readerIndex());
        System.out.println(b.writerIndex());

//        while (b.isReadable()){
//            System.out.println(b.readByte());
//        }

        for(int i=0;i<b.capacity();i++){
            System.out.println(b.readByte());
            System.out.println("rdx: "+b.readerIndex());
        }

    }
}
