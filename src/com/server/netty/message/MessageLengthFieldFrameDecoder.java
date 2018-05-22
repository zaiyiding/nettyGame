package com.server.netty.message;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MessageLengthFieldFrameDecoder extends LengthFieldBasedFrameDecoder {

	public MessageLengthFieldFrameDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset,
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
	            throw new Exception("可读长度不够");  
	     }  
	
	     int size = in.readInt(); 
		 int id   = in.readInt();
		 
		 System.out.println("server start : size : " + size + " id :" + id);
		 
		 ByteBuf buf = in.readBytes(size-Message.getHeadSize());  
	     byte[] req = new byte[size-Message.getHeadSize()];  
	     buf.readBytes(req);  
	     String  body = new String(req);
	     System.out.println("server start : body : " + body);
	     Message tmpms = new Message(id, body.getBytes());	
		 return tmpms;
	 }
}
