package com.client.net;

import java.nio.ByteOrder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.command.clientCommand;
import com.protobuff.message.clientToServer.client_to_server_register;
import com.server.netty.message.message;
import com.server.netty.message.messageConnectEvent;
import com.server.netty.message.messageEncoder;
import com.server.netty.message.messageLengthFieldFrameDecoder;
import com.server.netty.message.messageLengthFieldFrameHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class nettyClient implements Runnable {
	private Channel sendchannel = null;
	private final static String host = "localhost";
	private final static int port = 12345;
	
	private static final int MAX_FRAME_LENGTH = 1024 * 1024;  
    private static final int LENGTH_FIELD_LENGTH = 4;  
    private static final int LENGTH_FIELD_OFFSET = 0;  
    private static final int LENGTH_ADJUSTMENT = 0;  
    private static final int INITIAL_BYTES_TO_STRIP = 0; 
    
    private static nettyClient instance = new nettyClient();
    
    static public synchronized nettyClient  Instance() {
    	return instance;
    }
    
    private nettyClient() {
    	
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
                                    ch.pipeline().addFirst(new messageConnectEvent("client"))
                                    			 .addLast(new messageLengthFieldFrameDecoder(ByteOrder.LITTLE_ENDIAN, MAX_FRAME_LENGTH,LENGTH_FIELD_LENGTH,LENGTH_FIELD_OFFSET,LENGTH_ADJUSTMENT,INITIAL_BYTES_TO_STRIP,false)) 			                                     
                                    			 .addLast(new messageLengthFieldFrameHandler())
                                    			 .addLast(new messageEncoder());
                                }  
								
							}) .option(ChannelOption.SO_KEEPALIVE, true); ;

				// Start the client.
				ChannelFuture f = b.connect(host, port).sync();
				System.out.println("clinet run11111111111 ");	
				sendchannel = f.channel();
				if (f.isDone()) {
					System.out.println("���ӳɹ�");				
					//test :
					client_to_server_register tmpMessage = client_to_server_register
							.newBuilder().setAccount("test1").setPassword("passwd1").build();
					
					message test1 = new message(1, tmpMessage.toByteArray());					
					sendMsg(test1);
				}

			} catch (InterruptedException e) {
				// connect fail 
				e.printStackTrace();
			} finally {			
				//System.out.println("Client shutdownGracefully:\t");
				//group.shutdownGracefully();
			}
	}
	
	
	public void sendMsg(Object msgEntity) {
		sendchannel.writeAndFlush(msgEntity);
		System.out.println("Client send over:\t" + msgEntity.toString());
	}
	
	 /*public static void main(String[] args) {
   	  
 		ExecutorService exec = Executors.newCachedThreadPool(); 		
 		// ��ʼ��netty
 		//NettyClient test1 = new NettyClient();
 		exec.execute(NettyClient.Instance());			
 		exec.execute(clientCommand.Instance());	
	 }*/
	
}

			
			
	

