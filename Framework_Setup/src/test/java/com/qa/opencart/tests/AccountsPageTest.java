package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority =1)
	public void getaccountPageTitle() throws InterruptedException  {
		String title =accountsPage.getAccountPageTitle();
	    Assert.assertEquals(title, Constants.Account_PAGE_TITLE);
	}
	
	@Test(priority =1)
	public void isLogoutlinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutlinkExists());
	}
	@Test(priority =1)
	public void searchTest() {
		accountsPage.doSearch("Mackbook Pro");
	
}
}
