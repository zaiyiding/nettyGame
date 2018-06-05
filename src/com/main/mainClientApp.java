package com.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.client.manager.clientLoginManager;
import com.client.net.NettyClient;
import com.command.clientCommand;
import com.server.managers.gameManger;
import com.server.managers.messageManager;

public class mainClientApp {
	 public static void main(String[] args) {
	    	eventMangerInit();
	    	ExecutorService exec = Executors.newCachedThreadPool(); 		
	 		// ≥ı ºªØnetty
	 		//NettyClient test1 = new NettyClient();
	 		exec.execute(NettyClient.Instance());			
	 		exec.execute(clientCommand.Instance());  		
			gameManger.getInstance().noticeInit();	
			System.out.println("client start...............");
			while(true) {			
				try {
					gameManger.getInstance().run();
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			
	    }
	    
	    static public void eventMangerInit() {
	    	clientLoginManager.getInstance();
	    	messageManager.getInstance();
	    }
}
