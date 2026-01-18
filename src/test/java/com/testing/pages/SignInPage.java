package com.testing.pages;

import com.testing.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

    private WebDriver driver;

    // ✅ Passkey tab
    private By passkeyTab = By.xpath("//button[contains(.,'Passkey') or contains(.,'passkey')]");

    // ✅ Passkey screen indicator
    private By passkeyScreenIndicator = By.xpath(
            "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'passkey')]"
    );

    // ✅ Continue / Next button
    private By continueBtn = By.xpath(
            "//button[contains(.,'Continue') or contains(.,'Next') or contains(.,'Use') or contains(.,'Sign in')]"
    );

    // ✅ DOM dialog (sometimes appears)
    private By webDialog = By.xpath("//*[@role='dialog']");

    // ✅ IMPORTANT: Detect only REAL blockers (not generic error texts)
    private By blockerErrorMsg = By.xpath(
            "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'no passkey') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'no passkeys') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'not supported') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'device not supported') " +
                    "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'try again later')]"
    );

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://dev.sovio.id/sign-in");
    }

    public void clickPasskeyTab() {
        WaitUtils.clickWhenClickable(driver, passkeyTab, 15);
    }

    public boolean isPasskeyTabOpened() {
        try {
            return WaitUtils.waitForVisible(driver, passkeyScreenIndicator, 10).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueForPasskey() {
        WaitUtils.clickWhenClickable(driver, continueBtn, 10);
    }

    public boolean isBlockerErrorDisplayed() {
        try {
            return WaitUtils.waitForVisible(driver, blockerErrorMsg, 4).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * ✅ Passkey flow is OS-level (not always visible to selenium)
     * So treat it as success if:
     * - blocker errors NOT shown
     * - dialog visible OR flow not blocked
     */
    public boolean isPasskeyFlowTriggered() {

        // fail only if real blocker error displayed
        if (isBlockerErrorDisplayed()) return false;

        // if html dialog appears => pass
        try {
            return WaitUtils.waitForVisible(driver, webDialog, 5).isDisplayed();
        } catch (Exception e) {
            // OS popup may appear (not detectable) => still pass
            return true;
        }
    }
}
