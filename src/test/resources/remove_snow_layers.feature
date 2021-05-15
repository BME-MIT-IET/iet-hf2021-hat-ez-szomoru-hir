Feature: remove snow layers from tile

  Scenario Outline: remove snow layers without shovel
    Given tile has <layer_n> snow layers
    When player remove <number> times
    Then tile has <result> snow layers remaining

    Examples:
    | layer_n | number  | result  |
    | 3       | 1       | 2       |
    | 0       | 1       | 0       |
    | 1       | 2       | 0       |


  Scenario Outline: remove snow layers with shovel
    Given tile has <layer_n> snow layers
    And player has shovel
    When player remove <number> times with item
    Then tile has <result> snow layers remaining

    Examples:
      | layer_n | number  | result  |
      | 3       | 1       | 1       |
      | 0       | 1       | 0       |
      | 1       | 2       | 0       |


  Scenario Outline: remove snow layers with breakable shovel
    Given tile has <layer_n> snow layers
    And player has breakable shovel
    When player remove <number> times with item
    Then tile has <result> snow layers remaining
    And shovel is <usable>

    Examples:
      | layer_n | number  | result  | usable  |
      | 3       | 1       | 1       | usable  |
      | 0       | 1       | 0       | usable  |
      | 1       | 2       | 0       | usable  |
      | 6       | 3       | 0       | broken  |
      | 8       | 4       | 2       | broken  |