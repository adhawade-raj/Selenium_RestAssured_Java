package com.gorest.in;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GET_11_ValidateBody_ExtractFunction_MultipleElement_SingleJson {
	@Test()
	public void getProductDataAPI_WIthExtractBodyTest3() {
		RestAssured.baseURI="https://gorest.co.in";
		Response response = given().log().all()
				.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
				.contentType(ContentType.JSON)
				.when().log().all()
				.get("/public/v2/users/6903740")
				.then()
				.extract()
				.response();
				
		int id = response.path("id");
		System.out.println(id);
		
		String name = response.path("name");
		System.out.println(name);
		
		String email = response.path("email");
		System.out.println(email);
		
		String gender = response.path("gender");
		System.out.println(gender);
		
		String status = response.path("status");
		System.out.println(status);
		
	}				
}
