package com.cydeo.step_definitions;

import com.cydeo.pages.LoginPage;
import com.cydeo.pages.MainPage;
import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_StepDefinitions {
    LoginPage loginPage= new LoginPage();


    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {

    }
    @When("the user logs in using {string} and {string}")
    public void the_user_logs_in_using_and(String string, String string2) {

    }
    @When("mainpage's title contains {string}")
    public void mainpage_s_title_contains(String string) {

    }

}



