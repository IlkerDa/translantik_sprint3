package com.cydeo.step_definitions;

import com.cydeo.pages.BasePage;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class Login_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);


    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("user enters valid credentials for {string}")
    public void user_enters_valid_credentials_for(String userType) {
        loginPage.login(userType);

    }

    @And("user lands on {string}")
    public void theUserLandsOn(String expectedText) {
        BrowserUtils.waitForVisibility(homePage.pageHeading, 5);
        Assert.assertEquals(expectedText, homePage.pageHeading.getText());
    }

    @Then("user should see {string}, {string}, {string}, {string} of Dashboard Page properly")
    public void theUserShouldSeeOfDashboardPageProperly(String breadcrumb, String pageHeading, String url, String title) {
        Assert.assertEquals(breadcrumb, homePage.breadcrumb.getText());
        Assert.assertEquals(pageHeading, homePage.pageHeading.getText());
        Assert.assertTrue(BrowserUtils.verifyURLContains(url));
        Assert.assertTrue(Driver.getDriver().getTitle().contains(title));
    }


    @And("user copies current URL and log out and paste the same URL to the browser")
    public void theUserCopiesCurrentURLAndLogOutAndPasteTheSameURLToTheBrowser() {
        BrowserUtils.waitFor(2);
        String urlAfterLogin = Driver.getDriver().getCurrentUrl();
        BrowserUtils.waitFor(2);
        homePage.logout();
        BrowserUtils.waitFor(2);
        Driver.getDriver().get(urlAfterLogin);
    }

    @Then("system shouldn't allow users to access the application")
    public void systemShouldnTAllowUsersToAccessTheApplication() {
        String expectedURL = ConfigurationReader.getProperty("url");
        BrowserUtils.sleep(2);
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

        //Open new tab
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.open();");

        //handling multiple tabs with an ArrayList
        ArrayList<String> windowHandles = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(windowHandles.get(0));
        Driver.getDriver().close();
        Driver.getDriver().switchTo().window(windowHandles.get(1));
        Driver.getDriver().get(getTheCurrentUrl());
    }

    @Then("user shouldn't able to access application without logging in")
    public void userShouldnTAbleToAccessApplicationWithoutLoggingIn() {
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

    @Then("the {string} and {string} placeholders should be present")
    public void theAndPlaceholdersShouldBePresent(String Username, String Password) {
        String placeholderUsernameText = loginPage.placeholderUsername.getAttribute("placeholder");
        String placeholderPasswordText = loginPage.placeholderPassword.getAttribute("placeholder");
        Assert.assertEquals(Username, placeholderUsernameText);
        Assert.assertEquals(Password, placeholderPasswordText);
        Assert.assertTrue(loginPage.placeholderUsername.isDisplayed());
        Assert.assertTrue(loginPage.placeholderPassword.isDisplayed());
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
                || messageInInputBox.isEmpty() || messageInPwdBox.isEmpty();
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
        System.out.println(localClipboardData);
        Assert.assertNotEquals("UserUser123", localClipboardData);
    }

    @When("user clicks Forgot your password link")
    public void userClicksForgotYourPasswordLink() {
        loginPage.forgotPasswordLink.click();
        BrowserUtils.waitForVisibility(forgotPasswordPage.forgotPasswordBox, 3);
    }

    @And("user lands on {string} page")
    public void userLandsOnPage(String expectedURL) {
        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL);
    }

    @Then("user changed own password as {string}")
    public void userChangedOwnPasswordAs(String username) {
        forgotPasswordPage.forgotPasswordBox.sendKeys(username);
        forgotPasswordPage.requestButton.click();
        BrowserUtils.waitFor(2);
        String errorMessage1 = "Unable to send the email.";
        String errorMessage2 = "There is no active user with username or email address";
        String actualMessage = forgotPasswordPage.alertMessage.getText();
        System.out.println(actualMessage);
        Assert.assertTrue(!(actualMessage.contains(errorMessage1)||actualMessage.contains(errorMessage2)));
    }
    @When("user sees {string} link")
    public void userSeesLink(String expectedText) {
        String actualText= loginPage.rememberMeLink.getText();
        Assert.assertEquals(expectedText,actualText);
    }



    @Then("the link should be clickable")
    public void theLinkShouldBeClickable() {
       BrowserUtils.clickWithJS(loginPage.rememberMeLink);
       BrowserUtils.sleep(5);
       boolean isClicked = BrowserUtils.isClicked(loginPage.rememberMeLink);
       Assert.assertTrue(isClicked);
    }


    @When("user enters a valid {string} into username placeholder")
    public void userEntersAValidIntoUsernamePlaceholder(String username) {
        loginPage.inputUsername.click();
        loginPage.inputUsername.sendKeys(username);
        BrowserUtils.sleep(2);
    }

    @And("user hits ENTER button after entering username")
    public void userHitsENTERButtonAfterEnteringUsername() {
        loginPage.inputUsername.sendKeys(Keys.ENTER);
    }

    @And("user enters a valid {string} into password placeholder")
    public void userEntersAValidIntoPasswordPlaceholder(String password) {
        loginPage.inputPassword.sendKeys(password);
        BrowserUtils.sleep(2);
    }

    @And("user hits ENTER button after entering password")
    public void userHitsENTERButtonAfterEnteringPassword() {
        loginPage.inputPassword.sendKeys(Keys.ENTER);
        BrowserUtils.sleep(5);
    }

    @Then("user should land on Homepage {string}")
    public void userShouldLandOnHomepage(String expectedPageTitle) {
        BrowserUtils.verifyTitle(expectedPageTitle);
    }

    @And("user hits TAB button after entering username")
    public void userHitsTABButtonAfterEnteringUsername() {
        loginPage.inputUsername.sendKeys(Keys.TAB);
    }


    @Then("hex code of the background color of the log in button should be {string}")
    public void hexCodeOfTheBackgroundColorOfTheLogInButtonShouldBe(String expectedHexCode) {
        String s = loginPage.loginButton.getCssValue("background-color");
        String c = Color.fromString(s).asHex();
        System.out.println("Color is :" + s);
        System.out.println("Hex code for color:" + c);
        Assert.assertEquals(expectedHexCode, c);
    }



}



