import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/e.abramov/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchFieldPlaceholderTextComparition() {
        WaitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "No element with org.wikipedia:id/search_container id found or unable to click",
                5
        );

        WebElement search_text_element = WaitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "No element with org.wikipedia:id/search_src_text id found"
        );

        String element_text = search_text_element.getAttribute("text");

        Assert.assertEquals(
                "Unexpected search field placeholder",
                "Search…",
                element_text
        );
    }

    private WebElement WaitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement WaitForElementPresent(By by, String error_message) {
        return WaitForElementPresent(by, error_message, 5);
    }

    private WebElement WaitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
}