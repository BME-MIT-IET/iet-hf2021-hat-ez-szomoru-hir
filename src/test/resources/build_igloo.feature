Feature: Build igloo

  Scenario Outline: Eskimo build igloo on "<tile>"
    Given Eskimo is on "<tile>"
    When Eskimo tries to build igloo
    Then igloo is on "<tile>" is "<result>"

    Examples:
    | tile      | result  |
    | icetile   | true    |
    | holetile  | false   |

