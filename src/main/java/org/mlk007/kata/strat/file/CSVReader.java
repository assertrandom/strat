package org.mlk007.kata.strat.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.StringTokenizer;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CSVReader {
	
	//Assumptions 
	//Duration XX: XX  is taken as minutes : seconds 

	private static final Logger LOGGER = LoggerFactory.getLogger(CSVReader.class);

	public static void main(String[] args) {

		CSVReader csvReader = new CSVReader();
		try {
			long totalSeconds = csvReader.getTotalSecondsinFile("download.csv");
			printTotalDuration(totalSeconds);
			totalSeconds = csvReader.getTotalSecondsinFile("download01.csv");
			printTotalDuration(totalSeconds);
		} catch (URISyntaxException e) {
			LOGGER.error("Exception ", e);
		} catch (FileNotFoundException e) {
			LOGGER.error("Exception ", e);
		} catch (IOException e) {
			LOGGER.error("Exception ", e);
		}

	}

	private static void printTotalDuration(long totalSeconds) {
		Duration duration = Duration.ofSeconds(totalSeconds);
		long minutes = duration.toMinutes();
		LOGGER.debug("Total time is " + minutes + " minutes & " + duration.minusMinutes(minutes).getSeconds()
				+ " seconds");
	}

	private long getTotalSecondsinFile(String fileName) throws URISyntaxException, FileNotFoundException, IOException {
		Resource resource = new ClassPathResource(fileName);
		FileInputStream fis = new FileInputStream(resource.getFile());
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		long totalSeconds = 0;

		String line = null;
		int counter = 1;
		while ((line = br.readLine()) != null) {
			if (counter > 5 && ((counter % 4) == 0)) {
				long durationInSeconds = getDurationInSeconds(line);
				if (durationInSeconds != -1) {
					totalSeconds += durationInSeconds;
				}
			}
			counter++;
		}
		br.close();
		return totalSeconds;
	}

	// TODO - Add logic to check if minutes & hours is positive and less than 60
	public long getDurationInSeconds(String durationStr) {
		String duration = durationStr.substring(0, durationStr.lastIndexOf(","));
		StringTokenizer strTokens = new StringTokenizer(duration, ":");
		if (strTokens.countTokens() < 2)
			return -1;
		String minutesStr = strTokens.nextToken().trim();
		String secondsStr = strTokens.nextToken().trim();
		if (!NumberUtils.isNumber(minutesStr) || !NumberUtils.isNumber(secondsStr))
			return -1;
		Duration durationVal = Duration.ofSeconds(Integer.parseInt(secondsStr)).plusMinutes(
				Integer.parseInt(minutesStr));
		return durationVal.getSeconds();
	}

}
