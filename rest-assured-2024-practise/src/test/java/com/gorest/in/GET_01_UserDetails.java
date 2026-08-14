package com.gorest.in;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GET_01_UserDetails {

	
	@Test
	public void getUserDetailsTest() {
		
		RestAssured.baseURI="https://gorest.co.in/";
		
		given().log().all()
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.contentType(ContentType.JSON)
		.when()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200);

	}
}
