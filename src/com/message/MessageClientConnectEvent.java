package com.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MessageClientConnectEvent extends ChannelInboundHandlerAdapter{
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("client out ip" + ctx.channel().remoteAddress().toString() + " id :");
	}
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("client connect ip" + ctx.channel().remoteAddress().toString() + " id :");
	}
	
}