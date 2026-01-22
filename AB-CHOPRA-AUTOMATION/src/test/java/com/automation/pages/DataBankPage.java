package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class DataBankPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public DataBankPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==================== LOCATORS ====================

    // Step 3 - Click and verify DATA BANK
    private final String dataBankButtonXpath = "//android.view.View[@content-desc=\"DATA BANK\"]";
    private final String dataBankHeadingXpath = "//android.view.View[@content-desc=\"DATA BANK\"]";

    // Test Case 1 - Specific Steps
    private final String packagesAndPricingXpath = "//android.view.View[@content-desc=\"PACKAGES & PRICING\"]";
    private final String continueButtonXpath = "//android.widget.Button[@content-desc=\"CONTINUE\"]";
    private final String checkboxXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]";

    // Test Case 1 - Checkout Flow Steps (8-32)
    private final String bagPageXpath = "//android.view.View[@content-desc=\"BAG\"]";
    private final String essentialEpigeneticsDropdownXpath = "//android.widget.ImageView[@content-desc=\"Essential Epigenetics\"]";
    private final String proceedToCheckoutButtonXpath = "//android.widget.Button[@content-desc=\"PROCEED TO CHECKOUT\"]";
    private final String checkoutPageXpath = "//android.view.View[@content-desc=\"CHECKOUT\"]";
    private final String deliveryAddressXpath = "//android.view.View[@content-desc=\"Delivery Address\"]";
    private final String nameFieldXpath = "//android.widget.EditText[@text=\"John Doe\"]";
    private final String confirmButtonXpath = "//android.widget.Button[@content-desc=\"CONFIRM\"]";
    private final String genderDropdownXpath = "//android.widget.ImageView[@content-desc=\"Gender\"]";
    private final String maleButtonXpath = "//android.widget.Button[@content-desc=\"Male\"]";
    private final String countryCodeXpath = "//android.view.View[@content-desc=\"🇦🇫 +93\"]";
    private final String countrySearchFieldXpath = "//android.widget.EditText";
    private final String phoneNumberFieldXpath = "//android.widget.ScrollView/android.widget.EditText[2]";
    private final String addressFieldXpath = "//android.widget.ScrollView/android.widget.EditText[3]";
    private final String cityFieldXpath = "//android.widget.ScrollView/android.widget.EditText[4]";
    private final String countryDropdownXpath = "//android.widget.ImageView[@content-desc=\"India\"]";
    private final String indiaButtonXpath = "//android.widget.Button[@content-desc=\"India\"]";
    private final String postalCodeFieldXpath = "//android.widget.ScrollView/android.widget.EditText[5]";
    private final String saveAddressButtonXpath = "//android.widget.Button[@content-desc=\"SAVE ADDRESS\"]";
    private final String errorDialogXpath = "//android.view.View[@content-desc=\"FIX THE FOLLOWING ERRORS\"]";

    private final String shippingMethodXpath = "//android.view.View[@content-desc=\"Shipping Method\"]";
    private final String proceedToPaymentButtonXpath = "//android.widget.Button[@content-desc=\"PROCEED TO PAYMENT\"]";
    private final String closeSheetXpath = "//android.view.View[@content-desc=\"Close sheet\"]";
    private final String paymentErrorDialogXpath = "//android.view.View[@content-desc=\"PAYMENT ERROR\"]";
    private final String paymentErrorMessageXpath = "//android.view.View[@content-desc=\"Please try again later\"]";
    private final String retryPaymentButtonXpath = "//android.view.View[@content-desc=\"Retry Payment\"]";
    private final String paymentPageButtonXpath = "//android.widget.ScrollView/android.view.View[1]/android.widget.Button";

    // Test Case 2 - Specific Steps
    private final String uploadDataXpath = "//android.view.View[@content-desc=\"UPLOAD DATA\"]";
    private final String uploadReportButtonXpath = "//android.widget.Button[@content-desc=\"UPLOAD REPORT\"]";
    private final String uploadFailedDialogXpath = "//android.view.View[@content-desc=\"UPLOAD FAILED\"]";
    private final String uploadFailedMessageXpath = "//android.view.View[@content-desc=\"Wrong file type chosen. Only allowed type is pdf.\"]";
    private final String okButtonXpath = "//android.widget.Button[@content-desc=\"OK\"]";
    private final String uploadSuccessfulDialogXpath = "//android.view.View[@content-desc=\"UPLOAD SUCCESSFUL\"]";
    private final String uploadSuccessMessageXpath = "//android.view.View[@content-desc=\"Your document has been successfully uploaded\"]";
    private final String removeButtonXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]";
    private final String areYouSureDialogXpath = "//android.view.View[@content-desc=\"ARE YOU SURE?\"]";
    private final String yesButtonXpath = "//android.widget.Button[@content-desc=\"YES\"]";
    private final String deleteSuccessfulDialogXpath = "//android.view.View[@content-desc=\"DELETE SUCCESSFUL\"]";
    private final String deleteSuccessMessageXpath = "//android.view.View[@content-desc=\"Your report has been successfully removed.\"]";

    // Test Case 3 - Specific Steps
    private final String devicesXpath = "//android.view.View[@content-desc=\"DEVICES\"]";
    private final String linkDeviceButtonXpath = "//android.widget.Button[@content-desc=\"LINK DEVICE\"]";
    private final String ultrahumanButtonXpath = "//android.view.View[@content-desc=\"ULTRAHUMAN\"]";
    private final String connectWithUltrahumanDialogXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View";
    private final String emailFieldXpath = "//android.widget.EditText";
    private final String scrollViewXpath = "//android.widget.ScrollView";
    private final String verifyButtonXpath = "//android.widget.Button[@content-desc=\"VERIFY\"]";
    private final String invalidEmailDialogXpath = "//android.view.View[@content-desc=\"INVALID EMAIL ID\"]";
    private final String deviceLinkedDialogXpath = "//android.view.View[@content-desc=\"DEVICE LINKED\"]";
    private final String deviceLinkedMessageXpath = "//android.view.View[@content-desc=\"Your device has been successfully linked.\"]";

    // Test Case 4 - Specific Steps
    private final String reportsXpath = "//android.view.View[@content-desc=\"REPORTS\"]";
    private final String bloodReportXpath = "//android.view.View[@content-desc=\"BLOOD REPORT\"]";
    private final String dnaReportXpath = "//android.view.View[@content-desc=\"DNA REPORT\"]";
    private final String deviceReportXpath = "//android.view.View[@content-desc=\"DEVICE REPORT\"]";
    private final String uploadReportButtonCase4Xpath = "//android.widget.Button[@content-desc=\"UPLOAD REPORT\"]";
    private final String uploadDataPageXpath = "//android.view.View[@content-desc=\"UPLOAD DATA\"]";
    private final String epigeneticMappingButtonXpath = "//android.widget.Button[@content-desc=\"EPIGENETIC MAPPING\"]";
    private final String packagesAndPricingPageXpath = "//android.view.View[@content-desc=\"PACKAGES & PRICING\"]";
    private final String backButtonXpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView";

    // Test Case 4 - Priority Filter Steps (New Steps 6-13)
    private final String highPriorityButtonXpath = "//android.view.View[@content-desc=\"HIGH PRIORITY\"]";
    private final String mediumPriorityButtonXpath = "//android.view.View[@content-desc=\"MEDIUM PRIORITY\"]";
    private final String lowPriorityButtonXpath = "//android.view.View[@content-desc=\"LOW PRIORITY\"]";
    private final String renalDropdownXpath = "//android.widget.ImageView[@content-desc=\"RENAL\"]";
    // XPath for verification (using contains() to handle newline characters)
    private final String creatinineHighPriorityXpath = "//android.widget.ImageView[contains(@content-desc, 'Creatinine') and contains(@content-desc, 'High Priority')]";
    private final String bunMediumPriorityXpath = "//android.widget.ImageView[contains(@content-desc, 'Bun') and contains(@content-desc, 'Medium Priority') and not(contains(@content-desc, 'creatinine'))]";
    private final String bunCreatinineLowPriorityXpath = "//android.widget.ImageView[contains(@content-desc, 'Bun/creatinine') and contains(@content-desc, 'Low Priority')]";
    private final String egfrMediumPriorityXpath = "//android.widget.ImageView[contains(@content-desc, 'Egfr') and contains(@content-desc, 'Medium Priority')]";
    // XPath for second click in step 12 (simpler - just the name)
    private final String bunSimpleXpath = "//android.widget.ImageView[@content-desc=\"Bun\"]";
    private final String creatinineSimpleXpath = "//android.widget.ImageView[@content-desc=\"Creatinine\"]";
    private final String bunCreatinineSimpleXpath = "//android.widget.ImageView[@content-desc=\"Bun/creatinine\"]";
    private final String egfrSimpleXpath = "//android.widget.ImageView[@content-desc=\"Egfr\"]";

    // ==================== COMMON STEP 3 ====================

    /**
     * COMMON STEP 3 FOR ALL 4 TEST CASES:
     * Click "DATA BANK" and verify it's displayed
     */
    public void clickAndVerifyDataBank() {
        try {
            // Click DATA BANK button
            WebElement dataBankButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(dataBankButtonXpath)));
            dataBankButton.click();
            System.out.println("✓ Step 3: Clicked 'DATA BANK' button");

            // Verify DATA BANK heading is displayed
            WebElement dataBankHeading = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(dataBankHeadingXpath)));

            if (dataBankHeading.isDisplayed()) {
                System.out.println("✓ Step 3: Verified 'DATA BANK' heading is displayed");
            } else {
                throw new RuntimeException("DATA BANK heading is not displayed");
            }

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to navigate to/verify Data Bank in Step 3", e);
        }
    }

    // ==================== TEST CASE 1 SPECIFIC METHODS ====================

    /**
     * TEST CASE 1 - STEP 4:
     * Click "PACKAGES & PRICING"
     */
    public void clickPackagesAndPricing() {
        try {
            WebElement packagesAndPricing = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(packagesAndPricingXpath)));
            packagesAndPricing.click();
            System.out.println("✓ Step 4: Clicked 'PACKAGES & PRICING'");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click PACKAGES & PRICING in Step 4", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 5 & 7:
     * Click "CONTINUE" button
     */
    public void clickContinueButton() {
        try {
            WebElement continueButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(continueButtonXpath)));
            continueButton.click();
            System.out.println("✓ Clicked 'CONTINUE' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click CONTINUE button", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 6:
     * Click the checkbox
     */
    public void clickCheckbox() {
        try {
            WebElement checkbox = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(checkboxXpath)));
            checkbox.click();
            System.out.println("✓ Step 6: Clicked checkbox");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click checkbox in Step 6", e);
        }
    }

    // ==================== TEST CASE 2 SPECIFIC METHODS ====================

    /**
     * TEST CASE 2 - STEP 4:
     * Click "UPLOAD DATA"
     */
    public void clickUploadData() {
        try {
            WebElement uploadData = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(uploadDataXpath)));
            uploadData.click();
            System.out.println("✓ Step 4: Clicked 'UPLOAD DATA'");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click UPLOAD DATA in Step 4", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 5:
     * Click "UPLOAD REPORT" button
     */
    public void clickUploadReportButton() {
        try {
            WebElement uploadReportButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(uploadReportButtonXpath)));
            uploadReportButton.click();
            System.out.println("✓ Step 5: Clicked 'UPLOAD REPORT' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click UPLOAD REPORT button in Step 5", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 8:
     * Upload wrong format file (JPG instead of PDF)
     * Uses Android file picker to select the file
     */
    public void uploadWrongFormatFile() {
        try {
            // Wait for file picker to open
            Thread.sleep(3000);

            System.out.println("✓ Step 8: Navigating Android file picker to select JPG file");

            // Click on the menu/hamburger icon to show storage options (if needed)
            try {
                WebElement menuButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//android.widget.ImageButton[@content-desc='Show roots']")));
                menuButton.click();
                Thread.sleep(1000);
                System.out.println("  - Opened storage menu");
            } catch (Exception e) {
                System.out.println("  - Storage menu already open or not needed");
            }

            // Select "Internal storage" or device name
            try {
                WebElement internalStorage = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//android.widget.TextView[contains(@text, 'Internal') or contains(@text, '24116RNC1I')]")));
                internalStorage.click();
                Thread.sleep(1000);
                System.out.println("  - Selected Internal storage");
            } catch (Exception e) {
                System.out.println("  - Already in Internal storage");
            }

            // Navigate to Download folder
            WebElement downloadFolder = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
            downloadFolder.click();
            Thread.sleep(1000);
            System.out.println("  - Opened Download folder");

            // Click Download folder again to navigate deeper
            WebElement downloadFolderAgain = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
            downloadFolderAgain.click();
            Thread.sleep(1000);
            System.out.println("  - Opened Download folder again");

            // Select the JPEG file
            WebElement jpegFile = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//android.widget.TextView[contains(@text, 'images.jpeg') or @text='images.jpeg']")));
            jpegFile.click();
            Thread.sleep(1000);
            System.out.println(
                    "✓ Step 8: Selected JPEG file - images.jpeg from Download folder");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during file upload in Step 8", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to navigate file picker or select file in Step 8", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 9:
     * Validate UPLOAD FAILED dialog is shown and capture error message
     * 
     * @return The error message displayed in the dialog
     */
    public String validateUploadFailedDialog() {
        try {
            // Verify UPLOAD FAILED dialog is displayed
            WebElement uploadFailedDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadFailedDialogXpath)));

            if (uploadFailedDialog.isDisplayed()) {
                System.out.println("✓ Step 9: UPLOAD FAILED dialog is displayed");
            } else {
                throw new RuntimeException("UPLOAD FAILED dialog is not displayed");
            }

            // Capture the error message
            WebElement errorMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadFailedMessageXpath)));

            String message = errorMessage.getAttribute("content-desc");
            System.out.println("✓ Step 9: Error message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to validate UPLOAD FAILED dialog in Step 9", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 10 & 15:
     * Click OK button (used after both failed and successful upload dialogs)
     */
    public void clickOkButton() {
        try {
            WebElement okButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(okButtonXpath)));
            okButton.click();
            System.out.println("✓ Clicked 'OK' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click OK button", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 12:
     * Upload correct format file (PDF)
     * Navigates through file picker to select PM0140.pdf from Download folder
     */
    public void uploadCorrectFormatFile() {
        try {
            // Wait for file picker to open
            Thread.sleep(3000);

            System.out.println("✓ Step 12: Navigating Android file picker to select PDF file");

            // Click on the menu/hamburger icon to show storage options (if needed)
            try {
                WebElement menuButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//android.widget.ImageButton[@content-desc='Show roots']")));
                menuButton.click();
                Thread.sleep(1000);
                System.out.println("  - Opened storage menu");
            } catch (Exception e) {
                System.out.println("  - Storage menu already open or not needed");
            }

            // Select "Internal storage" or device name
            try {
                WebElement internalStorage = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//android.widget.TextView[contains(@text, 'Internal') or contains(@text, '24116RNC1I')]")));
                internalStorage.click();
                Thread.sleep(1000);
                System.out.println("  - Selected Internal storage");
            } catch (Exception e) {
                System.out.println("  - Already in Internal storage");
            }

            // Strategy 1: Try to navigate via Document → Download path
            boolean foundViaDocumentPath = false;
            try {
                WebElement documentFolder = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//android.widget.TextView[contains(@text, 'Documents') or @text='Documents']")));
                documentFolder.click();
                Thread.sleep(1000);
                System.out.println("  - Opened Document folder");

                // Navigate to Download subfolder inside Document
                WebElement downloadFolder = wait.until(
                        ExpectedConditions
                                .elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
                downloadFolder.click();
                Thread.sleep(1000);
                System.out.println("  - Opened Download subfolder");
                foundViaDocumentPath = true;

            } catch (TimeoutException e) {
                System.out.println("  - Document folder path not available, trying alternative...");
            }

            // Strategy 2: If Document path failed, try Download folder directly (like JPEG
            // upload)
            if (!foundViaDocumentPath) {
                try {
                    WebElement downloadFolder = wait.until(
                            ExpectedConditions
                                    .elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
                    downloadFolder.click();
                    Thread.sleep(1000);
                    System.out.println("  - Opened Download folder directly");

                    // Try clicking Download again (nested structure)
                    try {
                        WebElement downloadFolderAgain = wait.until(
                                ExpectedConditions
                                        .elementToBeClickable(By.xpath("//android.widget.TextView[@text='Download']")));
                        downloadFolderAgain.click();
                        Thread.sleep(1000);
                        System.out.println("  - Opened Download folder again");
                    } catch (Exception nested) {
                        System.out.println("  - Single Download folder (no nesting)");
                    }
                } catch (TimeoutException e2) {
                    System.out.println("  - Download folder also not found, will try to find PDF directly");
                }
            }

            // Select the PDF file
            WebElement pdfFile = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//android.widget.TextView[contains(@text, 'PM0140.pdf') or @text='PM0140.pdf']")));
            pdfFile.click();
            Thread.sleep(1000);
            System.out.println("✓ Step 12: Selected PDF file - PM0140.pdf from Document/Download folder");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during PDF file upload in Step 12", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to navigate file picker or select PDF file in Step 12", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 13 & 14:
     * Validate UPLOAD SUCCESSFUL dialog is shown and capture success message
     * 
     * @return The success message displayed in the dialog
     */
    public String validateUploadSuccessfulDialog() {
        try {
            // Verify UPLOAD SUCCESSFUL dialog is displayed
            WebElement uploadSuccessDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadSuccessfulDialogXpath)));

            if (uploadSuccessDialog.isDisplayed()) {
                System.out.println("✓ Step 13: UPLOAD SUCCESSFUL dialog is displayed");
            } else {
                throw new RuntimeException("UPLOAD SUCCESSFUL dialog is not displayed");
            }

            // Capture the success message
            WebElement successMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadSuccessMessageXpath)));

            String message = successMessage.getAttribute("content-desc");
            System.out.println("✓ Step 14: Success message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to validate UPLOAD SUCCESSFUL dialog in Step 13/14", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 16:
     * Click remove button and verify "ARE YOU SURE?" dialog is displayed
     */
    public void clickRemoveAndVerifyAreYouSureDialog() {
        try {
            // Click remove button
            WebElement removeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(removeButtonXpath)));
            removeButton.click();
            System.out.println("✓ Step 16: Clicked remove button");

            // Verify ARE YOU SURE? dialog is displayed
            WebElement areYouSureDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(areYouSureDialogXpath)));

            if (areYouSureDialog.isDisplayed()) {
                System.out.println("✓ Step 16: ARE YOU SURE? dialog is displayed");
            } else {
                throw new RuntimeException("ARE YOU SURE? dialog is not displayed");
            }

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click remove button or verify ARE YOU SURE? dialog in Step 16", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 17:
     * Click YES button and verify DELETE SUCCESSFUL dialog is displayed
     */
    public void clickYesAndVerifyDeleteSuccessfulDialog() {
        try {
            // Click YES button
            WebElement yesButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(yesButtonXpath)));
            yesButton.click();
            System.out.println("✓ Step 17: Clicked YES button");

            // Verify DELETE SUCCESSFUL dialog is displayed
            WebElement deleteSuccessDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deleteSuccessfulDialogXpath)));

            if (deleteSuccessDialog.isDisplayed()) {
                System.out.println("✓ Step 17: DELETE SUCCESSFUL dialog is displayed");
            } else {
                throw new RuntimeException("DELETE SUCCESSFUL dialog is not displayed");
            }

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click YES button or verify DELETE SUCCESSFUL dialog in Step 17", e);
        }
    }

    /**
     * TEST CASE 2 - STEP 18:
     * Capture the delete success message at runtime
     * 
     * @return The delete success message displayed in the dialog
     */
    public String captureDeleteSuccessMessage() {
        try {
            // Capture the success message
            WebElement successMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deleteSuccessMessageXpath)));

            String message = successMessage.getAttribute("content-desc");
            System.out.println("✓ Step 18: Delete success message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to capture delete success message in Step 18", e);
        }
    }

    // ==================== TEST CASE 3 SPECIFIC METHODS ====================

    /**
     * TEST CASE 3 - STEP 4:
     * Click "DEVICES"
     */
    public void clickDevices() {
        try {
            WebElement devices = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(devicesXpath)));
            devices.click();
            System.out.println("✓ Step 4: Clicked 'DEVICES'");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click DEVICES in Step 4", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 5 (also used in Step 14):
     * Click "LINK DEVICE" button
     */
    public void clickLinkDeviceButton() {
        try {
            WebElement linkDeviceButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(linkDeviceButtonXpath)));
            linkDeviceButton.click();
            System.out.println("✓ Clicked 'LINK DEVICE' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click LINK DEVICE button", e);
        }
    }

    /**
     * TEST CASE 3 - NEW STEP 6:
     * Click "ULTRAHUMAN" button
     */
    public void clickUltrahumanButton() {
        try {
            WebElement ultrahumanButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(ultrahumanButtonXpath)));
            ultrahumanButton.click();
            System.out.println("✓ Clicked 'ULTRAHUMAN' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click ULTRAHUMAN button", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 8:
     * Verify "CONNECT WITH ULTRAHUMAN" dialog is displayed
     */
    public void verifyConnectWithUltrahumanDialog() {
        try {
            WebElement dialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(connectWithUltrahumanDialogXpath)));

            if (dialog.isDisplayed()) {
                System.out.println("✓ Step 8: CONNECT WITH ULTRAHUMAN dialog is displayed");
            } else {
                throw new RuntimeException("CONNECT WITH ULTRAHUMAN dialog is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to verify CONNECT WITH ULTRAHUMAN dialog in Step 8", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 9:
     * Enter invalid email in the email field
     * 
     * @param email The invalid email to enter
     */
    public void enterInvalidEmail(String email) {
        try {
            // Click, clear, and send keys to email field
            WebElement emailField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(emailFieldXpath)));
            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);
            System.out.println("✓ Step 9: Entered invalid email: " + email);

            // Click on ScrollView to dismiss keyboard
            WebElement scrollView = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(scrollViewXpath)));
            scrollView.click();
            System.out.println("✓ Step 9: Clicked ScrollView");

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to enter invalid email in Step 9", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 18:
     * Enter valid email in the email field
     * 
     * @param email The valid email to enter
     */
    public void enterValidEmail(String email) {
        try {
            // Click, clear, and send keys to email field
            WebElement emailField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(emailFieldXpath)));
            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);
            System.out.println("✓ Step 18: Entered valid email: " + email);

            // Click on ScrollView to dismiss keyboard
            WebElement scrollView = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(scrollViewXpath)));
            scrollView.click();
            System.out.println("✓ Step 18: Clicked ScrollView");

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to enter valid email in Step 18", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 10 & 19:
     * Click "VERIFY" button
     */
    public void clickVerifyButton() {
        try {
            WebElement verifyButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(verifyButtonXpath)));
            verifyButton.click();
            System.out.println("✓ Clicked 'VERIFY' button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click VERIFY button", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 11 & 12:
     * Validate INVALID EMAIL ID dialog is shown and capture error message
     * 
     * @return The error message displayed in the dialog
     */
    public String validateInvalidEmailDialog() {
        try {
            // Verify INVALID EMAIL ID dialog is displayed
            WebElement invalidEmailDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(invalidEmailDialogXpath)));

            if (invalidEmailDialog.isDisplayed()) {
                System.out.println("✓ Step 11: INVALID EMAIL ID dialog is displayed");
            } else {
                throw new RuntimeException("INVALID EMAIL ID dialog is not displayed");
            }

            // Capture the error message (the dialog itself contains the message)
            String message = invalidEmailDialog.getAttribute("content-desc");
            System.out.println("✓ Step 12: Error message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to validate INVALID EMAIL ID dialog in Step 11/12", e);
        }
    }

    /**
     * TEST CASE 3 - STEP 20 & 21:
     * Validate DEVICE LINKED dialog is shown and capture success message
     * 
     * @return The success message displayed in the dialog
     */
    public String validateDeviceLinkedDialog() {
        try {
            // Verify DEVICE LINKED dialog is displayed
            WebElement deviceLinkedDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deviceLinkedDialogXpath)));

            if (deviceLinkedDialog.isDisplayed()) {
                System.out.println("✓ Step 20: DEVICE LINKED dialog is displayed");
            } else {
                throw new RuntimeException("DEVICE LINKED dialog is not displayed");
            }

            // Capture the success message
            WebElement successMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deviceLinkedMessageXpath)));

            String message = successMessage.getAttribute("content-desc");
            System.out.println("✓ Step 21: Success message captured: " + message);
            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to validate DEVICE LINKED dialog in Step 20/21", e);
        }
    }

    // ==================== TEST CASE 4 SPECIFIC METHODS ====================

    /**
     * TEST CASE 4 - STEP 4, 8, 12:
     * Click "REPORTS"
     */
    public void clickReports() {
        try {
            WebElement reports = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(reportsXpath)));
            reports.click();
            System.out.println("✓ Clicked 'REPORTS'");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click REPORTS", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 5:
     * Click "BLOOD REPORT" and verify page is displayed
     */
    public void clickAndVerifyBloodReport() {
        try {
            // Click BLOOD REPORT
            WebElement bloodReport = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(bloodReportXpath)));
            bloodReport.click();
            System.out.println("✓ Step 5: Clicked 'BLOOD REPORT'");

            // Verify BLOOD REPORT page is displayed
            WebElement bloodReportPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(bloodReportXpath)));

            if (bloodReportPage.isDisplayed()) {
                System.out.println("✓ Step 5: Verified 'BLOOD REPORT' page is displayed");
            } else {
                throw new RuntimeException("BLOOD REPORT page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click/verify BLOOD REPORT in Step 5", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 9:
     * Click "DNA REPORT" and verify page is displayed
     */
    public void clickAndVerifyDnaReport() {
        try {
            // Click DNA REPORT
            WebElement dnaReport = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(dnaReportXpath)));
            dnaReport.click();
            System.out.println("✓ Step 9: Clicked 'DNA REPORT'");

            // Verify DNA REPORT page is displayed
            WebElement dnaReportPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(dnaReportXpath)));

            if (dnaReportPage.isDisplayed()) {
                System.out.println("✓ Step 9: Verified 'DNA REPORT' page is displayed");
            } else {
                throw new RuntimeException("DNA REPORT page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click/verify DNA REPORT in Step 9", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 13:
     * Click "DEVICE REPORT" and verify page is displayed
     */
    public void clickAndVerifyDeviceReport() {
        try {
            // Click DEVICE REPORT
            WebElement deviceReport = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(deviceReportXpath)));
            deviceReport.click();
            System.out.println("✓ Step 13: Clicked 'DEVICE REPORT'");

            // Verify DEVICE REPORT page is displayed
            WebElement deviceReportPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deviceReportXpath)));

            if (deviceReportPage.isDisplayed()) {
                System.out.println("✓ Step 13: Verified 'DEVICE REPORT' page is displayed");
            } else {
                throw new RuntimeException("DEVICE REPORT page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click/verify DEVICE REPORT in Step 13", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 6:
     * Click "UPLOAD REPORT" button and verify UPLOAD DATA page is displayed
     */
    public void clickUploadReportAndVerifyUploadDataPage() {
        try {
            // Click UPLOAD REPORT button
            WebElement uploadReportButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(uploadReportButtonCase4Xpath)));
            uploadReportButton.click();
            System.out.println("✓ Step 6: Clicked 'UPLOAD REPORT' button");

            // Verify UPLOAD DATA page is displayed
            WebElement uploadDataPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(uploadDataPageXpath)));

            if (uploadDataPage.isDisplayed()) {
                System.out.println("✓ Step 6: Verified 'UPLOAD DATA' page is displayed");
            } else {
                throw new RuntimeException("UPLOAD DATA page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click UPLOAD REPORT or verify UPLOAD DATA page in Step 6", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 10:
     * Click "EPIGENETIC MAPPING" button and verify PACKAGES & PRICING page is
     * displayed
     */
    public void clickEpigeneticMappingAndVerifyPackagesPage() {
        try {
            // Click EPIGENETIC MAPPING button
            WebElement epigeneticMappingButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(epigeneticMappingButtonXpath)));
            epigeneticMappingButton.click();
            System.out.println("✓ Step 10: Clicked 'EPIGENETIC MAPPING' button");

            // Verify PACKAGES & PRICING page is displayed
            WebElement packagesPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(packagesAndPricingPageXpath)));

            if (packagesPage.isDisplayed()) {
                System.out.println("✓ Step 10: Verified 'PACKAGES & PRICING' page is displayed");
            } else {
                throw new RuntimeException("PACKAGES & PRICING page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    "Failed to click EPIGENETIC MAPPING or verify PACKAGES & PRICING page in Step 10", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 7, 11, 14:
     * Click back button (can be called multiple times)
     */
    public void clickBackButton() {
        try {
            WebElement backButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(backButtonXpath)));
            backButton.click();
            System.out.println("✓ Clicked back button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click back button", e);
        }
    }

    /**
     * TEST CASE 4 - STEP 7, 11, 14:
     * Click back button twice
     */
    public void clickBackButtonTwice() {
        try {
            // First click
            WebElement backButton1 = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(backButtonXpath)));
            backButton1.click();
            System.out.println("✓ Clicked back button (1st time)");

            Thread.sleep(500); // Small delay between clicks

            // Second click
            WebElement backButton2 = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(backButtonXpath)));
            backButton2.click();
            System.out.println("✓ Clicked back button (2nd time)");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during back button clicks", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click back button twice", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 6:
     * Click HIGH PRIORITY filter button
     */
    public void clickHighPriority() {
        try {
            WebElement highPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(highPriorityButtonXpath)));
            highPriority.click();
            System.out.println("✓ Clicked 'HIGH PRIORITY' filter");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click HIGH PRIORITY filter", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 6:
     * Click MEDIUM PRIORITY filter button
     */
    public void clickMediumPriority() {
        try {
            WebElement mediumPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(mediumPriorityButtonXpath)));
            mediumPriority.click();
            System.out.println("✓ Clicked 'MEDIUM PRIORITY' filter");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click MEDIUM PRIORITY filter", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 6:
     * Click LOW PRIORITY filter button
     */
    public void clickLowPriority() {
        try {
            WebElement lowPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(lowPriorityButtonXpath)));
            lowPriority.click();
            System.out.println("✓ Clicked 'LOW PRIORITY' filter");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click LOW PRIORITY filter", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 7:
     * Click RENAL dropdown
     */
    public void clickRenalDropdown() {
        try {
            WebElement renalDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(renalDropdownXpath)));
            renalDropdown.click();
            System.out.println("✓ Clicked 'RENAL' dropdown");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click RENAL dropdown", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 8:
     * Click HIGH PRIORITY filter and verify Creatinine High Priority is shown
     */
    public void clickHighPriorityAndVerifyCreatinine() {
        try {
            // Click HIGH PRIORITY filter
            WebElement highPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(highPriorityButtonXpath)));
            highPriority.click();
            System.out.println("✓ Clicked 'HIGH PRIORITY' filter");

            // Wait for UI to update after filter click
            Thread.sleep(1000);

            // Verify Creatinine High Priority is displayed
            WebElement creatinineHighPriority = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(creatinineHighPriorityXpath)));

            if (creatinineHighPriority.isDisplayed()) {
                System.out.println("✓ Verified 'Creatinine High Priority' is displayed");
            } else {
                throw new RuntimeException("Creatinine High Priority is not displayed");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during HIGH PRIORITY verification", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click HIGH PRIORITY or verify Creatinine High Priority", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 9:
     * Click MEDIUM PRIORITY filter and verify Bun Medium Priority is shown
     */
    public void clickMediumPriorityAndVerifyBun() {
        try {
            // Click MEDIUM PRIORITY filter
            WebElement mediumPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(mediumPriorityButtonXpath)));
            mediumPriority.click();
            System.out.println("✓ Clicked 'MEDIUM PRIORITY' filter");

            // Wait for UI to update after filter click
            Thread.sleep(1000);

            // Verify Bun Medium Priority is displayed
            WebElement bunMediumPriority = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(bunMediumPriorityXpath)));

            if (bunMediumPriority.isDisplayed()) {
                System.out.println("✓ Verified 'Bun Medium Priority' is displayed");
            } else {
                throw new RuntimeException("Bun Medium Priority is not displayed");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during MEDIUM PRIORITY verification", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click MEDIUM PRIORITY or verify Bun Medium Priority", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 10:
     * Click LOW PRIORITY filter and verify Bun/creatinine Low Priority is shown
     */
    public void clickLowPriorityAndVerifyBunCreatinine() {
        try {
            // Click LOW PRIORITY filter
            WebElement lowPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(lowPriorityButtonXpath)));
            lowPriority.click();
            System.out.println("✓ Clicked 'LOW PRIORITY' filter");

            // Wait for UI to update after filter click
            Thread.sleep(1000);

            // Verify Bun/creatinine Low Priority is displayed
            WebElement bunCreatinineLowPriority = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(bunCreatinineLowPriorityXpath)));

            if (bunCreatinineLowPriority.isDisplayed()) {
                System.out.println("✓ Verified 'Bun/creatinine Low Priority' is displayed");
            } else {
                throw new RuntimeException("Bun/creatinine Low Priority is not displayed");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during LOW PRIORITY verification", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click LOW PRIORITY or verify Bun/creatinine Low Priority", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 12:
     * Click priority items twice to deselect them
     */
    public void clickPriorityItemsTwice() {
        try {
            // Click Bun Medium Priority twice
            WebElement bunMediumPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(bunMediumPriorityXpath)));
            bunMediumPriority.click();
            System.out.println("✓ Clicked 'Bun Medium Priority' (1st time)");
            Thread.sleep(1000); // 1 second wait
            bunMediumPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(bunSimpleXpath)));
            bunMediumPriority.click();
            System.out.println("✓ Clicked 'Bun' (2nd time)");

            Thread.sleep(1000); // 1 second wait

            // Click Creatinine High Priority twice
            WebElement creatinineHighPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(creatinineHighPriorityXpath)));
            creatinineHighPriority.click();
            System.out.println("✓ Clicked 'Creatinine High Priority' (1st time)");
            Thread.sleep(1000); // 1 second wait
            creatinineHighPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(creatinineSimpleXpath)));
            creatinineHighPriority.click();
            System.out.println("✓ Clicked 'Creatinine' (2nd time)");

            Thread.sleep(1000); // 1 second wait

            // Click Bun/creatinine Low Priority twice
            WebElement bunCreatinineLowPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(bunCreatinineLowPriorityXpath)));
            bunCreatinineLowPriority.click();
            System.out.println("✓ Clicked 'Bun/creatinine Low Priority' (1st time)");
            Thread.sleep(1000); // 1 second wait
            bunCreatinineLowPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(bunCreatinineSimpleXpath)));
            bunCreatinineLowPriority.click();
            System.out.println("✓ Clicked 'Bun/creatinine' (2nd time)");

            Thread.sleep(1000); // 1 second wait

            // Click Egfr Medium Priority twice
            WebElement egfrMediumPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(egfrMediumPriorityXpath)));
            egfrMediumPriority.click();
            System.out.println("✓ Clicked 'Egfr Medium Priority' (1st time)");
            Thread.sleep(1000); // 1 second wait
            egfrMediumPriority = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(egfrSimpleXpath)));
            egfrMediumPriority.click();
            System.out.println("✓ Clicked 'Egfr' (2nd time)");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during priority items clicks", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click priority items twice", e);
        }
    }

    /**
     * TEST CASE 4 - NEW STEP 13:
     * Swipe up once to scroll the view
     */
    public void swipeUpOnce() {
        try {
            // Get screen dimensions
            int height = driver.manage().window().getSize().getHeight();
            int width = driver.manage().window().getSize().getWidth();

            // Calculate swipe coordinates (swipe from bottom to top, middle of screen)
            int startX = width / 2;
            int startY = (int) (height * 0.8); // Start at 80% of screen height
            int endY = (int) (height * 0.2); // End at 20% of screen height

            // Perform swipe using W3C Actions
            org.openqa.selenium.interactions.PointerInput finger = new org.openqa.selenium.interactions.PointerInput(
                    org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
            org.openqa.selenium.interactions.Sequence swipe = new org.openqa.selenium.interactions.Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(java.time.Duration.ofMillis(0),
                    org.openqa.selenium.interactions.PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(
                    finger.createPointerDown(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(java.time.Duration.ofMillis(1000),
                    org.openqa.selenium.interactions.PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(
                    finger.createPointerUp(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(java.util.Arrays.asList(swipe));
            System.out.println("✓ Swiped up once");

        } catch (Exception e) {
            throw new RuntimeException("Failed to swipe up", e);
        }
    }

    // ==================== TEST CASE 1 - CHECKOUT FLOW METHODS (STEPS 8-32)
    // ====================

    /**
     * TEST CASE 1 - STEP 8:
     * Verify BAG page is displayed
     */
    public void verifyBagPage() {
        try {
            WebElement bagPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(bagPageXpath)));

            if (bagPage.isDisplayed()) {
                System.out.println("✓ Step 8: BAG page is displayed");
            } else {
                throw new RuntimeException("BAG page is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to verify BAG page in Step 8", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 9:
     * Click Essential Epigenetics dropdown
     */
    public void clickEssentialEpigeneticsDropdown() {
        try {
            WebElement dropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(essentialEpigeneticsDropdownXpath)));
            dropdown.click();
            System.out.println("✓ Step 9: Clicked Essential Epigenetics dropdown");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click Essential Epigenetics dropdown in Step 9", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 10:
     * Click PROCEED TO CHECKOUT button
     */
    public void clickProceedToCheckoutButton() {
        try {
            WebElement proceedButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(proceedToCheckoutButtonXpath)));
            proceedButton.click();
            System.out.println("✓ Step 10: Clicked PROCEED TO CHECKOUT button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click PROCEED TO CHECKOUT button in Step 10", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 11:
     * Verify CHECKOUT page and Delivery Address are displayed
     */
    public void verifyCheckoutPage() {
        try {
            // Verify CHECKOUT page
            WebElement checkoutPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(checkoutPageXpath)));

            if (checkoutPage.isDisplayed()) {
                System.out.println("✓ Step 11: CHECKOUT page is displayed");
            } else {
                throw new RuntimeException("CHECKOUT page is not displayed");
            }

            // Verify Delivery Address
            WebElement deliveryAddress = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(deliveryAddressXpath)));

            if (deliveryAddress.isDisplayed()) {
                System.out.println("✓ Step 11: Delivery Address is displayed");
            } else {
                throw new RuntimeException("Delivery Address is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to verify CHECKOUT page in Step 11", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 12:
     * Click name field and fill with "Kathir"
     */
    public void fillNameField(String name) {
        try {
            WebElement nameField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(nameFieldXpath)));
            nameField.click();
            nameField.clear();
            nameField.sendKeys(name);
            System.out.println("✓ Step 12: Filled name field with: " + name);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill name field in Step 12", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 13 (First attempt with wrong DOB):
     * Click DOB field and set date using seekbars (swipe down once on each)
     */
    /**
     * TEST CASE 1 - STEP 13 (First attempt with wrong DOB):
     * Click DOB field and set date using robust swipe actions
     */
    public void fillDOBFieldWrong() {
        try {
            clickDateOfBirth();
            performDateSelection();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fill DOB field in Step 13", e);
        }
    }

    /**
     * Click the Date of Birth field (Text-independent locator)
     * Requirement: Always click the same element regardless of empty or filled
     * state.
     */
    /**
     * Click the Date of Birth field for STEP 13 (Wrong DOB)
     * Requirement: Uses the existing instance-based locator which is working.
     */
    public void clickDateOfBirth() {
        try {
            System.out.println("Step 13: Clicking DOB field using stable instance(15) selector...");
            hideKeyboard();
            Thread.sleep(1000);

            WebElement dobField = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(15)")));

            dobField.click();
            System.out.println("✓ Step 13: DOB field clicked (Instance 15)");

        } catch (Exception e) {
            throw new RuntimeException("CRITICAL: Failed Step 13 DOB click: " + e.getMessage(), e);
        }
    }

    /**
     * Click the Date of Birth field for STEP 22 (Correct DOB) using dynamic bounds.
     * Requirement: Step 22 fails with locators when populated.
     * This method locates the element by class+clickable+enabled, retrieves its
     * bounds,
     * calculates the center, and performs a tap. It is resolution-independent.
     */
    public void clickDobFieldDynamic() {
        int maxAttempts = 2;
        boolean success = false;
        Exception lastException = null;

        for (int i = 1; i <= maxAttempts; i++) {
            try {
                System.out.println("Step 22: Attempt " + i + " to click DOB field via dynamic bounds...");
                hideKeyboard();
                Thread.sleep(1500);

                // Locate clickable Views. DOB is typically the main clickable View in this
                // context.
                // We avoid text/index/fixed coordinates as requested.
                // Using UIAutomator to find clickable and enabled Views.
                List<WebElement> clickableViews = driver.findElements(AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.view.View\").clickable(true).enabled(true)"));

                if (clickableViews.isEmpty()) {
                    throw new RuntimeException("No clickable android.view.View found for DOB");
                }

                // In Step 22, the DOB field is a prominent clickable View.
                // If multiple exist, we target the one likely to be the input field.
                // For robustness across devices, we pick the one that has valid dimensions.
                WebElement dobField = null;
                for (WebElement v : clickableViews) {
                    String b = v.getAttribute("bounds");
                    if (b != null && b.contains("][")) {
                        dobField = v; // Found potential field
                        break;
                    }
                }

                if (dobField == null)
                    throw new RuntimeException("Could not identify DOB field from clickable views");

                // 2-3. Retrieve bounds dynamically and calculate center
                String bounds = dobField.getAttribute("bounds");
                Pattern p = Pattern.compile("\\[(\\d+),(\\d+)\\]\\[(\\d+),(\\d+)\\]");
                Matcher m = p.matcher(bounds);
                if (!m.find())
                    throw new RuntimeException("Failed to parse bounds: " + bounds);

                int x1 = Integer.parseInt(m.group(1));
                int y1 = Integer.parseInt(m.group(2));
                int x2 = Integer.parseInt(m.group(3));
                int y2 = Integer.parseInt(m.group(4));

                int centerX = (x1 + x2) / 2;
                int centerY = (y1 + y2) / 2;

                // 4. Perform tap using W3C Actions on calculated center
                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                Sequence tap = new Sequence(finger, 1);
                tap.addAction(
                        finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
                tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                driver.perform(Collections.singletonList(tap));

                System.out.println("✓ Step 22: DOB field tapped via Dynamic Bounds " + bounds + " at (" + centerX + ", "
                        + centerY + ")");
                success = true;
                break;
            } catch (Exception e) {
                lastException = e;
                System.out.println("⚠ Attempt " + i + " failed: " + e.getMessage());
            }
        }

        if (!success) {
            throw new RuntimeException(
                    "CRITICAL: DOB field could not be clicked via dynamic bounds after " + maxAttempts + " attempts",
                    lastException);
        }
    }

    /**
     * ✅ PERFORM DATE SELECTION (SWIPE ACTIONS)
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
        try {
            int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
            int startY = element.getRect().getY() + (element.getRect().getHeight() / 2);
            int endY = startY + 200; // Standard swipe

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        } catch (Exception e) {
            System.err.println("Swipe down failed: " + e.getMessage());
        }
    }

    /**
     * Helper method to swipe down on an element quickly
     */
    private void fastSwipe(WebElement element) {
        try {
            int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
            int startY = element.getRect().getY() + (element.getRect().getHeight() / 2);
            int endY = startY + 300; // Longer swipe for speed

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(
                    finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
        } catch (Exception e) {
            System.err.println("Fast swipe failed: " + e.getMessage());
        }
    }

    /**
     * Helper method to Tap on an element using W3C Actions (Force Click)
     */

    /**
     * Scroll Page Up (Finger Bottom -> Top)
     * Moves content UP, so we see content BELOW.
     */
    public void scrollPageUp() {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        performScroll(centerX, startY, endY);
    }

    /**
     * Scroll Page Down (Finger Top -> Bottom)
     * Moves content DOWN, so we see content ABOVE.
     */
    public void scrollPageDown() {
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);
        performScroll(centerX, startY, endY);
    }

    private void performScroll(int centerX, int startY, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), centerX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    /**
     * TEST CASE 1 - STEP 13 (Gender selection):
     * Click gender dropdown and select Male
     */
    public void selectGender() {
        try {
            System.out.println("Step 13: Selecting gender...");
            hideKeyboard();
            Thread.sleep(1000);

            // 1-2. Try primary Gender XPath, else fallback to Male ImageView
            WebElement genderElement = null;
            try {
                genderElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(genderDropdownXpath)));
            } catch (Exception e) {
                try {
                    // 3. Fallback click option
                    genderElement = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.ImageView[@content-desc=\"Male\"]")));
                } catch (Exception ex) {
                    // 10. Specific failure message
                    throw new RuntimeException("Gender selection failed: Gender element or Male option not found");
                }
            }

            // 5-6. Only one click, no double clicks
            genderElement.click();
            System.out.println("✓ Step 13: Gender element (or fallback) clicked");

            // 7. Proceed with existing selection flow (unchanged delays)
            Thread.sleep(500);
            WebElement maleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(maleButtonXpath)));
            maleButton.click();
            System.out.println("✓ Step 13: Gender selection completed (Male)");

        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("Gender selection failed")) {
                throw new RuntimeException(e.getMessage());
            }
            throw new RuntimeException("Failed to select gender in Step 13: " + e.getMessage(), e);
        }
    }

    /**
     * TEST CASE 1 - STEP 14:
     * Click country code, search for India, and select it
     */
    public void selectCountryCode() {
        int maxRetries = 3;
        boolean success = false;
        String errorMessage = "India (+91) country code not found after search";

        for (int i = 1; i <= maxRetries; i++) {
            try {
                System.out.println("Step 14: Country selection attempt " + i + " of " + maxRetries);

                // 1. Open picker & wait for search input visibility
                WebElement countryCodeDropdown;
                try {
                    countryCodeDropdown = wait
                            .until(ExpectedConditions.elementToBeClickable(By.xpath(countryCodeXpath)));
                } catch (Exception e) {
                    countryCodeDropdown = wait.until(ExpectedConditions
                            .elementToBeClickable(By.xpath("//android.view.View[contains(@content-desc, \"+\")]")));
                }
                countryCodeDropdown.click();

                Thread.sleep(1500);
                WebElement searchField = wait
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(countrySearchFieldXpath)));

                // 2. Clear Box and type "India"
                searchField.click();
                searchField.clear();
                searchField.sendKeys("India");
                System.out.println("  - Typed 'India' in search box");

                // 3. Wait for results list refresh
                Thread.sleep(2500);

                // 4-9. Identify Button: India +91, avoid BIOT (+246)
                // Chaining UiSelector as requested
                String uiAutomatorExpr = "new UiSelector().className(\"android.widget.Button\")" +
                        ".descriptionContains(\"India\")" +
                        ".descriptionContains(\"+91\")";

                WebElement indiaButton = null;
                try {
                    indiaButton = driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorExpr));
                } catch (Exception e) {
                    // 8. Scroll vertically if required
                    System.out.println("  - India button not visible, attempting scroll...");
                    scrollPageUp(); // Re-using existing scroll
                    Thread.sleep(1000);
                    indiaButton = driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorExpr));
                }

                if (indiaButton != null) {
                    String desc = indiaButton.getAttribute("content-desc");
                    // 6-7. Ensure no match for +246 or BIOT
                    if (desc != null && (desc.contains("+246") || desc.contains("British Indian Ocean Territory"))) {
                        System.out.println("  ⚠ Unexpectedly found BIOT/+246, skipping...");
                        continue;
                    }

                    indiaButton.click();
                    System.out.println("  ✓ Clicked India (+91) Button");
                }

                // 10. Wait until picker closes
                Thread.sleep(2000);

                // 11. Validate selected country code is displayed as +91
                WebElement activeCountry = wait.until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//android.view.View[contains(@content-desc, '+91')]")));
                if (activeCountry.isDisplayed()) {
                    System.out.println("✓ Step 14: Country code validation passed (+91)");
                    success = true;
                    break;
                }

            } catch (Exception e) {
                System.out.println("  ⚠ Attempt " + i + " failed: " + e.getMessage());
                hideKeyboard();
                try {
                    driver.navigate().back();
                } catch (Exception ex) {
                } // Try to close picker if stuck
            }
        }

        if (!success) {
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * TEST CASE 1 - STEP 15:
     * Click phone number field, fill it, and hide keyboard
     */
    public void fillPhoneNumber(String phoneNumber) {
        try {
            WebElement phoneField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(phoneNumberFieldXpath)));
            phoneField.click();
            phoneField.clear();
            phoneField.sendKeys(phoneNumber);
            System.out.println("✓ Step 15: Filled phone number: " + phoneNumber);

            // Hide keyboard
            hideKeyboard();

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill phone number in Step 15", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 16:
     * Click address field, fill it, and hide keyboard
     */
    public void fillAddress(String address) {
        try {
            WebElement addressField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(addressFieldXpath)));
            addressField.click();
            addressField.clear();
            addressField.sendKeys(address);
            System.out.println("✓ Step 16: Filled address: " + address);

            // Hide keyboard
            hideKeyboard();

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill address in Step 16", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 17:
     * Click city field, fill it, and hide keyboard
     */
    public void fillCity(String city) {
        try {
            WebElement cityField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(cityFieldXpath)));
            cityField.click();
            cityField.clear();
            cityField.sendKeys(city);
            System.out.println("✓ Step 17: Filled city: " + city);

            // Hide keyboard
            hideKeyboard();

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill city in Step 17", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 18:
     * Click country dropdown and select India
     */
    public void selectCountry() {
        try {
            // Click country dropdown
            WebElement countryDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(countryDropdownXpath)));
            countryDropdown.click();
            System.out.println("✓ Step 18: Clicked country dropdown");

            Thread.sleep(500);

            // Click India button
            WebElement indiaButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(indiaButtonXpath)));
            indiaButton.click();
            System.out.println("✓ Step 18: Selected India");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during country selection in Step 18", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to select country in Step 18", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 19:
     * Click postal code field, fill with wrong code, and hide keyboard
     */
    public void fillPostalCodeWrong(String postalCode) {
        try {
            WebElement postalField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(postalCodeFieldXpath)));
            postalField.click();
            postalField.clear();
            postalField.sendKeys(postalCode);
            System.out.println("✓ Step 19: Filled postal code (wrong): " + postalCode);

            // Hide keyboard
            hideKeyboard();

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill postal code in Step 19", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 20:
     * Click SAVE ADDRESS button
     */
    public void clickSaveAddressButton() {
        try {
            WebElement saveButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(saveAddressButtonXpath)));
            saveButton.click();
            System.out.println("✓ Step 20: Clicked SAVE ADDRESS button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click SAVE ADDRESS button in Step 20", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 21:
     * Verify error dialog "FIX THE FOLLOWING ERRORS" is displayed
     */
    public void verifyErrorDialog() {
        try {
            WebElement errorDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(errorDialogXpath)));

            if (errorDialog.isDisplayed()) {
                System.out.println("✓ Step 21: Error dialog 'FIX THE FOLLOWING ERRORS' is displayed");
            } else {
                throw new RuntimeException("Error dialog is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to verify error dialog in Step 21", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 22 (Corrected DOB):
     * Click DOB field and set correct date (swipe to year 2000)
     */

    public void fillDOBFieldCorrect() {
        try {
            // ✅ STEP 22: Click DOB field using DYNAMIC BOUNDS (Device Independent)
            clickDobFieldDynamic();
            System.out.println("✓ Step 22: Clicked DOB field via Dynamic Bounds");

            Thread.sleep(1500); // Wait for date picker

            // Use index-based locators for stability
            String daySeekBarXpath = "(//android.widget.SeekBar)[1]";
            String monthSeekBarXpath = "(//android.widget.SeekBar)[2]";
            String yearSeekBarXpath = "(//android.widget.SeekBar)[3]";

            // Swipe once on day and month
            WebElement daySeekBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(daySeekBarXpath)));
            swipeDown(daySeekBar);
            Thread.sleep(500);

            WebElement monthSeekBar = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(monthSeekBarXpath)));
            swipeDown(monthSeekBar);
            Thread.sleep(500);

            // ✅ STEP 22: Robust Dynamic Year selection (< 2005)
            boolean found = false;
            int maxAttempts = 40;
            int attempts = 0;

            System.out.println("Step 22: Searching for any year < 2005...");
            while (!found && attempts < maxAttempts) {
                // Read all visible seekbar/numeric elements
                List<WebElement> scrollElements = driver.findElements(By.className("android.widget.SeekBar"));

                for (WebElement el : scrollElements) {
                    String desc = el.getAttribute("content-desc");
                    if (desc != null && desc.matches("\\d{4}")) { // Check if it's a 4-digit year
                        try {
                            int yearValue = Integer.parseInt(desc);
                            if (yearValue < 2005) {
                                found = true;
                                el.click();
                                System.out.println("✓ Step 22: Found and selected year " + yearValue);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            // Not a year
                        }
                    }
                }

                if (found)
                    break;

                // If not found, fast swipe only on the Year SeekBar (index 3)
                WebElement yearSeekBar = wait
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(yearSeekBarXpath)));
                fastSwipe(yearSeekBar);
                attempts++;
                Thread.sleep(200);
            }

            if (!found) {
                throw new RuntimeException("CRITICAL: No year below 2005 found in DOB picker");
            }

            Thread.sleep(500);

            // Click CONFIRM button
            WebElement confirmBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(confirmButtonXpath)));
            confirmBtn.click();
            System.out.println("✓ Step 22: Clicked CONFIRM button");

        } catch (Exception e) {
            throw new RuntimeException("CRITICAL: Failed to fill correct DOB in Step 22: " + e.getMessage(), e);
        }
    }

    /**
     * TEST CASE 1 - STEP 23:
     * Click phone number field, clear it, fill with correct number, and hide
     * keyboard
     */
    public void fillPhoneNumberCorrect(String phoneNumber) {
        try {
            WebElement phoneField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(phoneNumberFieldXpath)));
            phoneField.click();
            phoneField.clear();
            phoneField.sendKeys(phoneNumber);
            System.out.println("✓ Step 23: Filled correct phone number: " + phoneNumber);

            // Hide keyboard
            hideKeyboard();

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill correct phone number in Step 23", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 24:
     * Click postal code field, clear it, fill with correct code, and hide keyboard
     */
    public void fillPostalCodeCorrect(String postalCode) {
        try {
            WebElement postalField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(postalCodeFieldXpath)));
            postalField.click();
            postalField.clear();
            postalField.sendKeys(postalCode);
            System.out.println("✓ Step 24: Filled correct postal code: " + postalCode);

            // Hide keyboard
            hideKeyboard();

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill correct postal code in Step 24", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 26:
     * Verify Shipping Method is displayed
     */
    public void verifyShippingMethod() {
        try {
            WebElement shippingMethod = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(shippingMethodXpath)));

            if (shippingMethod.isDisplayed()) {
                System.out.println("✓ Step 26: Shipping Method is displayed");
            } else {
                throw new RuntimeException("Shipping Method is not displayed");
            }
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to verify Shipping Method in Step 26", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 27:
     * Click PROCEED TO PAYMENT button and wait 9 seconds
     */
    public void clickProceedToPaymentButton() {
        try {
            WebElement proceedButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(proceedToPaymentButtonXpath)));
            proceedButton.click();
            System.out.println("✓ Step 27: Clicked PROCEED TO PAYMENT button");

            // Wait 9 seconds
            Thread.sleep(9000);
            System.out.println("✓ Step 27: Waited 9 seconds");

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during payment button click in Step 27", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click PROCEED TO PAYMENT button in Step 27", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 29:
     * Click Close sheet
     */
    public void clickCloseSheet() {
        try {
            WebElement closeSheet = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(closeSheetXpath)));
            closeSheet.click();
            System.out.println("✓ Step 29: Clicked Close sheet");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click Close sheet in Step 29", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 30:
     * Verify payment error dialog and get error message
     * 
     * @return The payment error message
     */
    public String verifyPaymentErrorAndGetMessage() {
        try {
            // Verify PAYMENT ERROR dialog
            WebElement paymentErrorDialog = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(paymentErrorDialogXpath)));

            if (paymentErrorDialog.isDisplayed()) {
                System.out.println("✓ Step 30: PAYMENT ERROR dialog is displayed");
            } else {
                throw new RuntimeException("PAYMENT ERROR dialog is not displayed");
            }

            // Get error message
            WebElement errorMessage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(paymentErrorMessageXpath)));
            String message = errorMessage.getAttribute("content-desc");
            System.out.println("✓ Step 30: Payment error message captured: " + message);

            return message;

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to verify payment error or get message in Step 30", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 31:
     * Click OK button
     */
    public void clickOKButton() {
        try {
            WebElement okButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(okButtonXpath)));
            okButton.click();
            System.out.println("✓ Step 31: Clicked OK button");
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click OK button in Step 31", e);
        }
    }

    /**
     * TEST CASE 1 - STEP 32:
     * Click Retry Payment button, wait 9 seconds, and verify payment page
     */
    public void clickRetryPaymentAndVerify() {
        try {
            // Click Retry Payment button
            WebElement retryButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(retryPaymentButtonXpath)));
            retryButton.click();
            System.out.println("✓ Step 32: Clicked Retry Payment button");

            // Wait 9 seconds
            Thread.sleep(9000);
            System.out.println("✓ Step 32: Waited 9 seconds");

            // Verify payment page is displayed
            WebElement paymentPageButton = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(paymentPageButtonXpath)));

            if (paymentPageButton.isDisplayed()) {
                System.out.println("✓ Step 32: Payment page is displayed");
            } else {
                throw new RuntimeException("Payment page is not displayed");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted during retry payment in Step 32", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to click Retry Payment or verify payment page in Step 32", e);
        }
    }

    // ==================== HELPER METHODS ====================

    /**
     * Helper method to hide keyboard
     */
    private void hideKeyboard() {
        try {
            ((io.appium.java_client.android.AndroidDriver) driver).hideKeyboard();
            System.out.println("  ✓ Keyboard hidden");
        } catch (Exception e) {
            System.out.println("  ⚠ Keyboard already hidden or not present");
        }
    }

    /**
     * Helper method to swipe down on a seekbar element
     */

    /**
     * Verify if Data Bank page is currently displayed
     * 
     * @return true if Data Bank heading is visible, false otherwise
     */
    public boolean isDataBankPageDisplayed() {
        try {
            WebElement dataBankHeading = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(dataBankHeadingXpath)));
            return dataBankHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if already on Data Bank page by looking for REPORTS tab
     * This is more reliable than checking for DATA BANK heading when already inside
     * the section
     * 
     * @return true if REPORTS tab is visible (indicating we're on Data Bank page),
     *         false otherwise
     */
    public boolean isAlreadyOnDataBankPage() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement reportsTab = shortWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(reportsXpath)));
            return reportsTab.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the Data Bank heading text for verification
     * 
     * @return Content description of the Data Bank heading
     */
    public String getDataBankHeadingText() {
        try {
            WebElement dataBankHeading = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(dataBankHeadingXpath)));
            return dataBankHeading.getAttribute("content-desc");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get validation message from success/error dialog
     * Replaces newlines with spaces for easier assertion
     */
    public String getValidationMessage() {
        try {
            // Robust locator using contains(@content-desc)
            // Using a strictly general locator as requested by user to catch ANY validation
            // message causing the dialog
            WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//android.view.View[contains(@content-desc, 'Invalid') or contains(@content-desc, 'at least')]")));

            String rawMessage = messageElement.getAttribute("content-desc");
            if (rawMessage == null)
                return null;

            // "Replace \n with space OR validate each message separately"
            // Replacing \n with space as per user instruction
            return rawMessage.replace("\n", " ").trim();

        } catch (Exception e) {
            System.out.println("Validation message not found: " + e.getMessage());
            return null;
        }
    }
}