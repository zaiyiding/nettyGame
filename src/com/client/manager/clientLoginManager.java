package com.client.manager;

import com.action.actionCell;
import com.protobuff.message.messageId;
import com.server.managers.gameManger;
import com.server.managers.initAndEndObersver;
import com.server.managers.messageManager;
import com.server.netty.message.message;

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
			messageManager.getInstance().putAction(messageId._client_to_server_register_respone,  (new actionCell(){    		    
				@Override
				public Object run(Object... args) {					
						message tmpMsg = (message)args[0];
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
	
	public void onServerLoginRespone(message inputMsg) {
		System.out.println("onServerLoginRespone");
	}
}
