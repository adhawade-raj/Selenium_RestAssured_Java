package RA_08B_AuthAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class H6_APIKey_AuthTest {
	
//	Not Working As of Now
	@Test
	public void basicAuthTest() {
	RestAssured.baseURI="http://api.weatherapi.com";
	
	String responseBody = RestAssured.given()
			.queryParam("q", "London")
			.queryParam("aqi", "no")
			.queryParam("key", "jdsfhgdfjk1223")
			.when()
			.get("/v1/current.json")
			.then().assertThat() 
			.statusCode(200)
			.extract().body().asString();
	
	System.out.println(responseBody);
}
}
