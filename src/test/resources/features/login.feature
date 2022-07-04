Feature: User account tests
  As a user, I should be able to login with different users using their correct usernames and passwords.

  Background: Go to login page of Translantik
    Given the user is on the login page

  @ac1
  Scenario Outline: Login as different users

    When the user enters valid credentials for "<userType>"
    And the user lands on "<mainPage>"
    Then the user should see "<breadcrumb>", "<pageHeading>", "<pageURL>", "<pageTitle>" of Dashboard Page properly

    Examples:
      | userType      | mainPage        | breadcrumb            | pageHeading     | pageURL            | pageTitle |
      | driver        | Quick Launchpad |                       | Quick Launchpad | qa.translantik.com | Dashboard |
      | sales manager | Dashboard       | Dashboards/ Dashboard | Dashboard       | qa.translantik.com | Dashboard |
      | store manager | Dashboard       | Dashboards/ Dashboard | Dashboard       | qa.translantik.com | Dashboard |


  @ac2
  Scenario Outline: Repeat login function after logout
    Given the user enters valid credentials for "<userType>"
    And the user copies current URL and log out and paste the same URL to the browser
    Then system shouldn't allow users to access the application
    Examples:
      | userType      |
      | driver        |
      | sales manager |
      | store manager |

  @ac3
  Scenario Outline: Open new tab Closing the Browser without logging out

    When "<userType>" is on the Dashboard Page
    And user copies the current url
    And user closes browser without logging out
    And user opens a new empty tab and pastes the previous url
    Then the user shouldn't able to access application without logging in

    Examples:
      | userType     |
      | driver       |
      #| storemanager |
      #| salesmanager |




  #3- Validate Logging into the Application, closing the Browser without logging out,
  # and opening the application in the new Browser/TAB again(optional)
  #   * After logging into the APP, copy the URL, close the browser,and
  #   then open a new browser. After pasting the URL
  #   we should NOT navigate to the Dashboard Page.(Because of the cookies,
  #   it is possible to navigate to Dashboard Page while manual testing)
  #   * After logging into the App, copy the URL, open a new TAB,
  #   close the previous TAB and then paste the URL.
  #   This time we should see the Dashboard Page.