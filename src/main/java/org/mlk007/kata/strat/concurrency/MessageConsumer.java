package org.mlk007.kata.strat.concurrency;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageConsumer implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);
	private MessageQueue messageQueue;

	Random random = new Random();
	
	public MessageConsumer(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 50; i++) {
				messageQueue.getAsString();
				Thread.sleep(10 * random.nextInt(10));
//				Thread.sleep(5);
			}
		} catch (InterruptedException e) {
			LOGGER.error("Exception ", e);
		}
	}

}
