package org.mlk007.kata.strat.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueueExecutor {

	public static void main(String[] args) {
		MessageQueue messageQueue = new MessageQueue(10);

		MessagePublisher publisher = new MessagePublisher("Publish Message", messageQueue);

		MessageConsumer consumer = new MessageConsumer(messageQueue);

		ExecutorService publisherService = Executors.newFixedThreadPool(20);

		ExecutorService consumerService = Executors.newFixedThreadPool(20);

		for (int i = 0; i < 40; i++) {
			Thread pubThread = new Thread(publisher);
			publisherService.submit(pubThread);
		}

		for (int i = 0; i < 40; i++) {
			Thread consumerThread = new Thread(consumer);
			consumerService.submit(consumerThread);
		}

		publisherService.shutdown();
		consumerService.shutdown();

	}

}
