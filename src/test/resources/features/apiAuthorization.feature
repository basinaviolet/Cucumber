
@api
Feature: API-tests

  Scenario:
    When sent the get request about user info
    Then the response is insert into pojo class
    And match the result with real user info

