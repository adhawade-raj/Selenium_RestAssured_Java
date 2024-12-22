package RA_01A_02_GetAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class B1_GetAPIUsersDataAPI_WithQueryParam {

	
	@Test()
	public void getProductDataAPIWithQueryParamTest() {
	
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		.queryParam("name", "Darshan")
		.queryParam("status", "active")
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON);
	}
	
	
}
