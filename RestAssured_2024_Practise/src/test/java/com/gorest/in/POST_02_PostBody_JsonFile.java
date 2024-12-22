package com.gorest.in;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POST_02_PostBody_JsonFile {

	@Test
	public void postBodyTest() {
	RestAssured.baseURI="https://gorest.co.in";
	
	Response response = RestAssured.given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.body(new File("./src/test/resources/postBody.json"))
	.when().log().all()
	.post("/public/v2/users/");
	
	response.prettyPrint();
	
	}
	
}
