Feature: Amazon Product search

  Scenario: Amazon Search
    Given I have a search field on Amazon Page
    When I search for product with name "Apple Mackbook Pro" and price 1000
    Then Product with name "Apple Mackbook Pro" should be displayed
