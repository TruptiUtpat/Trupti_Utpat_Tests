package com.testing.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    // ✅ Use User sign-in URL
    private final String BASE_URL = "https://dev.sovio.id/sign-in";

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // ✅ Browser configs
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        // ✅ This prevents Selenium from waiting for FULL load (popup blocks full load)
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);

        // ✅ Timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // ✅ Open URL (handle timeout due to certificate popup)
        try {
            driver.get(BASE_URL);
        } catch (TimeoutException e) {
            System.out.println("⚠️ Page load timed out (possible certificate popup). Continuing...");
        }

        // ✅ Try dismiss certificate popup (ESC twice)
        dismissCertificatePopup();
    }

    /**
     * ✅ Handles browser-native "Select a certificate" dialog.
     * Selenium cannot click it, so we use keyboard ESC.
     */
    private void dismissCertificatePopup() {
        try {
            Thread.sleep(1500); // allow popup to appear

            Robot robot = new Robot();

            // ESC twice because you said popup appears again
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);

            Thread.sleep(800);

            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);

            Thread.sleep(800);
        } catch (Exception e) {
            System.out.println("⚠️ Could not dismiss certificate popup: " + e.getMessage());
        }
    }

    @AfterMethod
    public void teardown() {
        // ✅ Small delay for debugging (optional)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // ignore
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
