package com.testing.pages;

import com.testing.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HeaderPage {

    private WebDriver driver;

    // ✅ There are 2 menus: Select Organization + Avatar menu
    private By allMenuButtons = By.xpath("//button[@aria-haspopup='menu']");

    // ✅ Profile option
    private By profileOption = By.xpath("//*[@role='menuitem' and contains(.,'Profile')]");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUserMenu() {
        List<WebElement> menus = driver.findElements(allMenuButtons);

        if (menus.size() == 0) {
            throw new RuntimeException("❌ No menu buttons found on dashboard!");
        }

        // ✅ LAST menu button = Avatar dropdown
        WebElement avatarMenu = menus.get(menus.size() - 1);
        WaitUtils.clickElement(driver, avatarMenu, 10);
    }

    public void clickProfile() {
        WaitUtils.clickWhenClickable(driver, profileOption, 15);
    }
}
