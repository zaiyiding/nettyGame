package com.main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.command.serverCommand;
import com.server.managers.gameManger;
import com.server.managers.loginManager;
import com.server.managers.messageManager;
import com.server.net.nettyServer;

public class mainServerApp {
	private static mainServerApp instance = new mainServerApp();
	private volatile static boolean flag = true;
	static ExecutorService exec = Executors.newCachedThreadPool(); 		
	public static mainServerApp Instance() {
		return instance;
	}
	
	
    public static void main(String[] args) {
    	mainServerApp.Instance().eventMangerInit();
		
		// ≥ı ºªØnetty			     		
		gameManger.getInstance().noticeInit();					
		nettyServer.Instance().setPort(12345);
		
		
		exec.execute(nettyServer.Instance()); 
		exec.execute(serverCommand.Instance()); 
		System.out.println("server start...............");
		while(flag) {			
			try {
				gameManger.getInstance().run();
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		System.out.println("end");
    }
    
    public void eventMangerInit() {
    	loginManager.getInstance();
    	messageManager.getInstance();
    }
    
    public void stop() {
    	gameManger.getInstance().noticeEnd();
    	nettyServer.Instance().stop();
    	serverCommand.Instance().stop();
    	Timer testTime = new Timer();
    	exec.shutdown();
    		
    	testTime.schedule(new TimerTask () {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				flag  = false;
				//exec.shutdownNow();		
				System.out.println("all done");		
				testTime.cancel();
			}}, 3000);   
    	
    }
}
