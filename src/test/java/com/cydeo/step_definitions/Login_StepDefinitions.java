package com.cydeo.step_definitions;

import com.cydeo.pages.ForgotPasswordPage;
import com.cydeo.pages.HomePage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Login_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();


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
        BrowserUtils.waitForVisibility(homePage.pageHeading, 5);
        Assert.assertEquals(expectedText, homePage.pageHeading.getText());
    }

    @Then("the user should see {string}, {string}, {string}, {string} of Dashboard Page properly")
    public void theUserShouldSeeOfDashboardPageProperly(String breadcrumb, String pageHeading, String url, String title) {
        Assert.assertEquals(breadcrumb, homePage.breadcrumb.getText());
        Assert.assertEquals(pageHeading, homePage.pageHeading.getText());
        Assert.assertTrue(BrowserUtils.verifyURLContains(url));
        Assert.assertTrue(Driver.getDriver().getTitle().contains(title));
    }


    @And("the user copies current URL and log out and paste the same URL to the browser")
    public void theUserCopiesCurrentURLAndLogOutAndPasteTheSameURLToTheBrowser() {
        BrowserUtils.waitForClickablility(homePage.userMenu, 10);
        String urlAfterLogin = Driver.getDriver().getCurrentUrl();
        BrowserUtils.waitForClickablility(homePage.userMenu, 10);
        homePage.logut();
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
        String urlHomePage = Driver.getDriver().getCurrentUrl();
        return urlHomePage;
    }

    @And("user closes browser without logging out")
    public void userClosesBrowserWithoutLoggingOut() {
        Driver.closeDriver();
        BrowserUtils.sleep(2);
    }

    @And("user opens a new empty tab and pastes the previous url")
    public void userOpensANewEmptyTabAndPastesThePreviousUrl() {

        Driver.getDriver().get(getTheCurrentUrl());
    }

    @Then("the user shouldn't able to access application without logging in")
    public void theUserShouldnTAbleToAccessApplicationWithoutLoggingIn() {
        String oldUrl = ConfigurationReader.getProperty("url_after_login");
        String newUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertFalse(oldUrl.equals(newUrl));

    }


    @When("user as {string} enters trimmed username with correct password")
    public void userAsEntersTrimmedUsernameWithCorrectPassword(String userType) {
        loginPage.loginWithLeadingAndTrailingSpacesUsername(userType);
    }

    @Then("user should log in application")
    public void userShouldLogInApplication() {
        String oldUrl = ConfigurationReader.getProperty("url_after_login");
        String newUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(oldUrl.equals(newUrl));
    }

    @Then("Verify the {string} and {string} placeholders are present")
    public void verifyTheAndPlaceholdersArePresent(String Username, String Password) {
        String placeholderUsernameText = loginPage.placeholderUsername.getAttribute("placeholder");
        String placeholderPasswordText = loginPage.placeholderPassword.getAttribute("placeholder");
        Assert.assertEquals(Username, placeholderUsernameText);
        Assert.assertEquals(Password, placeholderPasswordText);
    }

    @When("user enters invalid credentials {string} and {string}")
    public void userEntersInvalidCredentialsAnd(String Username, String Password) {
        loginPage.login(Username, Password);

    }

    @Then("{string} should be displayed.")
    public void shouldBeDisplayed(String WarningMessage) {
        String actualWarningMessageText = loginPage.warningMessage.getText();
        Assert.assertEquals(WarningMessage, actualWarningMessageText);
    }


    @When("user enters empty credentials in placeholder of {string} and or {string}")
    public void userEntersEmptyCredentials(String Username, String Password) {
        loginPage.login(Username, Password);
    }

    @Then("{string} should be displayed in the empty field")
    public void shouldBeDisplayedInTheEmptyField(String expectedMessage) {
        BrowserUtils.sleep(1);
        String messageInInputBox = loginPage.placeholderUsername.getAttribute("validationMessage");
        String messageInPwdBox = loginPage.placeholderPassword.getAttribute("validationMessage");
        boolean isMessageDisplayed = (messageInPwdBox.equals(expectedMessage) || messageInPwdBox.equals(expectedMessage))
                ||messageInInputBox.isEmpty()||messageInPwdBox.isEmpty();
        Assert.assertTrue(isMessageDisplayed);
    }

    @When("user enters valid credentials {string} and {string}")
    public void userEntersValidCredentialsAnd(String username, String password) {
        loginPage.inputUsername.sendKeys(username);
        loginPage.inputPassword.sendKeys(password);
    }

    @Then("the password field is toggled to hide its visibility")
    public void thePasswordFieldIsToggledToHideItsVisibility() {
        String actualTypeAttributeText = loginPage.placeholderPassword.getAttribute("type");
        String expectedTypeAttributeText = "password";
        Assert.assertEquals(expectedTypeAttributeText, actualTypeAttributeText);
    }

    @Given("user enters valid credentials for {string} without login")
    public void userEntersValidCredentialsForWithoutLogin(String userType) {
        loginPage.enteringValidCredentialsWithoutSubmitting(userType);
    }

    @Then("password should not be displayed in the page source")
    public void passwordShouldNotBeDisplayedInThePageSource() {
        String pageSource = Driver.getDriver().getPageSource();
        boolean isDisplayed = pageSource.contains(ConfigurationReader.getProperty("driver_password"));
        Assert.assertFalse(isDisplayed);
    }

    @Then("user shouldn't copy the password")
    public void userShouldnTCopyThePassword() throws IOException, UnsupportedFlavorException {
        loginPage.inputPassword.sendKeys("UserUser123");
        loginPage.placeholderPassword.sendKeys(Keys.chord(Keys.CONTROL, "A"));
        loginPage.placeholderPassword.sendKeys(Keys.chord(Keys.CONTROL, "C"));
        String localClipboardData = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        Assert.assertNotEquals("UserUser123", localClipboardData);
    }

    @When("user clicks Forgot your password link")
    public void userClicksForgotYourPasswordLink() {
        loginPage.forgotPasswordLink.click();
        BrowserUtils.waitForVisibility(forgotPasswordPage.forgotPasswordBox,3);
    }

    @And("user lands on {string} page")
    public void userLandsOnPage(String expectedURL) {
       String actualURL=Driver.getDriver().getCurrentUrl();
       Assert.assertEquals(expectedURL,actualURL);
    }

    @Then("user changed own password as {string}")
    public void userChangedOwnPasswordAs(String username) {
        forgotPasswordPage.forgotPasswordBox.sendKeys(username);
        forgotPasswordPage.requestButton.click();
        Assert.assertNotNull(forgotPasswordPage.alertMessage);
    }




    @When("user sees {string} link")
    public void userSeesLink(String link) {
        Assert.assertEquals(link, loginPage.rememberMeLink.getText());
        System.out.println("loginPage.rememberMeLink.getText() = " + loginPage.rememberMeLink.getText());
    }

    @Then("the link should be clickable")
    public void theLinkShouldBeClickable() {
        BrowserUtils.sleep(2);
        loginPage.rememberMeLink.click();
        BrowserUtils.sleep(2);
        Assert.assertTrue(loginPage.rememberMeLink.isEnabled());
    }


    @When("user enters a valid {string} into username placeholder")
    public void userEntersAValidIntoUsernamePlaceholder(String username) {
        loginPage.inputUsername.sendKeys(username);
    }

    @And("user hits ENTER button after entering username")
    public void userHitsENTERButtonAfterEnteringUsername() {
        // loginPage.inputUsername.sendKeys(Keys.ENTER);
    }

    @And("user enters a valid {string} into password placeholder")
    public void userEntersAValidIntoPasswordPlaceholder(String password) {
       // loginPage.inputPassword.sendKeys(password);
    }

    @And("user hits ENTER button after entering password")
    public void userHitsENTERButtonAfterEnteringPassword() {
        //loginPage.inputPassword.sendKeys(Keys.ENTER);
    }

    @Then("user should land on Homepage {string}")
    public void userShouldLandOnHomepage(String expectedPageTitle) {
        //BrowserUtils.verifyTitle(expectedPageTitle);
    }
}



