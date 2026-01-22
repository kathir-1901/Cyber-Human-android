package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangePasswordPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public ChangePasswordPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String changePasswordHeadingXpath = "//android.view.View[@content-desc='CHANGE PASSWORD']";
    private final String currentPasswordFieldXpath = "//android.view.View[@content-desc='Enter Current Password']/android.widget.EditText";
    private final String newPasswordFieldXpath = "//android.view.View[@content-desc='New Password']/android.widget.EditText";
    private final String confirmPasswordFieldXpath = "//android.view.View[@content-desc='Confirm New Password']/android.widget.EditText";
    private final String changePasswordButtonXpath = "//android.widget.Button[@content-desc='CHANGE PASSWORD']";

    // Validation Messages
    private final String wrongPasswordErrorXpath = "//android.view.View[@content-desc='Wrong password. Please enter correct password']";
    private final String weakPasswordErrorXpath = "//android.view.View[@content-desc='Use at least 8 characters with uppercase, lowercase, number, and special symbol.']";
    private final String samePasswordErrorXpath = "//android.view.View[@content-desc='Current and new password cannot be the same.']";
    private final String successDialogXpath = "//android.view.View[@content-desc='PASSWORD CHANGE SUCCESSFUL']";
    private final String successMessageXpath = "//android.view.View[@content-desc='Your password was successfully changed.']";
    private final String okButtonXpath = "//android.widget.Button[@content-desc='OK']";

    /**
     * Verify if Change Password page is displayed
     */
    public boolean isChangePasswordPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(changePasswordHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enter Current Password
     */
    public void enterCurrentPassword(String password) {
        enterText(currentPasswordFieldXpath, password);
    }

    /**
     * Enter New Password
     */
    public void enterNewPassword(String password) {
        enterText(newPasswordFieldXpath, password);
    }

    /**
     * Enter Confirm Password
     */
    public void enterConfirmPassword(String password) {
        enterText(confirmPasswordFieldXpath, password);
    }

    /**
     * Helper method to Click -> Clear -> SendKeys -> HideKeyboard
     */
    private void enterText(String xpath, String text) {
        try {
            WebElement field = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            field.click();
            field.clear();
            field.sendKeys(text);
            hideKeyboard();
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not found: " + xpath, e);
        }
    }

    /**
     * Click Change Password Button
     */
    public void clickChangePasswordButton() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(changePasswordButtonXpath)));
            btn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Change Password button not found", e);
        }
    }

    /**
     * Get Validation Error Message
     * Returns the text of the error if found, else null.
     */
    public String getValidationErrorMessage(String expectedErrorXpath) {
        try {
            WebElement error = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(expectedErrorXpath)));
            return error.getAttribute("content-desc");
        } catch (Exception e) {
            return null;
        }
    }

    // Getters for specific error XPaths to use in tests
    public String getWrongPasswordErrorXpath() {
        return wrongPasswordErrorXpath;
    }

    public String getWeakPasswordErrorXpath() {
        return weakPasswordErrorXpath;
    }

    public String getSamePasswordErrorXpath() {
        return samePasswordErrorXpath;
    }

    /**
     * Check if Success Dialog is displayed
     */
    public boolean isSuccessDialogDisplayed() {
        try {
            WebElement dialog = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(successDialogXpath)));
            return dialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Success Message
     */
    public String getSuccessMessage() {
        try {
            WebElement msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(successMessageXpath)));
            return msg.getAttribute("content-desc");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Click OK Button
     */
    public void clickOkButton() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(okButtonXpath)));
            btn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("OK button not found", e);
        }
    }

    private void hideKeyboard() {
        try {
            if (driver instanceof io.appium.java_client.android.AndroidDriver) {
                ((io.appium.java_client.android.AndroidDriver) driver).hideKeyboard();
            }
        } catch (Exception ignored) {
        }
    }
}
