package _Playwright._Playwright;

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

public class Delete01_DeleteTheUser {
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
		
		Post05_LombokPojo requestBody = new Post05_LombokPojo("raj","raj13@gmail.com", "male", "active");

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
		
		int userId = responseBody.getId();
        System.out.println("new user id is : " + userId);

        System.out.println("---------------DELETE CALL----------------");

        //2. PUT Call - update user:
        APIResponse apiPUTResponse = apiRequestContext.delete("https://gorest.co.in/public/v2/users/" + userId,
          RequestOptions.create()
               .setHeader("Authorization", "Bearer ea94c395f5df75e2d78ea00a764aa9d6b96e055e9d8b38e0311178406a3c081c"));

        System.out.println(apiPUTResponse.status() + " : " + apiPUTResponse.statusText());
        Assert.assertEquals(apiPUTResponse.status(), 204);

        System.out.println("---------------GET CALL----------------");

        //3. Get the updates user with GET CALL:
        APIResponse apiGETResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users/"+userId,
                RequestOptions.create()
                .setHeader("Authorization", "Bearer ea94c395f5df75e2d78ea00a764aa9d6b96e055e9d8b38e0311178406a3c081c"));

        System.out.println(apiGETResponse.url());

        int statusCode = apiGETResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 404);
       }
}
