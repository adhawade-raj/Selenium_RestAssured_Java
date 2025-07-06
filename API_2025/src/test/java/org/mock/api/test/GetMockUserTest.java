package org.mock.api.test;

import static org.hamcrest.Matchers.equalTo;

import org.mock.api.ApiMocks;
import org.mock.api.WiremockSetup;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetMockUserTest {

	@BeforeTest
	public void setup() {
		WiremockSetup.createWireMockServer();
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8085;
	}

	@AfterTest
	public void tearDown() {
		WiremockSetup.stopWireMockServer();
	}

	@Test(enabled = false) // This test is disabled by default)
	public void mockUserAPITest() {

		ApiMocks.getDummyUser();
		RestAssured.given().log().all()
		.when().get("/api/users")
		.then().log().all()
			.statusCode(200)
			.body("name",equalTo("Raj"));

	}

	@Test(enabled = true)
	public void mockUserWithQueryParamAPITest() {
		ApiMocks.getDummyUserWithQueryParam();
		RestAssured.given().log().all()
		.queryParam("param", "value")
		.when().log().all()
		.get("/api/users")
		.then().log().all()
			.statusCode(200)
			.contentType("application/json");

	}
}
