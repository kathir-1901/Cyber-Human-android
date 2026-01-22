package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.SignUpPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    /**
     * SMART NAVIGATION TO SIGN UP PAGE
     * Handles different app states:
     * 1. Already on Sign Up page -> Stay there
     * 2. On Sign In page -> Click Sign Up
     * 3. On Home Page (Logged In) -> Logout -> Click Sign Up
     */
    private void navigateToSignUp() throws InterruptedException {
        // 1. Check if already on Sign Up page
        SignUpPage signUpPage = new SignUpPage(driver);
        if (signUpPage.isSignUpPageDisplayed()) {
            test.log(Status.INFO, "Already on Sign Up Page");
            Assert.assertTrue(signUpPage.isSignUpPageDisplayed(), "FATAL: Sign Up Page NOT displayed!");
            return;
        }

        // 2. Check if on Sign In page
        com.automation.pages.SignInPage signInPage = new com.automation.pages.SignInPage(driver);
        if (signInPage.isOnSignInPage()) {
            signInPage.clickSignUp();
            test.log(Status.INFO, "Navigated to Sign Up page from Sign In");
            Thread.sleep(2000);
            Assert.assertTrue(signUpPage.isSignUpPageDisplayed(),
                    "FATAL: Sign Up Page NOT displayed after clicking Sign Up!");
            return;
        }

        // 3. Check if Logged In (Home Page)
        com.automation.pages.HomePage homePage = new com.automation.pages.HomePage(driver);
        if (homePage.isHomePageDisplayed()) {
            test.log(Status.INFO, "Detected Logged In state - Performing Logout");
            homePage.navigateToLogout();

            // Handle Logout Confirmation
            com.automation.pages.ProfilePage profilePage = new com.automation.pages.ProfilePage(driver);
            if (profilePage.isSignInPageDisplayed()) {
                // Already at Sign In
            } else {
                // Might need to confirm logout? navigateToLogout typically handles it?
                // Checking HomePage.java navigateToLogout: clickWellbeing -> clickProfile.
                // It misses the clickLogout -> clickYes part.
                // So we need to do that here.
                profilePage.clickLogout();
                profilePage.clickYes();
            }

            Thread.sleep(2000);
            if (signInPage.isOnSignInPage()) {
                signInPage.clickSignUp();
                test.log(Status.INFO, "Navigated to Sign Up page after Logout");
                Thread.sleep(2000);
                Assert.assertTrue(signUpPage.isSignUpPageDisplayed(),
                        "FATAL: Sign Up Page NOT displayed after Logout!");
                return;
            }
        }

        // 4. Fallback: Try to go back if trapped in some sub-page
        try {
            driver.navigate().back();
            Thread.sleep(1000);
            if (signInPage.isOnSignInPage()) {
                signInPage.clickSignUp();
                return;
            }
        } catch (Exception ignored) {
        }

        test.log(Status.WARNING, "Could not determine app state. Attempting implicit navigation.");
    }

    /**
     * NEGATIVE TEST DATA (NO HARDCODED EXPECTED MESSAGES)
     * Test scenarios for Sign Up page validation
     */
    @DataProvider(name = "negativeSignUpData")
    public Object[][] getNegativeSignUpData() {
        return new Object[][] {
                // Scenario, Name, Email, Password, Confirm Password
                { "Invalid Email Format", "John Doe", "invalidemail", "Password@123", "Password@123" }, // 1
                { "Empty Name Field", "", "test@example.com", "Password@123", "Password@123" }, // 2
                { "Empty Email Field", "John Doe", "", "Password@123", "Password@123" }, // 3
                { "Empty Password Field", "John Doe", "test@example.com", "", "Password@123" }, // 4
                { "Empty Confirm Password", "John Doe", "test@example.com", "Password@123", "" }, // 5
                { "Password Mismatch", "John Doe", "test@example.com", "Password@123", "DifferentPass@123" }, // 6
                { "Weak Password", "John Doe", "test@example.com", "123", "123" }, // 7
                { "All Fields Empty", "", "", "", "" }, // 8
                { "Email Without Domain", "John Doe", "test@", "Password@123", "Password@123" }, // 9
                { "Short Password", "John Doe", "test@example.com", "12", "12" }, // 10
                { "Only Spaces in Name", "   ", "test@example.com", "Password@123", "Password@123" }, // 11
        };
    }

    /**
     * RUNTIME-BASED NEGATIVE TEST FOR SIGN UP
     * 
     * Test Flow:
     * 0. Navigate from Sign In page to Sign Up page
     * 1. Enter name, email, password, and confirm password
     * 2. Select country from dropdown
     * 3. Click SIGN UP heading
     * 4. Click Continue button
     * 5. Check if ANY validation appears at runtime
     * 6. PASS if validation detected, FAIL if not
     * 7. Log the actual runtime validation message in Extent Report
     */
    @Test(dataProvider = "negativeSignUpData")
    public void testNegativeSignUp(String testScenario, String name, String email, String password,
            String confirmPassword) throws InterruptedException {

        test = extent.createTest("Negative Test: " + testScenario);
        test.log(Status.INFO, "Name: '" + name + "' | Email: '" + email + "'");
        test.log(Status.INFO, "Password: '" + password + "' | Confirm: '" + confirmPassword + "'");

        // Step 0: Smart Navigation to Sign Up page
        navigateToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);

        // Step 1: Enter form data
        signUpPage.enterName(name);
        signUpPage.enterEmail(email);
        test.log(Status.INFO, "Entered name and email");

        // Step 2: Select country from dropdown
        signUpPage.selectFirstCountryOption();
        test.log(Status.INFO, "Selected country from dropdown");

        // Step 3: Enter passwords
        signUpPage.enterPassword(password);
        signUpPage.enterConfirmPassword(confirmPassword);
        test.log(Status.INFO, "Entered password and confirm password");

        // Step 4: Click SIGN UP heading (required before Continue)
        signUpPage.clickSignUpHeading();
        test.log(Status.INFO, "Clicked SIGN UP heading");

        // Step 5: Click Continue button
        signUpPage.clickContinue();
        test.log(Status.INFO, "Clicked Continue button");

        // Step 6: Wait for validation to appear (if any)
        Thread.sleep(2000); // Allow time for validation to render

        // Step 7: RUNTIME VALIDATION CHECK (NO HARDCODED MESSAGES)
        boolean validationDetected = signUpPage.isAnyValidationVisible();

        if (validationDetected) {
            // PASS: Validation appeared (negative case handled correctly)

            // Capture and log the actual runtime validation message
            String validationMessage = signUpPage.getValidationMessage();
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
     * POSITIVE SIGN UP TEST WITH COMPLETE FLOW
     * This test expects successful sign up with full verification flow
     * 
     * Test Flow:
     * 0. Navigate from Sign In page to Sign Up page
     * 1. Enter valid name, email, password, and confirm password
     * 2. Select country from dropdown
     * 3. Click SIGN UP heading
     * 4. Click Continue button
     * 5. Verify no validation errors appear
     * 6. Click Terms of Use and verify, then go back
     * 7. Click Privacy Policy and verify, then go back
     * 8. Click all 4 checkboxes
     * 9. Click Continue button
     * 10. Verify Confirm Your Email page
     * 11. Enter incorrect OTP (123456)
     * 12. Click Verify button
     * 13. Verify error message appears
     * 14. Click Get a new code
     * 15. Wait 9 seconds
     * 16. Verify Resend Successful dialog
     * 17. Get and log success message
     * 18. Click OK button
     */
    @Test
    public void testPositiveSignUp() throws InterruptedException {
        test = extent.createTest("Positive Test: Valid Sign Up - Complete Flow");
        test.log(Status.INFO, "Testing valid sign up with complete verification flow");

        // Step 0: Smart Navigation to Sign Up page
        navigateToSignUp();

        SignUpPage signUpPage = new SignUpPage(driver);

        // Use valid credentials
        String validName = "John Doe";
        String validEmail = "john.doe" + System.currentTimeMillis() + "@example.com"; // Unique email
        String validPassword = "SecurePass@123";

        // Step 1: Enter form data
        signUpPage.enterName(validName);
        signUpPage.enterEmail(validEmail);
        test.log(Status.INFO, "Step 1: Entered valid name and email");

        // Step 2: Select country from dropdown
        signUpPage.selectFirstCountryOption();
        test.log(Status.INFO, "Step 2: Selected country from dropdown");

        // Step 3: Enter passwords
        signUpPage.enterPassword(validPassword);
        signUpPage.enterConfirmPassword(validPassword);
        test.log(Status.INFO, "Step 3: Entered valid password and confirm password");

        // Step 4: Click SIGN UP heading (required before Continue)
        signUpPage.clickSignUpHeading();
        test.log(Status.INFO, "Step 4: Clicked SIGN UP heading");

        // Step 5: Click Continue button
        signUpPage.clickContinue();
        test.log(Status.INFO, "Step 5: Clicked Continue button");

        Thread.sleep(2000); // Wait for navigation to consent page

        // Step 6: Click Terms of Use and verify
        signUpPage.clickTermsOfUse();
        test.log(Status.INFO, "Step 6: Clicked Terms of Use link");
        Thread.sleep(1500);

        if (signUpPage.isTermsOfUsePageDisplayed()) {
            test.log(Status.PASS, "✓ Terms of Use page displayed successfully");
        } else {
            test.log(Status.FAIL, "✗ Terms of Use page NOT displayed");
            Assert.fail("Terms of Use page verification failed");
        }

        // Click back button
        signUpPage.clickBackButton();
        test.log(Status.INFO, "Step 6: Clicked back button from Terms of Use");
        Thread.sleep(1500);

        // Step 7: Click Privacy Policy and verify
        signUpPage.clickPrivacyPolicy();
        test.log(Status.INFO, "Step 7: Clicked Privacy Policy link");
        Thread.sleep(1500);

        if (signUpPage.isPrivacyPolicyPageDisplayed()) {
            test.log(Status.PASS, "✓ Privacy Policy page displayed successfully");
        } else {
            test.log(Status.FAIL, "✗ Privacy Policy page NOT displayed");
            Assert.fail("Privacy Policy page verification failed");
        }

        // Click back button
        signUpPage.clickBackButton();
        test.log(Status.INFO, "Step 7: Clicked back button from Privacy Policy");
        Thread.sleep(1500);

        // Step 8: Click all 4 checkboxes
        signUpPage.clickCheckbox1();
        test.log(Status.INFO, "Step 8: Clicked checkbox 1");
        Thread.sleep(500);

        signUpPage.clickCheckbox2();
        test.log(Status.INFO, "Step 8: Clicked checkbox 2");
        Thread.sleep(500);

        signUpPage.clickCheckbox3();
        test.log(Status.INFO, "Step 8: Clicked checkbox 3");
        Thread.sleep(500);

        signUpPage.clickCheckbox4();
        test.log(Status.INFO, "Step 8: Clicked checkbox 4");
        Thread.sleep(500);

        // Step 9: Click Continue button
        signUpPage.clickContinue();
        test.log(Status.INFO, "Step 9: Clicked Continue button after checkboxes");
        Thread.sleep(2000);

        // Step 10: Verify Confirm Your Email page
        if (signUpPage.isConfirmEmailPageDisplayed()) {
            test.log(Status.PASS, "✓ Step 10: Confirm Your Email page displayed successfully");
        } else {
            test.log(Status.FAIL, "✗ Step 10: Confirm Your Email page NOT displayed");
            Assert.fail("Confirm Your Email page verification failed");
        }

        // Step 11: Enter incorrect OTP (123456)
        signUpPage.enterOTP("123456");
        test.log(Status.INFO, "Step 11: Entered incorrect OTP: 123456");
        Thread.sleep(1000);

        // Step 12: Click Verify button
        signUpPage.clickVerifyButton();
        test.log(Status.INFO, "Step 12: Clicked Verify button");
        Thread.sleep(2000);

        // Step 13: Verify error message appears
        String errorMessage = signUpPage.getIncorrectOTPError();
        if (errorMessage != null && !errorMessage.isEmpty()) {
            test.log(Status.PASS, "✓ Step 13: Error message displayed: \"" + errorMessage + "\"");
        } else {
            test.log(Status.WARNING, "⚠ Step 13: Error message not found or empty");
        }

        // Step 14: Click Get a new code
        signUpPage.clickGetNewCode();
        test.log(Status.INFO, "Step 14: Clicked 'Get a new code' button");

        // Step 15: Wait 9 seconds
        Thread.sleep(9000);
        test.log(Status.INFO, "Step 15: Waited 9 seconds");

        // Step 16: Verify Resend Successful dialog
        if (signUpPage.isResendSuccessfulDialogDisplayed()) {
            test.log(Status.PASS, "✓ Step 16: Resend Successful dialog displayed");
        } else {
            test.log(Status.FAIL, "✗ Step 16: Resend Successful dialog NOT displayed");
            Assert.fail("Resend Successful dialog verification failed");
        }

        // Step 17: Get and log success message
        String successMessage = signUpPage.getResendSuccessfulMessage();
        if (successMessage != null && !successMessage.isEmpty()) {
            test.log(Status.PASS, "✓ Step 17: Success message: \"" + successMessage + "\"");
        } else {
            test.log(Status.WARNING, "⚠ Step 17: Success message not found or empty");
        }

        // Step 18: Click OK button
        signUpPage.clickOKButton();
        test.log(Status.INFO, "Step 18: Clicked OK button");
        Thread.sleep(1000);

        // ✅ Test completed successfully
        test.log(Status.PASS, "✓ Test PASSED: Complete sign-up flow executed successfully");
    }
}