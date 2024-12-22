package RA_03B_04_PostAPIs;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Pojo.D2_User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

	

public class D3_CreateUserWithPojo_RandomMailGenerationTest {
	
	//Approach 1 - using generic method 
	public static String generateRandomMailId() {
		return "apiautomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	// Approach 2 - Faker library
	Faker faker = new Faker();
	String firstName = faker.name().firstName();
	String emailId = firstName+"@gmail.com";
	
	
	@Test()
	public void postGetCallwitjhPojoTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
		D2_User d2_User = new D2_User(firstName, generateRandomMailId(), "male", "active");
		
		//Post Call - Create User
	int userId = given().log().all()
		.contentType(ContentType.JSON)
		.body(d2_User)
	    .header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
    .when().log().all()
	    .post("/public/v2/users")
	.then().log().all()
	    .assertThat()
	    .statusCode(201)
	    .body("name", equalTo(d2_User.getName()))
	    .extract()
	    .path("id");
	
	//Get Call - get the same user
given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
.when().log().all()
	.get("public/v2/users/"+userId)
.then()
	.assertThat()
	.statusCode(200)
	.body("id", equalTo(userId))
	.body("name", equalTo(d2_User.getName()));
	
	
	}
}
