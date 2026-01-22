# Profile Test Suite - Complete Implementation

## Overview
This document provides a complete overview of the Profile test suite implementation, including all 5 test cases with the ProfileNavigationHelper module integration.

## Files Created/Modified

### 1. **ProfilePage.java** (Modified)
**Location**: `src/test/java/com/automation/pages/ProfilePage.java`

**Added Locators**:
- My Orders: `MY ORDERS`, `TRACK ORDER`, `SEND SAMPLE`
- Help & Support: `HELP & SUPPORT`, `GETTING STARTED`, `DNA KIT`, `SUBSCRIPTION & BILLING`, `TROUBLESHOOTING`
- Legal Information: `LEGAL INFORMATION`, `T&C`, `PRIVACY POLICY`, `OPEN CONTENT`
- Logout: `LOG OUT`, `NO`, `YES`, `SIGN IN` (for verification)
- Common: Back button

**Added Methods** (30+ new methods):
- My Orders: `clickMyOrders()`, `clickTrackOrder()`, `clickSendSample()`, verification methods
- Help & Support: Click and verify methods for all 4 sub-sections
- Legal Information: Click and verify methods for all 3 sub-sections
- Logout: `clickLogout()`, `clickNo()`, `clickYes()`, `isSignInPageDisplayed()`
- Common: `clickBack()`

### 2. **ProfileTest.java** (Created)
**Location**: `src/test/java/com/automation/tests/ProfileTest.java`

**Contains 5 Test Cases**:
1. `testMyOrders_TrackOrder()` - Test Case 1
2. `testMyOrders_SendSample()` - Test Case 2
3. `testHelpSupport_AllSections()` - Test Case 3 (4 sub-sections)
4. `testLegalInformation_AllSections()` - Test Case 4 (3 sub-sections)
5. `testLogout()` - Test Case 5

## Test Cases Breakdown

### Test Case 1: My Orders - Track Order

**Flow**:
```
1-4. Navigate to Profile (via ProfileNavigationHelper)
5. Click MY ORDERS
6. Click TRACK ORDER button
7. Verify TRACK ORDER page is displayed
```

**XPaths Used**:
- MY ORDERS: `//android.view.View[@content-desc="MY ORDERS"]`
- TRACK ORDER: `//android.view.View[@content-desc="TRACK ORDER"]`

**Validations**:
- ✅ TRACK ORDER page heading is visible

---

### Test Case 2: My Orders - Send Sample

**Flow**:
```
1-4. Navigate to Profile (via ProfileNavigationHelper)
5. Click MY ORDERS
6. Click SEND SAMPLE button
7. Verify SEND SAMPLE page is displayed
```

**XPaths Used**:
- MY ORDERS: `//android.view.View[@content-desc="MY ORDERS"]`
- SEND SAMPLE: `//android.view.View[@content-desc="SEND SAMPLE"]`

**Validations**:
- ✅ SEND SAMPLE page heading is visible

---

### Test Case 3: Help & Support - All Sections

**Flow** (4 sub-sections, each follows the same pattern):

#### Sub-section 1: Getting Started
```
1-4. Navigate to Profile (via ProfileNavigationHelper)
5. Click HELP & SUPPORT
6. Click GETTING STARTED
7. Verify GETTING STARTED page is displayed
8. Click back button
```

#### Sub-section 2: DNA Kit
```
5. Click HELP & SUPPORT
6. Click DNA KIT
7. Verify DNA KIT page is displayed
8. Click back button
```

#### Sub-section 3: Subscription & Billing
```
5. Click HELP & SUPPORT
6. Click SUBSCRIPTION & BILLING
7. Verify SUBSCRIPTION & BILLING page is displayed
8. Click back button
```

#### Sub-section 4: Troubleshooting
```
5. Click HELP & SUPPORT
6. Click TROUBLESHOOTING
7. Verify TROUBLESHOOTING page is displayed
8. Click back button
```

**XPaths Used**:
- HELP & SUPPORT: `//android.view.View[@content-desc="HELP & SUPPORT"]`
- GETTING STARTED: `//android.view.View[@content-desc="GETTING STARTED"]`
- DNA KIT: `//android.view.View[@content-desc="DNA KIT"]`
- SUBSCRIPTION & BILLING: `//android.view.View[@content-desc="SUBSCRIPTION & BILLING"]`
- TROUBLESHOOTING: `//android.view.View[@content-desc="TROUBLESHOOTING"]`
- Back button: `//android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.ImageView`

