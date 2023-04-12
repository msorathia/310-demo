Feature: Movie search
  As a user of the movie search website,
  I want to search for movies,
  so that I can find information about them.

  Scenario: Search for a movie based on keyword with "Enter" key
    Given I am on the movie search page
    When I press the keyword radio button
    And I search for "a" using the Enter key
    Then I should see a list of search results that includes "a"

  Scenario: Search for a movie based on keyword with "Search" button
    Given I am on the movie search page
    When I press the keyword radio button
    And I search for "a" using the Search button
    Then I should see a list of search results that includes "a"

  Scenario: Search for a movie based on actor with "Enter" button
    Given I am on the movie search page
    When I press the actor radio button
    And I search for "Tom Cruise" using the Enter key
    And I click on the first Search Result
    Then I should see a list of search results that includes "Tom Cruise"

  Scenario: Search for a movie based on actor with "Search" button
    Given I am on the movie search page
    When I press the actor radio button
    And I search for "Tom Cruise" using the Search button
    And I click on the first Search Result
    Then I should see a list of search results that includes "Tom Cruise"

  Scenario: Search for a movie based on title with "Enter" button
    Given I am on the movie search page
    When I press the title radio button
    And I search for "Inception" using the Enter key
    Then I should see a list of search results that includes "Inception"

  Scenario: Search for a movie based on title with "Search" button
    Given I am on the movie search page
    When I press the title radio button
    And I search for "Inception" using the Search button
    Then I should see a list of search results that includes "Inception"

  Scenario: Load more search results
    Given I am on the movie search page
    When I press the title radio button
    And I search for "fast and furious" using the Enter key
    And I click on the "Load More" button
    Then I should see more search results

  Scenario: View movie details
    Given I am on the movie search page
    When I search for "Inception" using the Enter key
    And I click on the first Search Result
    Then I should see "Actors" on the page

  Scenario: Close movie details card
    Given I am on the movie search page
    When I search for "Inception" using the Enter key
    And I click on the first Search Result
    And I click on the first Search Result
    Then I should not see "Actors" on the page

  Scenario: View movie details with a list of actors more than ten
    Given I am on the movie search page
    When I press the keyword radio button
    And I search for "Inception" using the Enter key
    And I click on the first Search Result
    Then The list should be scrollable

  Scenario: View movie details with a list of actors at or less than ten
    Given I am on the movie search page
    When I press the keyword radio button
    And I search for "Inception" using the Enter key
    And I click on the first Search Result
    Then The list should not be scrollable

  Scenario: I enter a search that yields no results
    Given I am on the movie search page
    When I press the keyword radio button
    And I search for "fhoubaeiebfi" using the Enter key, not expecting results
    Then I should see "No results found" on the page