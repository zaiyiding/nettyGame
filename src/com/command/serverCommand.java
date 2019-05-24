package com.command;

import com.action.actionCell;
import com.main.mainServerApp;
import com.server.managers.gameManger;
import com.server.managers.initAndEndObersver;
import com.server.net.nettyServer;

public class serverCommand extends commandRunnable implements initAndEndObersver {
	private static serverCommand instance = null;

	/*static public serverCommand Instance() {
		if(instance == null){
			synchronized (serverCommand.class) {
				if (instance == null) {
					instance = new serverCommand();
					instance.init();

				}
			}
    	}
		return instance;
	}*/
	static synchronized public serverCommand Instance() {
		if(instance == null){{
			instance = new serverCommand();
			instance.init();
			}
    	}
		return instance;
	}
	
	
	private serverCommand() {
		init();
		gameManger.getInstance().addInitAndEndObserver(this);
	}
	
	@Override
	public void init() {
		addCommand("exit", (new actionCell(){    		    
			@Override
			public Object run(Object... args) {
				mainServerApp.Instance().stop();
				return null;
			}
		})); 
		
		/*addCommand("test1", (new actionCell(){    		    
			@Override
			public Object run(Object... args) {
				int a = 0;
				a += 100;				
				return a;
			}
		})); */
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		this.stop();		
	}
	
	public static void main(String[] args) throws Exception {
		int a = (int)serverCommand.Instance().excute("test1", 1,1,1);    	
    	System.out.println("test1 : " + a);
    }

}
