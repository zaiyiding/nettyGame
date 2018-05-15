package com.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ������Ϣ���еĻ���,������ڼ̳�
 * */
public class BaseQueue<T> {

	/** ��Ϣ���� */
	private final BlockingQueue<T> queue = new LinkedBlockingQueue<T>();

	/**
	 * ������,���̷���;û��
	 * */
	public T take() {
		return queue.poll();
	}

	/**
	 * ����,��Ϣ���ܶ���
	 * */
	public void put(T t) {
		try {
			queue.put(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getQueueSize() {
		return queue.size();
	}

}
