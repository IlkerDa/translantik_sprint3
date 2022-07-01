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
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_StepDefinitions {
    LoginPage loginPage= new LoginPage();
    LandingPage landingPage =new LandingPage();


    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("the user logs in using {string} and {string} and click")
    public void the_user_logs_in_using_and_and_click(String username, String password) {
       loginPage.login(username,password);
    }

    @And("the user lands on {string}")
    public void theUserLandsOn(String expectedText) {
        BrowserUtils.waitForVisibility(landingPage.pageHeading, 5);
        Assert.assertEquals(expectedText, landingPage.pageHeading.getText());
    }

    @Then("the user should see {string}, {string}, {string}, {string} of Dashboard Page properly")
    public void theUserShouldSeeOfDashboardPageProperly(String breadcrumb, String pageHeading, String url, String title) {
        Assert.assertEquals(breadcrumb,landingPage.breadcrumb.getText());
        Assert.assertEquals(pageHeading,landingPage.pageHeading.getText() );
        Assert.assertEquals(url, Driver.getDriver().getCurrentUrl());
        Assert.assertEquals(title, Driver.getDriver().getTitle());
    }
}



