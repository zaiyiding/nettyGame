package com.message;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.handler.codec.MessageToByteEncoder;  

public class MessageEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext arg0, Message msg, ByteBuf outputbuf) throws Exception {
		// TODO Auto-generated method stub
		
		byte[] output = msg.toByte();
		outputbuf.writeBytes(output);
		System.out.println("encode : " + output);  
		
	}

}
