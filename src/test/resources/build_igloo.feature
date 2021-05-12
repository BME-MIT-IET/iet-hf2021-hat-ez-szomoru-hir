Feature: Build igloo

  Scenario Outline: Eskimo build igloo on <tile>
    Given Eskimo is on <tile>
    When Eskimo tries to build igloo
    Then tile has igloo is <result>

    Examples:
    | tile  | result  |
    | i     | true    |
    | h     | false   |
    | u     | true    |

  Scenario Outline: Eskimo build igloo on <tile>
    Given Eskimo is on <tile> and it has igloo
    When Eskimo tries to build igloo
    Then it is unsuccessful

    Examples:
      | tile  |
      | i     |
      | u     |

