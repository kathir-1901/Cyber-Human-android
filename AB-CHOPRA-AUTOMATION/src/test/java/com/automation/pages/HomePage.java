package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriverWait wait;

    public HomePage(AppiumDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String dailyPriorityHeadingXpath = "//android.view.View[@content-desc='DAILY PRIORITY']";
    private final String wellbeingDashboardXpath = "//android.view.View[@content-desc='WELLBEING DASHBOARD\nHOME']";
    private final String proceedButtonXpath = "//android.widget.Button[@content-desc='PROCEED']";

    /**
     * Click on the Wellbeing Dashboard menu button at the bottom
     */
    public void clickWellbeingDashboard() {
        try {
            WebElement dashboardBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(wellbeingDashboardXpath)));
            dashboardBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Wellbeing Dashboard button not found on Home page", e);
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
            throw new RuntimeException("DAILY PRESCRIPTION button not found on Home page", e);
        }
    }

    /**
     * Check if Home page is displayed
     */
    public boolean isHomePageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(dailyPriorityHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
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
