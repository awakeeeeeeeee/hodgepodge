package com.awakeee.hodgepodge.netty1.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

//        ch.pipeline().addLast(new MyByteToLongDecoder());
        ch.pipeline().addLast(new MyByteToLongDecoder2());
        ch.pipeline().addLast(new MyLongToByteEncoder());
        ch.pipeline().addLast(new MyClientHandler());
    }
}
