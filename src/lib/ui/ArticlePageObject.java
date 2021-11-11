package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.jvm.hotspot.utilities.Assert;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE_ID = "org.wikipedia:id/view_page_title_text",
            OPTIONS_BUTTON = "//*[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON_ID = "org.wikipedia:id/title",
            OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY_ID = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON_TEXT = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//*[@content-desc='Navigate up']",
            FOLDER_NAME_TPL = "//*[@text='{SUBSTRING}']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String addFolderName(String substring)
    {
        return FOLDER_NAME_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public WebElement waitForTitleElement()
    {
        return this.WaitForElementPresent(By.id(TITLE_ID), "No element with " + TITLE_ID + " id found", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void addArticleToMyListFisrtTime(String name_of_folder)
    {
        this.WaitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can not find 'More options' button",
                5
        );
        //Wait for button Add to reading list
        this.WaitForElementBeingActive(
                By.id(OPTIONS_ADD_TO_MY_LIST_BUTTON_ID),
                "Could not wait for element to be active"
        );

        this.WaitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT),
                "Can not find 'Add to reading list' button",
                5
        );

        this.WaitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY_ID),
                "Can not find 'GOT IT' button",
                5
        );

        this.WaitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find article folder input text field",
                5
        );

        this.WaitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot put text into article folder input text field",
                5,
                name_of_folder
        );

        this.WaitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON_TEXT),
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle()
    {
        this.WaitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find 'Navigate up' X button",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder)
    {

        this.WaitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find 'More options' button",
                5
        );

        this.WaitForElementBeingActive(
                By.id(OPTIONS_ADD_TO_MY_LIST_BUTTON_ID),
                "Could not wait for element to be active"
        );

        this.WaitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT),
                "Can not find 'Add to reading list' button",
                5
        );
        String folder_name_xpath = addFolderName(name_of_folder);
        ;
        this.WaitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Can not find '"+ name_of_folder + "' button",
                5
        );
    }

    public void checkTitlePresentWithAssert()
    {
        this.assertElementPresent(
                By.id(TITLE_ID),
                "No article title found"
        );
    }
}