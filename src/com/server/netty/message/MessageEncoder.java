package com.server.netty.message;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.handler.codec.MessageToByteEncoder;  

public class messageEncoder extends MessageToByteEncoder<message> {

	@Override
	protected void encode(ChannelHandlerContext arg0, message msg, ByteBuf outputbuf) throws Exception {
		// TODO Auto-generated method stub
		
		byte[] output = msg.toByte();
		outputbuf.writeBytes(output);
		System.out.println("encode : " + output);  
		
	}

}
