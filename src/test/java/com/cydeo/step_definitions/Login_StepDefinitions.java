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
    @When("User enters valid credentials for {string} and login")
    public void user_enters_valid_credentials_for_and_login(String userType) {
       loginPage.login(userType);
    }

    @Then("{string} should land on {string} and verify the {string} {string} {string} {string}")
    public void should_land_on_and_verify_the(String string, String string2, String string3, String string4, String string5, String string6) {

    }

}
