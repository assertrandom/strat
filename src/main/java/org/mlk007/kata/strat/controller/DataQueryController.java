package org.mlk007.kata.strat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.mlk007.kata.strat.service.DataService;
import org.mlk007.kata.strat.service.WordReplacementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
public class DataQueryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataQueryController.class);

	@Autowired
	DataService redisDataService;

	@Autowired
	WordReplacementService wordReplacementService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.debug("Root context is accessed from {} ", request.getRemoteAddr());
		return "Root context is accessed from "+ request.getRemoteAddr();
	}

	@RequestMapping(value = "/pageinfo", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String getPageInfo(@RequestParam(value = "page") String page, @RequestParam(value = "size") String size,
			@RequestParam(value = "zoomfactor") String zoomfactor) {

		String dataFromStore = redisDataService.getDataFromStore(page, size, zoomfactor);
		if (StringUtils.isEmpty(dataFromStore))
			return "No matching entry for the page " + page + " size " + size + " zoomfactor " + zoomfactor;
		return wordReplacementService.processAndReplaceWords(dataFromStore);

	}

}
