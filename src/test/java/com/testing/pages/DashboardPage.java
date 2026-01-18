package com.testing.pages;

import com.testing.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private WebDriver driver;

    // ✅ Dashboard welcome text from your screenshot
    private By welcomeText = By.xpath("//*[contains(.,'Hi, Welcome') or contains(.,'Welcome')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardLoaded() {
        try {
            return WaitUtils.waitForVisible(driver, welcomeText, 25).isDisplayed();
        } catch (Exception e) {
            System.out.println("⚠️ Dashboard not detected.");
            System.out.println("⚠️ Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }
}
