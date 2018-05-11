package com.client.net;

import java.io.IOException;  
import java.io.OutputStream;  
import java.net.Socket;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Scanner;

import com.message.Message;
import com.sun.corba.se.impl.ior.ByteBuffer;  
  
public class NIOClient {  
	public static int count = 0;
	
	public void testConect() {
		 try {  
	            // 连接到服务器  
	            Socket socket = new Socket("127.0.0.1", 12345);  

	            System.out.println("client ByteOrder" + ByteOrder.nativeOrder()); 
	            try {  
	                // 向服务器端发送信息的DataOutputStream  
	                OutputStream out = socket.getOutputStream();  
	                // 装饰标准输入流，用于从控制台输入  
	                //Scanner scanner = new Scanner(System.in);  
	                while (count < 20) {  
	                    //String send = scanner.nextLine();  
	                    int ntmpCount = count +10;
	                    String tmpStrign = "test count:"+count*10;
	                    System.out.println("tmpStrign " + tmpStrign.length()); 
	                    Message message = new Message(ntmpCount , tmpStrign);  
	                    out.write(message.toByte()); 
	                    //out.write('\n');  
	                    out.flush();  
	                    // 把从控制台得到的信息传送给服务器  
	                    // out.writeUTF("客户端：" + send);  
	                    // 读取来自服务器的信息  
	                    ++count;
	                }  
	  
	            } finally {  
	                socket.close();  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	}
	
    public static void main(String[] args) {  
    	 NIOClient client1 = new NIOClient();
    	 client1.testConect();
    }  
}  