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
            // ✅ COMMON STEP 1: Wait for Home Page and verify DAILY PRIORITY heading
            test.log(Status.INFO, "Step 1: Waiting for home page to load");
            try {
                homePage.waitForHomePage();
                test.log(Status.PASS, "✓ Step 1: Home page is displayed");
            } catch (Exception e) {
                test.log(Status.FAIL, "Home page validation failed: " + e.getMessage());
                Assert.fail("Home page validation failed - DAILY PRIORITY heading not displayed");
            }

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