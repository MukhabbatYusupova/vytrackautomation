package com.vytrack.pages;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCalEventPage {

    public CreateCalEventPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css="input[data-related-field=interval]")
    public WebElement repeatEvery;

    @FindBy(xpath = "//input[@type='checkbox'][@data-name='recurrence-repeat']")
    public WebElement repeatCheckbox;

    @FindBy(css="div[class$='controls'][data-name='recurrence-summary']")
    public WebElement summary;

    @FindBy(xpath="//input[@type='radio'])[1]")
    public WebElement dailyCheck;

    @FindBy(xpath="//input[@type='radio'])[2]")
    public WebElement weeklyCheck;


    @FindBy(xpath="(//input[@type='radio'])[4]")
    public WebElement afterCheck;

    @FindBy(css = "span[id^=temp-validation]")
    public WebElement errorMsg;

    @FindBy(css = "span[id^=temp-validation]")
    public WebElement errorMsg2;
    @FindBy(css = "input[data-related-field='occurrences']")
    public WebElement occurences;

}
