# Profile Navigation Helper Module

## Overview
The **ProfileNavigationHelper** is a reusable utility module that encapsulates common navigation steps required to reach the Profile page from the Home page. This module eliminates code duplication across multiple test cases and provides consistent logging and validation.

## Location
```
src/test/java/com/automation/utils/ProfileNavigationHelper.java
```

## Common Steps Implemented

The module implements the following common navigation steps:

1. **Step 1**: Verify DAILY PRIORITY heading is displayed on home page
2. **Step 2**: Click Wellbeing Dashboard (if not already there)
3. **Step 3**: Click PROFILE button
4. **Step 4**: Verify Profile page is displayed

## Available Methods

### 1. `navigateToProfile(AppiumDriver driver, ExtentTest test)`

**Full navigation with validation and assertions**

- Performs all 4 common steps
- Validates each step and logs to Extent Report
- Throws assertion failures if validation fails
- Returns `ProfilePage` instance for further actions

**Usage:**
```java
ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
```

**When to use:**
- When you need strict validation at each step
- For critical test flows where navigation must succeed
- When you want detailed logging of each navigation step

---

### 2. `navigateToProfileSimple(AppiumDriver driver, ExtentTest test)`

**Simplified navigation without assertions**

- Performs navigation steps without strict validation
- Logs actions but doesn't fail on validation errors
- Returns `ProfilePage` instance
- Useful when you want to handle validation yourself

**Usage:**
```java
ProfilePage profilePage = ProfileNavigationHelper.navigateToProfileSimple(driver, test);
```

**When to use:**
- When you want to handle validation logic yourself
- For flexible test flows where navigation failure is acceptable
- When you need custom error handling

---

### 3. `isOnHomePage(AppiumDriver driver, ExtentTest test)`

**Check if currently on Home page**

- Returns `true` if on Home page, `false` otherwise
- Logs the result to Extent Report
- Useful for conditional navigation

**Usage:**
```java
boolean isHome = ProfileNavigationHelper.isOnHomePage(driver, test);
if (isHome) {
    // Proceed with navigation
}
```

---

### 4. `isOnProfilePage(AppiumDriver driver, ExtentTest test)`

**Check if currently on Profile page**

- Returns `true` if on Profile page, `false` otherwise
- Logs the result to Extent Report
- Useful for verification after navigation

**Usage:**
```java
boolean isProfile = ProfileNavigationHelper.isOnProfilePage(driver, test);
if (isProfile) {
    // Perform profile-specific actions
}
```

## Complete Usage Examples

### Example 1: Basic Navigation to Profile
```java
@Test
public void testProfileFeature() throws InterruptedException {
    test = extent.createTest("Test Profile Feature");
    
    // Navigate to Profile page (with full validation)
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    
    // Now perform your test-specific actions
    profilePage.clickAccount();
    test.log(Status.INFO, "Clicked ACCOUNT");
}
```

### Example 2: Navigate and Click My Orders
```java
@Test
public void testMyOrders() throws InterruptedException {
    test = extent.createTest("Test My Orders");
    
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    profilePage.clickMyOrders();
    test.log(Status.INFO, "Navigated to My Orders");
    
    // Continue with My Orders specific tests...
}
```

### Example 3: Navigate and Click Help & Support
```java
@Test
public void testHelpSupport() throws InterruptedException {
    test = extent.createTest("Test Help & Support");
    
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    profilePage.clickHelpSupport();
    test.log(Status.INFO, "Navigated to Help & Support");
    
    // Continue with Help & Support specific tests...
}
```

### Example 4: Navigate and Logout
```java
@Test
public void testLogout() throws InterruptedException {
    test = extent.createTest("Test Logout");
    
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    profilePage.clickLogout();
    test.log(Status.INFO, "Clicked Logout");
    
    // Verify logout success...
}
```

### Example 5: Conditional Navigation
```java
@Test
public void testConditionalNavigation() throws InterruptedException {
    test = extent.createTest("Test Conditional Navigation");
    
    // Check if already on Profile page
    if (!ProfileNavigationHelper.isOnProfilePage(driver, test)) {
        // Navigate only if not already there
        ProfileNavigationHelper.navigateToProfile(driver, test);
    }
    
    // Perform actions...
}
```

