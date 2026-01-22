package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.ProfilePage;
import com.automation.utils.ProfileNavigationHelper;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * ✅ PROFILE PAGE TEST SUITE
 * 
 * This test class contains 5 comprehensive test cases for the Profile page:
 * 1. My Orders - Track Order
 * 2. My Orders - Send Sample
 * 3. Help & Support (4 sub-sections: Getting Started, DNA Kit, Subscription &
 * Billing, Troubleshooting)
 * 4. Legal Information (3 sub-sections: T&C, Privacy Policy, Open Content)
 * 5. Logout functionality
 * 
 * All tests use the ProfileNavigationHelper module for common navigation steps.
 */
public class ProfileTest extends BaseTest {

    /**
     * ✅ TEST CASE 1: MY ORDERS - TRACK ORDER
     * 
     * Steps:
     * 1-4: Common navigation to Profile page (via ProfileNavigationHelper)
     * 5. Click MY ORDERS
     * 6. Click TRACK ORDER button
     * 7. Verify TRACK ORDER page is displayed
     */
    @Test(priority = 1)
    public void testMyOrders_TrackOrder() throws InterruptedException {
        test = extent.createTest("Test Case 1: My Orders - Track Order");
        test.log(Status.INFO, "Testing Track Order functionality");

        // Common navigation steps (Steps 1-4)
        ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);

        // Step 5: Click MY ORDERS
        test.log(Status.INFO, "Step 5: Clicking MY ORDERS");
        profilePage.clickMyOrders();
        test.log(Status.PASS, "✓ MY ORDERS clicked");
        Thread.sleep(2000);

        // Step 6: Click TRACK ORDER button
        test.log(Status.INFO, "Step 6: Clicking TRACK ORDER button");
        profilePage.clickTrackOrder();
        test.log(Status.PASS, "✓ TRACK ORDER button clicked");
        Thread.sleep(2000);

        // Step 7: Verify TRACK ORDER page is displayed
        test.log(Status.INFO, "Step 7: Verifying TRACK ORDER page is displayed");
        boolean isTrackOrderPageDisplayed = profilePage.isTrackOrderPageDisplayed();
        if (!isTrackOrderPageDisplayed) {
            test.log(Status.FAIL, "TRACK ORDER page not displayed");
            Assert.fail("TRACK ORDER page validation failed");
        }
        test.log(Status.PASS, "✓ TRACK ORDER page is displayed successfully");

        // Step 8: Navigate back to Profile page for next test
        test.log(Status.INFO, "Step 8: Navigating back to Profile page");
        profilePage.navigateBackToProfile();
        test.log(Status.PASS, "✓ Navigated back to Profile page");

