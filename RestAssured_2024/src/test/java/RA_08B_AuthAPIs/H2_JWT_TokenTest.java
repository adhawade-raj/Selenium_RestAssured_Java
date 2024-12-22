package RA_08B_AuthAPIs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class H2_JWT_TokenTest {

	@BeforeTest()
	public void allureSetup() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	@Test()
	public void jwtAuthWithJsonBodyTest() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		String jwtToken = RestAssured.given()
				.contentType(ContentType.JSON)
				.body("{\r\n"
						+ "    \"username\": \"mor_2314\",\r\n"
						+ "    \"password\": \"83r5^_\"\r\n"
						+ "}")
				.when()
				.post("/auth/login")
				.then()
				.assertThat().statusCode(200)
				.extract().path("token");
		System.out.println(jwtToken);
		
		//How to to fetch jwt token in three parts
		System.out.println("-----Token splitting into 3 parts----");
		String tokenArr[] = jwtToken.split("\\.");
		System.out.println(tokenArr[0]);
		System.out.println(tokenArr[1]);
		System.out.println(tokenArr[2]);
		
		
		/**
		 * eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
		 * eyJzdWIiOjIsInVzZXIiOiJtb3JfMjMxNCIsImlhdCI6MTcxNTUyMDQyNX0
		 * 5LNOX_9aYQ0efAIDu2M4UO_ZrvRm
		 */
		
//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXIiOiJtb3JfMjMxNCIsImlhdCI6MTcxNTUyMDQyNX0.5LNOX_9aYQ0efAIDu2M4UO_ZrvRm
		
		
	}
}
