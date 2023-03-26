Feature: testing out the login page and its components.
  Scenario: go to registration page and create account
    Given I am on the login page
    When I click Sign Up
    And I enter "John Doe" for the name.
    And I enter "test@email.com" for the email
    And I enter "test123" for the password
    And I enter "test123" for the password confirmation
    And I click Sign Up
    Then I am registered.
  Scenario: go to registration page and fail to confirm password
    Given I am on the login page
    When I click Sign Up
    And I enter "John Doe" for the name.
    And I enter "test@email.com" for the email
    And I enter "test1234" for the password
    And I enter "test1235" for the password confirmation
    And I click Sign Up
    Then I am not registered.
  Scenario: open the page and login
    Given I am on the login page
    When I enter "tempemail@gmail.com" for the email
    And I enter "1234567" for the password
    And I click submit
    Then I should be logged in.
  Scenario: open the page and give an invalid email
    Given I am on the login page
    When I enter "falseuser@email.com" for the email
    And I enter "1234567" for the password
    And I click submit
    Then I should not be logged in.
  Scenario: open the page and give invalid password
    Given I am on the login page
    When I enter "tempemail@gmail.com" for the email
    And I enter "123456" for the password
    And I click submit
    Then I should not be logged in.