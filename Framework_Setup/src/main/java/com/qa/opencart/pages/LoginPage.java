package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
private WebDriver driver;
private ElementUtil eleUtil;

public LoginPage(WebDriver driver)
{
	this.driver=driver;
	eleUtil = new ElementUtil(driver);
}

private By emailId = By.xpath("//input[@name='email']");
private By password = By.xpath("//input[@name='password']");
private By loginBtn = By.xpath("//button[text()='Login']");
private By registerLink = By.xpath("(//a[text()='Register'])[2]");
private By forgotPWD = By.xpath("(//a[text()='Forgotten Password'])[2]");


//Page Actions

public String getLoginPageTitle() {
	return driver.getTitle();
}

public String geLogintUrl()
{
	return driver.getCurrentUrl();
}

public boolean isForgotPWDLinkExists() {
	return eleUtil.doIsDisplayed(forgotPWD);
}

public boolean isRegisterLinkexists() {
	return eleUtil.doIsDisplayed(registerLink);
}

public AccountsPage doLogin(String un, String pwd) {
	eleUtil.doSendKeys(emailId, un);
	eleUtil.doSendKeys(password, pwd);
	eleUtil.doClick(loginBtn);
	return new AccountsPage(driver);
}

}
