package com.gorest.in;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class GET_04_User_PathQueryParam {

//	You need to be more careful with combination of path & query param
//	Otherwise u will get 40 : resource not found
//	for go rest only this combination is working
	@Test
	public void getUserWithPathQueryParam() {
	
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.queryParam("status", "active")
		.pathParam("id", 6902529)
		.when().log().all()
		.get("/public/v2/users/{id}")
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}
}
