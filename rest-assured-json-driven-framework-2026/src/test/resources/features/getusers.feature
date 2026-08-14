Feature: Users API - GET endpoints

  Background:
    Given the API base URI is loaded from configuration

  @noAuth
  Scenario: Get all users without authorization
    Given the API endpoint is "/public/v2/users"
    When I send a GET request without authorization
    Then the response status code should be 200
    And the response should contain a list of users


  @JsonDrivenFile

  Scenario: Get users without authorization using json
    Given user ids are loaded from "get_specific_users.json"
    When I send GET request without authorization for each user
    Then response status code should be 200
