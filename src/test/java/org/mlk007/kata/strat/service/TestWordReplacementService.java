package org.mlk007.kata.strat.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/test-servlet-context.xml")
public class TestWordReplacementService {

	@Autowired
	WordReplacementService wordReplacementService;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWordreplacementServicePostConstruct() {
		assertNotNull(wordReplacementService.getReplacementWordsArray());
		assertEquals(wordReplacementService.getReplacementWordsArray().length,
				wordReplacementService.getBlockedWordsArray().length);
	}

	@Test
	public void testWordReplacement() throws Exception {
		assertEquals(wordReplacementService.processAndReplaceWords("dollar"), "pound");
		assertEquals(wordReplacementService.processAndReplaceWords("simple"), "simple");
		assertEquals(wordReplacementService.processAndReplaceWords("sedan"), "saloon");

	}

}
