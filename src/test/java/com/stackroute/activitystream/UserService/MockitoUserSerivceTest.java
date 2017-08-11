package com.stackroute.activitystream.UserService;

import static org.junit.Assert.*;

import org.hibernate.jpa.criteria.expression.SearchedCaseExpression.WhenClause;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stackroute.activitystream.UserService.dao.UserDao;
import com.stackroute.activitystream.UserService.model.User;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=UserServiceApplication.class)
@WebAppConfiguration
public class MockitoUserSerivceTest {
	 @Autowired
	    private WebApplicationContext wac;
	    private MockMvc mockMvc;
	    @Before
	    public void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	    }
	    @Test
	    public void getAllUserTest() throws Exception {
	    	this.mockMvc.perform(get("http://localhost:9000/alluser"))
	         .andExpect(status().isOk())
	         .andExpect(content().contentType("application/json;charset=UTF-8"));
	    	}
	    @Test
	    public void signUp() throws Exception{
	    	User user=new User();
	    	user.setUserName("mary selvi");
	    	user.setEmailId("mary@gmail.com");
	    	user.setUserPass("sweta@123");
	    	user.setUserAddress("sweta street mockmvc");
	    	
	    	this.mockMvc.perform(post("http://localhost:9000/signup")
	    	        .contentType(MediaType.APPLICATION_JSON).content(javaToJson(user)))
	    			.andExpect(status().isCreated());
	    			
	    }
	    @Test
	    public void signIn() throws Exception{
	    	User user=new User();
	    	user.setEmailId("jk@gmail.com");
	    	user.setUserPass("jk123");
	    	RequestBuilder requestBuilder = post("/signin")
	                .param("username", user.getEmailId())
	                .param("password", user.getUserPass());
	        mockMvc.perform(requestBuilder)
	                .andExpect(status().isOk())
	                .andExpect(cookie().exists("JSESSIONID"));	    	    	
	    }
	    
	   /* @Test
	    public void testUserLogin() throws Exception {
	        RequestBuilder requestBuilder = post("/login")
	                .param("username", testUser.getUsername())
	                .param("password", testUser.getPassword());
	        mockMvc.perform(requestBuilder)
	                .andDo(print())
	                .andExpect(status().isOk())
	                .andExpect(cookie().exists("JSESSIONID"));
	    }*/
	    
	    
	    // json convertion method
	    private String javaToJson(User user){
	    	Gson gson = new Gson();
	        String json = gson.toJson(user);
	        return json;
	    }
}


