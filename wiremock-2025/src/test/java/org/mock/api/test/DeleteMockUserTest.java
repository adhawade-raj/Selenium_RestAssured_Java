package org.mock.api.test;

import org.mock.api.ApiMocks;
import org.mock.api.WiremockSetup;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteMockUserTest {
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

	@Test
	public void createMockUserAPITest() {

		ApiMocks.deleteDummyUser();
		Response response = RestAssured.given().log().all()
				.when()
				.delete("/api/users");

		response.then().log().all()
		.statusCode(204);

	}
}
