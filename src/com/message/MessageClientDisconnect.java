package com.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MessageClientDisconnect extends ChannelInboundHandlerAdapter{
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("client out ip" + ctx.channel().remoteAddress().toString() + " id :");
	}
}
