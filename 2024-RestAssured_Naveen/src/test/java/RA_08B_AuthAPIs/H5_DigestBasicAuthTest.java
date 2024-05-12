package RA_08B_AuthAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class H5_DigestBasicAuthTest {
	@Test
	public void basicAuthTest() {
	RestAssured.baseURI="https://the-internet.herokuapp.com";
	
	String responseBody = RestAssured.given()
			.auth().digest("admin", "admin")
			.when()
			.get("/basic_auth")
			.then().assertThat() 
			.statusCode(200)
			.extract().body().asString();
	
	System.out.println(responseBody);
}
}
