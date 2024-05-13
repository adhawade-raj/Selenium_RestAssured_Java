package com.gorest.in;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GET_08_ValidateBody_JsonPath_ArrayListElement {
	@Test
	public void getUSerWithQueryParamTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response = given().log().all()
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.contentType(ContentType.JSON)
		.when()
		.get("/public/v2/users");
		
		JsonPath jsonPath =response.jsonPath();
		
		List<String> idList2 = jsonPath.getList("id", String.class);
		List<Integer> idList = jsonPath.getList("id");
		List<String> nameList = jsonPath.getList("name");
		List<String> emailList = jsonPath.getList("email");
		List<String> genderList = jsonPath.getList("gender");
		List<String> statusList = jsonPath.getList("status");
		
		for(int i=0; i<nameList.size(); i++) {
//			System.out.println("\"id\" : "+idList.get(i));
			System.out.println("id in String Format : "+idList2.get(i));
			System.out.println("id : "+idList.get(i));
			System.out.println("name : "+nameList.get(i));
			System.out.println("email : "+emailList.get(i));
			System.out.println("gender : "+genderList.get(i));
			System.out.println("status : "+statusList.get(i));
			System.out.println("----------------------------------");
		}
}
}
