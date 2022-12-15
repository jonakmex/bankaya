Feature: Find Pokemon Id

Scenario: Finding the id for an existing pokemon by name
    Given There is a pokemon called ditto
    When I retrieve it's id
    Then I should get it's id

Scenario: Finding the id for a non existing pokemon
    Given There is not a pokemon called xyz
    When I retrieve it's id
    Then I should get an empty id