package playwright;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpHeader;

public class Get04_FetchResposneHeaders {
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
	public void fetchResponseHeaders() {
		
		Playwright playwright = Playwright.create();
		APIRequest apiRequest =  playwright.request();
		APIRequestContext apiRequestContext =  apiRequest.newContext();
		
		
		APIResponse apiResponse =  apiRequestContext.get("https://gorest.co.in/public/v2/users");
		
		int status = apiResponse.status();
		System.out.println("Response code is : "+status);
		Assert.assertTrue(true, "Status is not 200");

//		Using Map
		System.out.println("====Fetch Reponse Headers using Map");
		Map<String, String> headersMap = apiResponse.headers();
		headersMap.forEach((k,v) -> System.out.println(k+" : "+v));
		System.out.println("total response headers : "+headersMap.size());
		Assert.assertEquals(headersMap.get("server"), "cloudflare");
		Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");
		
		System.out.println("===============================================================");
		System.out.println("======Fetch Reponse Headers using List (headersArray())========");
		
		List<HttpHeader> headersList = apiResponse.headersArray();
		for(HttpHeader e: headersList) {
			System.out.println(e.name+ " : "+e.value);
		}
		
		
	}
	
}
