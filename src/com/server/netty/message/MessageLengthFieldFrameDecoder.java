package com.server.netty.message;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class messageLengthFieldFrameDecoder extends LengthFieldBasedFrameDecoder {

	public messageLengthFieldFrameDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
		super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
		// TODO Auto-generated constructor stub
	}

	 @Override  
	    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		 
		 if (in == null) {  
	            return null;  
	     }  
	     if (in.readableBytes() < 8) {  
	            throw new Exception("�ɶ����Ȳ���");  
	     }  
	
	     int size = in.readInt(); 
		 int id   = in.readInt();
		 
		 System.out.println("server start : size : " + size + " id :" + id);
		 
		 ByteBuf buf = in.readBytes(size - message.getHeadSize());  
	     byte[] req = new byte[size - message.getHeadSize()];  
	     buf.readBytes(req);  
	     String  body = new String(req);
	     System.out.println("server start : body : " + body);
	     message tmpms = new message(id, body.getBytes());	
		 return tmpms;
	 }
}
