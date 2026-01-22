package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.SignInPage;
import com.automation.pages.HomePage;
import com.automation.pages.ProfilePage;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignInTest extends BaseTest {

    /**
     * NAVIGATE TO SIGN IN PAGE BEFORE EACH TEST
     * This ensures every test starts from Sign In page.
     */
    @BeforeMethod
    public void navigateToSignInPageBeforeTest() throws Exception {
        navigateToSignInPage();
    }

    /**
     * NAVIGATE TO SIGN IN PAGE
     * This ensures tests start from Sign In page.
     */
    private void navigateToSignInPage() throws Exception {
        // Driver already initialized by BaseTest.setup()

        SignInPage signInPage = new SignInPage(driver);

        // Check if already on Sign In page
        if (signInPage.isOnSignInPage()) {
            System.out.println("✓ Already on Sign In page");
            return;
        }

        // Check if on Home page (logged in)
        HomePage homePage = new HomePage(driver);
        if (homePage.isHomePageDisplayed()) {
            System.out.println("User is logged in, navigating to Sign In page...");

            // Navigate: Wellbeing Dashboard → Profile → Logout → Yes
            homePage.navigateToLogout();

            ProfilePage profilePage = new ProfilePage(driver);
            profilePage.clickLogout();
            Thread.sleep(1000);

            // Click YES in logout confirmation
            profilePage.clickYes();
            Thread.sleep(2000);

            System.out.println("✓ Navigated to Sign In page");
            return;
        }

        // If on other page, press back until Sign In page appears
        int maxAttempts = 10;
        int attempts = 0;
        while (!signInPage.isOnSignInPage() && attempts < maxAttempts) {
            driver.navigate().back();
            Thread.sleep(500);
            attempts++;
        }

        if (signInPage.isOnSignInPage()) {
            System.out.println("✓ Navigated to Sign In page via back button");
        } else {
            System.out.println("⚠ Warning: Could not navigate to Sign In page");
        }
    }

    /**
     * NEGATIVE TEST DATA (NO HARDCODED EXPECTED MESSAGES)
     * Only test scenario name, email, and password
     */
    @DataProvider(name = "negativeLoginData")
    public Object[][] getNegativeLoginData() {
        return new Object[][] {
                { "Invalid Password", "testuser@example.com", "WrongPass" },
                { "Invalid Email Format", "testuser", "Password@123" },
                { "Non-existent Account", "notfound@example.com", "Password@123" },
                { "Empty Email", "", "Password@123" },
                { "Empty Password", "testuser@example.com", "" },
                { "Both Empty", "", "" },
                { "Special Characters Email", "test@#$%@example.com", "Password@123" },
        };
    }

    /**
     * RUNTIME-BASED NEGATIVE TEST (NO HARDCODED VALIDATION)
     * 
     * Test Flow:
     * 1. Enter email and password
     * 2. Click Continue button
     * 3. Check if ANY validation appears at runtime
     * 4. PASS if validation detected, FAIL if not
     * 5. Log the actual runtime validation message in Extent Report
     */
    @Test(dataProvider = "negativeLoginData")
    public void testNegativeSignIn(String testScenario, String email, String password)
            throws InterruptedException {

        test = extent.createTest("Negative Test: " + testScenario);
        test.log(Status.INFO, "Email: '" + email + "' | Password: '" + password + "'");

        SignInPage signInPage = new SignInPage(driver);

        // Step 1: Enter credentials
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        test.log(Status.INFO, "Entered credentials");

        // Step 2: Click Continue button
        signInPage.clickContinue();
        test.log(Status.INFO, "Clicked Continue button");

        // Step 3: Wait for validation to appear using explicit wait (replaces
        // Thread.sleep)
        signInPage.waitForValidationToAppear(3);

        // Step 4: RUNTIME VALIDATION CHECK (NO HARDCODED MESSAGES)
        boolean validationDetected = signInPage.isAnyValidationVisible();

        if (validationDetected) {
            // PASS: Validation appeared (negative case handled correctly)

            // Capture and log the actual runtime validation message
            String validationMessage = signInPage.getValidationMessage();
            if (validationMessage != null && !validationMessage.trim().isEmpty()) {
                test.log(Status.INFO, "📋 Validation message displayed: \"" + validationMessage + "\"");
            }

            test.log(Status.PASS, "✓ Validation detected at runtime - Negative case handled correctly");
            test.log(Status.PASS, "Test PASSED: Application showed validation for invalid input");
        } else {
            // FAIL: No validation appeared (security/UX issue)
            test.log(Status.FAIL, "✗ NO validation detected at runtime");
            test.log(Status.FAIL, "Test FAILED: Application did not show any validation for invalid input");
            Assert.fail("Expected validation to appear for negative test case, but NONE was detected");
        }
    }

    /**
     * POSITIVE LOGIN TEST (Optional - for comparison)
     * This test expects successful login without validation errors
     */
    @Test
    public void testPositiveSignIn() throws InterruptedException {
        test = extent.createTest("Positive Test: Valid Login");
        test.log(Status.INFO, "Testing valid credentials");

        SignInPage signInPage = new SignInPage(driver);

        // Use valid credentials
        String validEmail = "ramesh@navadhiti.com";
        String validPassword = "Testing@2025";

        signInPage.enterEmail(validEmail);
        signInPage.enterPassword(validPassword);
        test.log(Status.INFO, "Entered valid credentials");

        signInPage.clickContinue();
        test.log(Status.INFO, "Clicked Continue button");

        // Wait for navigation to LINK DEVICES page
        // User requested approx 5 sec wait/check for "LINK DEVICES"
        boolean isLinkDevicesPage = signInPage.isLinkDevicesDisplayed();

        if (isLinkDevicesPage) {
            // PASS: Successfully navigated to Link Devices page
            test.log(Status.PASS, "✓ 'LINK DEVICES' page displayed - Login successful");
            test.log(Status.PASS, "Test PASSED: Valid credentials accepted and navigated to next screen");

            /*
             * AUTO-RESET: Perform Logout
             * This ensures the app returns to Sign In page for subsequent tests/runs.
             */
            test.log(Status.INFO, "initiating Auto-Reset (Logout)...");

            // 1. Click "SKIP FOR NOW"
            signInPage.clickSkipForNow();

            // 2. Wait for "DAILY PRIORITY" (Home Page verification)
            HomePage homePage = new HomePage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//android.view.View[@content-desc='DAILY PRIORITY']")));
            } catch (Exception e) {
                test.log(Status.WARNING, "DAILY PRIORITY not found after skipping link devices");
            }

            // 3. Click "WELLBEING DASHBOARD HOME"
            try {
                WebElement dashboardBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.View[@content-desc='WELLBEING DASHBOARD HOME']")));
                dashboardBtn.click();
            } catch (Exception e) {
                // Fallback to existing locator if specific one fails, or log warning
                homePage.clickWellbeingDashboard();
            }

            // 4. Click "PROFILE"
            try {
                WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.View[@content-desc='PROFILE']")));
                profileBtn.click();
            } catch (Exception e) {
                test.log(Status.WARNING, "PROFILE button not found");
            }

            // 5. Click "LOG OUT"
            ProfilePage profilePage = new ProfilePage(driver);
            try {
                WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.View[@content-desc='LOG OUT']")));
                logoutBtn.click();
            } catch (Exception e) {
                test.log(Status.WARNING, "LOG OUT button not found");
            }

            // 6. Click "YES"
            try {
                WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.View[@content-desc='YES']")));
                yesBtn.click();
            } catch (Exception e) {
                test.log(Status.WARNING, "YES button not found");
            }

            // 7. Wait 2 sec and verify logout
            Thread.sleep(2000);

            if (signInPage.isOnSignInPage()) {
                test.log(Status.INFO, "✓ Auto-Reset Successful: App logged out and returned to Sign In page");
            } else {
                test.log(Status.WARNING, "⚠ Auto-Reset Incomplete: Could not verify return to Sign In page");
            }

        } else {
            // FAIL: Did not navigate to Link Devices page
            test.log(Status.FAIL, "✗ Failed to navigate to 'LINK DEVICES' page");

            // Check if validation error is present to give more context
            if (signInPage.isAnyValidationVisible()) {
                String msg = signInPage.getValidationMessage();
                test.log(Status.INFO, "Validation error detected: " + msg);
            }

            Assert.fail("Expected to navigate to 'LINK DEVICES' page after valid login");
        }
    }
}
