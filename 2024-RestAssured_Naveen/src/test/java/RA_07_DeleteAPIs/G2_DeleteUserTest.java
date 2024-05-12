package RA_07_DeleteAPIs;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import RA_07_PutAPIs.G1_UserPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class G2_DeleteUserTest {

	
	@Test
	public void deleteUserTest() {

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
			
	System.out.println("--------------Delete-------------------");
			given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
			.body(g1_UserPojo)
			.when().log().all()
			.delete("/public/v2/users/"+userId)
			.then().log().all()
			.assertThat()
			.statusCode(204);
			
	System.out.println("--------------Get-------------------");
	given().log().all()
	.contentType(ContentType.JSON)
	.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.when()
	.get("/public/v2/users/"+userId)
	.then().log().all()
	.assertThat()
	.statusCode(404)
	.body("message", equalTo("Resource not found"));
	
}
}
