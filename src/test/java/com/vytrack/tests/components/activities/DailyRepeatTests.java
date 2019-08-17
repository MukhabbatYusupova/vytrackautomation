package com.vytrack.tests.components.activities;

import com.vytrack.pages.CreateCalEventPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Random;
import static com.vytrack.utilities.BrowserUtils.*;
import static com.vytrack.utilities.VytrackUtils.*;
import static org.testng.Assert.*;

public class DailyRepeatTests extends TestBase {

    CreateCalEventPage createCalEventPage;
    @Test (description = "Base Test")
    public void test0(){
        extentTest = report.createTest("Activities -Calendar Evenets");
        createCalEventPage = new CreateCalEventPage();
        //TODO login
        login(driver, ConfigurationReader.get("truckDriver"), ConfigurationReader.get("password") );
        selectMenuOption(driver, "Activities", "Calendar Events");
        //TODO create Calendar Event
        clickWithJS(driver.findElement(By.linkText("Create Calendar Event")));
        //TODO click on REPEAT
        clickWithJS(createCalEventPage.repeatCheckbox);

    }

    @Test(description ="Daily Repeat option, Repeat every, summary")
    public void test1(){
        test0();

        clickWithJS(createCalEventPage.weeklyCheck);
        assertTrue(createCalEventPage.weeklyCheck.isSelected());
        assertFalse(createCalEventPage.dailyCheck.isSelected());
        assertEquals(createCalEventPage.summary.getText(),
                "Daily, every weekday");
    }


    @Test (description = "Daily repeat, Repeat every, default values")
    public void test2(){
        test0();
    assertTrue(createCalEventPage.dailyCheck.isSelected());
    assertEquals(createCalEventPage.repeatEvery.getAttribute("value"), "1");
    assertEquals(createCalEventPage.summary.getText(),
                        "Daily every 1 day");
    }


    @Test (description = "Daily repeat option, repeat every day(s), error message")
    public void test3() {
    test0();

    createCalEventPage.repeatEvery.sendKeys(Keys.BACK_SPACE + "0" + Keys.ENTER);
    waitForVisibility(createCalEventPage.errorMsg, 10);
    assertEquals(createCalEventPage.errorMsg.getText(),
           "The value have not to be less than 1.");

    createCalEventPage.repeatEvery.sendKeys(Keys.BACK_SPACE + "100" + Keys.ENTER);
    waitForVisibility(createCalEventPage.errorMsg, 10);
    assertEquals(createCalEventPage.errorMsg.getText(),
         "The value have not to be more than 99.");

    createCalEventPage.repeatEvery.clear();
    createCalEventPage.repeatEvery.sendKeys("1" + Keys.ENTER);
    assertFalse(createCalEventPage.errorMsg.isDisplayed());
    }


    @Test (description = "Daily repeat option, Repeat every day(s), functionality")
    public void test4(){
        test0();

    Random random = new Random();
    int n = random.nextInt(99);

   createCalEventPage.repeatEvery.sendKeys(""+ Keys.BACK_SPACE + n + Keys.ENTER);
   assertEquals(createCalEventPage.summary.getText(),
            "Daily every "+n+" days" );
    }


    @Test (description = "Daily repeat option, blank fields")
    public void test5(){
        test0();

    createCalEventPage.repeatEvery.sendKeys(Keys.BACK_SPACE + "" + Keys.ENTER);
    assertEquals(createCalEventPage.errorMsg.getText(), "This value should not be blank.");
    createCalEventPage.repeatEvery.sendKeys("1" + Keys.ENTER);
    assertFalse(createCalEventPage.errorMsg.isDisplayed());
    clickWithJS(createCalEventPage.afterCheck);

    createCalEventPage.occurences.sendKeys(Keys.ENTER);

//    msg = driver.findElement(By.xpath("(//span[contains(.,'not be blank')])[4]")).getText();
//    Assert.assertEquals(msg,
//            "This value should not be blank.");
    }
@Test (description = "Daily repeat option, Ends, error messages")
    public void test6(){

    System.out.println("don't delete me-Atike");
}

}
