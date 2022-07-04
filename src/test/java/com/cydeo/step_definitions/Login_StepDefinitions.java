package com.cydeo.step_definitions;

import com.cydeo.pages.LandingPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    LandingPage landingPage = new LandingPage();


    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("the user enters valid credentials for {string}")
    public void the_user_enters_valid_credentials_for(String userType) {
        loginPage.login(userType);
    }

    @And("the user lands on {string}")
    public void theUserLandsOn(String expectedText) {
        BrowserUtils.waitForVisibility(landingPage.pageHeading, 5);
        Assert.assertEquals(expectedText, landingPage.pageHeading.getText());
    }

    @Then("the user should see {string}, {string}, {string}, {string} of Dashboard Page properly")
    public void theUserShouldSeeOfDashboardPageProperly(String breadcrumb, String pageHeading, String url, String title) {
        Assert.assertEquals(breadcrumb, landingPage.breadcrumb.getText());
        Assert.assertEquals(pageHeading, landingPage.pageHeading.getText());
        Assert.assertTrue(BrowserUtils.verifyURLContains(url));
        Assert.assertTrue(Driver.getDriver().getTitle().contains(title));
    }


    @And("the user copies current URL and log out and paste the same URL to the browser")
    public void theUserCopiesCurrentURLAndLogOutAndPasteTheSameURLToTheBrowser() {
        BrowserUtils.waitForClickablility(landingPage.userMenu,10);
        String urlAfterLogin = Driver.getDriver().getCurrentUrl();
        BrowserUtils.waitForClickablility(landingPage.userMenu,10);
        landingPage.logut();
        BrowserUtils.sleep(2);
        Driver.getDriver().get(urlAfterLogin);
    }

    @Then("system shouldn't allow users to access the application")
    public void systemShouldnTAllowUsersToAccessTheApplication() {
        BrowserUtils.sleep(1);
        String expectedURL = ConfigurationReader.getProperty("url");
        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL);
    }


    @When("{string} is on the Dashboard Page")
    public void isOnTheDashboardPage(String userType) {
        loginPage.login(userType);
    }

    @And("user copies the current url")
    public String getTheCurrentUrl() {
        String urlLandingPage = Driver.getDriver().getCurrentUrl();
       return urlLandingPage;
    }
    @And("user closes browser without logging out")
    public void userClosesBrowserWithoutLoggingOut() {
        Driver.closeDriver();
    }

    @And("user opens a new empty tab and pastes the previous url")
    public void userOpensANewEmptyTabAndPastesThePreviousUrl() {
        BrowserUtils.waitForVisibility(loginPage.loginButton,10);
     Driver.getDriver().get(getTheCurrentUrl());
    }

    @Then("the user shouldn't able to access application without logging in")
    public void theUserShouldnTAbleToAccessApplicationWithoutLoggingIn() {
        String oldUrl = getTheCurrentUrl();
        String newUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(!(oldUrl.equals(newUrl)));

    }



}



