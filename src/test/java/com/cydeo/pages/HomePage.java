package com.cydeo.pages;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(className = "oro-subtitle")
    public WebElement pageHeading;

    @FindBy(id = "breadcrumb")
    public WebElement breadcrumb;

    @FindBy(xpath = "//li[@id='user-menu']/a")
    public WebElement userMenu;

    @FindBy(linkText = "Logout")
    public WebElement logutButton;

   /* @FindBy(id = "user-menu")
    public WebElement userMenu;

    @FindBy(partialLinkText = "logout")
    public WebElement logutButton;

    */
// <a href="/user/logout" class="no-hash">Logout</a>



public void logut(){
    BrowserUtils.waitForClickablility(userMenu,10);
    userMenu.click();
    BrowserUtils.waitForClickablility(logutButton,10);
    logutButton.click();
    BrowserUtils.sleep(1);
}











}
