package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;

public class GET_03_UserWithQueryParamTest {

	
	RestClient restClient;
	@Test
	public void getAlluserTest() {
		restClient = new RestClient();
		
		Map<String, String> queryparams = new HashMap<String, String>();
		queryparams.put("name", "Soma");
		queryparams.put("status", "inactive");
		
		restClient.get("/public/v2/users", null, queryparams, true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
}
