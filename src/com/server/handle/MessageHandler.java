package com.server.handle;

import com.message.Message;

import io.netty.channel.ChannelHandlerAdapter;  
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;  

///https://blog.csdn.net/u010853261/article/details/55803933

public class MessageHandler extends ChannelInboundHandlerAdapter  {
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {		
		try {
			
			Message tmpms = (Message)msg ; 
			
			System.out.println("MessageHandler:" + tmpms.ToString()); 
        } finally {
            ReferenceCountUtil.release(msg);
        }
		
    }
}
