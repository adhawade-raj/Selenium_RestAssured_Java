package com.gorest.in;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class POST_03_PostBody_PojoTest {

	
	@Test
	public void postBodyTest() {
	RestAssured.baseURI="https://gorest.co.in";
	POST_03_PostBody_Pojo pojo = new POST_03_PostBody_Pojo("raj11", "raj11@gmail.com", "male", "active" );
	
	Response response = RestAssured.given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.body(pojo)
	.when().log().all()
	.post("/public/v2/users/");
	
	JsonPath json = response.jsonPath();
	System.out.println(json.getInt("id"));
	
	response.prettyPrint();
	
	}
}
