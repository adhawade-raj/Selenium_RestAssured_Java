package com.qa.ergast.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ergast.base.BaseTest;
import com.qa.ergast.client.RestClient;
import com.qa.ergast.constants.APIHttpStatus;

public class GET_01_GetCircuits_Test extends BaseTest {

	
	@BeforeMethod()
	public void getCircuitsSetup() {
		restClient = new RestClient(prop, baseURI);
	}

	@Test()
	public void getAllCicrcuiteDetailsTest() {
		restClient.get(CIRCUIT_ENDPOINT+"/2017/circuits.json", true, false)
		.then().log().all()
		.assertThat()
//		.statusCode(200)
		.statusCode(APIHttpStatus.OK_200.getCode());	
		
	}
}
