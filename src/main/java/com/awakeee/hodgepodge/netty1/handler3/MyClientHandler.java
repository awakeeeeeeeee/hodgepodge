package com.awakeee.hodgepodge.netty1.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {


        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接受的数据: ");
        System.out.println("长度: "+length);
        System.out.println("内容: "+new String(content, CharsetUtil.UTF_8));

        System.out.println("客户端接受的消息数量: "+ (++count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for(int i=0;i<10;i++){
            String message = "send from client ";
            byte[] content = message.getBytes(CharsetUtil.UTF_8);
            int length = message.getBytes(CharsetUtil.UTF_8).length;

            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);

            ctx.writeAndFlush(personProtocol);
        }
    }

    public static void main(String[] args) {
        String message = "send from client ";
        int length = message.getBytes(CharsetUtil.UTF_8).length;
        System.out.println(length);

    }
}
