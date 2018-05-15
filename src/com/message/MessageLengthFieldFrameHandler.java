package com.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageLengthFieldFrameHandler extends SimpleChannelInboundHandler<Object>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		 if(msg instanceof Message) {  
			 Message customMsg = (Message)msg;  
	            System.out.println("MessageLengthFieldFrameHandler :"+ customMsg.toString());  
	        } 		
	}

}
