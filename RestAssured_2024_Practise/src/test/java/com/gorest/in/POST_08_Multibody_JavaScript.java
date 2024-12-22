package com.gorest.in;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class POST_08_Multibody_JavaScript {
	@Test
	public void bodyWithText() {
		RestAssured.baseURI="http://httpbin.org";
		String payLoad = "function login(){\r\n"
				+ "    let x=10;\r\n"
				+ "    let y=20;\r\n"
				+ "    console.login(x+y);\r\n"
				+ "}";
		io.restassured.response.Response response = RestAssured.given()
				.header("Content-Type", "application/javascript")
				.body(payLoad)
				.when()
				.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
	}
}
