package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;

public class GET_02_SpecificUserTest extends BaseTest {

	
	@Test
	public void getOneUserTest() {
		restClient.get("/public/v2/users/6914514",true, true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
}
