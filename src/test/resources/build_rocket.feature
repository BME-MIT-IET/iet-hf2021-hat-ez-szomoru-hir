Feature: cannot build rocket

  Scenario Outline: Scientist cannot build rocket
    Given Scientist has <number> parts
    When tries to build rocket
    Then it is unsuccessful

    Examples:
    | number  |
    | 1       |
    | 2       |