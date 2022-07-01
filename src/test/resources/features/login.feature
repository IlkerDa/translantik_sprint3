Feature: User account tests
  As a user, I should be able to login with different users using their correct usernames and passwords.

  @all_accounts
  Scenario Outline: Login as different users
    Given the user is on the login page
    When the user logs in using "<username>" and "<password>" and click
    And the user lands on "<mainPage>"
    Then the user should see "<breadcrumb>", "<pageHeading>", "<pageURL>", "<pageTitle>" of Dashboard Page properly

    Examples:
      | username        | password    | mainPage        | breadcrumb            | pageHeading     | pageURL                     | pageTitle |
      | user4           | UserUser123 | Quick Launchpad |                       | Quick Launchpad | https://qa.translantik.com/ | Dashboard |
      | storemanager54  | UserUser123 | Dashboard       | Dashboards/ Dashboard | Dashboard       | https://qa.translantik.com/ | Dashboard |
      | salesmanager104 | UserUser123 | Dashboard       | Dashboards/ Dashboard | Dashboard       | https://qa.translantik.com/ | Dashboard |
