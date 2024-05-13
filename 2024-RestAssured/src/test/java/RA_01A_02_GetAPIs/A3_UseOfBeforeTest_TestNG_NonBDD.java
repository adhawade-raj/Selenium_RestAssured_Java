package RA_01A_02_GetAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class A3_UseOfBeforeTest_TestNG_NonBDD {

	RequestSpecification request;
	
	@BeforeTest()
	public void setup() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		 request = RestAssured.given();
		 request.header("Authorization", "4d5ca2826ef627bc6b000b82bb7df8c26831266a1b936e701a3c06f3f1e9bf77");
	}
	
	@Test(priority =1)
	public void getAPIRequestTest() {
		
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
//		List<Header> headerList = response.headers().asList();
//		System.out.println(headerList.size());
//		for(Header h: headerList) {
//			System.out.println(h.getName() +" : "+ h.getValue());
//		}
	}
	
	
	@Test(priority =2)
	public void gteUsersWithQueryParamTest() {
		System.out.println("_____________Approach 1 queryParam_______________");
		
		request.queryParam("name", "Darshan");
		request.queryParam("status", "active");
		
		Response response = request.get("/public/v2/users");
		
		System.out.println(response.getStatusCode());
		response.prettyPrint();
	}
	
	
	@Test(priority =3)
	public void gteUsersWithQueryParam_WithHashMapTest() {
		System.out.println("_____________Approach 2 queryParams using HashMap_______________");
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("name", "Darshan");
		queryParamsMap.put("status", "active");
		
		request.queryParams(queryParamsMap);
		
		Response response = request.get("/public/v2/users");
		
		System.out.println(response.getStatusCode());
		response.prettyPrint();
	}
}
