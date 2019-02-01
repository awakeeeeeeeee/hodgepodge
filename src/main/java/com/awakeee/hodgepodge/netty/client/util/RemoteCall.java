package com.awakeee.hodgepodge.netty.client.util;

import com.awakeee.hodgepodge.netty.client.handler.NettyClientHandlerAdapter;
import com.awakeee.hodgepodge.netty.server.request.RequestInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.beans.factory.annotation.Value;

public class RemoteCall {

    private static ChannelFuture future;

    @Value("${netty.server.port}")
    private int port;


    public static Object call(RequestInfo req){
        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        NettyClientHandlerAdapter nettyClientHandlerAdapter = new NettyClientHandlerAdapter();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                        pipeline.addLast("encoder", new ObjectEncoder());
                        pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                        pipeline.addLast("handler",nettyClientHandlerAdapter);
                    }
                });
        try {
            future = b.connect("localhost", 12345).sync();
            future.channel().writeAndFlush(req).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
        return nettyClientHandlerAdapter.getResponse();
    }
}
