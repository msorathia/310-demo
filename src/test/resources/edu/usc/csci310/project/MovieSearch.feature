Feature: Movie search
  As a user of the movie search website,
  I want to search for movies,
  so that I can find information about them.

  Scenario: Search for a movie based on keyword with "Enter" key
    Given I am on the movie search page
    When I search for "Inception" using the "Enter" key with the "Keyword" radio button pressed
    Then I should see a list of search results based on keyword

  Scenario: Search for a movie based on keyword with "Search" button
    Given I am on the movie search page
    When I search for "Inception" using the "Search" button with the "Keyword" radio button pressed
    Then I should see a list of search results based on keyword

  Scenario: Search for a movie based on actor with "Enter" button
    Given I am on the movie search page
    When I search for "Inception" using the "Enter" button with the "Actor" radio button pressed
    Then I should see a list of search results based on actor

  Scenario: Search for a movie based on actor with "Search" button
    Given I am on the movie search page
    When I search for "Inception" using the "Search" button with the "Actor" radio button pressed
    Then I should see a list of search results based on actor

  Scenario: Search for a movie based on title with "Enter" button
    Given I am on the movie search page
    When I search for "Inception" using the "Enter" button with the "Title" radio button pressed
    Then I should see a list of search results based on title

  Scenario: Search for a movie based on title with "Search" button
    Given I am on the movie search page
    When I search for "Inception" using the "Search" button with the "Title" radio button pressed
    Then I should see a list of search results based on title

  Scenario: Load more search results
    Given I am on the movie search page
    When I search for "Inception"
    And I click on the "Load more" button
    Then I should see more search results

  Scenario: View movie details
    Given I am on the movie search page
    When I search for "Inception"
    And I click on a movie in the search results
    Then I should see details about the movie

  Scenario: Close movie details popup
    Given I am viewing details about a movie
    When I click the movie title
    Then the movie details popup should close

  Scenario: View movie details with long list of actors
    Given I am viewing details about a movie
    When The list of actors has more than 10 actors
    Then The list should be scrollable

  Scenario: View movie details with short list of actors
    Given I am viewing details about a movie
    When The list of actors has 10 actors or less
    Then The list should not be scrollable