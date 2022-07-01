package com.cydeo.step_definitions;

import com.cydeo.pages.LoginPage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_StepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("the user enters valid credentials for each {string}")
    public void the_user_enters_valid_credentials_for_each(String userType) {
      loginPage.enteringValidCredentials(userType);
    }

    @When("the user clicks login button")
    public void the_user_clicks_login_button() {
        loginPage.loginButton.click();

    }
    @Then("the page subtitle is {string}")
    public void the_page_subtitle_is(String string) {

    }

}
