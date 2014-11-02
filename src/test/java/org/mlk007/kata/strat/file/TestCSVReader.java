package org.mlk007.kata.strat.file;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCSVReader {
	
	CSVReader csvReader;

	@Before
	public void setUp() throws Exception {
		csvReader = new CSVReader();
	}


	@Test
	public void testForValidDuration() {

		assertTrue(csvReader.getDurationInSeconds("11 : 22 ,") > 0);;
		assertTrue(csvReader.getDurationInSeconds("10 :22,") > 0);;
		assertTrue(csvReader.getDurationInSeconds("10 :22,\n") > 0);;

		assertTrue(csvReader.getDurationInSeconds("IV :22,\n") == -1);
		assertTrue(csvReader.getDurationInSeconds("11 :b2,\n") == -1);
	}
	
	@Test
	public void testStringToSecondsConversion() throws Exception {
		assertEquals(csvReader.getDurationInSeconds("01: 01 ,"), 61);
		assertEquals(csvReader.getDurationInSeconds("10: 01 ,"), 601);
		assertEquals(csvReader.getDurationInSeconds("0: 01 ,"), 1);
		assertEquals(csvReader.getDurationInSeconds("10: 59 ,"), 659);
		
	}

}
