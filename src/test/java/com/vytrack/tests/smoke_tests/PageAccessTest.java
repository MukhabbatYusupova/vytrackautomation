package com.vytrack.tests.smoke_tests;

import com.vytrack.pages.LogInPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VytrackUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;



public class PageAccessTest extends TestBase {
    LogInPage logInPage;
    @Test
    public void vehicleContractsStoreManager() throws InterruptedException {
        logInPage = new LogInPage();
        logInPage.login(ConfigurationReader.get("storemanager"), ConfigurationReader.get("password"));

        VytrackUtils.selectMenuOption(driver, "Fleet", "Vehicle Contracts");
        wait.until(ExpectedConditions.titleContains("All - Vehicle Contract"));
        Assert.assertEquals(driver.getTitle(), "All - Vehicle Contract - Entities - System - Car - Entities - System");
    }

    @Test
    public void vehicleContractsSalesManager() throws InterruptedException {
        logInPage = new LogInPage();
        logInPage.login(ConfigurationReader.get("salesmanager"), ConfigurationReader.get("password"));
        VytrackUtils.selectMenuOption(driver, "Fleet", "Vehicle Contracts");
        wait.until(ExpectedConditions.titleContains("All - Vehicle Contract"));
        Assert.assertEquals(driver.getTitle(), "All - Vehicle Contract - Entities - System - Car - Entities - System");
    }

    @Test
    public void vehicleContractsTruckDriver() throws InterruptedException {
        logInPage = new LogInPage();
        logInPage.login(ConfigurationReader.get("truckDriver"), ConfigurationReader.get("password"));
        extentTest = report.createTest("Vehicle Contracts Access");
        VytrackUtils.selectMenuOption(driver, "Fleet", "Vehicle Contracts");
        extentTest.info("Verifying the error message");
        Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='message'])[2]")).getText(),
                "You do not have permission to perform this action.");
  //TODO  sometimes it is not passing due to the not appearance of the alert msg
    }

}
