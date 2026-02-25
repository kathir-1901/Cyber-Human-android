# Profile Navigation Helper - Quick Reference

## Import Statement
```java
import com.automation.utils.ProfileNavigationHelper;
```

## Quick Usage

### Standard Navigation (Recommended)
```java
ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
```

### Simplified Navigation (No Assertions)
```java
ProfilePage profilePage = ProfileNavigationHelper.navigateToProfileSimple(driver, test);
```

### Check Current Page
```java
boolean isHome = ProfileNavigationHelper.isOnHomePage(driver, test);
boolean isProfile = ProfileNavigationHelper.isOnProfilePage(driver, test);
```

## Common Test Patterns

### Pattern 1: Navigate and Click Account
```java
@Test
public void testAccount() throws InterruptedException {
    test = extent.createTest("Test Account");
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    profilePage.clickAccount();
}
```

### Pattern 2: Navigate and Click My Orders
```java
@Test
public void testMyOrders() throws InterruptedException {
    test = extent.createTest("Test My Orders");
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    profilePage.clickMyOrders();
}
```

### Pattern 3: Navigate and Click Help & Support
```java
@Test
public void testHelpSupport() throws InterruptedException {
    test = extent.createTest("Test Help & Support");
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    profilePage.clickHelpSupport();
}
```

### Pattern 4: Navigate and Logout
```java
@Test
public void testLogout() throws InterruptedException {
    test = extent.createTest("Test Logout");
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    profilePage.clickLogout();
}
```

## What It Does

The helper automatically handles these steps:

1. ✅ Verify DAILY PRIORITY heading on home page
2. ✅ Click Wellbeing Dashboard (if needed)
3. ✅ Click PROFILE button
4. ✅ Verify Profile page is displayed
5. ✅ Log all steps to Extent Report
6. ✅ Return ProfilePage instance for further actions

## Benefits

- **No code duplication** - Write once, use everywhere
- **Consistent logging** - All tests log the same way
- **Easy maintenance** - Update one place, fixes all tests
- **Clean test code** - Focus on test logic, not navigation

## See Also

- Full documentation: `PROFILE_NAVIGATION_MODULE.md`
- Example tests: `ProfileNavigationExample.java`
- Helper class: `src/test/java/com/automation/utils/ProfileNavigationHelper.java`
