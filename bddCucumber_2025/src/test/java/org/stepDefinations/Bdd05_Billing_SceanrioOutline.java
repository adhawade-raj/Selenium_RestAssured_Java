package org.stepDefinations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Bdd05_Billing_SceanrioOutline {

    double billingAmount;
    double taxAmount;
    double finalAmount;
    @Given("I have a navigated to Billing Page")
    public void i_have_a_navigated_to_billing_page() {

    }

    @When("User enters {string} in billingAmount field")
    public void user_enters_in_billing_amount_field(String billingAmount) {
    this.billingAmount=Double.parseDouble(billingAmount);
    }
    @When("User enters {string} in taxAmount field")
    public void user_enters_in_tax_amount_field(String taxAmount) {
        this.taxAmount=Double.parseDouble(taxAmount);
    }
    @Then("it gives finalAmount {string}")
    public void it_gives_final_amount(String expectedFinalAmount) {
    this.finalAmount = this.billingAmount+this.taxAmount;
        Assert.assertTrue(this.finalAmount == Double.parseDouble(expectedFinalAmount));
    }


}
