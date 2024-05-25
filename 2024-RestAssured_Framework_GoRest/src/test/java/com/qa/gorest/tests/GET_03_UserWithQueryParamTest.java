package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;

public class GET_03_UserWithQueryParamTest extends BaseTest {

	@Test
	public void getUserWithQueryParamTest() {

		Map<String, String> queryparams = new HashMap<String, String>();
		queryparams.put("name", "Soma");
		queryparams.put("status", "inactive");
		
		restClient.get(GOREST_ENDPOINT, null, queryparams, true,true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	
}
