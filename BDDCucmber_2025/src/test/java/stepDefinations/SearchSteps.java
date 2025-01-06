package stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {

	@Before
	public void beforeScenario() {
		System.out.println("Before Scenario: Initializing Test");
	}

	@Given("I have a search field on Amazon Page")
	public void i_have_a_search_field_on_Amazon_Page() {
		System.out.println("Step 1: I am on the Amazon search page.");
	}

	@When("I search for product with name {string} and price {int}")
	public void i_search_for_product_with_name_and_price(String productName, Integer price) {
		System.out.println("Step 2: Searching for product: " + productName + " with price: " + price);
	}

	@Then("Product with name {string} should be displayed")
	public void product_with_name_should_be_displayed(String productName) {
		System.out.println("Step 3: Product " + productName + " is displayed.");
	}

	@After
	public void afterScenario() {
		System.out.println("After Scenario: Test Completed");
	}
}
