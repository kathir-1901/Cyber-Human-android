package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.DailyPrescriptionPage;
import com.automation.pages.HomePage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DailyPrescriptionTest extends BaseTest {

    @Test
    public void testScheduleTimeAndFileCreation() {
        test = extent.createTest("Daily Prescription - Schedule Time & File Creation Test");

        HomePage homePage = new HomePage(driver);
        DailyPrescriptionPage dailyPrescriptionPage = new DailyPrescriptionPage(driver);

        try {
            // Step 1: Verify DAILY PRIORITY heading is displayed on home page
            test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading on home page");
            boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
            if (!isHomePageDisplayed) {
                test.log(Status.FAIL, "DAILY PRIORITY heading not found on home page");
                Assert.fail("Home page validation failed - DAILY PRIORITY heading not displayed");
            }
            test.log(Status.PASS, "✓ DAILY PRIORITY heading is displayed on home page");

            // Step 2: Click Wellbeing Dashboard (if not already there)
            test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
            try {
                homePage.clickWellbeingDashboard();
                test.log(Status.PASS, "✓ Wellbeing Dashboard clicked");
            } catch (Exception e) {
                test.log(Status.INFO, "Wellbeing Dashboard not found, assuming already on dashboard");
            }

            // Step 3: Click Daily Prescription
            test.log(Status.INFO, "Step 3: Clicking Daily Prescription");
            homePage.clickDailyPrescription();
            Thread.sleep(2000); // Wait for page to load
            test.log(Status.PASS, "✓ Daily Prescription clicked");

            // Step 4: Click SCHEDULE TIME button
            test.log(Status.INFO, "Step 4: Clicking SCHEDULE TIME button");
            dailyPrescriptionPage.clickScheduleTime();
            test.log(Status.PASS, "✓ SCHEDULE TIME button clicked");

            // Step 5: Swipe once in time picker
            test.log(Status.INFO, "Step 5: Swiping up one time in the time picker container");
            dailyPrescriptionPage.swipeUpOnce();
            test.log(Status.PASS, "✓ Swipe up completed");

            // Step 6: Click CONFIRM button
            test.log(Status.INFO, "Step 6: Clicking CONFIRM button");
            dailyPrescriptionPage.clickConfirm();
            test.log(Status.PASS, "✓ CONFIRM button clicked");

            // Step 7: Validate SUCCESS dialog is displayed
            test.log(Status.INFO, "Step 7: Validating SUCCESS dialog is displayed");
            boolean successDialogDisplayed = dailyPrescriptionPage.isSuccessDialogDisplayed();
            if (!successDialogDisplayed) {
                test.log(Status.FAIL, "SUCCESS dialog not displayed");
                Assert.fail("SUCCESS dialog validation failed");
            }
            test.log(Status.PASS, "✓ SUCCESS dialog is displayed");

            // Step 8: Get and validate success message
            test.log(Status.INFO, "Step 8: Extracting success message");
            String successMessage = dailyPrescriptionPage.getSuccessMessage();
            if (successMessage == null || successMessage.isEmpty()) {
                test.log(Status.FAIL, "Failed to extract success message");
                Assert.fail("Success message extraction failed");
            }
            test.log(Status.PASS, "✓ Success message: <b>" + successMessage + "</b>");

            // Step 9: Click OK button
            test.log(Status.INFO, "Step 9: Clicking OK button");
            dailyPrescriptionPage.clickOk();
            test.log(Status.PASS, "✓ OK button clicked");

            // Step 10: Swipe left and right on Nutrition & Metabolism section
            test.log(Status.INFO,
                    "Step 10: Swiping left and right on Nutrition & Metabolism section and waiting for data load");
            dailyPrescriptionPage.swipeNutritionSection();
            test.log(Status.PASS, "✓ Swipe left and right completed, data loaded");

            // Step 11: Swipe up 2 times in ScrollView
            test.log(Status.INFO, "Step 11: Swiping up 2 times in ScrollView");
            dailyPrescriptionPage.swipeUpTwiceInScrollView();
            test.log(Status.PASS, "✓ Swipe up completed (2 times)");

            // Step 12: Click whatever article is present
            test.log(Status.INFO, "Step 12: Clicking whichever article is present in the list");
            dailyPrescriptionPage.clickAnyArticle();
            test.log(Status.PASS, "✓ Article clicked");

            // Step 13: Get heading from detail page at runtime
            test.log(Status.INFO, "Step 13: Capturing article heading from detail page at runtime");
            String articleHeading = dailyPrescriptionPage.getArticleHeading();
            if (articleHeading == null || articleHeading.isEmpty() || articleHeading.contains("Unable to extract")) {
                test.log(Status.FAIL, "Failed to extract article heading");
                Assert.fail("Article heading extraction failed");
            }
            test.log(Status.PASS, "✓ Article heading: <b>" + articleHeading + "</b>");

            // Step 14: Click Add To File radio icon
            test.log(Status.INFO, "Step 14: Clicking 'Add To File' radio icon");
            dailyPrescriptionPage.clickAddToFileRadioIcon();
            test.log(Status.PASS, "✓ 'Add To File' icon clicked");

            // Step 15: Validate ADD TO FILE dialog is displayed
            test.log(Status.INFO, "Step 15: Validating ADD TO FILE dialog");
            boolean addToFileDialogDisplayed = dailyPrescriptionPage.isAddToFileDialogDisplayed();
            if (!addToFileDialogDisplayed) {
                test.log(Status.FAIL, "ADD TO FILE dialog not displayed");
                Assert.fail("ADD TO FILE dialog validation failed");
            }
            test.log(Status.PASS, "✓ ADD TO FILE dialog is displayed");

            // Step 16: Search and validate "one"
            test.log(Status.INFO, "Step 16: Searching for 'one' in ADD TO FILE dialog");
            dailyPrescriptionPage.searchInAddToFile("one");
            boolean isResultVisible = dailyPrescriptionPage.isSearchResultDisplayed("One\nModified Dec 19");
            if (!isResultVisible) {
                test.log(Status.FAIL, "Search result 'One\\nModified Dec 19' not found");
                Assert.fail("Search result validation failed - Step 16");
            } else {
                test.log(Status.PASS, "✓ Search result 'One\\nModified Dec 19' is displayed");
            }
            dailyPrescriptionPage.clearSearchField();
            test.log(Status.INFO, "✓ Search field cleared");

            // Step 17-18: Swipe and click New File icon
            test.log(Status.INFO, "Step 17-18: Swiping and clicking New File icon");
            dailyPrescriptionPage.swipeAndClickNewFile();
            test.log(Status.PASS, "✓ New File icon clicked");
            Thread.sleep(5000); // 5 sec delay after step 18

            // Step 19: Enter file name "AutoFile"
            test.log(Status.INFO, "Step 19: Entering file name 'AutoFile'");
            dailyPrescriptionPage.enterFileName("AutoFile");
            test.log(Status.PASS, "✓ File name 'AutoFile' entered");
            Thread.sleep(2000); // Small wait for button to stabilize

            // Wait for Modified button
            dailyPrescriptionPage.waitForModifiedButton();

            // Step 20: Click modified button
            test.log(Status.INFO, "Step 20: Clicking modified button");
            dailyPrescriptionPage.clickModifiedButton();
            test.log(Status.PASS, "✓ Modified button clicked");
            Thread.sleep(5000); // 5 sec delay after step 20

            // Step 21: Click final close icon
            test.log(Status.INFO, "Step 21: Clicking final close icon");
            dailyPrescriptionPage.clickFinalCloseIcon();
            test.log(Status.PASS, "✓ Final close icon clicked");

            // Step 22: Validate SAVED dialog and message
            test.log(Status.INFO, "Step 22: Validating SAVED dialog");
            boolean savedDialogDisplayed = dailyPrescriptionPage.isSavedDialogDisplayed();
            if (!savedDialogDisplayed) {
                test.log(Status.FAIL, "SAVED dialog not displayed");
                Assert.fail("SAVED dialog validation failed");
            }
            test.log(Status.PASS, "✓ SAVED dialog is displayed");

            String savedMessage = dailyPrescriptionPage.getSavedMessage();
            if (savedMessage != null && !savedMessage.isEmpty()) {
                test.log(Status.PASS, "✓ Saved message: <b>" + savedMessage + "</b>");
            } else {
                test.log(Status.WARNING, "SAVED dialog displayed but message not found");
            }

            // ✅ ALL STEPS COMPLETED SUCCESSFULLY
            test.log(Status.PASS,
                    "<b>🎉 TEST PASSED - All 22 steps executed successfully with runtime validation</b>");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed with exception: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test execution failed: " + e.getMessage());
        }
    }
}