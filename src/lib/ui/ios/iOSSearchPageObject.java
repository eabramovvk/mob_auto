package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENTS = "xpath://XCUIElementTypeCell";
        SEARCH_RESULT_ID = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULTS_TITLE_AND_DESCRIPTION_COMPARISION_TPL = "xpath://ancestor::*[*[//XCUIElementTypeStaticText and contains(@name, '{TITLE}')] and *[//XCUIElementTypeStaticText and contains(@name, '{DESCRIPTION}')]]";

    }

    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }


}