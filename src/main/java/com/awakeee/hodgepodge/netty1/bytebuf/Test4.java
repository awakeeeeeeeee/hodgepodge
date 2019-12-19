package com.awakeee.hodgepodge.netty1.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Test4 {

    public static void main(String[] args) {


        ByteBuf byteBuf = Unpooled.buffer(10);
        byteBuf.writeInt(1);
        byteBuf.writeInt(2);

        while (byteBuf.isReadable()) {
            System.out.println(byteBuf.readInt());
        }

//        System.out.println(byteBuf.readInt());
    }
}
