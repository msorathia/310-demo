Feature: Create and save movie watch lists

  Scenario: Hover over search result to display additional controls
    Given the user is on the search results page
    When the user hovers over a search result on a desktop device
    Then additional controls should appear

  Scenario: Add movie to an existing or new watch list
    Given the user is on the search results page
    When the user clicks the add to watch list button
    Then the user should be able to add the movie to an existing list or create a new list

  Scenario: Create a new watch list
    Given the user is adding a movie to a watch list
    When the user chooses to create a new list
    And the user enters a name for the new list
    And the user presses "enter" or clicks "create"
    Then a new watch list should be created with the default setting as "private"
    And the movie should be automatically added to the new list

  Scenario: Delete movie from watch list
    Given the user has a movie in their watch list
    When the user clicks the delete button for the movie
    And the user confirms the deletion in the dialog
    Then the movie should be removed from the watch list

  Scenario: Move movie to another watch list
    Given the user has a movie in their watch list
    When the user clicks the move button for the movie
    And the user selects another list to move or copy the movie to
    Then the movie should be moved to the selected list accordingly and removed from the existing list

  Scenario: Copy movie to another watch list
    Given the user has a movie in their watch list
    When the user clicks the copy button for the movie
    And the user selects another list to copy the movie to
    Then the movie should be copied to the selected list accordingly
