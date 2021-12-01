package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "id:{TITLE}"; //"xpath://*[@text='{TITLE}']"       "xpath://XCUIElementTypeStaticText[contains(@name='{TITLE}')]"
        POPUP_CLOSE_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
        POPUP_TITLE  = "id:Sync your saved articles?";
        SWIPE_TRASH_BUTTON = "id:swipe action delete";
    }
    public iOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}