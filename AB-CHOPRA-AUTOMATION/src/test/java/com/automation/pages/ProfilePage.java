package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public ProfilePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ==================== MAIN PROFILE LOCATORS ====================
    private final String profileHeadingXpath = "//android.view.View[@content-desc='PROFILE']";
    private final String accountButtonXpath = "//android.widget.Button[@content-desc='ACCOUNT']";
    private final String myOrdersXpath = "//android.view.View[@content-desc='MY ORDERS']";
    private final String helpSupportXpath = "//android.view.View[@content-desc='HELP & SUPPORT']";
    private final String legalInformationXpath = "//android.view.View[@content-desc='LEGAL INFORMATION']";
    private final String logoutXpath = "//android.view.View[@content-desc='LOG OUT']";

    // ==================== MY ORDERS LOCATORS ====================
    private final String trackOrderXpath = "//android.view.View[@content-desc='TRACK ORDER']";
    private final String sendSampleXpath = "//android.view.View[@content-desc='SEND SAMPLE']";
    private final String mailSampleXpath = "//android.widget.ImageView[@content-desc='MAIL SAMPLE']";
    private final String confirmShipmentXpath = "(//android.widget.ImageView[@content-desc='CONFIRM SHIPMENT'])";
    private final String receivedAtLabXpath = "(//android.widget.ImageView[@content-desc='RECEIVED AT LAB'])";
    private final String reportGeneratedXpath = "(//android.widget.ImageView[@content-desc='REPORT GENERATED'])";

    // ==================== HELP & SUPPORT LOCATORS ====================
    private final String gettingStartedXpath = "//android.view.View[@content-desc='GETTING STARTED']";
    private final String dnaKitXpath = "//android.view.View[@content-desc='DNA KIT']";
    private final String subscriptionBillingXpath = "//android.view.View[@content-desc='SUBSCRIPTION & BILLING']";
    private final String troubleshootingXpath = "//android.view.View[@content-desc='TROUBLESHOOTING']";
    private final String howToCreateAccountXpath = "//android.widget.ImageView[@content-desc='HOW DO I CREATE AN ACCOUNT?']";
    private final String doINeedAppXpath = "(//android.widget.ImageView[@content-desc='DO I NEED TO DOWNLOAD AN APP TO ACCESS MY RESULTS?'])";
    private final String howLongToReceiveKitXpath = "//android.widget.ImageView[@content-desc='HOW LONG DOES IT TAKE TO RECEIVE MY DNA KIT?']";
    private final String howToActivateKitXpath = "//android.widget.ImageView[@content-desc='HOW DO I ACTIVATE MY DNA KIT?']";
    private final String whatToDoIfKitDamagedXpath = "//android.widget.ImageView[@content-desc='WHAT SHOULD I DO IF MY DNA KIT IS DAMAGED OR MISSING?']";
    private final String whatPaymentMethodsAcceptedXpath = "//android.widget.ImageView[@content-desc='WHAT PAYMENT METHODS ARE ACCEPTED?']";
    private final String canIPauseOrCancelSubXpath = "//android.widget.ImageView[@content-desc='CAN I PAUSE OR CANCEL MY SUBSCRIPTION?']";
    private final String willBeNotifiedBeforeRenewalXpath = "//android.widget.ImageView[@content-desc='WILL I BE NOTIFIED BEFORE MY SUBSCRIPTION RENEWS?']";
    private final String cantLogIntoAccountXpath = "//android.widget.ImageView[@content-desc='I CAN’T LOG INTO MY ACCOUNT. WHAT SHOULD I DO?']";
    private final String resultsDelayedXpath = "//android.widget.ImageView[@content-desc='MY RESULTS ARE DELAYED. HOW CAN I CHECK THE STATUS?']";

    // ==================== LEGAL INFORMATION LOCATORS ====================
    private final String tcXpath = "//android.view.View[@content-desc='T&C']";
    private final String privacyPolicyXpath = "//android.view.View[@content-desc='PRIVACY POLICY']";
    private final String openContentXpath = "//android.view.View[@content-desc='OPEN CONTENT']";
    private final String termsConditionsHeadingXpath = "//android.view.View[@content-desc='TERMS & CONDITIONS']";
    private final String privacyPolicyHeadingXpath = "//android.view.View[@content-desc='PRIVACY POLICY']";
    private final String openSourceContentHeadingXpath = "//android.view.View[@content-desc='OPEN SOURCE CONTENT']";

    // ==================== LOGOUT LOCATORS ====================
    private final String noButtonXpath = "//android.view.View[@content-desc='NO']";
    private final String yesButtonXpath = "//android.view.View[@content-desc='YES']";
    private final String signInHeadingXpath = "//android.view.View[@content-desc='SIGN IN']";

    // ==================== COMMON METHODS ====================
    // Note: No UI-based back button locator needed - using Android system back
    // instead

    // ==================== MAIN PROFILE METHODS ====================

    /**
     * Click the ACCOUNT button to navigate to Edit Profile page
     */
    public void clickAccount() {
        try {
            WebElement accountBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(accountButtonXpath)));
            accountBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("ACCOUNT button not found on Profile page", e);
        }
    }

    /**
     * Click the MY ORDERS option
     */
    public void clickMyOrders() {
        try {
            WebElement myOrdersBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(myOrdersXpath)));
            myOrdersBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("MY ORDERS option not found on Profile page", e);
        }
    }

    /**
     * Click the HELP & SUPPORT option
     */
    public void clickHelpSupport() {
        try {
            WebElement helpBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(helpSupportXpath)));
            helpBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("HELP & SUPPORT option not found on Profile page", e);
        }
    }

    /**
     * Click the LEGAL INFORMATION option
     */
    public void clickLegalInformation() {
        try {
            WebElement legalBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(legalInformationXpath)));
            legalBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("LEGAL INFORMATION option not found on Profile page", e);
        }
    }

    /**
     * Click the LOG OUT option
     */
    public void clickLogout() {
        try {
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(logoutXpath)));
            logoutBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("LOG OUT option not found on Profile page", e);
        }
    }

    /**
     * Check if Profile page is displayed
     */
    public boolean isProfilePageDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(profileHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== MY ORDERS METHODS ====================

    /**
     * Click TRACK ORDER button
     */
    public void clickTrackOrder() {
        try {
            WebElement trackOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(trackOrderXpath)));
            trackOrderBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("TRACK ORDER button not found", e);
        }
    }

    /**
     * Click SEND SAMPLE button
     */
    public void clickSendSample() {
        try {
            WebElement sendSampleBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sendSampleXpath)));
            sendSampleBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("SEND SAMPLE button not found", e);
        }
    }

    /**
     * Click MAIL SAMPLE button
     */
    public void clickMailSample() {
        try {
            WebElement mailSampleBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(mailSampleXpath)));
            mailSampleBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("MAIL SAMPLE button not found", e);
        }
    }

    /**
     * Click CONFIRM SHIPMENT button
     */
    public void clickConfirmShipment() {
        try {
            WebElement confirmShipmentBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(confirmShipmentXpath)));
            confirmShipmentBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("CONFIRM SHIPMENT button not found", e);
        }
    }

    /**
     * Click RECEIVED AT LAB button
     */
    public void clickReceivedAtLab() {
        try {
            WebElement receivedAtLabBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(receivedAtLabXpath)));
            receivedAtLabBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("RECEIVED AT LAB button not found", e);
        }
    }

    /**
     * Click REPORT GENERATED button
     */
    public void clickReportGenerated() {
        try {
            WebElement reportGeneratedBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(reportGeneratedXpath)));
            reportGeneratedBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("REPORT GENERATED button not found", e);
        }
    }

    /**
     * Verify TRACK ORDER page is displayed
     */
    public boolean isTrackOrderPageDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(trackOrderXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify SEND SAMPLE page is displayed
     */
    public boolean isSendSamplePageDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sendSampleXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== HELP & SUPPORT METHODS ====================

    /**
     * Click GETTING STARTED option
     */
    public void clickGettingStarted() {
        try {
            WebElement gettingStartedBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(gettingStartedXpath)));
            gettingStartedBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("GETTING STARTED option not found", e);
        }
    }

    /**
     * Click DNA KIT option
     */
    public void clickDnaKit() {
        try {
            WebElement dnaKitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dnaKitXpath)));
            dnaKitBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("DNA KIT option not found", e);
        }
    }

    /**
     * Click SUBSCRIPTION & BILLING option
     */
    public void clickSubscriptionBilling() {
        try {
            WebElement subscriptionBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(subscriptionBillingXpath)));
            subscriptionBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("SUBSCRIPTION & BILLING option not found", e);
        }
    }

    /**
     * Click TROUBLESHOOTING option
     */
    public void clickTroubleshooting() {
        try {
            WebElement troubleshootingBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(troubleshootingXpath)));
            troubleshootingBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("TROUBLESHOOTING option not found", e);
        }
    }

    /**
     * Click HOW DO I CREATE AN ACCOUNT? option
     */
    public void clickHowToCreateAccount() {
        try {
            WebElement howToBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(howToCreateAccountXpath)));
            howToBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("HOW DO I CREATE AN ACCOUNT? button not found", e);
        }
    }

    /**
     * Click DO I NEED TO DOWNLOAD AN APP... option
     */
    public void clickDoINeedApp() {
        try {
            WebElement doINeedAppBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(doINeedAppXpath)));
            doINeedAppBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("DO I NEED TO DOWNLOAD AN APP? button not found", e);
        }
    }

    /**
     * Click HOW LONG DOES IT TAKE TO RECEIVE MY DNA KIT? option
     */
    public void clickHowLongToReceiveKit() {
        try {
            WebElement howLongBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(howLongToReceiveKitXpath)));
            howLongBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("HOW LONG TO RECEIVE KIT button not found", e);
        }
    }

    /**
     * Click HOW DO I ACTIVATE MY DNA KIT? option
     */
    public void clickHowToActivateKit() {
        try {
            WebElement activateBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(howToActivateKitXpath)));
            activateBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("HOW TO ACTIVATE KIT button not found", e);
        }
    }

    /**
     * Click WHAT SHOULD I DO IF MY DNA KIT IS DAMAGED OR MISSING? option
     */
    public void clickWhatToDoIfKitDamaged() {
        try {
            WebElement damagedBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(whatToDoIfKitDamagedXpath)));
            damagedBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("WHAT TO DO IF KIT DAMAGED button not found", e);
        }
    }

    /**
     * Click WHAT PAYMENT METHODS ARE ACCEPTED? option
     */
    public void clickWhatPaymentMethodsAccepted() {
        try {
            WebElement paymentBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(whatPaymentMethodsAcceptedXpath)));
            paymentBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("WHAT PAYMENT METHODS ARE ACCEPTED button not found", e);
        }
    }

    /**
     * Click CAN I PAUSE OR CANCEL MY SUBSCRIPTION? option
     */
    public void clickCanIPauseOrCancelSub() {
        try {
            WebElement pauseBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(canIPauseOrCancelSubXpath)));
            pauseBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("CAN I PAUSE OR CANCEL SUBSCRIPTION button not found", e);
        }
    }

    /**
     * Click WILL I BE NOTIFIED BEFORE MY SUBSCRIPTION RENEWS? option
     */
    public void clickWillBeNotifiedBeforeRenewal() {
        try {
            WebElement notifyBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(willBeNotifiedBeforeRenewalXpath)));
            notifyBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("WILL BE NOTIFIED BEFORE RENEWAL button not found", e);
        }
    }

    /**
     * Click I CAN'T LOG INTO MY ACCOUNT. WHAT SHOULD I DO? option
     */
    public void clickCantLogIntoAccount() {
        try {
            WebElement cantLogBtn = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(cantLogIntoAccountXpath)));
            cantLogBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("I CAN'T LOG INTO MY ACCOUNT button not found", e);
        }
    }

    /**
     * Click MY RESULTS ARE DELAYED. HOW CAN I CHECK THE STATUS? option
     */
    public void clickResultsDelayed() {
        try {
            WebElement delayedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resultsDelayedXpath)));
            delayedBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("MY RESULTS ARE DELAYED button not found", e);
        }
    }

    /**
     * Verify GETTING STARTED page is displayed
     */
    public boolean isGettingStartedPageDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(gettingStartedXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify DNA KIT page is displayed
     */
    public boolean isDnaKitPageDisplayed() {
        try {
            WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dnaKitXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify SUBSCRIPTION & BILLING page is displayed
     */
    public boolean isSubscriptionBillingPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(subscriptionBillingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify TROUBLESHOOTING page is displayed
     */
    public boolean isTroubleshootingPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(troubleshootingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== LEGAL INFORMATION METHODS ====================

    /**
     * Click T&C option
     */
    public void clickTC() {
        try {
            WebElement tcBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tcXpath)));
            tcBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("T&C option not found", e);
        }
    }

    /**
     * Click PRIVACY POLICY option
     */
    public void clickPrivacyPolicy() {
        try {
            WebElement privacyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(privacyPolicyXpath)));
            privacyBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("PRIVACY POLICY option not found", e);
        }
    }

    /**
     * Click OPEN CONTENT option
     */
    public void clickOpenContent() {
        try {
            WebElement openContentBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(openContentXpath)));
            openContentBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("OPEN CONTENT option not found", e);
        }
    }

    /**
     * Verify TERMS & CONDITIONS page is displayed
     */
    public boolean isTermsConditionsPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(termsConditionsHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify PRIVACY POLICY page is displayed
     */
    public boolean isPrivacyPolicyPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(privacyPolicyHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify OPEN SOURCE CONTENT page is displayed
     */
    public boolean isOpenSourceContentPageDisplayed() {
        try {
            WebElement heading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(openSourceContentHeadingXpath)));
            return heading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== LOGOUT METHODS ====================

    /**
     * Click NO button in logout confirmation dialog
     */
    public void clickNo() {
        try {
            WebElement noBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(noButtonXpath)));
            noBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("NO button not found in logout dialog", e);
        }
    }

    /**
     * Click YES button in logout confirmation dialog
     */
    public void clickYes() {
        try {
            WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(yesButtonXpath)));
            yesBtn.click();
        } catch (TimeoutException e) {
            throw new RuntimeException("YES button not found in logout dialog", e);
        }
    }

    /**
     * Verify user is logged out (Sign In page is displayed)
     */
    public boolean isSignInPageDisplayed() {
        try {
            WebElement signInHeading = wait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(signInHeadingXpath)));
            return signInHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ==================== NAVIGATION METHODS ====================

    /**
     * Navigate back using Android system back button
     * This is more reliable than clicking UI back button
     */
    public void navigateBack() {
        try {
            driver.navigate().back();
            Thread.sleep(1000); // Brief wait for navigation to complete
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate back", e);
        }
    }

    /**
     * Navigate back to Profile page from any sub-page
     * Keeps pressing back until Profile page is displayed
     * 
     * @param maxAttempts Maximum number of back presses (default: 5)
     */
    public void navigateBackToProfile(int maxAttempts) {
        int attempts = 0;
        while (!isProfilePageDisplayed() && attempts < maxAttempts) {
            navigateBack();
            attempts++;
        }

        if (!isProfilePageDisplayed()) {
            throw new RuntimeException("Failed to navigate back to Profile page after " + maxAttempts + " attempts");
        }
    }

    /**
     * Navigate back to Profile page (uses default max attempts of 5)
     */
    public void navigateBackToProfile() {
        navigateBackToProfile(5);
    }

    /**
     * Navigate back to Home page from Profile or any sub-page
     * 
     * @param maxAttempts Maximum number of back presses (default: 10)
     */
    public void navigateBackToHome(int maxAttempts) {
        int attempts = 0;

        // Keep pressing back until we reach home page
        while (attempts < maxAttempts) {
            try {
                // Check if we're on home page by looking for DAILY PRIORITY heading
                WebElement homeHeading = driver.findElement(
                        By.xpath("//android.view.View[@content-desc='DAILY PRIORITY']"));
                if (homeHeading.isDisplayed()) {
                    return; // Successfully reached home page
                }
            } catch (Exception e) {
                // Not on home page yet, continue
            }

            navigateBack();
            attempts++;
        }

        throw new RuntimeException("Failed to navigate back to Home page after " + maxAttempts + " attempts");
    }

    /**
     * Navigate back to Home page (uses default max attempts of 10)
     */
    public void navigateBackToHome() {
        navigateBackToHome(10);
    }
}
