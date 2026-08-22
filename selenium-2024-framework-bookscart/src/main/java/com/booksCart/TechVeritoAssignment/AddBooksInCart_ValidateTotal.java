package com.booksCart.TechVeritoAssignment;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddBooksInCart_ValidateTotal {

	private WebDriver driver;
	 
	private LoginPage loginPage;
	private double actualSumOfBooks=0;
	private  char actCount;
	private  Utilities utilities;
	
	int bookSelectionCount;
	
	public AddBooksInCart_ValidateTotal(WebDriver driver) {
	this.driver=driver;
	loginPage = new LoginPage(driver);
	utilities = new Utilities(driver);
	}
	
	private By searchButton = (By.xpath("//a[@href='/search']"));
	private By cart = By.xpath("//a[@href='/cart'] [@name='cart']");
	private By prodPrice = (By.xpath("//div[contains(@class,'list-group-item list')]/div//span[@class='cart-productPrice']"));
	private By cartTotalValue = (By.xpath("(//strong/span[@class='cart-productPrice'])[1]"));
	private By discountValue = (By.xpath("(//strong/span[@class='cart-productPrice'])[2]"));
	private By shippingChargesValue = By.xpath("(//strong/span[@class='cart-productPrice'])[3]");
	private By paybleTotalValue = By.xpath("(//strong/span[@class='cart-productPrice'])[4]");
	private By headerList = By.xpath("//div[@class='hmenu']/a");
	private By booksCount = By.xpath("//div/small[@class='mobile-center ']");
	
	
	
	/**
	 * Steps to validate the cart price
	 */
	public int validateCartPrices() {
		
		int repeatCount = addBooksToCart(5);
		utilities.addThreadSleep(2000);
		
		WebElement cartButton = driver.findElement(cart);
		cartButton.click();
		utilities.addThreadSleep(2000);
		System.out.println("Expected total count of selected books is : "+repeatCount);
		
		return repeatCount;

	}
		 /**
		  * Actual addition of cart Books
		  * @return
		  */
		 public double expectedTotalOfBooks_WithoutCharges() {
			
			 
			/**To check Actual Sum*/
			List<WebElement> totalPriceOfProduct = driver.findElements(prodPrice);
			for(WebElement e: totalPriceOfProduct) {
			String totalPriceOFProductString = utilities.trimSpecialCharacter(e);
			double parsedValue = Double.parseDouble(totalPriceOFProductString);
			actualSumOfBooks= actualSumOfBooks+parsedValue;
			}
			System.out.println("Expected Sum of Books : "+actualSumOfBooks);
			System.out.println("----------------------------------------");
			return actualSumOfBooks;
		 }
		

		/**Calculated total from UI only Books Total*/	
		public double actualTotalOfBooks_WithoutCharges() {
			
			
		WebElement cartTotal = driver.findElement(cartTotalValue);
		String cartTotal2 = utilities.trimSpecialCharacter(cartTotal);
		double parsedCartTotal = Double.parseDouble(cartTotal2);
		System.out.println("Actual Cart Total :"+parsedCartTotal);
		return parsedCartTotal;
			}
			
		/**
		 * Expected Total Calculated cart including discount, Shipping, total of Books
		 * @return
		 */
		public double expectedTotalPayble() {
			WebElement discount = driver.findElement(discountValue);
			String discount2 = utilities.trimSpecialCharacter(discount);
			double parsedDiscount = Double.parseDouble(discount2);
			System.out.println("Actual Discount:"+parsedDiscount);
			
			WebElement shippingCharges = driver.findElement(shippingChargesValue);
			String shippingCharges2 = utilities.trimSpecialCharacter(shippingCharges);
			double parsedShippingCharges = Double.parseDouble(shippingCharges2);
			System.out.println("Actual Shipping Charges :"+parsedShippingCharges);
			
			/**Addition of cart total*/
			double parsedCartTotal = actualTotalOfBooks_WithoutCharges();
			double expectedTotal = utilities.cartAddition(parsedCartTotal, parsedDiscount, parsedShippingCharges);
			System.out.println("Expected Total : "+expectedTotal);
			return expectedTotal;
			
		}
		
		/**Actual total from UI including discount+Shipping+total of Books*/
		public double actualTotalPayble() {
			WebElement payableTotal = driver.findElement(paybleTotalValue);
			String totalPayable2 = utilities.trimSpecialCharacter(payableTotal);
			double parsedtotalPayable = Double.parseDouble(totalPayable2);
			System.out.println("Actual Total Payble :"+parsedtotalPayable);
			return parsedtotalPayable;
		}
		
		/**
		 * To validate the actual count of selected Book
		 * @return
		 */
		public int actualSelectedBooks() {
			
		WebElement actualBooksCount	 = driver.findElement(booksCount);
		String bookCountText = actualBooksCount.getText();
		String actualCountString = bookCountText.replaceAll("[a-zA-Z]", "");
		actualCountString = actualCountString.replaceAll(" ", "");
		for(int i=0; i<actualCountString.length(); i++) {
			actCount = actualCountString.charAt(0);
		}
		int actualCount = Integer.parseInt(String.valueOf(actCount));
		System.out.println("----------------------------------------");
		System.out.println("Actual total count of Selected books is : "+actualCount);
		System.out.println("----------------------------------------");
		return actualCount;
			
		}

		
		
	/**GENERIC FUNCTIONS*/
		
		public int addBooksToCart(int bookSelectionCount) {
		loginPage.loginPage();
		 
		 /**To add items to cart*/
		 driver.findElement(searchButton).click(); 
		 getTheListOfBookHeader("Only ₹99");
		 utilities.addThreadSleep(1000);
		 System.out.println("=========================================================");
		 for(int i=1; i<=bookSelectionCount; i++) {
		 addToCart(i);
		 utilities.addThreadSleep(100);
		}
		 System.out.println("=========================================================");
		 return bookSelectionCount;
		}
		
		/**
		 * Function to add items in cart
		 */
		public void addToCart(int indexValue) {
		WebElement bookIndex = driver.findElement(By.xpath("(//span[@class='title-main'])"+"["+indexValue+"]"));
		System.out.println(bookIndex.getText());
		
		WebElement buttonIndex = driver.findElement(By.xpath("(//button[@type='button'])"+"["+indexValue+"]"));
		buttonIndex.click();
				}
		
		/**
		 * @param value
		 */
		public void getTheListOfBookHeader(String value) {
		List<WebElement> listOfOptions = driver.findElements(headerList);
		System.out.println("------------List of Book Headers---------");
		
		for(WebElement e: listOfOptions) {
			System.out.println(e.getText());
			if(e.getText().equals(value)) {
				utilities.addThreadSleep(1000);
				e.click();
			}
		}
	}

	

	
	
	
}
