package com.awakeee.hodgepodge.netty1.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class Test1 {


    public static void main(String[] args) {


        ByteBuf byteBuf = Unpooled.copiedBuffer("长hello world", Charset.forName("utf-8"));

        if(byteBuf.hasArray()){//是否堆上缓冲 headBuffer
            byte[] bytes = byteBuf.array();
            System.out.println(new String(bytes, Charset.forName("utf-8")));

            System.out.println(byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.readableBytes());

            for(int i=0;i<byteBuf.readableBytes();i++){
                System.out.println((char)byteBuf.getByte(i));
//                System.out.println((char)byteBuf.readByte());
            }

            System.out.println(byteBuf.getCharSequence(0,4, CharsetUtil.UTF_8));
            System.out.println(byteBuf.getCharSequence(4,6, CharsetUtil.UTF_8));

        }else {
            //direct buffer
            //操作系统上的直接缓冲,jvm保存该缓冲区引用
        }

    }
}
