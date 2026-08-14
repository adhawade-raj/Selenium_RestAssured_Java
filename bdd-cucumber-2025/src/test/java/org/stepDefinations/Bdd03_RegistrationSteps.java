package org.stepDefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class Bdd03_RegistrationSteps {


    @Given("user is on Registration Page")
    public void user_is_on_registration_page() {

    }
    @When("User enters following details")
    public void user_enters_following_details(DataTable dataTable) {
        List<List<String>> userList = dataTable.asLists(String.class);
        for(List<String> e: userList){
            System.out.println("FirstName="+e.get(0));
            System.out.println("LastName="+e.get(1));
            System.out.println("MailId="+e.get(2));
            System.out.println("Phone No="+e.get(3));
            System.out.println("Place="+e.get(4));
            System.out.println("************************");
        }
    }

    @When("User enters following details as Map")
    public void user_enters_following_details_as_Map(DataTable dataTable) {
        List<Map<String, String>> userList = dataTable.asMaps(String.class, String.class);
        for(Map<String, String> e: userList){
            System.out.println("FirstName="+e.get("firstName"));
            System.out.println("LastName="+e.get("lastName"));
            System.out.println("MailId="+e.get("mailId"));
            System.out.println("Phone No="+e.get("phoneNo"));
            System.out.println("Place="+e.get("city"));
            System.out.println("************************");
        }
    }
    @Then("User Registration should be successful")
    public void user_registration_should_be_successful() {
        System.out.println("-----Registration Successful-----");
    }


}
