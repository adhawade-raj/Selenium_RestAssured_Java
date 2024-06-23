package com.qa.ergast.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ergast.base.BaseTest;
import com.qa.ergast.client.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GET_02_GetCircuitID_JsonPath_Test extends BaseTest {

	@BeforeMethod()
	public void circuitSetup() {
		
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void validateCircuitDataTest() {
		Response response = restClient.get(CIRCUIT_ENDPOINT+"/2017/circuits.json", true, false);
		
//		response.prettyPrint();
		JsonPath jsonPath =response.jsonPath();
		
		List<String> circuitId = jsonPath.getList("MRData.CircuitTable.Circuits.circuitId");
		for(String e: circuitId) {
			System.out.println(e);
		}
		
		Assert.assertTrue(circuitId.contains("americas"));

	}
}
