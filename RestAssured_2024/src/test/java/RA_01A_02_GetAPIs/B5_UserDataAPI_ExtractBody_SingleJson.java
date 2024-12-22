package RA_01A_02_GetAPIs;

import static io.restassured.RestAssured.given;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class B5_UserDataAPI_ExtractBody_SingleJson {

	
	@Test()
	public void getProductDataAPI_WIthExtractBodyTest3() {
		RestAssured.baseURI="https://gorest.co.in";
		io.restassured.response.Response response = given().log().all()
				.given().log().all()
				.get("/public/v2/users/6880165");
				
				JsonPath js = response.jsonPath();
				
				System.out.println(js.getInt("id"));
				System.out.println(js.getString("email"));
				
}
}
