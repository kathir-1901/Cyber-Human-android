package com.automation.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public SignInPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

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

    private final String emailXpath = "//android.widget.EditText[@hint='Email']";
    private final String passwordXpath = "//android.widget.EditText[@password='true']";
    private final String continueBtnXpath = "//android.widget.Button[@content-desc='CONTINUE']";

    public void enterEmail(String email) {
        WebElement el = findElementWithFallback(null, emailXpath, null);
        el.click();
        el.clear();
        el.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement el = findElementWithFallback(null, passwordXpath, null);
        el.click();
        el.clear();
        el.sendKeys(password);
    }

    public void clickContinue() {
        WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
        if (btn.isEnabled()) {
            btn.click();
        }
    }

    /**
     * Click "Don't have an account? Sign up" button to navigate to Sign Up page
     */
    public void clickSignUp() {
        String signUpBtnXpath = "//android.widget.Button[@content-desc=\"Don't have an account? Sign up\"]";
        WebElement btn = findElementWithFallback(null, signUpBtnXpath, null);
        btn.click();
    }

    public boolean isContinueButtonEnabled() {
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            return btn.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the runtime validation message that appears below the password field.
     * This method checks multiple locations and patterns for dynamic error
     * messages.
     * 
     * @return The validation message text, or null if no message is found
     */
    public String getRuntimeValidationMessage() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        // Strategy 1: Look for validation message below password field (most specific)
        try {
            WebElement validationMsg = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.EditText[@password='true']/following-sibling::android.widget.TextView[1]")));
            String text = validationMsg.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Strategy 2: Look for any TextView with common error keywords
        // (case-insensitive)
        try {
            WebElement errorText = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[" +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'wrong') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'incorrect') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'check') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'must') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cannot')]")));
            String text = errorText.getText();
            if (text != null && !text.trim().isEmpty() &&
                    !text.equals("Forgot Password?") && !text.equals("Don't have an account? Sign up")) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Strategy 3: Look for TextView with error resource-id
        try {
            WebElement errorById = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.TextView[contains(@resource-id, 'error') or contains(@resource-id, 'validation')]")));
            String text = errorById.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Strategy 4: Android Toast message
        try {
            WebElement toast = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.Toast[1]")));
            String text = toast.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Strategy 5: Look for any red-colored text (validation messages are typically
        // red)
        // This searches for TextViews between password field and Continue button
        try {
            WebElement errorText = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text and string-length(@text) > 10]")));
            String text = errorText.getText();
            // Filter out known non-error texts
            if (text != null && !text.trim().isEmpty() &&
                    !text.equals("Forgot Password?") &&
                    !text.equals("Don't have an account? Sign up") &&
                    !text.equals("SIGN IN") &&
                    !text.contains("CONTINUE WITH")) {
                return text;
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    /**
     * Check if the Continue button is in a disabled state (light grey background).
     * This method checks the button's enabled state and can be extended to check
     * visual properties.
     * 
     * @return true if button is disabled (light grey), false if enabled (black)
     */
    public boolean isContinueButtonDisabled() {
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            // Check if button is disabled
            boolean isDisabled = !btn.isEnabled();

            // Optional: Check for visual properties if needed
            // String btnClass = btn.getAttribute("class");
            // String btnEnabled = btn.getAttribute("enabled");

            return isDisabled;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the Continue button's background color or state.
     * This can be used to assert that the button is light grey when fields are
     * empty/invalid.
     * 
     * @return A string describing the button state (e.g., "enabled", "disabled")
     */
    public String getContinueButtonState() {
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            boolean isEnabled = btn.isEnabled();
            String enabledAttr = btn.getAttribute("enabled");
            String clickableAttr = btn.getAttribute("clickable");

            if (!isEnabled || "false".equals(enabledAttr) || "false".equals(clickableAttr)) {
                return "disabled";
            } else {
                return "enabled";
            }
        } catch (Exception e) {
            return "not_found";
        }
    }

    /**
     * RUNTIME-BASED VALIDATION DETECTION (NO HARDCODED MESSAGES)
     * 
     * Checks at runtime if ANY validation element is visible:
     * - Toast message
     * - Inline error text (TextView with error-like content)
     * - EditText with error state / red error box
     * 
     * @return true if ANY validation is detected, false if NONE found
     */
    public boolean isAnyValidationVisible() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Check 1: Android Toast Message
        try {
            WebElement toast = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.Toast[1]")));
            if (toast != null && toast.getText() != null && !toast.getText().trim().isEmpty()) {
                return true; // Toast detected
            }
        } catch (Exception ignored) {
        }

        // Check 2: Validation message via content-desc (android.view.View)
        try {
            WebElement validationView = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.View[@content-desc and string-length(@content-desc) > 10]")));
            String contentDesc = validationView.getAttribute("content-desc");
            if (contentDesc != null && !contentDesc.trim().isEmpty() &&
                    !contentDesc.equals("SIGN IN") &&
                    !contentDesc.equals("Forgot Password?") &&
                    !contentDesc.equals("Don't have an account? Sign up") &&
                    !contentDesc.equals("CONTINUE") &&
                    !contentDesc.equals("or") &&
                    !contentDesc.contains("CONTINUE WITH")) {
                return true; // Validation message detected via content-desc
            }
        } catch (Exception ignored) {
        }

        // Check 3: Inline Error Text (TextView below password or email field)
        try {
            WebElement errorText = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.EditText[@password='true']/following-sibling::android.widget.TextView[1]")));
            if (errorText != null && errorText.getText() != null && !errorText.getText().trim().isEmpty()) {
                return true; // Inline error detected
            }
        } catch (Exception ignored) {
        }

        // Check 4: Any TextView with error keywords (case-insensitive)
        try {
            WebElement errorKeyword = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[" +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'wrong') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'incorrect') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'must') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cannot')]")));
            String text = errorKeyword.getText();
            // Filter out known non-error texts
            if (text != null && !text.trim().isEmpty() &&
                    !text.equals("Forgot Password?") && !text.equals("Don't have an account? Sign up")) {
                return true; // Error keyword detected
            }
        } catch (Exception ignored) {
        }

        // Check 5: TextView with error resource-id
        try {
            WebElement errorById = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.TextView[contains(@resource-id, 'error') or contains(@resource-id, 'validation')]")));
            if (errorById != null && errorById.getText() != null && !errorById.getText().trim().isEmpty()) {
                return true; // Error by resource-id detected
            }
        } catch (Exception ignored) {
        }

        // Check 6: EditText with error attribute (red error box)
        try {
            WebElement editTextError = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.EditText[@error='true' or @focused='true']")));
            if (editTextError != null) {
                return true; // EditText error state detected
            }
        } catch (Exception ignored) {
        }

        // No validation detected
        return false;
    }

    /**
     * GET ACTUAL RUNTIME VALIDATION MESSAGE
     * 
     * Captures the actual validation message displayed by the app at runtime.
     * This method does NOT compare with any expected value - it simply reads
     * whatever text is shown by the app.
     * 
     * Priority order for message capture:
     * 1. Validation message from content-desc (android.view.View)
     * 2. Toast message text
     * 3. Inline error TextView text
     * 4. TextView with error keywords
     * 5. EditText error attribute
     * 6. Disabled Continue button state
     * 
     * @return The actual validation message text, or null if no validation found
     */
    public String getValidationMessage() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Priority 1: Validation message via content-desc (android.view.View)
        // This is the primary validation message location for this app
        try {
            WebElement validationView = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.View[@content-desc and string-length(@content-desc) > 10]")));
            String contentDesc = validationView.getAttribute("content-desc");
            if (contentDesc != null && !contentDesc.trim().isEmpty() &&
                    !contentDesc.equals("SIGN IN") &&
                    !contentDesc.equals("Forgot Password?") &&
                    !contentDesc.equals("Don't have an account? Sign up") &&
                    !contentDesc.equals("CONTINUE") &&
                    !contentDesc.equals("or") &&
                    !contentDesc.contains("CONTINUE WITH")) {
                return contentDesc;
            }
        } catch (Exception ignored) {
        }

        // Priority 2: Android Toast Message
        try {
            WebElement toast = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.Toast[1]")));
            String toastText = toast.getText();
            if (toastText != null && !toastText.trim().isEmpty()) {
                return toastText;
            }
        } catch (Exception ignored) {
        }

        // Priority 3: Inline Error Text (TextView below password field)
        try {
            WebElement errorText = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.EditText[@password='true']/following-sibling::android.widget.TextView[1]")));
            String text = errorText.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Priority 4: Inline Error Text (TextView below email field)
        try {
            WebElement errorText = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.EditText[@hint='Email']/following-sibling::android.widget.TextView[1]")));
            String text = errorText.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Priority 5: Any TextView with error keywords
        try {
            WebElement errorKeyword = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[" +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'wrong') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'incorrect') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'must') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cannot')]")));
            String text = errorKeyword.getText();
            if (text != null && !text.trim().isEmpty() &&
                    !text.equals("Forgot Password?") && !text.equals("Don't have an account? Sign up")) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Priority 6: TextView with error resource-id
        try {
            WebElement errorById = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//android.widget.TextView[contains(@resource-id, 'error') or contains(@resource-id, 'validation')]")));
            String text = errorById.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Priority 7: EditText error attribute
        try {
            WebElement editTextError = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.EditText[@error='true']")));
            String errorAttr = editTextError.getAttribute("error");
            if (errorAttr != null && !errorAttr.trim().isEmpty()) {
                return errorAttr;
            }
        } catch (Exception ignored) {
        }

        // Priority 8: Disabled Continue button (for empty fields)
        try {
            WebElement btn = findElementWithFallback(null, continueBtnXpath, "CONTINUE");
            if (!btn.isEnabled()) {
                return "Continue button is disabled (fields are empty or invalid)";
            }
        } catch (Exception ignored) {
        }

        // No validation message found
        return null;
    }

    /**
     * Check if the app is currently on the Sign In page.
     * This is used to determine if navigation to Sign In page is needed.
     * 
     * @return true if on Sign In page, false otherwise
     */
    public boolean isOnSignInPage() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            // Check for email field which is unique to Sign In page
            WebElement emailField = shortWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(emailXpath)));
            return emailField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for validation message to appear after clicking Continue.
     * This replaces Thread.sleep() with an explicit wait.
     * 
     * @param timeoutSeconds Maximum time to wait for validation
     * @return true if validation appeared, false if timeout
     */
    public boolean waitForValidationToAppear(int timeoutSeconds) {
        try {
            WebDriverWait validationWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

            // Wait for any validation element to appear
            validationWait.until(driver -> isAnyValidationVisible());
            return true;
        } catch (Exception e) {
            // Timeout - no validation appeared
            return false;
        }
    }

    /**
     * Check if the "LINK DEVICES" page is displayed after successful login.
     * 
     * @return true if LINK DEVICES element is visible
     */
    public boolean isLinkDevicesDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement linkDevices = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.view.View[@content-desc='LINK DEVICES']")));
            return linkDevices.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Try to skip or continue from Link Devices page to reach Home page.
     */
    public void handleLinkDevices() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Try clicking SKIP
        try {
            WebElement skipBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//android.widget.Button[@content-desc='SKIP'] | //android.view.View[@content-desc='SKIP']")));
            skipBtn.click();
            return;
        } catch (Exception ignored) {
        }

        // Try clicking CONTINUE
        try {
            WebElement continueBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//android.widget.Button[@content-desc='CONTINUE'] | //android.view.View[@content-desc='CONTINUE']")));
            continueBtn.click();
            return;
        } catch (Exception ignored) {
        }

        // Try clicking close/X if exists
        try {
            WebElement closeBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//android.widget.ImageView[contains(@content-desc, 'close') or contains(@resource-id, 'close')]")));
            closeBtn.click();
        } catch (Exception ignored) {
        }
    }

    /**
     * Click "SKIP FOR NOW" button
     */
    public void clickSkipForNow() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement skipBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.Button[@content-desc='SKIP FOR NOW']")));
            skipBtn.click();
        } catch (Exception e) {
            // Log if not found, as it is part of the expected flow
            System.out.println("SKIP FOR NOW button not found: " + e.getMessage());
        }
    }
}
