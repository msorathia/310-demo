Feature: Movie Watch Lists
  As a user of the movie search website,
  I want to create and manage movie watch lists,
  so that I can keep track of the movies I want to watch.

  Scenario: Create a new watch list
    Given I am on the movie search page
    When I hover over a movie search result
    And I click on the "+" button to the right of the search result
    And I select "Create new list"
    And I enter a name for the new list
    And I select whether to make it public or private
    And I click "Create"
    Then the new list should be created
    And the movie should be added to the new list

  Scenario: Add a movie to an existing watch list
    Given I am on the movie search page
    When I hover over a movie search result
    And I click on the "+" button
    And I select an existing list to add the movie to
    Then the movie should be added to the selected list

  Scenario: Delete a movie from a watch list
    Given I am viewing a movie in a watch list
    When I click on the "-" button
    Then a confirmation dialog should appear
    And if I confirm, the movie should be removed from the list