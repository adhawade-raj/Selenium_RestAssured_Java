package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {

	@Given("I have a search field on amazon page")
	public void i_Have_Seach_Field_On_Amazon_Page()
	{
		System.out.println("Step 1");
	}
	
	@When("I search product with name {String} and Price {int}")
	public void SeachProduct_With_NameAndPrice()
	{
		System.out.println("Step 2");
	}
	@Then("product with name {String} should be displayed")
	public void product_with_name_should_be_displayed()
	{
		System.out.println("Step 3");
	}
	
	
	
}
