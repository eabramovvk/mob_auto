import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
    public void testSearchCancellation() {
        int expectedElementsCount = 1;
        String textToSearch = "Java";

        WaitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "No element with org.wikipedia:id/search_container id found or unable to click",
                5
        );

        WaitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Can not find or send keys to element with org.wikipedia:id/search_src_text id or sendKeys " + textToSearch,
                5,
                textToSearch
        );

        int elements_count = WaitForElementsPresentAndCount(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Can not count list of elements with org.wikipedia:id/page_list_item_container id",
                5
        );

        Assert.assertTrue(
                "Elements count is " + expectedElementsCount + " or smaller",
                elements_count > expectedElementsCount
        );

        WaitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "No element with org.wikipedia:id/search_close_btn id found or unable to click",
                0
        );

        WaitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Element with id org.wikipedia:id/page_list_item_container still present on screen",
                5
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

    private WebElement WaitForElementAndSendKeys(By by, String error_message, long timeoutInSeconds, String textToSend)
    {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(textToSend);
        return element;
    }

    private int WaitForElementsPresentAndCount(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List elements = driver.findElements(by);
        return elements.size();

    }

    private boolean WaitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
}