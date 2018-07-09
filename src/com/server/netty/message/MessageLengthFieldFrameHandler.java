package com.server.netty.message;

import com.queue.messageQueue;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class messageLengthFieldFrameHandler extends SimpleChannelInboundHandler<Object>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		 if(msg instanceof message) {  
			 message customMsg = (message)msg;  
			 customMsg.setChannel(ctx.channel());
			 messageQueue.getInstance().put(customMsg);
	            System.out.println("MessageLengthFieldFrameHandler :"+ customMsg.toString());  
	        } 		
	}
	@Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
		System.out.println("Unexpected exception from downstream." + cause.toString());  
        ctx.close();  
    } 

}
