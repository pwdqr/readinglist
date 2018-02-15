package com.study.readlinglist;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.study.readlinglist.domain.Reader;

@RunWith(SpringRunner.class) // SpringRunner = SpringJUnit4ClassRunner
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // security() 포함
public class ReadinglistApplicationTests {

//	@Test
//	public void contextLoads() {
//	}
	
	private static final Logger logger = LoggerFactory.getLogger(ReadinglistApplicationTests.class);

//	@Autowired
//	private WebApplicationContext webContext;
	
	@Autowired
	private MockMvc mockMvc;
	
	
//    @Before
//    public void setupMockMvc() {
//    	/*
//    	 * springSecurity()
//    	 * Mock MVC용으로 스프링 시큐리티를 활성화하는 Mock MVC 구성자를 반환한다.
//    	 * MockMvc로 수행하는 모든 요청에 스프링 시큐리티가 적용된다.
//    	 */
//    	mockMvc = MockMvcBuilders.webAppContextSetup(webContext).apply(springSecurity()).build();
//    }

	
    @Test
    public void homePage_unauthenticatedUser() throws Exception {
    	logger.info("unauthenticatedUser");
    	
    	mockMvc.perform(get("/"))
    	.andExpect(status().is3xxRedirection())
    	.andExpect(header().string("Location", "http://localhost/login"));
    }
    
    @Test
    @WithUserDetails("james")
    public void homePage_authenticatedUser() throws Exception {
    	logger.info("authenticatedUser");
    	
    	Reader expectedReader = new Reader();
        expectedReader.setUsername("james");
        expectedReader.setPassword("password");
        expectedReader.setFullname("lb james");
        
    	mockMvc.perform(get("/"))
    	.andExpect(status().isOk())
    	.andExpect(view().name("readingList"))
    	.andExpect(model().attribute("reader", samePropertyValuesAs(expectedReader)))
    	.andExpect(model().attribute("books", hasSize(0)));
//    	.andExpect(model().attribute("amazonId", "habuma-20"));
    }

}
