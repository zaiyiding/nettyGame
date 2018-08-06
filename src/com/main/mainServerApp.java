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
				
		exec.execute(nettyServer.getInstance()); 
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

		// init netty				
    	nettyServer.getInstance().setPort(12345);
    	// 
    	messageManager.getInstance();
    	loginManager.getInstance();		
		gameManger.getInstance().noticeInit();		
    }
    
    public void stop() {
    	// 消息包没处理完的会被先丢弃
    	gameManger.getInstance().noticeEnd();
    	Timer testTime = new Timer();
    	exec.shutdown();
    	int nMillSeconds = 3000;
    	testTime.schedule(new TimerTask () {
			@Override
			public void run() {
				flag  = false;
				//exec.shutdownNow();		
				System.out.println("all done");	
				// very improtant
				testTime.cancel();
			}}, nMillSeconds); 
    	System.out.println("showdown in " + nMillSeconds + "Millseconds");
    	
    }
}
