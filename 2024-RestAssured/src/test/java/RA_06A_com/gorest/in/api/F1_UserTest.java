package RA_06A_com.gorest.in.api;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class F1_UserTest {

	Faker faker = new Faker();
	String name = faker.name().firstName();
	String emailId = name+"@gmail.com";
	
	@Test(enabled = true)
	public void getProduct_WithLobokPojoTest_WithoutBuilder() {
		System.out.println("__________Approach 1 Without Builder Pattern________");
		RestAssured.baseURI = "https://gorest.co.in";
		F1_UserPojo f1_UserPojo = new F1_UserPojo(name, emailId, "male", "active" );
		
		Response response = given().log().all()
 .contentType(ContentType.JSON)
.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
.body(f1_UserPojo)		
.when().log().all()
		.post("/public/v2/users");
		
		Integer userId = response.jsonPath().get("id");
		System.out.println("UserID : "+userId);
		
	//GET Api to get same user
		Response getResponse = given().log().all()
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.when().log().all()
		.get("/public/v2/users/"+userId);
				
		//De-serialization
		ObjectMapper mapper = new ObjectMapper();
		try {
		F1_UserPojo f1_ResponseUserPojo = mapper.readValue(getResponse.getBody().asString(), F1_UserPojo.class);
	
		System.out.println("ID :" +f1_ResponseUserPojo.getId());
		System.out.println("Name : "+f1_ResponseUserPojo.getName());
		System.out.println("Email : "+f1_ResponseUserPojo.getEmail());
		System.out.println("Gender : "+f1_ResponseUserPojo.getGender());
		System.out.println("Status : "+f1_ResponseUserPojo.getStatus());
		System.out.println("------------------------------------");
		
		Assert.assertEquals(userId, f1_ResponseUserPojo.getId());
		Assert.assertEquals(f1_UserPojo.getName(), f1_ResponseUserPojo.getName());
		Assert.assertEquals(f1_UserPojo.getEmail(), f1_ResponseUserPojo.getEmail());
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
}
	
	
	
	@Test(enabled =false)
	public void getProduct_WithLobokPojoTest_WithBuilder() {
		System.out.println("__________Approach 2 With Builder Pattern________");
		RestAssured.baseURI = "https://gorest.co.in";
		
		F1_UserPojo f1_UserPojo = new F1_UserPojo.F1_UserPojoBuilder()
				.name(name)
				.email(emailId)
				.status("active")
				.gender("male")
				.build();
		
		Response response = given().log().all()
 .contentType(ContentType.JSON)
.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
.body(f1_UserPojo)		
.when().log().all()
		.post("/public/v2/users");
		
		Integer userId = response.jsonPath().get("id");
		System.out.println("UserID : "+userId);
		
	//GET Api to get same user
		Response getResponse = given().log().all()
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.when().log().all()
		.get("/public/v2/users/"+userId);
				
		//De-serialization
		ObjectMapper mapper = new ObjectMapper();
		try {
		F1_UserPojo f1_ResponseUserPojo = mapper.readValue(getResponse.getBody().asString(), F1_UserPojo.class);
	
		System.out.println("ID :" +f1_ResponseUserPojo.getId());
		System.out.println("Name : "+f1_ResponseUserPojo.getName());
		System.out.println("Email : "+f1_ResponseUserPojo.getEmail());
		System.out.println("Gender : "+f1_ResponseUserPojo.getGender());
		System.out.println("Status : "+f1_ResponseUserPojo.getStatus());
		System.out.println("------------------------------------");
		
		Assert.assertEquals(userId, f1_ResponseUserPojo.getId());
		Assert.assertEquals(f1_UserPojo.getName(), f1_ResponseUserPojo.getName());
		Assert.assertEquals(f1_UserPojo.getEmail(), f1_ResponseUserPojo.getEmail());
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
}
	}
	
