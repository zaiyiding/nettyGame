package com.test;

import com.server.netty.message.Message;

public class maintest {
	public static void main(String[] args){	        
			System.out.println("hello world");
	    }	
		
		
		public static void handleHello(int test1, Message o1) {
			//int test1 = (int)args[0]; 
			//Message o1 = (Message) args[1];
			System.out.println("handleHello1 : " + o1.toString() + " key :" + String.valueOf(test1));
		}
		
		public void handleHello2(Object input) {
			// 数组参数不能传来传去..要当成一个object
			// 但是我们的实际要用的还是数组.需要强转一次
			Object[] args = (Object[])input;
			int test1 = (int)args[0]; 
			Message o1 = (Message) args[1];
			System.out.println("handleHello2 : " + o1.toString() + " key :" + String.valueOf(test1));
		}	

}
