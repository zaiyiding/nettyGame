package com.client.net;

import java.nio.ByteOrder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.command.clientCommand;
import com.protobuff.message.ClientToServer.client_to_server_register;
import com.server.netty.message.Message;
import com.server.netty.message.MessageConnectEvent;
import com.server.netty.message.MessageEncoder;
import com.server.netty.message.MessageLengthFieldFrameDecoder;
import com.server.netty.message.MessageLengthFieldFrameHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient implements Runnable {
	private Channel sendchannel = null;
	private final static String host = "localhost";
	private final static int port = 12345;
	
	private static final int MAX_FRAME_LENGTH = 1024 * 1024;  
    private static final int LENGTH_FIELD_LENGTH = 4;  
    private static final int LENGTH_FIELD_OFFSET = 0;  
    private static final int LENGTH_ADJUSTMENT = 0;  
    private static final int INITIAL_BYTES_TO_STRIP = 0; 
    
    private static NettyClient instance = new NettyClient();
    
    static public synchronized NettyClient  Instance() {
    	return instance;
    }
    
    private NettyClient() {
    	
    }
    
	public void run() {
			EventLoopGroup group = new NioEventLoopGroup();
			try {

				Bootstrap b = new Bootstrap();
				b.group(group)
							.channel(NioSocketChannel.class)
							.option(ChannelOption.TCP_NODELAY, true)
							.handler(new ChannelInitializer<SocketChannel>() {
								public void initChannel(SocketChannel ch) throws Exception {  
                                    ch.pipeline().addFirst(new MessageConnectEvent("client"))
                                    			 .addLast(new MessageLengthFieldFrameDecoder(ByteOrder.LITTLE_ENDIAN, MAX_FRAME_LENGTH,LENGTH_FIELD_LENGTH,LENGTH_FIELD_OFFSET,LENGTH_ADJUSTMENT,INITIAL_BYTES_TO_STRIP,false)) 			                                     
                                    			 .addLast(new MessageLengthFieldFrameHandler())
                                    			 .addLast(new MessageEncoder());
                                }  
								
							}) .option(ChannelOption.SO_KEEPALIVE, true); ;// 这里为了简便直接使用内部类

				// Start the client.
				ChannelFuture f = b.connect(host, port).sync();
				sendchannel = f.channel();
				if (f.isDone()) {
					System.out.println("连接成功");				
					//test :
					client_to_server_register tmpMessage = client_to_server_register
							.newBuilder().setAccount("test1").setPassword("passwd1").build();
					
					Message test1 = new Message(1, tmpMessage.toByteArray());					
					sendMsg(test1);
				}

			} catch (InterruptedException e) {// 链接失败
				e.printStackTrace();
			} finally {			
				//System.out.println("Client shutdownGracefully:\t");
				//group.shutdownGracefully();
			}
	}
	
	
	public void sendMsg(Object msgEntity) {
		sendchannel.writeAndFlush(msgEntity);
		System.out.println("Client 发送数据成功,命令码:\t" + msgEntity.toString());
	}
	
	 /*public static void main(String[] args) {
   	  
 		ExecutorService exec = Executors.newCachedThreadPool(); 		
 		// 初始化netty
 		//NettyClient test1 = new NettyClient();
 		exec.execute(NettyClient.Instance());			
 		exec.execute(clientCommand.Instance());	
	 }*/
	
}

			
			
	

