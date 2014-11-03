package org.mlk007.kata.strat.concurrency;

public class QueueExecutor {

	public static void main(String[] args) {
		MessageQueue messageQueue = new MessageQueue(10);
		
		MessagePublisher publisher = new MessagePublisher("Publish Message", messageQueue);
		
		MessageConsumer consumer = new MessageConsumer(messageQueue);
		
		Thread pubThread = new Thread(publisher);
		
		Thread consumerThread = new Thread(consumer);
		
		pubThread.start();
		
		consumerThread.start();
	}

}
