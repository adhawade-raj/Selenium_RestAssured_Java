package com.gorest.in;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POST_01_PostBody_HardcodedString {

	
	@Test
	public void postBodyTest() {
	RestAssured.baseURI="https://gorest.co.in";
	
	Response response = RestAssured.given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.body("{\r\n"
			+ "\"name\":\"raj9\",\r\n"
			+ "\"email\":\"raj9@gmail.com\",\r\n"
			+ "\"gender\":\"male\",\r\n"
			+ "\"status\":\"active\"\r\n"
			+ "}")
	.when().log().all()
	.post("/public/v2/users/");
	
	response.prettyPrint();
	
	}
}
