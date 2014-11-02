package org.mlk007.kata.strat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.mlk007.kata.strat.dao.BlackListWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordReplacementService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WordReplacementService.class);

	private List<BlackListWord> blacklistWordList = new ArrayList<BlackListWord>();

	private String[] blockedWordsArray;
	private String[] replacementWordsArray;
	
	@Autowired
    Properties blockedwords;

	@PostConstruct
	private void populateReplacementWord() {
		LOGGER.debug("blockedwords.size()  {}", blockedwords.size());
		for (Entry<Object, Object> entry: blockedwords.entrySet()) {
			LOGGER.debug("entry.getKey()  {} {} ", entry.getKey()  ,entry.getValue());
			blacklistWordList.add(new BlackListWord((String)entry.getKey(), (String)entry.getValue()));
		}
		populateReplacementWords(blacklistWordList);
	}

	public String processAndReplaceWords(String dataFromStore) {
		LOGGER.debug("Replacing Words for {}", dataFromStore);
		return StringUtils.replaceEach(dataFromStore, blockedWordsArray, replacementWordsArray);
	}

	public void populateReplacementWords(List<BlackListWord> blacklistWordList) {

		List<String> blacklistWords = new ArrayList<String>();
		List<String> replacementWords = new ArrayList<String>();
		for (BlackListWord blackListWord : blacklistWordList) {
			if (StringUtils.isNotEmpty(blackListWord.getBlacklistWord())) {
				blacklistWords.add(blackListWord.getBlacklistWord());
				replacementWords.add(blackListWord.getReplacementWord());
			}
		}
		blockedWordsArray = blacklistWords.toArray(new String[blacklistWords.size()]);
		replacementWordsArray = replacementWords.toArray(new String[replacementWords.size()]);
	}

	public String[] getBlockedWordsArray() {
		return blockedWordsArray;
	}

	public String[] getReplacementWordsArray() {
		return replacementWordsArray;
	}
}
