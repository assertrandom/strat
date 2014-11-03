package org.mlk007.kata.strat.concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageQueue {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageQueue.class);
	
	Queue<String> messageQueue = new ConcurrentLinkedQueue<String>();
	
	private int maxSize;

	public MessageQueue(int maxSize) {
		this.maxSize = maxSize;
	}
	
	public synchronized String getAsString() throws InterruptedException {
		if (messageQueue.size() == 0) {
			LOGGER.debug(" {} Waiting for Queue population", Thread.currentThread().getName());
			wait();
		}
		LOGGER.debug( " {}  Returning String {}", Thread.currentThread().getName(), messageQueue.size());
		
		notifyAll();
		
		return messageQueue.poll();
	}
	
	public synchronized void store(String input) throws InterruptedException {
		if (messageQueue.size() == maxSize ) {
			LOGGER.debug(" {} Max size reached ",  Thread.currentThread().getName());
			wait();
		}
		notifyAll();
		LOGGER.debug( "  {} Populating String {}" , Thread.currentThread().getName(), messageQueue.size());
		messageQueue.add(input);
		
		
	}

}