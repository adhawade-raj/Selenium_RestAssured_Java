package playwright;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

public class Get01_GetAllUsers {

	JsonNode jsonResponse;
	
	@Test()
	public void getAllUSerTest() {
		
		Playwright playwright = Playwright.create();
		APIRequest apiRequest =  playwright.request();
		APIRequestContext apiRequestContext =  apiRequest.newContext();
		
		
		APIResponse apiResponse =  apiRequestContext.get("https://gorest.co.in/public/v2/users");
		
		int status = apiResponse.status();
		System.out.println("Response code is : "+status);
		Assert.assertTrue(true, "Status is not 200");
		Assert.assertEquals(apiResponse.ok(), true);
		
		String statusText = apiResponse.statusText();
		System.out.println("Response status message is : "+statusText);
		
		System.out.println("-----Printing API Response body,  which is not valid-----");
		System.out.println(apiResponse.body());
		System.out.println(apiResponse.body().toString());
		
		
		System.out.println("-----Printing API Response body,  which is valid using Jackson API-----");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			 jsonResponse = mapper.readTree(apiResponse.body());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String jsonPrettyResponse = jsonResponse.toPrettyString();
		System.out.println(jsonPrettyResponse);
		
		
		System.out.println("-----Printing URL-----");
		System.out.println(apiResponse.url());
		
		System.out.println("-----Response Header-----");
		Map<String, String> headersMap = apiResponse.headers();
		System.out.println(headersMap);
		
		
		Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");
		
		
		System.out.println("-----Print API Response with plain text-----");
		System.out.println(apiResponse.text());
		
		
		
		
	}
}
