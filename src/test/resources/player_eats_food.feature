Feature: player eats food

  Scenario Outline: player eats food wit full temp
    Given <player> has food and <temp> temp
    When player eat food
    Then its temp is <temp2>

    Examples:
    | player    | temp   | temp2 |
    | eskimo    | 5      | 5     |
    | eskimo    | 4      | 5     |
    | scientist | 4      | 4     |
    | scientist | 3      | 4     |