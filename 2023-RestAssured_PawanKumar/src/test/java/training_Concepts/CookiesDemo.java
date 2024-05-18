package training_Concepts;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo {

	
	
	@Test(priority=1)
	void testCookies() {
		
		given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.cookie("AEC","Ad49MVFZEnz97j4oX_nCzLVBPoTgF_PdvsraVUuWFoIhNvue3RWFd0Iwdw")
		.log().all();
		
	}
	
	@Test(priority=2)
	void getCookiesInfo() {
		
	Response res=given()
			
			.when()
			.get("https://www.google.com/");
			
	//To get Single Cookie Info
//			String cookie_value=res.getCookie("AEC");
//	System.out.println("Value f Cookie is---->"+cookie_value);
	
	
//	To get All Cookie Info
	Map <String,String> cookies_values = res.getCookies();
	
	for(String e: cookies_values.keySet()) {
		String cookie_Value=res.getCookie(e);
		System.out.println(e+"    "+cookie_Value);
	}
	}
}
