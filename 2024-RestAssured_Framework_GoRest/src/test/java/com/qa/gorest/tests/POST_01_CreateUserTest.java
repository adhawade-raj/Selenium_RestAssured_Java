package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class POST_01_CreateUserTest {

	
User user = new User("Raj", StringUtils.getRandomEmailId(), "male", "active");
	
	
	RestClient restClient;
	@Test
	public void createUserTest() {
		restClient = new RestClient();
		
		Integer userId = restClient.post("/public/v2/users/","json",user, true)
		.then().log().all()
		.assertThat().statusCode(201)
		.extract()
		.path("id");
		System.out.println(userId);
		
		restClient.get("/public/v2/users/"+userId, true)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.body("id", equalTo(userId));
		
		
		
	}
	
}
