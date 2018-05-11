package com.server.net;
//import org.springframework.stereotype.Component;  

import java.nio.ByteOrder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.message.MessageDecoder;
import com.message.MessageEncoder;
import com.message.MessageLengthFieldFrameDecoder;
import com.message.MessageLengthFieldFrameHandler;
import com.server.handle.MessageHandler;

import io.netty.bootstrap.ServerBootstrap;  
import io.netty.buffer.ByteBuf;  
import io.netty.buffer.Unpooled;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioServerSocketChannel;  
import io.netty.handler.codec.LineBasedFrameDecoder;

  
/** 
 * ChatServer.java 
 *  
 * @author  https://blog.csdn.net/h348592532/article/details/52816148
 * @version 1.0 
 */  
 
public class NettyServer implements Runnable{  
  
    private int port=12345;  
    /*
     *1��LENGTH_FIELD_LENGTHָ�ľ����������CustomMsg��length������ԵĴ�С�����������int�ͣ�������4
      2��LENGTH_FIELD_OFFSETָ�ľ����������length�ֶε���ʼλ�ã���Ϊǰ����type��flag�������ԣ�
                    �����������Զ���byte����������2�ֽڣ�����ƫ������2
      3��LENGTH_ADJUSTMENTָ����length������Ե�ֵ���������ǵ�body������40����ʱ��
                     ��Щ��ϲ����lengthд��44����Ϊlength����ռ��4���ֽڣ���������Ҫ����һ�£�
                     ��ô����Ҫ-4���������û��������������д0�Ϳ�����
     *
     * */
     
    private static final int MAX_FRAME_LENGTH = 1024 * 1024;  
    private static final int LENGTH_FIELD_LENGTH = 4;  
    private static final int LENGTH_FIELD_OFFSET = 0;  
    private static final int LENGTH_ADJUSTMENT = 0;  
    private static final int INITIAL_BYTES_TO_STRIP = 0;  
  
    public NettyServer(int inputPort) {
    	super();
    	this.port = inputPort;
    }
    public void run()  {  
        EventLoopGroup bossGroup = new NioEventLoopGroup();  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
        ByteBuf heapBuffer = Unpooled.buffer(8);  
        heapBuffer.writeBytes("\r".getBytes());  
        try {  
            ServerBootstrap b = new ServerBootstrap(); // (2)  
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)  
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)  
                                @Override  
                                public void initChannel(SocketChannel ch) throws Exception {  
                                    ch.pipeline()//.addLast("encoder", new MessageEncoder())
			                                     //.addLast("decoder", new MessageDecoder())
			                                     //.addFirst(new LineBasedFrameDecoder(65535))
                                    			 //.addLast(new MessageHandler());
                                    			 .addLast(new MessageLengthFieldFrameDecoder(ByteOrder.LITTLE_ENDIAN, MAX_FRAME_LENGTH,LENGTH_FIELD_LENGTH,LENGTH_FIELD_OFFSET,LENGTH_ADJUSTMENT,INITIAL_BYTES_TO_STRIP,false)) 			                                     
                                    			 .addLast(new MessageLengthFieldFrameHandler());
                                }  
                            }).option(ChannelOption.SO_BACKLOG, 1024) // (5)  
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)  
            ChannelFuture f = b.bind(port).sync(); // (7)  
            
            //System.out.println("server ByteOrder" + ByteOrder.nativeOrder()); 
            f.channel().closeFuture().sync();  
            
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {  
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
        }  
    }  
      
    public void start(int port) throws InterruptedException{  
      this.port=port;  
      this.run();  
    }  
    
    public static void main(String[] args) {
    	  
    		ExecutorService exec = Executors.newCachedThreadPool();
   		
    		// ��ʼ��netty
    		System.out.println("server start : 1");
    		exec.execute(new NettyServer(12345));
    		System.out.println("server start : 2");
        
    	
    }
  
}  
