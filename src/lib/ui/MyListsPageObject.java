package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import lib.Platform;
import java.awt.*;


abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            POPUP_CLOSE_BUTTON,
            POPUP_TITLE,
            SWIPE_TRASH_BUTTON;

    public MyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String addFolderName(String folder_name)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getSavedArticleByXpath(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATE METHODS */

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = addFolderName(name_of_folder);
        this.WaitForElementBeingActive(
                folder_name_xpath,
                "Cannot find folder by name"
        );

        this.WaitForElementAndClick(
                folder_name_xpath,
                "Can not click folder by name",
                5
        );
    }

    public void swipeByArticleToDelete(String articleTitle)
    {
        String article_xpath = getSavedArticleByXpath(articleTitle);


        this.SwipeElementToTheLeft(
                article_xpath,
                "Cannot swipe article"
        );

        if (Platform.getInstance().isIOS())
        {
            this.WaitForElementAndClick(SWIPE_TRASH_BUTTON, "Cannot trash article", 5);
        }
    }

    public void openArticle(String articleTitle)
    {
        String article_xpath = getSavedArticleByXpath(articleTitle);
        if (Platform.getInstance().isAndroid())
        {
            this.WaitForElementBeingActive(
                    article_xpath,
                    "Could not wait for article to be active"
            );
        }
        this.WaitForElementAndClick(
                article_xpath,
                "No element with " + article_xpath + " found or unable to click",
                5
        );
    }

    public void closeSyncPopup(){
        this.WaitForElementAndClick(POPUP_CLOSE_BUTTON, "Cannot press sync popup close button", 5);
    }
}
