package com.tool;

public class Tools {
	/**  
	  * 将int转为低字节在前，高字节在后的byte数组  
	  * @param n int  
	  * @return byte[]  
	  */  
	public static byte[] intToByte(int newint) {  
        byte[] intbyte = new byte[4];  
        intbyte[3] = (byte) ((newint >> 24) & 0xFF);  
        intbyte[2] = (byte) ((newint >> 16) & 0xFF);  
        intbyte[1] = (byte) ((newint >> 8) & 0xFF);  
        intbyte[0] = (byte) (newint & 0xFF);  
        return intbyte;  
    }  
  
	/**  
	  * 将int转为高字节在前，低字节在后的byte数组  
	  * @param n int  
	  * @return byte[]  
	  */  
	public static byte[] intToByte2(int value)   
	{   
	    byte[] src = new byte[4];  
	    src[0] = (byte) ((value>>24) & 0xFF);  
	    src[1] = (byte) ((value>>16)& 0xFF);  
	    src[2] = (byte) ((value>>8)&0xFF);    
	    src[3] = (byte) (value & 0xFF);       
	    return src;  
	}  
	
	/**  
	  * 将高字节数组转换为int  
	  * @param b byte[]  
	  * @return int  
	  */  
	public static int hBytesToInt(byte[] b) {  
	  int s = 0;  
	  for (int i = 0; i < 3; i++) {  
	    if (b[0] >= 0) {  
	    s = s + b[0];  
	    } else {  
	    s = s + 256 + b[0];  
	    }  
	    s = s * 256;  
	  }  
	  if (b[3] >= 0) {  
	    s = s + b[3];  
	  } else {  
	    s = s + 256 + b[3];  
	  }  
	  return s;  
	}   
}
