package com.command;

import com.action.ActionCell;
import com.main.mainServerApp;

public class serverCommand extends commandRunnable {
	private static serverCommand instance = new serverCommand();

	static public serverCommand Instance() {
		return instance;
	}
	
	private serverCommand() {
		init();
	}
	
	void init() {
		addCommand("exit", (new ActionCell(){    		    
			@Override
			public Object run(Object... args) {
				mainServerApp.Instance().stop();
				return null;
			}
		})); 
	}
}
