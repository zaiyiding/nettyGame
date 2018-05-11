package com.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import com.tool.Tools;
import com.tool.*;
public class Message {
	//判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是int +int = 4 + 4 = 6  
    private static final int HEADER_SIZE = 8;  
    
    public static int getHeadSize() {
    	return HEADER_SIZE;
    }
    
    private int size;  
  
    private int id;        
  
    private String body;  
	
	public Message(int idInput, String strinput) {
		id     = idInput;
		body   = strinput;		
		size   = HEADER_SIZE+body.length();		
	}
	
	public void length(int input) {
		size = input;
	}
	
	 public byte[] toByte() throws IOException {  
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  	        
	        out.write(Tools.intToByte2(size));  
	        out.write(Tools.intToByte2(id)); 
	        byte[] bodyBytes = body.getBytes(Charset.forName("utf-8"));  
	        out.write(bodyBytes);
	        byte[] tmpByte = out.toByteArray();
	        System.out.println("toByte(): " + tmpByte.length);
	        return out.toByteArray();        
	 }
	
	 public String ToString() throws UnsupportedEncodingException, IOException {
		 String tmpString;
		 tmpString = "size " + size + " id "+ id + " string :" + body; 
		 return tmpString;
	 }
}
