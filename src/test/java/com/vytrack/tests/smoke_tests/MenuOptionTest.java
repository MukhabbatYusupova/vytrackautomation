package com.vytrack.tests.smoke_tests;

import com.vytrack.pages.LogInPage;
import com.vytrack.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import static com.vytrack.utilities.BrowserUtils.*;
import static com.vytrack.utilities.VytrackUtils.*;
import static org.testng.Assert.assertEquals;

public class MenuOptionTest extends TestBase {
LogInPage logInPage;
@Test
    public void menuOptionDriver()  {
     logInPage = new LogInPage();
     logInPage.login(ConfigurationReader.get("truckDriver"), ConfigurationReader.get("password") );
        extentTest= report.createTest("Menu option Modules");
// TODO FLEET:
    extentTest.info("Fleet Module");
    VytrackUtils.selectMenuOption(driver, "Fleet", "Vehicles");
    wait.until(ExpectedConditions.titleIs("Car - Entities - System - Car - Entities - System"));
    assertEquals(driver.getTitle(),"Car - Entities - System - Car - Entities - System");

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='oro-subtitle']")));
    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(),"Cars");
        extentTest.info("Customers Module");
 //TODO CUSTOMERS
    VytrackUtils.selectMenuOption(driver, "Customers", "Accounts");
    wait.until(ExpectedConditions.titleIs("Accounts - Customers"));
    assertEquals("Accounts - Customers", driver.getTitle());

    waitForVisibility(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")),10);
   assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "Accounts");

    driver.findElement(By.linkText("Customers")).click();
    waitForVisibility(driver.findElement(By.linkText("Contacts")),5)
            .click();

    wait.until(ExpectedConditions.titleIs("Contacts - Customers"));
    assertEquals("Contacts - Customers", driver.getTitle());

    waitForVisibility(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")),5);
    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "Contacts");
        extentTest.info("Activities Module");
 //TODO ACTIVITIES:
    selectMenuOption(driver, "Activities", "Calendar Events");

    wait.until(ExpectedConditions.titleIs("Calendar Events - Activities"));
    assertEquals("Calendar Events - Activities", driver.getTitle());

    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "Calendar Events");


}

@Test
    public void menuOptionStoreManager() {
        login(driver, "storemanager87", "UserUser123");

        VytrackUtils.selectMenuOption(driver, "Dashboards", "Dashboard");
        wait.until(ExpectedConditions.titleIs("Dashboard - Dashboards"));
        assertEquals(driver.getTitle(),"Dashboard - Dashboards");
        assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "Dashboard");

    VytrackUtils.selectMenuOption(driver, "Fleet", "Vehicles");
    wait.until(ExpectedConditions.titleIs("All - Car - Entities - System - Car - Entities - System"));
    assertEquals("All - Car - Entities - System - Car - Entities - System", driver.getTitle());
    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Cars");

    VytrackUtils.selectMenuOption(driver, "Customers", "Accounts");
    wait.until(ExpectedConditions.titleIs("All - Accounts - Customers"));
    assertEquals(driver.getTitle(),"All - Accounts - Customers");
    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Accounts");

    VytrackUtils.selectMenuOption(driver, "Customers", "Contacts");
    wait.until(ExpectedConditions.titleIs("All - Contacts - Customers"));
    assertEquals(driver.getTitle(),"All - Contacts - Customers");
    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Contacts");

    VytrackUtils.selectMenuOption(driver, "Sales", "Opportunities");
    wait.until(ExpectedConditions.titleIs("Open Opportunities - Opportunities - Sales"));
    assertEquals(driver.getTitle(),"Open Opportunities - Opportunities - Sales");
    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "Open Opportunities");

    VytrackUtils.selectMenuOption(driver, "Activities", "Calls");
    wait.until(ExpectedConditions.titleIs("All - Calls - Activities"));
    assertEquals(driver.getTitle(),"All - Calls - Activities");
    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Calls");

    VytrackUtils.selectMenuOption(driver, "Activities", "Calendar Events");
    wait.until(ExpectedConditions.titleIs("All - Calendar Events - Activities"));
    assertEquals(driver.getTitle(),"All - Calendar Events - Activities");
    assertEquals(driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText(), "All Calendar Events");

}
@Test (description = "Log in StoreManager")
public void test1()  {

    login(driver, "storemanager87", "UserUser123");

    assertEquals(driver.findElement(By.cssSelector("a[data-toggle*=dropdown]")).getText(), "Bobbie Hintz");
    assertEquals(driver.getTitle(),"Dashboard");

    clickWithJS(driver.findElement(By.id("user-menu")));
    clickWithJS(driver.findElement(By.cssSelector("a[class=no-hash]")));  //Log OUT
}

@Test (description = "Log in SalesManager")
public void test2() {

    login(driver, "salesmanager123", "UserUser123");

    assertEquals(driver.findElement(By.cssSelector("a[data-toggle*=dropdown]")).getText(), "Fredy Wintheiser");
    assertEquals(driver.getTitle(), "Dashboard");
    clickWithJS(driver.findElement(By.id("user-menu")));
    clickWithJS(driver.findElement(By.cssSelector("a[class=no-hash]")));
}
@Test (description = "Log in Driver")
public void test3(){
    login(driver,"user39", "UserUser123" );

    assertEquals(driver.findElement(By.cssSelector("a[data-toggle*=dropdown]")).getText(), "Dewayne Tromp");
    assertEquals(driver.getTitle(),"Dashboard");

    waitForClickablility(driver.findElement(By.id("user-menu")),5) //TODO sometimes not working, used JS
            .click();
    clickWithJS( driver.findElement(By.cssSelector("a[class=no-hash]")));
    }


@Test (description = "Log in with Invalid credentialas")

public void logInNegative(){
    driver.findElement(By.id("prependedInput")).
            sendKeys("Wrong username");

    driver.findElement(By.id("prependedInput2")).
            sendKeys("Wrong password"+ Keys.ENTER);
    waitFor(2);
    WebElement text = driver.findElement(By.cssSelector("div[class^=alert]"));
    waitForClickablility(text,3).click();
    assertEquals(text.getText(), "Invalid user name or password.");

    wait.until(ExpectedConditions.titleIs("Login"));
    assertEquals(driver.getTitle(),"Login");


    System.out.println("MUALLA DID ANY CHANGE");;
}

}
