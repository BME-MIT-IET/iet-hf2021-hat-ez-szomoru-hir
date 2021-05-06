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

  Scenario Outline: read list of primitives from graph file
    Given graph is in "<file>"
    When I ask for the graph with lists
    Then I should get these lists
      | mInts  | mFloats  | mDoubles | mBools         |
      | 4,5    | 8,20     | 22,33    | true,false     |

  Examples:
    | file |
    | primitive_lists.nt |