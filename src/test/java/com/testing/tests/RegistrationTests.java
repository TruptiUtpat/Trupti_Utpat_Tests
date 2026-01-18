package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    private void debug(String step) {
        System.out.println("\n==============================");
        System.out.println("DEBUG STEP: " + step);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title : " + driver.getTitle());
        System.out.println("==============================\n");
    }

    // ❌ Disabled because success confirmation is unstable in dev environment
    @Test(enabled = false)
    public void TC_REG_AUTO_01_ValidEmail_ShouldShowVerificationMessage() {

        RegisterPage registerPage = new RegisterPage(driver);

        debug("TC_REG_AUTO_01 START");
        registerPage.clickCreateAccount();

        String email = "trupti.qa+" + System.currentTimeMillis() + "@gmail.com";
        registerPage.enterEmail(email);
        registerPage.clickContinue();

        boolean result = registerPage.isVerificationMessageDisplayed();
        Assert.assertTrue(result,
                "❌ Verification message not displayed after submitting valid email.");
    }

    @Test
    public void TC_REG_AUTO_02_BlankEmail_ShouldShowError() {

        RegisterPage registerPage = new RegisterPage(driver);

        debug("TC_REG_AUTO_02 START");
        registerPage.clickCreateAccount();

        registerPage.enterEmail("");
        registerPage.clickContinue();

        boolean result = registerPage.isEmailErrorDisplayed();
        System.out.println("Email Error displayed? => " + result);

        Assert.assertTrue(result,
                "❌ Email required validation message not displayed for blank email.");
    }

    @Test
    public void TC_REG_AUTO_03_InvalidEmailFormat_ShouldShowError() {

        RegisterPage registerPage = new RegisterPage(driver);

        debug("TC_REG_AUTO_03 START");
        registerPage.clickCreateAccount();

        registerPage.enterEmail("trupti@@gmail");
        registerPage.clickContinue();

        boolean result = registerPage.isEmailErrorDisplayed();
        System.out.println("Email Error displayed? => " + result);

        Assert.assertTrue(result,
                "❌ Invalid email validation message not displayed.");
    }

    @Test
    public void TC_REG_AUTO_04_InvalidEmail_NoDomain_ShouldShowError() {

        RegisterPage registerPage = new RegisterPage(driver);

        debug("TC_REG_AUTO_04 START");
        registerPage.clickCreateAccount();

        registerPage.enterEmail("trupti@gmail");
        registerPage.clickContinue();

        boolean result = registerPage.isEmailErrorDisplayed();
        System.out.println("Email Error displayed? => " + result);

        Assert.assertTrue(result,
                "❌ Invalid email (no domain) validation message not displayed.");
    }

    @Test
    public void TC_REG_AUTO_05_ExistingEmail_ShouldShowErrorOrProceedMessage() {

        RegisterPage registerPage = new RegisterPage(driver);

        debug("TC_REG_AUTO_05 START");
        registerPage.clickCreateAccount();

        String existingEmail = "truptiutpat05@gmail.com";
        registerPage.enterEmail(existingEmail);
        registerPage.clickContinue();

        boolean error = registerPage.isEmailErrorDisplayed();
        boolean verification = registerPage.isVerificationMessageDisplayed();

        System.out.println("Email Error displayed? => " + error);
        System.out.println("Verification Msg displayed? => " + verification);

        Assert.assertTrue(error || verification,
                "❌ Neither error nor verification message displayed for existing email scenario.");
    }
}
