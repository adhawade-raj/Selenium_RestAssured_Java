package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;

public class GET_01_AllUserTest {

	RestClient restClient;
	@Test
	public void getAlluserTest() {
		restClient = new RestClient();
		
		restClient.get("/public/v2/users", true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
}
