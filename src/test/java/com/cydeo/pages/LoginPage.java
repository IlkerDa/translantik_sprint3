package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

   // <input type="password" id="prependedInput2" class="span2" name="_password" required="required" placeholder="Password" autocomplete="off">


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
    }

}
