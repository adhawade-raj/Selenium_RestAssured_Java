package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By header = By.xpath("//div[contains(@id,'content')]/h2");
	private By searchField = By.xpath("//input[@name ='search']");
	private By searchBtn = By.xpath("(//button[@type='button'])[1]");
	private By logoutLink = By.xpath("(//a[text()='Logout'])[last()]");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
		public String getAccountPageTitle() throws InterruptedException {
			Thread.sleep(5000);
			return eleUtil.doGetTitle(Constants.Account_PAGE_TITLE, 20);
		}
		
		public boolean isLogoutlinkExists() {
			return eleUtil.doIsDisplayed(logoutLink);
		}
		
		public void logout() {
			if(isLogoutlinkExists()) {
				eleUtil.doClick(logoutLink);
			}
		}
		
		public List<String> getAccountSecList() {
			List<WebElement> accList =  eleUtil.WaitForeElementsToBePresent(header, 10);
			List<String> accSecList = new ArrayList<String>();
			
			for(WebElement e: accList)
			{
				String text = e.getText();
				accSecList.add(text);
			}
			return accSecList;
		}
		
		public boolean isSearchExists() {
			return eleUtil.doIsDisplayed(searchField);
		}
		
		public searchResultsPage doSearch(String productName) {
			eleUtil.doSendKeys(searchField, productName);
			eleUtil.doClick(searchBtn);
			return new searchResultsPage(driver);
		}
		
		
		}
	

