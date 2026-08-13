package org.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Bdd02_ProductSteps_Tags {
    @Given("User is on amazon home page")
    public void userIsOnAmazonHomePage() {
        System.out.println("Step 1");
    }


    @When("User selects the {string} product")
    public void user_selects_the_product(String product) {
        System.out.println("Step 2" + product);
    }

    @Then("User can add the products to cart")
    public void userCanAddTheProductsToCart() {
        System.out.println("Step 3");
    }
}
