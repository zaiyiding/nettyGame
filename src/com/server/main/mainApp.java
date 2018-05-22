package com.server.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.server.managers.gameManger;
import com.server.managers.loginManager;
import com.server.managers.messageManager;
import com.server.net.NettyServer;

public class mainApp {
    public static void main(String[] args) {
    	eventMangerInit();
		ExecutorService exec = Executors.newCachedThreadPool(); 		
		// ≥ı ºªØnetty
		exec.execute(new NettyServer(12345));      		
		gameManger.getInstance().noticeInit();	
		System.out.println("start...............");
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
    	loginManager.getInstance();
    	messageManager.getInstance();
    }
}
