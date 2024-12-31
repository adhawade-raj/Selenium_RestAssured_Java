package playwright;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class Get02_GetSpecificUser {

	
	
	Playwright playwright;
	APIRequest apiRequest;
	APIRequestContext apiRequestContext;
	JsonNode jsonResponse;
	
	@BeforeTest()
	public void setup() {

		 playwright = Playwright.create();
		 apiRequest =  playwright.request();
		 apiRequestContext =  apiRequest.newContext();
	 
	}
	
	@AfterTest()
	public void tearDown() {
		playwright.close();
	}
	
	@Test
	public void getSpecificUserTest() {
		APIResponse apiResponse =  apiRequestContext.get("https://gorest.co.in/public/v2/users", 
				RequestOptions.create()
//				.setQueryParam("gender", "male")
//				.setQueryParam("status", "active")
				.setQueryParam("id", "7491953"));
		
		
		int status = apiResponse.status();
		System.out.println("Response code is : "+status);
		Assert.assertTrue(true, "Status is not 200");
		Assert.assertEquals(apiResponse.ok(), true);
		
		String statusText = apiResponse.statusText();
		System.out.println("Response status message is : "+statusText);
		
		
System.out.println("-----Printing API Response body,  which is valid using Jackson API-----");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			 jsonResponse = mapper.readTree(apiResponse.body());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String jsonPrettyResponse = jsonResponse.toPrettyString();
		System.out.println(jsonPrettyResponse);
		
	}
}
