package com.awakeee.hodgepodge.netty1.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

public class Test3 {

    public static void main(String[] args) {


        ByteBuffer byteBuffer = ByteBuffer.allocate(10);//nio 不会自动扩容

        for(int i=0;i<100;i++){
            byteBuffer.putInt(1);
        }

        ByteBuf buf = Unpooled.buffer(10);
        for(int i=0;i<100;i++){
            buf.writeByte(i);
        }

    }
}
