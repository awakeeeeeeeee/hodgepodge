package com.awakeee.hodgepodge.netty1.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class MyClient {

    public static void main(String[] args) throws InterruptedException {


        EventLoopGroup eventLoop = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoop).channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("localhost",8899))
                .handler(new MyClientInitializer());

        ChannelFuture channelFuture = bootstrap.connect().sync();
        channelFuture.channel().closeFuture().sync();

        eventLoop.shutdownGracefully().sync();

    }
}
