package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HeaderPage;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.JsonCredentialsReader;
import utils.Log;
import utils.ScreenshotUtils;

import java.lang.reflect.Method;

public class BaseTest {

    // Thread-safe WebDriver for parallel testing
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected ExtentReports extent;
    protected ExtentTest test;

    // Getter for the current thread's driver
    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentReportManager.getExtent();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser, Method method) {
        WebDriver webDriver;
        JsonCredentialsReader reader = new JsonCredentialsReader();
        String username = reader.getUsername();
        String password = reader.getPassword();

        switch (browser.toLowerCase()) {
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                webDriver = new ChromeDriver();
                break;
        }

        driver.set(webDriver);
        getDriver().manage().window().maximize();
        getDriver().get("https://www.amazon.in");

        // Thread-safe ExtentTest
        ExtentTest test = ExtentReportManager.getExtent().createTest(method.getName() + " [" + browser + "]");
        ExtentReportManager.setTest(test);
        Log.setExtentTest(test);

        Log.info("Test started with browser: " + browser);

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.clickSignIn();
        loginPage.enterEmail(username);
        loginPage.clickContinue();
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @AfterMethod
    public void tearDown(org.testng.ITestResult result) {
        new HeaderPage(getDriver()).logout(); // ← Use POM method


        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(getDriver(), result.getName());
            ExtentReportManager.getTest().fail("Test failed: " + result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            ExtentReportManager.getTest().fail("Test failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportManager.getTest().pass("Test passed");
        } else {
            ExtentReportManager.getTest().skip("Test skipped: " + result.getThrowable());
        }

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
