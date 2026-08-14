@Order_DryRun
Feature: Amazon Order Page

  Scenario: Validate Previous Orders
    Given a register user exists
    Given User is on amazon Login page
    When User enters Username and Password
    And Clicks on Login button
    And User is navigated to Home Page
    Then User clicks on Orders Page
    When user clicks on Order Page
    When User checks previous order details
    Then User will be displayed with list of orders