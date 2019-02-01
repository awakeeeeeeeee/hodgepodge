package com.awakeee.hodgepodge.netty.server.handler;

import com.awakeee.hodgepodge.netty.server.utils.MethodInvokeUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ConcurrentHashMap;

@ChannelHandler.Sharable
public class NettyServerHandlerAdapter extends ChannelInboundHandlerAdapter {

    private MethodInvokeUtil methodInvokeUtil = new MethodInvokeUtil();

    public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<String,Object>();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object res = methodInvokeUtil.processMethod(msg);
        if(null != res){
            ctx.writeAndFlush(res);
        }
        ctx.close();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
