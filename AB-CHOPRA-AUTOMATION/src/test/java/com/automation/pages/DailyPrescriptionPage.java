package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput;

import java.time.Duration;
import java.util.Collections;

public class DailyPrescriptionPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public DailyPrescriptionPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String pageHeadingXpath = "//android.view.View[@content-desc='DAILY PRESCRIPTION']";
    private final String scheduleTimeButtonXpath = "//android.widget.Button[@content-desc='SCHEDULE TIME']";
    private final String confirmButtonXpath = "//android.widget.Button[@content-desc='CONFIRM']";
    private final String okButtonXpath = "//android.widget.Button[@content-desc='OK']";

    /**
     * Check if Daily Prescription page is displayed
     */
    public boolean isDailyPrescriptionPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(pageHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 3: Click SCHEDULE TIME button
     */
    public void clickScheduleTime() {
        try {
            WebElement scheduleBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(scheduleTimeButtonXpath)));
            scheduleBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("SCHEDULE TIME button not found", e);
        }
    }

    /**
     * Step 5: Perform a single swipe up in the time picker
     * Targeted specifically at the ScrollView picker container.
     */
    public void swipeUpOnce() {
        try {
            // Identify the picker container specifically - often the ScrollView within the
            // modal
            WebElement pickerContainer = wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath(
                            "//android.view.View[contains(@content-desc, 'min')]/ancestor::android.widget.ScrollView[1]")));

            org.openqa.selenium.Point location = pickerContainer.getLocation();
            org.openqa.selenium.Dimension size = pickerContainer.getSize();

            // Calculate swipe points within the picker bounds (Center-X, Bottom-to-Top Y)
            int centerX = location.getX() + (size.getWidth() / 2);
            int startY = location.getY() + (int) (size.getHeight() * 0.8);
            int endY = location.getY() + (int) (size.getHeight() * 0.2);

            System.out.println("Single Swipe: Targeting ScrollView picker at " + location + " with size " + size);

            // Perform exactly one slow, stable W3C swipe gesture inside the picker bounds
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(
                    finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), centerX,
                            endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
            Thread.sleep(1200); // Wait for wheel to settle/decelerate

        } catch (Exception e) {
            System.out.println("Error performing single swipe in picker: " + e.getMessage());
            throw new RuntimeException("Time picker swipe failed: " + e.getMessage(), e);
        }
    }

    /**
     * Step 5: Click CONFIRM button
     */
    public void clickConfirm() {
        try {
            WebElement confirmBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(confirmButtonXpath)));
            confirmBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("CONFIRM button not found", e);
        }
    }

    /**
     * Step 6: Validate success dialog is displayed
     * 
     * @return true if success dialog is visible
     */
    public boolean isSuccessDialogDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement successView = shortWait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='SUCCESS!']")));
            return successView.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 7: Get validation message from success dialog
     * 
     * @return The success message text
     */
    public String getSuccessMessage() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement messageView = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//android.view.View[@content-desc='The scheduled time has been updated successfully.']")));
            return messageView.getAttribute("content-desc");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Step 8: Click OK button
     */
    public void clickOk() {
        try {
            WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(okButtonXpath)));
            okBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("OK button not found", e);
        }
    }

    /**
     * Step 10: Swipe left and right on Nutrition & Metabolism section
     * Wait for data to load after each swipe
     */
    public void swipeNutritionSection() {
        try {
            Thread.sleep(1000); // Wait for page to settle

            // 1. Swipe Left (Right to Left gesture)
            System.out.println("Performing Swipe Left...");
            swipeHorizontal(true);
            Thread.sleep(2000); // Wait for data to load

            // 2. Swipe Right (Left to Right gesture)
            System.out.println("Performing Swipe Right...");
            swipeHorizontal(false);
            Thread.sleep(2000); // Wait for data to load
        } catch (Exception e) {
            System.out.println("Error in swipeNutritionSection: " + e.getMessage());
        }
    }

    /**
     * Step 11: Swipe up 2 times in the ScrollView component
     */
    public void swipeUpTwiceInScrollView() {
        try {
            Thread.sleep(1000);
            // Swipe up first time
            swipeUpVertical();
            Thread.sleep(500);
            // Swipe up second time
            swipeUpVertical();
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Error in swipeUpTwiceInScrollView: " + e.getMessage());
        }
    }

    /**
     * Step 12: Click on whichever article is present using a common XPath
     */
    public void clickAnyArticle() {
        try {
            // Use a common XPath to find any article view
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
            WebElement articleElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.View[contains(@content-desc, 'Article')]")));

            // Get location and size
            org.openqa.selenium.Point location = articleElement.getLocation();
            org.openqa.selenium.Dimension size = articleElement.getSize();

            // Calculate coordinates - target the left side (usually image area)
            int leftX = location.getX() + (int) (size.getWidth() * 0.25);
            int centerY = location.getY() + (size.getHeight() / 2);

            System.out.println("Tapping article at coordinates: " + leftX + ", " + centerY);

            // Use W3C Actions for tapping at coordinates
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), leftX, centerY));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(tap));

            Thread.sleep(2000); // Wait for article detail page to load

            // Validate that an article detail page opened (generic validation)
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//android.view.View[contains(@content-desc, 'min read')]")));
                System.out.println("✓ Article detail page opened successfully");
            } catch (Exception e) {
                System.out.println("Warning: Could not confirm article detail page via 'min read' tag");
            }
        } catch (Exception e) {
            System.out.println("Error in clickAnyArticle: " + e.getMessage());
            throw new RuntimeException("Failed to click article or verify detail page opened", e);
        }
    }

    /**
     * Step 13: Get heading from any article detail page at runtime
     */
    public String getArticleHeading() {
        try {
            Thread.sleep(1500); // Wait for page to fully load
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));

            // Strategy 1: Find the first significant View in the ScrollView that isn't a
            // tag
            // Usually the heading is the first or second View with content-desc
            try {
                WebElement headingElement = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//android.widget.ScrollView//android.view.View[@content-desc!='' and not(contains(@content-desc, 'min read')) and not(contains(@content-desc, 'Article Detail'))][1]")));
                String heading = headingElement.getAttribute("content-desc");
                if (heading != null && !heading.isEmpty()) {
                    System.out.println("✓ Extracted article heading: " + heading);
                    return heading;
                }
            } catch (Exception e1) {
                System.out.println("Strategy 1 failed, trying Strategy 2...");
            }

            // Strategy 2: Fallback to the second View in ScrollView
            try {
                WebElement headingElement = driver.findElement(
                        By.xpath("(//android.widget.ScrollView//android.view.View[@content-desc!=''])[2]"));
                String heading = headingElement.getAttribute("content-desc");
                if (heading != null && !heading.isEmpty() && !heading.contains("min read")) {
                    return heading;
                }
            } catch (Exception e2) {
                System.out.println("Strategy 2 failed: " + e2.getMessage());
            }

            return "Article heading extracted";
        } catch (Exception e) {
            System.out.println("Error extracting article heading: " + e.getMessage());
            return "Unable to extract heading";
        }
    }

    /**
     * Step 14: Click the radio icon to open ADD TO FILE dialog (replaces back
     * button logic)
     */
    public void clickAddToFileRadioIcon() {
        try {
            // Updated locator provided by user:
            // //android.widget.ScrollView/android.view.View[4]
            // This element acts as a radio button to show the dialog
            WebElement radioIcon = wait.until(
                    ExpectedConditions
                            .elementToBeClickable(By.xpath("//android.widget.ScrollView/android.view.View[4]")));
            radioIcon.click();
            System.out.println("✓ Clicked 'Add To File' radio icon");
            Thread.sleep(1000); // Wait for dialog animation
        } catch (Exception e) {
            throw new RuntimeException("Add to File radio icon not found", e);
        }
    }

    /**
     * Step 15: Validate ADD TO FILE dialog is displayed
     * 
     * @return true if dialog is visible
     */
    public boolean isAddToFileDialogDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement dialogView = shortWait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='ADD TO FILE']")));
            return dialogView.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 16-17: Swipe up once and click New File icon
     */
    public void swipeAndClickNewFile() {
        String newFileXpath = "(//android.widget.ImageView[@content-desc=\"New File\"])";
        int maxSwipes = 5;
        int swipes = 0;

        while (swipes < maxSwipes) {
            try {
                // Check if New File icon is present and clickable
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement newFileIcon = shortWait
                        .until(ExpectedConditions.elementToBeClickable(By.xpath(newFileXpath)));

                if (newFileIcon.isDisplayed()) {
                    newFileIcon.click();
                    System.out.println("✓ Found and clicked New File icon after " + swipes + " swipes");
                    return;
                }
            } catch (Exception e) {
                // Not found or not clickable yet, swipe up
                System.out.println("New File icon not ready, swiping... (" + (swipes + 1) + ")");
                swipeUpInDialog();
                swipes++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }
        throw new RuntimeException("Could not find or click New File icon after " + maxSwipes + " swipes");
    }

    /**
     * Search for a file in the ADD TO FILE dialog
     */
    public void searchInAddToFile(String query) {
        try {
            WebElement editText = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText")));
            editText.click();
            editText.clear();
            editText.sendKeys(query);
            System.out.println("✓ Searched for: " + query);
        } catch (Exception e) {
            throw new RuntimeException("Failed to search in ADD TO FILE dialog: " + e.getMessage());
        }
    }

    /**
     * Check if a specific search result is displayed
     */
    public boolean isSearchResultDisplayed(String contentDesc) {
        try {
            // Remove potential trailing space in XPath and handle newline character
            String xpath = "(//android.widget.Button[@content-desc=\"" + contentDesc + "\"])";
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement result = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return result.isDisplayed();
        } catch (Exception e) {
            // Fallback: If literal match fails, try replacing newline with space as some
            // tools report it that way
            try {
                String altContentDesc = contentDesc.replace("\n", " ");
                String xpathAlt = "(//android.widget.Button[@content-desc=\"" + altContentDesc + "\"])";
                return driver.findElement(By.xpath(xpathAlt)).isDisplayed();
            } catch (Exception e2) {
                System.out.println("Search result not found: " + contentDesc);
                return false;
            }
        }
    }

    /**
     * Clear the search field in ADD TO FILE dialog
     */
    public void clearSearchField() {
        try {
            WebElement editText = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText")));
            editText.clear();
            System.out.println("✓ Search field cleared");
        } catch (Exception e) {
            System.out.println("Could not clear search field: " + e.getMessage());
        }
    }

    /**
     * Step 18: Enter file name in EditText field
     */
    public void enterFileName(String fileName) {
        try {
            // Locator updated as per user request to target specific file name input
            WebElement editText = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//android.view.View[@content-desc='ADD TO FILE']/android.view.View/android.view.View/android.widget.EditText")));
            editText.click();
            editText.clear();
            editText.sendKeys(fileName);
            // hideKeyboard(); // Removed as per user request to keep dialog open
        } catch (TimeoutException e) {
            throw new RuntimeException("EditText field not found", e);
        }
    }

    /**
     * Explicitly wait for the Modified button before clicking
     */
    public void waitForModifiedButton() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.Button[contains(@content-desc, 'Modified')]")));
        } catch (TimeoutException e) {
            throw new RuntimeException("Modified button did not appear after entering file name");
        }
    }

    /**
     * Step 19: Click modified button
     */
    public void clickModifiedButton() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.Button[contains(@content-desc, 'Modified')]")));
            btn.click();
            System.out.println("✓ Clicked modified button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Modified button not found", e);
        }
    }

    /**
     * Step 20: Click final close icon
     */
    public void clickFinalCloseIcon() {
        try {
            WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.ImageView")));
            icon.click();
            System.out.println("✓ Clicked final close icon");
        } catch (TimeoutException e) {
            throw new RuntimeException("Final close icon not found", e);
        }
    }

    /**
     * Step 21: Validate SAVED dialog is displayed
     * 
     * @return true if SAVED dialog is visible
     */
    public boolean isSavedDialogDisplayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement savedView = shortWait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='SAVED']")));
            return savedView.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 21: Get saved success message
     * 
     * @return The success message text
     */
    public String getSavedMessage() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement messageView = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.View[@content-desc='Your article has been successfully saved.']")));
            return messageView.getAttribute("content-desc");
        } catch (Exception e) {
            return null;
        }
    }

    /* ================= SWIPE UTILITY METHODS ================= */

    /**
     * Swipe up vertically (scroll down)
     */
    private void swipeUpVertical() {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /**
     * Swipe down vertically (scroll up)
     */
    private void swipeDownVertical() {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /**
     * Swipe horizontally
     * 
     * @param rightToLeft true for right-to-left swipe, false for left-to-right
     */
    private void swipeHorizontal(boolean rightToLeft) {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int centerY = size.height / 2;

        int startX, endX;
        if (rightToLeft) {
            startX = (int) (size.width * 0.8);
            endX = (int) (size.width * 0.2);
        } else {
            startX = (int) (size.width * 0.2);
            endX = (int) (size.width * 0.8);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /**
     * Swipe up within dialog (smaller swipe area)
     */
    private void swipeUpInDialog() {
        try {
            // Target the specific container provided by the user
            String containerXpath = "//android.view.View[@content-desc=\"ADD TO FILE\"]/android.view.View/android.view.View/android.view.View[2]";
            WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(containerXpath)));

            org.openqa.selenium.Point location = container.getLocation();
            org.openqa.selenium.Dimension size = container.getSize();

            int centerX = location.getX() + (size.getWidth() / 2);
            int startY = location.getY() + (int) (size.getHeight() * 0.82);
            int endY = location.getY() + (int) (size.getHeight() * 0.18);

            System.out.println("Swiping up in container: " + containerXpath + " at " + location + " size " + size);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(650), PointerInput.Origin.viewport(), centerX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        } catch (Exception e) {
            System.out.println("Specific container swipe failed, falling back to window swipe: " + e.getMessage());
            org.openqa.selenium.Dimension winSize = driver.manage().window().getSize();
            int winCenterX = winSize.width / 2;
            int winStartY = (int) (winSize.height * 0.75);
            int winEndY = (int) (winSize.height * 0.25);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(
                    finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), winCenterX, winStartY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), winCenterX,
                            winEndY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(swipe));
        }
    }

    /**
     * Helper to hide keyboard safely
     */
    private void hideKeyboard() {
        try {
            if (driver instanceof io.appium.java_client.android.AndroidDriver) {
                ((io.appium.java_client.android.AndroidDriver) driver).hideKeyboard();
            }
        } catch (Exception ignored) {
        }
    }

    public void printPageSource() {
        System.out.println(driver.getPageSource());
    }
}