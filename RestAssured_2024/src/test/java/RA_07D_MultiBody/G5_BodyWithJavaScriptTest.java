package RA_07D_MultiBody;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class G5_BodyWithJavaScriptTest {
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
