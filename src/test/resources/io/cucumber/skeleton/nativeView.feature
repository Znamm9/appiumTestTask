Feature: Native view
  Scenario: check native view displayed
    Given app started
    And I click login
    When I click native view
    Then I see native elements

  Scenario: Check native first element view text
    Given app started
    When I click login
    And I click native view
    Then I text of first element
