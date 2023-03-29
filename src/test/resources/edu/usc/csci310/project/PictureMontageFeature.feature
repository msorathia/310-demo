# Created by aryanshukla at 3/28/23
Feature: Picture Montage Feature for Movie Lists; As a user, if I have a saved movie list, I want to be able to go to the
  montage page and get a collage of 10 movies (no matter how many movies are in my watch list) based on the movies that are
  in the watch list.

  Scenario: User wants to see a picture montage with no watch list
    Given I am on the Montage page
    Then I should see a collage of ten dummy movies

  Scenario: User wants to see a picture montage based on one of their watch lists with exactly ten movies
    Given The user is on the page with their watch list
    And the watch list has ten movies in it
    Then they click on the Show Montage button
    Then the user should be redirected to a montage page with a collage of ten movies
    And the movies referenced in the photos in the collage should all be related to the movies in the starting watch list.
    And each movie in the watch list should be referenced by a photo once.

  Scenario: User wants to see a picture montage based on one of their watch lists with less than ten movies
    Given The user is on the page with their watch list
    And the watch list has ten movies in it
    Then they click on the Show Montage button
    Then the user should be redirected to a montage page with a collage of ten movies
    And the movies referenced in the photos in the collage should all be related to the movies in the starting watch list.
    And each movie in the watch list should be referenced by a photo at least once but may be referenced multiple times

  Scenario: User wants to see a picture montage based on one of their watch lists with more than ten movies
    Given The user is on the page with their watch list
    And the watch list has ten movies in it
    Then they click on the Show Montage button
    Then the user should be redirected to a montage page with a collage of ten movies
    And the movies referenced in the photos in the collage should all be related to the movies in the starting watch list.
    And each picture in the collage should represent a different movie in the watch list but some movies in the list may not be represented.