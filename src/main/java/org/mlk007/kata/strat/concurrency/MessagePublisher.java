package org.mlk007.kata.strat.concurrency;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagePublisher implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessagePublisher.class);
	
	Random random = new Random();

	private String messageName;
	private MessageQueue messageQueue;

	public MessagePublisher(String messageName, MessageQueue messageQueue) {
		this.messageName = messageName;
		this.messageQueue = messageQueue;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 50; i++) {
				messageQueue.store(messageName);
				Thread.sleep(10 * random.nextInt(10));

			}
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException  ", e);
		}
	}

}
