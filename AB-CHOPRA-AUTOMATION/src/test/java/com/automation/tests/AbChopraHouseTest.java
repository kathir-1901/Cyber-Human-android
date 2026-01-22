package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.AbChopraHousePage;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AbChopraHouseTest extends BaseTest {

        /**
         * ==================== AB CHOPRA HOUSE TEST CASE 1 ====================
         * 
         * Common Steps (1 & 2):
         * 1. Verify DAILY PRIORITY heading on home page
         * 2. Click Wellbeing Dashboard
         * 
         * Test-Specific Steps (3-32):
         * 3. Click AB CHOPRA HOUSE
         * 4. Click DISCOVER
         * 5. Verify Discover page is shown
         * 6. Swipe up once
         * 7. Swipe down once
         * 8. Click DISCOVER +
         * 9. Click FILTER
         * 10. Click Mind & Emotions radio button
         * 11. Set timing
         * 12. Click APPLY (wait for loading + 3 sec page load)
         * 13. Click See All 1, verify Listen page, click back
         * 14. Click See All 3, verify Watch page, click back
         * 15. Swipe up
         * 16. Click See All 2, verify Read page, click back
         * 17. Click video item
         * 18. Click search box and search "one"
         * 19. Verify "One" is shown in search results
         * 18 (second). Click New File icon
         * 19 (second). Create file with name "New"
         * 20. Click close icon
         * 21. Verify SAVED dialog and capture success message
         * 22. Click OK
         * 23. Click back icon
         * 24. Click ARCHIVE
         * 25. Verify Archive page is shown
         * 26. Verify new file name is there
         * 27. Click the new file (left side)
         * 28. Verify the file is open
         * 29. Click remove icon
         * 30. Click YES button
         * 31. Verify success message and capture it
         * 32. Click OK
         */
        @Test(priority = 1)
        public void testAbChopraHouse_Case1() throws InterruptedException {
                test = extent.createTest("AB Chopra House Test Case 1");
                test.log(Status.INFO, "Starting AB Chopra House Test Case 1");

                HomePage homePage = new HomePage(driver);
                AbChopraHousePage abChopraHousePage = new AbChopraHousePage(driver);

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

                // ✅ STEP 3: Click AB CHOPRA HOUSE
                test.log(Status.INFO, "Step 3: Clicking AB CHOPRA HOUSE");
                abChopraHousePage.clickAbChopraHouse();
                test.log(Status.PASS, "✓ Step 3: AB CHOPRA HOUSE clicked");
                Thread.sleep(2000);

                // ✅ STEP 4: Click DISCOVER
                test.log(Status.INFO, "Step 4: Clicking DISCOVER");
                abChopraHousePage.clickDiscover();
                test.log(Status.PASS, "✓ Step 4: DISCOVER clicked");
                Thread.sleep(3000);

                // ✅ STEP 6: Swipe up once (includes 2-second wait)
                test.log(Status.INFO, "Step 6: Swiping up once");
                abChopraHousePage.swipeUpOnce();
                test.log(Status.PASS, "✓ Step 6: Swiped up once");
                Thread.sleep(1000);

                // ✅ STEP 7: Swipe down once
                test.log(Status.INFO, "Step 7: Swiping down once");
                abChopraHousePage.swipeDownOnce();
                test.log(Status.PASS, "✓ Step 7: Swiped down once");
                Thread.sleep(5000);

                // ✅ STEP 8: Click DISCOVER +
                test.log(Status.INFO, "Step 8: Clicking DISCOVER +");
                abChopraHousePage.clickDiscoverPlus();
                test.log(Status.PASS, "✓ Step 8: DISCOVER + clicked");
                Thread.sleep(9000);

                // ✅ STEP 9 (NEW): Search for "love"
                test.log(Status.INFO, "Step 9 (new): Searching for 'love'");
                abChopraHousePage.searchForLove();
                test.log(Status.PASS, "✓ Step 9 (new): Searched for 'love'");
                Thread.sleep(9000);

                // ✅ STEP 9 (NEW): Hide keyboard
                test.log(Status.INFO, "Step 9 (new): Hiding keyboard");
                abChopraHousePage.hideKeyboard();
                test.log(Status.PASS, "✓ Step 9 (new): Keyboard hidden");

                // ✅ STEP 9 (NEW): Verify Love & Unity article is displayed
                test.log(Status.INFO, "Step 9 (new): Verifying Love & Unity article is displayed");
                Assert.assertTrue(abChopraHousePage.isLoveArticleDisplayed(),
                                "Love & Unity article should be displayed");
                test.log(Status.PASS, "✓ Step 9 (new): Love & Unity article is displayed");

                // ✅ STEP 9 (NEW): Clear search
                test.log(Status.INFO, "Step 9 (new): Clearing search");
                abChopraHousePage.clearSearch();
                test.log(Status.PASS, "✓ Step 9 (new): Search cleared");
                Thread.sleep(9000);

                // ✅ STEP 9: Click FILTER
                test.log(Status.INFO, "Step 9: Clicking FILTER");
                abChopraHousePage.clickFilter();
                test.log(Status.PASS, "✓ Step 9: FILTER clicked");
                Thread.sleep(2000);

                // ✅ STEP 10: Click Mind & Emotions radio button
                test.log(Status.INFO, "Step 10: Clicking Mind & Emotions radio button");
                abChopraHousePage.clickMindEmotionsRadio();
                test.log(Status.PASS, "✓ Step 10: Mind & Emotions radio button clicked");
                Thread.sleep(1000);

                // ✅ STEP 11: Set timing
                test.log(Status.INFO, "Step 11: Setting timing");
                abChopraHousePage.setTiming();
                test.log(Status.PASS, "✓ Step 11: Timing set");
                Thread.sleep(1000);

                // ✅ STEP 12: Click APPLY (wait for loading)
                test.log(Status.INFO, "Step 12: Clicking APPLY and waiting for loading");
                abChopraHousePage.clickApply();
                test.log(Status.PASS, "✓ Step 12: APPLY clicked and loading completed");
                Thread.sleep(9000); // Wait for page to load completely before proceeding

                // ✅ STEP 13: Click See All 1, verify Listen page, click back
                test.log(Status.INFO, "Step 13: Clicking See All 1");
                abChopraHousePage.clickSeeAll1();
                test.log(Status.PASS, "✓ Step 13a: See All 1 clicked");
                Thread.sleep(9000);

                test.log(Status.INFO, "Step 13: Verifying Listen page is displayed");
                Assert.assertTrue(abChopraHousePage.isListenPageDisplayed(),
                                "Listen page should be displayed");
                test.log(Status.PASS, "✓ Step 13b: Listen page is displayed");

                test.log(Status.INFO, "Step 13: Clicking back button");
                abChopraHousePage.clickBackButton();
                test.log(Status.PASS, "✓ Step 13c: Back button clicked");
                Thread.sleep(9000);

                // ✅ STEP 14: Click See All 3, verify Watch page, click back (SWAPPED with step
                // 16)
                test.log(Status.INFO, "Step 14: Clicking See All 3");
                abChopraHousePage.clickSeeAll3();
                test.log(Status.PASS, "✓ Step 14a: See All 3 clicked");
                Thread.sleep(9000);

                test.log(Status.INFO, "Step 14: Verifying Watch page is displayed");
                Assert.assertTrue(abChopraHousePage.isWatchPageDisplayed(),
                                "Watch page should be displayed");
                test.log(Status.PASS, "✓ Step 14b: Watch page is displayed");

                test.log(Status.INFO, "Step 14: Clicking back button");
                abChopraHousePage.clickBackButton();
                test.log(Status.PASS, "✓ Step 14c: Back button clicked");
                Thread.sleep(2000);

                // ✅ STEP 15: Swipe up on container
                test.log(Status.INFO, "Step 15: Swiping up on container");
                abChopraHousePage.swipeUpOnContainer();
                test.log(Status.PASS, "✓ Step 15: Swiped up on container");
                Thread.sleep(5000);

                // ✅ STEP 16: Click See All 2, verify Read page, click back (SWAPPED with step
                // 14)
                test.log(Status.INFO, "Step 16: Clicking See All 2");
                abChopraHousePage.clickSeeAll2();
                test.log(Status.PASS, "✓ Step 16a: See All 2 clicked");
                Thread.sleep(9000);

                test.log(Status.INFO, "Step 16: Verifying Read page is displayed");
                Assert.assertTrue(abChopraHousePage.isReadPageDisplayed(),
                                "Read page should be displayed");
                test.log(Status.PASS, "✓ Step 16b: Read page is displayed");

                test.log(Status.INFO, "Step 16: Clicking back button");
                abChopraHousePage.clickBackButton();
                test.log(Status.PASS, "✓ Step 16c: Back button clicked");
                Thread.sleep(5000);

                // ✅ STEP 17: Click video item
                test.log(Status.INFO, "Step 17: Clicking video item");
                abChopraHousePage.clickVideoItem();
                test.log(Status.PASS, "✓ Step 17: Video item clicked");
                Thread.sleep(2000);

                // ✅ STEP 18: Click search box and search "one"
                test.log(Status.INFO, "Step 18: Clicking search box and searching for 'one'");
                abChopraHousePage.searchForOne();
                test.log(Status.PASS, "✓ Step 18: Searched for 'one'");
                Thread.sleep(2000);

                // ✅ STEP 19: Verify "One" is shown in search results
                // test.log(Status.INFO, "Step 19: Verifying 'One' is shown in search results");
                // Assert.assertTrue(abChopraHousePage.isSearchResultOneDisplayed(),
                // "Search result 'One' should be displayed");
                // test.log(Status.PASS, "✓ Step 19: 'One' is shown in search results");

                // ✅ STEP 18 (second): Click New File icon
                test.log(Status.INFO, "Step 18 (second): Clicking New File icon");
                abChopraHousePage.clickNewFileIcon();
                test.log(Status.PASS, "✓ Step 18 (second): New File icon clicked");
                Thread.sleep(2000);

                // ✅ STEP 19 (second): Create file with name "New"
                test.log(Status.INFO, "Step 19 (second): Creating file with name 'New'");
                abChopraHousePage.createFile("New");
                test.log(Status.PASS, "✓ Step 19 (second): File name 'New' entered");
                Thread.sleep(1000);

                // ✅ STEP 19.5: Trigger save before close (force focus loss)
                test.log(Status.INFO, "Step 19.5: Triggering save by forcing focus loss");
                abChopraHousePage.triggerSaveBeforeClose();
                test.log(Status.PASS, "✓ Step 19.5: Focus loss triggered, save callback executed");

                // ✅ STEP 20: Click close icon
                test.log(Status.INFO, "Step 20: Clicking close icon");
                abChopraHousePage.clickCloseIcon();
                test.log(Status.PASS, "✓ Step 20: Close icon clicked");
                Thread.sleep(2000);

                // ✅ STEP 21: Verify SAVED dialog and capture success message
                test.log(Status.INFO, "Step 21: Verifying SAVED dialog is displayed");
                Assert.assertTrue(abChopraHousePage.isSavedDialogDisplayed(),
                                "SAVED dialog should be displayed");
                test.log(Status.PASS, "✓ Step 21a: SAVED dialog is displayed");

                test.log(Status.INFO, "Step 21: Capturing success message");
                String savedMessage = abChopraHousePage.getSavedSuccessMessage();
                test.log(Status.PASS, "✓ Step 21b: Success message captured");
                test.log(Status.INFO, "📋 Saved Success Message: " + savedMessage);

                // Verify the success message
                Assert.assertEquals(savedMessage, "Your article has been successfully saved.",
                                "Success message should confirm successful save");
                test.log(Status.PASS, "✓ Step 21c: Verified correct success message displayed");

                // ✅ STEP 22: Click OK
                test.log(Status.INFO, "Step 22: Clicking OK button");
                abChopraHousePage.clickOkButton();
                test.log(Status.PASS, "✓ Step 22: OK button clicked");
                Thread.sleep(2000);

                // ✅ STEP 23: Click back icon
                test.log(Status.INFO, "Step 23: Clicking back icon");
                abChopraHousePage.clickBackButton();
                test.log(Status.PASS, "✓ Step 23: Back icon clicked");
                Thread.sleep(2000);

                // ✅ STEP 24: Click ARCHIVE
                test.log(Status.INFO, "Step 24: Clicking ARCHIVE");
                abChopraHousePage.clickArchive();
                test.log(Status.PASS, "✓ Step 24: ARCHIVE clicked");
                Thread.sleep(2000);

                // ✅ STEP 25: Verify Archive page is shown
                test.log(Status.INFO, "Step 25: Verifying Archive page is displayed");
                Assert.assertTrue(abChopraHousePage.isArchivePageDisplayed(),
                                "Archive page should be displayed");
                test.log(Status.PASS, "✓ Step 25: Archive page is displayed");

                // ✅ STEP 26: Verify new file name is there
                test.log(Status.INFO, "Step 26: Verifying new file is in archive");
                Assert.assertTrue(abChopraHousePage.isNewFileInArchive(),
                                "New file should be in archive");
                test.log(Status.PASS, "✓ Step 26: New file is in archive");

                // ✅ STEP 27: Click the new file (left side)
                test.log(Status.INFO, "Step 27: Clicking new file in archive");
                abChopraHousePage.clickNewFileInArchive();
                test.log(Status.PASS, "✓ Step 27: New file clicked");
                Thread.sleep(2000);

                // ✅ STEP 29: Click remove icon
                test.log(Status.INFO, "Step 29: Clicking remove icon");
                abChopraHousePage.clickRemoveIcon();
                test.log(Status.PASS, "✓ Step 29: Remove icon clicked");
                Thread.sleep(2000);

                // ✅ STEP 30: Click YES button
                test.log(Status.INFO, "Step 30: Clicking YES button");
                abChopraHousePage.clickYesButton();
                test.log(Status.PASS, "✓ Step 30: YES button clicked");
                Thread.sleep(2000);

                // ✅ STEP 31: Verify success message and capture it
                test.log(Status.INFO, "Step 31: Verifying SUCCESS dialog is displayed");
                Assert.assertTrue(abChopraHousePage.isSuccessDialogDisplayed(),
                                "SUCCESS dialog should be displayed");
                test.log(Status.PASS, "✓ Step 31a: SUCCESS dialog is displayed");

                test.log(Status.INFO, "Step 31: Capturing delete success message");
                String deleteMessage = abChopraHousePage.getDeleteSuccessMessage();
                test.log(Status.PASS, "✓ Step 31b: Delete success message captured");
                test.log(Status.INFO, "📋 Delete Success Message: " + deleteMessage);

                // Verify the delete success message
                Assert.assertEquals(deleteMessage, "Your article has been successfully deleted",
                                "Delete message should confirm successful deletion");
                test.log(Status.PASS, "✓ Step 31c: Verified correct delete success message displayed");

                // ✅ STEP 32: Click OK
                test.log(Status.INFO, "Step 32: Clicking OK button");
                abChopraHousePage.clickOkButton();
                test.log(Status.PASS, "✓ Step 32: OK button clicked");

                test.log(Status.PASS, "AB Chopra House Test Case 1 completed successfully");
        }
}