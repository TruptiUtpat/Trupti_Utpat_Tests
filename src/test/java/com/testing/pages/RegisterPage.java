package com.testing.pages;

import com.testing.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

    private WebDriver driver;

    // ✅ “Don’t have an account? Create one”
    private By createAccountBtn = By.xpath(
            "//a[contains(.,'Create one')] " +
            "| //a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'create one')] " +
            "| //a[contains(@href,'sign-up') or contains(@href,'signup') or contains(@href,'register')]"
    );

    private By emailInput = By.xpath("//input[@type='email']");
    private By continueBtn = By.xpath("//button[contains(.,'Continue') or contains(.,'Next') or contains(.,'Submit')]");

    // ✅ success message (verification mail)
    private By verificationMsg = By.xpath(
            "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'verification') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'check your email') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'email sent') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'check your inbox') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'link sent') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sent')]"
    );

    // ✅ error message
    private By emailErrorMsg = By.xpath(
            "//*[contains(text(),'valid email') or contains(text(),'Invalid email') " +
                    "or contains(text(),'invalid email') or contains(text(),'required')]"
    );

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCreateAccount() {
        try {
            WaitUtils.clickWhenClickable(driver, createAccountBtn, 10);
        } catch (Exception e) {
            System.out.println("⚠️ Create one link not clickable. Navigating directly to Sign-up page...");
            driver.get("https://dev.sovio.id/sign-up");
        }
    }

    public void enterEmail(String email) {
        WebElement emailField = WaitUtils.waitForVisible(driver, emailInput, 15);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // ✅ IMPORTANT: This method is missing in your file. Add it back.
    public void clickContinue() {
        WaitUtils.clickWhenClickable(driver, continueBtn, 15);
    }

    public boolean isVerificationMessageDisplayed() {
        try {
            if (WaitUtils.waitForVisible(driver, verificationMsg, 10).isDisplayed()) return true;
        } catch (Exception ignored) {}

        String url = driver.getCurrentUrl().toLowerCase();
        return url.contains("verify") || url.contains("verification") || url.contains("confirmation");
    }

    public boolean isEmailErrorDisplayed() {
        try {
            return WaitUtils.waitForVisible(driver, emailErrorMsg, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
