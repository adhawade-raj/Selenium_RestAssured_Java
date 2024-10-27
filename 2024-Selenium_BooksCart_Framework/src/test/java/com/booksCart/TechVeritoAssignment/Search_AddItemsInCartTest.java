package com.booksCart.TechVeritoAssignment;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Search_AddItemsInCartTest extends BaseTest {

	double actualSumOfBooks;
	double expectedSumSumOfBooks;
	int expectedSelectedBooks;
	int actualSelectedBooks;
	double actualTotal;
	double expctedTotal;

	 @Test()
	 public void sumOfBooksWithoutAllCharges() {
		 expectedSelectedBooks = addBooksInCart_ValidateTotal.validateCartPrices();
		 
		/**To validate the actual price of Books and cart addition price of books*/
		 expectedSumSumOfBooks = addBooksInCart_ValidateTotal.expectedTotalOfBooks_WithoutCharges();
		 actualSumOfBooks = addBooksInCart_ValidateTotal.actualTotalOfBooks_WithoutCharges();
		 Assert.assertTrue(actualSumOfBooks==expectedSumSumOfBooks, "Total Not Matched");
		
		
		/**To validate the total calculation*/
		 actualTotal = addBooksInCart_ValidateTotal.actualTotalPayble();
		 expctedTotal = addBooksInCart_ValidateTotal.expectedTotalPayble();
		
		
		/**To validate if selected books count is valid or not*/
		actualSelectedBooks = addBooksInCart_ValidateTotal.actualSelectedBooks();
	 
	 }
	 
	 @Test()
	 public void sumOfBooksWithAllCharges() {
	 Assert.assertTrue(actualTotal==expctedTotal, "Total Not Matched");
	 }
	 
	 @Test()
	  public void totalSelectedBooksCount() {
		Assert.assertTrue(actualSelectedBooks==expectedSelectedBooks, "Total Not Matched");
	 }
	 
}

