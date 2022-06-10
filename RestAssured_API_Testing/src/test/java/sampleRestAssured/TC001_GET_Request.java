package sampleRestAssured;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {

//	This test is getting failed becz URI is no longer active------ code is for reference
	@Test
	void getWeatherDetails() {
	
		
//		Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
//		Request Object
		RequestSpecification httpsRequest = RestAssured.given();
		
//		Response Object
		Response response = httpsRequest.request(Method.GET, "/Hyderabad");
		
//		Print response in console window for just reference
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:"+responseBody);
		
//		status code
		int StatusCode= response.getStatusCode();
		System.out.println("Status code is :"+StatusCode);
		Assert.assertEquals(StatusCode, 200);
		
		
//		status line verification
		String statusLine = response.getStatusLine();
		System.out.println("StatusLine is :" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		}
	
	
}
