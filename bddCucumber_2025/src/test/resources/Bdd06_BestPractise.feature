#Given - Pre Conditions, necessary for user action. Avoid talking about user interactions
#When - To describe user actions
#Then - To observe expected output
#And - Addition to previous step
#But - Addition to previous step but represents negative statement


  Feature: Login Page
    In order to test login page
    As a registered user
    I want to specify the login conditions

    #Ugly Way
  Scenario: Amazon login page
    Given User is on amazon Login page
    Given Sign in button is present on login page
    When user clicks on Sign in button
    Then user can see login screen
    When user enters "Raj@gmal.com" in username field
    When user enters "Raj@123" in password field
    When user clicks on sign in button
    Then user is on home page
    Then title of home page is "Amazon"

    Scenario: Amazon login page
      Given User is on amazon Login page
      And Sign in button is present on login page
      When user clicks on Sign in button
      Then user can see login screen
      When user enters "Raj@gmal.com" in username field
      And user enters "Raj@123" in password field
      And user clicks on sign in button
      Then user is on home page
      But sign in button is not present
