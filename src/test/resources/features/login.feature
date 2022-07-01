
Feature: Default
@wip
  Scenario Outline: Login as different users
    Given the user is on the login page
    When the user logs in using "<username>" and "<password>"
    And mainpage's title contains "<mainPage>"
    Examples:
      | username        | password    | mainPage        |
      | user4           | UserUser123 | Quick Launchpad |
      | storemanager54  | UserUser123 | Dashboard       |
      | salesmanager104 | UserUser123 | Dashboard       |



