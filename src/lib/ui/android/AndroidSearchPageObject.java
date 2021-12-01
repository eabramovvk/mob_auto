package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENTS = "id:org.wikipedia:id/page_list_item_container";
        SEARCH_RESULT_ID = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULTS_TITLE_AND_DESCRIPTION_COMPARISION_TPL = "xpath://ancestor::*[*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '{TITLE}')] and *[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text, '{DESCRIPTION}')]]";
    }

    public AndroidSearchPageObject (AppiumDriver driver)
    {
        super(driver);
    }
}