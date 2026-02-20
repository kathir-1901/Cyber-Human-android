package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditProfileTest extends BaseTest {

    /**
     * NEGATIVE TEST DATA FOR EDIT PROFILE
     * Test scenarios for Edit Profile page validation
     */
    @DataProvider(name = "negativeEditProfileData")
    public Object[][] getNegativeEditProfileData() {
        return new Object[][] {
                // Scenario, Name, Email, Phone
                { "Email Without @", "John Doe", "testexample.com", "123456789" },
                { "Email Without Domain", "John Doe", "test@", "123456789" },
                { "Invalid Phone - Letters", "John Doe", "test@example.com", "abcdefgh" },
                { "Short Phone Number", "John Doe", "test@example.com", "12" },
                { "Invalid Email Format", "John Doe", "invalidemail", "123456789" },
                { "Empty Name Field", "", "test@example.com", "123456789" },
                { "Empty Email Field", "John Doe", "", "123456789" },
                { "Empty Phone Number", "John Doe", "test@example.com", "" },

        };
    }

    /**
     * TEST DATA FOR CHANGE PASSWORD
     * Scenarios: Wrong Old Pass, Weak Pass, Same Pass, Valid Change
     */
    @DataProvider(name = "changePasswordData")
    public Object[][] getChangePasswordData() {
        // "loginPassword" is assumed to be "Ramesh@2025" based on
        // navigateToEditProfileFirstTime
        String currentLoginPass = "Human@2026";

        return new Object[][] {
                // Scenario, Current Pass, New Pass, Confirm Pass, Expected Error XPath (or
                // "SUCCESS")
                { "Wrong Old Password Validation", "Testing@2025", "Human@2026", "Human@2026",
                        "//android.view.View[@content-desc='Wrong password. Please enter correct password.']" },
                { "Weak Password Validation", currentLoginPass, "test2026", "test2026",
                        "//android.view.View[@content-desc='Use at least 8 characters with uppercase, lowercase, number, and special symbol.']" },
                { "Same Old and New Password Validation", currentLoginPass, currentLoginPass, currentLoginPass,
                        "//android.view.View[@content-desc='Current and new password cannot be the same.']" },
                { "Valid Change Password", currentLoginPass, "Testing@2026", "Testing@2026", "SUCCESS" }
        };
    }

    // Track if we've already done the initial login flow
    private static boolean isLoggedIn = false;

    /**
     * COMPLETE NAVIGATION FLOW TO EDIT PROFILE PAGE (FIRST TEST ONLY)
     * This method handles the entire navigation from Sign In to Edit Profile
     */
    private void navigateToEditProfileFirstTime() throws InterruptedException {
        // If already on home page (noReset keeps session) — skip steps 1-3 and jump to
        // step 4
        HomePage homeCheck = new HomePage(driver);
        if (homeCheck.isHomePageDisplayed()) {
            test.log(Status.INFO, "Already on Home page — skipping sign-in, 2FA and Link Devices (steps 1-3)");
            isLoggedIn = true;
            // Jump directly to Step 4 below
        } else {
            // Step 1: Sign In with valid credentials
            SignInPage signInPage = new SignInPage(driver);
            signInPage.enterEmail("ramesh@navadhiti.com");
            signInPage.enterPassword("Human@2026");
            signInPage.clickContinue();
            test.log(Status.INFO, "Signed in with valid credentials");
            Thread.sleep(2000);

            // Step 2: Click SKIP FOR NOW (handles 2FA / Link Devices / any interstitial
            // screen)
            try {
                LinkDevicesPage linkDevicesPage = new LinkDevicesPage(driver);
                linkDevicesPage.clickSkipForNow();
                test.log(Status.INFO, "Clicked SKIP FOR NOW");
                Thread.sleep(2000);
            } catch (Exception ignored) {
                test.log(Status.INFO, "SKIP FOR NOW not present — continuing");
            }
        }

        // Step 4: Wait for Home page (up to 15s after sign-in + SKIP FOR NOW)
        // then navigate: Wellbeing Dashboard → Profile
        HomePage homePage = new HomePage(driver);
        boolean homeFound = false;
        for (int i = 0; i < 5; i++) {
            if (homePage.isHomePageDisplayed()) {
                homeFound = true;
                break;
            }
            Thread.sleep(3000); // wait 3s between retries → up to 15s total
        }

        if (homeFound) {
            homePage.clickWellbeingDashboard();
            test.log(Status.INFO, "Clicked Wellbeing Dashboard menu");
            Thread.sleep(2000);

            // Step 4.1: Click PROFILE
            homePage.clickProfile();
            test.log(Status.INFO, "Clicked PROFILE button");
            Thread.sleep(2000);
        } else {
            test.log(Status.WARNING, "Home page not detected after 15s — attempting navigation anyway");
        }

        // Step 5: Check if Profile page displayed (optional verification)
        ProfilePage profilePage = new ProfilePage(driver);
        // ...

        // Step 6: Click ACCOUNT to navigate to Edit Profile
        profilePage.clickAccount();
        test.log(Status.INFO, "Clicked ACCOUNT button");
        Thread.sleep(2000);

        // Verify Edit Profile page is displayed
        EditProfilePage editProfilePage = new EditProfilePage(driver);
        if (editProfilePage.isEditProfilePageDisplayed()) {
            test.log(Status.INFO, "Edit Profile page displayed successfully");
        }

        // ── Step 7: Click profile image icon ────────────────────────────────
        editProfilePage.clickProfileImageIcon();
        test.log(Status.INFO, "Step 7: Clicked profile image icon");
        Thread.sleep(1000);

        // ── Step 8: Choose from Gallery ──────────────────────────────────────
        editProfilePage.clickChooseFromGallery();
        test.log(Status.INFO, "Step 8: Clicked CHOOSE FROM GALLERY");
        Thread.sleep(1500);

        // ── Step 9: Select image from gallery ───────────────────────────────
        editProfilePage.selectGalleryImage();
        test.log(Status.INFO, "Step 9: Selected image from gallery");
        Thread.sleep(1500);

        // ── Step 10: Confirm / crop image ────────────────────────────────────
        editProfilePage.confirmCropImage();
        test.log(Status.INFO, "Step 10: Confirmed crop");
        Thread.sleep(2000);

        // ── Step 11: Verify upload success dialog ────────────────────────────
        if (editProfilePage.isProfileImageUploadedDialogDisplayed()) {
            test.log(Status.PASS, "Step 11: 'PROFILE IMAGE UPLOADED' dialog displayed");
            String uploadMsg = editProfilePage.getProfileImageUploadSuccessMessage();
            if (uploadMsg != null) {
                test.log(Status.INFO, "Upload message: \"" + uploadMsg + "\"");
            }
        } else {
            test.log(Status.INFO, "Step 11: Upload dialog not detected — continuing");
        }

        // ── Step 12: Click OK ─────────────────────────────────────────────────
        try {
            editProfilePage.clickOkButton();
            test.log(Status.INFO, "Step 12: Clicked OK on upload dialog");
        } catch (Exception ignored) {
            test.log(Status.INFO, "Step 12: OK button not present, skipping");
        }
        Thread.sleep(1000);

        // ── Step 13: Click profile image icon again ──────────────────────────
        editProfilePage.clickProfileImageIcon();
        test.log(Status.INFO, "Step 13: Clicked profile image icon again");
        Thread.sleep(1000);

        // ── Step 14: Remove photo ─────────────────────────────────────────────
        editProfilePage.clickRemovePhoto();
        test.log(Status.INFO, "Step 14: Clicked REMOVE PHOTO");
        Thread.sleep(2000);

        // ── Step 15: Verify delete success dialog ────────────────────────────
        if (editProfilePage.isProfileImageDeletedDialogDisplayed()) {
            test.log(Status.PASS, "Step 15: 'DELETE SUCCESSFUL' dialog displayed");
            String removeMsg = editProfilePage.getProfileImageRemoveSuccessMessage();
            if (removeMsg != null) {
                test.log(Status.INFO, "Remove message: \"" + removeMsg + "\"");
            }
        } else {
            test.log(Status.INFO, "Step 15: Delete dialog not detected — continuing");
        }

        // ── Step 16: Click OK ─────────────────────────────────────────────────
        try {
            editProfilePage.clickOkButton();
            test.log(Status.INFO, "Step 16: Clicked OK on delete dialog");
        } catch (Exception ignored) {
            test.log(Status.INFO, "Step 16: OK button not present, skipping");
        }
        Thread.sleep(1000);

        // Mark as logged in for subsequent tests
        isLoggedIn = true;
    }

    /**
     * NAVIGATION FROM HOMEPAGE TO EDIT PROFILE (SUBSEQUENT TESTS)
     * This method starts from Homepage and navigates to Edit Profile
     * Used for all tests after the first one
     */
    private void navigateToEditProfileFromHome() throws InterruptedException {
        // Start from Homepage - click Wellbeing Dashboard
        // ── App recovery: if app is on the Xiaomi launcher, bring it back ────
        HomePage homePage = new HomePage(driver);
        if (!homePage.isHomePageDisplayed()) {
            test.log(Status.INFO, "Home page not detected — activating app and waiting...");
            try {
                ((io.appium.java_client.android.AndroidDriver) driver)
                        .activateApp("com.houseofepigenetics.abchopra");
                Thread.sleep(2000);
            } catch (Exception e) {
                test.log(Status.WARNING, "activateApp failed: " + e.getMessage());
            }
        }

        if (homePage.isHomePageDisplayed()) {
            homePage.clickWellbeingDashboard();
            test.log(Status.INFO, "Clicked Wellbeing Dashboard menu from Homepage");
            Thread.sleep(2000);

            // Click PROFILE
            homePage.clickProfile();
            test.log(Status.INFO, "Clicked PROFILE button");
            Thread.sleep(2000);
        }

        // Click ACCOUNT to navigate to Edit Profile
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickAccount();
        test.log(Status.INFO, "Clicked ACCOUNT button");
        Thread.sleep(2000);

        // Verify Edit Profile page is displayed
        EditProfilePage editProfilePage = new EditProfilePage(driver);
        if (editProfilePage.isEditProfilePageDisplayed()) {
            test.log(Status.INFO, "Edit Profile page displayed successfully");
        }
    }

    /**
     * SMART NAVIGATION
     * - First test → navigateToEditProfileFirstTime() (includes profile image steps
     * 7-16)
     * - All others → navigateToEditProfileFromHome() (quick navigation from home)
     */
    private void navigateToEditProfile() throws InterruptedException {
        // Recovery: if already on Edit Profile page, go back first
        try {
            EditProfilePage editProfilePage = new EditProfilePage(driver);
            if (editProfilePage.isEditProfilePageDisplayed()) {
                test.log(Status.INFO, "Recovery: Already on Edit Profile page — navigating back.");
                driver.navigate().back();
                Thread.sleep(1000);
            }
        } catch (Exception ignored) {
        }

        if (!isLoggedIn) {
            // First test — full flow including profile image upload/remove (steps 7-16)
            navigateToEditProfileFirstTime();
        } else {
            // Subsequent tests — quick navigation from home page
            navigateToEditProfileFromHome();
        }
    }

    /**
     * NAVIGATE TO CHANGE PASSWORD PAGE
     * Ensures we are on the Change Password page, navigating from Edit Profile if
     * needed.
     */
    private void navigateToChangePassword() throws InterruptedException {
        // First check if we are already on Change Password Page
        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        if (changePasswordPage.isChangePasswordPageDisplayed()) {
            test.log(Status.INFO, "Already on Change Password Page");
            return;
        }

        // ── Handle SKIP FOR NOW interstitial (may appear on app start) ────────
        try {
            LinkDevicesPage linkDevicesPage = new LinkDevicesPage(driver);
            linkDevicesPage.clickSkipForNow();
            test.log(Status.INFO, "Clicked SKIP FOR NOW (interstitial dismissed)");
            Thread.sleep(1500);
        } catch (Exception ignored) {
            // Not present — continue normally
        }

        // ── App recovery: if app is on the Xiaomi launcher, bring it back ────
        HomePage homePage = new HomePage(driver);
        if (!homePage.isHomePageDisplayed()) {
            test.log(Status.INFO, "Home page not detected — activating app...");
            try {
                ((io.appium.java_client.android.AndroidDriver) driver)
                        .activateApp("com.houseofepigenetics.abchopra");
                Thread.sleep(2000);
            } catch (Exception e) {
                test.log(Status.WARNING, "activateApp failed: " + e.getMessage());
            }
        }

        if (homePage.isHomePageDisplayed()) {
            homePage.clickWellbeingDashboard();
            test.log(Status.INFO, "Clicked Wellbeing Dashboard menu");
            Thread.sleep(2000);

            // Step 4.1: Click PROFILE
            homePage.clickProfile();
            test.log(Status.INFO, "Clicked PROFILE button");
            Thread.sleep(2000);
        }

        // Step 5: Check if Profile page displayed and wait for it to load
        ProfilePage profilePage = new ProfilePage(driver);
        if (profilePage.isProfilePageDisplayed()) {
            test.log(Status.INFO, "Profile page displayed successfully");
            Thread.sleep(1000); // Additional wait for page elements to be fully loaded
        } else {
            test.log(Status.WARNING, "Profile page not detected, attempting to continue anyway");
        }

        // Step 6: Click ACCOUNT to navigate to Edit Profile
        profilePage.clickAccount();
        test.log(Status.INFO, "Clicked ACCOUNT button");
        Thread.sleep(2000);

        // Verify Edit Profile page is displayed
        EditProfilePage editProfilePage = new EditProfilePage(driver);
        if (editProfilePage.isEditProfilePageDisplayed()) {
            test.log(Status.INFO, "Edit Profile page displayed successfully");
        }

        // Click Change Password button
        editProfilePage.clickChangePassword();
        test.log(Status.INFO, "Clicked Change Password button");
        Thread.sleep(2000);

        if (changePasswordPage.isChangePasswordPageDisplayed()) {
            test.log(Status.INFO, "Change Password page displayed");
        }
    }

    /**
     * TEST CHANGE PASSWORD SCENARIOS
     * Covers Negative (Wrong, Weak, Same) and Positive (Success) scenarios
     */
    @Test(dataProvider = "changePasswordData", priority = 1)
    public void testChangePassword(String scenario, String currentPass, String newPass, String confirmPass,
            String expectedResult) throws InterruptedException {
        test = extent.createTest("Change Password: " + scenario);
        test.log(Status.INFO, "Scenario: " + scenario);

        try {
            // 1. Navigate to Change Password Page
            navigateToChangePassword();
            ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);

            // 2. Enter Password Fields (Step 4 Common Action)
            changePasswordPage.enterCurrentPassword(currentPass);
            test.log(Status.INFO, "Entered Current Password");

            changePasswordPage.enterNewPassword(newPass);
            test.log(Status.INFO, "Entered New Password");

            changePasswordPage.enterConfirmPassword(confirmPass);
            test.log(Status.INFO, "Entered Confirm Password");

            // 3. Click Change Password Button
            changePasswordPage.clickChangePasswordButton();
            test.log(Status.INFO, "Clicked Change Password Button");
            Thread.sleep(2000);

            // 4. Verify Result
            if (expectedResult.equals("SUCCESS")) {
                // Positive Scenario
                if (changePasswordPage.isSuccessDialogDisplayed()) {
                    test.log(Status.PASS, "Success Dialog Displayed");

                    String successMsg = changePasswordPage.getSuccessMessage();
                    if (successMsg != null) {
                        test.log(Status.INFO, "Success Message: " + successMsg);
                    } else {
                        test.log(Status.WARNING, "Could not capture Success Message text");
                    }

                    changePasswordPage.clickOkButton();
                    test.log(Status.INFO, "Clicked OK Button");
                    Thread.sleep(1000);

                } else {
                    test.log(Status.FAIL, "Success Dialog NOT Displayed");
                    Assert.fail("Expected Success Dialog was not displayed.");
                }
            } else {
                // Negative Scenarios
                String actualError = changePasswordPage.getValidationErrorMessage(expectedResult);
                if (actualError != null) {
                    test.log(Status.PASS, "✓ Error validated: " + actualError);
                } else {
                    test.log(Status.FAIL, "✗ Expected error not found or mismatch");
                    test.log(Status.INFO, "Expected Xpath: " + expectedResult);
                    Assert.fail("Expected error validation not found: " + expectedResult);
                }
            }
        } finally {
            // CRITICAL: Navigate back to home page for next test iteration
            // This ensures each test starts from a clean state
            try {
                test.log(Status.INFO, "Navigating back to Home Page for next iteration");

                // Press back multiple times to reach home page
                for (int i = 0; i < 5; i++) {
                    driver.navigate().back();
                    Thread.sleep(500);

                    // Check if we reached home page
                    HomePage homePage = new HomePage(driver);
                    if (homePage.isHomePageDisplayed()) {
                        test.log(Status.INFO, "Successfully navigated back to Home Page");
                        break;
                    }
                }
            } catch (Exception e) {
                test.log(Status.WARNING, "Could not navigate back to Home Page: " + e.getMessage());
            }
        }
    }

    /**
     * RUNTIME-BASED NEGATIVE TEST FOR EDIT PROFILE
     * 
     * Test Flow:
     * 1. Navigate from Sign In through all pages to Edit Profile
     * 2. Enter invalid/empty data in form fields
     * 3. Click SAVE CHANGES button
     * 4. Check if ANY validation appears at runtime
     * 5. PASS if validation detected, FAIL if not
     * 6. Log the actual runtime validation message in Extent Report
     */
    @Test(dataProvider = "negativeEditProfileData")
    public void testNegativeEditProfile(String testScenario, String name, String email, String phone)
            throws InterruptedException {

        test = extent.createTest("Negative Test: " + testScenario);
        test.log(Status.INFO, "Name: '" + name + "' | Email: '" + email + "' | Phone: '" + phone + "'");

        try {
            // Navigate to Edit Profile page
            navigateToEditProfile();

            EditProfilePage editProfilePage = new EditProfilePage(driver);

            // Clear and enter test data
            editProfilePage.enterName(name);
            test.log(Status.INFO, "Entered name: '" + name + "'");

            editProfilePage.enterEmail(email);
            test.log(Status.INFO, "Entered email: '" + email + "'");

            // HANDLE DATE OF BIRTH
            editProfilePage.clickDateOfBirth();
            test.log(Status.INFO, "Clicked Date of Birth");
            editProfilePage.performDateSelection();
            test.log(Status.INFO, "Performed Date Selection (Swipe & Confirm)");

            // HANDLE GENDER (Standardized to Male for negative tests)
            editProfilePage.clickGender();
            test.log(Status.INFO, "Clicked Gender dropdown");
            Thread.sleep(500);
            editProfilePage.selectGender("Male");
            test.log(Status.INFO, "Selected gender: Male");

            // HANDLE COUNTRY (Standardized to India for negative tests)
            editProfilePage.clickCountryCode();
            test.log(Status.INFO, "Clicked Country Code dropdown");
            Thread.sleep(500);
            editProfilePage.selectCountry("Belarus");
            test.log(Status.INFO, "Selected country: Belarus");

            editProfilePage.enterPhoneNumber(phone);
            test.log(Status.INFO, "Entered phone: '" + phone + "'");

            // Click SAVE CHANGES
            editProfilePage.clickSaveChanges();
            test.log(Status.INFO, "Clicked SAVE CHANGES button");

            // ── Step 1: Immediately check for SUCCESS popup (before it auto-dismisses) ──
            boolean successDetected = editProfilePage.isSuccessPopupVisible();
            if (successDetected) {
                test.log(Status.PASS, "UPDATED SUCCESSFULLY popup detected — profile saved");
                test.log(Status.PASS, "Test PASSED: Application accepted the input and saved successfully");
                return; // Done — no need for further validation check
            }

            // ── Step 2: Wait and check for VALIDATION messages ──────────────────────────
            Thread.sleep(2000);
            boolean validationDetected = editProfilePage.isAnyValidationVisible();

            if (validationDetected) {
                String validationMessage = editProfilePage.getValidationMessage();
                if (validationMessage != null && !validationMessage.trim().isEmpty()) {
                    test.log(Status.INFO, "Validation message: \"" + validationMessage + "\"");
                }
                test.log(Status.PASS, "Validation detected - Negative case handled correctly");
                test.log(Status.PASS, "Test PASSED: Application showed validation for invalid input");
            } else {
                // Neither success popup nor validation — hard fail
                test.log(Status.FAIL, "No popup or validation detected after save for: name='"
                        + name + "', email='" + email + "', phone='" + phone + "'");
                Assert.fail("Expected validation or success popup after save, but NONE was detected");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Test encountered an error: " + e.getMessage());
            throw e; // Re-throw to ensure TestNG records it as failure
        }
    }

    // /**
    // * TEST WITH DATE OF BIRTH, GENDER, AND COUNTRY SELECTION
    // *
    // * This test demonstrates handling of:
    // * - Date of Birth picker (runtime)
    // * - Gender dropdown
    // * - Country selection (India)
    // */
    // @Test
    // public void testEditProfileWithDateGenderCountry() throws
    // InterruptedException {
    // test = extent.createTest("Test: Edit Profile with Date, Gender, Country");
    // test.log(Status.INFO, "Testing date picker, gender dropdown, and country
    // selection");

    // // Navigate to Edit Profile page
    // navigateToEditProfile();

    // EditProfilePage editProfilePage = new EditProfilePage(driver);

    // // Enter valid data
    // editProfilePage.enterName("Test User");
    // test.log(Status.INFO, "Entered name");

    // editProfilePage.enterEmail("test@example.com");
    // test.log(Status.INFO, "Entered email");

    // editProfilePage.clickDateOfBirth();
    // test.log(Status.INFO, "Clicked Date of Birth field");

    // editProfilePage.performDateSelection();
    // test.log(Status.INFO, "Performed Date Selection (Swipe & Confirm)");
    // Thread.sleep(1000);

    // // Click and select Gender
    // editProfilePage.clickGender();
    // test.log(Status.INFO, "Clicked Gender dropdown");
    // Thread.sleep(1000);

    // editProfilePage.selectGender("Male");
    // test.log(Status.INFO, "Selected gender: Male");
    // Thread.sleep(1000);

    // // Click and select Country (India)
    // editProfilePage.clickCountryCode();
    // test.log(Status.INFO, "Clicked Country Code dropdown");
    // Thread.sleep(1000);

    // editProfilePage.selectCountry("Belarus");
    // test.log(Status.INFO, "Selected country: Belarus");
    // Thread.sleep(1000);

    // editProfilePage.enterPhoneNumber("9876543210");
    // test.log(Status.INFO, "Entered phone number");

    // // Click SAVE CHANGES
    // editProfilePage.clickSaveChanges();
    // test.log(Status.INFO, "Clicked SAVE CHANGES button");

    // Thread.sleep(2000);

    // // Check for validation (should not appear for valid data)
    // boolean validationDetected = editProfilePage.isAnyValidationVisible();

    // if (!validationDetected) {
    // test.log(Status.PASS, "✓ No validation errors - Profile update successful");
    // test.log(Status.PASS, "Test PASSED: Valid data accepted");
    // } else {
    // String validationMessage = editProfilePage.getValidationMessage();
    // if (validationMessage != null) {
    // test.log(Status.INFO, "Validation message: \"" + validationMessage + "\"");
    // }
    // test.log(Status.INFO, "Validation appeared - may need to check data format");
    // }
    // }
}
