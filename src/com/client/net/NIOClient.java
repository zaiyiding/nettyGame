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
	            // ���ӵ�������  
	            Socket socket = new Socket("127.0.0.1", 12345);  

	            System.out.println("client ByteOrder" + ByteOrder.nativeOrder()); 
	            try {  
	                // ��������˷�����Ϣ��DataOutputStream  
	                OutputStream out = socket.getOutputStream();  
	                // װ�α�׼�����������ڴӿ���̨����  
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
	                    // �Ѵӿ���̨�õ�����Ϣ���͸�������  
	                    // out.writeUTF("�ͻ��ˣ�" + send);  
	                    // ��ȡ���Է���������Ϣ  
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