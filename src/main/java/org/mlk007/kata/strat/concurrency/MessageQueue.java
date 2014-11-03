package org.mlk007.kata.strat.concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {
	
	Queue<String> messageQueue = new ConcurrentLinkedQueue<String>();
	
	private int maxSize;

	public MessageQueue(int maxSize) {
		this.maxSize = maxSize;
	}
	
	public synchronized String getAsString() throws InterruptedException {
		if (messageQueue.size() == 0) {
			wait();
		}
		
		return messageQueue.poll();
	}
	
	public synchronized void store(String input) throws InterruptedException {
		if (messageQueue.size() == maxSize ) {
			wait();
		}
		
		messageQueue.add(input);
		
		
	}

}
