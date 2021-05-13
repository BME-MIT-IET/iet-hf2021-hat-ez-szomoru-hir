Feature: Scientist scans the limit of neighbor tile

  Scenario Outline: scientist scan tile
    Given tile is type <tile_type>
    When scientist scan tile
    Then it should return <limit>

    Examples:
    | tile_type | limit |
    | i         | -1    |
    | h         | 0     |

  Scenario Outline: scientist scan tile
    Given tile is unstable and has <limit> limit
    When scientist scan tile
    Then it should return <limit>

    Examples:
      | limit |
      | 2     |
      | 5     |