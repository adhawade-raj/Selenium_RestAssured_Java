package com.gorest.in;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GET_09_ValidateBody_JsonPath_SingleJson {

	
	@Test
	public void getUSerWithQueryParamTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response = given().log().all()
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.contentType(ContentType.JSON)
		.queryParam("id", 6903740)
		.when()
		.get("/public/v2/users/");

	JsonPath jsonPath = response.jsonPath();
	String name = jsonPath.getString("name");
	System.out.println(name);
	System.out.println(jsonPath.getString("gender"));
	
	
	System.out.println(name+ " : "+jsonPath.getString("status"));
	
//	java.lang.NumberFormatException: For input string: "[6903740]"
//	We will get exception as rest of the array is String and we we are trying to print Integer 
//	Integer id = jsonPath.getInt("id");
//	System.out.println(id+" : "+name+ " : "+jsonPath.getString("status"));
	
	
//	This is not a method of jsonpath so this will give a exception
//	String name2 = jsonPath.get("name");
//	System.out.println(name2);  //java.lang.ClassCastException:
	
	
	}
}
