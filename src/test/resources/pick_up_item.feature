Feature: Player picks up item

  Scenario Outline: Player tries to pick uo item
    Given item is <frozen> and <reachable>
    When player tries to pick up
    Then player has the item is <result>

    Examples:
    | frozen    | reachable   | result  |
    | frozen    | reachable   | false   |
    | unfreezed | reachable   | true    |
    | frozen    | unreachable | false   |
    | unfreezed | unreachable | false   |