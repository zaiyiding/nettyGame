package com.server.player;

import com.protobuff.message.messageId;
import com.server.netty.message.message;

import io.netty.channel.Channel;

public class serverPlayer {
	private long id;
	private String name;
	private Channel channel = null;
	private String account;
	public long getId() {
		return id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public void SendMessages(Object input) {
		if(null != channel) {
			channel.writeAndFlush(input);
		}
	}
	
	public void SendMessages(int idInput, byte[] byteInput) {
		message tmpRes = new message(messageId._client_to_server_register_respone, byteInput);		
		SendMessages(tmpRes);
	}
}
