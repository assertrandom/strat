package org.mlk007.kata.strat.dao;

public class BlackListWord {

	private String blacklistWord;
	private String replacementWord;

	public BlackListWord(String blacklistWord, String replacementWord) {
		this.blacklistWord = blacklistWord;
		this.replacementWord = replacementWord;
	}

	public String getReplacementWord() {
		return replacementWord;
	}

	public String getBlacklistWord() {
		return blacklistWord;
	}


}
