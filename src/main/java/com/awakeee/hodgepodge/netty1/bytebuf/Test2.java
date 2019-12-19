package com.awakeee.hodgepodge.netty1.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

public class Test2 {

    public static void main(String[] args) {

        //复合缓冲
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf headBuff = Unpooled.buffer(10);
        ByteBuf directBuff = Unpooled.directBuffer(8);

        compositeByteBuf.addComponents(headBuff,directBuff);
//        compositeByteBuf.removeComponent(0);

        Iterator<ByteBuf> iterator =  compositeByteBuf.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        compositeByteBuf.forEach(System.out::println);
    }
}
