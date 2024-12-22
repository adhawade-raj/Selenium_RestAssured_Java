package com.gorest.in;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class GET_03_User_PathParam {

	@Test
	public void getUserWithParamTest() {
		
		RestAssured.baseURI="https://gorest.co.in/";
		
		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.pathParam("id", 6902529)
		.when()
		
		.get("/public/v2/users/{id}")
		
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
	}
	
}
