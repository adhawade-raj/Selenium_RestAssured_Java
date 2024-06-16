package com.gorest.in;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_11_Multibody_MultipartFormData {
	@Test
	public void bodyWithText() {
		RestAssured.baseURI="http://httpbin.org";
		
		io.restassured.response.Response response = RestAssured.given()
				.contentType(ContentType.MULTIPART)
				.multiPart("name", "testing")
				.multiPart(new File("./src/test/resources/multipart form data.pdf"))
				.when()
				.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
	}
}
