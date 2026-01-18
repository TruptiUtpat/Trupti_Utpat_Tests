package com.testing.pages;

import com.testing.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    // ✅ Locators (generic; may adjust based on DOM)
    private By emailInput = By.xpath("//input[@type='email']");
    private By passwordInput = By.xpath("//input[@type='password']");
    private By signInBtn = By.xpath("//button[contains(.,'Sign in') or contains(.,'Sign In')]");
    private By passkeyTab = By.xpath("//*[contains(text(),'Passkey') or contains(text(),'passkey')]");

    // Error/Validation messages
    private By loginErrorMsg = By.xpath(
            "//*[contains(text(),'Invalid') or contains(text(),'invalid') " +
            "or contains(text(),'Incorrect') or contains(text(),'incorrect') " +
            "or contains(text(),'Authentication') or contains(text(),'failed')]"
    );

    private By emailValidationMsg = By.xpath(
            "//*[contains(text(),'Email') and (contains(text(),'required') or contains(text(),'invalid') or contains(text(),'valid'))]"
    );

    private By passwordValidationMsg = By.xpath(
            "//*[contains(text(),'Password') and (contains(text(),'required') or contains(text(),'invalid'))]"
    );

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebElement e = WaitUtils.waitForVisible(driver, emailInput, 15);
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement p = WaitUtils.waitForVisible(driver, passwordInput, 15);
        p.clear();
        p.sendKeys(password);
    }

    public void clickSignIn() {
        WaitUtils.clickWhenClickable(driver, signInBtn, 15);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

    public boolean isLoginErrorVisible() {
        try {
            return WaitUtils.waitForVisible(driver, loginErrorMsg, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailValidationVisible() {
        try {
            return WaitUtils.waitForVisible(driver, emailValidationMsg, 8).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordValidationVisible() {
        try {
            return WaitUtils.waitForVisible(driver, passwordValidationMsg, 8).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasskeyTabVisible() {
        try {
            return driver.findElement(passkeyTab).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
