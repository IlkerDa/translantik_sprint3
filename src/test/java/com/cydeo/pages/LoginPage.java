package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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



}
