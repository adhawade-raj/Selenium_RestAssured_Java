package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	//1.declare private driver;
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2.Page Constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3.By Locator
	private By emailId= By.xpath("//input[@name='email']");
	private By password= By.xpath("//input[@name='password' and @type='password']");
	private By loginBtn= By.xpath("//input[@type='submit' and @value='Login']");
	private By RegisterLink= By.xpath("(//a[text()='Register'])[2]");
	private By forgotPwdLink= By.xpath("(//a[text()='Forgotten Password'])[1]");
	
	
	//4.PageActions
	public String getLoginPageTitle()
	{
		return eleUtil.doGetTitle(Constants.LOGINPAGE_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	public boolean getLoginUrl()
	{
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	public boolean isRegisterLinkexist()
	{
		return eleUtil.doIsDisplayed(RegisterLink);
	}
	public AccountsPage doLogin(String un, String pwd)
	{
		System.out.println("Login With:" +un+":" +pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
	
	public RegistrationPage goToRegistrationPahe()
	{
		eleUtil.doClick(RegisterLink);
		return new RegistrationPage(driver);
	}
	
	
	
}
