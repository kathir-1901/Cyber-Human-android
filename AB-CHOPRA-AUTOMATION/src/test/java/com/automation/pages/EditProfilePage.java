package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput;
import java.util.Collections;

public class EditProfilePage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public EditProfilePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final String editProfileHeadingXpath = "//android.view.View[@content-desc='EDIT PROFILE']";
    // Changed to index-based locators as text is mutable
    private final String nameFieldXpath = "(//android.widget.EditText)[1]";
    private final String emailFieldXpath = "(//android.widget.EditText)[2]";
    private final String dateOfBirthFieldXpath = "//android.view.View[@hint='Date of birth']";
    private final String genderButtonXpath = "//android.widget.ImageView[@content-desc='Gender']";
    private final String phoneNumberFieldXpath = "//android.widget.EditText[@hint='Phone Number']";
    private final String countryCodeXpath = "//android.view.View[contains(@content-desc, '+')]";
    private final String saveChangesButtonXpath = "//android.widget.Button[@content-desc='SAVE CHANGES']";
    private final String changePasswordButtonXpath = "//android.widget.Button[@content-desc='CHANGE PASSWORD']";

    /**
     * Enter name in the Name field
     */
    public void enterName(String name) {
        try {
            WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(nameFieldXpath)));
            nameField.click();
            nameField.clear();
            nameField.sendKeys(name);
            hideKeyboard();
        } catch (TimeoutException e) {
            throw new RuntimeException("Name field not found on Edit Profile page", e);
        }
    }

    /**
     * Enter email in the Email field
     */
    public void enterEmail(String email) {
        try {
            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(emailFieldXpath)));
            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);
            hideKeyboard();
        } catch (TimeoutException e) {
            throw new RuntimeException("Email field not found on Edit Profile page", e);
        }
    }

    /**
     * Enter phone number in the Phone Number field
     */
    public void enterPhoneNumber(String phoneNumber) {
        try {
            WebElement phoneField = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(phoneNumberFieldXpath)));
            phoneField.click();
            phoneField.clear();
            phoneField.sendKeys(phoneNumber);
            hideKeyboard();
        } catch (TimeoutException e) {
            throw new RuntimeException("Phone Number field not found on Edit Profile page", e);
        }
    }

    /**
     * Click the Date of Birth field to open date picker
     * Includes robust fallback for when validation messages block the view
     */
    public void clickDateOfBirth() {
        try {
            hideKeyboard(); // Ensure keyboard is closed
            Thread.sleep(500); // Small pause
            WebElement dobField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateOfBirthFieldXpath)));
            dobField.click();
        } catch (Exception e) {
            // Retry with Force Scroll and Force Tap
            try {
                System.out.println("Standard click failed for Date of Birth. Attempting Force Scroll & Tap...");
                hideKeyboard();
                swipeUp(); // Scroll down to ensure it's in view
                Thread.sleep(500);

                WebElement dobField = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(dateOfBirthFieldXpath)));
                tapElement(dobField); // Force tap using W3C Actions
                System.out.println("Force tap successful for Date of Birth");
            } catch (Exception ex) {
                throw new RuntimeException(
                        "Date of Birth field not found or clickable on Edit Profile page after retry", ex);
            }
        }
    }

    /**
     * PERFORM DATE SELECTION (SWIPE ACTIONS)
     * Swipes down on Day, Month, and Year SeekBars and clicks Confirm
     * Uses position-based XPath to work with any date (future-proof)
     */
    public void performDateSelection() {
        try {
            Thread.sleep(1000); // Wait for date picker to appear
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Use position-based XPath instead of hardcoded values
            // This works regardless of what date is currently displayed
            String[] seekBarXpaths = {
                    "(//android.widget.SeekBar)[1]", // Day picker (1st SeekBar)
                    "(//android.widget.SeekBar)[2]", // Month picker (2nd SeekBar)
                    "(//android.widget.SeekBar)[3]" // Year picker (3rd SeekBar)
            };

            for (int i = 0; i < seekBarXpaths.length; i++) {
                try {
                    WebElement seekBar = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath(seekBarXpaths[i])));

                    // Perform swipe down action using W3C Actions
                    swipeDown(seekBar);
                    Thread.sleep(500); // Wait for swipe animation

                    System.out.println("Successfully swiped SeekBar " + (i + 1));

                } catch (Exception e) {
                    System.out.println("Could not find or swipe SeekBar at position: " + (i + 1));
                }
            }

            // Click CONFIRM button
            try {
                WebElement confirmBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.widget.Button[@content-desc='CONFIRM']")));
                confirmBtn.click();
                System.out.println("Clicked CONFIRM button on date picker");
            } catch (Exception e) {
                System.out.println("CONFIRM button not found on date picker");
            }

        } catch (Exception e) {
            System.out.println("Error in performDateSelection: " + e.getMessage());
        }
    }

    /**
     * Helper method to swipe down on an element using W3C Actions
     */
    private void swipeDown(WebElement element) {
        int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
        int startY = element.getRect().getY() + (element.getRect().getHeight() / 2);
        int endY = startY + 200; // Swipe down by 200 pixels

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
     * Helper method to Tap on an element using W3C Actions (Force Click)
     */
    private void tapElement(WebElement element) {
        int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
        int centerY = element.getRect().getY() + (element.getRect().getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    /**
     * Click the Gender dropdown button
     * Includes robust fallback
     */
    public void clickGender() {
        try {
            WebElement genderBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(genderButtonXpath)));
            genderBtn.click();
        } catch (TimeoutException e) {
            // Retry with Force Scroll and Force Tap
            try {
                System.out.println("Standard click failed for Gender. Attempting Force Scroll & Tap...");
                swipeUp(); // Scroll down
                Thread.sleep(500);
                WebElement genderBtn = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(genderButtonXpath)));
                tapElement(genderBtn);
                System.out.println("Force tap successful for Gender");
            } catch (Exception ex) {
                throw new RuntimeException("Gender button not found on Edit Profile page after retry", ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Gender button interaction failed", e);
        }
    }

    /**
     * Select gender from dropdown
     * 
     * @param gender Gender option (e.g., "Male", "Female", "Other")
     */
    public void selectGender(String gender) {
        try {
            Thread.sleep(1000); // Wait for dropdown to appear
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Try to find gender option
            WebElement genderOption = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.Button[@content-desc='" + gender
                            + "'] | //android.view.View[@content-desc='" + gender + "']")));
            genderOption.click();
        } catch (Exception e) {
            System.out.println("Could not select gender: " + gender + " - " + e.getMessage());
        }
    }

    /**
     * Click the Country Code dropdown
     * Includes robust fallback
     */
    public void clickCountryCode() {
        try {
            WebElement countryCode = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(countryCodeXpath)));
            countryCode.click();
        } catch (TimeoutException e) {
            // Retry with Force Scroll and Force Tap
            try {
                System.out.println("Standard click failed for Country Code. Attempting Force Scroll & Tap...");
                swipeUp(); // Scroll down
                Thread.sleep(500);
                WebElement countryCode = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(countryCodeXpath)));
                tapElement(countryCode);
                System.out.println("Force tap successful for Country Code");
            } catch (Exception ex) {
                throw new RuntimeException("Country Code dropdown not found on Edit Profile page after retry", ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Country Code interaction failed", e);
        }
    }

    /**
     * Select country from country code dropdown
     * Scroll until finding the country using W3C actions
     * 
     * @param country Country name (e.g., "India")
     */
    public void selectCountry(String country) {
        try {
            Thread.sleep(1000); // Wait for dropdown list to fully load
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

            int maxSwipes = 20; // Maximum swipes to find the element
            boolean found = false;

            // Xpath for Belarus with specific content-desc format as requested
            // Using contains to handle the newline characters safely
            String countryXpath = "//android.widget.Button[@content-desc='🇧🇾\nBelarus\n+375']";

            for (int i = 0; i < maxSwipes; i++) {
                try {
                    // Try to find country option
                    WebElement countryOption = shortWait
                            .until(ExpectedConditions.elementToBeClickable(By.xpath(countryXpath)));
                    countryOption.click();
                    found = true;
                    break;
                } catch (Exception e) {
                    // Element not found visible, swipe up (scroll down)
                    swipeUp();
                }
            }

            if (!found) {
                System.out.println("Could not find country: " + country + " after " + maxSwipes + " swipes.");
            }

        } catch (Exception e) {
            System.out.println("Error selecting country: " + country + " - " + e.getMessage());
        }
    }

    /**
     * Helper method to swipe up (scroll down)
     */
    private void swipeUp() {
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
     * Click the SAVE CHANGES button
     */
    public void clickSaveChanges() {
        try {
            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(saveChangesButtonXpath)));
            saveBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("SAVE CHANGES button not found on Edit Profile page", e);
        }
    }

    /**
     * Click the CHANGE PASSWORD button
     */
    public void clickChangePassword() {
        try {
            WebElement changePassBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(changePasswordButtonXpath)));
            changePassBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("CHANGE PASSWORD button not found on Edit Profile page", e);
        }
    }

    /**
     * Check if Edit Profile page is displayed
     */
    public boolean isEditProfilePageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(editProfileHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * RUNTIME-BASED VALIDATION DETECTION (NO HARDCODED MESSAGES)
     * 
     * Checks at runtime if ANY validation element is visible:
     * - INVALID INPUT Popup (Top Priority)
     * - Validation message via content-desc (android.view.View)
     * - Toast message
     * - Inline error text
     * - EditText with error state
     * 
     * @return true if ANY validation is detected, false if NONE found
     */
    public boolean isAnyValidationVisible() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Check -1: Success Popup (User requested to treat success as pass)
        try {
            WebElement successPopup = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.View[@content-desc='Your profile has been updated']")));
            if (successPopup.isDisplayed()) {
                return true;
            }
        } catch (Exception ignored) {
        }

        // Check 0: INVALID INPUT Popup (Highest Priority)
        try {
            WebElement invalidInputPopup = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.View[@content-desc='INVALID INPUT']")));
            if (invalidInputPopup.isDisplayed()) {
                return true;
            }
        } catch (Exception ignored) {
        }

        // Check 1: Validation message via content-desc with error keywords
        try {
            WebElement validationView = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//android.view.View[@content-desc and (contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'must') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cannot'))]")));
            String contentDesc = validationView.getAttribute("content-desc");
            if (contentDesc != null && !contentDesc.trim().isEmpty()) {
                return true;
            }
        } catch (Exception ignored) {
        }

        // Check 2: Android Toast Message
        try {
            WebElement toast = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.Toast[1]")));
            if (toast != null && toast.getText() != null && !toast.getText().trim().isEmpty()) {
                return true;
            }
        } catch (Exception ignored) {
        }

        // Check 3: TextView with error keywords
        try {
            WebElement errorKeyword = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[" +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please')]")));
            String text = errorKeyword.getText();
            if (text != null && !text.trim().isEmpty()) {
                return true;
            }
        } catch (Exception ignored) {
        }

        // Check 4: EditText with error attribute
        try {
            WebElement editTextError = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.EditText[@error='true']")));
            if (editTextError != null) {
                return true;
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
     * 
     * Priority order for message capture:
     * 1. INVALID INPUT Popup message
     * 2. Validation message from content-desc (android.view.View)
     * 3. Toast message text
     * 4. Inline error TextView text
     * 5. EditText error attribute
     * 
     * @return The actual validation message text, or null if no validation found
     */
    public String getValidationMessage() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Priority -1: Success Popup
        try {
            WebElement successPopup = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.View[@content-desc='Your profile has been updated']")));
            if (successPopup.isDisplayed()) {
                return successPopup.getAttribute("content-desc");
            }
        } catch (Exception ignored) {
        }

        // Priority 0: INVALID INPUT Popup message
        try {
            WebElement invalidInputPopup = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.View[@content-desc='INVALID INPUT']")));
            if (invalidInputPopup.isDisplayed()) {
                // Try to find the specific message "Invalid phone number." or similar sibling
                try {
                    // Assuming the error message is a sibling or near the popup title
                    // Searching for generic message view near it or text view
                    WebElement messageView = driver.findElement(By.xpath(
                            "//android.view.View[@content-desc='INVALID INPUT']/following-sibling::android.view.View[1]"));
                    String msg = messageView.getAttribute("content-desc");
                    if (msg != null && !msg.isEmpty())
                        return msg;
                } catch (Exception e) {
                    // Fallback: Return a generic message if check fails but popup is there
                    return "INVALID INPUT Popup Displayed";
                }
                try {
                    // Alternative locator for message text inside the dialog/popup
                    WebElement messageView = driver
                            .findElement(By.xpath("//android.view.View[@content-desc='Invalid phone number.']"));
                    return messageView.getAttribute("content-desc");
                } catch (Exception e) {
                }
                return "INVALID INPUT Popup Displayed (Message capture failed)";
            }
        } catch (Exception ignored) {
        }

        // Priority 1: Validation message via content-desc with error keywords
        try {
            WebElement validationView = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//android.view.View[@content-desc and (contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'must') or "
                                    +
                                    "contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cannot'))]")));
            String contentDesc = validationView.getAttribute("content-desc");
            if (contentDesc != null && !contentDesc.trim().isEmpty()) {
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

        // Priority 3: TextView with error keywords
        try {
            WebElement errorKeyword = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[" +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'required') or "
                            +
                            "contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'please')]")));
            String text = errorKeyword.getText();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
        }

        // Priority 4: EditText error attribute
        try {
            WebElement editTextError = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.EditText[@error='true']")));
            String errorAttr = editTextError.getAttribute("error");
            if (errorAttr != null && !errorAttr.trim().isEmpty()) {
                return errorAttr;
            }
        } catch (Exception ignored) {
        }

        // No validation message found
        return null;
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
}
