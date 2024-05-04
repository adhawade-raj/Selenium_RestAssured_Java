package RA_01_02_GetAPIs;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class A1_GetAPIRequestTest_NonBDD {

	
	@Test()
	public void getAPIRequestTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		
//		request.headers("Authorization", "Bearer ");
		
		Response response = request.get("/public/v2/users");
		
		// 1. Status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//2. Status Msg
		
		String statusMsg = response.statusLine();
		System.out.println(statusMsg);
		
		//3. Fetch Body
//		response.prettyPrint();
		
		//4. Fetch Headers
		
//		A] Specific Header
		String contentType = response.header("content-type");
		System.out.println(contentType);
		
		
//		B] All Headers
		
		List<Header> headerList = response.headers().asList();
		System.out.println(headerList.size());
		for(Header h: headerList) {
			System.out.println(h.getName() +" : "+ h.getValue());
		}
	}
}
