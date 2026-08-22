package com.booksCart.TechVeritoAssignment;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest{

	WebDriver driver;

	@Test()
	public void loginPageTest() {
		String userName = loginPage.loginPage();
		Assert.assertTrue(userName.contains("Welcome Raj Adhawade"), "The user is not logged in as expected.");
	}
}
