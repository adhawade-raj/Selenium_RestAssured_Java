package RA_07D_MultiBody;

import org.asynchttpclient.Response;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class G4_BodyWithTextTest {

	@Test
	public void bodyWithText() {
		RestAssured.baseURI="http://httpbin.org";
		String payLoad = "Hi this is raj";
		io.restassured.response.Response response = RestAssured.given()
				.contentType(ContentType.TEXT)
				.body(payLoad)
				.when()
				.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
	}
	
}
