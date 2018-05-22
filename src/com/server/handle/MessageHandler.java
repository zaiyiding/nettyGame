package com.server.handle;

import com.server.managers.messageManager;
import com.server.netty.message.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;  

public class MessageHandler extends ChannelInboundHandlerAdapter  {
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {		
		try {
			
			Message tmpms = (Message)msg ; 
			messageManager.getInstance().invokeMap(tmpms);
			System.out.println("MessageHandler:" + tmpms.toString()); 
        } finally {
            ReferenceCountUtil.release(msg);
        }
		
    }
}
