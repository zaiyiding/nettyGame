package com.server.managers;

import com.server.player.serverPlayer;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.TObjectLongMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.map.hash.TObjectLongHashMap;
import io.netty.channel.Channel;

public class serverPlayerManager {
	public final static TObjectLongMap<Channel> channelId = new TObjectLongHashMap<Channel>();
	public final static TLongObjectMap<serverPlayer> playersMap = new TLongObjectHashMap<serverPlayer>();
	
	private serverPlayerManager() {
		
	}
	
	private final static serverPlayerManager instance = new serverPlayerManager();
	
	public static serverPlayerManager Instance() {
		return instance;
	}
	
	public void  addPlayer(serverPlayer playerInput) {
		playersMap.put(playerInput.getId(), playerInput);
		channelId.put(playerInput.getChannel(), playerInput.getId());
	}
	
	public void removePlayer(long input) {
		serverPlayer player = getPlayerByid(input);
		playersMap.remove(input);
		if(null != player) {
			channelId.remove(player.getId());
		}	
	}
	
	public serverPlayer getPlayerByid(long input) {
		return playersMap.get(input);		
	}
	
	boolean isExistPlayer(long Id) {
		return playersMap.containsKey(Id);
	}
	
	public serverPlayer getPlayerByChannel(Channel chInput) {
		if(channelId.containsKey(chInput)) {
			return getPlayerByid(channelId.get(chInput));
		}
		return null;
	}
	
	public void OnPlayerOffline(Channel input) {
		serverPlayer tmpPlayer = serverPlayerManager.Instance().getPlayerByChannel(input);
		if (null == tmpPlayer) {
			return;
		}
		
		serverPlayerManager.Instance().removePlayer(tmpPlayer.getId());
		System.out.println("player " + tmpPlayer.getAccount() + "off line");
		// todo: Êý¾Ý¿â´æ´¢
	}
	
	
	
}
