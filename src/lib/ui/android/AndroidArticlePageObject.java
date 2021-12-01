package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE_ID = "id:org.wikipedia:id/view_page_title_text";
        OPTIONS_BUTTON = "xpath://*[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON_ID = "id:org.wikipedia:id/title";
        OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT = "xpath://*[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY_ID = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON_TEXT = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']";
        FOLDER_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        FOOTER_ELEMENT = "xpath://*[@text=''View page in browser]";
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}