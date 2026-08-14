@ScenarioOutline
Feature: Amazon Login Page

  Scenario Outline: Login
    Given I have a navigated to Login Page
    When User enters "<username>" in username field
    When User enters "<password>" in password field
    And User clicks on Login Button
    Then user is navigated to home page

    Examples:
    | username | password |
    | Raj1      | test1     |
    | Raj2     | test2     |


  Scenario Outline: Billing Amount
    Given I have a navigated to Billing Page
    When User enters "<BillingAmount>" in billingAmount field
    When User enters "<TaxAmount>" in taxAmount field
    And User clicks on Login Button
    Then it gives finalAmount "<FinalAmount>"

    Examples:
      | BillingAmount | TaxAmount | FinalAmount |
      | 1000          | 180       | 1180        |
      | 2000          | 180       | 2180        |
