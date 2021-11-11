package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }
    public WebElement WaitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement WaitForElementPresent(By by, String error_message) {
        return WaitForElementPresent(by, error_message, 5);
    }

    public WebElement WaitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement WaitForElementAndSendKeys(By by, String error_message, long timeoutInSeconds, String textToSend) {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(textToSend);
        return element;
    }

    public int WaitForElementsPresentAndCount(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List elements = driver.findElements(by);
        return elements.size();

    }

    public boolean WaitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement assertElementHasText(By by, String expected_text, String error_message) {

        WebElement element = WaitForElementPresent(by, error_message, 5);
        String search_field_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_text,
                search_field_text
        );
        return element;
    }

    public WebElement WaitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void SwipeElementToTheLeft(By by, String error_message) {
        WebElement element = WaitForElementPresent(by, error_message);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release().perform();
    }

    public WebElement WaitForElementBeingActive(By by, String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.elementToBeClickable(by)
        );
    }

    public void assertElementPresent (By by, String error_message)
    {
        boolean isTrue;
        try {
            driver.findElement(by);
            isTrue = true;
        } catch (RuntimeException t) {
            isTrue = false;
        }
        Assert.assertTrue(error_message, isTrue);
    }

    public String WaitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    public void assertElementsCountComparision (By by, String error_message, long timeoutInSeconds, long expected_value)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List elements = driver.findElements(by);
        long actual_value = elements.size();
        boolean isBigger;
        if (actual_value > expected_value)
        {
            isBigger = true;
        } else
        {
            isBigger = false;
        }
        Assert.assertTrue("Actual number of elements " + actual_value + " is not equal to expected " + expected_value, isBigger);
    }
}
