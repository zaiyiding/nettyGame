package com.action;

import java.lang.reflect.Method;  
import java.util.HashMap;  
import java.util.Map;

import com.message.Message;
import com.test.maintest;
  
public class ActionMapUtil {  
  
    private static Map<Integer, Method> map = new HashMap<Integer, Method>();  
    public static Object invoteWithStatic(Integer key, Object... args) throws Exception {  
    	Method action = map.get(key);  
        if (action != null) {  
            try {  
                return action.invoke(null, args);  
            } catch (Exception e) {  
                throw e;  
            }  
        }  
        return null;  
    }  
  
    
    public static Object invoteWithObject(Object callObj, Integer key, Object... args) throws Exception {  
    	Method action = map.get(key);  
        if (action != null) {  
            try {  
                return action.invoke(callObj, args);  
            } catch (Exception e) {  
                throw e;  
            }  
        }  
        return null;  
    }  
    
    public void put(Integer key, Method action) { 
        map.put(key, action);  
    }  
  
    public static void main(String[] args) throws Exception {
    	/*ActionMapUtil testMap = new ActionMapUtil();	 
    	try {    
    		testMap.put(1, maintest.class.getMethod("handleHello", new Class[] {int.class, Message.class}));
    		Message testMsg2 = new Message(1, "aaaa");   
    		ActionMapUtil.invoteWithStatic(1, new Object[] {1 , testMsg2});
       	}catch(NoSuchMethodException e) {
       		e.printStackTrace();
       	}catch(SecurityException e) {
       		e.printStackTrace();
       	}catch (Exception e) {
       		e.printStackTrace();
       	}
    	
    	
    	try {    
    		maintest testMain = new maintest();
    		testMap.put(1, testMain.getClass().getMethod("handleHello2", Object.class));
    		Message testMsg2 = new Message(1, "aaaa");   
    		// 数组参数不能传来传去..要当成一个object
    		ActionMapUtil.invoteWithObject(testMain, 1, (Object)(new Object[] {1 , testMsg2}));
       	}catch(NoSuchMethodException e) {
       		e.printStackTrace();
       	}catch(SecurityException e) {
       		e.printStackTrace();
       	}catch (Exception e) {
       		e.printStackTrace();
       	}*/
    	
    	
    }
    
    
}  