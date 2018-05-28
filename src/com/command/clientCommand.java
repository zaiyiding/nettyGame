package com.command;

import com.action.ActionCell;
import com.client.net.NettyClient;
import com.protobuff.message.ClientToServer.client_to_server_register;
import com.server.netty.message.Message;

public class clientCommand extends commandRunnable {

	
	private static clientCommand instance = new clientCommand();
	
	static public clientCommand Instance() {
		return instance;
	}
	
	private clientCommand() {
		init();
	}
	
	void init() {
		addCommand("send", (new ActionCell(){    		    
			@Override
			public Object run(Object... args) {
				
				client_to_server_register tmpMessage = client_to_server_register
						.newBuilder().setAccount("test1").setPassword("passwd1").build();
				
				Message test1 = new Message(1, tmpMessage.toByteArray());
				NettyClient.Instance().sendMsg(test1);
				return null;
			}
		})); 
	}

	
}
