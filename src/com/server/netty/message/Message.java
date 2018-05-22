package com.server.netty.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.tool.Tools;

import io.netty.channel.Channel;
public class Message {
	//判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是int +int = 4 + 4 = 6  
    private static final int HEADER_SIZE = 8;  
    
    public static int getHeadSize() {
    	return HEADER_SIZE;
    }
    
    private int size;  
  
    private int id;        
  
    private byte[] body;  
    
    
    private Channel channel;
    
    public void setChannel(Channel input) {
    	channel = input;
    }
    
    public Channel getChannel() {
    	return channel;
    }
    
    public byte[] getBody() {
    	return body;
    }
	    
	public Message(int idInput, byte[] byteInput) {
		id     = idInput;
		body   = byteInput;		
		size   = HEADER_SIZE+body.length;		
	}
	

	public void length(int input) {
		size = input;
	}
	
	public int getid(){
		return id;		
	}
	
	 public byte[] toByte() throws IOException {  
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  	        
	        out.write(Tools.intToByte2(size));  
	        out.write(Tools.intToByte2(id));
	        out.write(body);
	        byte[] tmpByte = out.toByteArray();
	        System.out.println("toByte(): " + tmpByte.length);
	        return out.toByteArray();        
	 }
	 @Override
	 public String toString() {
		 String tmpString;
		 tmpString = "size " + size + " id "+ id + " string :" + body; 
		 return tmpString;
	 }
}