### Example 6: Custom Validation
```java
@Test
public void testCustomValidation() throws InterruptedException {
    test = extent.createTest("Test Custom Validation");
    
    // Use simplified navigation
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfileSimple(driver, test);
    
    // Custom validation
    if (ProfileNavigationHelper.isOnProfilePage(driver, test)) {
        test.log(Status.PASS, "✓ Navigation successful");
        // Continue with test...
    } else {
        test.log(Status.FAIL, "✗ Navigation failed");
        Assert.fail("Failed to reach Profile page");
    }
}
```

## Benefits

### 1. **Code Reusability**
- Write navigation logic once, use it across all test cases
- No need to duplicate the same 4 steps in every test

### 2. **Consistency**
- All tests use the same navigation flow
- Consistent logging format across all tests
- Standardized validation approach

### 3. **Maintainability**
- If navigation flow changes, update only one place
- Easy to add new validation steps
- Centralized error handling

### 4. **Readability**
- Test code is cleaner and more focused on test logic
- Navigation details are abstracted away
- Clear intent with method names

### 5. **Logging**
- Automatic Extent Report logging for all steps
- Consistent log messages
- Easy to debug failures

## Integration with Existing Tests

You can easily integrate this module into your existing test classes:

### Before (Without Module):
```java
@Test
public void testSomething() throws InterruptedException {
    test = extent.createTest("Test Something");
    
    // Step 1: Verify home page
    test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading");
    HomePage homePage = new HomePage(driver);
    boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
    if (!isHomePageDisplayed) {
        test.log(Status.FAIL, "DAILY PRIORITY heading not found");
        Assert.fail("Home page validation failed");
    }
    test.log(Status.PASS, "✓ DAILY PRIORITY heading displayed");
    
    // Step 2: Click Wellbeing Dashboard
    test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
    try {
        homePage.clickWellbeingDashboard();
        test.log(Status.PASS, "✓ Wellbeing Dashboard clicked");
    } catch (Exception e) {
        test.log(Status.INFO, "Already on dashboard");
    }
    Thread.sleep(2000);
    
    // Step 3: Click PROFILE
    homePage.clickProfile();
    test.log(Status.INFO, "Clicked PROFILE button");
    Thread.sleep(2000);
    
    // Step 4: Verify Profile page
    ProfilePage profilePage = new ProfilePage(driver);
    // ... more code
    
    // Finally do your actual test logic
    profilePage.clickAccount();
}
```

### After (With Module):
```java
@Test
public void testSomething() throws InterruptedException {
    test = extent.createTest("Test Something");
    
    // Navigate to Profile (all common steps handled)
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    
    // Your actual test logic
    profilePage.clickAccount();
}
```

## Error Handling

The module handles errors gracefully:

- **Home page not displayed**: Fails with assertion and detailed log
- **Wellbeing Dashboard not found**: Logs info message and continues (assumes already on dashboard)
- **Profile page not displayed**: Fails with assertion and detailed log

## Extent Report Logging

The module provides detailed logging at each step:

```
INFO: Step 1: Verifying DAILY PRIORITY heading on home page
PASS: ✓ DAILY PRIORITY heading is displayed on home page
INFO: Step 2: Clicking Wellbeing Dashboard
PASS: ✓ Wellbeing Dashboard clicked
INFO: Step 3: Clicking PROFILE button
PASS: ✓ PROFILE button clicked
INFO: Step 4: Verifying Profile page is displayed
PASS: ✓ Profile page is displayed successfully
```

## Best Practices

1. **Use `navigateToProfile()` for most cases**: It provides full validation and clear error messages
2. **Use `navigateToProfileSimple()` when you need custom validation**: Gives you more control
3. **Always pass the `test` parameter**: Ensures proper logging in Extent Reports
4. **Store the returned `ProfilePage` instance**: Use it for subsequent actions
5. **Handle `InterruptedException`**: Add `throws InterruptedException` to your test methods

## Future Enhancements

Potential improvements for this module:

1. Add retry logic for flaky navigation
2. Add screenshot capture on navigation failure
3. Add configurable wait times
4. Add navigation to other pages (Edit Profile, My Orders, etc.)
5. Add support for different navigation paths

## Example Test Class

See `ProfileNavigationExample.java` for complete working examples of all usage patterns.

---

**Created by**: Antigravity  
**Date**: 2025-12-26  
**Version**: 1.0
