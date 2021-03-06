package com.server.netty.message;

import com.server.managers.serverPlayerManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class messageConnectEvent extends ChannelInboundHandlerAdapter{
	public String apptype = "null";
	
	public String getApptype() {
		return apptype;
	}
	
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	
	public messageConnectEvent(String nInput) {
		apptype = nInput;
	}
	
	
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("client out ip" + ctx.channel().remoteAddress().toString() + " type : " + apptype);
        serverPlayerManager.Instance().OnPlayerOffline(ctx.channel());
        
	}
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("client connect ip" + ctx.channel().remoteAddress().toString() + " type : " + apptype);
	}
	
}
