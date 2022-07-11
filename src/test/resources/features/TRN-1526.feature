@TRN-1527
Feature: Default

  Background:
		#@TRN-1526
    Given user is on the login page

  @TRN-1525
  Scenario Outline: US001-AC01-TC01 (Ilker) Login as different users with valid credentials
    When user enters valid credentials for "<userType>"
    And user lands on "<mainPage>"
    Then user should see "<breadcrumb>", "<pageHeading>", "<pageURL>", "<pageTitle>" of Dashboard Page properly

    Examples:
      | userType      | mainPage        | breadcrumb            | pageHeading     | pageURL            | pageTitle |
      | driver        | Quick Launchpad |                       | Quick Launchpad | qa.translantik.com | Dashboard |
      | sales manager | Dashboard       | Dashboards/ Dashboard | Dashboard       | qa.translantik.com | Dashboard |
      | store manager | Dashboard       | Dashboards/ Dashboard | Dashboard       | qa.translantik.com | Dashboard |

  @TRN-1536
  Scenario Outline: US001-AC02-TC02 (Ilker) Verify no access to application after logout without credentials
    When user enters valid credentials for "<userType>"
    And user copies current URL and log out and paste the same URL to the browser
    Then system shouldn't allow users to access the application
    Examples:
      | userType      |
      | driver        |
      | sales manager |
      | store manager |

  @TRN-1545
  Scenario Outline: US001-AC03-TC03 (Ilker) Verify logging into the Application after closing the Browser without logging out
    When "<userType>" is on the Dashboard Page
    And user copies the current url
    And user closes browser without logging out
    And user opens a new empty tab and pastes the previous url
    Then user shouldn't able to access application without logging in

    Examples:
      | userType      |
      | driver        |
      | store manager |
      | sales manager |

  @TRN-1546
  Scenario Outline: US001-AC04-TC04 (Ilker) Verify that the leading and trailing spaces entered into the Username field are trimmed
    When user as "<userType>" enters trimmed username with correct password
    Then user should log in application
    Examples:
      | userType      |
      | driver        |
      | store manager |
      | sales manager |

  @TRN-1547
  Scenario Outline: US001-AC05-TC05 (Ilker) Verify that all the fields in the login page have the proper placeholders
    Then the "<Username>" and "<Password>" placeholders should be present
    Examples:
      | Username          | Password |
      | Username or Email | Password |

  @TRN-1548
  Scenario Outline: US001-AC06-TC06 (Ilker) Verify that "Invalid username or password." message should be displayed for invalid credentials
    When user enters invalid credentials "<Username>" and "<Password>"
    Then "<Warning Messages>" should be displayed.

    Examples:
      | Username | Password    | Warning Messages               |
      | user4444 | UserUser123 | Invalid user name or password. |
      | user4    | UserUser    | Invalid user name or password. |
      | user4444 | UserUser    | Invalid user name or password. |


  @TRN-1549
  Scenario Outline: US001-AC06-TC07 (Ilker) Verify that "Please fill out this field." message should be displayed for any empty field
    When user enters empty credentials in placeholder of "<Username>" and or "<Password>"
    Then "<Warning Messages>" should be displayed in the empty field

    Examples:
      | Username | Password    | Warning Messages            |
      |          | UserUser123 | Please fill out this field. |
      | user4    |             | Please fill out this field. |
      |          |             | Please fill out this field. |

  @TRN-1550
  Scenario: US001-AC07-TC08 (Ilker) Verify that the Password text entered into the 'Password' field is toggled to hide its visibility
    When user enters valid credentials "user4" and "UserUser123"
    Then the password field is toggled to hide its visibility

  @TRN-1551
  Scenario Outline: US001-AC08-TC09 (Ilker) Verify that Password is not visible in the Page Source
    When user enters valid credentials for "<userType>" without login
    Then password should not be displayed in the page source

    Examples:
      | userType      |
      | driver        |
      | sales manager |
      | store manager |

  @TRN-1552
  Scenario: US001-AC09-TC10 (Ilker) Verify that the copying of the text can not be entered into the Password field
    When user enters valid credentials "user4" and "UserUser123"
    Then user shouldn't copy the password

  @TRN-1562
  Scenario: US001-AC10-TC11 (Ilker) Verify that user can change their password by using username
    When user clicks Forgot your password link
    And user lands on "https://qa.translantik.com/user/reset-request" page
    Then user changed own password as "user4"

  @TRN-1563
  Scenario: US001-AC11-TC12 (Ilker) Verify user can see the "Remember me on this computer" link on the login page and it should be clickable
    When user sees "Remember me on this computer" link
    Then the link should be clickable

  @TRN-1603
  Scenario Outline: US001-AC12-TC13 (Ilker) Verify login by using ENTER button on the keyboard
    When user enters a valid "<username>" into username placeholder
    And user hits ENTER button after entering username
    And user enters a valid "<password>" into password placeholder
    And user hits ENTER button after entering password
    Then user should land on Homepage "<pageTitle>"

    Examples:
      | username        | password    | pageTitle |
      | user4           | UserUser123 | Dashboard |
      | storemanager54  | UserUser123 | Dashboard |
      | salesmanager104 | UserUser123 | Dashboard |

  @TRN-1604
  Scenario Outline: US001-AC12-TC14 (Ilker) Verify login by using TABbutton on the keyboard
    When user enters a valid "<username>" into username placeholder
    And user hits TAB button after entering username
    And user enters a valid "<password>" into password placeholder
    And user hits ENTER button after entering password
    Then user should land on Homepage "<pageTitle>"

    Examples:
      | username        | password    | pageTitle |
      | user4           | UserUser123 | Dashboard |
      | storemanager54  | UserUser123 | Dashboard |
      | salesmanager104 | UserUser123 | Dashboard |

  @TRN-1606
  Scenario: US001-AC13-TC15 (Ilker) Verify background color  of "LOG IN" ** button (hex code #0c84a3)
    Then hex code of the background color of the log in button should be "#0c84a3"