
@api
Feature: API-tests

  Scenario:
    When sent get request about existed boards
    Then the response is insert into list
    Then check the result

  Scenario Outline: checking a new board creating and increasing of the count of boards by 1 after that
    When sent the get request about existed board names
    Then get count of boards and check that number > 0
    Then the post request to create a new board with name=<boardName>
    Then sent the new get request about existed board names
    And get new count of boards
    And get new boards name list
    And check that the count of pages is changed by 1 and that the board with name=<boardName> is exist

    Examples:
      | boardName   |
      | NewApiBoard |
