package playwright;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;

public class Get03_DisposeFunction {
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
	public void disposeAPIResponseTest() {
		
		Playwright playwright = Playwright.create();
		APIRequest apiRequest =  playwright.request();
		APIRequestContext apiRequestContext =  apiRequest.newContext();
		
		
		APIResponse apiResponse =  apiRequestContext.get("https://gorest.co.in/public/v2/users");
		
		int status = apiResponse.status();
		System.out.println("Response code is : "+status);
		Assert.assertTrue(true, "Status is not 200");
		
		System.out.println("-----API Response before dispose-----");
		System.out.println(apiResponse.text());
		
		apiResponse.dispose();
		int status2 = apiResponse.status();
		System.out.println("Response code after dispose : "+status2);
		
		
		try {
		System.out.println("-----API Response after dispose-----");
		System.out.println(apiResponse.text());
		}
		catch(PlaywrightException e) {
			System.out.println("Response has been disposed");
		}
		
		
	}
}
