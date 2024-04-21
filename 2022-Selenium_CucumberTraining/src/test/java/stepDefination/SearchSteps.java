package stepDefination;



import amazonImplementation.Product;
import amazonImplementation.Search;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {
	
	Product product;
	Search search;

	@Given("I have a search field on amazon page")
	public void i_Have_Seach_Field_On_Amazon_Page()
	{
		System.out.println("Step 1");
	}
	
	@When("I search product with name {String} and Price {int}")
	public void SeachProduct_With_NameAndPrice(String productName, Integer price)
	{
		
		product = new Product(productName, price);
		System.out.println("Step 2");
	}
	
	@Then("product with name {String} should be displayed")
	public void product_with_name_should_be_displayed(String productName) {
	
	search = new Search();
	
	String name = search.displayProduct(product);
	System.out.println(name);
	
Assertions.assertEquals(product.getProductName(), name);
	}
	
}
