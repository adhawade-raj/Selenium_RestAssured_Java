package com.gorest.in;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GET_05_User_PathParam_DataProvider_DataDrivenTesting {

	
	@DataProvider()
	public static Object[][] pathParamData(){
		
		return new Object [] [] {
			{"male", "active"},
			{"female", "active"}
		};
	}
	
	
	@Test(dataProvider="pathParamData")
	public void getUsersWithPathPara_DataProviderTest(String genderValue, String statusValue) {
		RestAssured.baseURI="https://gorest.co.in";
		
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77")
		
		.queryParam("gender", genderValue)
		.queryParam("status", statusValue)
		
		.when()
		.get("/public/v2/users/")
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}
}
