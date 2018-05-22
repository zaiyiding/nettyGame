package com.server.managers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.server.managers.initAndEndObersver;


public class gameManger {
	
	private static final gameManger instance = new gameManger();
	private List<initAndEndObersver> myObervser = new LinkedList<initAndEndObersver>();
	private List<runObersver> myRunObervser = new LinkedList<runObersver>();
	private boolean  runFlag = true; 
	
	private gameManger() {
		
	}
	
	public static gameManger getInstance() {
		return instance;
	}
	
	public void addInitAndEndObserver(initAndEndObersver oInput) {
		if(!myObervser.contains(oInput)) {
			myObervser.add(oInput);
		}			
	}
	
	public void addRunObserver(runObersver oInput) {
		if(!myRunObervser.contains(oInput)) {
			myRunObervser.add(oInput);
		}
	}
	
	public void removeObserver(initAndEndObersver oInput) {
		myObervser.remove(oInput);		
	}
	
	public void noticeInit() {
		Iterator<initAndEndObersver> it = myObervser.iterator();
		while(it.hasNext()) {
			initAndEndObersver o = (initAndEndObersver)it.next();
			o.init();
		}
	}
	
	public void noticeEnd() {
		Iterator<initAndEndObersver> it = myObervser.iterator();
		while(it.hasNext()) {
			initAndEndObersver o = (initAndEndObersver)it.next();
			o.end();
		}
	}
	
	public void run() {		
		if(!runFlag) {
			return;
		}
		
		Iterator<runObersver> it = myRunObervser.iterator();
		while(it.hasNext()) {
			runObersver o = (runObersver)it.next();
			o.run();
		
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 如果已经取完则让给其他线程一些时间片
		
	}
}
