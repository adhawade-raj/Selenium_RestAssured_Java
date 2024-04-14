package training_Concepts;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {


	@Test(priority=1, enabled = false)
	void testHeaders() {
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.header("content-type", "text/html; charset-ISO-8859-1")
		.header("content-Encoding", "gzip")
		
		
		.log().all();
	}
	
	
	
	@Test(priority=2)
	void getHeaders() {
		Response res = given()
				
				.when()
				.get("https://www.google.com");
		
		//get single Header Info
//		String headersValue = res.getHeader("content-type");
//		System.out.println("Value of fcontent-type hader is ---> "+headersValue);
	
//	Get All Headers Info
		Headers myHeaders = res.getHeaders();
		
		for(Header e: myHeaders) {
			System.out.println(e.getName()+"     "+e.getValue());
		}
	
	}
}
