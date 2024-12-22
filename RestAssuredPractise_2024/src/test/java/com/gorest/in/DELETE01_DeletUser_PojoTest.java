package com.gorest.in;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DELETE01_DeletUser_PojoTest {

	
	Faker faker = new Faker();
	String name = faker.name().firstName();
	String emailId = name+"@gmail.com";

	@Test
	public void updateUserTest() {
	RestAssured.baseURI = "https://gorest.co.in";
	DELETE_01_DeleteUser_Pojo userBody = new DELETE_01_DeleteUser_Pojo(name, emailId, "male", "active" );
	
	
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
	.delete("/public/v2/users/"+userId)
	.then().log().all()
	.assertThat()
	.statusCode(204);
	
	
	//Get The body - to verify status & body got deleted or not
	
	given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.body(userBody)
	.when().log().all()
	.get("/public/v2/users/"+userId)
	.then().log().all()
	.assertThat()
	.statusCode(404);
	
}
}
