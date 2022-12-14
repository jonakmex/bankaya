Feature: Find Pokemon Base Experience

Scenario: Finding the base experience for an existing pokemon by name
    Given There is a pokemon called ditto
    When I retrieve it's base experience
    Then I should get it's base experience

Scenario: Finding base experience for a non existing pokemon
    Given There is not a pokemon called xyz
    When I retrieve it's base experience
    Then I should get an empty result