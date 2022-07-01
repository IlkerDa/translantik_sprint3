@TRN-1527
Feature: Default

	Background:
		#@TRN-1526
		Given user is on the login page
@TRN-1525
	Scenario Outline: US001-TC01 Verify that user can login with different user types
		Given User is on the Login Page
		    When User enters valid credentials for "<UserType>" and login
		    Then "<UserType>" should land on "<MainPage>" and verify the "Breadcrumb" "PageHeading" "PageURL" "PageTitle"
		    Examples:
		      | UserType     | MainPage        |
		      | Driver       | Quick Launchpad |
		      | Salesmanager | Dashboard       |
		      | Storemanager | Dashboard       |