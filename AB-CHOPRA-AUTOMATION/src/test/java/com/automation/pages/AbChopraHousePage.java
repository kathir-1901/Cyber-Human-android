package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class AbChopraHousePage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public AbChopraHousePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==================== LOCATORS ====================

    // Step 3: AB Chopra House
    private final String abChopraHouseXpath = "//android.view.View[@content-desc=\"AB CHOPRA HOUSE\"]";

    // Step 4: Discover button
    private final String discoverButtonXpath = "//android.view.View[@content-desc=\"DISCOVER\"]";

    // Step 5: Discover page heading
    private final String discoverPageHeadingXpath = "//android.view.View[@content-desc=\"DISCOVER +\"]";

    // Step 6 & 7: Scroll view for swipe
    private final String scrollViewXpath = "//android.widget.ScrollView";

    // Step 8: Discover + button (note: has newline in content-desc)
    private final String discoverPlusButtonXpath = "//android.view.View[@content-desc=\"DISCOVER\\n +\"]";

    // Step 9: Filter button
    private final String filterButtonXpath = "//android.widget.Button[@content-desc=\"FILTER\"]";

    // Step 10: Mind & Emotions radio button
    private final String mindEmotionsRadioXpath = "//android.view.View[@content-desc=\"Mind & Emotions\"]";
    // Step 11: Timing SeekBar
    private final String timingSeekBarXpath = "//android.widget.SeekBar[@content-desc=\"the end value is 60.0\"]";

    // Step 12: Apply button
    private final String applyButtonXpath = "//android.widget.Button[@content-desc=\"APPLY\"]";

    // Step 13: See All 1, Listen page, Back button
    private final String seeAll1Xpath = "(//android.widget.ImageView[@content-desc=\"See All\"])[1]";
    private final String listenPageXpath = "//android.view.View[@content-desc=\"Listen\"]";
    private final String backButtonXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView";

    // Step 14: See All 2, Read page
    private final String seeAll2Xpath = "//android.widget.ImageView[@content-desc=\"See All\"]";
    private final String readPageXpath = "//android.view.View[@content-desc=\"Read\"]";

    // Step 15: Swipe container
    private final String swipeContainerXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View";

    // Step 16: See All 3, Watch page
    private final String seeAll3Xpath = "(//android.widget.ImageView[@content-desc=\"See All\"])[2]";
    private final String watchPageXpath = "//android.view.View[@content-desc=\"Watch\"]";

    // Step 17: Article item (XPath now inline in clickVideoItem() method using
    // contains())

    // Step 18: Search box
    private final String searchBoxXpath = "//android.widget.EditText";

    // Step 19: Search result (XPath now inline in isSearchResultOneDisplayed()
    // method using contains())

    // Step 18 (second): New File icon
    private final String newFileIconXpath = "//android.widget.ImageView[@content-desc=\"New File\"]";

    // Step 19 (second): File name input
    private final String fileNameInputXpath = "//android.view.View[@content-desc=\"ADD TO FILE\"]/android.view.View/android.view.View/android.widget.EditText";

    // Step 20: Close icon
    private final String closeIconXpath = "//android.view.View[@content-desc=\"ADD TO FILE\"]/android.widget.ImageView";

    // Step 21: Saved dialog and success message
    private final String savedDialogXpath = "//android.view.View[@content-desc=\"SAVED\"]";
    private final String savedSuccessMessageXpath = "//android.view.View[@content-desc=\"Your article has been successfully saved.\"]";

    // Step 22: OK button
    private final String okButtonXpath = "//android.widget.Button[@content-desc=\"OK\"]";

    // Step 24: Archive button
    private final String archiveButtonXpath = "//android.view.View[@content-desc=\"ARCHIVE\"]";

    // Step 25: Archive page heading
    private final String archivePageHeadingXpath = "//android.view.View[@content-desc=\"ARCHIVE\"]";

    // Step 26 & 27: New file in archive (uses contains() to handle multiline
    // content-desc like "New\nModified Dec 30")
    // Locator is now defined inline in methods to use contains() for dynamic date
    // handling

    // Step 28: File open verification
    private final String fileOpenXpath = "//android.view.View[@content-desc=\"new\"]";

    // Step 30: Yes button
    private final String yesButtonXpath = "//android.widget.Button[@content-desc=\"YES\"]";

    // Step 31: Success dialog and delete message
    private final String successDialogXpath = "//android.view.View[@content-desc=\"SUCCESS\"]";
    private final String deleteSuccessMessageXpath = "//android.view.View[@content-desc=\"Your article has been successfully deleted\"]";

    // ==================== METHODS ====================

    /**
     * Step 3: Click AB Chopra House
     */
    public void clickAbChopraHouse() {
        try {
            WebElement abChopraHouse = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(abChopraHouseXpath)));
            abChopraHouse.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("AB CHOPRA HOUSE button not found", e);
        }
    }

    /**
     * Step 4: Click Discover button
     */
    public void clickDiscover() {
        try {
            WebElement discoverBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(discoverButtonXpath)));
            discoverBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("DISCOVER button not found", e);
        }
    }

    /**
     * Step 6: Swipe up once
     */
    public void swipeUpOnce() {
        try {
            // Wait 2 seconds before swiping
            Thread.sleep(2000);
            WebElement scrollView = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(scrollViewXpath)));
            Dimension size = driver.manage().window().getSize();
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        } catch (Exception e) {
            throw new RuntimeException("Failed to swipe up", e);
        }
    }

    /**
     * Step 7: Swipe down once
     */
    public void swipeDownOnce() {
        try {
            WebElement scrollView = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(scrollViewXpath)));
            Dimension size = driver.manage().window().getSize();
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.2);
            int endY = (int) (size.height * 0.8);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        } catch (Exception e) {
            throw new RuntimeException("Failed to swipe down", e);
        }
    }

    /**
     * Step 8: Click Discover + button
     */
    public void clickDiscoverPlus() {
        try {
            // Wait 3 seconds for page to settle after swipes
            Thread.sleep(3000);

            // Increase wait time to 15 seconds for this element
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Try multiple XPath strategies
            WebElement discoverPlusBtn = null;

            // Strategy 1: Exact match with double quotes
            try {
                discoverPlusBtn = longWait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.View[@content-desc=\"DISCOVER +\"]")));
            } catch (Exception e1) {
                // Strategy 2: Contains match
                try {
                    discoverPlusBtn = driver.findElement(
                            By.xpath("//android.view.View[contains(@content-desc, 'DISCOVER')]"));
                } catch (Exception e2) {
                    // Strategy 3: Try with wildcard element type
                    try {
                        discoverPlusBtn = driver.findElement(
                                By.xpath("//*[contains(@content-desc, 'DISCOVER +')]"));
                    } catch (Exception e3) {
                        // Strategy 4: Try button element
                        discoverPlusBtn = driver.findElement(
                                By.xpath("//android.widget.Button[contains(@content-desc, 'DISCOVER')]"));
                    }
                }
            }

            if (discoverPlusBtn != null) {
                discoverPlusBtn.click();
            } else {
                throw new RuntimeException("DISCOVER + button not found with any strategy");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("DISCOVER + button not found", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while waiting", e);
        }
    }

    /**
     * Step 9: Click Filter button
     */
    public void clickFilter() {
        try {
            WebElement filterBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(filterButtonXpath)));
            filterBtn.click();
            // Wait for filter dialog to appear
            Thread.sleep(2000);
        } catch (TimeoutException e) {
            throw new RuntimeException("FILTER button not found", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while waiting for filter dialog", e);
        }
    }

    /**
     * Step 10: Click Mind & Emotions radio button
     */
    public void clickMindEmotionsRadio() {
        try {
            // Use longer wait time for filter dialog elements to load
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement radioBtn = longWait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(mindEmotionsRadioXpath)));
            radioBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Mind & Emotions radio button not found", e);
        }
    }

    /**
     * Step 11: Set timing on SeekBar
     */
    public void setTiming() {
        try {
            WebElement seekBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(timingSeekBarXpath)));
            seekBar.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Timing SeekBar not found", e);
        }
    }

    /**
     * Step 12: Click Apply button
     */
    public void clickApply() {
        try {
            WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(applyButtonXpath)));
            applyBtn.click();
            // Wait for loading to complete
            Thread.sleep(3000);
        } catch (TimeoutException e) {
            throw new RuntimeException("APPLY button not found", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while waiting for loading", e);
        }
    }

    /**
     * Step 13 (new): Search for "love" in search box
     */
    public void searchForLove() {
        try {
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchBoxXpath)));
            searchBox.click();
            searchBox.clear();
            searchBox.sendKeys("love");
        } catch (TimeoutException e) {
            throw new RuntimeException("Search box not found", e);
        }
    }

    /**
     * Step 13 (new): Hide keyboard
     */
    public void hideKeyboard() {
        try {
            ((io.appium.java_client.android.AndroidDriver) driver).hideKeyboard();
        } catch (Exception e) {
            // Keyboard might already be hidden, ignore
            System.out.println("Keyboard hide failed or already hidden: " + e.getMessage());
        }
    }

    /**
     * Step 13 (new): Verify Love & Unity article is displayed
     */
    public boolean isLoveArticleDisplayed() {
        try {
            WebElement article = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//android.view.View[@content-desc='Love & Unity\nAudio \n 8 min']")));
            return article.isDisplayed();
        } catch (Exception e) {
            System.out.println("Love & Unity article not found. Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Step 13 (new): Clear search box
     */
    public void clearSearch() {
        try {
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchBoxXpath)));
            searchBox.click();
            searchBox.clear();
        } catch (TimeoutException e) {
            throw new RuntimeException("Search box not found for clearing", e);
        }
    }

    /**
     * Step 13: Click See All 1
     */
    public void clickSeeAll1() {
        try {
            WebElement seeAll1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(seeAll1Xpath)));
            seeAll1.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("See All 1 button not found", e);
        }
    }

    /**
     * Step 13: Verify Listen page is displayed
     */
    public boolean isListenPageDisplayed() {
        try {
            // Use longer wait time for Listen page to load
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement listenPage = longWait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(listenPageXpath)));
            return listenPage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Listen page not found. Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Step 13, 14, 16, 23: Click back button
     */
    public void clickBackButton() {
        try {
            WebElement backBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(backButtonXpath)));
            backBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Back button not found", e);
        }
    }

    /**
     * Step 14: Click See All 2
     */
    public void clickSeeAll2() {
        try {
            WebElement seeAll2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(seeAll2Xpath)));
            seeAll2.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("See All 2 button not found", e);
        }
    }

    /**
     * Step 14: Verify Read page is displayed
     */
    public boolean isReadPageDisplayed() {
        try {
            // Use longer wait time for Read page to load
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement readPage = longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readPageXpath)));
            return readPage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Read page not found. Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Step 15: Swipe up on container
     */
    public void swipeUpOnContainer() {
        try {
            WebElement container = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(swipeContainerXpath)));
            Dimension size = driver.manage().window().getSize();
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        } catch (Exception e) {
            throw new RuntimeException("Failed to swipe up on container", e);
        }
    }

    /**
     * Step 16: Click See All 3
     */
    public void clickSeeAll3() {
        try {
            WebElement seeAll3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(seeAll3Xpath)));
            seeAll3.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("See All 3 button not found", e);
        }
    }

    /**
     * Step 16: Verify Watch page is displayed
     */
    public boolean isWatchPageDisplayed() {
        try {
            // Use longer wait time for Watch page to load
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement watchPage = longWait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(watchPageXpath)));
            return watchPage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Watch page not found. Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Step 17: Click article radio button
     * Clicks the radio button inside the article card, not the card itself
     * Radio button is a clickable android.view.View without content-desc
     */
    public void clickVideoItem() {
        try {
            // Locate radio button relative to article title
            // Radio button has clickable='true' and focusable='true' but no content-desc
            By articleRadio = By.xpath(
                    "//android.view.View[contains(@content-desc,'The Timeless Dance')]" +
                            "//android.view.View[@clickable='true' and @focusable='true']");
            WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(articleRadio));
            radioButton.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Article radio button not found", e);
        }
    }

    /**
     * Step 18: Click search box and search for "one"
     */
    public void searchForOne() {
        try {
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchBoxXpath)));
            searchBox.click();
            searchBox.clear();
            searchBox.sendKeys("one");
        } catch (TimeoutException e) {
            throw new RuntimeException("Search box not found", e);
        }
    }

    /**
     * Step 19: Verify "One" is shown in search results
     * Uses contains() to handle async loading and partial text matches
     */
    public boolean isSearchResultOneDisplayed() {
        try {
            // Use contains() to handle partial text match and async rendering
            By searchResult = By.xpath(
                    "//android.view.View[contains(@content-desc,'One')]");

            // Wait for element to be visible (handles async rendering)
            WebElement result = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(searchResult));

            return result.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 18 (second): Click New File icon
     */
    public void clickNewFileIcon() {
        try {
            WebElement newFileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newFileIconXpath)));
            newFileIcon.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("New File icon not found", e);
        }
    }

    /**
     * Step 19 (second): Create file by typing "New"
     */
    public void createFile(String fileName) {
        try {
            WebElement fileNameInput = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(fileNameInputXpath)));
            fileNameInput.click();
            fileNameInput.clear();
            fileNameInput.sendKeys(fileName);
        } catch (TimeoutException e) {
            throw new RuntimeException("File name input not found", e);
        }
    }

    /**
     * Step 19.5: Trigger save before closing by forcing focus loss
     * This method MUST be called after entering data and BEFORE clicking Close icon
     * 
     * The app only saves data when input focus is lost.
     * Direct Close click skips the save logic.
     * 
     * Solution: Force focus loss by:
     * 1. Hide keyboard to remove input focus
     * 2. Tap outside the input field (bottom of screen) using W3C touch actions
     * 3. Wait for app to trigger its save callback
     * 
     * This simulates real user behavior and ensures data is saved.
     */
    public void triggerSaveBeforeClose() {
        try {
            // Step 1: Hide keyboard to remove input focus
            try {
                ((io.appium.java_client.android.AndroidDriver) driver).hideKeyboard();
                Thread.sleep(500); // Allow keyboard to fully hide
            } catch (Exception e) {
                // Keyboard might already be hidden, continue
                System.out.println("Keyboard hide failed or already hidden: " + e.getMessage());
            }

            // Step 2: Tap outside the input field at bottom of screen
            // This forces focus loss and triggers the app's auto-save callback
            Dimension screenSize = driver.manage().window().getSize();

            // Calculate tap coordinates: center X, 90% down Y (bottom area)
            int tapX = screenSize.width / 2;
            int tapY = (int) (screenSize.height * 0.9);

            // Perform coordinate-based tap using W3C Actions
            // PointerInput.Kind.TOUCH simulates a real finger tap
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);

            // Move pointer to bottom area
            tap.addAction(finger.createPointerMove(
                    Duration.ZERO,
                    PointerInput.Origin.viewport(),
                    tapX,
                    tapY));

            // Pointer down (finger press)
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

            // Pointer up (finger release)
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Execute the tap action
            driver.perform(Collections.singletonList(tap));

            // Step 3: Wait for app to trigger save callback
            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Failed to trigger save before close", e);
        }
    }

    /**
     * Step 20: Click close icon
     * NOTE: Must call triggerSaveBeforeClose() BEFORE this method
     * to ensure data is saved (by forcing focus loss)
     */
    public void clickCloseIcon() {
        try {
            WebElement closeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(closeIconXpath)));
            closeIcon.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Close icon not found", e);
        }
    }

    /**
     * Step 21: Verify Saved dialog is displayed
     */
    public boolean isSavedDialogDisplayed() {
        try {
            WebElement savedDialog = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(savedDialogXpath)));
            return savedDialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 21: Get success message
     */
    public String getSavedSuccessMessage() {
        try {
            WebElement successMessage = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(savedSuccessMessageXpath)));
            return successMessage.getAttribute("content-desc");
        } catch (TimeoutException e) {
            throw new RuntimeException("Saved success message not found", e);
        }
    }

    /**
     * Step 22, 32: Click OK button
     */
    public void clickOkButton() {
        try {
            WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(okButtonXpath)));
            okBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("OK button not found", e);
        }
    }

    /**
     * Step 24: Click Archive button
     */
    public void clickArchive() {
        try {
            WebElement archiveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(archiveButtonXpath)));
            archiveBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("ARCHIVE button not found", e);
        }
    }

    /**
     * Step 25: Verify Archive page is displayed
     */
    public boolean isArchivePageDisplayed() {
        try {
            WebElement archivePage = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(archivePageHeadingXpath)));
            return archivePage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 26: Verify new file is in archive
     * Uses contains() to handle multiline content-desc (e.g., "New\nModified Dec
     * 30")
     * Element is android.widget.ImageView, not View
     */
    public boolean isNewFileInArchive() {
        try {
            // Use contains() to handle multiline content-desc with dynamic date
            By archivedNewFile = By.xpath(
                    "//android.widget.ImageView[contains(@content-desc,'New')]");

            // Wait until the archive list is visible
            WebElement file = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(archivedNewFile));

            // Validate using isDisplayed()
            return file.isDisplayed();
        } catch (Exception e) {
            System.out.println("New file not found in archive. Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Step 27: Click new file in archive using coordinate-based tap
     * Taps the LEFT SIDE (image area) of the container at 25% width from left
     * Normal click() taps center/right which doesn't trigger navigation
     * Uses W3C PointerInput actions for precise coordinate tapping
     */
    public void clickNewFileInArchive() {
        try {
            // Locate the article/container element
            By articleLocator = By.xpath(
                    "//android.widget.ImageView[contains(@content-desc,'New')]");

            WebElement articleElement = wait.until(
                    ExpectedConditions.presenceOfElementLocated(articleLocator));

            // Get container position & size
            org.openqa.selenium.Point location = articleElement.getLocation();
            Dimension size = articleElement.getSize();

            // Calculate LEFT-SIDE tap (image area) - 25% width from left + vertical center
            int tapX = location.getX() + (int) (size.getWidth() * 0.25);
            int tapY = location.getY() + (size.getHeight() / 2);

            // Perform coordinate tap using W3C Actions
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(
                    Duration.ZERO,
                    PointerInput.Origin.viewport(),
                    tapX,
                    tapY));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(tap));

            // Wait for navigation to complete
            Thread.sleep(2000);

        } catch (Exception e) {
            throw new RuntimeException("Failed to tap left side of archive item", e);
        }
    }

    /**
     * Step 28: Verify file is open
     */
    public boolean isFileOpen() {
        try {
            WebElement fileOpen = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fileOpenXpath)));
            return fileOpen.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 29: Click remove icon
     * Uses relative XPath to locate the clickable ImageView within the article view
     * Waits for element to be visible and clickable before clicking
     */
    public void clickRemoveIcon() {
        try {
            // Use relative XPath to find the clickable ImageView (remove icon)
            // This locates the icon relative to the article content-desc
            By removeIconLocator = By.xpath(
                    "//android.view.View[contains(@content-desc,'The Timeless Dance')]//android.widget.ImageView[@clickable='true']");

            // Wait for the remove icon to be visible and clickable
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement removeIcon = longWait.until(
                    ExpectedConditions.elementToBeClickable(removeIconLocator));

            // Click the remove icon
            removeIcon.click();

            // Wait for remove confirmation dialog to appear
            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Failed to click remove icon", e);
        }
    }

    /**
     * Step 30: Click Yes button
     */
    public void clickYesButton() {
        try {
            WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(yesButtonXpath)));
            yesBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("YES button not found", e);
        }
    }

    /**
     * Step 31: Verify Success dialog is displayed
     */
    public boolean isSuccessDialogDisplayed() {
        try {
            WebElement successDialog = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(successDialogXpath)));
            return successDialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Step 31: Get delete success message
     */
    public String getDeleteSuccessMessage() {
        try {
            WebElement deleteMessage = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(deleteSuccessMessageXpath)));
            return deleteMessage.getAttribute("content-desc");
        } catch (TimeoutException e) {
            throw new RuntimeException("Delete success message not found", e);
        }
    }
}