package com.action;

import java.lang.reflect.Method;  
import java.util.HashMap;  
import java.util.Map;

import com.server.netty.message.Message;
import com.server.player.serverPlayer;
import com.test.maintest;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
  
public class ActionMapUtil {  
    
    //private Map<Integer, ActionCell> commands = new HashMap<Integer, ActionCell>();
	// 改成快的和小的map
	public final static TLongObjectMap<ActionCell> commands = new TLongObjectHashMap<ActionCell>();
	
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
  
               
    public void putAction(Integer key, ActionCell action) {
    	commands.put(key, action);
    }
  
    public static void main(String[] args) throws Exception {
    	
    }
    
    
}  