package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }
    public WebElement WaitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement WaitForElementPresent(String locator, String error_message)
    {
        return WaitForElementPresent(locator, error_message, 5);
    }

    public WebElement WaitForElementAndClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement WaitForElementAndSendKeys(String locator, String error_message, long timeoutInSeconds, String textToSend)
    {
        WebElement element = WaitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(textToSend);
        return element;
    }

    public int WaitForElementsPresentAndCount(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List elements = driver.findElements(by);
        return elements.size();

    }

    public boolean WaitForElementNotPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement assertElementHasText(String locator, String expected_text, String error_message) {

        WebElement element = WaitForElementPresent(locator, error_message, 5);
        String search_field_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_text,
                search_field_text
        );
        return element;
    }

    public WebElement WaitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void SwipeElementToTheLeft(String locator, String error_message)
    {
        WebElement element = WaitForElementPresent(locator, error_message);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();

        //java-client-4.1.2
//        TouchAction action = new TouchAction(driver);
//        action
//                .press(right_x, middle_y)
//                .waitAction(300)
//                .moveTo(left_x, middle_y)
//                .release().perform();
    }

    public WebElement WaitForElementBeingActive (String locator, String error_message)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.elementToBeClickable(by)
        );
    }

    public void assertElementPresent (String locator, String error_message)
    {
        By by = this.getLocatorByString(locator);
        boolean isTrue;
        try {
            driver.findElement(by);
            isTrue = true;
        } catch (RuntimeException t) {
            isTrue = false;
        }
        Assert.assertTrue(error_message, isTrue);
    }

    public String WaitForElementAndGetAttribute (String locator, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    public void assertElementsCountComparision (String locator, String error_message, long timeoutInSeconds, long expected_value)
    {
        By by = this.getLocatorByString(locator);
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

    private By getLocatorByString(String locator_with_type)
    {
        String [] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath"))
        {
            return By.xpath(locator);
        } else if (by_type.equals("id"))
        {
            return By.id(locator);
        } else
        {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }
}
