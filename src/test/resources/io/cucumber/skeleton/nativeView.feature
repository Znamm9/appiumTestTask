Feature: Native view
  Scenario: check native view displayed
    Given app started
    And I click login
    When I click native view
    Then I see native elements
