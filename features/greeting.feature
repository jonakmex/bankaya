Feature: Greet the user by her name
  Say Hello to the user by using her name

  Scenario: Hello to a valid name
    Given My Name is Jonathan
    When I ask for the greeting
    Then I should be told Hello Jonathan
