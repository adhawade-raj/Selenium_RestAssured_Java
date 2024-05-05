package RA_03_04_PostAPIs;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class C3_Post_BookingAuth_Approach1Test {

	@Test
	public void getBookingAuthTest() {

		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		String tokenId = given()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("token");
		
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);
	}
}
