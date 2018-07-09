package com.protobuff;

import com.action.actionCell;
import com.action.actionMapUtil;

public class protobuffParas {
	static actionMapUtil myMap = new actionMapUtil();
     
    public static Object invoteWithStatic(Integer key, Object... args) throws Exception {  
    	 try {
    		 return myMap.invoteWithStatic(key, args);
    	 }
    	 catch(Exception e) {
    		 e.printStackTrace();
    	 }
    	 return null;
    }  
    
    public static void putAction(Integer key, actionCell action) {
    	myMap.putAction(key, action); 
    	
    }
    
    
    public static void initCallBack() throws NoSuchMethodException, SecurityException {
    	//put(1, client_to_server_register.class.getMethod("parseFrom", byte[].class));
    	
    	/*putAction(1,  (new ActionCell(){    		    
				@Override
				public Object run(Object... args) {
					try {
						return client_to_server_register.parseFrom((byte[])args[0]);
					} catch (InvalidProtocolBufferException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
    		}));*/   
    }
    
    public static void main(String[] args) throws Exception {  
    	/*
    	 initCallBack();
    	 
        client_to_server_register john = client_to_server_register.newBuilder().setId(1).build();
        
        System.out.println(john.toString());
        System.out.println("===== 构建GPS模型结束1 =====");
        
        client_to_server_register testReq;
        testReq = client_to_server_register.parseFrom(john.toByteArray());   
        System.out.println("===== 构建GPS模型结束2 ===== \n" + testReq.toString());
        
       
        client_to_server_register testReq2;
        testReq2 = (client_to_server_register)invoteWithStatic(1, john.toByteArray());
        if(null != testReq2) {
        	System.out.println("===== 构建GPS模型结束3 ===== " + testReq2.toString());
        }  */     
                  
    }  
    
	
	
}
