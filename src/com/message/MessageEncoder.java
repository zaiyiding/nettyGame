package com.message;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.handler.codec.MessageToByteEncoder;  

public class MessageEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext arg0, Message arg1, ByteBuf arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("encode");  
	}

}
