package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class LinkDevicesPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public LinkDevicesPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String skipForNowButtonXpath = "//android.widget.Button[@content-desc='SKIP FOR NOW']";
    private final String ultrahumanOptionXpath = "//android.view.View[@content-desc='ULTRAHUMAN']";
    private final String linkDevicesHeadingXpath = "//android.view.View[@content-desc='LINK DEVICES']";

    /**
     * Click the SKIP FOR NOW button to skip device linking.
     *
     * Strategy 1: elementToBeClickable + standard click (content-desc XPath).
     * Strategy 2: presenceOfElementLocated + W3C force tap (content-desc XPath).
     * Strategy 3: Coordinate tap using known bounds [52,1344][668,1438] → center
     * (360, 1391).
     */
    public void clickSkipForNow() {
        // Strategy 1: Accessibility ID (content-desc = "SKIP FOR NOW")
        try {
            WebElement skipBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    io.appium.java_client.AppiumBy.accessibilityId("SKIP FOR NOW")));
            skipBtn.click();
            System.out.println("Clicked SKIP FOR NOW using Accessibility ID.");
            return;
        } catch (Exception e) {
            System.out.println("Strategy 1 (AccessibilityId) failed for SKIP FOR NOW: " + e.getMessage());
        }

        // Strategy 2: XPath content-desc + standard click
        try {
            WebElement skipBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(skipForNowButtonXpath)));
            skipBtn.click();
            System.out.println("Clicked SKIP FOR NOW using XPath standard click.");
            return;
        } catch (Exception e) {
            System.out.println("Strategy 2 (XPath click) failed for SKIP FOR NOW: " + e.getMessage());
        }

        // Strategy 3: XPath presence + W3C force tap
        try {
            WebElement skipBtn = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(skipForNowButtonXpath)));
            tapElement(skipBtn);
            System.out.println("Clicked SKIP FOR NOW using W3C force tap.");
            return;
        } catch (Exception e) {
            System.out.println("Strategy 3 (W3C tap) failed for SKIP FOR NOW: " + e.getMessage());
        }

        // Strategy 4: Coordinate tap — center of bounds [52,1344][668,1438]
        try {
            int x = (52 + 668) / 2; // 360
            int y = (1344 + 1438) / 2; // 1391
            tapCoordinates(x, y);
            System.out.println("Clicked SKIP FOR NOW using coordinate tap (" + x + ", " + y + ").");
        } catch (Exception e) {
            throw new RuntimeException("All strategies failed for SKIP FOR NOW button", e);
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

    /**
     * W3C Actions force tap on a located element
     */
    private void tapElement(WebElement element) {
        int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
        int centerY = element.getRect().getY() + (element.getRect().getHeight() / 2);
        tapCoordinates(centerX, centerY);
    }

    /**
     * W3C Actions tap at specific screen coordinates
     */
    private void tapCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(tap));
    }
}
