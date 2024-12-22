package com.gorest.in;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GET_02_User_QueryParam {

	
	@Test
	public void getUSerWithQueryParamTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.contentType(ContentType.JSON)
		.queryParam("status", "active")
		.queryParam("gender", "male")
		.queryParam("page", "1")
		.when()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200);

	}
}
