package com.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.action.actionCell;
public class commandRunnable implements Runnable{
	
	private Map<String, actionCell>  map = new HashMap<String, actionCell>();

	private boolean  flag = true;
	
	public void addCommand(String cmd, actionCell input) {
		map.put(cmd, input);		
	}
	
	public void excute(String cmd, Object... args) {
		actionCell tmp =  map.get(cmd);
		if(null != tmp) {
			tmp.run(args);
		}else {
			System.out.println("unRegister cmd : " + cmd);
		}
	}
	
	
	@Override
	public void run() {	 	  
		
		while(flag) {
		    try{ //�÷������и�IOExcepiton��Ҫ����   
			    InputStreamReader is = new InputStreamReader(System.in); //new����InputStreamReader����   
			    BufferedReader br = new BufferedReader(is); //�ù���ķ�������BufferedReader�� 
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
