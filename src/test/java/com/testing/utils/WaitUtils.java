package com.testing.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils {

    public static WebElement waitForVisible(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void clickWhenClickable(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public static void clickElement(WebDriver driver, WebElement element, int seconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    wait.until(ExpectedConditions.elementToBeClickable(element)).click();

   
}

}


