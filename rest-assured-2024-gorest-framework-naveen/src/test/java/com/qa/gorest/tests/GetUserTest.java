package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import io.qameta.allure.Description;

public class GetUserTest extends BaseTest{
	
	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, baseURI);
	}
	//code smell: sonarQube
	@Test(enabled = true)
	public void getAllUsersTest() {
		restClient.get(GOREST_ENDPOINT, true,  true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
						
	}
	
	///public/v2/users/4165714/?name&staus
	@Test(enabled = false)
	public void getUserTest() {
		restClient.get(GOREST_ENDPOINT+"/"+4438975, true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
							.and().body("id", equalTo(4165714));
						
	}
	
	//url?name&staus
	@Test()
	public void getUserWithQueryParamsTest() {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("name", "naveen");
		queryParams.put("status", "active");

		restClient.get(GOREST_ENDPOINT, queryParams, null,true, true)
					.then().log().all()
						.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
													
	}
	
	

}
