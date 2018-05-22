package com.server.managers;



import com.action.ActionCell;
import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuff.message.ClientToServer.client_to_server_register;

public class loginManager implements initAndEndObersver{

	private static final loginManager instance = new loginManager();
	
	private loginManager() {
		gameManger.getInstance().addInitAndEndObserver(this);
	}

	public static loginManager getInstance() {
		return instance;
	}
	
	public void responeClientResigter(client_to_server_register msgInput) {
		System.out.println("responeClientResigter: im coming , im: " + msgInput.getAccount() + " psw: " + msgInput.getPassword()); 
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {					
			messageManager.getInstance().putAction(1,  (new ActionCell(){    		    
				@Override
				public Object run(Object... args) {
					try {
						loginManager.getInstance().responeClientResigter(client_to_server_register.parseFrom((byte[])args[0]));
					} catch (InvalidProtocolBufferException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		return;
	}	
	
}
