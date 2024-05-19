package com.qa.gorest.tests;

import org.testng.annotations.Test;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;


public class GET_01_AllUserTest extends BaseTest {

	@Test(priority=1)
	public void getAllUserTest() {

		restClient.get("/public/v2/users",true, true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
	/**To Check if this is failing or not*/
	@Test(priority=2)
	public void getOneUserTest2() {
		restClient = new RestClient(prop, baseURI);
		restClient.get("/public/v2/users/6914496",true, true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
}
