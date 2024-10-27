package com.booksCart.TechVeritoAssignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utilities {

	WebDriver driver;
	public Utilities(WebDriver driver) {
	this.driver=driver;
	}
	
	
	/**
	 * To trim Special Character
	 * @param ele
	 * @return
	 */
	public String trimSpecialCharacter(WebElement ele) {
		return ele.getText().replaceAll("[^a-zA-Z0-9.]","");
	}
	
	
	/**
	 * To add Thread.sleep
	 * @param value
	 */
	public void addThreadSleep(int value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Cart addition
	 */
	public double cartAddition( double a, double b, double c) {
		double total = a+b+c;
		System.out.println("----------------------------------------");
		System.out.println("Actual Total Payble :"+total);
		return total;
	}
	
	
}
