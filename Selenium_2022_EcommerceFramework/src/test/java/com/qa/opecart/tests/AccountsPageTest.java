package com.qa.opecart.tests;

import java.util.List;

import org.openqa.selenium.devtools.v85.database.model.Error;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;



public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
//		String actTitle = accountPage.getAccountPageTitle();
//		System.out.println("acc page title: " + actTitle);
//		Assert.assertEquals(actTitle, Constants.ACCOUNT_PAGE_TITLE);
		Assert.assertTrue(accountPage.getAccountPageTitle());
	}

	@Test(priority = 2,enabled=false)
	public void accPageHeaderTest() {
//		String header = accountPage.getAccountsPageHeader();
//		System.out.println("acc page header is: " + header);
//		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
		Assert.assertTrue(accountPage.getAccountsPageHeader());
	}

	@Test(priority = 3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountPage.isLogoutLinkExist());
	}

//	@Test(priority = 4,enabled=false)
//	public void accPageSectionsTest() {
//		List<String> actAccSecList = accountPage.getAccountSecList();
//		Assert.assertEquals(actAccSecList, Constants.getExpectedAccSecList());
//	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { 
			{ "MacBook" }, 
			{ "Apple" }, 
			{ "Samsung" }, 
			};
	}

	@Test(priority = 5, dataProvider = "productData", enabled=false)
	public void searchTest(String productName) {
		searchResultsPage = accountPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductsListCount() > 0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { 
			{ "MacBook" , "MacBook Pro"}, 
			{ "iMac", "iMac" }, 
			{ "Samsung" , "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""}
			};
	}

	@Test(priority = 5, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchResultsPage = accountPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);
	}

}


























