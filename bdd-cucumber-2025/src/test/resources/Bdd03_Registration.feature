@Registration
Feature: Amazon Registration

  Background:
    Given user is on Registration Page
    Then User Registration should be successful

  Scenario: User Registration with different data(List)

    When User enters following details
    | Raj | Automation | raj@gmail.com | 77777 | Pune |
    | Naveen | Automation | naveen@gmail.com | 77777 | Pune |


  Scenario: User Registration with different data(Map)
    When User enters following details as Map
      | firstName | lastName | mailId | phoneNo | city |
      | Raj | Automation | raj@gmail.com | 77777 | Pune |
      | Naveen | Automation | naveen@gmail.com | 77777 | Pune |

