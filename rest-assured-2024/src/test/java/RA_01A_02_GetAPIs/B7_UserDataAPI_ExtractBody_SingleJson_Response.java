package RA_01A_02_GetAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class B7_UserDataAPI_ExtractBody_SingleJson_Response {

//	To fetch multiple values o single json using extract
	@Test()
	public void getProductDataAPI_WIthExtractBodyTest1() {
		System.out.println("________________Approach 1________________");
		RestAssured.baseURI="https://gorest.co.in";
		Response response = given().log().all()
				.when().log().all()
				.get("/public/v2/users/6880165");
				
				int userId = response.then().extract().path("id");
				String email = response.then().extract().path("email");
				System.out.println(userId);
				System.out.println(email);
}
	
	//.then().extract() removed and provided in bdd approach lines
	@Test()
	public void getProductDataAPI_WIthExtractBodyTest2() {
		System.out.println("________________Approach 2________________");
		RestAssured.baseURI="https://gorest.co.in";
		Response response = given().log().all()
				.when().log().all()
				.get("/public/v2/users/6880165")
				.then()
				.extract()
				.response();
				
				int userId = response.path("id");
				String email = response.path("email");
				System.out.println(userId);
				System.out.println(email);
}
	
}
