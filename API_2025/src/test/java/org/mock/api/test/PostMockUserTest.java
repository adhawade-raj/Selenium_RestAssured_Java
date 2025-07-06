package org.mock.api.test;

import static org.hamcrest.Matchers.equalTo;

import org.mock.api.ApiMocks;
import org.mock.api.WiremockSetup;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostMockUserTest {

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

		ApiMocks.createDummyUser();
		Response response = RestAssured.given().log().all()
				.body("{\r\n" + "\"name\" : \"Raj\"\r\n" + "}")
				.when()
				.post("/api/users");

		response.then().log().all()
		.statusCode(201)
		.body("name", equalTo("Raj"));
		System.out.println("Response: " + response.asString());

	}

}
