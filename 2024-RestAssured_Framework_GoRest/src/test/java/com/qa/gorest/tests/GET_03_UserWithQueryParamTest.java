package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;

public class GET_03_UserWithQueryParamTest extends BaseTest {

	@Test
	public void getAlluserTest() {

		Map<String, String> queryparams = new HashMap<String, String>();
		queryparams.put("name", "Soma");
		queryparams.put("status", "inactive");
		
		restClient.get("/public/v2/users", null, queryparams, true,true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
}
