package GetAPIs;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class A2_GetUsersWithQueryParameterTest_NonBDD {

	@Test()
	public void gteUsersWithQueryParamTest() {
		System.out.println("_____________Approach 1 queryParam_______________");

		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		
		request.queryParam("name", "Darshan");
		request.queryParam("status", "active");
		
		Response response = request.get("/public/v2/users");
		
		System.out.println(response.getStatusCode());
		response.prettyPrint();
	}
	
	
	@Test()
	public void gteUsersWithQueryParam_WithHashMapTest() {
		System.out.println("_____________Approach 2 queryParams using HashMap_______________");
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("name", "Darshan");
		queryParamsMap.put("status", "active");
		
		request.queryParams(queryParamsMap);
		
		
		Response response = request.get("/public/v2/users");
		
		System.out.println(response.getStatusCode());
		response.prettyPrint();
	}
}