        test.log(Status.PASS, "Test PASSED: Track Order functionality verified");
    }

    /**
     * ✅ TEST CASE 2: MY ORDERS - SEND SAMPLE
     * 
     * Steps:
     * 1-4: Common navigation to Profile page (via ProfileNavigationHelper)
     * 5. Click MY ORDERS
     * 6. Click SEND SAMPLE button
     * 7. Verify SEND SAMPLE page is displayed
     */
    @Test(priority = 2)
    public void testMyOrders_SendSample() throws InterruptedException {
        test = extent.createTest("Test Case 2: My Orders - Send Sample");
        test.log(Status.INFO, "Testing Send Sample functionality");

        // Common navigation steps (Steps 1-4)
        ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);

        // Step 5: Click MY ORDERS
        test.log(Status.INFO, "Step 5: Clicking MY ORDERS");
        profilePage.clickMyOrders();
        test.log(Status.PASS, "✓ MY ORDERS clicked");
        Thread.sleep(2000);

        // Step 6: Click SEND SAMPLE button
        test.log(Status.INFO, "Step 6: Clicking SEND SAMPLE button");
        profilePage.clickSendSample();
        test.log(Status.PASS, "✓ SEND SAMPLE button clicked");
        Thread.sleep(2000);

        // Step 7: Verify SEND SAMPLE page is displayed
        test.log(Status.INFO, "Step 7: Verifying SEND SAMPLE page is displayed");
        boolean isSendSamplePageDisplayed = profilePage.isSendSamplePageDisplayed();
        if (!isSendSamplePageDisplayed) {
            test.log(Status.FAIL, "SEND SAMPLE page not displayed");
            Assert.fail("SEND SAMPLE page validation failed");
        }
        test.log(Status.PASS, "✓ SEND SAMPLE page is displayed successfully");

        // Step 8: Click MAIL SAMPLE
        test.log(Status.INFO, "Step 8: Clicking MAIL SAMPLE");
        profilePage.clickMailSample();
        test.log(Status.PASS, "✓ MAIL SAMPLE clicked");
        Thread.sleep(1000);

        // Step 9: Click CONFIRM SHIPMENT
        test.log(Status.INFO, "Step 9: Clicking CONFIRM SHIPMENT");
        profilePage.clickConfirmShipment();
        test.log(Status.PASS, "✓ CONFIRM SHIPMENT clicked");
        Thread.sleep(1000);

        // Step 10: Click RECEIVED AT LAB
        test.log(Status.INFO, "Step 10: Clicking RECEIVED AT LAB");
        profilePage.clickReceivedAtLab();
        test.log(Status.PASS, "✓ RECEIVED AT LAB clicked");
        Thread.sleep(1000);

        // Step 11: Click REPORT GENERATED
        test.log(Status.INFO, "Step 11: Clicking REPORT GENERATED");
        profilePage.clickReportGenerated();
        test.log(Status.PASS, "✓ REPORT GENERATED clicked");
        Thread.sleep(1000);

        // Step 12: Navigate back to Profile page for next test
        test.log(Status.INFO, "Step 12: Navigating back to Profile page");
        profilePage.navigateBackToProfile();
        test.log(Status.PASS, "✓ Navigated back to Profile page");

        test.log(Status.PASS, "Test PASSED: Send Sample functionality verified");
    }

    /**
     * ✅ TEST CASE 3: HELP & SUPPORT (4 SUB-SECTIONS)
     * 
     * This test verifies all 4 Help & Support sub-sections:
     * - Getting Started
     * - DNA Kit
     * - Subscription & Billing
     * - Troubleshooting
     * 
     * Each sub-section is tested with: Click → Verify → Back
     */
    @Test(priority = 3)
    public void testHelpSupport_AllSections() throws InterruptedException {
        test = extent.createTest("Test Case 3: Help & Support - All Sections");
        test.log(Status.INFO, "Testing all Help & Support sections");

        // Common navigation steps (Steps 1-4)
        ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);

        // ========== SUB-SECTION 1: GETTING STARTED ==========
        test.log(Status.INFO, "--- Testing GETTING STARTED section ---");

        // Click HELP & SUPPORT
        test.log(Status.INFO, "Step 5.1: Clicking HELP & SUPPORT");
        profilePage.clickHelpSupport();
        test.log(Status.PASS, "✓ HELP & SUPPORT clicked");
        Thread.sleep(2000);

        // Click GETTING STARTED
        test.log(Status.INFO, "Step 6.1: Clicking GETTING STARTED");
        profilePage.clickGettingStarted();
        test.log(Status.PASS, "✓ GETTING STARTED clicked");
        Thread.sleep(2000);

        // Verify GETTING STARTED page is displayed
        test.log(Status.INFO, "Step 7.1: Verifying GETTING STARTED page is displayed");
        boolean isGettingStartedDisplayed = profilePage.isGettingStartedPageDisplayed();
        if (!isGettingStartedDisplayed) {
            test.log(Status.FAIL, "GETTING STARTED page not displayed");
            Assert.fail("GETTING STARTED page validation failed");
        }
        test.log(Status.PASS, "✓ GETTING STARTED page is displayed");

        // Step 7.2: Click HOW DO I CREATE AN ACCOUNT? FAQ
        test.log(Status.INFO, "Step 7.2: Clicking HOW DO I CREATE AN ACCOUNT?");
        profilePage.clickHowToCreateAccount();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Step 7.3: Click DO I NEED TO DOWNLOAD AN APP TO ACCESS MY RESULTS? FAQ
        test.log(Status.INFO, "Step 7.3: Clicking DO I NEED TO DOWNLOAD AN APP?");
        profilePage.clickDoINeedApp();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Navigate back using Android system back
        test.log(Status.INFO, "Step 7.4: Navigating back to Help & Support menu");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Help & Support menu");
        Thread.sleep(1000);

        // ========== SUB-SECTION 2: DNA KIT ==========
        test.log(Status.INFO, "--- Testing DNA KIT section ---");

        // Click HELP & SUPPORT
        test.log(Status.INFO, "Step 5.2: Clicking HELP & SUPPORT");
        profilePage.clickHelpSupport();
        test.log(Status.PASS, "✓ HELP & SUPPORT clicked");
        Thread.sleep(2000);

        // Click DNA KIT
        test.log(Status.INFO, "Step 6.2: Clicking DNA KIT");
        profilePage.clickDnaKit();
        test.log(Status.PASS, "✓ DNA KIT clicked");
        Thread.sleep(2000);

        // Verify DNA KIT page is displayed
        test.log(Status.INFO, "Step 7.2: Verifying DNA KIT page is displayed");
        boolean isDnaKitDisplayed = profilePage.isDnaKitPageDisplayed();
        if (!isDnaKitDisplayed) {
            test.log(Status.FAIL, "DNA KIT page not displayed");
            Assert.fail("DNA KIT page validation failed");
        }
        test.log(Status.PASS, "✓ DNA KIT page is displayed");

        // Step 7.3: Click HOW LONG DOES IT TAKE TO RECEIVE MY DNA KIT? FAQ
        test.log(Status.INFO, "Step 7.3: Clicking HOW LONG DOES IT TAKE TO RECEIVE MY DNA KIT?");
        profilePage.clickHowLongToReceiveKit();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Step 7.4: Click HOW DO I ACTIVATE MY DNA KIT? FAQ
        test.log(Status.INFO, "Step 7.4: Clicking HOW DO I ACTIVATE MY DNA KIT?");
        profilePage.clickHowToActivateKit();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Step 7.5: Click WHAT SHOULD I DO IF MY DNA KIT IS DAMAGED OR MISSING? FAQ
        test.log(Status.INFO, "Step 7.5: Clicking WHAT SHOULD I DO IF MY DNA KIT IS DAMAGED OR MISSING?");
        profilePage.clickWhatToDoIfKitDamaged();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Navigate back using Android system back
        test.log(Status.INFO, "Step 7.6: Navigating back to Help & Support menu");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Help & Support menu");
        Thread.sleep(1000);

        // ========== SUB-SECTION 3: SUBSCRIPTION & BILLING ==========
        test.log(Status.INFO, "--- Testing SUBSCRIPTION & BILLING section ---");

        // Click HELP & SUPPORT
        test.log(Status.INFO, "Step 5.3: Clicking HELP & SUPPORT");
        profilePage.clickHelpSupport();
        test.log(Status.PASS, "✓ HELP & SUPPORT clicked");
        Thread.sleep(2000);

        // Click SUBSCRIPTION & BILLING
        test.log(Status.INFO, "Step 6.3: Clicking SUBSCRIPTION & BILLING");
        profilePage.clickSubscriptionBilling();
        test.log(Status.PASS, "✓ SUBSCRIPTION & BILLING clicked");
        Thread.sleep(2000);

        // Verify SUBSCRIPTION & BILLING page is displayed
        test.log(Status.INFO, "Step 7.3: Verifying SUBSCRIPTION & BILLING page is displayed");
        boolean isSubscriptionBillingDisplayed = profilePage.isSubscriptionBillingPageDisplayed();
        if (!isSubscriptionBillingDisplayed) {
            test.log(Status.FAIL, "SUBSCRIPTION & BILLING page not displayed");
            Assert.fail("SUBSCRIPTION & BILLING page validation failed");
        }
        test.log(Status.PASS, "✓ SUBSCRIPTION & BILLING page is displayed");

        // Step 7.7: Click WHAT PAYMENT METHODS ARE ACCEPTED? FAQ
        test.log(Status.INFO, "Step 7.7: Clicking WHAT PAYMENT METHODS ARE ACCEPTED?");
        profilePage.clickWhatPaymentMethodsAccepted();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Step 7.8: Click CAN I PAUSE OR CANCEL MY SUBSCRIPTION? FAQ
        test.log(Status.INFO, "Step 7.8: Clicking CAN I PAUSE OR CANCEL MY SUBSCRIPTION?");
        profilePage.clickCanIPauseOrCancelSub();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Step 7.9: Click WILL I BE NOTIFIED BEFORE MY SUBSCRIPTION RENEWS? FAQ
        test.log(Status.INFO, "Step 7.9: Clicking WILL I BE NOTIFIED BEFORE MY SUBSCRIPTION RENEWS?");
        profilePage.clickWillBeNotifiedBeforeRenewal();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Navigate back using Android system back
        test.log(Status.INFO, "Step 7.10: Navigating back to Help & Support menu");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Help & Support menu");
        Thread.sleep(1000);

        // ========== SUB-SECTION 4: TROUBLESHOOTING ==========
        test.log(Status.INFO, "--- Testing TROUBLESHOOTING section ---");

        // Click HELP & SUPPORT
        test.log(Status.INFO, "Step 5.4: Clicking HELP & SUPPORT");
        profilePage.clickHelpSupport();
        test.log(Status.PASS, "✓ HELP & SUPPORT clicked");
        Thread.sleep(2000);

        // Click TROUBLESHOOTING
        test.log(Status.INFO, "Step 6.4: Clicking TROUBLESHOOTING");
        profilePage.clickTroubleshooting();
        test.log(Status.PASS, "✓ TROUBLESHOOTING clicked");
        Thread.sleep(2000);

        // Verify TROUBLESHOOTING page is displayed
        test.log(Status.INFO, "Step 7.4: Verifying TROUBLESHOOTING page is displayed");
        boolean isTroubleshootingDisplayed = profilePage.isTroubleshootingPageDisplayed();
        if (!isTroubleshootingDisplayed) {
            test.log(Status.FAIL, "TROUBLESHOOTING page not displayed");
            Assert.fail("TROUBLESHOOTING page validation failed");
        }
        test.log(Status.PASS, "✓ TROUBLESHOOTING page is displayed");

        // Step 7.11: Click I CAN’T LOG INTO MY ACCOUNT. WHAT SHOULD I DO? FAQ
        test.log(Status.INFO, "Step 7.11: Clicking I CAN’T LOG INTO MY ACCOUNT. WHAT SHOULD I DO?");
        profilePage.clickCantLogIntoAccount();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Step 7.12: Click MY RESULTS ARE DELAYED. HOW CAN I CHECK THE STATUS? FAQ
        test.log(Status.INFO, "Step 7.12: Clicking MY RESULTS ARE DELAYED. HOW CAN I CHECK THE STATUS?");
        profilePage.clickResultsDelayed();
        test.log(Status.PASS, "✓ FAQ clicked");
        Thread.sleep(1000);

        // Navigate back using Android system back
        test.log(Status.INFO, "Step 7.13: Navigating back to Help & Support menu");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Help & Support menu");
        Thread.sleep(1000);

        // Navigate back to Profile page for next test
        test.log(Status.INFO, "Step 9: Navigating back to Profile page");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Profile page");

        test.log(Status.PASS, "Test PASSED: All Help & Support sections verified successfully");
    }

    /**
     * ✅ TEST CASE 4: LEGAL INFORMATION (3 SUB-SECTIONS)
     * 
     * This test verifies all 3 Legal Information sub-sections:
     * - T&C (Terms & Conditions)
     * - Privacy Policy
     * - Open Content (Open Source Content)
     * 
     * Each sub-section is tested with: Click → Verify → Back
     */
    @Test(priority = 4)
    public void testLegalInformation_AllSections() throws InterruptedException {
        test = extent.createTest("Test Case 4: Legal Information - All Sections");
        test.log(Status.INFO, "Testing all Legal Information sections");

        // Common navigation steps (Steps 1-4)
        ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);

        // ========== SUB-SECTION 1: T&C (TERMS & CONDITIONS) ==========
        test.log(Status.INFO, "--- Testing T&C section ---");

        // Click LEGAL INFORMATION
        test.log(Status.INFO, "Step 5.1: Clicking LEGAL INFORMATION");
        profilePage.clickLegalInformation();
        test.log(Status.PASS, "✓ LEGAL INFORMATION clicked");
        Thread.sleep(2000);

        // Click T&C
        test.log(Status.INFO, "Step 6.1: Clicking T&C");
        profilePage.clickTC();
        test.log(Status.PASS, "✓ T&C clicked");
        Thread.sleep(2000);

        // Verify TERMS & CONDITIONS page is displayed
        test.log(Status.INFO, "Step 7.1: Verifying TERMS & CONDITIONS page is displayed");
        boolean isTermsConditionsDisplayed = profilePage.isTermsConditionsPageDisplayed();
        if (!isTermsConditionsDisplayed) {
            test.log(Status.FAIL, "TERMS & CONDITIONS page not displayed");
            Assert.fail("TERMS & CONDITIONS page validation failed");
        }
        test.log(Status.PASS, "✓ TERMS & CONDITIONS page is displayed");

        // Navigate back using Android system back
        test.log(Status.INFO, "Step 8.1: Navigating back to Legal Information menu");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Legal Information menu");
        Thread.sleep(1000);

        // ========== SUB-SECTION 2: PRIVACY POLICY ==========
        test.log(Status.INFO, "--- Testing PRIVACY POLICY section ---");

        // Click LEGAL INFORMATION
        test.log(Status.INFO, "Step 5.2: Clicking LEGAL INFORMATION");
        profilePage.clickLegalInformation();
        test.log(Status.PASS, "✓ LEGAL INFORMATION clicked");
        Thread.sleep(2000);

        // Click PRIVACY POLICY
        test.log(Status.INFO, "Step 6.2: Clicking PRIVACY POLICY");
        profilePage.clickPrivacyPolicy();
        test.log(Status.PASS, "✓ PRIVACY POLICY clicked");
        Thread.sleep(2000);

        // Verify PRIVACY POLICY page is displayed
        test.log(Status.INFO, "Step 7.2: Verifying PRIVACY POLICY page is displayed");
        boolean isPrivacyPolicyDisplayed = profilePage.isPrivacyPolicyPageDisplayed();
        if (!isPrivacyPolicyDisplayed) {
            test.log(Status.FAIL, "PRIVACY POLICY page not displayed");
            Assert.fail("PRIVACY POLICY page validation failed");
        }
        test.log(Status.PASS, "✓ PRIVACY POLICY page is displayed");

        // Navigate back using Android system back
        test.log(Status.INFO, "Step 8.2: Navigating back to Legal Information menu");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Legal Information menu");
        Thread.sleep(1000);

        // ========== SUB-SECTION 3: OPEN CONTENT (OPEN SOURCE CONTENT) ==========
        test.log(Status.INFO, "--- Testing OPEN CONTENT section ---");

        // Click LEGAL INFORMATION
        test.log(Status.INFO, "Step 5.3: Clicking LEGAL INFORMATION");
        profilePage.clickLegalInformation();
        test.log(Status.PASS, "✓ LEGAL INFORMATION clicked");
        Thread.sleep(2000);

        // Click OPEN CONTENT
        test.log(Status.INFO, "Step 6.3: Clicking OPEN CONTENT");
        profilePage.clickOpenContent();
        test.log(Status.PASS, "✓ OPEN CONTENT clicked");
        Thread.sleep(2000);

        // Verify OPEN SOURCE CONTENT page is displayed
        test.log(Status.INFO, "Step 7.3: Verifying OPEN SOURCE CONTENT page is displayed");
        boolean isOpenSourceContentDisplayed = profilePage.isOpenSourceContentPageDisplayed();
        if (!isOpenSourceContentDisplayed) {
            test.log(Status.FAIL, "OPEN SOURCE CONTENT page not displayed");
            Assert.fail("OPEN SOURCE CONTENT page validation failed");
        }
        test.log(Status.PASS, "✓ OPEN SOURCE CONTENT page is displayed");

        // Navigate back using Android system back
        test.log(Status.INFO, "Step 8.3: Navigating back to Legal Information menu");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Legal Information menu");
        Thread.sleep(1000);

        // Navigate back to Profile page for next test
        test.log(Status.INFO, "Step 9: Navigating back to Profile page");
        profilePage.navigateBack();
        test.log(Status.PASS, "✓ Navigated back to Profile page");

        test.log(Status.PASS, "Test PASSED: All Legal Information sections verified successfully");
    }

    /**
     * ✅ TEST CASE 5: LOGOUT FUNCTIONALITY
     * 
     * Steps:
     * 1-4: Common navigation to Profile page (via ProfileNavigationHelper)
     * 5. Click LOG OUT
     * 6. Click NO button (cancel logout)
     * 7. Click LOG OUT again
     * 8. Click YES button (confirm logout)
     * 9. Wait and verify user is logged out (Sign In page displayed)
     */
    @Test(priority = 5)
    public void testLogout() throws InterruptedException {
        test = extent.createTest("Test Case 5: Logout Functionality");
        test.log(Status.INFO, "Testing Logout functionality");

        // Common navigation steps (Steps 1-4)
        ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);

        // Step 5: Click LOG OUT (first time - will click NO)
        test.log(Status.INFO, "Step 5: Clicking LOG OUT");
        profilePage.clickLogout();
        test.log(Status.PASS, "✓ LOG OUT clicked");
        Thread.sleep(2000);

        // Step 6: Click NO button (cancel logout)
        test.log(Status.INFO, "Step 6: Clicking NO button to cancel logout");
        profilePage.clickNo();
        test.log(Status.PASS, "✓ NO button clicked - Logout cancelled");
        Thread.sleep(2000);

        // Step 7: Click LOG OUT again (second time - will click YES)
        test.log(Status.INFO, "Step 7: Clicking LOG OUT again");
        profilePage.clickLogout();
        test.log(Status.PASS, "✓ LOG OUT clicked");
        Thread.sleep(2000);

        // Step 8: Click YES button (confirm logout)
        test.log(Status.INFO, "Step 8: Clicking YES button to confirm logout");
        profilePage.clickYes();
        test.log(Status.PASS, "✓ YES button clicked - Logout confirmed");
        Thread.sleep(3000); // Wait for logout to complete

        // Step 9: Verify user is logged out (Sign In page is displayed)
        test.log(Status.INFO, "Step 9: Verifying user is logged out");
        boolean isSignInPageDisplayed = profilePage.isSignInPageDisplayed();
        if (!isSignInPageDisplayed) {
            test.log(Status.FAIL, "Sign In page not displayed - Logout failed");
            Assert.fail("Logout validation failed - User not redirected to Sign In page");
        }
        test.log(Status.PASS, "✓ Sign In page is displayed - User successfully logged out");
        test.log(Status.PASS, "Test PASSED: Logout functionality verified successfully");
    }
}