package playwright;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class Post04_CreateUserUsingPojo {

	Playwright playwright;
	APIRequest apiRequest;
	APIRequestContext apiRequestContext;
	
	
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
	public void createUser_StringBody() throws IOException {
		
		Post04_Pojo requestBody = new Post04_Pojo("raj","raj06@gmail.com", "male", "active");

		APIResponse apiResponse = apiRequestContext.post("https://gorest.co.in/public/v2/users", 
		RequestOptions.create()
		.setHeader("Content-Type", "application/json")
		.setHeader("Authorization", "Bearer ea94c395f5df75e2d78ea00a764aa9d6b96e055e9d8b38e0311178406a3c081c")
		.setData(requestBody)
		);
		
		System.out.println(apiResponse.status());
		Assert.assertEquals(apiResponse.status(), 201);
		
		System.out.println("-----API Response Body-----");
		String responseBodyText = apiResponse.text();
		System.out.println(responseBodyText);
		System.out.println("-----API Response Body-----");
		
		
		ObjectMapper mapper = new ObjectMapper();
		Post04_Pojo responseBody = mapper.readValue(responseBodyText, Post04_Pojo.class);
		
		Assert.assertEquals(requestBody.getName(), responseBody.getName());
		Assert.assertEquals(requestBody.getGender(), responseBody.getGender());
		Assert.assertEquals(requestBody.getStatus(), responseBody.getStatus());
		Assert.assertNotNull(responseBody.getId());

	}
	
}
