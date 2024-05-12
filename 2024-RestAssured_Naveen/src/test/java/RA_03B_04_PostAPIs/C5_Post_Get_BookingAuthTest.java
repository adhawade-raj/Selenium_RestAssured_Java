package RA_03B_04_PostAPIs;

import static org.hamcrest.Matchers.*;
import java.io.File;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class C5_Post_Get_BookingAuthTest {
	
	@Test(enabled =true)
	public void addUserTest() {
		RestAssured.baseURI="https://gorest.co.in";
		
//		1.Post Call - create user
		int userId = given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/resources/data/addUser.json"))
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.when().log().all()
		.post("/public/v2/users/")
		.then()
		.assertThat()
		.statusCode(201)
		.body("name", equalTo("ajay"))
		.extract()
		.path("id");
		System.out.println("User ID : -> "+userId);
		
//		2.Get Call - Validate the same User
	given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
	.when()
		.get("/public/v2/users/"+userId)
	.then()
		.assertThat()
		.statusCode(200)
		.body("id", equalTo(userId));
	}
	
	@Test(enabled = false)
	public void getUserTest() {
		RestAssured.baseURI="https://gorest.co.in";
		
		int id =given()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		.when()
		.get("/public/v2/users/6889707")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("id");
		
		System.out.println(id);
}
}
