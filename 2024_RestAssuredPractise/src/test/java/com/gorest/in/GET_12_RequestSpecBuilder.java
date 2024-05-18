package com.gorest.in;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class GET_12_RequestSpecBuilder {

	public static RequestSpecification reSpecBuilder() {
		
		RequestSpecification req = new RequestSpecBuilder()
		.setBaseUri("https://gorest.co.in")
		.addHeader("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.setContentType(ContentType.JSON)
		.build();
		
		return req;
		
	}
	
	@Test
	public void getUserDetailsWithReqSpec() {
		
		RestAssured.given().log().all()
		.spec(reSpecBuilder())
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}
}
