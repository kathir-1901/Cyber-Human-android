package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.DataBankPage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataBankTest extends BaseTest {

    /**
     * ==================== DATA BANK TEST CASE 1 ====================
     * 
     * Common Steps (1, 2 & 3):
     * 1. Verify DAILY PRIORITY heading on home page
     * 2. Click Wellbeing Dashboard
     * 3. Click DATA BANK and verify it's displayed
     * 
     * Test-Specific Steps:
     * 4. Click PACKAGES & PRICING
     * 5. Click CONTINUE button
     * 6. Click checkbox
     * 7. Click CONTINUE button
     * 8. Verify BAG page
     * 9. Click Essential Epigenetics dropdown
     * 10. Click PROCEED TO CHECKOUT button
     * 11. Verify CHECKOUT page and Delivery Address
     * 12. Fill name field with "Kathir"
     * 13. Fill DOB field (wrong), select Gender (Male)
     * 14. Select country code (India +91)
     * 15. Fill phone number (wrong - 1234568)
     * 16. Fill address (5/1029,KTG)
     * 17. Fill city (Coimbatore)
     * 18. Select country (India)
     * 19. Fill postal code (wrong - kahsw)
     * 20. Click SAVE ADDRESS button
     * 21. Verify error dialog "FIX THE FOLLOWING ERRORS"
     * 22. Get error message, click OK, fill DOB correctly (year 2000)
     * 23. Fill phone number correctly (8072971990)
     * 24. Fill postal code correctly (643217)
     * 25. Click SAVE ADDRESS button
     * 26. Verify Shipping Method is displayed
     * 27. Click PROCEED TO PAYMENT button and wait 9 seconds
     * 29. Click Close sheet
     * 30. Verify payment error dialog and get error message
     * 31. Click OK button
     * 32. Click Retry Payment button, wait 9 seconds, verify payment page
     */
    @Test(priority = 1)
    public void testDataBank_Case1() throws InterruptedException {
        test = extent.createTest("Data Bank Test Case 1");
        test.log(Status.INFO, "Starting Data Bank Test Case 1");

        HomePage homePage = new HomePage(driver);
        DataBankPage dataBankPage = new DataBankPage(driver);

        // ✅ COMMON STEP 1: Verify DAILY PRIORITY heading is displayed on home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        if (!isHomePageDisplayed) {
            test.log(Status.FAIL, "DAILY PRIORITY heading not found on home page");
            Assert.fail("Home page validation failed - DAILY PRIORITY heading not displayed");
        }
        test.log(Status.PASS, "✓ Step 1: DAILY PRIORITY heading is displayed on home page");

        // ✅ COMMON STEP 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Step 2: Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }

        // ✅ COMMON STEP 3: Click DATA BANK and verify
        test.log(Status.INFO, "Step 3: Clicking DATA BANK");
        dataBankPage.clickAndVerifyDataBank();
        test.log(Status.PASS, "✓ Step 3: DATA BANK clicked and verified");
        Thread.sleep(3000);
        // Verify Data Bank page is displayed
        Assert.assertTrue(dataBankPage.isDataBankPageDisplayed(),
                "Data Bank page should be displayed");
        test.log(Status.PASS, "✓ Verified: Data Bank page is displayed");
        Thread.sleep(3000); // Wait 3 seconds for page to load

        // ✅ TEST CASE 1 - STEP 4: Click PACKAGES & PRICING
        test.log(Status.INFO, "Step 4: Clicking PACKAGES & PRICING");
        dataBankPage.clickPackagesAndPricing();
        test.log(Status.PASS, "✓ Step 4: PACKAGES & PRICING clicked");
        Thread.sleep(9000); // Wait 3 seconds for page to load

        // ✅ TEST CASE 1 - STEP 5: Click CONTINUE button
        test.log(Status.INFO, "Step 5: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 5: CONTINUE button clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 6: Click checkbox
        test.log(Status.INFO, "Step 6: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 6: Checkbox clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 7: Click CONTINUE button again
        test.log(Status.INFO, "Step 7: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        System.out.println("✓ Step 7: CONTINUE button clicked (Test)");
        test.log(Status.PASS, "✓ Step 7: CONTINUE button clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 8: Verify BAG page
        System.out.println("Step 8: Starting Verify BAG page...");
        test.log(Status.INFO, "Step 8: Verifying BAG page");
        dataBankPage.verifyBagPage();
        System.out.println("✓ Step 8: BAG page verified (Test)");
        test.log(Status.PASS, "✓ Step 8: BAG page verified");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 9: Click Essential Epigenetics dropdown
        test.log(Status.INFO, "Step 9: Clicking Essential Epigenetics dropdown");
        dataBankPage.clickEssentialEpigeneticsDropdown();
        test.log(Status.PASS, "✓ Step 9: Essential Epigenetics dropdown clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 10: Click PROCEED TO CHECKOUT button
        test.log(Status.INFO, "Step 10: Clicking PROCEED TO CHECKOUT button");
        dataBankPage.clickProceedToCheckoutButton();
        test.log(Status.PASS, "✓ Step 10: PROCEED TO CHECKOUT button clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 11: Verify CHECKOUT page and Delivery Address
        test.log(Status.INFO, "Step 11: Verifying CHECKOUT page and Delivery Address");
        dataBankPage.verifyCheckoutPage();
        test.log(Status.PASS, "✓ Step 11: CHECKOUT page and Delivery Address verified");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 12: Fill name field with "Kathir"
        test.log(Status.INFO, "Step 12: Filling name field with 'Kathir'");
        dataBankPage.fillNameField("Kathir");
        test.log(Status.PASS, "✓ Step 12: Name field filled with 'Kathir'");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 13: Fill DOB field (wrong - will cause error)
        test.log(Status.INFO, "Step 13: Filling DOB field (wrong date)");
        dataBankPage.fillDOBFieldWrong();
        test.log(Status.PASS, "✓ Step 13: DOB field filled (wrong date)");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 13: Select Gender (Male)
        test.log(Status.INFO, "Step 13: Selecting Gender (Male)");
        dataBankPage.selectGender();
        test.log(Status.PASS, "✓ Step 13: Gender selected (Male)");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 14: Select country code (India +91)
        test.log(Status.INFO, "Step 14: Selecting country code (India +91)");
        dataBankPage.selectCountryCode();
        test.log(Status.PASS, "✓ Step 14: Country code selected (India +91)");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 15: Fill phone number (wrong - will cause error)
        test.log(Status.INFO, "Step 15: Filling phone number (wrong)");
        dataBankPage.fillPhoneNumber("1234568");
        test.log(Status.PASS, "✓ Step 15: Phone number filled (wrong)");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 16: Fill address
        test.log(Status.INFO, "Step 16: Filling address");
        dataBankPage.fillAddress("5/1029,KTG");
        test.log(Status.PASS, "✓ Step 16: Address filled");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 17: Fill city
        test.log(Status.INFO, "Step 17: Filling city");
        dataBankPage.fillCity("Coimbatore");
        test.log(Status.PASS, "✓ Step 17: City filled");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 18: Select country (India)
        test.log(Status.INFO, "Step 18: Selecting country (India)");
        dataBankPage.selectCountry();
        test.log(Status.PASS, "✓ Step 18: Country selected (India)");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 19: Fill postal code (wrong - will cause error)
        test.log(Status.INFO, "Step 19: Filling postal code (wrong)");
        dataBankPage.fillPostalCodeWrong("kahsw");
        test.log(Status.PASS, "✓ Step 19: Postal code filled (wrong)");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 20: Click SAVE ADDRESS button
        test.log(Status.INFO, "Step 20: Clicking SAVE ADDRESS button");
        dataBankPage.clickSaveAddressButton();
        test.log(Status.PASS, "✓ Step 20: SAVE ADDRESS button clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 21: Verify error dialog
        test.log(Status.INFO, "Step 21: Verifying error dialog 'FIX THE FOLLOWING ERRORS'");
        dataBankPage.verifyErrorDialog();
        test.log(Status.PASS, "✓ Step 21: Error dialog verified");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 22: Get validation message and click OK
        test.log(Status.INFO, "Step 22: Getting validation message from success dialog");
        String errorMessage = dataBankPage.getValidationMessage();

        if (errorMessage != null) {
            test.log(Status.INFO, "📋 Step 22 - Validation Message: " + errorMessage);
            // "DO NOT use direct assertEquals() with multiline text"
            Assert.assertTrue(errorMessage.contains("Invalid phone number"),
                    "Message should contain 'Invalid phone number'");
            Assert.assertTrue(errorMessage.contains("Invalid Postal Code"),
                    "Message should contain 'Invalid Postal Code'");
        } else {
            test.log(Status.FAIL, "Validation message not found");
        }

        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 22: Validation message captured and OK clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 22: Fill DOB field (correct - year 2000)
        test.log(Status.INFO, "Step 22: Filling DOB field (correct - year 2000)");
        dataBankPage.fillDOBFieldCorrect();
        test.log(Status.PASS, "✓ Step 22: DOB field filled correctly (year 2000)");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 23: Fill phone number (correct)
        test.log(Status.INFO, "Step 23: Filling phone number (correct)");
        dataBankPage.fillPhoneNumberCorrect("8072971990");
        test.log(Status.PASS, "✓ Step 23: Phone number filled correctly");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 24: Fill postal code (correct)
        test.log(Status.INFO, "Step 24: Filling postal code (correct)");
        dataBankPage.fillPostalCodeCorrect("643217");
        test.log(Status.PASS, "✓ Step 24: Postal code filled correctly");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 25: Click SAVE ADDRESS button again
        test.log(Status.INFO, "Step 25: Clicking SAVE ADDRESS button");
        dataBankPage.clickSaveAddressButton();
        test.log(Status.PASS, "✓ Step 25: SAVE ADDRESS button clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 26: Verify Shipping Method is displayed
        test.log(Status.INFO, "Step 26: Verifying Shipping Method is displayed");
        dataBankPage.verifyShippingMethod();
        test.log(Status.PASS, "✓ Step 26: Shipping Method verified");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 27: Click PROCEED TO PAYMENT button and wait 9 seconds
        test.log(Status.INFO, "Step 27: Clicking PROCEED TO PAYMENT button and waiting 9 seconds");
        dataBankPage.clickProceedToPaymentButton();
        test.log(Status.PASS, "✓ Step 27: PROCEED TO PAYMENT button clicked and waited 9 seconds");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 29: Click Close sheet
        test.log(Status.INFO, "Step 29: Clicking Close sheet");
        dataBankPage.clickCloseSheet();
        test.log(Status.PASS, "✓ Step 29: Close sheet clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 30: Verify payment error dialog and get error message
        test.log(Status.INFO, "Step 30: Verifying payment error dialog");
        String paymentErrorMessage = dataBankPage.verifyPaymentErrorAndGetMessage();
        test.log(Status.INFO, "📋 Step 30 - Payment Error Message: " + paymentErrorMessage);
        test.log(Status.PASS, "✓ Step 30: Payment error dialog verified and message captured");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 31: Click OK button
        test.log(Status.INFO, "Step 31: Clicking OK button");
        dataBankPage.clickOKButton();
        test.log(Status.PASS, "✓ Step 31: OK button clicked");
        Thread.sleep(3000);

        // ✅ TEST CASE 1 - STEP 32: Click Retry Payment button, wait 9 seconds, and
        // verify payment page
        test.log(Status.INFO, "Step 32: Clicking Retry Payment button and verifying payment page");
        dataBankPage.clickRetryPaymentAndVerify();
        test.log(Status.PASS, "✓ Step 32: Retry Payment clicked, waited 9 seconds, and payment page verified");
        Thread.sleep(3000);

        test.log(Status.PASS, "Data Bank Test Case 1 completed successfully");
    }

    /**
     * ==================== DATA BANK TEST CASE 2 ====================
     * 
     * Common Steps (1, 2 & 3):
     * 1. Verify DAILY PRIORITY heading on home page
     * 2. Click Wellbeing Dashboard
     * 3. Click DATA BANK and verify it's displayed
     * 
     * Test-Specific Steps:
     * 4. Click UPLOAD DATA
     * 5. Click UPLOAD REPORT button
     * 6. Click checkbox
     * 7. Click CONTINUE button
     * 8. Upload wrong format file (JPG)
     * 9. Validate UPLOAD FAILED dialog and capture error message
     * 10. Click OK button
     * 11. Repeat steps 5, 6, 7 (Upload Report, Checkbox, Continue)
     * 12. Upload correct format file (PDF)
     * 13. Validate UPLOAD SUCCESSFUL dialog
     * 14. Capture success message
     * 15. Click OK button
     * 16. Click remove button and verify ARE YOU SURE? dialog
     * 17. Click YES button and verify DELETE SUCCESSFUL dialog
     * 18. Capture delete success message
     */
    @Test(priority = 2)
    public void testDataBank_Case2() throws InterruptedException {
        test = extent.createTest("Data Bank Test Case 2");
        test.log(Status.INFO, "Starting Data Bank Test Case 2");

        HomePage homePage = new HomePage(driver);
        DataBankPage dataBankPage = new DataBankPage(driver);

        // ✅ COMMON STEP 1: Verify DAILY PRIORITY heading is displayed on home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        if (!isHomePageDisplayed) {
            test.log(Status.FAIL, "DAILY PRIORITY heading not found on home page");
            Assert.fail("Home page validation failed - DAILY PRIORITY heading not displayed");
        }
        test.log(Status.PASS, "✓ Step 1: DAILY PRIORITY heading is displayed on home page");

        // ✅ COMMON STEP 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Step 2: Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }

        // ✅ COMMON STEP 3: Click DATA BANK and verify
        test.log(Status.INFO, "Step 3: Clicking DATA BANK");
        dataBankPage.clickAndVerifyDataBank();
        test.log(Status.PASS, "✓ Step 3: DATA BANK clicked and verified");

        // Verify Data Bank page is displayed
        Assert.assertTrue(dataBankPage.isDataBankPageDisplayed(),
                "Data Bank page should be displayed");
        test.log(Status.PASS, "✓ Verified: Data Bank page is displayed");

        // ✅ TEST CASE 2 - STEP 4: Click UPLOAD DATA
        test.log(Status.INFO, "Step 4: Clicking UPLOAD DATA");
        dataBankPage.clickUploadData();
        test.log(Status.PASS, "✓ Step 4: UPLOAD DATA clicked");

        // ✅ TEST CASE 2 - STEP 5: Click UPLOAD REPORT button
        test.log(Status.INFO, "Step 5: Clicking UPLOAD REPORT button");
        dataBankPage.clickUploadReportButton();
        test.log(Status.PASS, "✓ Step 5: UPLOAD REPORT button clicked");

        // ✅ TEST CASE 2 - STEP 6: Click checkbox
        test.log(Status.INFO, "Step 6: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 6: Checkbox clicked");

        // ✅ TEST CASE 2 - STEP 7: Click CONTINUE button
        test.log(Status.INFO, "Step 7: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 7: CONTINUE button clicked");

        // ✅ TEST CASE 2 - STEP 8: Upload wrong format file (JPG instead of PDF)
        test.log(Status.INFO, "Step 8: Uploading wrong format file (JPG)");
        dataBankPage.uploadWrongFormatFile();
        test.log(Status.INFO, "✓ Step 8: Wrong format file upload attempted");

        // ✅ TEST CASE 2 - STEP 9: Validate UPLOAD FAILED dialog and capture error
        // message
        test.log(Status.INFO, "Step 9: Validating UPLOAD FAILED dialog");
        String errorMessage = dataBankPage.validateUploadFailedDialog();
        test.log(Status.PASS, "✓ Step 9: UPLOAD FAILED dialog validated");
        test.log(Status.INFO, "📋 Error Message: " + errorMessage);

        // Verify the error message is correct
        Assert.assertEquals(errorMessage, "Wrong file type chosen. Only allowed type is pdf.",
                "Error message should indicate wrong file type");
        test.log(Status.PASS, "✓ Verified: Correct error message displayed");

        // ✅ TEST CASE 2 - STEP 10: Click OK button after failed upload
        test.log(Status.INFO, "Step 10: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 10: OK button clicked");

        // ✅ TEST CASE 2 - STEP 11: Repeat steps 5, 6, 7 (Upload Report, Checkbox,
        // Continue)
        test.log(Status.INFO, "Step 11: Repeating upload flow - Click UPLOAD REPORT button");
        dataBankPage.clickUploadReportButton();
        test.log(Status.PASS, "✓ Step 11a: UPLOAD REPORT button clicked");

        test.log(Status.INFO, "Step 11: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 11b: Checkbox clicked");

        test.log(Status.INFO, "Step 11: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 11c: CONTINUE button clicked");

        // ✅ TEST CASE 2 - STEP 12: Upload correct format file (PDF)
        test.log(Status.INFO, "Step 12: Uploading correct format file (PDF)");
        dataBankPage.uploadCorrectFormatFile();
        test.log(Status.INFO, "✓ Step 12: Correct format file (PDF) upload attempted");
        Thread.sleep(5000);

        // ✅ TEST CASE 2 - STEP 13 & 14: Validate UPLOAD SUCCESSFUL dialog and capture
        // success message
        test.log(Status.INFO, "Step 13: Validating UPLOAD SUCCESSFUL dialog");
        String successMessage = dataBankPage.validateUploadSuccessfulDialog();
        test.log(Status.PASS, "✓ Step 13: UPLOAD SUCCESSFUL dialog validated");
        test.log(Status.INFO, "📋 Success Message: " + successMessage);

        // Verify the success message is correct
        Assert.assertEquals(successMessage, "Your document has been successfully uploaded",
                "Success message should confirm successful upload");
        test.log(Status.PASS, "✓ Step 14: Verified correct success message displayed");

        // ✅ TEST CASE 2 - STEP 15: Click OK button after successful upload
        test.log(Status.INFO, "Step 15: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 15: OK button clicked");

        // Wait for UI to settle after closing the success dialog
        Thread.sleep(2000);

        // ✅ TEST CASE 2 - STEP 16: Click remove button and verify ARE YOU SURE? dialog
        test.log(Status.INFO, "Step 16: Clicking remove button and verifying ARE YOU SURE? dialog");
        dataBankPage.clickRemoveAndVerifyAreYouSureDialog();
        test.log(Status.PASS, "✓ Step 16: Remove button clicked and ARE YOU SURE? dialog verified");

        // ✅ TEST CASE 2 - STEP 17: Click YES button and verify DELETE SUCCESSFUL dialog
        test.log(Status.INFO, "Step 17: Clicking YES button and verifying DELETE SUCCESSFUL dialog");
        dataBankPage.clickYesAndVerifyDeleteSuccessfulDialog();
        test.log(Status.PASS, "✓ Step 17: YES button clicked and DELETE SUCCESSFUL dialog verified");

        // ✅ TEST CASE 2 - STEP 18: Capture delete success message
        test.log(Status.INFO, "Step 18: Capturing delete success message");
        String deleteSuccessMessage = dataBankPage.captureDeleteSuccessMessage();
        test.log(Status.PASS, "✓ Step 18: Delete success message captured");
        test.log(Status.INFO, "📋 Delete Success Message: " + deleteSuccessMessage);

        // Verify the delete success message is correct
        Assert.assertEquals(deleteSuccessMessage, "Your report has been successfully removed.",
                "Delete success message should confirm successful removal");
        test.log(Status.PASS, "✓ Verified: Correct delete success message displayed");

        // ✅ TEST CASE 2 - STEP 19: Click OK button after delete success
        test.log(Status.INFO, "Step 19: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 19: OK button clicked");

        test.log(Status.PASS, "Data Bank Test Case 2 completed successfully");
    }

    /**
     * ==================== DATA BANK TEST CASE 3 ====================
     * 
     * Common Steps (1, 2 & 3):
     * 1. Verify DAILY PRIORITY heading on home page
     * 2. Click Wellbeing Dashboard
     * 3. Click DATA BANK and verify it's displayed
     * 
     * Test-Specific Steps:
     * TODO: Add your specific test steps here
     */
    @Test(priority = 3)
    public void testDataBank_Case3() throws InterruptedException {
        test = extent.createTest("Data Bank Test Case 3");
        test.log(Status.INFO, "Starting Data Bank Test Case 3");

        HomePage homePage = new HomePage(driver);
        DataBankPage dataBankPage = new DataBankPage(driver);

        // ✅ COMMON STEP 1: Verify DAILY PRIORITY heading is displayed on home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        if (!isHomePageDisplayed) {
            test.log(Status.FAIL, "DAILY PRIORITY heading not found on home page");
            Assert.fail("Home page validation failed - DAILY PRIORITY heading not displayed");
        }
        test.log(Status.PASS, "✓ Step 1: DAILY PRIORITY heading is displayed on home page");

        // ✅ COMMON STEP 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Step 2: Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }

        // ✅ COMMON STEP 3: Click DATA BANK and verify
        test.log(Status.INFO, "Step 3: Clicking DATA BANK");
        dataBankPage.clickAndVerifyDataBank();
        test.log(Status.PASS, "✓ Step 3: DATA BANK clicked and verified");

        // Verify Data Bank page is displayed
        Assert.assertTrue(dataBankPage.isDataBankPageDisplayed(),
                "Data Bank page should be displayed");
        test.log(Status.PASS, "✓ Verified: Data Bank page is displayed");

        // ✅ TEST CASE 3 - STEP 4: Click DEVICES
        test.log(Status.INFO, "Step 4: Clicking DEVICES");
        dataBankPage.clickDevices();
        test.log(Status.PASS, "✓ Step 4: DEVICES clicked");
        Thread.sleep(2000); // Wait for UI to update after clicking DEVICES

        // ✅ TEST CASE 3 - STEP 5: Click LINK DEVICE button
        test.log(Status.INFO, "Step 5: Clicking LINK DEVICE button");
        dataBankPage.clickLinkDeviceButton();
        test.log(Status.PASS, "✓ Step 5: LINK DEVICE button clicked");

        // ✅ TEST CASE 3 - NEW STEP 6: Click ULTRAHUMAN button
        test.log(Status.INFO, "Step 6: Clicking ULTRAHUMAN button");
        dataBankPage.clickUltrahumanButton();
        test.log(Status.PASS, "✓ Step 6: ULTRAHUMAN button clicked");

        // ✅ TEST CASE 3 - STEP 7: Click checkbox (previously step 6)
        test.log(Status.INFO, "Step 7: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 7: Checkbox clicked");

        // ✅ TEST CASE 3 - STEP 8: Click CONTINUE button (previously step 7)
        test.log(Status.INFO, "Step 8: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 8: CONTINUE button clicked");

        // ✅ TEST CASE 3 - STEP 9: Verify CONNECT WITH ULTRAHUMAN dialog (previously
        // step 8)
        test.log(Status.INFO, "Step 9: Verifying CONNECT WITH ULTRAHUMAN dialog");
        dataBankPage.verifyConnectWithUltrahumanDialog();
        test.log(Status.PASS, "✓ Step 9: CONNECT WITH ULTRAHUMAN dialog verified");

        // ✅ TEST CASE 3 - STEP 10: Enter invalid email (previously step 9)
        test.log(Status.INFO, "Step 10: Entering invalid email");
        dataBankPage.enterInvalidEmail("invalid.email");
        test.log(Status.PASS, "✓ Step 10: Invalid email entered");

        // ✅ TEST CASE 3 - STEP 11: Click VERIFY button (previously step 10)
        test.log(Status.INFO, "Step 11: Clicking VERIFY button");
        dataBankPage.clickVerifyButton();
        test.log(Status.PASS, "✓ Step 11: VERIFY button clicked");

        // ✅ TEST CASE 3 - STEP 12 & 13: Validate INVALID EMAIL ID dialog and capture
        // error message (previously step 11 & 12)
        test.log(Status.INFO, "Step 12: Validating INVALID EMAIL ID dialog");
        String errorMessage = dataBankPage.validateInvalidEmailDialog();
        test.log(Status.PASS, "✓ Step 12: INVALID EMAIL ID dialog validated");
        test.log(Status.INFO, "📋 Step 13 - Error Message: " + errorMessage);

        // Verify the error message
        Assert.assertEquals(errorMessage, "INVALID EMAIL ID",
                "Error message should indicate invalid email");
        test.log(Status.PASS, "✓ Step 13: Verified error message captured");

        // ✅ TEST CASE 3 - STEP 14: Click OK button after invalid email (previously step
        // 13)
        test.log(Status.INFO, "Step 14: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 14: OK button clicked");

        // ✅ TEST CASE 3 - STEP 15: Click ULTRAHUMAN button
        test.log(Status.INFO, "Step 15: Clicking ULTRAHUMAN button");
        dataBankPage.clickUltrahumanButton();
        test.log(Status.PASS, "✓ Step 15: ULTRAHUMAN button clicked");

        // ✅ TEST CASE 3 - STEP 16: Click checkbox
        test.log(Status.INFO, "Step 16: Clicking checkbox");
        dataBankPage.clickCheckbox();
        test.log(Status.PASS, "✓ Step 16: Checkbox clicked");

        // ✅ TEST CASE 3 - STEP 17: Click CONTINUE button
        test.log(Status.INFO, "Step 17: Clicking CONTINUE button");
        dataBankPage.clickContinueButton();
        test.log(Status.PASS, "✓ Step 17: CONTINUE button clicked");

        // ✅ TEST CASE 3 - STEP 18: Enter valid email
        test.log(Status.INFO, "Step 18: Entering valid email");
        dataBankPage.enterValidEmail("valid@example.com");
        test.log(Status.PASS, "✓ Step 18: Valid email entered");

        // ✅ TEST CASE 3 - STEP 19: Click VERIFY button
        test.log(Status.INFO, "Step 19: Clicking VERIFY button");
        dataBankPage.clickVerifyButton();
        test.log(Status.PASS, "✓ Step 19: VERIFY button clicked");

        // ✅ TEST CASE 3 - STEP 20 & 21: Validate DEVICE LINKED dialog and capture
        // success message
        test.log(Status.INFO, "Step 20: Validating DEVICE LINKED dialog");
        String successMessage = dataBankPage.validateDeviceLinkedDialog();
        test.log(Status.PASS, "✓ Step 20: DEVICE LINKED dialog validated");
        test.log(Status.INFO, "📋 Step 21 - Success Message: " + successMessage);

        // Verify the success message
        Assert.assertEquals(successMessage, "Your device has been successfully linked.",
                "Success message should confirm successful device linking");
        test.log(Status.PASS, "✓ Step 21: Verified success message captured");

        // ✅ TEST CASE 3 - STEP 22: Click OK button after successful device linking
        test.log(Status.INFO, "Step 22: Clicking OK button");
        dataBankPage.clickOkButton();
        test.log(Status.PASS, "✓ Step 22: OK button clicked");

        // ✅ TEST CASE 3 - STEP 23: Close the application to start fresh for the next
        // test
        test.log(Status.INFO, "Step 23: Closing application for fresh start");
        ((io.appium.java_client.android.AndroidDriver) driver).terminateApp("com.houseofepigenetics.abchopra");
        test.log(Status.PASS, "✓ Step 23: Application closed successfully");

        test.log(Status.PASS, "Data Bank Test Case 3 completed successfully");
    }

    /**
     * ==================== DATA BANK TEST CASE 4 ====================
     * 
     * Common Steps (1, 2 & 3):
     * 1. Verify DAILY PRIORITY heading on home page
     * 2. Click Wellbeing Dashboard
     * 3. Click DATA BANK and verify it's displayed
     * 
     * Test-Specific Steps:
     * TODO: Add your specific test steps here
     */
    @Test(priority = 4)
    public void testDataBank_Case4() throws InterruptedException {
        test = extent.createTest("Data Bank Test Case 4");
        test.log(Status.INFO, "Starting Data Bank Test Case 4");

        HomePage homePage = new HomePage(driver);
        DataBankPage dataBankPage = new DataBankPage(driver);

        // ✅ COMMON STEP 1: Verify DAILY PRIORITY heading is displayed on home page
        test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        if (!isHomePageDisplayed) {
            test.log(Status.INFO,
                    "DAILY PRIORITY heading not found - app is already on Data Bank page from previous test, continuing...");
        } else {
            test.log(Status.PASS, "✓ Step 1: DAILY PRIORITY heading is displayed on home page");
        }

        // ✅ COMMON STEP 2: Click Wellbeing Dashboard (if not already there)
        test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
        try {
            homePage.clickWellbeingDashboard();
            test.log(Status.PASS, "✓ Step 2: Wellbeing Dashboard clicked");
        } catch (Exception e) {
            test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
        }

        // ✅ COMMON STEP 3: Click DATA BANK and verify (or verify if already there)
        test.log(Status.INFO, "Step 3: Checking if DATA BANK page is displayed");

        // Check if we're already on the Data Bank page (by looking for REPORTS tab)
        if (dataBankPage.isAlreadyOnDataBankPage()) {
            test.log(Status.INFO, "Already on DATA BANK page from previous test, skipping click");
            test.log(Status.PASS, "✓ Step 3: DATA BANK page is already displayed");
        } else {
            // Not on Data Bank page, need to click it
            test.log(Status.INFO, "Step 3: Clicking DATA BANK");
            dataBankPage.clickAndVerifyDataBank();
            test.log(Status.PASS, "✓ Step 3: DATA BANK clicked and verified");
        }

        // Verify Data Bank page is displayed
        Assert.assertTrue(dataBankPage.isDataBankPageDisplayed(),
                "Data Bank page should be displayed");
        test.log(Status.PASS, "✓ Verified: Data Bank page is displayed");

        // ✅ TEST CASE 4 - STEP 4: Click REPORTS
        test.log(Status.INFO, "Step 4: Clicking REPORTS");
        dataBankPage.clickReports();
        test.log(Status.PASS, "✓ Step 4: REPORTS clicked");

        // ✅ TEST CASE 4 - STEP 5: Click BLOOD REPORT and verify page
        test.log(Status.INFO, "Step 5: Clicking BLOOD REPORT and verifying page");
        dataBankPage.clickAndVerifyBloodReport();
        test.log(Status.PASS, "✓ Step 5: BLOOD REPORT clicked and page verified");

        // ✅ TEST CASE 4 - NEW STEP 6: Click priority filters (HIGH, MEDIUM, LOW)
        test.log(Status.INFO, "Step 6: Clicking HIGH PRIORITY filter");
        dataBankPage.clickHighPriority();
        test.log(Status.PASS, "✓ Step 6: HIGH PRIORITY filter clicked");

        test.log(Status.INFO, "Step 6: Clicking MEDIUM PRIORITY filter");
        dataBankPage.clickMediumPriority();
        test.log(Status.PASS, "✓ Step 6: MEDIUM PRIORITY filter clicked");

        test.log(Status.INFO, "Step 6: Clicking LOW PRIORITY filter");
        dataBankPage.clickLowPriority();
        test.log(Status.PASS, "✓ Step 6: LOW PRIORITY filter clicked");

        // ✅ TEST CASE 4 - NEW STEP 7: Click RENAL dropdown
        test.log(Status.INFO, "Step 7: Clicking RENAL dropdown");
        dataBankPage.clickRenalDropdown();
        test.log(Status.PASS, "✓ Step 7: RENAL dropdown clicked");

        // ✅ TEST CASE 4 - NEW STEP 8: Click HIGH PRIORITY and verify Creatinine High
        // Priority
        test.log(Status.INFO, "Step 8: Clicking HIGH PRIORITY and verifying Creatinine High Priority");
        dataBankPage.clickHighPriorityAndVerifyCreatinine();
        test.log(Status.PASS, "✓ Step 8: HIGH PRIORITY clicked and Creatinine High Priority verified");

        // ✅ TEST CASE 4 - NEW STEP 9: Click MEDIUM PRIORITY and verify Bun Medium
        // Priority
        test.log(Status.INFO, "Step 9: Clicking MEDIUM PRIORITY and verifying Bun Medium Priority");
        dataBankPage.clickMediumPriorityAndVerifyBun();
        test.log(Status.PASS, "✓ Step 9: MEDIUM PRIORITY clicked and Bun Medium Priority verified");

        // ✅ TEST CASE 4 - NEW STEP 10: Click LOW PRIORITY and verify Bun/creatinine Low
        // Priority
        test.log(Status.INFO, "Step 10: Clicking LOW PRIORITY and verifying Bun/creatinine Low Priority");
        dataBankPage.clickLowPriorityAndVerifyBunCreatinine();
        test.log(Status.PASS, "✓ Step 10: LOW PRIORITY clicked and Bun/creatinine Low Priority verified");

        // ✅ TEST CASE 4 - NEW STEP 12: Click priority items twice to deselect
        test.log(Status.INFO, "Step 12: Clicking priority items twice to deselect them");
        dataBankPage.clickPriorityItemsTwice();
        test.log(Status.PASS, "✓ Step 12: All priority items clicked twice");

        // ✅ TEST CASE 4 - NEW STEP 13: Swipe up once
        test.log(Status.INFO, "Step 13: Swiping up once");
        dataBankPage.swipeUpOnce();
        test.log(Status.PASS, "✓ Step 13: Swiped up once");

        // ✅ TEST CASE 4 - STEP 14: Click back button once (previously step 6)
        test.log(Status.INFO, "Step 14: Clicking back button once");
        dataBankPage.clickBackButton();
        test.log(Status.PASS, "✓ Step 14: Back button clicked once");

        // ✅ TEST CASE 4 - STEP 15: Click REPORTS again (previously step 7)
        test.log(Status.INFO, "Step 15: Clicking REPORTS");
        dataBankPage.clickReports();
        test.log(Status.PASS, "✓ Step 15: REPORTS clicked");

        // ✅ TEST CASE 4 - STEP 16: Click DNA REPORT and verify page (previously step 8)
        test.log(Status.INFO, "Step 16: Clicking DNA REPORT and verifying page");
        dataBankPage.clickAndVerifyDnaReport();
        test.log(Status.PASS, "✓ Step 16: DNA REPORT clicked and page verified");

        // ✅ TEST CASE 4 - STEP 17: Click EPIGENETIC MAPPING button and verify PACKAGES
        // & PRICING page (previously step 9)
        test.log(Status.INFO, "Step 17: Clicking EPIGENETIC MAPPING button and verifying PACKAGES & PRICING page");
        dataBankPage.clickEpigeneticMappingAndVerifyPackagesPage();
        test.log(Status.PASS, "✓ Step 17: EPIGENETIC MAPPING clicked and PACKAGES & PRICING page verified");
        Thread.sleep(9000); // Wait 9 seconds for page to load

        // ✅ TEST CASE 4 - STEP 18: Click back button twice (previously step 10)
        test.log(Status.INFO, "Step 18: Clicking back button twice");
        dataBankPage.clickBackButtonTwice();
        test.log(Status.PASS, "✓ Step 18: Back button clicked twice");

        // ✅ TEST CASE 4 - STEP 19: Click REPORTS again (previously step 11)
        test.log(Status.INFO, "Step 19: Clicking REPORTS");
        dataBankPage.clickReports();
        test.log(Status.PASS, "✓ Step 19: REPORTS clicked");

        // ✅ TEST CASE 4 - STEP 20: Click DEVICE REPORT and verify page (previously step
        // 12)
        test.log(Status.INFO, "Step 20: Clicking DEVICE REPORT and verifying page");
        dataBankPage.clickAndVerifyDeviceReport();
        test.log(Status.PASS, "✓ Step 20: DEVICE REPORT clicked and page verified");

        // ✅ TEST CASE 4 - STEP 21: Click back button twice (previously step 13)
        test.log(Status.INFO, "Step 21: Clicking back button twice");
        dataBankPage.clickBackButtonTwice();
        test.log(Status.PASS, "✓ Step 21: Back button clicked twice");

        test.log(Status.PASS, "Data Bank Test Case 4 completed successfully");
    }
}