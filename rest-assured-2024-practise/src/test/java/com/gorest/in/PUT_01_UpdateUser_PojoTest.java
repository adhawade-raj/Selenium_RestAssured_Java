package com.gorest.in;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PUT_01_UpdateUser_PojoTest {

	Faker faker = new Faker();
	String name = faker.name().firstName();
	String emailId = name+"@gmail.com";

	@Test
	public void updateUserTest() {
	RestAssured.baseURI = "https://gorest.co.in";
	PUT_01_UpdateUser_Pojo userBody = new PUT_01_UpdateUser_Pojo(name, emailId, "male", "active" );
	
	
	Response response = given().log().all()
		.contentType(ContentType.JSON)
 	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.body(userBody)	
	.when().log().all()
	.post("/public/v2/users");
	
	Integer userId =response.jsonPath().get("id");
	System.out.println("user Id :"+userId);
	
	userBody.setName("raj15");
	given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.body(userBody)
	.when().log().all()
	.put("/public/v2/users/"+userId)
	.then().log().all()
	.assertThat()
	.statusCode(200)
	.and()
	.body("id", equalTo(userId))
	.body("name", equalTo(userBody.getName()))
	.body("status", equalTo(userBody.getStatus()));
	
	
	
	}
}
