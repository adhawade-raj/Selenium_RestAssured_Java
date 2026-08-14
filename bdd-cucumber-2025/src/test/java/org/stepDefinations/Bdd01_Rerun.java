package org.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Bdd01_Rerun {

    @Given("I have a search field on Amazon Page Rerun")
    public void i_have_a_search_field_on_Amazon_Page_Rerun() {
        System.out.println("Step 1: I am on the Amazon search page.");
    }

    @When("I search for product with name {string} and price {int} Rerun")
    public void i_search_for_product_with_name_and_price_Rerun(String productName, Integer price) {
        System.out.println("Step 2: Searching for product: " + productName + " with price: " + price);
    }

//    @Then("Product with name {string} should be displayed Rerun")
//    public void product_with_name_should_be_displayed_Rerun(String productName) {
//        System.out.println("Step 3: Product " + productName + " is displayed.");
//    }
}
