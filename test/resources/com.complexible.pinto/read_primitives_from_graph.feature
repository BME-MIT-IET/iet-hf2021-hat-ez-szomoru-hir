Feature: Read primitives from graph

  Scenario Outline: read primitives from graph file
    Given graph file is primitives.nt
    When I ask the the "<type>" argument
    Then I should be told "<value>"

  Examples:
    | type  | value |
    | string | str value |
    | char | o |
    | double | 20.22 |
    | int | 8 |
    | float | 4.5 |