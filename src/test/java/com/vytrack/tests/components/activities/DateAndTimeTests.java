package com.vytrack.tests.components.activities;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.vytrack.utilities.BrowserUtils.*;
import static com.vytrack.utilities.VytrackUtils.*;


public class DateAndTimeTests extends TestBase {

    @Test (description = "Base Test")
    public void test0(){
        login(driver, ConfigurationReader.get("truckDriver"), ConfigurationReader.get("password") );
        selectMenuOption(driver, "Activities", "Calendar Events");
        clickWithJS(driver.findElement(By.linkText("Create Calendar Event")));
    }

    @Test(description = "Start date, End date auto adjust")
    public void test1(){
    test0();

        waitForClickablility(
        driver.findElement(By.cssSelector("input[id^='date_selector_oro_calendar_event_form_start']")),5)
        .sendKeys(Keys.ENTER);

        int day = localDate.getDayOfMonth()+1;
        System.out.println("day = " + day);
        clickWithJS(driver.findElement(By.linkText(String.valueOf(day))));

        waitFor(10);
        WebElement endDate = driver.findElement(By.cssSelector("input[id^='date_selector_oro_calendar_event_form_end']"));
        waitForVisibility(endDate,10);
        assertEquals(endDate.getAttribute("value"), "Jul "+String.valueOf(day)+", 2019");
    }

    @Test (description = "Start time and end time auto adjust")
    public void test2(){
        test0();
        waitForClickablility(
                driver.findElement(By.cssSelector("input[id^='time_selector_oro_calendar_event_form_start']")),5)
                .sendKeys(Keys.ENTER);


    clickWithJS(driver.findElement(By.xpath("//li[contains(text(),'10:00 PM')]")));

    WebElement endTime = driver.findElement(By.cssSelector("input[id^='time_selector_oro_calendar_event_form_end']"));
    waitForVisibility(endTime,10);
    assertEquals(endTime.getAttribute("value"), "11:00 PM");
    }

    @Test (description = "Start time, End date/time auto adjust")
    public void test3(){
        test0();
        waitForClickablility(
                driver.findElement(By.cssSelector("input[id^='time_selector_oro_calendar_event_form_start']")),5)
                .sendKeys(Keys.ENTER);


        clickWithJS(driver.findElement(By.xpath("//li[contains(text(),'11:30 PM')]")));
        WebElement endTime = driver.findElement(By.cssSelector("input[id^='time_selector_oro_calendar_event_form_end']"));
        waitForVisibility(endTime,10);
        assertEquals(endTime.getAttribute("value"), "12:30 AM");

    }


}
