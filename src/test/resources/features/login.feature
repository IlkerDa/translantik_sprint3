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
      | userType      |
      | driver        |
      | store manager |
      | sales manager |

  @ac4
  Scenario Outline: Validate whether the leading and trailing spaces entered into the Username field are trimmed
    When user as "<userType>" enters trimmed username with correct password
    Then user should log in application
    Examples:
      | userType      |
      | driver        |
      | store manager |
      | sales manager |
  #3- Validate Logging into the Application, closing the Browser without logging out,
  # and opening the application in the new Browser/TAB again(optional)
  #   * After logging into the APP, copy the URL, close the browser,and
  #   then open a new browser. After pasting the URL
  #   we should NOT navigate to the Dashboard Page.(Because of the cookies,
  #   it is possible to navigate to Dashboard Page while manual testing)
  #   * After logging into the App, copy the URL, open a new TAB,
  #   close the previous TAB and then paste the URL.
  #   This time we should see the Dashboard Page.
  @ac5
  Scenario Outline: Validate all the fields in the Login page have the proper placeholders (Username or Email and Password)
    Then Verify the "<Username>" and "<Password>" placeholders are present
    Examples:
      | Username          | Password |
      | Username or Email | Password |

  @ac6_1
  Scenario Outline: Warning Messages should be displayed
    When user enters invalid credentials "<Username>" and "<Password>"
    Then "<Warning Messages>" should be displayed.

    Examples:
      | Username | Password    | Warning Messages               |
      | user4444 | UserUser123 | Invalid user name or password. |
      | user4    | UserUser    | Invalid user name or password. |

  @ac6_2
  Scenario Outline: Leave fields as empty
    When user enters empty credentials in placeholder of "<Username>" and or "<Password>"
    Then "<Warning Messages>" should be displayed in the empty field

    Examples:
      | Username | Password    | Warning Messages            |
      |          | UserUser123 | Please fill out this field. |
      | user4    |             | Please fill out this field. |
      |          |             | Please fill out this field. |

  @ac7
  Scenario: the 'Password' field is toggled to hide its visibility
    When user enters valid credentials "user4" and "UserUser123"
    Then the password field is toggled to hide its visibility

  @ac8
  Scenario Outline: Password is not visible in the Page Source
    Given user enters valid credentials for "<userType>" without login
    Then password should not be displayed in the page source

    Examples:
      | userType      |
      | driver        |
      | sales manager |
      | store manager |

  @ac09
  Scenario: the copying of the text entered into the Password field
    Then user shouldn't copy the password

  @ac10
  Scenario: "Forgot Password" menu
    When user clicks Forgot your password link
    And user lands on "https://qa.translantik.com/user/reset-request" page
    Then user changed own password as "user4"

  @ac11
  Scenario: the "Remember me on this computer" link on the login page
    When user sees "Remember me on this computer" link
    Then the link should be clickable

  @ac12
  Scenario Outline: login by using the ENTER button

    When user enters a valid "<username>" into username placeholder
    And user hits ENTER button after entering username
    And user enters a valid "<password>" into password placeholder
    And user hits ENTER button after entering password
    Then user should land on Homepage "<pageTitle>"
    Examples:
      | username        | password    | pageTitle |
      | user4           | UserUser123 | Dashboard |
      #| storemanager54  | UserUser123 | Dashboard |
      #| salesmanager104 | UserUser123 | Dashboard |