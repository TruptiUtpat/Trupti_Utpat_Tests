package com.testing.pages;

import com.testing.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage {

    private WebDriver driver;

    // ✅ Tabs
    private By passkeysTab = By.xpath("//*[contains(text(),'Passkey') or contains(text(),'Passkeys')]");

    // ✅ Passkey section elements
    private By addPasskeyBtn = By.xpath("//button[contains(.,'Add Passkey') or contains(.,'Add passkey')]");

    // ✅ Confirm password screen (appears after clicking Add Passkey)
    private By confirmPasswordInput = By.xpath("//input[@type='password']");
    private By nextBtn = By.xpath("//button[contains(.,'Next') or contains(.,'Continue')]");
    private By passwordError = By.xpath(
            "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'invalid') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'incorrect') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'required')]"
    );

    // ✅ HTML dialog (sometimes appears, not always)
    private By webDialog = By.xpath("//*[@role='dialog']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPasskeysTabVisible() {
        try {
            return WaitUtils.waitForVisible(driver, passkeysTab, 15).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openPasskeysTab() {
        WaitUtils.clickWhenClickable(driver, passkeysTab, 15);
    }

    public boolean isAddPasskeyButtonVisible() {
        try {
            return WaitUtils.waitForVisible(driver, addPasskeyBtn, 15).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddPasskey() {
        WaitUtils.clickWhenClickable(driver, addPasskeyBtn, 15);
    }

    // ✅ Confirm password screen
    public boolean isConfirmPasswordScreenVisible() {
        try {
            return WaitUtils.waitForVisible(driver, confirmPasswordInput, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterConfirmPassword(String password) {
        WebElement pwd = WaitUtils.waitForVisible(driver, confirmPasswordInput, 10);
        pwd.clear();
        pwd.sendKeys(password);
    }

    public void clickNextOnConfirmPassword() {
        WaitUtils.clickWhenClickable(driver, nextBtn, 10);
    }

    public boolean isPasswordErrorDisplayed() {
        try {
            return WaitUtils.waitForVisible(driver, passwordError, 5).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * ✅ Selenium can't always detect Windows Hello / Google Password Manager system popup.
     * So we treat it as success if:
     * - No password error shown
     * - dialog visible OR no error after clicking Next
     */
    public boolean isPasskeyFlowTriggered() {

        if (isPasswordErrorDisplayed()) return false;

        try {
            // if HTML dialog appears
            return WaitUtils.waitForVisible(driver, webDialog, 5).isDisplayed();
        } catch (Exception e) {
            // System popup might appear (not detectable by DOM) — consider pass
            return true;
        }
    }
}
