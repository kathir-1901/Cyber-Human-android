package com.automation.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import com.automation.utils.ConfigReader;

public class BaseTest {

    public static AppiumDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    /* ================= REPORT SETUP ================= */

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
        spark.config().setReportName("Appium Login Automation Report");
        spark.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("OS", "Android");
        extent.setSystemInfo("Tester", "Antigravity");
    }

    /* ================= DRIVER SETUP ================= */

    @BeforeMethod
    public void setup(java.lang.reflect.Method method) throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(ConfigReader.getProperty("deviceName"));
        options.setAutomationName(ConfigReader.getProperty("automationName"));
        options.setAppPackage(ConfigReader.getProperty("appPackage"));
        options.setAppActivity(ConfigReader.getProperty("appActivity"));
        options.setNoReset(true); // Keep login state

        // Stability capabilities for Android 15
        options.setCapability("appium:disableWindowAnimation", true);
        options.setCapability("appium:skipUnlock", true);
        options.setCapability("appium:ignoreHiddenApiPolicyError", true);
        options.setCapability("appium:noSign", true);

        int newCommandTimeout = Integer.parseInt(ConfigReader.getProperty("newCommandTimeout"));
        options.setNewCommandTimeout(Duration.ofSeconds(newCommandTimeout));

        String appiumUrl = ConfigReader.getProperty("appiumUrl");
        driver = new AndroidDriver(new URL(appiumUrl), options);

        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(implicitWait));

        // FORCE OPEN APP: Ensure app is active and in foreground before ANY test starts
        forceOpenApp();

        // LOGGING: Inform about test start
        String testClassName = method.getDeclaringClass().getSimpleName();
        System.out.println("🚀 Starting test: " + testClassName + "." + method.getName());
    }

    /**
     * Force open application and bring to foreground
     */
    private void forceOpenApp() {
        try {
            String appPackage = ConfigReader.getProperty("appPackage");
            
            // Bring app to foreground if already running, or launch it
            // Using activateApp ensures it's opened even if session was already active
            ((AndroidDriver) driver).activateApp(appPackage);
            
            // Wait for app to be ready and visible
            Thread.sleep(4000); 

            System.out.println("✓ App force opened and brought to foreground");
        } catch (Exception e) {
            System.out.println("⚠ Warning: Force open app failed: " + e.getMessage());
        }
    }

    /* ================= CLEAN TEARDOWN ================= */

    @AfterMethod
    public void tearDown(ITestResult result) {

        // Screenshot + FAIL logging ONLY on real failure
        if (test != null && result.getStatus() == ITestResult.FAILURE) {

            test.log(Status.FAIL, "Test Failed");
            test.log(Status.FAIL, result.getThrowable());

            try {
                String screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BASE64);
                test.addScreenCaptureFromBase64String(screenshot);
            } catch (Exception ignored) {
            }
        }

        // App + Driver cleanup
        if (driver != null) {
            try {
                ((AndroidDriver) driver)
                        .terminateApp(ConfigReader.getProperty("appPackage"));
            } catch (Exception ignored) {
            }
            driver.quit();
        }

        // User requested 2-second delay after every test
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* ================= REPORT FLUSH ================= */

    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
