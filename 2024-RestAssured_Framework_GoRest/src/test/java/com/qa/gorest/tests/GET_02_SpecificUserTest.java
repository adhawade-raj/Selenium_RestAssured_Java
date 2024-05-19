package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;

public class GET_02_SpecificUserTest {

	RestClient restClient;
	@Test
	public void getAlluserTest() {
		restClient = new RestClient();
		
		restClient.get("/public/v2/users/6914514", false)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
}
