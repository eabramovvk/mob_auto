package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE_ID = "id:Java (programming language)";
        OPTIONS_ADD_TO_MY_LIST_BUTTON_ID = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        FOLDER_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        FOOTER_ELEMENT = "id:View article in browser";
    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}