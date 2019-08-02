package com.vytrack.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class VytrackUtils {

    public static void login(WebDriver driver, String username, String password){
        driver.findElement(By.id("prependedInput")).
                sendKeys(username);

        driver.findElement(By.id("prependedInput2")).
                sendKeys(password+ Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs("Dashboard"));

    }

    public static void selectMenuOption(WebDriver driver, String tab, String module)  {
        String tabXpath = "//span[@class='title title-level-1' and contains(text(), '"+tab+"')]";
        WebElement tabEl = driver.findElement(By.xpath(tabXpath));
        BrowserUtils.hover(tabEl);

        String moduleXpath = "//span[@class='title title-level-2' and contains(text(), '"+module+"')]";
        WebElement moduleEl = driver.findElement(By.xpath(moduleXpath));
        BrowserUtils.waitForClickablility(moduleEl, 10).click();

       // WebDriverWait wait = new WebDriverWait(driver, 5);
       //wait.until(ExpectedConditions.titleContains(module));
    }


    public static void waitForUIOverlay(){
        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loader-mask.shown")));
    }
}
