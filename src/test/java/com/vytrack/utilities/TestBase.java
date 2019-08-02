package com.vytrack.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public LocalDate localDate = LocalDate.now();
    public LocalTime nowTime = LocalTime.now();
    public String dateStr = String.valueOf(localDate);

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentTest;

@BeforeTest
public void start(){
    String filePath = System.getProperty("user.dir") + "/test-output/report.html";
    htmlReporter = new ExtentHtmlReporter(filePath);
    report = new ExtentReports();
    report.attachReporter(htmlReporter);

    htmlReporter.config().setReportName("Vytrack automated test reports");

    report.setSystemInfo("Environment", "qa3");
    report.setSystemInfo("OS", System.getProperty("os.name"));
    report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
    report.setSystemInfo("Testing Engineer", "Muhabbat");

}

@AfterTest
public void flush(){
    report.flush();
}
    @BeforeMethod
    public void setUp(){
        driver= Driver.get();
        driver.get(ConfigurationReader.get("url"));
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 10);


    }


    @AfterMethod
    public void tearDownMethod(ITestResult result) throws InterruptedException, IOException {
        // if the test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            // record the failed test
            extentTest.fail(result.getName());
            // take screen shot and add to report0
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
            extentTest.addScreenCaptureFromPath(screenshotLocation);
            // capture the exception
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test case skipped: " + result.getName());
        }

        Thread.sleep(4000);
        Driver.closeDriver();
    }





}
