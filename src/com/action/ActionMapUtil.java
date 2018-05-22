package com.action;

import java.lang.reflect.Method;  
import java.util.HashMap;  
import java.util.Map;

import com.server.netty.message.Message;
import com.test.maintest;
  
public class ActionMapUtil {  
  
    private Map<Integer, Method> map = new HashMap<Integer, Method>();  
    private Map<Integer, ActionCell> commands = new HashMap<Integer, ActionCell>();
	
    public Object invoteWithStatic(Integer key, Object... args) throws Exception {     	
    	ActionCell tmpAciton = commands.get(key);
    	 if (tmpAciton != null) {  
             try {  
                 return tmpAciton.run(args);  
             } catch (Exception e) {  
                 throw e;  
             }  
         }  
        return null;  
    }  
  
    
    public Object invoteWithObject(Object callObj, Integer key, Object... args) throws Exception {  
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
        
    public void putAction(Integer key, ActionCell action) {
    	commands.put(key, action);
    }
  
    public static void main(String[] args) throws Exception {
    	ActionMapUtil testMap = new ActionMapUtil();	 
    	maintest test1 = new maintest();
    	Message testMsg2 = new Message(1, "aaaa".getBytes());   
    	try {    
    		//testMap.put(1, maintest.class.getMethod("handleHello", new Class[] {int.class, Message.class}));    		
    		testMap.putAction(1, (new ActionCell(){    		    
				@Override
				public Object run(Object... args) {
					test1.handleHello2(args);
					return null;
				}
    		}));    
    		testMap.invoteWithStatic(1, new Object[] {1 , testMsg2});
       	}catch(NoSuchMethodException e) {
       		e.printStackTrace();
       	}catch(SecurityException e) {
       		e.printStackTrace();
       	}catch (Exception e) {
       		e.printStackTrace();
       	}
    	
    	/*
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