package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class searchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	public searchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}

}
