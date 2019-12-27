package com.awakeee.hodgepodge.netty1.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

public class PersonProtocol {

    private int length;

    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }



    public static void main(String[] args) {

        ByteBuf msg = Unpooled.buffer();

        byte[] bytes = new byte[10];
        for(int i=0;i<bytes.length;i++){
            bytes[i] = (byte)i;
        }

        msg.writeBytes(bytes);

        System.out.println(msg.readableBytes());
        byte[] bytes1 = new byte[msg.readableBytes()];
        msg.readBytes(bytes1);

        for(int i=0;i<bytes1.length;i++){
            System.out.println((int)bytes1[i]);
        }

//        while (msg.isReadable()){
//            System.out.println((int)msg.readByte());
//        }

    }
}
