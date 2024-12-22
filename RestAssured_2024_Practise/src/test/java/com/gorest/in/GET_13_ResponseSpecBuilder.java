package com.gorest.in;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class GET_13_ResponseSpecBuilder {

	public static ResponseSpecification responseSpec() {
	
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.build();
		
		return responseSpec;
	}
	
	@Test
	public void getUserWithSpecBuilder() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		RestAssured.given()
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.contentType(ContentType.JSON)
	.when()
	.get("/public/v2/users")
	.then()
	.spec(responseSpec());
	}
	
}
