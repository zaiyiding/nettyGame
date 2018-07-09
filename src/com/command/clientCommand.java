package com.command;

import com.action.actionCell;
import com.client.net.nettyClient;
import com.protobuff.message.clientToServer.client_to_server_register;
import com.server.netty.message.message;

public class clientCommand extends commandRunnable {

	
	private static clientCommand instance = new clientCommand();
	
	static public clientCommand Instance() {
		return instance;
	}
	
	private clientCommand() {
		init();
	}
	
	void init() {
		addCommand("send", (new actionCell(){    		    
			@Override
			public Object run(Object... args) {
				
				client_to_server_register tmpMessage = client_to_server_register
						.newBuilder().setAccount("test1").setPassword("passwd1").build();
				
				message test1 = new message(1, tmpMessage.toByteArray());
				nettyClient.Instance().sendMsg(test1);
				return null;
			}
		})); 
	}

	
}
