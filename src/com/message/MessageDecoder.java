package com.message;

import java.util.List;
import com.message.Message;
import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.handler.codec.ByteToMessageDecoder;  
import io.netty.handler.codec.CorruptedFrameException;  

public class MessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		buffer.markReaderIndex();
		int readAbleCoutn = buffer.readableBytes();
		if (buffer.readableBytes() < 16) {  
            //throw new CorruptedFrameException("包长度问题");		
			System.out.println("buffer.readableBytes : " +readAbleCoutn); 
			return;
        }  
		System.out.println("buffer.readableBytes : " +readAbleCoutn); 
		int size = buffer.readInt(); //buffer.readInt();
		int id   = buffer.readInt();
		int arg0 = buffer.readInt();
		int arg1 = buffer.readInt();		
		//Message tmpms = new Message(size, id, arg0, arg1);		
		//out.add(tmpms);
		// TODO Auto-generated method stub
		System.out.println("encode: size:" + size + " id : " + id + " arg0 : " + arg0 + " arg1 : " + arg1); 
	}
}
