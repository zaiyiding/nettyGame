package com.server.netty.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.tool.tools;

import io.netty.channel.Channel;
public class message {
	//�жϴ��Ϳͻ��˴��͹����������Ƿ���Э�鴫�䣬ͷ����Ϣ�Ĵ�СӦ����int +int = 4 + 4 = 6  
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
	    
	public message(int idInput, byte[] byteInput) {
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
	        out.write(tools.intToByte2(size));  
	        out.write(tools.intToByte2(id));
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
