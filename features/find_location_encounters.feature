Feature: Find Pokemon Location Encounters

Scenario: Finding the Location Encounters for an existing pokemon by name
    Given There is a pokemon called ditto
    When I retrieve it's Location Encounters
    Then I should get it's Location Encounters

Scenario: Finding Location Encounters for a non existing pokemon
    Given There is not a pokemon called xyz
    When I retrieve it's Location Encounters
    Then I should get an empty Location Encounters