Feature: Testing login signup page
  Scenario: Testing the toggle button to switch from login to sign up
    Given I am on the login signup page
    When I click on the sign up button on the login page
    Then I should be on the sign up section

  Scenario: Testing the toggle button to switch from sign up to login
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I click on the login button on the sign up page
    Then I should be on the login section

  Scenario: Testing the login page with empty or no inputs
    Given I am on the login signup page
    When I enter no inputs
    And I click on the login button on the login page
    Then I should see "User does not exist. Please sign up first." message on the login page

  Scenario: Testing the login page with just the email field empty
    Given I am on the login signup page
    When I enter no inputs for the email id field
    And I enter "12345678" in log in password field
    And I click on the login button on the login page
    Then I should see "User does not exist. Please sign up first." message on the login page

  Scenario: Testing the login page with just the password field empty
    Given I am on the login signup page
    And I enter "testing1@usc.edu" in login's email id field again
    And I click on the login button on the login page with empty password
    Then I should see "User does not exist. Please sign up first." message on the login page

  Scenario: Testing the login page with both email and password filled but user was not signed up for
    Given I am on the login signup page
    When I enter "failed@usc.edu" in login's email id field
    And I enter "12345678" in log in password field
    And I click on the login button on the login page
    Then I should see "User does not exist. Please sign up first." message on the login page

  Scenario: Testing login page with entering email id only but user was signed up
    Given I am on the login signup page
    When I enter "doesnotexist@usc.edu" in login's email id field
    And I enter no inputs for the password field
    And I click on the login button on the login page
    Then I should see "User does not exist. Please sign up first." message on the login page

  Scenario: Testing the login page with incorrect password field empty but user was signed up for
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "testerpassword" in the sign up name field
    And I enter "testingpassword@usc.edu" in the sign up email field
    And I enter "12345678" in the sign up password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page with expectation of message
    And I click on the login button on the sign up page
    And I enter "testingpassword@usc.edu" in login's email id field again
    And I enter "123" in log in password field again
    And I click on the login button on the login page
    Then I should see "User exists but your password is incorrect" message on the login page


  Scenario: Testing the login page with all fields correctly entered
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "tester" in the sign up name field
    And I enter "testing2@usc.edu" in the sign up email field
    And I enter "12345678" in the sign up password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page with expectation of message
    And I click on the login button on the sign up page
    And I enter "testing2@usc.edu" in login's email id field again
    And I enter "12345678" in log in password field again
    And I click on the login button on the login page
    Then I should see "Login successful, Welcome!" message on the login page

  Scenario: Testing the sign up page with no inputs
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I click on the sign up button on the sign up page
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with no inputs for the name field
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter no inputs in the name field
    And I enter "dummy1@usc.edu" in the sign up email field
    And I enter "12345678" in the sign up password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with no inputs for the email field
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter nothing in the email field
    And I enter "12345678" in the sign up password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with no inputs for the password field
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter "dummy2@usc.edu" in the sign up email field
    And I enter nothing in the password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with no inputs for the confirm password field
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter "dummy3@usc.edu" in the sign up email field
    And I enter "12345678" in the sign up password field
    And I enter no inputs in the confirm password field
    And I click on the sign up button on the sign up page
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with invalid email id syntax of missing @
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter "dummymissing" in the sign up email field
    And I enter "12345678" in the sign up password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page with invalid email id syntax of missing address part of email
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter "dummynoaddress@" in the sign up email field
    And I enter "12345678" in the sign up password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page
    Then I should see an automated message on the sign up page while remaining on the sign up page

  Scenario: Testing the sign up page by signing up with already taken email id
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter "duplicate@usc.edu" in the sign up email field
    And I enter "12345678" in the sign up password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page
    And I enter "dummy2" in the sign up name field again
    And I enter "duplicate@usc.edu" in the sign up email field again
    And I enter "123456789" in the sign up password field again
    And I enter "123456789" in the sign up confirm password field again
    And I click on the sign up button on the sign up page with expectation of message
    Then I should see "User already exists" message on the sign up page

  Scenario: Testing sign up page with password length less than 8
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter "passwordlength@usc.edu" in the sign up email field
    And I enter "12345" in the sign up password field
    And I enter "12345" in the sign up confirm password field
    And I click on the sign up button on the sign up page with expectation of message
    Then I should see "Password is less than 8 characters" message on the sign up page

  Scenario: Testing sign up page with passwords not matching
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter "passwordmismatch@usc.edu" in the sign up email field
    And I enter "12345" in the sign up password field
    And I enter "123456" in the sign up confirm password field
    And I click on the sign up button on the sign up page with expectation of message
    Then I should see "Passwords do not match." message on the sign up page

  Scenario: Testing the sign up page with valid inputs
    Given I am on the login signup page
    When I click on the sign up button on the login page
    And I enter "dummy" in the sign up name field
    And I enter "dummy4@usc.edu" in the sign up email field
    And I enter "12345678" in the sign up password field
    And I enter "12345678" in the sign up confirm password field
    And I click on the sign up button on the sign up page with expectation of message
    Then I should see "Sign Up Successful!" message on the sign up page
