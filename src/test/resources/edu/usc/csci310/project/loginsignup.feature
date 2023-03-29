Feature: Testing login signup page
  Scenario: Testing the toggle button to switch from login to sign up
    Given I am on the login signup page
    When I click on the sign up button
    Then I should be on the sign up section

  Scenario: Testing the toggle button to switch from sign up to login
      Given I am on the login signup page
      When I click on the sign up button
      And I click on the login button
      Then I should be on the login section

  Scenario: Testing the login page with empty or no inputs
    Given I am on the login signup page
    When I enter no inputs
    And I click on the login button
    Then I should see "User does not exist. Please sign up first." message on the login page

  Scenario: Testing the login page with just the email field empty
    Given I am on the login signup page
    When I enter no inputs for the email id field
    And I enter "12345678" in the password field
    Then I should see "User does not exist. Please sign up first." message on the login page

  Scenario: Testing the login page with just the password field empty
    Given I am on the login signup page
    When I enter no inputs for the password field
    And I enter "testing" in the email id field
    Then I should see "User exists but your password is incorrect" message on the login page

  Scenario: Testing the login page with all fields correctly entered
    Given I am on the login signup page
    When I enter "testing" in the email id field
    And I enter "12345678" in the password field
    Then I should see "Login successful, Welcome!" message on the login page

  Scenario: Testing the sign up page with no inputs
    Given I am on the sign up section
    When I enter no inputs
    And I click on the sign up button
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with no inputs for the name field
    Given I am on the sign up section
    When I enter no inputs in the name field
    And I enter "dummy@usc.edu" in the email field
    And I enter "12345678" in the password section
    And I enter "12345678" in the confirm password section
    And I click on the sign up button
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with no inputs for the email field
    Given I am on the sign up section
    When I enter "dummy" in the name field
    And I enter nothing in the email field
    And I enter "12345678" in the password section
    And I enter "12345678" in the confirm password section
    And I click on the sign up button
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with no inputs for the password field
    Given I am on the sign up section
    When I enter "dummy" in the name field
    And I enter "dummy@usc.edu" in the email field
    And I enter nothing in the password section
    And I enter "12345678" in the confirm password section
    And I click on the sign up button
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with no inputs for the confirm password field
    Given I am on the sign up section
    When I enter "dummy" in the name field
    And I enter "dummy@usc.edu" in the email field
    And I enter "12345678" in the password section
    And I enter no inputs in the confirm password section
    And I click on the sign up button
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with valid inputs
    Given I am on the sign up section
    When I enter "dummy" in the name field
    And I enter "dummy@usc.edu" in the email field
    And I enter "12345678" in the password section
    And I enter "12345678" in the confirm password section
    And I click on the sign up button
    Then I should see "Sign Up Successful!" message on the sign up page

    //add tests for id already taken and other messages