# Profile Navigation Module Architecture

## Module Structure

```
mobile-automation-java/
├── src/test/java/com/automation/
│   ├── base/
│   │   └── BaseTest.java                    # Base test class with driver setup
│   ├── pages/
│   │   ├── HomePage.java                    # Home page locators & methods
│   │   └── ProfilePage.java                 # Profile page locators & methods
│   ├── utils/
│   │   └── ProfileNavigationHelper.java     # ⭐ NEW: Navigation helper module
│   └── tests/
│       ├── EditProfileTest.java             # Can use the helper
│       ├── ProfileNavigationExample.java    # ⭐ NEW: Example usage
│       └── [YourNewTests].java              # Your future tests
└── PROFILE_NAVIGATION_MODULE.md             # ⭐ NEW: Full documentation
```

## Data Flow Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         Your Test Class                          │
│                    (e.g., ProfileTest.java)                      │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             │ 1. Call navigateToProfile()
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                   ProfileNavigationHelper                        │
│                  (Reusable Navigation Module)                    │
│                                                                   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Step 1: Verify DAILY PRIORITY heading                   │   │
│  │         ↓                                                │   │
│  │ Step 2: Click Wellbeing Dashboard                       │   │
│  │         ↓                                                │   │
│  │ Step 3: Click PROFILE button                            │   │
│  │         ↓                                                │   │
│  │ Step 4: Verify Profile page displayed                   │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                   │
│  Uses ↓                                                          │
└────────┬──────────────────────────────────────┬─────────────────┘
         │                                       │
         ▼                                       ▼
┌─────────────────┐                    ┌─────────────────┐
│   HomePage      │                    │  ProfilePage    │
│   - Locators    │                    │  - Locators     │
│   - Methods     │                    │  - Methods      │
└─────────────────┘                    └─────────────────┘
```

## Usage Flow

```
┌──────────────────────────────────────────────────────────────────┐
│                    Test Execution Flow                            │
└──────────────────────────────────────────────────────────────────┘

1. Test Method Starts
   │
   ├─→ Create ExtentTest instance
   │
   ├─→ Call ProfileNavigationHelper.navigateToProfile(driver, test)
   │   │
   │   ├─→ [Helper] Verify home page
   │   │   └─→ Log to Extent Report ✓
   │   │
   │   ├─→ [Helper] Click Wellbeing Dashboard
   │   │   └─→ Log to Extent Report ✓
   │   │
   │   ├─→ [Helper] Click PROFILE
   │   │   └─→ Log to Extent Report ✓
   │   │
   │   ├─→ [Helper] Verify Profile page
   │   │   └─→ Log to Extent Report ✓
   │   │
   │   └─→ Return ProfilePage instance
   │
   ├─→ Use ProfilePage to perform test-specific actions
   │   │
   │   ├─→ profilePage.clickAccount()
   │   ├─→ profilePage.clickMyOrders()
   │   ├─→ profilePage.clickHelpSupport()
   │   └─→ profilePage.clickLogout()
   │
   └─→ Test assertions and completion
```

## Before vs After Comparison

### ❌ Before (Without Module)

```java
@Test
public void testCase1() throws InterruptedException {
    test = extent.createTest("Test Case 1");
    
    // Duplicate navigation code (20+ lines)
    test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading");
    HomePage homePage = new HomePage(driver);
    boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
    if (!isHomePageDisplayed) {
        test.log(Status.FAIL, "DAILY PRIORITY heading not found");
        Assert.fail("Home page validation failed");
    }
    test.log(Status.PASS, "✓ DAILY PRIORITY heading displayed");
    
    test.log(Status.INFO, "Step 2: Clicking Wellbeing Dashboard");
    try {
        homePage.clickWellbeingDashboard();
        test.log(Status.PASS, "✓ Wellbeing Dashboard clicked");
    } catch (Exception e) {
        test.log(Status.INFO, "Already on dashboard");
    }
    Thread.sleep(2000);
    
    homePage.clickProfile();
    test.log(Status.INFO, "Clicked PROFILE button");
    Thread.sleep(2000);
    
    ProfilePage profilePage = new ProfilePage(driver);
    // ... more validation code
    
    // Finally, your actual test logic
    profilePage.clickAccount();
}

@Test
public void testCase2() throws InterruptedException {
    test = extent.createTest("Test Case 2");
    
    // Same 20+ lines repeated again! 😫
    test.log(Status.INFO, "Step 1: Verifying DAILY PRIORITY heading");
    // ... duplicate code ...
}
```

### ✅ After (With Module)

```java
@Test
public void testCase1() throws InterruptedException {
    test = extent.createTest("Test Case 1");
    
    // One line replaces 20+ lines! 🎉
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    
    // Your actual test logic
    profilePage.clickAccount();
}

