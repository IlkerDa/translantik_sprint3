@TRN-1527
Feature: Login Functionality

	Background:
		#@TRN-1526
		Given user is on the login page

	@TRN-1525
	Scenario Outline: US001-TC01 Verify that user can login with different user types
		When the user enters valid credentials for each "<userType>"
		And the user clicks login button
		Then the page subtitle is "<subtitle>"
		    Examples:
		      | userType      | subtitle        |
		      | driver        | Quick Launchpad |
		      | store manager | Dashboard       |
		      | sales manager | Dashboard       |