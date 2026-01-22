package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TwoFactorAuthPage {
    private WebDriverWait wait;

    public TwoFactorAuthPage(AppiumDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String yesButtonXpath = "//android.widget.Button[@content-desc='YES']";
    private final String noButtonXpath = "//android.widget.Button[@content-desc='NO']";
    private final String twoFactorHeadingXpath = "//android.view.View[@content-desc='TWO-FACTOR AUTHENTICATION SETUP']";

    /**
     * Click the YES button to enable Two-Factor Authentication
     */
    public void clickYes() {
        try {
            WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(yesButtonXpath)));
            yesBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("YES button not found on Two-Factor Authentication page", e);
        }
    }

    /**
     * Click the NO button to skip Two-Factor Authentication
     */
    public void clickNo() {
        try {
            WebElement noBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(noButtonXpath)));
            noBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("NO button not found on Two-Factor Authentication page", e);
        }
    }

    /**
     * Check if Two-Factor Authentication page is displayed
     */
    public boolean isTwoFactorAuthPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(twoFactorHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
