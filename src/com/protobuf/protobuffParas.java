package com.protobuf;

import java.lang.reflect.Method;
import proto.protoJava.Any;
import com.action.ActionMapUtil;

public class protobuffParas {
	static ActionMapUtil myMap = new ActionMapUtil();
     
    public static Object invoteWithStatic(Integer key, Object... args) throws Exception {  
    	 try {
    		 return myMap.invoteWithStatic(key, args);
    	 }
    	 catch(Exception e) {
    		 e.printStackTrace();
    	 }
    	 return null;
    }  
    
    public static void put(int key, Method action) {
    	myMap.put(key, action); 
    }
    
    
    public static void initCallBack() throws NoSuchMethodException, SecurityException {
    	// put(1, proto.protoJava.Any.gps_data.class.getMethod("parseFrom", byte[].class));
    }
    
    public static void main(String[] args) throws Exception {  
    	/*
    	 initCallBack();
    	 
        Any.gps_data john = Any.gps_data.newBuilder().setId(1).setLon(13.1).build();
        
        System.out.println(john.toString());
        System.out.println("===== 构建GPS模型结束1 =====");
        
        proto.protoJava.Any.gps_data testReq;
        testReq = Any.gps_data.parseFrom(john.toByteArray());   
        System.out.println("===== 构建GPS模型结束2 ===== \n" + testReq.toString());
        
       
        proto.protoJava.Any.gps_data testReq2;
        testReq2 = (proto.protoJava.Any.gps_data)invoteWithStatic(1, john.toByteArray());
        if(null != testReq2) {
        	System.out.println("===== 构建GPS模型结束3 ===== " + testReq2.toString());
        }       
               */      
    }  
    
	
	
}
