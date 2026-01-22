package com.automation.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ForgotPasswordPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public ForgotPasswordPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==================== LOCATORS ====================

    // Step 1: Forgot Password button
    private final String forgotPasswordBtnXpath = "//android.widget.Button[@content-desc=\"Forgot Password?\"]";

    // Step 2 & 5: Email input box
    private final String emailInputXpath = "//android.widget.EditText";

    // Step 3 & 6: Send Message button
    private final String sendMessageBtnXpath = "//android.widget.Button[@content-desc=\"SEND MESSAGE\"]";

    // Step 4: Invalid email error message
    // Using contains for robustness and handling potential smart quotes
    private final String invalidEmailErrorXpath = "//android.view.View[contains(@content-desc, \"We couldn\") and contains(@content-desc, \"find an account\")]";

    // Step 7: Enter Verification Code page
    private final String verificationCodePageXpath = "//android.view.View[@content-desc=\"ENTER VERIFICATION CODE\"]";

    // Step 8: Verify button
    private final String verifyBtnXpath = "//android.widget.Button[@content-desc=\"VERIFY\"]";

    // Step 9: Failed to verify OTP dialog
    private final String failedToVerifyOtpXpath = "//android.view.View[@content-desc=\"FAILED TO VERIFY OTP\"]";

    // Step 10: OTP error message
    private final String otpErrorMessageXpath = "//android.view.View[@content-desc=\"Please try again later.\"]";

    // Step 11: OK button
    private final String okButtonXpath = "//android.widget.Button[@content-desc=\"OK\"]";

    // Step 12: Get a new code link
    private final String getNewCodeXpath = "//android.view.View[@content-desc=\"Get a new code\"]";

    // Step 13: Resend Successful dialog
    private final String resendSuccessfulDialogXpath = "//android.view.View[@content-desc=\"RESEND SUCCESSFUL\"]";
    private final String resendSuccessMessageXpath = "//android.view.View[@content-desc=\"Verification code has been send successfully.\"]";

    // Step 16: Reset Password page
    private final String resetPasswordPageXpath = "//android.view.View[@content-desc=\"RESET PASSWORD\"]";

    // Step 17: Password fields - Parent View elements (method will find EditText
    // child)
    private final String enterPasswordXpath = "//android.view.View[@content-desc=\"Enter Password\"]";
    private final String confirmPasswordXpath = "//android.view.View[@content-desc=\"Confirm Password\"]";

    // Step 18, 22, 26: Reset Password button
    private final String resetPasswordBtnXpath = "//android.widget.Button[@content-desc=\"RESET PASSWORD\"]";

    // Step 19: Password validation error
    private final String passwordValidationErrorXpath = "//android.view.View[@content-desc=\"Use at least 8 characters with uppercase, lowercase, number, and special symbol.\"]";

    // Step 23: Passwords do not match error
    private final String passwordMismatchErrorXpath = "//android.view.View[@content-desc=\"Passwords do not match\"]";

    // Step 27: Sign In page
    private final String signInPageXpath = "//android.view.View[@content-desc=\"SIGN IN\"]";

    // ==================== HELPER METHODS ====================

    private WebElement findElementWithFallback(String id, String xpath, String accessibilityId) {
        try {
            if (id != null && !id.isEmpty())
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } catch (TimeoutException ignored) {
        }
        try {
            if (xpath != null && !xpath.isEmpty())
                return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException ignored) {
        }
        try {
            if (accessibilityId != null && !accessibilityId.isEmpty())
                return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(accessibilityId)));
        } catch (TimeoutException ignored) {
        }

        throw new RuntimeException(
                "Element not found with ID: " + id + ", XPath: " + xpath + ", AccessID: " + accessibilityId);
    }

    private WebElement waitForElement(String xpath, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    private void clickClearAndSendKeys(String xpath, String text) throws InterruptedException {
        WebElement element = findElementWithFallback(null, xpath, null);
        element.click();
        Thread.sleep(500);
        element.clear();
        Thread.sleep(500);
        element.sendKeys(text);
    }

    /**
     * Robust password field interaction with explicit wait
     * Handles both parent View elements and direct EditText elements
     * - For Enter Password: clicks parent View, then finds EditText child
     * - For Confirm Password: directly interacts with EditText if XPath points to
     * it
     */
    private void enterPasswordField(String xpath, String text) throws InterruptedException {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Check if XPath already points to EditText (contains
        // "/android.widget.EditText")
        if (xpath.endsWith("/android.widget.EditText")) {
            // Direct EditText - click, clear, and send keys
            WebElement editText = longWait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            editText.click();
            Thread.sleep(500);
            editText.clear();
            Thread.sleep(500);
            editText.sendKeys(text);
        } else {
            // Parent View element - click parent, then find EditText child
            WebElement parentView = longWait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            parentView.click();
            Thread.sleep(500);

            // Find the EditText child within the parent View
            String editTextXpath = xpath + "/android.widget.EditText";
            WebElement editText = longWait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(editTextXpath)));

            // Clear and enter text in the EditText
            editText.clear();
            Thread.sleep(500);
            editText.sendKeys(text);
        }
    }

    // ==================== PAGE METHODS ====================

    /**
     * Step 1: Click Forgot Password button
     */
    public void clickForgotPassword() {
        WebElement btn = findElementWithFallback(null, forgotPasswordBtnXpath, "Forgot Password?");
        btn.click();
    }

    /**
     * Step 2: Enter invalid email (click, clear, send keys)
     */
    public void enterInvalidEmail(String email) throws InterruptedException {
        clickClearAndSendKeys(emailInputXpath, email);
    }

    /**
     * Step 3: Click Send Message button
     */
    public void clickSendMessage() {
        WebElement btn = findElementWithFallback(null, sendMessageBtnXpath, "SEND MESSAGE");
        btn.click();
    }

    /**
     * Step 4: Get runtime error message for invalid email
     * Waits up to 5 seconds for the error message to appear
     * Returns a message indicating no error was shown if element not found
     */
    /**
     * Step 4: Get runtime error message for invalid email
     * Waits up to 10 seconds for the error message to appear
     * Returns the content-desc attribute of the error view
     */
    public String getInvalidEmailErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(invalidEmailErrorXpath)));
            return errorMsg.getAttribute("content-desc");
        } catch (Exception e) {
            // Error message might not appear if email format is actually valid
            // or if the error appears in a different format
            return "No error message displayed: " + e.getMessage();
        }
    }

    /**
     * Step 5: Enter valid email (click, clear, send keys)
     */
    public void enterValidEmail(String email) throws InterruptedException {
        clickClearAndSendKeys(emailInputXpath, email);
    }

    /**
     * Step 7: Verify Enter Verification Code page is displayed
     */
    public boolean isVerificationCodePageDisplayed() {
        try {
            WebElement page = findElementWithFallback(null, verificationCodePageXpath, "ENTER VERIFICATION CODE");
            return page.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 7a: Enter OTP verification code
     * Uses the specific XPath for the OTP input field
     */
    public void enterOtpCode(String otpCode) throws InterruptedException {
        String otpFieldXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[1]";

        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement otpField = longWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(otpFieldXpath)));

        otpField.click();
        Thread.sleep(500);
        otpField.clear();
        Thread.sleep(500);
        otpField.sendKeys(otpCode);
        Thread.sleep(500);
    }

    /**
     * Step 12a: Clear all OTP verification code fields
     * Clicks and clears all 6 EditText fields
     * Field 6 uses index, fields 1-5 use text attribute
     */
    public void clearOtpCode() throws InterruptedException {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Clear field 6 using index-based XPath
        try {
            String field6Xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[6]";
            WebElement field6 = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(field6Xpath)));
            field6.click();
            Thread.sleep(200);
            field6.clear();
            Thread.sleep(200);
            System.out.println("✓ Cleared OTP field 6");
        } catch (Exception e) {
            System.out.println("⚠ Skipped OTP field 6 (not found)");
        }

        // Clear fields 5 to 1 using text attribute
        for (int i = 5; i >= 1; i--) {
            try {
                String fieldXpath = "//android.widget.EditText[@text=\"" + i + "\"]";
                WebElement field = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fieldXpath)));
                field.click();
                Thread.sleep(200);
                field.clear();
                Thread.sleep(200);
                System.out.println("✓ Cleared OTP field " + i);
            } catch (Exception e) {
                System.out.println("⚠ Skipped OTP field " + i + " (not found)");
            }
        }
    }

    /**
     * Step 8: Click Verify button (with invalid OTP)
     */
    public void clickVerifyButton() {
        WebElement btn = findElementWithFallback(null, verifyBtnXpath, "VERIFY");
        btn.click();
    }

    /**
     * Step 9: Check if Failed to Verify OTP dialog is displayed
     */
    public boolean isFailedToVerifyOtpDialogDisplayed() {
        try {
            WebElement dialog = findElementWithFallback(null, failedToVerifyOtpXpath, "FAILED TO VERIFY OTP");
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 10: Get OTP error message
     */
    public String getOtpErrorMessage() {
        try {
            WebElement errorElement = waitForElement(otpErrorMessageXpath, 5);
            return errorElement.getAttribute("content-desc");
        } catch (Exception e) {
            return "No OTP error message displayed";
        }
    }

    /**
     * Step 11: Click OK button
     */
    public void clickOkButton() {
        WebElement btn = findElementWithFallback(null, okButtonXpath, "OK");
        btn.click();
    }

    /**
     * Step 12: Wait until "Get a new code" is found and click it
     */
    public void waitAndClickGetNewCode(int timeoutSeconds) {
        WebElement link = waitForElement(getNewCodeXpath, timeoutSeconds);
        link.click();
    }

    /**
     * Step 13: Verify Resend Successful dialog is displayed
     */
    public boolean isResendSuccessfulDialogDisplayed() {
        try {
            WebElement dialog = findElementWithFallback(null, resendSuccessfulDialogXpath, "RESEND SUCCESSFUL");
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 13: Get resend success message
     */
    public String getResendSuccessMessage() {
        WebElement messageElement = findElementWithFallback(null, resendSuccessMessageXpath, null);
        return messageElement.getAttribute("content-desc");
    }

    /**
     * Step 16: Verify Reset Password page is displayed
     */
    public boolean isResetPasswordPageDisplayed() {
        try {
            WebElement page = findElementWithFallback(null, resetPasswordPageXpath, "RESET PASSWORD");
            return page.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 17: Enter passwords (click, clear, send keys)
     * Uses robust password field interaction with explicit waits
     */
    public void enterPasswords(String password, String confirmPassword) throws InterruptedException {
        enterPasswordField(enterPasswordXpath, password);
        Thread.sleep(500);
        enterPasswordField(confirmPasswordXpath, confirmPassword);
    }

    /**
     * Step 20: Enter wrong password in first field (click, clear, send keys)
     * Uses Enter Password parent View to find EditText child
     */
    public void enterWrongPassword1(String password) throws InterruptedException {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Click parent View first
        WebElement enterPasswordView = longWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(enterPasswordXpath)));
        enterPasswordView.click();
        Thread.sleep(500);

        // Find the EditText child within Enter Password View
        String enterPasswordEditTextXpath = enterPasswordXpath + "/android.widget.EditText";
        WebElement passwordField = longWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(enterPasswordEditTextXpath)));
        passwordField.clear();
        Thread.sleep(500);
        passwordField.sendKeys(password);
        Thread.sleep(500);
    }

    /**
     * Step 21: Enter wrong password in second field (click, clear, send keys)
     * Directly interacts with EditText child without waiting for parent View
     */
    /**
     * Step 21: Enter wrong password in second field (click, clear, send keys)
     * Uses fallback strategy: tries specific XPath first, then second EditText
     */
    public void enterWrongPassword2(String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Dismiss keyboard first
        try {
            ((io.appium.java_client.android.AndroidDriver) driver).hideKeyboard();
            Thread.sleep(500);
        } catch (Exception ignored) {
        }

        WebElement confirmPasswordField;
        try {
            confirmPasswordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(confirmPasswordXpath + "/android.widget.EditText")));
        } catch (org.openqa.selenium.TimeoutException e) {
            // Fallback: Confirm Password is the second EditText
            confirmPasswordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//android.widget.EditText)[2]")));
        }

        confirmPasswordField.click();
        Thread.sleep(500);
        confirmPasswordField.clear();
        Thread.sleep(500);
        confirmPasswordField.sendKeys(password);
        Thread.sleep(500);
    }

    /**
     * Step 24: Enter correct password in first field (click, clear, send keys)
     * Uses Enter Password parent View to find EditText child
     */
    public void enterCorrectPassword1(String password) throws InterruptedException {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Click parent View first
        WebElement enterPasswordView = longWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(enterPasswordXpath)));
        enterPasswordView.click();
        Thread.sleep(500);

        // Find the EditText child within Enter Password View
        String enterPasswordEditTextXpath = enterPasswordXpath + "/android.widget.EditText";
        WebElement passwordField = longWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(enterPasswordEditTextXpath)));
        passwordField.clear();
        Thread.sleep(500);
        passwordField.sendKeys(password);
        Thread.sleep(500);
    }

    /**
     * Step 25: Enter correct password in second field (click, clear, send keys)
     * Directly interacts with EditText child without waiting for parent View
     */
    /**
     * Step 25: Enter correct password in second field (click, clear, send keys)
     * Uses fallback strategy: tries specific XPath first, then second EditText
     */
    public void enterCorrectPassword2(String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Dismiss keyboard first
        try {
            ((io.appium.java_client.android.AndroidDriver) driver).hideKeyboard();
            Thread.sleep(500);
        } catch (Exception ignored) {
        }

        WebElement confirmPasswordField;
        try {
            confirmPasswordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(confirmPasswordXpath + "/android.widget.EditText")));
        } catch (org.openqa.selenium.TimeoutException e) {
            // Fallback: Confirm Password is the second EditText
            confirmPasswordField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//android.widget.EditText)[2]")));
        }

        confirmPasswordField.click();
        Thread.sleep(500);
        confirmPasswordField.clear();
        Thread.sleep(500);
        confirmPasswordField.sendKeys(password);
        Thread.sleep(500);
    }

    /**
     * Step 18, 22, 26: Click Reset Password button
     */
    /**
     * Step 18, 22, 26: Click Reset Password button
     */
    public void clickResetPasswordButton() {
        try {
            ((io.appium.java_client.android.AndroidDriver) driver).hideKeyboard();
            Thread.sleep(500);
        } catch (Exception ignored) {
        }

        // Try to scroll to the button using UiScrollable
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                            + "new UiSelector().description(\"RESET PASSWORD\"));"));
            Thread.sleep(500);
        } catch (Exception ignored) {
            // If scrolling fails or not needed, continue to click
        }

        WebElement btn = findElementWithFallback(null, resetPasswordBtnXpath, "RESET PASSWORD");
        btn.click();
    }

    /**
     * Step 19: Get password validation error message
     * Returns "Passwords do not match" error if validation error not found
     */
    public String getPasswordValidationError() {
        try {
            // First try to find the password validation error
            WebElement errorElement = waitForElement(passwordValidationErrorXpath, 3);
            return errorElement.getAttribute("content-desc");
        } catch (Exception e) {
            // If not found, try to find "Passwords do not match" error
            try {
                WebElement mismatchElement = waitForElement(passwordMismatchErrorXpath, 2);
                return mismatchElement.getAttribute("content-desc");
            } catch (Exception e2) {
                return "No password error message displayed";
            }
        }
    }

    /**
     * Step 23: Get password mismatch error message
     * Checks for rule validation errors first (using content-desc contains)
     * Then checks for specific mismatch error
     */
    public String getPasswordMismatchError() {
        // First check for password rule validation message
        try {
            List<WebElement> ruleErrors = driver.findElements(
                    By.xpath("//android.view.View[contains(@content-desc,'Use at least 8 characters')]"));

            if (!ruleErrors.isEmpty()) {
                // Rule error found
                return ruleErrors.get(0).getAttribute("content-desc");
            }
        } catch (Exception ignored) {
        }

        // If no rule error, check for mismatch error
        try {
            WebElement errorElement = waitForElement(passwordMismatchErrorXpath, 5);
            return errorElement.getAttribute("content-desc");
        } catch (Exception e) {
            return "No password mismatch error displayed";
        }
    }

    /**
     * Dismiss password error overlay by clicking and clearing password field
     * This makes the password fields accessible after an error is displayed
     */
    public void dismissPasswordError() {
        try {
            // Try to find any visible password EditText field with bullet characters
            String[] possiblePasswordTexts = { "••••", "•••", "••", "•", "••••••••", "••••••" };

            for (String passwordText : possiblePasswordTexts) {
                try {
                    String passwordFieldXpath = "//android.widget.EditText[@text='" + passwordText + "']";
                    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                    WebElement passwordField = shortWait.until(
                            ExpectedConditions.elementToBeClickable(By.xpath(passwordFieldXpath)));

                    // Click and clear the field to dismiss error
                    passwordField.click();
                    Thread.sleep(300);
                    passwordField.clear();
                    Thread.sleep(300);
                    return; // Successfully dismissed error
                } catch (Exception ignored) {
                    // Try next password text pattern
                }
            }

            // If no password field with bullets found, try clicking password parent Views
            // Try Enter Password first
            try {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                WebElement enterPasswordView = shortWait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath(enterPasswordXpath)));
                enterPasswordView.click();
                Thread.sleep(300);
                return; // Successfully dismissed by clicking Enter Password
            } catch (Exception ignored) {
                // Try Confirm Password instead
            }

            // Try Confirm Password
            try {
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                WebElement confirmPasswordView = shortWait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath(confirmPasswordXpath)));
                confirmPasswordView.click();
                Thread.sleep(300);
            } catch (Exception ignored) {
                // Error might already be dismissed or not present
            }
        } catch (Exception e) {
            // Error dismissal failed, but continue anyway
            System.out.println("Could not dismiss password error - continuing anyway");
        }
    }

    /**
     * Step 27: Verify Sign In page is displayed
     * XPath: //android.view.View[@content-desc="SIGN IN"]
     */
    public boolean isSignInPageDisplayed() {
        try {
            WebElement page = findElementWithFallback(null, signInPageXpath, "SIGN IN");
            return page.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for specified seconds
     */
    public void waitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }
}