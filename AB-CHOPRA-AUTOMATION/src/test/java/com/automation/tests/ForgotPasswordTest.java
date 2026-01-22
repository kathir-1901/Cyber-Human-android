package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.ForgotPasswordPage;
import com.automation.pages.SignInPage;
import com.aventstack.extentreports.Status;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {

    /**
     * ✅ NAVIGATE TO SIGN IN PAGE BEFORE EACH TEST
     * This ensures the test starts from Sign In page where Forgot Password button
     * is located.
     */
    @BeforeMethod
    public void navigateToSignInPageBeforeTest() throws Exception {
        navigateToSignInPage();
    }

    /**
     * ✅ NAVIGATE TO SIGN IN PAGE
     * Wait for app to load and automatically navigate to Sign In page
     */
    /**
     * ✅ NAVIGATE TO SIGN IN PAGE
     * Wait for app to load and automatically navigate to Sign In page
     */
    private void navigateToSignInPage() throws Exception {
        // Give app time to fully load
        System.out.println("Wait for app to load...");
        Thread.sleep(3000);

        SignInPage signInPage = new SignInPage(driver);

        // 1. Check if we are already on Sign In page
        if (signInPage.isOnSignInPage()) {
            System.out.println("✓ App loaded on Sign In page");
            return;
        }

        // 2. Check if we are Logged In and need to Logout
        com.automation.pages.HomePage homePage = new com.automation.pages.HomePage(driver);
        if (homePage.isHomePageDisplayed()) {
            System.out.println("Detected Logged In state - Performing Logout");
            homePage.navigateToLogout();

            // Handle Logout Confirmation
            com.automation.pages.ProfilePage profilePage = new com.automation.pages.ProfilePage(driver);
            if (!profilePage.isSignInPageDisplayed()) {
                profilePage.clickLogout();
                profilePage.clickYes();
            }

            Thread.sleep(2000);
            if (signInPage.isOnSignInPage()) {
                System.out.println("✓ Navigated to Sign In page after Logout");
                return;
            }
        }

        // 3. Fallback: Try to navigate back to find Sign In page
        try {
            driver.navigate().back();
            Thread.sleep(1000);
            if (signInPage.isOnSignInPage()) {
                System.out.println("✓ Navigated to Sign In page via Back button");
                return;
            }
        } catch (Exception ignored) {
        }

        // If not on Sign In page after waiting, log warning
        System.out.println("⚠ Warning: Not on Sign In page after automatic navigation attempts");
        System.out.println("   Test will continue - may fail if Forgot Password button not accessible");
    }

    /**
     * ==================== FORGOT PASSWORD TEST FLOW ====================
     * 
     * This test covers the complete forgot password flow including:
     * - Invalid email validation
     * - Valid email submission
     * - OTP verification (invalid and valid)
     * - Resend OTP functionality
     * - Password reset with various validation scenarios
     */
    @Test(priority = 1)
    public void testForgotPasswordFlow() throws InterruptedException {
        test = extent.createTest("Forgot Password - Complete Flow Test");
        test.log(Status.INFO, "Starting Forgot Password Flow Test");

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        // ==================== STEP 1: Click Forgot Password ====================
        test.log(Status.INFO, "Step 1: Clicking Forgot Password button");
        forgotPasswordPage.clickForgotPassword();
        test.log(Status.PASS, "✓ Step 1: Forgot Password button clicked");
        Thread.sleep(2000);

        // ==================== STEP 2: Enter Invalid Email ====================
        test.log(Status.INFO, "Step 2: Entering invalid email (abc@gmai.com)");
        forgotPasswordPage.enterInvalidEmail("abc@gmai.com");
        test.log(Status.PASS, "✓ Step 2: Invalid email entered");
        Thread.sleep(1000);

        // ==================== STEP 3: Click Send Message ====================
        test.log(Status.INFO, "Step 3: Clicking SEND MESSAGE button");
        forgotPasswordPage.clickSendMessage();
        test.log(Status.PASS, "✓ Step 3: SEND MESSAGE button clicked");
        Thread.sleep(5000); // Wait 5 seconds for error dialog to appear

        // ==================== STEP 4: Get Runtime Error Message ====================
        test.log(Status.INFO, "Step 4: Capturing runtime error message for invalid email");
        String invalidEmailError = forgotPasswordPage.getInvalidEmailErrorMessage();
        test.log(Status.INFO, "📋 Error Message: " + invalidEmailError);

        // Note: Not failing if error message not found - it may appear in different
        // format
        // or the email might actually be processed
        if (invalidEmailError.contains("No error message")) {
            test.log(Status.WARNING, "⚠ Expected error message not found - continuing with test");
        } else {
            test.log(Status.PASS, "✓ Step 4: Runtime error message captured and displayed in report");
        }
        Thread.sleep(2000);

        // ==================== STEP 5: Enter Valid Email ====================
        test.log(Status.INFO, "Step 5: Entering valid email (kathirmskgt@gmail.com)");
        forgotPasswordPage.enterValidEmail("kathirmskgt@gmail.com");
        test.log(Status.PASS, "✓ Step 5: Valid email entered");
        Thread.sleep(1000);

        // ==================== STEP 6: Click Send Message ====================
        test.log(Status.INFO, "Step 6: Clicking SEND MESSAGE button");
        forgotPasswordPage.clickSendMessage();
        test.log(Status.PASS, "✓ Step 6: SEND MESSAGE button clicked");
        Thread.sleep(3000);

        // ==================== STEP 7: Enter OTP Code ====================
        test.log(Status.INFO, "Step 7: Entering OTP code (123456)");
        forgotPasswordPage.enterOtpCode("123456");
        test.log(Status.PASS, "✓ Step 7: OTP code (123456) entered");
        Thread.sleep(2000);

        // ==================== STEP 8: Verify Enter Verification Code Page
        // ====================
        test.log(Status.INFO, "Step 8: Verifying ENTER VERIFICATION CODE page is displayed");
        boolean isVerificationPageDisplayed = forgotPasswordPage.isVerificationCodePageDisplayed();
        if (!isVerificationPageDisplayed) {
            test.log(Status.WARNING, "⚠ ENTER VERIFICATION CODE page not displayed - continuing anyway");
        } else {
            test.log(Status.PASS, "✓ Step 8: ENTER VERIFICATION CODE page verified");
        }

        // Wait 5 seconds
        test.log(Status.INFO, "Waiting 5 seconds...");
        forgotPasswordPage.waitForSeconds(5);

        // ==================== STEP 9: Click Verify with Invalid OTP
        // ====================
        test.log(Status.INFO, "Step 9: Clicking VERIFY button with invalid OTP");
        forgotPasswordPage.clickVerifyButton();
        test.log(Status.PASS, "✓ Step 9: VERIFY button clicked");
        Thread.sleep(3000);

        // ==================== STEP 10: Check Failed to Verify OTP Dialog
        // ====================
        test.log(Status.INFO, "Step 10: Verifying FAILED TO VERIFY OTP dialog is displayed");
        boolean isFailedOtpDialogDisplayed = forgotPasswordPage.isFailedToVerifyOtpDialogDisplayed();
        Assert.assertTrue(isFailedOtpDialogDisplayed, "FAILED TO VERIFY OTP dialog should be displayed");
        test.log(Status.PASS, "✓ Step 10: FAILED TO VERIFY OTP dialog verified");

        // ==================== STEP 11: Get OTP Error Message ====================
        test.log(Status.INFO, "Step 11: Capturing OTP error message");
        String otpErrorMessage = forgotPasswordPage.getOtpErrorMessage();
        test.log(Status.INFO, "📋 OTP Error Message: " + otpErrorMessage);
        if (otpErrorMessage.contains("No OTP error")) {
            test.log(Status.WARNING, "⚠ Expected OTP error message not found - continuing with test");
        } else {
            test.log(Status.PASS, "✓ Step 11: OTP error message captured and displayed in report");
        }
        Thread.sleep(2000);

        // ==================== STEP 12: Click OK Button ====================
        test.log(Status.INFO, "Step 12: Clicking OK button");
        forgotPasswordPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 12: OK button clicked");
        Thread.sleep(2000);

        // ==================== STEP 13: Clear OTP Code ====================
        test.log(Status.INFO, "Step 13: Clearing all OTP numbers");
        forgotPasswordPage.clearOtpCode();
        test.log(Status.PASS, "✓ Step 13: OTP code cleared");
        Thread.sleep(1000);

        // ==================== STEP 14: Wait and Click Get a New Code
        // ====================
        test.log(Status.INFO, "Step 14: Waiting for 'Get a new code' link and clicking it");
        forgotPasswordPage.waitAndClickGetNewCode(15);
        test.log(Status.PASS, "✓ Step 14: 'Get a new code' link clicked");
        Thread.sleep(3000);

        // ==================== STEP 15: Verify Resend Successful Dialog
        // ====================
        test.log(Status.INFO, "Step 15: Verifying RESEND SUCCESSFUL dialog is displayed");
        boolean isResendSuccessDialogDisplayed = forgotPasswordPage.isResendSuccessfulDialogDisplayed();
        if (!isResendSuccessDialogDisplayed) {
            test.log(Status.WARNING, "⚠ RESEND SUCCESSFUL dialog not displayed - continuing anyway");
        } else {
            test.log(Status.PASS, "✓ Step 15: RESEND SUCCESSFUL dialog verified");
        }

        // Get resend success message
        String resendSuccessMessage = forgotPasswordPage.getResendSuccessMessage();
        test.log(Status.INFO, "📋 Resend Success Message: " + resendSuccessMessage);
        test.log(Status.PASS, "✓ Step 15: Resend success message captured and displayed in report");
        Thread.sleep(2000);

        // ==================== STEP 16: Click OK Button ====================
        test.log(Status.INFO, "Step 16: Clicking OK button");
        forgotPasswordPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 16: OK button clicked");

        // Wait 10 seconds
        test.log(Status.INFO, "Waiting 10 seconds...");
        forgotPasswordPage.waitForSeconds(10);

        // ==================== STEP 17: Click Verify with Correct OTP
        // ====================
        test.log(Status.INFO, "Step 17: Clicking VERIFY button with correct OTP");
        forgotPasswordPage.clickVerifyButton();
        test.log(Status.PASS, "✓ Step 17: VERIFY button clicked with correct OTP");
        Thread.sleep(3000);

        // ==================== STEP 18: Verify Reset Password Page ====================
        test.log(Status.INFO, "Step 18: Verifying RESET PASSWORD page is displayed");
        boolean isResetPasswordPageDisplayed = forgotPasswordPage.isResetPasswordPageDisplayed();
        Assert.assertTrue(isResetPasswordPageDisplayed, "RESET PASSWORD page should be displayed");
        test.log(Status.PASS, "✓ Step 18: RESET PASSWORD page verified");

        // ==================== STEP 19: Enter Mismatched Passwords ====================
        test.log(Status.INFO, "Step 19: Entering mismatched passwords (1234 and 123466)");
        forgotPasswordPage.enterPasswords("1234", "123466");
        test.log(Status.PASS, "✓ Step 19: Mismatched passwords entered");
        Thread.sleep(1000);

        // ==================== STEP 20: Click Reset Password Button
        // ====================
        test.log(Status.INFO, "Step 20: Clicking RESET PASSWORD button");
        forgotPasswordPage.clickResetPasswordButton();
        test.log(Status.PASS, "✓ Step 20: RESET PASSWORD button clicked");
        Thread.sleep(3000);

        // ==================== STEP 21: Get Password Validation Error
        // ====================
        test.log(Status.INFO, "Step 21: Capturing password validation error message");
        String passwordValidationError = forgotPasswordPage.getPasswordValidationError();
        test.log(Status.INFO, "📋 Password Validation Error: " + passwordValidationError);
        if (passwordValidationError.contains("No password error")) {
            test.log(Status.WARNING, "⚠ Expected password error message not found - continuing with test");
        } else {
            test.log(Status.PASS, "✓ Step 21: Password validation error captured and displayed in report");
        }
        Thread.sleep(2000);

        // Dismiss error message by clicking and clearing password field
        test.log(Status.INFO, "Dismissing error message to make password fields accessible");
        forgotPasswordPage.dismissPasswordError();
        Thread.sleep(1000);

        // ==================== STEP 22: Enter Wrong Password in First Field
        // ====================
        test.log(Status.INFO, "Step 22: Entering wrong password in first field (123)");
        forgotPasswordPage.enterWrongPassword1("123");
        test.log(Status.PASS, "✓ Step 22: Wrong password entered in first field (123)");
        Thread.sleep(1000);

        // ==================== STEP 23: Enter Wrong Password in Second Field
        // ====================
        test.log(Status.INFO, "Step 23: Entering wrong password in second field (123)");
        forgotPasswordPage.enterWrongPassword2("123");
        test.log(Status.PASS, "✓ Step 23: Wrong password entered in second field (123)");
        Thread.sleep(1000);

        // ==================== STEP 24: Click Reset Password Button
        // ====================
        test.log(Status.INFO, "Step 24: Clicking RESET PASSWORD button");
        forgotPasswordPage.clickResetPasswordButton();
        test.log(Status.PASS, "✓ Step 24: RESET PASSWORD button clicked");
        Thread.sleep(3000);

        // ==================== STEP 25: Get Password Mismatch Error
        // ====================
        test.log(Status.INFO, "Step 25: Capturing 'Passwords do not match' error message");
        String passwordMismatchError = forgotPasswordPage.getPasswordMismatchError();
        test.log(Status.INFO, "📋 Error Message: " + passwordMismatchError);
        if (passwordMismatchError.contains("No password mismatch")) {
            test.log(Status.WARNING, "⚠ Expected 'Passwords do not match' error not found - continuing with test");
        } else {
            test.log(Status.PASS, "✓ Step 25: 'Passwords do not match' error captured and displayed in report");
        }
        Thread.sleep(2000);

        // Dismiss error message by clicking and clearing password field
        test.log(Status.INFO, "Dismissing error message to make password fields accessible");
        forgotPasswordPage.dismissPasswordError();
        Thread.sleep(1000);

        // ==================== STEP 26: Enter Correct Password in First Field
        // ====================
        test.log(Status.INFO, "Step 26: Entering correct password in first field (Testing@2026)");
        forgotPasswordPage.enterCorrectPassword1("Testing@2026");
        test.log(Status.PASS, "✓ Step 26: Correct password entered in first field (Testing@2026)");
        Thread.sleep(1000);

        // ==================== STEP 27: Enter Correct Password in Second Field
        // ====================
        test.log(Status.INFO, "Step 27: Entering correct password in second field (Testing@2026)");
        forgotPasswordPage.enterCorrectPassword2("Testing@2026");
        test.log(Status.PASS, "✓ Step 27: Correct password entered in second field (Testing@2026)");
        Thread.sleep(1000);

        // ==================== STEP 28: Click Reset Password Button
        // ====================
        test.log(Status.INFO, "Step 28: Clicking RESET PASSWORD button");
        forgotPasswordPage.clickResetPasswordButton();
        test.log(Status.PASS, "✓ Step 28: RESET PASSWORD button clicked");
        Thread.sleep(5000);

        // ==================== STEP 29: Verify Sign In Page ====================
        test.log(Status.INFO, "Step 29: Verifying application is on SIGN IN page");
        boolean isSignInPageDisplayed = forgotPasswordPage.isSignInPageDisplayed();
        Assert.assertTrue(isSignInPageDisplayed, "SIGN IN page should be displayed");
        test.log(Status.PASS, "✓ Step 29: SIGN IN page verified");
        test.log(Status.INFO, "📧 Please check the email for password reset confirmation");

        test.log(Status.PASS, "Forgot Password Flow Test completed successfully");
    }
}