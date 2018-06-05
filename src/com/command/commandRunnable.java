package com.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.action.ActionCell;
public class commandRunnable implements Runnable{
	
	private Map<String, ActionCell>  map = new HashMap<String, ActionCell>();

	private boolean  flag = true;
	
	public void addCommand(String cmd, ActionCell input) {
		map.put(cmd, input);		
	}
	
	public void excute(String cmd, Object... args) {
		ActionCell tmp =  map.get(cmd);
		if(null != tmp) {
			tmp.run(args);
		}else {
			System.out.println("unRegister cmd : " + cmd);
		}
	}
	
	
	@Override
	public void run() {	 	  
		
		while(flag) {
		    try{ //该方法中有个IOExcepiton需要捕获   
			    InputStreamReader is = new InputStreamReader(System.in); //new构造InputStreamReader对象   
			    BufferedReader br = new BufferedReader(is); //拿构造的方法传到BufferedReader中 
			    String name = br.readLine();   
			    //System.out.println("ReadTest Output:" + name);
			    this.excute(name);
			    }   
			    catch(IOException e){   
			      e.printStackTrace();   
			    }  		
		    try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		flag = false;
	}
	
	
}
