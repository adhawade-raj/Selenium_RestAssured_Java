package com.qa.gorest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class POST_01_CreateUserTest extends BaseTest {
	
	
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"Raj12","male", "active" },
			{"Raj13","male", "active" },
			{"Raj14","male", "active" },
		};
	}
	
	
	@Test(dataProvider="getUserData")
	public void createUserTest(String name, String gender, String status) {
		User user = new User(name, StringUtils.getRandomEmailId(), gender, status);
		
		//1.POST
		RestClient restClient1 = new RestClient(prop, baseURI);
		
		Integer userId = restClient1.post(GOREST_ENDPOINT,"json",user,true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
		.extract()
		.path("id");
		System.out.println(userId);
		
		//2.GET
		RestClient restClient2 = new RestClient(prop, baseURI);
		restClient2.get(GOREST_ENDPOINT+"/"+userId,true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.body("id", equalTo(userId));
		
	}
	
}
