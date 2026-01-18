package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasskeyLoginTests extends BaseTest {

    @Test
    public void TC_PASSLOGIN_AUTO_01_SignInWithPasskey_ShouldTriggerPasskeyPopup() {

        SignInPage signInPage = new SignInPage(driver);

        // ✅ Step 1: Open sign-in page
        signInPage.open();
        System.out.println("\n✅ Step 1: Opened URL: " + driver.getCurrentUrl());
        System.out.println("✅ Page Title: " + driver.getTitle());

        // ✅ Step 2: Click Passkey tab
        signInPage.clickPasskeyTab();
        System.out.println("\n✅ Step 2: Clicked Passkey tab");
        System.out.println("✅ URL after clicking Passkey tab: " + driver.getCurrentUrl());
        System.out.println("✅ Title: " + driver.getTitle());

        Assert.assertTrue(signInPage.isPasskeyTabOpened(),
                "❌ Passkey tab not opened / passkey screen not visible.");

        // ✅ Step 3: Click Continue / Next
        signInPage.clickContinueForPasskey();
        System.out.println("\n✅ Step 3: Clicked Continue/Next for Passkey");
        System.out.println("✅ URL after clicking Continue: " + driver.getCurrentUrl());
        System.out.println("✅ Title: " + driver.getTitle());

        // ✅ Step 4: Validate no blocker + flow triggered
        Assert.assertFalse(signInPage.isBlockerErrorDisplayed(),
                "❌ Blocker error displayed (No passkeys / Not supported etc).");

        Assert.assertTrue(signInPage.isPasskeyFlowTriggered(),
                "❌ Passkey flow not triggered OR blocker error displayed.");

        System.out.println("\n✅ Passkey Sign-in flow triggered successfully!\n");
    }
}
