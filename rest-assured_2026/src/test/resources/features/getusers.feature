Feature: Create users api

  @createUser
  Scenario Outline: create users scenarios
    Given test data file "<dataFile>" and testcase "<testcase>"
    When user calls the api
    Then response status should match expected


    Examples:
      | dataFile        | testcase         |
      | create_user.json| CU01_valid_user  |