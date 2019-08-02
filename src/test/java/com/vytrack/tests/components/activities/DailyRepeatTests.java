package com.vytrack.tests.components.activities;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static com.vytrack.utilities.BrowserUtils.*;
import static com.vytrack.utilities.VytrackUtils.*;

public class DailyRepeatTests extends TestBase {


    @Test (description = "Base Test")
    public void test0(){
        //TODO login
        login(driver, ConfigurationReader.get("truckDriver"), ConfigurationReader.get("password") );
        selectMenuOption(driver, "Activities", "Calendar Events");
        //TODO create Calendar Event
        waitForClickablility(driver.findElement(By.linkText("Create Calendar Event")),10);
        clickWithJS(driver.findElement(By.linkText("Create Calendar Event")));
        //TODO click on REPEAT
        WebElement click = driver.findElement(By.xpath("//input[@type='checkbox'][@data-name='recurrence-repeat']"));
        waitForVisibility(click, 10);
        clickWithJS(click);
    }

    @Test(description ="Daily Repeat option, Repeat every, summary")
    public void test1(){
        test0();

        //TODO Assertion

        //Assert.assertEquals(driver.findElement(By.id("uniform-recurrence-repeats-view101")).getAttribute("value"), "Daily");

        clickWithJS(driver.findElement(By.xpath("(//input[@type='radio'])[2]")));
        Assert.assertTrue(driver.findElement(By.xpath("(//input[@type='radio'])[2]")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("(//input[@type='radio'])[1]")).isSelected());

        Assert.assertEquals(driver.findElement(By.cssSelector("div[class$='controls'][data-name='recurrence-summary']")).getText(),
                "Daily, every weekday");
    }


    @Test (description = "Daily repeat, Repeat every, default values")
    public void test2(){
        test0();

//TODO verify Daily is default


        Assert.assertTrue(driver.findElement(By.xpath("(//input[@type='radio'])[1]")).isSelected());
    Assert.assertEquals(driver.findElement(By.cssSelector("input[data-related-field=interval]")).getAttribute("value"), "1");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class$='controls'][data-name='recurrence-summary']")).getText(),
                        "Daily every 1 day");
    }

@Test (description = "Daily repeat option, repeat every day(s), error message")
    public void test3() {
    test0();

    driver.findElement(By.cssSelector("input[data-related-field=interval]")).sendKeys(Keys.BACK_SPACE + "0" + Keys.ENTER);
    System.out.println(driver.findElement(By.cssSelector("input[data-related-field=interval]")).getAttribute("value"));

    waitForVisibility(driver.findElement(By.cssSelector("span[class=validation-failed]")), 10);
    System.out.println((driver.findElement(By.cssSelector("span[class=validation-failed]")).getText()));
    System.out.println("Thevalue: "+driver.findElement(By.cssSelector("span[class=validation-failed]")).getText());
   Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(), 'The value have not')]")).getText(),
           "The value have not to be less than 1.");

    driver.findElement(By.cssSelector("input[data-related-field=interval]")).sendKeys(Keys.BACK_SPACE + "100" + Keys.ENTER);
    waitForVisibility(driver.findElement(By.cssSelector("span[class=validation-failed]")), 10);
    System.out.println((driver.findElement(By.cssSelector("span[class=validation-failed]")).getText()));
     Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(), 'The value have not')]")).getText(),
         "The value have not to be more than 99.");
  //TODO put 1 and verify error msg disappeared

}
@Test (description = "Daily repeat option, Repeat every day(s), functionality")
    public void test4(){
        test0();

    Random random = new Random();
    int n = random.nextInt(99);

   driver.findElement(By.cssSelector("input[data-related-field=interval]")).sendKeys(""+ Keys.BACK_SPACE + n + Keys.ENTER);
    System.out.println("random = "+ n);
    Assert.assertEquals(driver.findElement(By.cssSelector("div[class$='controls'][data-name='recurrence-summary']")).getText(),
            "Daily every "+n+" days" );
    }

@Test (description = "Daily repeat option, blank fields")
    public void test5(){
        test0();

    driver.findElement(By.cssSelector("input[data-related-field=interval]")).sendKeys(Keys.BACK_SPACE + "" + Keys.ENTER);
    String msg = driver.findElement(By.cssSelector("span[id^=temp-validation-name]")).getText();
    Assert.assertEquals(msg,
            "This value should not be blank.");
    driver.findElement(By.cssSelector("input[data-related-field=interval]")).sendKeys("1" + Keys.ENTER);

    verifyElementNotDisplayed(By.cssSelector("span[class=validation-failed][id^='temp-validation-']"));
    waitForClickablility(driver.findElement(By.xpath("(//input[@type='radio'])[4]")),10).click();
   // actions.moveToElement(driver.findElement(By.xpath("(//input[@type='radio'])[4]"))).click().build().perform();

    waitFor(5);
   clickWithJS(driver.findElement(By.cssSelector("input[data-related-field='occurrences']")));
    waitFor(5);
    driver.findElement(By.cssSelector("input[data-related-field='occurrences']")).sendKeys(Keys.ENTER);
    waitFor(5);
//    msg = driver.findElement(By.xpath("(//span[contains(.,'not be blank')])[4]")).getText();
//    Assert.assertEquals(msg,
//            "This value should not be blank.");
    }
@Test (description = "Daily repeat option, Ends, error messages")
    public void test6(){

}

}
