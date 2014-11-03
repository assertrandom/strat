package org.mlk007.kata.strat.concurrency;

public class QueueExecutor {

	public static void main(String[] args) {
		MessageQueue messageQueue = new MessageQueue(10);
		
		MessagePublisher publisher = new MessagePublisher("Publish Messge", messageQueue);
		
		MessageConsumer consumer = new MessageConsumer(messageQueue);
	}

}
