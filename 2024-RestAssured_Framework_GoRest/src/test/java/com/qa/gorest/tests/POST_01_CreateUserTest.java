package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class POST_01_CreateUserTest extends BaseTest {

	@Test
	public void createUserTest() {
		User user = new User("Raj", StringUtils.getRandomEmailId(), "male", "active");
		
		//1.POST
		RestClient restClient1 = new RestClient(prop, baseURI);
		
		Integer userId = restClient1.post("/public/v2/users/","json",user,true, true)
		.then().log().all()
		.assertThat().statusCode(201)
		.extract()
		.path("id");
		System.out.println(userId);
		
		//2.GET
		RestClient restClient2 = new RestClient(prop, baseURI);
		restClient2.get("/public/v2/users/"+userId,true, true)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.body("id", equalTo(userId));
		
	}
	
}