@Test
public void testCase2() throws InterruptedException {
    test = extent.createTest("Test Case 2");
    
    // Same one line! No duplication! 🎉
    ProfilePage profilePage = ProfileNavigationHelper.navigateToProfile(driver, test);
    
    // Your actual test logic
    profilePage.clickMyOrders();
}
```

## Benefits Visualization

```
┌─────────────────────────────────────────────────────────────────┐
│                    Code Reduction Example                        │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  4 Test Cases WITHOUT Module:                                   │
│  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  │
│  Test 1: 25 lines (20 navigation + 5 test logic)                │
│  Test 2: 25 lines (20 navigation + 5 test logic)                │
│  Test 3: 25 lines (20 navigation + 5 test logic)                │
│  Test 4: 25 lines (20 navigation + 5 test logic)                │
│  ────────────────────────────────────────────────────────────   │
│  Total: 100 lines                                                │
│                                                                   │
│  4 Test Cases WITH Module:                                      │
│  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  │
│  Test 1: 6 lines (1 navigation + 5 test logic)                  │
│  Test 2: 6 lines (1 navigation + 5 test logic)                  │
│  Test 3: 6 lines (1 navigation + 5 test logic)                  │
│  Test 4: 6 lines (1 navigation + 5 test logic)                  │
│  ────────────────────────────────────────────────────────────   │
│  Total: 24 lines                                                 │
│                                                                   │
│  📊 Code Reduction: 76% less code!                              │
│  🎯 Maintenance: Update 1 place instead of 4!                   │
│  ✨ Readability: Focus on test logic, not navigation!           │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

## Integration Points

```
┌─────────────────────────────────────────────────────────────────┐
│              ProfileNavigationHelper Integration                 │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  Integrates with:                                                │
│  ✓ BaseTest.java         - Uses driver and test instances       │
│  ✓ HomePage.java         - Calls home page methods              │
│  ✓ ProfilePage.java      - Returns profile page instance        │
│  ✓ ExtentReports         - Logs all steps automatically         │
│  ✓ TestNG                - Works with @Test annotations         │
│                                                                   │
│  Used by:                                                        │
│  ✓ Any test class that needs to navigate to Profile             │
│  ✓ EditProfileTest.java  - Can replace existing navigation      │
│  ✓ Future test classes   - Ready to use immediately             │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

## Method Selection Guide

```
┌─────────────────────────────────────────────────────────────────┐
│              Which Method Should I Use?                          │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  navigateToProfile()                                             │
│  ├─ ✅ Use when: You need strict validation                     │
│  ├─ ✅ Use when: Navigation must succeed                        │
│  ├─ ✅ Use when: You want detailed logging                      │
│  └─ ✅ Recommended for: Most test cases (90%)                   │
│                                                                   │
│  navigateToProfileSimple()                                       │
│  ├─ ✅ Use when: You want custom validation                     │
│  ├─ ✅ Use when: Navigation failure is acceptable               │
│  └─ ✅ Recommended for: Advanced scenarios (10%)                │
│                                                                   │
│  isOnHomePage() / isOnProfilePage()                              │
│  ├─ ✅ Use when: You need to check current page                 │
│  ├─ ✅ Use when: Conditional navigation needed                  │
│  └─ ✅ Recommended for: Page state verification                 │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

## Maintenance Workflow

```
┌─────────────────────────────────────────────────────────────────┐
│              When Navigation Changes...                          │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  Scenario: App navigation flow changes                          │
│                                                                   │
│  ❌ WITHOUT Module:                                             │
│     1. Find all test files with navigation code                 │
│     2. Update EditProfileTest.java                              │
│     3. Update ProfileTest.java                                  │
│     4. Update [OtherTest1].java                                 │
│     5. Update [OtherTest2].java                                 │
│     6. Update [OtherTest3].java                                 │
│     7. Test each file individually                              │
│     8. Fix inconsistencies                                      │
│     ⏱️  Time: 2-3 hours                                         │
│                                                                   │
│  ✅ WITH Module:                                                │
│     1. Update ProfileNavigationHelper.java                      │
│     2. Test once                                                │
│     3. All tests automatically use new flow                     │
│     ⏱️  Time: 15 minutes                                        │
│                                                                   │
│  💡 Time Saved: ~90%                                            │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

---

This architecture provides a scalable, maintainable solution for Profile navigation across all your test cases.
