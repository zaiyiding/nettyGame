package com.queue;

import com.server.netty.message.Message;

public class MessageQueue extends BaseQueue<Message> {
	private static final MessageQueue INSTANCE = new MessageQueue();
	private MessageQueue() {
		
	}
	
	public static MessageQueue getInstance() {
		return INSTANCE;
	}
	
	public void putMsg(Message input) {
		MessageQueue.getInstance().put(input);
	}
}
