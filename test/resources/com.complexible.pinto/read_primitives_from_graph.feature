Feature: Read primitives from graph

  Scenario Outline: read primitives from graph file
    Given graph is in "<file>"
    When I ask for the graph
    Then I should be told
    | mInt  | mFloat  | mDouble | mChar | mString   | URI     |
    | 8     | 4.5     | 20.22   | o     | str value | urn:any |

    Examples:
    | file |
    | primitives.nt |