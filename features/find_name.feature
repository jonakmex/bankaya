Feature: Find Pokemon Name

Scenario: Finding the name for an existing pokemon by name
    Given There is a pokemon called ditto
    When I retrieve it's name
    Then I should get it's name

Scenario: Finding the id for a non existing pokemon
    Given There is not a pokemon called xyz
    When I retrieve it's name
    Then I should get an empty name