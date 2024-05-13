package com.gorest.in;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GET_07_ValidateBody_JsonPath_ArraySingleElement {

	
	@Test
	public void getUSerWithQueryParamTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response = given().log().all()
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.contentType(ContentType.JSON)
		.when()
		.get("/public/v2/users");
		
		JsonPath jsonPath =response.jsonPath();
		
		int id = jsonPath.getInt("[0].id");
		System.out.println(id);
		
		String name = jsonPath.getString("[0].name");
		System.out.println(name);

	}
	
}
