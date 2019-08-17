package com.vytrack.pages;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    public LogInPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id="prependedInput")
    public WebElement username;

    @FindBy(id="prependedInput2")
    public WebElement password;

    @FindBy(id = "_submit")
    public WebElement submit;


    public void login(String usernameStr, String passwordStr) {
        username.sendKeys(usernameStr);
        password.sendKeys(passwordStr);
        submit.click();
    }

// local change from home

 // this is my change

    
    //esra changed from remote

    //muhabbat changed from intelijj
}
