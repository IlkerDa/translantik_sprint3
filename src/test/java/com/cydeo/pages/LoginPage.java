package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "prependedInput")
    public WebElement inputUsername;

    @FindBy(id = "prependedInput2")
    public WebElement inputPassword;

    @FindBy(id = "_submit")
    public WebElement loginButton;

    @FindBy(xpath = "//input[@id='prependedInput']")
    public WebElement placeholderUsername;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement placeholderPassword;

    @FindBy(xpath = "//div[text()='Invalid user name or password.']")
    public WebElement warningMessage;

    @FindBy(xpath = "//a[.='Forgot your password?']")
    public WebElement forgotPasswordLink;

    @FindBy(css = ".custom-checkbox__text")
    public WebElement rememberMeLink;

      
    @FindBy(css = "div[class='loader-mask shown']")
    @CacheLookup
    protected WebElement loaderMask;

    @FindBy(css = "#user-menu > a")
    public WebElement userName;











    /**
     * This method will accept two arguments and login.
     *
     * @param userType

     */
    public void login(String userType) {
        if(userType.equalsIgnoreCase("driver")){
            inputUsername.sendKeys(ConfigurationReader.getProperty("driver_username"));
            inputPassword.sendKeys(ConfigurationReader.getProperty("sales_manager_password"));
        }else if (userType.equalsIgnoreCase("sales manager")){
            inputUsername.sendKeys(ConfigurationReader.getProperty("sales_manager_username"));
            inputPassword.sendKeys(ConfigurationReader.getProperty("sales_manager_password"));
        }else if (userType.equalsIgnoreCase("store manager")){
            inputUsername.sendKeys(ConfigurationReader.getProperty("store_manager_username"));
            inputPassword.sendKeys(ConfigurationReader.getProperty("store_manager_password"));
        }
        loginButton.click();
        waitUntilLoaderScreenDisappear();
    }

    public void enteringValidCredentialsWithoutSubmitting(String userType){
        if(userType.equalsIgnoreCase("driver")){
            inputUsername.sendKeys(ConfigurationReader.getProperty("driver_username"));
            inputPassword.sendKeys(ConfigurationReader.getProperty("sales_manager_password"));
        }else if (userType.equalsIgnoreCase("sales manager")){
            inputUsername.sendKeys(ConfigurationReader.getProperty("sales_manager_username"));
            inputPassword.sendKeys(ConfigurationReader.getProperty("sales_manager_password"));
        }else if (userType.equalsIgnoreCase("store manager")){
            inputUsername.sendKeys(ConfigurationReader.getProperty("store_manager_username"));
            inputPassword.sendKeys(ConfigurationReader.getProperty("store_manager_password"));
        }

    }
    public void login(String Username, String Password){
        inputUsername.sendKeys(Username);
        inputPassword.sendKeys(Password);
        loginButton.click();
        waitUntilLoaderScreenDisappear();
    }

    public void loginWithLeadingAndTrailingSpacesUsername(String userType) {
        if(userType.equalsIgnoreCase("driver")) {
            inputUsername.sendKeys("     "+ConfigurationReader.getProperty("driver_username")+"     ");

            inputPassword.sendKeys(ConfigurationReader.getProperty("sales_manager_password"));
        }else if (userType.equalsIgnoreCase("sales manager")){
            inputUsername.sendKeys("     "+ConfigurationReader.getProperty("sales_manager_username")+"     ");
            inputPassword.sendKeys(ConfigurationReader.getProperty("sales_manager_password"));
        }else if (userType.equalsIgnoreCase("store manager")){
            inputUsername.sendKeys("     "+ConfigurationReader.getProperty("store_manager_username")+"     ");
            inputPassword.sendKeys(ConfigurationReader.getProperty("store_manager_password"));
        }
        loginButton.click();
        waitUntilLoaderScreenDisappear();
    }

    public void waitUntilLoaderScreenDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
