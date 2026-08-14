package com.gorest.in;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GET_14_ReqRespSpecBuilder {

	
	public static RequestSpecification reqSpecBuilder() {
		
	RequestSpecification reSpec = new RequestSpecBuilder()
	.setBaseUri("https://gorest.co.in")
	.addHeader("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.setContentType(ContentType.JSON)
	.build();
	return reSpec;
	}
	
	public static ResponseSpecification resSpecBuilder() {
		
		ResponseSpecification respSpec = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.build();
		
		return respSpec;
	
	}
	
	@Test
	public void getUserDetails() {
		
		RestAssured.given().log().all()
		.spec(reqSpecBuilder())
		.when().log().all()
		.get("/public/v2/users")
		.then()
		.spec(resSpecBuilder());
	}
}
