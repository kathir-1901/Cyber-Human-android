package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HomePage {
    private WebDriverWait wait;
    private AppiumDriver driver;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String dailyPriorityHeadingXpath = "//android.view.View[@content-desc='DAILY PRIORITY']";
    private final String proceedButtonXpath = "//android.widget.Button[@content-desc='PROCEED']";

    /**
     * Click on the Wellbeing Dashboard menu button at the bottom.
     * Uses content-desc with newline character: 'WELLBEING DASHBOARD\nHOME'
     */
    public void clickWellbeingDashboard() {
        try {
            String xpathNewline = "//android.widget.ImageView[@content-desc='WELLBEING DASHBOARD\nHOME']";
            WebElement dashboardBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpathNewline)));
            dashboardBtn.click();
            System.out.println("Wellbeing Dashboard clicked (newline content-desc)");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Wellbeing Dashboard", e);
        }
    }

    /**
     * Click on the PROFILE options
     */
    public void clickProfile() {
        try {
            WebElement profileBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc='PROFILE']")));
            profileBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("PROFILE button not found on Dashboard page", e);
        }
    }

    /**
     * Click the PROCEED button if visible on homepage
     */
    public void clickProceed() {
        try {
            WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(proceedButtonXpath)));
            proceedBtn.click();
        } catch (TimeoutException e) {
            // PROCEED button may not always be present, so we don't throw exception
            System.out.println("PROCEED button not found, continuing...");
        }
    }

    private final String dailyPrescriptionXpath = "//android.view.View[@content-desc='DAILY PRESCRIPTION']";

    /**
     * Click on the DAILY PRESCRIPTION option
     */
    public void clickDailyPrescription() {
        try {
            WebElement dailyPrescriptionBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(dailyPrescriptionXpath)));
            dailyPrescriptionBtn.click();
        } catch (TimeoutException e) {
            try {
                File file = new File("target/page_source_failure.xml");
                FileWriter writer = new FileWriter(file);
                writer.write(driver.getPageSource());
                writer.close();
                System.out.println("DEBUG: Saved page source to " + file.getAbsolutePath());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            throw new RuntimeException("DAILY PRESCRIPTION button not found on Home page", e);
        }
    }

    /**
     * Check if Home page is displayed
     */
    public boolean isHomePageDisplayed() {
        try {
            // Short 3-second wait — avoids long hang on every failed check
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement heading = shortWait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(dailyPriorityHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for Home page to be displayed with a longer timeout.
     * Useful at the start of tests to ensure app is ready.
     */
    public void waitForHomePage() {
        try {
            System.out.println("⏳ Waiting for Home Page (DAILY PRIORITY heading)...");
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            longerWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dailyPriorityHeadingXpath)));
            System.out.println("✅ Home Page is displayed!");
        } catch (TimeoutException e) {
            throw new RuntimeException("Home page (DAILY PRIORITY) not displayed after 30 seconds wait", e);
        }
    }

    /**
     * Navigate to logout from Home page.
     * This is a helper method for SignInTest to navigate from logged-in state to
     * Sign In page.
     * Flow: Wellbeing Dashboard → Profile → Logout → Yes
     * 
     * @throws InterruptedException if sleep is interrupted
     */
    public void navigateToLogout() throws InterruptedException {
        // Click Wellbeing Dashboard
        clickWellbeingDashboard();
        Thread.sleep(1000);

        // Click Profile
        clickProfile();
        Thread.sleep(1000);
    }
}
