package com.queue;

import com.server.netty.message.message;

public class messageQueue extends baseQueue<message> {
	private static final messageQueue INSTANCE = new messageQueue();
	private messageQueue() {
		
	}
	
	public static messageQueue getInstance() {
		return INSTANCE;
	}
	
	public void putMsg(message input) {
		messageQueue.getInstance().put(input);
	}
	public void clean() {
		messageQueue.getInstance().clean();
	}
}
