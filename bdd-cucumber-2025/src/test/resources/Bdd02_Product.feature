@Regression
Feature: Amazon Product Page

  Scenario: Select Products
    Given User is on amazon home page
    When User selects the "Iphone12" product
    Then User can add the products to cart