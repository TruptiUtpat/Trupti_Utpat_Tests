package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.DashboardPage;
import com.testing.pages.HeaderPage;
import com.testing.pages.LoginPage;
import com.testing.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasskeyTests extends BaseTest {

    // ✅ Replace with your real credentials
    private final String EMAIL = "truptiutpat05@gmail.com";
    private final String PASSWORD = "Trupti@2003";

    @Test
    public void TC_PASSREG_AUTO_01_VerifyAddPasskeyFlow_WithPasswordConfirmation() {

        // ✅ Step 1: Login using password
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(EMAIL, PASSWORD);

        // ✅ Debug
        System.out.println("✅ After login URL  : " + driver.getCurrentUrl());
        System.out.println("✅ After login Title: " + driver.getTitle());

        // ✅ Step 2: Verify Dashboard loaded
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "❌ Dashboard not loaded after login. Passkey flow cannot continue.");

        // ✅ Step 3: Navigate to Profile using avatar dropdown
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openUserMenu();
        headerPage.clickProfile();

        // ✅ Step 4: Profile -> Passkeys
        ProfilePage profilePage = new ProfilePage(driver);

        Assert.assertTrue(profilePage.isPasskeysTabVisible(),
                "❌ Passkeys tab not visible on Profile page.");

        profilePage.openPasskeysTab();

        Assert.assertTrue(profilePage.isAddPasskeyButtonVisible(),
                "❌ Add Passkey button not visible.");

        // ✅ Step 5: Click Add Passkey
        profilePage.clickAddPasskey();

        // ✅ Step 6: Confirm password screen should appear
        Assert.assertTrue(profilePage.isConfirmPasswordScreenVisible(),
                "❌ Confirm password screen not displayed after clicking Add Passkey.");

        // ✅ Step 7: Enter password and click Next
        profilePage.enterConfirmPassword(PASSWORD);
        profilePage.clickNextOnConfirmPassword();

        // ✅ Step 8: Validate passkey flow triggered (system popup may not be captured by selenium)
        Assert.assertTrue(profilePage.isPasskeyFlowTriggered(),
                "❌ Passkey flow not triggered after entering password and clicking Next.");

        System.out.println("✅ Passkey flow triggered successfully!");
    }
}
