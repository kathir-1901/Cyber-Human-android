package com.automation.utils;

import com.automation.pages.HomePage;
import com.automation.pages.ProfilePage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;

/**
 * ✅ PROFILE NAVIGATION HELPER MODULE
 * 
 * This module provides reusable navigation methods to reach the Profile page
 * from the Home page. It includes common steps used across multiple test cases.
 * 
 * Common Steps:
 * 1. Verify DAILY PRIORITY heading is displayed on home page
 * 2. Click Wellbeing Dashboard (if not already there)
 * 3. Click PROFILE
 * 4. Verify Profile page is displayed
 * 
 * Usage:
 * ProfileNavigationHelper.navigateToProfile(driver, test);
 */
public class ProfileNavigationHelper {

    /**
     * Navigate to Profile page from Home page with full validation
     * 
     * @param driver AppiumDriver instance
     * @param test   ExtentTest instance for logging
     * @return ProfilePage instance
     * @throws InterruptedException if thread sleep is interrupted
     */
    public static ProfilePage navigateToProfile(AppiumDriver driver, ExtentTest test) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        // Check current state: Are we already on Profile page?
        test.log(Status.INFO, "Checking current page state...");
        Thread.sleep(2000); // Give page time to load
        boolean isOnProfilePage = profilePage.isProfilePageDisplayed();

        if (isOnProfilePage) {
            // Already on Profile page from previous test - just verify and return
            test.log(Status.INFO, "Already on Profile page from previous test");
            test.log(Status.PASS, "✓ Profile page is displayed");
            return profilePage;
        }

        // Check if we're on Home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();

        if (!isHomePageDisplayed) {
            // Not on home page and not on profile page - try to recover
            test.log(Status.INFO, "App is not on Home or Profile page. Attempting to navigate to Home page...");

            try {
                navigateToHomePageFromAnywhere(driver, test);
                test.log(Status.PASS, "✓ Successfully navigated to Home page");
            } catch (Exception e) {
                test.log(Status.FAIL, "Failed to navigate to Home page: " + e.getMessage());
                Assert.fail("Navigation failed - Could not reach Home page to start test.");
            }
        } else {
            test.log(Status.PASS, "✓ DAILY PRIORITY heading is displayed on home page");
        }

        // Step 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }
        Thread.sleep(2000);

        // Step 3: Click PROFILE
        test.log(Status.INFO, "Step 3: Clicking PROFILE button");
        homePage.clickProfile();
        test.log(Status.PASS, "✓ PROFILE button clicked");
        Thread.sleep(2000);

        // Step 4: Verify Profile page is displayed
        test.log(Status.INFO, "Step 4: Verifying Profile page is displayed");
        boolean isProfilePageDisplayed = profilePage.isProfilePageDisplayed();

        if (!isProfilePageDisplayed) {
            test.log(Status.FAIL, "Profile page not displayed");
            Assert.fail("Profile page validation failed - PROFILE heading not displayed");
        }
        test.log(Status.PASS, "✓ Profile page is displayed successfully");

        return profilePage;
    }

    /**
     * Navigate to Home page from anywhere in the app
     * Uses Android system back to clear navigation stack
     * 
     * @param driver AppiumDriver instance
     * @param test   ExtentTest instance for logging
     * @throws InterruptedException if thread sleep is interrupted
     */
    public static void navigateToHomePageFromAnywhere(AppiumDriver driver, ExtentTest test)
            throws InterruptedException {
        int maxAttempts = 10;
        int attempts = 0;

        test.log(Status.INFO, "Attempting to navigate to Home page using system back...");

        while (attempts < maxAttempts) {
            try {
                // Check if we're on home page
                org.openqa.selenium.WebElement homeHeading = driver.findElement(
                        org.openqa.selenium.By.xpath("//android.view.View[@content-desc='DAILY PRIORITY']"));
                if (homeHeading.isDisplayed()) {
                    test.log(Status.INFO, "✓ Reached Home page after " + attempts + " back presses");
                    return; // Successfully reached home page
                }
            } catch (Exception e) {
                // Not on home page yet, continue
            }

            // Press Android system back
            driver.navigate().back();
            Thread.sleep(500);
            attempts++;
        }

        throw new RuntimeException("Failed to navigate to Home page after " + maxAttempts + " attempts");
    }

    /**
     * Navigate to Profile page from Home page (simplified version without
     * assertions)
     * Use this when you want to navigate but handle validation yourself
     * 
     * @param driver AppiumDriver instance
     * @param test   ExtentTest instance for logging
     * @return ProfilePage instance
     * @throws InterruptedException if thread sleep is interrupted
     */
    public static ProfilePage navigateToProfileSimple(AppiumDriver driver, ExtentTest test)
            throws InterruptedException {
        test.log(Status.INFO, "Navigating to Profile page");

        HomePage homePage = new HomePage(driver);

        // Click Wellbeing Dashboard
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.INFO, "Clicked Wellbeing Dashboard");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }
        Thread.sleep(2000);

        // Click PROFILE
        homePage.clickProfile();
        test.log(Status.INFO, "Clicked PROFILE button");
        Thread.sleep(2000);

        return new ProfilePage(driver);
    }

    /**
     * Verify if currently on Home page
     * 
     * @param driver AppiumDriver instance
     * @param test   ExtentTest instance for logging
     * @return true if on home page, false otherwise
     */
    public static boolean isOnHomePage(AppiumDriver driver, ExtentTest test) {
        HomePage homePage = new HomePage(driver);
        boolean isHome = homePage.isHomePageDisplayed();

        if (isHome) {
            test.log(Status.INFO, "✓ Currently on Home page");
        } else {
            test.log(Status.INFO, "✗ Not on Home page");
        }

        return isHome;
    }

    /**
     * Verify if currently on Profile page
     * 
     * @param driver AppiumDriver instance
     * @param test   ExtentTest instance for logging
     * @return true if on profile page, false otherwise
     */
    public static boolean isOnProfilePage(AppiumDriver driver, ExtentTest test) {
        ProfilePage profilePage = new ProfilePage(driver);
        boolean isProfile = profilePage.isProfilePageDisplayed();

        if (isProfile) {
            test.log(Status.INFO, "✓ Currently on Profile page");
        } else {
            test.log(Status.INFO, "✗ Not on Profile page");
        }

        return isProfile;
    }
}
