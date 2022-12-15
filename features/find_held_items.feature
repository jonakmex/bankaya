Feature: Find Pokemon Held Items

Scenario: Finding the held items for an existing pokemon by name
    Given There is a pokemon called ditto
    When I retrieve it's held items
    Then I should get it's held items

Scenario: Finding held items for a non existing pokemon
    Given There is not a pokemon called xyz
    When I retrieve it's held items
    Then I should get an empty held items