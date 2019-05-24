package com.server.net;
//import org.springframework.stereotype.Component;  

import java.nio.ByteOrder;

import com.server.managers.gameManger;
import com.server.managers.initAndEndObersver;
import com.server.netty.message.messageConnectEvent;
import com.server.netty.message.messageEncoder;
import com.server.netty.message.messageLengthFieldFrameDecoder;
import com.server.netty.message.messageLengthFieldFrameHandler;

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

  
/** 
 * ChatServer.java 
 *  
 * @author  https://blog.csdn.net/h348592532/article/details/52816148
 * @version 1.0 
 */  
 
public class nettyServer implements Runnable, initAndEndObersver{  
  
    private int port=12345;  
    private volatile boolean stop = false;
    static private nettyServer instance = null; 
    EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    /*
     *1）LENGTH_FIELD_LENGTH指的就是我们这边CustomMsg中length这个属性的大小，我们这边是int型，所以是4
      2）LENGTH_FIELD_OFFSET指的就是我们这边length字段的起始位置，因为前面有type和flag两个属性，
                    且这两个属性都是byte，两个就是2字节，所以偏移量是2
      3）LENGTH_ADJUSTMENT指的是length这个属性的值，假如我们的body长度是40，有时候，
                     有些人喜欢将length写成44，因为length本身还占有4个字节，这样就需要调整一下，
                     那么就需要-4，我们这边没有这样做，所以写0就可以了
     *
     * */
     
    private static final int MAX_FRAME_LENGTH = 1024 * 1024;  
    private static final int LENGTH_FIELD_LENGTH = 4;  
    private static final int LENGTH_FIELD_OFFSET = 0;  
    private static final int LENGTH_ADJUSTMENT = 0;  
    private static final int INITIAL_BYTES_TO_STRIP = 0;  
  
    private nettyServer() {
    	gameManger.getInstance().addInitAndEndObserver(this);
    		
    }
    
    public void setPort(int inputPort) {
    	this.port = inputPort;
    }
    
    /*static public nettyServer getInstance() {
    	if(instance == null){
			synchronized (nettyServer.class) {
				if (instance == null) {
					instance = new nettyServer();
					instance.init();

				}
			}
    	}
    	return instance;
    }*/
    
    static  synchronized public nettyServer getInstance() {
    	if(instance == null){
			instance = new nettyServer();
			instance.init();
			}	
			return instance;
    }
    
    public void run()  {  
        bossGroup = new NioEventLoopGroup();  
        workerGroup = new NioEventLoopGroup();  
        //ByteBuf heapBuffer = Unpooled.buffer(8);  
        //heapBuffer.writeBytes("\r".getBytes());  
        try {  
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) 
                    .childHandler(new ChannelInitializer<SocketChannel>() {  
                                @Override  
                                public void initChannel(SocketChannel ch) throws Exception {  
                                    ch.pipeline().addFirst(new messageConnectEvent("server"))
                                    			 .addLast(new messageLengthFieldFrameDecoder(ByteOrder.LITTLE_ENDIAN, MAX_FRAME_LENGTH,LENGTH_FIELD_LENGTH,LENGTH_FIELD_OFFSET,LENGTH_ADJUSTMENT,INITIAL_BYTES_TO_STRIP,false)) 			                                     
                                    			 .addLast(new messageLengthFieldFrameHandler())
                                    			 .addLast(new messageEncoder());
                                    
                                }  
                            }).option(ChannelOption.SO_BACKLOG, 1024) 
                    .childOption(ChannelOption.SO_KEEPALIVE, true); 
            ChannelFuture f = b.bind(port).sync(); 
            
            //System.out.println("server ByteOrder" + ByteOrder.nativeOrder()); 
            f.channel().closeFuture().sync();  
            
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {  
			//System.out.println("Server shutdownGracefully:\t");
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
        }  
        
        if(stop) {      	
        	System.out.println("stop = true");
        	//workerGroup.shutdownGracefully();  
            //bossGroup.shutdownGracefully();  
        }
        
    }  
      
    public void start(int port) throws InterruptedException{  
      this.port=port;  
      this.run();  
    }  
    
    public synchronized void stop(){
    	stop = true;
    	workerGroup.shutdownGracefully();  
        bossGroup.shutdownGracefully();   
        System.out.println("Server shutdownGracefully:\t");
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		stop();		
	}
  
}  
