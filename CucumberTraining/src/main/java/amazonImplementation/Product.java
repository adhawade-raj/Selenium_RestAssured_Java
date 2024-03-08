package amazonImplementation;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	private String productName;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	private int price;

	public Product(String productName, Integer price) {
		this.productName=productName;
		this.price=price;
	}
	
	public List<String> getProductList(){
		List<String> productList = new ArrayList<>();
		productList.add("Apple Mackbook Pro");
		productList.add("Apple Mackbook Air");
		productList.add("Apple iphone 12");
		return productList;
	}

}
