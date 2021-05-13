Feature: storm damages players

  Scenario Outline: storm tries to damage eskimo
    Given tile has <building>
    When storm comes
    Then player has <temp> temp

    Examples:
    | building  | temp  |
    | nothing   | 4     |
    | igloo     | 5     |
    | tent      | 5     |