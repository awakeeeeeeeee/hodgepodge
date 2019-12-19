package com.awakeee.hodgepodge.netty1.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {


        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("服务端接受的数据: ");
        System.out.println("长度: "+length);
        System.out.println("内容: "+new String(content, CharsetUtil.UTF_8));

        System.out.println("服务端接受的消息数量: "+ (++count));

        String responseMessage = UUID.randomUUID().toString();

        int responseLength = responseMessage.getBytes("utf-8").length;

        byte[] responseContent = responseMessage.getBytes();

        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(responseLength);
        personProtocol.setContent(responseContent);

        ctx.writeAndFlush(personProtocol);

    }
}
