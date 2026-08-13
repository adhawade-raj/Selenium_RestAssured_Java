@Order
Feature: Amazon Order Page

  Background:
  Given a register user exists
    Given User is on amazon Login page
    When User enters Username and Password
    And Clicks on Login button
    And User is navigated to Home Page
    Then User clicks on Orders Page


  Scenario: Validate Previous Orders
    When user clicks on Order Page
    When User checks previous order details

  Scenario: Validate Open Orders
    When user clicks on Order Page
    When User checks Open order details
