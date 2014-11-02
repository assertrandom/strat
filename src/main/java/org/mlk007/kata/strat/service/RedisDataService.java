package org.mlk007.kata.strat.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class RedisDataService implements DataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisDataService.class);

	private Jedis jedis;

	@Value("${redis.host}")
	private String redisHost;

	@Override
	public String getDataFromStore(String pageNum, String size, String zoomFactor) {
		return jedis.get("page-" + pageNum);
	}

	@PostConstruct
	public void initiaiteRedisConnection() {
		LOGGER.debug("Initiate connection to Redis Post Construction {}", redisHost);
		jedis = new Jedis(redisHost);
		populateTestData();
	}

	private void populateTestData() {
		jedis.set("page-01", "Home Page");
		jedis.set("page-02", "Paid with dollar");
		jedis.set("page-03", "That is for Mr President");
		jedis.set("page-04", "That car is a sedan");
		jedis.set("page-05", "Incredible journey of voyager");

	}

	@PreDestroy
	public void closeRedisConnection() {
		LOGGER.debug("Close connection to Redis ");
		jedis.close();
	}

}
