package com.client.manager;

import com.action.ActionCell;
import com.protobuff.message.messageId;
import com.server.managers.gameManger;
import com.server.managers.initAndEndObersver;
import com.server.managers.messageManager;
import com.server.netty.message.Message;

public class clientLoginManager implements initAndEndObersver {
	
	private static clientLoginManager instance = new clientLoginManager();
	
	private clientLoginManager() {
		gameManger.getInstance().addInitAndEndObserver(this);
	}
	
	public static clientLoginManager getInstance() {
		return instance;	
	}
	
	@Override
	public void init() {
		try {					
			messageManager.getInstance().putAction(messageId._client_to_server_register_respone,  (new ActionCell(){    		    
				@Override
				public Object run(Object... args) {					
						Message tmpMsg = (Message)args[0];
						clientLoginManager.getInstance().onServerLoginRespone(tmpMsg);
					
					return null;
				}
    		})); 
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
	
	public void onServerLoginRespone(Message inputMsg) {
		System.out.println("onServerLoginRespone");
	}
}
