package com.server.netty.message;

import com.queue.MessageQueue;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageLengthFieldFrameHandler extends SimpleChannelInboundHandler<Object>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		 if(msg instanceof Message) {  
			 Message customMsg = (Message)msg;  
			 customMsg.setChannel(ctx.channel());
			 MessageQueue.getInstance().put(customMsg);
	            System.out.println("MessageLengthFieldFrameHandler :"+ customMsg.toString());  
	        } 		
	}
	@Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
		System.out.println("Unexpected exception from downstream." + cause.toString());  
        ctx.close();  
    } 

}