**Validations**:
- ✅ Each section's heading is visible after navigation
- ✅ Back button returns to Help & Support menu

---

### Test Case 4: Legal Information - All Sections

**Flow** (3 sub-sections, each follows the same pattern):

#### Sub-section 1: T&C (Terms & Conditions)
```
1-4. Navigate to Profile (via ProfileNavigationHelper)
5. Click LEGAL INFORMATION
6. Click T&C
7. Verify TERMS & CONDITIONS page is displayed
8. Click back button
```

#### Sub-section 2: Privacy Policy
```
5. Click LEGAL INFORMATION
6. Click PRIVACY POLICY
7. Verify PRIVACY POLICY page is displayed
8. Click back button
```

#### Sub-section 3: Open Content (Open Source Content)
```
5. Click LEGAL INFORMATION
6. Click OPEN CONTENT
7. Verify OPEN SOURCE CONTENT page is displayed
8. Click back button
```

**XPaths Used**:
- LEGAL INFORMATION: `//android.view.View[@content-desc="LEGAL INFORMATION"]`
- T&C: `//android.view.View[@content-desc="T&C"]`
- PRIVACY POLICY: `//android.view.View[@content-desc="PRIVACY POLICY"]`
- OPEN CONTENT: `//android.view.View[@content-desc="OPEN CONTENT"]`
- TERMS & CONDITIONS (heading): `//android.view.View[@content-desc="TERMS & CONDITIONS"]`
- PRIVACY POLICY (heading): `//android.view.View[@content-desc="PRIVACY POLICY"]`
- OPEN SOURCE CONTENT (heading): `//android.view.View[@content-desc="OPEN SOURCE CONTENT"]`
- Back button: Same as Help & Support

**Validations**:
- ✅ Each section's content page heading is visible
- ✅ Back button returns to Legal Information menu

---

### Test Case 5: Logout Functionality

**Flow**:
```
1-4. Navigate to Profile (via ProfileNavigationHelper)
5. Click LOG OUT
6. Click NO button (cancel logout)
7. Click LOG OUT again
8. Click YES button (confirm logout)
9. Wait and verify Sign In page is displayed
```

**XPaths Used**:
- LOG OUT: `//android.view.View[@content-desc="LOG OUT"]`
- NO button: `//android.view.View[@content-desc="NO"]`
- YES button: `//android.view.View[@content-desc="YES"]`
- SIGN IN (verification): `//android.view.View[@content-desc="SIGN IN"]`

**Validations**:
- ✅ NO button cancels logout (stays on Profile page)
- ✅ YES button confirms logout
- ✅ User is redirected to Sign In page after logout

---

## Common Navigation Steps (Steps 1-4)

All 5 test cases use the **ProfileNavigationHelper** module for common navigation:

```java
ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
```

This single line handles:
1. ✅ Verify DAILY PRIORITY heading on home page
2. ✅ Click Wellbeing Dashboard (if not already there)
3. ✅ Click PROFILE button
4. ✅ Verify Profile page is displayed

**Benefits**:
- No code duplication
- Consistent logging across all tests
- Easy maintenance - update once, fixes all tests
- Clean, readable test code

---

## Extent Report Logging

All tests include comprehensive logging:

### Status Levels Used:
- **INFO**: Step descriptions and actions being performed
- **PASS**: Successful validations and completions
- **FAIL**: Failed validations with detailed error messages

### Example Log Output:
```
INFO: Step 1: Verifying DAILY PRIORITY heading on home page
PASS: ✓ DAILY PRIORITY heading is displayed on home page
INFO: Step 2: Clicking Wellbeing Dashboard
PASS: ✓ Wellbeing Dashboard clicked
INFO: Step 3: Clicking PROFILE button
PASS: ✓ PROFILE button clicked
INFO: Step 4: Verifying Profile page is displayed
PASS: ✓ Profile page is displayed successfully
INFO: Step 5: Clicking MY ORDERS
PASS: ✓ MY ORDERS clicked
INFO: Step 6: Clicking TRACK ORDER button
PASS: ✓ TRACK ORDER button clicked
INFO: Step 7: Verifying TRACK ORDER page is displayed
PASS: ✓ TRACK ORDER page is displayed successfully
PASS: Test PASSED: Track Order functionality verified
```

---

## Running the Tests

### Run All Profile Tests:
```bash
mvn test -Dtest=ProfileTest
```

