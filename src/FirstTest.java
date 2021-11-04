import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


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
        driver.rotate(ScreenOrientation.PORTRAIT);


    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testChangeScreenOrientationOnScreenResults()
    {
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

        WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "No element with org.wikipedia:id/page_list_item_container resource-id found or unable to click",
                15
        );

        String titleBeforeRotation = WaitForElementAndGetAttribute (
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article title",
                5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = WaitForElementAndGetAttribute (
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find article title",
                5
        );

        Assert.assertEquals(
                "Titles text has been changed after rotation",
                "Title for test to fail",
                titleAfterRotation
        );
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

    @Test
    public void testSearchResultsTitlesCheck() {
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

        WaitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "No element with org.wikipedia:id/page_list_item_title id found",
                5
        );

        List<WebElement> elementsList = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        int elementsListLength = elementsList.size();

        for (int i = 0; i < elementsListLength; i++) {
            WebElement elementsListInstance = elementsList.get(i);
            String elementTitle = elementsListInstance.getText();
            Assert.assertTrue("One element title or more does not contains " + textToSearch, elementTitle.toLowerCase().contains(textToSearch.toLowerCase()));
        }
    }

    @Test
    public void testSearchFieldPlaceholderTextComparition() {
        WaitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "No element with org.wikipedia:id/search_container id found or unable to click",
                5
        );

        assertElementHasText(
                By.id("search_src_text"),
                "Search…",
                "Unexpected search field placeholder"
        );
    }

    @Test
    public void testDeleteArticleFromReadingList() throws InterruptedException {

        String textToSearch = "Java";
        String listName = "My test list";
        WebElement articleTitleResourceId;
        String articleTitleText;

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

        WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "No element with org.wikipedia:id/page_list_item_container resource-id found or unable to click",
                15
        );

        articleTitleResourceId = WaitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No element with org.wikipedia:id/view_page_title_text id found",
                5
        );

        articleTitleText = articleTitleResourceId.getText();

        WaitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Can not find 'More options' button",
                5
        );

        WaitForElementBeingActive(
                By.id("org.wikipedia:id/title"),
                "Could not wait for element to be active"
        );

        WaitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find 'Add to reading list' button",
                5
        );

        WaitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can not find 'GOT IT' button",
                5
        );


        WaitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find article folder input text field",
                5
        );

        WaitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Cannot put text into article folder input text field",
                5,
                listName
        );

        WaitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        WaitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' X button",
                5
        );

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

        WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Island of Indonesia']"),
                "No element with org.wikipedia:id/page_list_item_container resource-id found or unable to click",
                15
        );

        WaitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "No element with org.wikipedia:id/view_page_title_text id found",
                5
        );

        WaitForElementAndClick(
                By.xpath("//*[@content-desc='More options']"),
                "Cannot find 'More options' button",
                5
        );

        WaitForElementBeingActive(
                By.id("org.wikipedia:id/title"),
                "Could not wait for element to be active"
        );

        WaitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find 'Add to reading list' button",
                5
        );

        WaitForElementAndClick(
                By.xpath("//*[@text='" + listName + "']"),
                "Can not find '" + listName + "' button",
                5
        );

        WaitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up' X button",
                5
        );

        WaitForElementAndClick(
                By.xpath("//*[@content-desc='My lists']"),
                "Cannot find 'My lists' button",
                5
        );

        WaitForElementAndClick(
                By.xpath("//*[@text='" + listName + "']"),
                "Can not find '" + listName + "' button",
                5
        );

        SwipeElementToTheLeft(
                By.xpath("//*[@text='island of Indonesia']"),
                "Cannot find element with text 'island of Indonesia' or swipe it"
        );

        WaitForElementAndClick(
                By.xpath("//*[@text='" + articleTitleText + "']"),
                "No element with " + articleTitleText + " found or unable to click",
                5
        );

        WebElement elementTitle = WaitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        String articleTitle = elementTitle.getAttribute("text");
        Assert.assertEquals(
                "Unexpected article title",
                "Java (programming language)",
                articleTitle);
    }

    @Test
    public void testCheckArticleTitlePresent() {
        String textToSearch = "Java";
        String articleToOpen = "Object-oriented programming language";

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

        WaitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleToOpen + "']"),
                "No element with org.wikipedia:id/page_list_item_container resource-id found or unable to click",
                15
        );

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Title not found"
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

    private WebElement WaitForElementAndSendKeys(By by, String error_message, long timeoutInSeconds, String textToSend) {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(textToSend);
        return element;
    }

    private int WaitForElementsPresentAndCount(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List elements = driver.findElements(by);
        return elements.size();

    }

    private boolean WaitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement assertElementHasText(By by, String expected_text, String error_message) {

        WebElement element = WaitForElementPresent(by, error_message, 5);
        String search_field_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_text,
                search_field_text
        );
        return element;
    }

    private WebElement WaitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void SwipeElementToTheLeft(By by, String error_message) {
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

    private WebElement WaitForElementBeingActive(By by, String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.elementToBeClickable(by)
        );
    }

    private void assertElementPresent (By by, String error_message)
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

    private String WaitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = WaitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}