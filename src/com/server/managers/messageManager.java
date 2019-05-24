package com.server.managers;

import java.util.concurrent.TimeUnit;

import com.action.actionCell;
import com.action.actionMapUtil;
import com.queue.messageQueue;
import com.server.netty.message.message;
import com.server.player.serverPlayer;
//import com.sun.corba.se.pept.protocol.MessageMediator;

public class messageManager implements initAndEndObersver, runObersver
{
	private static final messageManager instance = new messageManager();
	private actionMapUtil myMap = new actionMapUtil();
	private messageManager() {
		gameManger.getInstance().addInitAndEndObserver(this);
		gameManger.getInstance().addRunObserver(this);
	}
	
	public static messageManager getInstance() {
		return instance;
	}
	
	public void putAction(Integer key, actionCell action) {
		myMap.putAction(key, action); 
    }
	
	public void invokeMap(message inputMsg)  {
		try {
			serverPlayer tmpPlayer = serverPlayerManager.Instance().getPlayerByChannel(inputMsg.getChannel());
			if(null == tmpPlayer) {
				System.out.println("messageManager invokeMap inputMsg.getChannel() = " + inputMsg.getChannel()); 
				return;
			}
			myMap.invoteWithStatic(inputMsg.getid(), inputMsg, tmpPlayer);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	void runStep() {
		// 一次处理30个消息包
		final int max_count = 30;
		int count = 0;
		while (count < max_count && messageQueue.getInstance().getQueueSize() > 0){			
			message msg = messageQueue.getInstance().take();
			invokeMap(msg);
			++count;
		}
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 如果已经取完则让给其他线程一些时间片
			
	}
	
	
	public static void main(String[] args) {
		
	
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		messageQueue.getInstance().cleanMsg();		
	}

	@Override
	public void run() {
		runStep();		
	}
	
	
	
}
