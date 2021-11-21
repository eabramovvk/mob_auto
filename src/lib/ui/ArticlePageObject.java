package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.jvm.hotspot.utilities.Assert;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE_ID = "id:org.wikipedia:id/view_page_title_text",
            OPTIONS_BUTTON = "xpath://*[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON_ID = "id:org.wikipedia:id/title",
            OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY_ID = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON_TEXT = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']",
            FOLDER_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";


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
        return this.WaitForElementPresent(TITLE_ID, "No element with " + TITLE_ID + " id found", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void addArticleToMyListFisrtTime(String name_of_folder)
    {
        this.WaitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find 'More options' button",
                5
        );
        //Wait for button Add to reading list
        this.WaitForElementBeingActive(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_ID,
                "Could not wait for element to be active"
        );

        this.WaitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT,
                "Can not find 'Add to reading list' button",
                5
        );

        this.WaitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY_ID,
                "Can not find 'GOT IT' button",
                5
        );

        this.WaitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find article folder input text field",
                5
        );

        this.WaitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                "Cannot put text into article folder input text field",
                5,
                name_of_folder
        );

        this.WaitForElementAndClick(
                MY_LIST_OK_BUTTON_TEXT,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle()
    {
        this.WaitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find 'Navigate up' X button",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder)
    {

        this.WaitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More options' button",
                5
        );

        this.WaitForElementBeingActive(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_ID,
                "Could not wait for element to be active"
        );

        this.WaitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT,
                "Can not find 'Add to reading list' button",
                5
        );
        String folder_name_xpath = addFolderName(name_of_folder);
        ;
        this.WaitForElementAndClick(
                folder_name_xpath,
                "Can not find '"+ name_of_folder + "' button",
                5
        );
    }

    public void checkTitlePresentWithAssert()
    {
        this.assertElementPresent(
                TITLE_ID,
                "No article title found"
        );
    }
}