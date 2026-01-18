package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.DashboardPage;
import com.testing.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    // ✅ Use your real registered email & password
    private final String EMAIL = "truptiutpat05@gmail.com";
    private final String PASSWORD = "Trupti@2003";

    // ✅ Valid Login
    @Test
    public void TC_LOGIN_AUTO_01_ValidLogin_ShouldRedirectToDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(EMAIL, PASSWORD);

        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "❌ Dashboard not loaded after valid login.");
    }

    // ✅ Invalid Password
    @Test
    public void TC_LOGIN_AUTO_02_InvalidPassword_ShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(EMAIL, "WrongPassword@123");

        Assert.assertTrue(loginPage.isLoginErrorVisible(),
                "❌ Login error message not shown for invalid password.");
    }

    // ✅ Blank Email
    @Test
    public void TC_LOGIN_AUTO_03_BlankEmail_ShouldShowValidationError() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail("");
        loginPage.enterPassword(PASSWORD);
        loginPage.clickSignIn();

        // Accept either field validation or general error
        boolean pass = loginPage.isEmailValidationVisible() || loginPage.isLoginErrorVisible();

        Assert.assertTrue(pass,
                "❌ Email validation message not displayed for blank email.");
    }

    // ✅ Blank Password
    @Test
    public void TC_LOGIN_AUTO_04_BlankPassword_ShouldShowValidationError() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(EMAIL);
        loginPage.enterPassword("");
        loginPage.clickSignIn();

        boolean pass = loginPage.isPasswordValidationVisible() || loginPage.isLoginErrorVisible();

        Assert.assertTrue(pass,
                "❌ Password validation message not displayed for blank password.");
    }

    // ✅ Invalid Email Format
    @Test
    public void TC_LOGIN_AUTO_05_InvalidEmailFormat_ShouldShowValidationError() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("invalidEmail@@gmail", PASSWORD);

        boolean pass = loginPage.isEmailValidationVisible() || loginPage.isLoginErrorVisible();

        Assert.assertTrue(pass,
                "❌ Invalid email format validation message not displayed.");
    }
}
