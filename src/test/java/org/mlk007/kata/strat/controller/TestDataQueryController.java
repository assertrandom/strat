package org.mlk007.kata.strat.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/test-servlet-context.xml")
public class TestDataQueryController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataQueryController.class);

    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private DataQueryController dataQueryController;
	
    private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReturnValue() throws Exception {
			ResultActions result = mockMvc.perform(get("/pageinfo?page=work&size=12&zoomfactor=121"));
			result.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//			.andExpect(content().string("121"));
			LOGGER.debug("content().toString() {}", content().toString());
	}

}
