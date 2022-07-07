package com.cydeo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage{
    @FindBy(id = "prependedInput")
    public WebElement forgotPasswordBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement requestButton;

    @FindBy(className = "alert_alert-error")
    public WebElement alertMessage;
}
