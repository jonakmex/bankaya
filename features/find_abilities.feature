Feature: Find Pokemon Abilities

Scenario: Finding abilities for an existing pokemon by name
    Given There is a pokemon called ditto
    When I retrieve it's abilities
    Then I should get a list of Abilities

Scenario: Finding abilities for a non existing pokemon
    Given There is not a pokemon called xyz
    When I retrieve it's abilities
    Then I should get an empty list of abilities