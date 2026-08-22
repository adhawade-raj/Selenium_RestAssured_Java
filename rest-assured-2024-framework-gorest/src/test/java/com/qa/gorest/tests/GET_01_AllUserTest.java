package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;


public class GET_01_AllUserTest extends BaseTest {

	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test(priority=3)
	public void getAllUserTest1() {

		restClient.get(GOREST_ENDPOINT,true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	
	/**To Check if this is failing or not*/
	@Test(priority=2)
	public void getOneUserTest2() {
		restClient = new RestClient(prop, baseURI);
		restClient.get(GOREST_ENDPOINT+"/"+6919719,true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	
	@Test(priority=1)
	public void getUserWithQueryParamTest3() {

		Map<String, String> queryparams = new HashMap<String, String>();
		queryparams.put("name", "Soma");
		queryparams.put("status", "inactive");
		
		restClient.get(GOREST_ENDPOINT, null, queryparams, true,true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	
}
