package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;

public class GET_04_GetCircuits extends BaseTest {

	@Test
	public void getAlluserTest() {

		restClient.get("/api/f1/2017/circuits.json",false, true)
		.then().log().all()
		.assertThat().statusCode(200);
		
	}
	
}
