package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

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
                By.xpath(folder_name_xpath),
                "Cannot find folder by name"
        );

        this.WaitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Can not click folder by name",
                5
        );
    }

    public void swipeByArticleToDelete(String articleTitle)
    {
        String article_xpath = getSavedArticleByXpath(articleTitle);
        this.WaitForElementBeingActive(
                By.xpath(article_xpath),
                "Could not wait for article to be active"
        );

        this.SwipeElementToTheLeft(
                By.xpath(article_xpath),
                "Cannot swipe article"
        );
    }

    public void openArticle(String articleTitle)
    {
        String article_xpath = getSavedArticleByXpath(articleTitle);
        this.WaitForElementBeingActive(
                By.xpath(article_xpath),
                "Could not wait for article to be active"
        );
        this.WaitForElementAndClick(
                By.xpath(article_xpath),
                "No element with " + article_xpath + " found or unable to click",
                5
        );
    }
}
