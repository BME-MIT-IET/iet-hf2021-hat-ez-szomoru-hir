Feature: Unfreeze item

  Scenario Outline: player unfreeze item
    Given item is <frozen> and <reachable>
    When player tries to unfreeze it
    Then the unfreezing was <success>

    Examples:
      | frozen    | reachable   | success        |
      | frozen    | reachable   | successful     |
      | unfreezed | reachable   | unsuccessful     |
      | frozen    | unreachable | unsuccessful   |
      | unfreezed | unreachable | unsuccessful   |
