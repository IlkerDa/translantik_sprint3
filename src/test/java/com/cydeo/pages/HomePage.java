package com.cydeo.pages;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(className = "oro-subtitle")
    public WebElement pageHeading;

    @FindBy(id = "breadcrumb")
    public WebElement breadcrumb;

    @FindBy(xpath = "//li[@id='user-menu']/a")
    public WebElement fullName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;



    public void logout(){
        BrowserUtils.waitFor(2);
        BrowserUtils.clickWithJS(fullName);
        BrowserUtils.waitFor(2);
        BrowserUtils.clickWithJS(logOutLink);
    }











}
