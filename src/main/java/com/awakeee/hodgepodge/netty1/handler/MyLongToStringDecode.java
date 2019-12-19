package com.awakeee.hodgepodge.netty1.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class MyLongToStringDecode extends MessageToMessageDecoder<Long> {

    @Override
    protected void decode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {

        System.out.println("MyLongToStringDecode invoke...");
        out.add(String.valueOf(msg));
    }
}
