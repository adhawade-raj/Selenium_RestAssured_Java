package com.qa.ergast.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ergast.base.BaseTest;
import com.qa.ergast.client.RestClient;
import com.qa.ergast.utils.JsonPathValidator;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GET_03_GetCircuitID_JsonPathValidator_Test extends BaseTest {
	
	@BeforeMethod()
	public void circuitSetup() {
		
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test
	public void validateCircuitDataTest() {
		Response response = restClient.get(CIRCUIT_ENDPOINT+"/2017/circuits.json",false, true);

		JsonPathValidator js = new JsonPathValidator();
		List<String> countryList = js.readList(response, "$.MRData.CircuitTable.Circuits[?(@.circuitId=='shanghai')].Location.country");
		
		System.out.println(countryList);
		Assert.assertTrue(countryList.contains("China"));
		

	}
}
