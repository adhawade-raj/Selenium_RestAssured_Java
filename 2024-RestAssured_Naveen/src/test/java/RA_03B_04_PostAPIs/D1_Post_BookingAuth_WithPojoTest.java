package RA_03B_04_PostAPIs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import Pojo.D1_Credentials;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class D1_Post_BookingAuth_WithPojoTest {

	
	@Test()
	public void postBookingAuth_WithPojoTest() {
		
		D1_Credentials creds = new D1_Credentials("admin", "password123");
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String tokenID = given().log().all()
		.contentType(ContentType.JSON)
		.body(creds)
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.assertThat()	
		.statusCode(200)
		.extract()
		.path("token");
		
		System.out.println(tokenID);
	}
	
}
