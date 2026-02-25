package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.SignInPage;
import com.automation.pages.HomePage;
import com.automation.pages.LinkDevicesPage;
import com.automation.pages.ProfilePage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
     */
    @Test
    public void testPositiveSignIn() throws InterruptedException {
        test = extent.createTest("Positive Test: Valid Login");
        test.log(Status.INFO, "Testing valid credentials");

        SignInPage signInPage = new SignInPage(driver);

        // Use valid credentials
        String validEmail = "ramesh@navadhiti.com";
        String validPassword = "Human@2026";

        signInPage.enterEmail(validEmail);
        signInPage.enterPassword(validPassword);
        test.log(Status.INFO, "Entered valid credentials");

        signInPage.clickContinue();
        test.log(Status.INFO, "Clicked Continue button");
        Thread.sleep(3000);

        // Step 1: Click SKIP FOR NOW (Pattern from EditProfileTest)
        // This handles Link Devices / 2FA / any interstitial screen
        try {
            LinkDevicesPage linkDevicesPage = new LinkDevicesPage(driver);
            linkDevicesPage.clickSkipForNow();
            test.log(Status.INFO, "Clicked SKIP FOR NOW");
            Thread.sleep(2000);
        } catch (Exception ignored) {
            test.log(Status.INFO, "SKIP FOR NOW not present — continuing");
        }

        // Step 2-4: Wait for Home page (up to 15s) and navigate
        HomePage homePage = new HomePage(driver);
        boolean homeFound = false;
        for (int i = 0; i < 5; i++) {
            if (homePage.isHomePageDisplayed()) {
                homeFound = true;
                break;
            }
            test.log(Status.INFO, "Waiting for Home page... (Attempt " + (i + 1) + ")");
            Thread.sleep(3000);
        }

        if (homeFound) {
            test.log(Status.PASS, "✓ Home page displayed successfully");

            // Step 3: Wellbeing Dashboard
            homePage.clickWellbeingDashboard();
            test.log(Status.INFO, "Clicked Wellbeing Dashboard menu");
            Thread.sleep(2000);

            // Step 4: Click PROFILE
            homePage.clickProfile();
            test.log(Status.INFO, "Clicked PROFILE button");
            Thread.sleep(2000);

            // Step 5: Click LOG OUT
            ProfilePage profilePage = new ProfilePage(driver);
            profilePage.clickLogout();
            test.log(Status.INFO, "Clicked LOG OUT button");
            Thread.sleep(1000);

            // Step 6: Click YES in logout confirmation
            profilePage.clickYes();
            test.log(Status.INFO, "Clicked YES for logout confirmation");
            Thread.sleep(2000);

            // Step 7: Final Verification - SIGN IN page
            if (profilePage.isSignInPageDisplayed()) {
                test.log(Status.PASS, "✓ SIGN IN page displayed - Test Passed");
            } else {
                test.log(Status.FAIL, "✗ Not on Sign In page after logout");
                Assert.fail("Test failed: SIGN IN page not displayed after logout");
            }
        } else {
            test.log(Status.FAIL, "Home page not detected after 15s timeout");
            Assert.fail("Test failed: Home page not reached after login");
        }
    }
}
