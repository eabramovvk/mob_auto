package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class SearchPageObject extends MainPageObject
{

        protected static String
                SEARCH_INIT_ELEMENT,
                SEARCH_INPUT,
                SEARCH_CANCEL_BUTTON,
                SEARCH_RESULT_BY_SUBSTRING_TPL,
                SEARCH_RESULT_ELEMENTS,
                SEARCH_RESULT_ID,
                SEARCH_RESULTS_TITLE_AND_DESCRIPTION_COMPARISION_TPL;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.WaitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click SEARCH_INIT_ELEMENT", 5);
        this.WaitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search element after clicking search init element", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.WaitForElementAndSendKeys(SEARCH_INPUT, "Cannot find SEARCH_TEXT_FIELD or send SEARCH_INPUT keys", 5, search_line);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.WaitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring, 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.WaitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForSearchResultsToDisapper()
    {
        this.WaitForElementNotPresent(SEARCH_RESULT_ELEMENTS, "Search results are still present", 5);
    }

    public void clickCancelSearch()
    {
        this.WaitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot press cancel search button", 5);
    }

    public void checkSearchResultsCount(int expected_quantity)
    {
        this.WaitForElementPresent(SEARCH_RESULT_ELEMENTS, "Cannot wait for SEARCH_RESULT_ELEMENTS", 5);
        this.assertElementsCountComparision(SEARCH_RESULT_ELEMENTS, "Not expected elements count", 5, expected_quantity);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.WaitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }
}
