package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LinkDevicesPage {
    private WebDriverWait wait;

    public LinkDevicesPage(AppiumDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String skipForNowButtonXpath = "//android.widget.Button[@content-desc='SKIP FOR NOW']";
    private final String ultrahumanOptionXpath = "//android.view.View[@content-desc='ULTRAHUMAN']";
    private final String linkDevicesHeadingXpath = "//android.view.View[@content-desc='LINK DEVICES']";

    /**
     * Click the SKIP FOR NOW button to skip device linking
     */
    public void clickSkipForNow() {
        try {
            WebElement skipBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(skipForNowButtonXpath)));
            skipBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("SKIP FOR NOW button not found on Link Devices page", e);
        }
    }

    /**
     * Click the ULTRAHUMAN option to link Ultrahuman device
     */
    public void clickUltrahuman() {
        try {
            WebElement ultrahumanOption = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(ultrahumanOptionXpath)));
            ultrahumanOption.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("ULTRAHUMAN option not found on Link Devices page", e);
        }
    }

    /**
     * Check if Link Devices page is displayed
     */
    public boolean isLinkDevicesPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(linkDevicesHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