### Run Individual Test Cases:
```bash
# Test Case 1: Track Order
mvn test -Dtest=ProfileTest#testMyOrders_TrackOrder

# Test Case 2: Send Sample
mvn test -Dtest=ProfileTest#testMyOrders_SendSample

# Test Case 3: Help & Support
mvn test -Dtest=ProfileTest#testHelpSupport_AllSections

# Test Case 4: Legal Information
mvn test -Dtest=ProfileTest#testLegalInformation_AllSections

# Test Case 5: Logout
mvn test -Dtest=ProfileTest#testLogout
```

---

## Test Execution Flow

```
┌─────────────────────────────────────────────────────────────────┐
│                    ProfileTest Execution                         │
└─────────────────────────────────────────────────────────────────┘

For Each Test Case:
  │
  ├─→ @BeforeMethod (BaseTest)
  │   ├─ Initialize Appium driver
  │   └─ Launch app
  │
  ├─→ Test Method Execution
  │   ├─ Create ExtentTest
  │   ├─ Call ProfileNavigationHelper.navigateToProfile()
  │   │   ├─ Verify home page
  │   │   ├─ Click Wellbeing Dashboard
  │   │   ├─ Click PROFILE
  │   │   └─ Verify Profile page
  │   ├─ Execute test-specific steps
  │   ├─ Perform validations
  │   └─ Log results to Extent Report
  │
  └─→ @AfterMethod (BaseTest)
      ├─ Capture screenshot (if failed)
      ├─ Terminate app
      └─ Quit driver
```

---

## Test Coverage

### Features Covered:
- ✅ My Orders (2 options)
  - Track Order
  - Send Sample
- ✅ Help & Support (4 sections)
  - Getting Started
  - DNA Kit
  - Subscription & Billing
  - Troubleshooting
- ✅ Legal Information (3 sections)
  - Terms & Conditions
  - Privacy Policy
  - Open Source Content
- ✅ Logout (with confirmation dialog)
  - Cancel logout (NO button)
  - Confirm logout (YES button)

### Total Test Steps:
- **Test Case 1**: 7 steps
- **Test Case 2**: 7 steps
- **Test Case 3**: 20 steps (4 sections × 5 steps each)
- **Test Case 4**: 15 steps (3 sections × 5 steps each)
- **Test Case 5**: 9 steps

**Total**: 58 automated test steps across 5 test cases

---

## Error Handling

All methods include proper error handling:

```java
try {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    element.click();
} catch (TimeoutException e) {
    throw new RuntimeException("Element not found: " + description, e);
}
```

**Benefits**:
- Clear error messages
- Easy debugging
- Proper exception propagation
- TestNG failure reporting

---

## Best Practices Implemented

1. **Page Object Model (POM)**: All locators and methods in ProfilePage.java
2. **Reusable Helper Module**: ProfileNavigationHelper for common steps
3. **Comprehensive Logging**: Every step logged to Extent Report
4. **Clear Test Structure**: Organized by feature sections
5. **Proper Waits**: Explicit waits for all elements
6. **Validation After Actions**: Every action followed by verification
7. **Thread.sleep() for Stability**: 2-second waits after clicks for UI stability
8. **Descriptive Method Names**: Clear, self-documenting code
9. **Comments and Documentation**: Well-commented code
10. **Assertion Messages**: Clear failure messages for debugging

---

## Maintenance Guide

### Adding New Test Cases:
1. Add locators to `ProfilePage.java`
2. Add methods to `ProfilePage.java`
3. Create test method in `ProfileTest.java`
4. Use `ProfileNavigationHelper.navigateToProfile()` for common steps
5. Add test-specific steps with logging

### Updating XPaths:
1. Update locator in `ProfilePage.java`
2. No changes needed in test methods
3. All tests automatically use updated locator

### Modifying Common Navigation:
1. Update `ProfileNavigationHelper.java`
2. All 5 test cases automatically use updated navigation
3. No changes needed in individual tests

---

## Summary

✅ **5 Complete Test Cases** implemented
✅ **ProfileNavigationHelper** module integrated
✅ **30+ Page Object Methods** added
✅ **58 Automated Test Steps** across all test cases
✅ **Comprehensive Extent Reporting** with detailed logs
✅ **Zero Code Duplication** - DRY principle followed
✅ **Easy Maintenance** - Update once, fixes everywhere
✅ **Production Ready** - Follows best practices

---

**Created by**: Antigravity  
**Date**: 2025-12-26  
**Version**: 1.0
