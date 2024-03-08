package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Title is "+actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void loginPageURL() {
		String Url= loginPage.geLogintUrl();
		System.out.println("Url is "+Url);
		Assert.assertEquals(Url, Constants.LOGIN_PAGE_URL);
	}
	
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPWDLinkExists());
	}
	@Test(priority = 4)
	public void registerLinkExists() {
		Assert.assertTrue(loginPage.isRegisterLinkexists());
	}
	
	@Test(priority = 5)
	public void doLogin() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
}
