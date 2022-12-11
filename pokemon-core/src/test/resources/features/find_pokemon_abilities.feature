Feature: Find Pokemon abilities
  We want to know what are our pokemon's abilities

  Scenario: Query the abilities for an existing pokemon
    Given There is a pokemon called ditto
    When The Pokemon Finder queries the pokemon abilities
    Then The Pokemon Finder should get the list of abilities

  Scenario: Query the abilities for a non existing pokemon
    Given There is not a pokemon called xyz
    When The Pokemon Finder queries the pokemon abilities
    Then The Pokemon finder should get an empty list of abilities

