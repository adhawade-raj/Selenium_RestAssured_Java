package RA_03_04_PostAPIs;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class C4_Post_BookingAuth_Approach2Test {
	@Test
	public void getBookingAuthTest() {

		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		String tokenId = given()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/resources/data/basicauth.json"))
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
