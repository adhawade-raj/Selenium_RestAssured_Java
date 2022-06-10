package sampleRestAssured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {

//	This test is getting failed becz URI is no longer active------ code is for reference
	@Test
	void registrationSuccessful() {
	
		
//		Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		
//		Request Object
		RequestSpecification httpsRequest = RestAssured.given();
		
//		
		
//		Request payload sending along with post request
		JSONObject Requestparams = new JSONObject();
		Requestparams.put("FirstName", "John");
		Requestparams.put("LastName", "Andrew");
		Requestparams.put("Username", "John123");
		Requestparams.put("Password", "Andrew@123");
		Requestparams.put("Email", "JohnAndrew@gmail.com");
		
		httpsRequest.header("content-type","application/json");
		
		httpsRequest.body(Requestparams.toJSONString());
		
		
//		Response Object
		Response response = httpsRequest.request(Method.POST, "/register");
		
//		Print response in console window for just reference
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:"+responseBody);
		
//		status code
		int StatusCode= response.getStatusCode();
		System.out.println("Status code is :"+StatusCode);
		Assert.assertEquals(StatusCode, 201);
		
		String SuccessCode=response.jsonPath().get("SuccessCode");
		Assert.assertEquals(SuccessCode, "OPERATION SUCCESS");
		}
	
}
