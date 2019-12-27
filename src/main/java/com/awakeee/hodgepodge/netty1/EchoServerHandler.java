package com.awakeee.hodgepodge.netty1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;


public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server reveived : "+msg.toString());
        System.out.println("server received buff :"+in.toString(CharsetUtil.UTF_8));
//        ctx.writeAndFlush("好的，我收到了");
        ctx.writeAndFlush(Unpooled.copiedBuffer("好的，我收到了", CharsetUtil.UTF_8));
//        ctx.channel().writeAndFlush("xxxx");
        NioEventLoop nioEventLoop = (NioEventLoop) ctx.channel().eventLoop();
        System.out.println(nioEventLoop.pendingTasks());
        nioEventLoop.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1111);
            }
        });
        System.out.println(nioEventLoop.pendingTasks());
    }



//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出错了..........");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        channelGroup.add(ctx.channel());
        //stringdecoder
        channelGroup.writeAndFlush("客户端连接"+ ctx.channel().remoteAddress() + "加入");
        //buffer 不指定encoder 默认是buffer
        channelGroup.writeAndFlush(Unpooled.copiedBuffer("客户端连接"+ ctx.channel().remoteAddress() + "加入", CharsetUtil.UTF_8));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        channelGroup.remove(ctx.channel());
        channelGroup.writeAndFlush("客户端连接"+ ctx.channel().remoteAddress() + "离开");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("客户端连接"+ ctx.channel().remoteAddress() + "上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("客户端连接"+ ctx.channel().remoteAddress() + "下线");
    }
}
