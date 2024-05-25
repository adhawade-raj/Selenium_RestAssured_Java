package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.APIHttpStatus;

public class GET_02_SpecificUserTest extends BaseTest {

	
	@Test
	public void getOneUserTest() {
		restClient.get(GOREST_ENDPOINT+"/"+6914514,true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	
}
