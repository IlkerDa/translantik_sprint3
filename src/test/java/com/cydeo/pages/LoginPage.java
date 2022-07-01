package com.cydeo.pages;

import com.cydeo.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "prependedInput")
    public WebElement inputUsername;

    @FindBy(id = "prependedInput2")
    public WebElement inputPassword;

    @FindBy(id = "_submit")
    public WebElement loginButton;

    /**
     * This method will accept userType as parameter and
     * enter valid credentials according to userType given
     * @param userType
     */
    public void enteringValidCredentials(String userType) {
        switch (userType.toLowerCase()) {
            case "driver":
                inputUsername.sendKeys("user4");
                inputPassword.sendKeys("UserUser123");
                break;
            case "store manager":
                inputUsername.sendKeys("storemanger54");
                inputPassword.sendKeys("UserUser123");
            case "sales manager":
                inputUsername.sendKeys("salesmanager104");
                inputPassword.sendKeys("UserUser123");

        }





    }

    /**
     * No parameters.
     * When we call this method, it will directly login using
     * <p>
     * Username: user4
     * Password: UserUser123
     */
    public void login(String userType) {
    enteringValidCredentials(userType);
    loginButton.click();
    }

    /**
     * This method will accept two arguments and login.
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        loginButton.click();
    }


}
