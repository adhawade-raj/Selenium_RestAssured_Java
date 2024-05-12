package RA_07_PutAPIs;

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
import static org.hamcrest.Matchers.*;

public class G1_UpdateUserTest {


	@Test
	public void updateUserTest() {

		Faker faker = new Faker();
		String name = faker.name().firstName();
		String emailId = name+"@gmail.com";
	
		RestAssured.baseURI = "https://gorest.co.in";
		G1_UserPojo g1_UserPojo = new G1_UserPojo(name, emailId, "male", "active" );
			
		Response response = given().log().all()
	 .contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.body(g1_UserPojo)	
	.when().log().all()
	.post("/public/v2/users");
			
			Integer userId =response.jsonPath().get("id");
			System.out.println("user Id :"+userId);
			
		//2. Update User
			
			g1_UserPojo.setName("Raj");
			g1_UserPojo.setStatus("inactive");
			
			given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
			.body(g1_UserPojo)
			.when().log().all()
			.put("/public/v2/users/"+userId)
			.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.body("id", equalTo(userId))
			.body("name", equalTo(g1_UserPojo.getName()))
			.body("status", equalTo(g1_UserPojo.getStatus()));
			
			
			
		}
}
