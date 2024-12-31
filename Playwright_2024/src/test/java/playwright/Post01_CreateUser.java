package playwright;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

public class Post01_CreateUser {
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
	public void createUser() throws IOException {
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "raj");
		data.put("email", "raj3@gmail.com");
		data.put("gender", "male");
		data.put("status", "active");
		
		
		APIResponse apiResponse = apiRequestContext.post("https://gorest.co.in/public/v2/users", 
		RequestOptions.create()
		.setHeader("Content-Type", "application/json")
		.setHeader("Authorization", "Bearer ea94c395f5df75e2d78ea00a764aa9d6b96e055e9d8b38e0311178406a3c081c")
		.setData(data)
		);
		
		System.out.println(apiResponse.status());
		Assert.assertEquals(apiResponse.status(), 201);
		
		System.out.println("-----API Response Body-----");
		System.out.println(apiResponse.text());
		System.out.println("-----API Response Body-----");
		
		
		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.readTree(apiResponse.body());
		System.out.println(node.toPrettyString());
		
		//Capture ID from api Response
		
		String userId = node.get("id").asText();
		System.out.println("User Id : "+userId);
		
		
		//GET Call - Fetch the same user
		
		APIResponse apiGetReponse = apiRequestContext.get("https://gorest.co.in/public/v2/users/"+userId,
		RequestOptions.create()
		.setHeader("Authorization", "Bearer ea94c395f5df75e2d78ea00a764aa9d6b96e055e9d8b38e0311178406a3c081c")				
				);
		
		Assert.assertEquals(apiGetReponse.status(), 200);
		Assert.assertTrue(apiGetReponse.text().contains(userId));
		
		
		
	}
}
