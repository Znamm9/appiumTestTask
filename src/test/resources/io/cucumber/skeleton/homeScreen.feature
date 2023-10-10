Feature: Application

  Background:
    Given app started

  Scenario: check app lunched
    When I click login
    Then User is on home screen

  Scenario: check list of elements on home screen
    When I click login
    Then I see more than <6> elements