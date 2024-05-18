package com.gorest.in;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class POST_05_LombokPojoTest {

	//O/P not getting printed on console
	
	Faker faker = new Faker();
	String name = faker.name().firstName();
	String emailId = name+"@gmail.com";
	
	@Test
	public void postBodyTest() {
	RestAssured.baseURI="https://gorest.co.in";
	POST_05_LombokPojo pojo = new POST_05_LombokPojo(name, emailId, "male", "active");
	
	Response response = RestAssured.given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.body(pojo)
	.when().log().all()
	.post("/public/v2/users/");
	
	Integer userId = response.jsonPath().get("id");
	System.out.println("UserID : "+userId);

	Response getResponse = given().log().all()
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.when()
	.get("/public/v2/users/"+userId);

	ObjectMapper mapper = new ObjectMapper();
	try {
		POST_05_LombokPojo object = mapper.readValue(getResponse.getBody().toString(), POST_05_LombokPojo.class);
		System.out.println("--------------------------------");
		System.out.println(object.getId());
		System.out.println(object.getName());
		System.out.println(object.getEmail());
		System.out.println(object.getGender());
		System.out.println(object.getStatus());
		System.out.println("--------------------------------");
	
	} catch (JsonProcessingException e) {
		
		e.printStackTrace();
	}

	
//	response.prettyPrint();
	
	
	
	}
	
}
