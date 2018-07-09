package com.server.managers;



import com.action.actionCell;
import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuff.message.clientToServer.client_to_server_register;
import com.protobuff.message.clientToServer.client_to_server_register_respone;
import com.protobuff.message.messageId;
import com.server.netty.message.message;
import com.server.player.serverPlayer;

public class loginManager implements initAndEndObersver{

	private static final loginManager instance = new loginManager();
	
	private loginManager() {
		gameManger.getInstance().addInitAndEndObserver(this);
	}

	public static loginManager getInstance() {
		return instance;
	}
	
	public void responeClientResigter(message msgInput) {
		client_to_server_register msg = null;
		try {
			msg = client_to_server_register.parseFrom((byte[])msgInput.getBody());
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("responeClientResigter: im coming , im: " + msg.getAccount() + " psw: " + msg.getPassword());
		int loginError = 0;
		if(!serverPlayerManager.Instance().isExistPlayer(msg.getId())) {
			// todo: 不可信任.回头要走数据库
			serverPlayer tmpPlayer = new serverPlayer();
			tmpPlayer.setChannel(msgInput.getChannel());
			tmpPlayer.setId(msg.getId());
			tmpPlayer.setAccount(msg.getAccount());
			serverPlayerManager.Instance().addPlayer(tmpPlayer);		
			loginError = 0;
			System.out.println(tmpPlayer.getAccount() + "login success");
		}else {
			loginError = 1;
		}
		
		client_to_server_register_respone tmpRespone = client_to_server_register_respone.newBuilder().setErrorCode(loginError).setAccount(msg.getAccount()).build();
		message tmpRes = new message(messageId._client_to_server_register_respone, tmpRespone.toByteArray());
				
		msgInput.getChannel().writeAndFlush(tmpRes);
		System.out.println("responeClientResigter: sended respone");
		if(loginError > 0) {			
			// 可以一个函数内发+关.
			msgInput.getChannel().close();
		}
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {					
			messageManager.getInstance().putAction(messageId._client_to_server_register,  (new actionCell(){    		    
				@Override
				public Object run(Object... args) {					
						message tmpMsg = (message)args[0];
						loginManager.getInstance().responeClientResigter(tmpMsg);
					
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
