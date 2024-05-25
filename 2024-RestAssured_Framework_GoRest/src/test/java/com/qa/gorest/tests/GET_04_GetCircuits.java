package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;

public class GET_04_GetCircuits extends BaseTest {

	@Test
	public void getAlluserTest() {

		restClient.get(CIRCUIT_ENDPOINT+"/2017/circuits.json",false, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	
}
