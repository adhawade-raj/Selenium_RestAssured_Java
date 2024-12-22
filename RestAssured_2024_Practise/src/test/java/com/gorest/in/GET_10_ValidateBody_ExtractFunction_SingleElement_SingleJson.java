package com.gorest.in;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GET_10_ValidateBody_ExtractFunction_SingleElement_SingleJson {

//	this extract function will be used to fetch only one value
//	e.g  we will be fetching token with this approach
//	So that we can pass the token id in another function
	
	@Test()
	public void getProductDataAPI_WIthExtractBodyTest3() {
		RestAssured.baseURI="https://gorest.co.in";
		String userName = given().log().all()
				.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
				.contentType(ContentType.JSON)
				.when().log().all()
				.get("/public/v2/users/6903740")
				.then()
				.extract().path("name");
		System.out.println(userName);
	}				
}
